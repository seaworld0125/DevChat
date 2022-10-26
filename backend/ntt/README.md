## 브랜치 전략
#### 이슈 기반 branch 생성

#### `main`

- 제품을 최종적으로 배포하는 브랜치 (`realease` 브랜치로부터 merge만 받는 브랜치)
- 배포에 사용됨

#### `feature`

- 새로운 기능 개발을 하는 브랜치
  - 반드시 develop 로부터 시작되고, develop 브랜치에 머지함
  - **feature/기능이름**
    ex) `feature/new-feature`

#### `realease`

- 배포 전, QA를 진행하는 브랜치
- 아직 배포되지 않은 공용 브랜치
- `feature` 브랜치로부터 merge를 받는다.

#### `hotfix`

- 다음 배포 전까지 급하게 고쳐야되는 버그를 처리하는 브랜치
  - 배포 버전 심각한 버그 수정이 필요한경우, 버그 수정을 진행한뒤 `main` 또는 `realease` 브랜치에 merge함
  - **hotfix/버그이름**
    ex) `hotfix/bugs`

<br>

## 커밋 컨벤션

| 타입 종류        | 설명                                 |
|--------------| ------------------------------------ |
| 🤖 feat      | 새로운 기능에 대한 커밋              |
| 🛠️ fix      | 수정에 대한 커밋                     |
| 👾 bug       | 버그에 대한 커밋                     |
| 📝 build     | 빌드 관련 파일 수정에 대한 커밋      |
| 🚀 ci/cd     | 배포 커밋                            |
| 📑 docs      | 문서 수정에 대한 커밋                |
| 🕵️‍ style   | 코드 스타일 혹은 포맷 등에 관한 커밋 |
| 👷‍ refactor | 코드 리팩토링에 대한 커밋            |
| 🩺 test      | 테스트 코드 수정에 대한 커밋         |

<br>

## 코딩 컨벤션

- Intellij plug-in `SonarLint` 사용