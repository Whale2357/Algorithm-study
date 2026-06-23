import os
import sys
from datetime import date
from urllib.parse import quote

import requests

NOTION_TOKEN = os.environ["NOTION_TOKEN"]
DATABASE_ID = os.environ["NOTION_DATABASE_ID"]
GITHUB_REPOSITORY = os.environ.get("GITHUB_REPOSITORY", "")

# 워크플로에서 여러 줄(FILE_PATHS) 또는 단일 경로(FILE_PATH)로 전달될 수 있다.
raw_paths = os.environ.get("FILE_PATHS") or os.environ.get("FILE_PATH", "")
file_paths = [p.strip() for p in raw_paths.splitlines() if p.strip()]

if not file_paths:
    # programmers 폴더 변경이 없는 푸시는 정상 종료 (워크플로 실패 아님)
    print("동기화할 programmers 파일이 없습니다. 종료합니다.")
    sys.exit(0)

headers = {
    "Authorization": f"Bearer {NOTION_TOKEN}",
    "Content-Type": "application/json",
    "Notion-Version": "2022-06-28",
}


def parse_problem(file_path):
    parts = file_path.split("/")
    # programmers/<level>/<filename> 형태를 기대
    level = parts[1].capitalize() if len(parts) >= 3 else "Unknown"
    filename = parts[-1]
    problem_name = os.path.splitext(filename)[0].replace("_", " ")
    return problem_name, level


def build_payload(file_path):
    problem_name, level = parse_problem(file_path)
    encoded_path = quote(file_path, safe="/")
    github_url = f"https://github.com/{GITHUB_REPOSITORY}/blob/main/{encoded_path}"
    return {
        "parent": {"database_id": DATABASE_ID},
        "properties": {
            "문제명": {"title": [{"text": {"content": problem_name}}]},
            "플랫폼": {"select": {"name": "Programmers"}},
            "난이도": {"select": {"name": level}},
            "풀이 날짜": {"date": {"start": str(date.today())}},
            "GitHub": {"url": github_url},
            "재풀이": {"checkbox": False},
            "메모": {"rich_text": []},
        },
    }


had_error = False

for file_path in file_paths:
    print(f"동기화 중: {file_path}")
    payload = build_payload(file_path)
    response = requests.post(
        "https://api.notion.com/v1/pages",
        headers=headers,
        json=payload,
    )
    print(response.status_code)

    if response.status_code >= 400:
        print(response.text)
        had_error = True
    else:
        print(f"등록 완료: {file_path}")

if had_error:
    # 하나라도 실패하면 워크플로를 실패 처리해서 문제를 드러낸다.
    sys.exit(1)
