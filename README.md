# Algorithm-study

알고리즘 학습을 위해 직접 푼 문제들을 정리하는 개인 저장소입니다.
주로 [프로그래머스(Programmers)](https://programmers.co.kr/) 문제를 **Java**로 풀이하며,
`main` 브랜치에 풀이를 푸시(push)하면 GitHub Actions가 자동으로 Notion 데이터베이스(Database)에 기록을 남깁니다.

---

## 진행 현황 / Progress

> 아래 표는 `main` 브랜치 푸시(push) 시 GitHub Actions가 자동으로 갱신합니다. (직접 수정하지 마세요)

<!-- PROGRESS:START -->
| 난이도 (Level) | 푼 문제 수 (Solved) |
| --- | --- |
| Lv2 | 4 |
| Lv3 | 10 |
| **합계 (Total)** | **14** |
<!-- PROGRESS:END -->

---

## 폴더 구조 / Structure

```
algorithm-study/
├─ programmers/
│  ├─ lv2/                # 프로그래머스 Level 2 풀이
│  └─ lv3/                # 프로그래머스 Level 3 풀이
├─ scripts/
│  ├─ notion_sync.py      # Notion 동기화(Sync) 스크립트
│  └─ update_progress.py  # 진행 현황 표 갱신 스크립트
├─ .github/workflows/
│  ├─ notion.yml          # Notion 자동 동기화 워크플로(Workflow)
│  └─ progress.yml        # 진행 현황 자동 갱신 워크플로(Workflow)
└─ README.md
```

---

## 풀이 / 사용 방법 (How to Use)

1. `programmers/<레벨>/` 경로에 풀이 `.java` 파일을 추가합니다.
   - 파일명(Filename)은 **문제 이름**으로 지정합니다. (예: `네트워크.java`)
2. 각 파일은 프로그래머스 제출(Submit) 형식인 `Solution` 클래스를 그대로 사용합니다.

```java
class Solution {
    public int solution(int n, int[][] computers) {
        // 풀이 코드 (solution code)
        return answer;
    }
}
```

3. 로컬에서 컴파일(Compile)/실행하려면:

```bash
javac "programmers/lv3/네트워크.java"
```

> 참고: 프로그래머스 제출용 코드라 `package` 선언이 없습니다.
> IDE의 Java 확장(Extension)에서 패키지 불일치 경고(Warning)가 보일 수 있으나 제출/실행에는 영향이 없습니다.

---

## 커밋 & 푸시 (Commit & Push)

풀이 파일을 추가/수정한 뒤 아래 순서로 커밋(Commit)하고 푸시(Push)합니다.

```bash
git add .
git commit -m "solve: 문제이름"
git push
```

푸시가 끝나면 GitHub Actions가 자동으로 실행됩니다.

- **Notion Sync**: 변경된 풀이를 Notion 데이터베이스에 등록
- **Update Progress**: 위 [진행 현황](#진행-현황--progress) 표를 갱신하고 `chore: 진행 현황 자동 갱신` 커밋을 자동 추가

> **중요**: Update Progress 워크플로가 원격(remote)에 커밋을 하나 더 추가하므로,
> 다음 작업 전에 반드시 원격 변경을 받아와야 다음 push가 거부(rejected)되지 않습니다.
>
> ```bash
> git pull --rebase
> ```
>
> 만약 `! [rejected] ... (fetch first)` 오류가 나면, 위 명령으로 원격 커밋을 받아온 뒤 다시 `git push` 하면 됩니다.

---

## Notion 자동 연동 (Auto-Sync)

`main` 브랜치에 `programmers/` 하위 파일이 변경되어 푸시되면,
GitHub Actions(`.github/workflows/notion.yml`)가 실행되어 변경된 파일을 Notion 데이터베이스에 자동 등록합니다.

동작 흐름(Flow):

1. `main` 브랜치 푸시(push)를 감지합니다.
2. 직전 커밋(Commit)과 비교해 변경된 `programmers/` 파일 목록을 추출합니다.
3. `scripts/notion_sync.py`가 각 파일에 대해 Notion 페이지(Page)를 생성합니다.

Notion에 기록되는 속성(Property):

| 속성 (Property) | 설명 |
| --- | --- |
| 문제명 (Title) | 파일명에서 추출한 문제 이름 |
| 플랫폼 (Platform) | `Programmers` 고정값 |
| 난이도 (Level) | 폴더명 기반 (예: `Lv2`, `Lv3`) |
| 풀이 날짜 (Date) | 동기화된 날짜 |
| GitHub (URL) | 해당 파일의 GitHub 링크 |
| 재풀이 (Checkbox) | 기본값 `false` |
| 메모 (Memo) | 직접 작성용 빈 필드 |
| 키워드 (Keyword) | 직접 작성용 빈 필드 |

### 설정 (Setup)

동기화를 사용하려면 저장소 Settings → Secrets에 아래 값을 등록해야 합니다.

| Secret | 설명 |
| --- | --- |
| `NOTION_TOKEN` | Notion 통합(Integration) 토큰 |
| `NOTION_DATABASE_ID` | 대상 Notion 데이터베이스 ID |

추가로 Notion 데이터베이스에 위 표의 속성들이 동일한 이름/타입(Type)으로 존재해야 합니다.

---

## 컨벤션 (Conventions)

- **언어 (Language)**: Java
- **폴더 (Folder)**: `programmers/<레벨 소문자>/` (예: `lv2`, `lv3`)
- **파일명 (Filename)**: 문제 이름 그대로 (`문제이름.java`)
  - Notion에 등록될 때 확장자를 제외한 파일명이 그대로 문제명이 됩니다.
  - 언더스코어(`_`)는 공백으로 변환됩니다. (`두_수의_합.java` → `두 수의 합`)
- **클래스 (Class)**: 프로그래머스 제출 형식인 `class Solution` 사용
- **커밋 (Commit)**: 문제 1개당 1커밋 권장 (Notion 동기화 단위가 깔끔해집니다)
