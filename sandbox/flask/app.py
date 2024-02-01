# import from module:flask class:Flask, render_template
from flask import Flask, render_template

# initiate Flask application
app = Flask(__name__) # __name__ refers to name of file (app.py)

# decorators. Used in Flask to associate a particular function with a particular URL.
@app.route("/")
def index():
    return render_template("index.html")

@app.route("/sample")
def sample():
    return "You are at the sample page."
