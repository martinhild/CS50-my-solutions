#import class:Flask from module:flask
from flask import Flask

#initiate Flask application
app = Flask(__name__)

#decorator. used in Flask to associate a particular function with a particular URL.
@app.route("/")
def index():
    return "You are at the index page."

