import os
import requests
from datetime import date

NOTION_TOKEN = os.environ["NOTION_TOKEN"]
DATABASE_ID = os.environ["NOTION_DATABASE_ID"]

FILE_PATH = os.environ["FILE_PATH"]

parts = FILE_PATH.split("/")

level = parts[1].capitalize()
filename = parts[-1]

problem_name = os.path.splitext(filename)[0]
problem_name = problem_name.replace("_", " ")

headers = {
"Authorization": f"Bearer {NOTION_TOKEN}",
"Content-Type": "application/json",
"Notion-Version": "2022-06-28",
}

github_url = (
f"https://github.com/{os.environ['GITHUB_REPOSITORY']}/blob/main/{FILE_PATH}"
)

payload = {
"parent": {"database_id": DATABASE_ID},
"properties": {
"문제명": {
"title": [
{
"text": {
"content": problem_name
}
}
]
},
"플랫폼": {
"select": {
"name": "Programmers"
}
},
"난이도": {
"select": {
"name": level
}
},
"풀이 날짜": {
"date": {
"start": str(date.today())
}
},
"GitHub": {
"url": github_url
},
"재풀이": {
"checkbox": False
},
"메모": {
"rich_text": []
}
}
}

response = requests.post(
"https://api.notion.com/v1/pages",
headers=headers,
json=payload
)

res = requests.get(
    f"https://api.notion.com/v1/databases/{DATABASE_ID}",
    headers=headers
)

print(res.status_code)
print(res.text)

print(response.status_code)
print(response.text)
