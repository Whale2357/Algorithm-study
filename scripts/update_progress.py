"""programmers 폴더의 풀이 개수를 세어 README의 진행 현황 표를 자동 갱신한다."""

import re
from pathlib import Path

ROOT = Path(__file__).resolve().parent.parent
PROGRAMMERS_DIR = ROOT / "programmers"
README = ROOT / "README.md"

START_MARKER = "<!-- PROGRESS:START -->"
END_MARKER = "<!-- PROGRESS:END -->"


def count_solutions():
    """레벨 폴더별 .java 파일 개수를 센다. {레벨: 개수} 형태로 반환."""
    counts = {}
    if not PROGRAMMERS_DIR.exists():
        return counts

    for level_dir in sorted(PROGRAMMERS_DIR.iterdir()):
        if not level_dir.is_dir():
            continue
        n = len(list(level_dir.glob("*.java")))
        if n > 0:
            counts[level_dir.name] = n
    return counts


def build_table(counts):
    lines = [
        "| 난이도 (Level) | 푼 문제 수 (Solved) |",
        "| --- | --- |",
    ]
    total = 0
    for level, n in counts.items():
        # lv2 -> Lv2 표기
        label = level.capitalize()
        lines.append(f"| {label} | {n} |")
        total += n
    lines.append(f"| **합계 (Total)** | **{total}** |")
    return "\n".join(lines)


def update_readme(table):
    text = README.read_text(encoding="utf-8")
    pattern = re.compile(
        re.escape(START_MARKER) + r".*?" + re.escape(END_MARKER),
        re.DOTALL,
    )
    replacement = f"{START_MARKER}\n{table}\n{END_MARKER}"

    if not pattern.search(text):
        raise SystemExit("README에 PROGRESS 마커가 없습니다.")

    new_text = pattern.sub(replacement, text)
    if new_text != text:
        README.write_text(new_text, encoding="utf-8")
        print("README 진행 현황을 갱신했습니다.")
    else:
        print("변경 사항이 없습니다.")


def main():
    counts = count_solutions()
    table = build_table(counts)
    update_readme(table)


if __name__ == "__main__":
    main()
