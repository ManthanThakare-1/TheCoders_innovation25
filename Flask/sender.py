import requests
server = "127.0.0.1:5000"

data = {"text":"Hello world"}
response = requests.post(f"http://{server}/send", data=data)
print(response.text)