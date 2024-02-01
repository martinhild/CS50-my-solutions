#import class:Flask from module:flask
from flask import Flask

#initiate Flask application
app = Flask(__name__)

#decorator

@app.route("/")
