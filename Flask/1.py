import flask import Flask
app = Flask(__name__)

@app.@app.route('/route_name')
def getMessage():
    return 'Hello World!'
def method_name():
    pass

if __name__ == '__main__':

    app.run(debug=True)
