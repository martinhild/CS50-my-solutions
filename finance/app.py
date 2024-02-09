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
    return apology("TODO")


@app.route("/buy", methods=["GET", "POST"])
@login_required
def buy():
    """Buy shares of stock"""

    if request.method == "POST":
        # Get user inputs
        symbol = request.form.get("symbol")
        shares = request.form.get("shares")
        quote = lookup(symbol)

        # Check if inputs are valid
        if not quote:
            return apology("No valid symbol")
        elif shares == "" or int(shares) < 1:
            return apology("Enter a positive number of shares")
        # if inputs are valid check if user can afford transaction
        else:
            cost = int(shares) * quote["price"]
            userid = session["user_id"]
            rows = db.execute("SELECT cash FROM users WHERE id = ?", userid)
            cash = rows[0]["cash"]
            if(cost > cash):
                return apology("Can't afford")
            else:
                username = db.execute("SELECT username FROM users WHERE id = ?", userid)[0]["username"]
                action = "buy"
                symbol = quote["symbol"]
                price = quote["price"]
                amount = int(shares)
                x = datetime.datetime.now()
                db.execute(
                    "INSERT INTO transactions (username, action, symbol, price, amount, datetime) VALUES (?, ?, ?, ?, ?, ?)", username, action, symbol, price, amount, x
                )
                return apology("Not implemented yet.")

    # If request method is "GET"
    else:
        return render_template("buy.html")

@app.route("/history")
@login_required
def history():
    """Show history of transactions"""
    return apology("TODO")


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
        return render_template("quote.html")
    else:
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
        # HIER HAB ICH AUFGEHÖRT. ALS NÄCHSTES DEN INSERT ANSCHAUEN
        sql = "INSERT INTO users (username, hash) VALUES (%s, %s)"
        val = ("John", "Highway 21")
        db.execute("INSERT INTO users (username, hash) VALUES(?, ?)", username, generate_password_hash(password))
        return render_template("login.html")
    # If request.method was "POST"
    else:
        return render_template("register.html")

@app.route("/sell", methods=["GET", "POST"])
@login_required
def sell():
    """Sell shares of stock"""
    return apology("TODO")
