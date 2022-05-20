# BlocKing

![image](https://user-images.githubusercontent.com/77562357/169428775-87e21583-f356-47d4-8a75-775f3cf78db4.png)

## 프로젝트 소개
- `대전`과 `아이템` 이라는 게임요소가 추가된 텍스트 기반의 테트리스

### Battle
![image](https://user-images.githubusercontent.com/77562357/169429046-0d0eba6b-a443-4062-a663-4be250483d32.png)
- 2인 플레이를 지원하여 서로 경쟁하는 게임 모드 입니다.
    - 1P
        - 블럭 이동 : 키보드 화살표 좌, 하, 우, Space Bar
        - 블럭 회전 : 키보드 화살표 상
    - 2P
        - 블럭 이동 : A, S, D, F
        - 블럭 회전 : W
        
- 블록을 2줄 이상 삭제한 경우 상대편에게 블럭을 보내는 공격기능을 지원합니다.

- 재밌습니다.

### Items

![image](https://user-images.githubusercontent.com/77562357/169430157-ea7d51d7-9414-4a7d-8ac4-f33f0fcfee58.png)
- 개성있는 아이템 요소를 추가하여 더 역동적으로 진행하는 게임 모드 입니다.
   - Weight (무게추) : 무게추 아이템은 떨어지면서 밑에 있는 모든 블럭을 지우는 아이템입니다.



   - LineClear (줄 삭제) : 줄 삭제 아이템은 기본 블록에 문자 'L'의 형태로 나타내어지고, 블럭이 바닥에 닿거나 더이상 움직이지 못하면 L이 위치한 줄이 다 채워져 있지 않더라도 해당 줄을 전부 삭제합니다.
       - ![image](https://user-images.githubusercontent.com/77562357/169430192-45455ff4-1941-47bc-9b8b-bb20a4fa639f.png)
   - Resurrection (부활) : Game over 위기에 처하면 보드의 위 10줄(보드의 절반)을 제거하는 아이템입니다.
   - DoubleBonusChance (점수 2배) : 일정 시간동안 획득하는 점수가 2배가 되는 아이템입니다.
   - SmallBlockChance (1칸 블럭) : 1칸의 블럭이 생성되는 아이템입니다.

### Feature

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
