# BlocKing

## 프로젝트 소개
- 기본적인 테트리스 게임을 구현하고, 다섯가지의 아이템을 사용 가능한 아이템 모드를 추가했습니다.

### 아이템 명세
 - Weight (무게추) : 무게추 아이템은 떨어지면서 밑에 있는 모든 블럭을 지우는 아이템입니다.
 - LineClear (줄 삭제) : 줄 삭제 아이템은 기본 블록에 문자 'L'의 형태로 나타내어지고, 블럭이 바닥에 닿거나 더이상 움직이지 못하면 L이 위치한 줄이 다 채워져 있지 않더라도 해당 줄을 전부 삭제합니다.
 - Resurrection (부활) : Game over 위기에 처하면 보드의 위 10줄(보드의 절반)을 제거하는 아이템입니다.
 - DoubleBonusChance (점수 2배) : 일정 시간동안 획득하는 점수가 2배가 되는 아이템입니다.
 - SmallBlockChance (1칸 블럭) : 1칸의 블럭이 생성되는 아이템입니다.

## Convention

### java Convention
- **directory** : 소문자로만 작성합니다.
  - ex) main, setting
- **ClassName** : 각 단어의 첫번째 문자는 대문자로 시작합니다.
  - ex) ScoreList
- **변수 및 함수명** : 첫 단어는 소문자, 두번째 단어부터는 대문자로 시작합니다. camelCase
  - ex) mainPanel

### commit Type
- feat: 새로운 기능 추가
- fix: 버그 수정
- docs: 문서 수정
- style: 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우
- refactor: 코드 리팩토링
- test: 테스트 코드, 리팩토링 테스트 코드 추가
- chore: 빌드 업무 수정, 패키지 매니징 

### branch
1. dev
2. dev1.1, dev1.2 ... (sprint 기준)
3. dev1.0, dev2.0 ... (requirements 기준)
