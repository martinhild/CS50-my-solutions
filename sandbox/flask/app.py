# import class:Flask from module:flask
from flask import Flask

# initiate Flask application
app = Flask(__name__) # __name__ refers to name of file (app.py)

# decorators. Used in Flask to associate a particular function with a particular URL.
@app.route("/")
def index():
    return "You are at the index page."

@app.route("/")
def sample():
    return "You are at the sample page."
