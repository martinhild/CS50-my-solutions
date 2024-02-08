from cs50 import SQL

db = SQL("sqlite:///finance.db")

rows = db.execute("SELECT * FROM users WHERE username = ?", "username")

print(len(rows))

