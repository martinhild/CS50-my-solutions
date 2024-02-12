import os
import datetime

from cs50 import SQL
from flask import Flask, flash, redirect, render_template, request, session
from flask_session import Session
from werkzeug.security import check_password_hash, generate_password_hash

from helpers import apology, login_required, lookup, usd

# Configure application
app = Flask(__name__)

# Custom filter
app.jinja_env.filters["usd"] = usd

# Configure session to use filesystem (instead of signed cookies)
app.config["SESSION_PERMANENT"] = False
app.config["SESSION_TYPE"] = "filesystem"
Session(app)

# Configure CS50 Library to use SQLite database
db = SQL("sqlite:///finance.db")


@app.after_request
def after_request(response):
    """Ensure responses aren't cached"""
    response.headers["Cache-Control"] = "no-cache, no-store, must-revalidate"
    response.headers["Expires"] = 0
    response.headers["Pragma"] = "no-cache"
    return response


@app.route("/")
@login_required
def index():
    """Show portfolio of stocks"""
    userid = session["user_id"]
    username = db.execute("SELECT username FROM users WHERE id = ?", userid)[0]["username"]
    stocks = db.execute("SELECT symbol, amount FROM stocks WHERE username = ?", username)
    list = []
    whole_total = 0
    for stock in stocks:
        price_per_share = lookup(stock["symbol"])["price"]
        total = stock["amount"] * price_per_share
        dic = {"symbol": stock["symbol"],
               "shares": stock["amount"],
               "price": usd(price_per_share),
               "total": usd(total)}
        list.append(dic)
        whole_total += total
    cash = db.execute("SELECT cash FROM users WHERE id = ?", userid)[0]["cash"]
    whole_total = usd(cash + whole_total)
    cash = usd(cash)
    message = "Hi " + username
    return render_template("index.html", list=list, whole_total=whole_total, cash=cash, message=message)


@app.route("/buy", methods=["GET", "POST"])
@login_required
def buy():
    """Buy shares of stock"""

    if request.method == "POST":
        # Check if inputs are valid
        if ";" in request.form.get("symbol") or "'" in request.form.get("symbol"):
            return apology("Character not allowed")
        if ";" in request.form.get("shares") or "'" in request.form.get("shares"):
            return apology("Character not allowed")
        if request.form.get("symbol") == "":
            return apology("Enter a symbol")
        if request.form.get("shares") == "":
            return apology("Enter shares")

        try:
            shares = int(request.form.get("shares"))
        except ValueError:
            return apology("No whole number")

        if shares < 1:
            return apology("Enter a positive number of shares")
        if not request.form.get("symbol"):
            return apology("No valid symbol")

        # Get user inputs
        symbol = request.form.get("symbol")

        quote = lookup(symbol)
        if shares == "" or shares < 1:
            return apology("Enter a positive number of shares")

        if not quote:
            return apology("No valid symbol")
        elif shares == "" or shares < 1:
            return apology("Enter a positive number of shares")
        # if inputs are valid check if user can afford transaction
        else:
            cost = shares * quote["price"]
            userid = session["user_id"]
            rows = db.execute("SELECT cash FROM users WHERE id = ?", userid)
            cash = rows[0]["cash"]
            if cost > cash:
                return apology("Can't afford")
            else:
                username = db.execute("SELECT username FROM users WHERE id = ?", userid)[0]["username"]
                action = "buy"
                symbol = quote["symbol"]
                price = quote["price"]
                t = datetime.datetime.now()

                # Upate user's cash in table 'users'
                cash = db.execute("SELECT cash FROM users WHERE id = ?", userid)[0]["cash"]
                cash = cash - cost
                db.execute("UPDATE users SET cash = ? WHERE id = ?", cash, userid)

                # Save the transaction into 'transactions' table
                db.execute("INSERT INTO transactions(username, action, symbol, price, amount, datetime) VALUES(?, ?, ?, ?, ?, ?)",
                           username, action, symbol, price, shares, t)

                # Save the transaction into 'stocks' table
                current_shares = db.execute("SELECT amount FROM stocks WHERE username = ? AND symbol = ?", username, symbol)
                if current_shares: #If user already owns shares of this stock
                    new_shares = current_shares[0]["amount"] + shares
                    db.execute("UPDATE stocks SET amount = ? WHERE username = ? AND symbol = ?", new_shares, username, symbol)
                else:
                    db.execute("INSERT INTO stocks (username, symbol, amount) VALUES (?, ?, ?)", username, symbol, shares)

                return redirect("/")

    # If request method is "GET"
    else:
        message = "Buy shares"
        return render_template("buy.html", message=message)


@app.route("/history")
@login_required
def history():
    """Show history of transactions"""
    userid = session["user_id"]
    username = db.execute("SELECT username FROM users WHERE id = ?", userid)[0]["username"]
    transactions = db.execute("SELECT action, symbol, price, amount, datetime FROM transactions WHERE username = ?", username)
    # format price to US Dollar
    for transaction in transactions:
        transaction["price"] = usd(transaction["price"])
    # reverse table so new transactions are at the top
    transactions.reverse()
    message = "History of your transactions"
    return render_template("history.html", transactions=transactions, message=message)


@app.route("/login", methods=["GET", "POST"])
def login():
    """Log user in"""

    # Forget any user_id
    session.clear()

    # User reached route via POST (as by submitting a form via POST)
    if request.method == "POST":
        # Ensure username was submitted
        if not request.form.get("username"):
            return apology("must provide username", 403)

        # Ensure password was submitted
        elif not request.form.get("password"):
            return apology("must provide password", 403)

        # Query database for username
        rows = db.execute(
            "SELECT * FROM users WHERE username = ?", request.form.get("username")
        )

        # Ensure username exists and password is correct
        if len(rows) != 1 or not check_password_hash(
            rows[0]["hash"], request.form.get("password")
        ):
            return apology("invalid username and/or password", 403)

        # Remember which user has logged in
        session["user_id"] = rows[0]["id"]

        # Redirect user to home page
        return redirect("/")

    # User reached route via GET (as by clicking a link or via redirect)
    else:
        return render_template("login.html")


@app.route("/logout")
def logout():
    """Log user out"""

    # Forget any user_id
    session.clear()

    # Redirect user to login form
    return redirect("/")


@app.route("/quote", methods=["GET", "POST"])
@login_required
def quote():
    """Get stock quote."""
    if request.method == "GET":
        message = "Search for prices of stocks"
        return render_template("quote.html", message=message)
    else:
        if ";" in request.form.get("symbol") or "'" in request.form.get("symbol"):
            return apology("Character not allowed")
        if not lookup(request.form.get("symbol")):
            return apology("No valid symbol")
        else:
            quote = lookup(request.form.get("symbol"))
            price = usd(quote["price"])
            symbol = (quote["symbol"])
            return render_template("quoted.html", symbol=symbol, price=price)


@app.route("/register", methods=["GET", "POST"])
def register():
    """Register user"""
    if request.method == "POST":
        # Check if user entered a username and if the username already exists
        username = request.form.get("username")
        if username == "":
            return apology("Enter a username")
        if len(db.execute("SELECT * FROM users WHERE username = ?", username)) != 0:
            return apology("Username already exists")
        # Check if user entered two identical passwords (password and confirmation)
        password = request.form.get("password")
        confirmation = request.form.get("confirmation")
        if password == "" or confirmation == "":
            return apology("Enter a password and confirm it")
        if password != confirmation:
            return apology("You entered two different passwords")
        # Insert the new user into users, storing a hash of the user’s password, not the password itself
        db.execute("INSERT INTO users (username, hash) VALUES(?, ?)", username, generate_password_hash(password))
        return render_template("login.html")
    # If request method was "POST"
    else:
        return render_template("register.html")


@app.route("/sell", methods=["GET", "POST"])
@login_required
def sell():
    """Sell shares of stock"""
    if request.method == "GET":
        username = db.execute("SELECT username FROM users WHERE id = ?", session["user_id"])[0]["username"]
        stocks = db.execute("SELECT symbol FROM stocks WHERE  username = ?", username)
        message = "Sell shares."
        return render_template("sell.html", stocks=stocks, message=message)

    # If request method was "POST"
    else:
        if ";" in request.form.get("symbol") or "'" in request.form.get("symbol"):
            return apology("Character not allowed")
        if ";" in request.form.get("shares") or "'" in request.form.get("shares"):
            return apology("Character not allowed")
        symbol = request.form.get("symbol")
        # No symbol
        if symbol == "":
            return apology("You didn't select a stock")
        if int(request.form.get("shares")) < 1:
            return apology("Select at least one.")

        username = db.execute("SELECT username FROM users WHERE id = ?", session["user_id"])[0]["username"]
        owned = db.execute("SELECT amount FROM stocks WHERE  username = ? AND symbol = ?", username, symbol)[0]["amount"]
        # If user does not own shares of this stock
        if not owned:
            return apology("You don't own any shares of this stock")

        shares_to_sell = int(request.form.get("shares"))
        # If user wants to sell more shares of a stock than he owns
        if shares_to_sell > owned:
         return apology("You don't own enough shares of this stock")

        # User owns enough shares. Can sell.
        else:
            # Selling
            price_per_share = lookup(symbol)["price"]


            # Save this transaction in table 'transactions'
            action = "sell"
            t = datetime.datetime.now()
            db.execute("INSERT INTO transactions(username, action, symbol, price, amount, datetime) VALUES(?, ?, ?, ?, ?, ?)",
                           username, action, symbol, price_per_share, shares_to_sell, t)

            # Sell/remove amount of shares of stock from table 'stocks'
            if shares_to_sell < owned:
                new_shares = owned - shares_to_sell
                db.execute("UPDATE stocks SET amount = ? WHERE username = ? AND symbol = ?", new_shares, username, symbol)
            else:
                db.execute("DELETE FROM stocks WHERE username = ? AND symbol = ?", username, symbol)

            # Add money to users 'cash' in table 'users'
            total_price = shares_to_sell * price_per_share
            current_cash = db.execute("SELECT cash FROM users WHERE username = ?", username)[0]["cash"]
            new_cash = current_cash + total_price
            db.execute("UPDATE users SET cash = ? WHERE username = ?", new_cash, username)
            return redirect("/")


@app.route("/password", methods=["GET", "POST"])
@login_required
def password():
    """Change user's password"""
    if request.method == "GET":
        return render_template("password.html")
    else:
        # Check for missing input
        if request.form.get("username") == "":
            return apology("Didn't enter username")
        if request.form.get("old_password") == "":
            return apology("Didn't enter old password")
        if request.form.get("new_password") == "":
            return apology("Didn't enter new password")
        if request.form.get("confirmation") == "":
            return apology("Didn't confirm new password")

        # Check for correct input
        userdata = db.execute("SELECT * FROM users WHERE id = ?", session["user_id"])[0]
        if request.form.get("username") != userdata["username"]:
            return apology("Wrong username")
        if not check_password_hash(userdata["hash"], request.form.get("old_password")):
            return apology("Old password was wrong", 403)
        if not request.form.get("new_password") == request.form.get("confirmation"):
            return apology("New password and confirmation was not the same")

        # set new password
        db.execute("UPDATE users SET hash = ? WHERE id = ?", generate_password_hash(request.form.get("new_password")), session["user_id"])
        return redirect("/")

