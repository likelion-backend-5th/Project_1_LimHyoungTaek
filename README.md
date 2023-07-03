# **백엔드 스쿨 5기 미니 개인프로젝트 - ♻️멋사마켓♻️**

```
요즘 사람들이 많이 사용하고 있는 🥕당근마켓, 중고나라 등의 중고 제품 거래 플랫폼을 만들어보는 미니 프로젝트입니다.
사용자가 중고 물품을 자유롭게 올리고, 댓글을 통해 소통하며, 최종적으로 구매 제안에 대하여
수락할 수 있는 형태의 중고 거래 플랫폼의 백엔드를 만들어봅시다.
```

<br>

## History

<details>
<summary>2023-06-29: Repository 생성, DTO 추가, SalesItem MVC 구조 </summary>
<div markdown="1">

---
### 2023-06-29
**Create**: Git Repository - 'MiniProject_Basic_LimHyoungTaek'<br>

> ### dependencies
>   - Spring Web
>   - Spring Boot DevTools
>   - Spring Data JPA
>   - Lombok
>   - Sqlite

**Add**:
> - DTO(SalesItem, Negotiation, Comment)
> - Controller, repository, entity, service associated (with SalesItem)
---
<br>
</div>
</details>



<details>
<summary>2023-06-30: ResponseDTO 추가, TODO 구현 </summary>
<div markdown="1">

---
### 2023-06-30
**Add**:
> - DTO(ResponseDto)

<br>

**TODO**:
> POST /items<br>
> GET /items?page={page}&limit={limit}<br>
> GET /items/{itemId}<br>
> PUT /items/{itemId}<br>
> DELETE /items/{itemId}<br>
---
<br>
</div>
</details>



<details>
<summary>2023-07-01: TODO [ PUT /items/{itemId}/image ] 구현 </summary>
<div markdown="1">

---
### 2023-07-01
**TODO**:
> PUT /items/{itemId}/image
---
<br>
</div>
</details>



<details>
<summary>2023-07-03: DAY 1 / 중고 물품 관리 요구사항 </summary>
<div markdown="1">

---
### 2023-07-03
**1. [POST] /items**<br>
`ItemController.create()`, `ItemService.createItem()`<br>: 누구든지 중고 거래를 목적으로 물품에 대한 정보를 등록할 수 있다.<br>

`ItemEntity - @Notnull`<br>: 이때 반드시 포함되어야 하는 내용은 **제목, 설명, 최소 가격, 작성자**이다.<br>

`ItemService.validPW()`<br>: 또한 사용자가 물품을 등록할 때, 비밀번호 항목을 추가해서 등록한다.<br>

`ItemService.createItem()`<br>: 최초로 물품이 등록될 때, 중고 물품의 상태는 **판매중** 상태가 된다.<br>

<br>

**2. [GET] /items?page={page}&limit={limit}**<br>
`ItemController.readAll()`, `ItemService - Page<ItemPageInfoDto> readItemsPaged()`<br>: 등록된 물품 정보는 누구든지 열람할 수 있다.<br> 페이지 단위 조회가 가능하다.<br>

`ItemController.readAll()`, `ItemController.readOne()`<br>: 전체 조회, 단일 조회 모두 가능하다.<br>

<br>

**3. [GET] /items/{itemId}**<br>
`ItemController.readOne()`<br>: 전체 조회, 단일 조회 모두 가능하다.<br>

<br>

**4. [PUT] /items/{itemId}**<br>
`ItemController.update()`, `ItemService.updateItem()`<br>: 등록된 물품 정보는 수정이 가능하다.
<br>

`ItemService.validPW()`<br>: 이때, 물품이 등록될 때 추가한 비밀번호를 첨부해야 한다.

<br>

**5. [DELETE] /items/{itemId}**<br>
`ItemController.delete()`, `ItemService.deleteItem()`<br>: 등록된 물품 정보는 삭제가 가능하다.<br>

`ItemService.validPW()`<br>: 이때, 물품이 등록될 때 추가한 비밀번호를 첨부해야 한다.

<br>

**6. [PUT] /items/{itemId}/image**<br>
`ItemController.uploadImage()`, `ItemService.uploadItemImage()`<br>: 등록된 물품 정보에 이미지를 첨부할 수 있다.<br>

`ItemService.validPW()`<br>: 이때, 물품이 등록될 때 추가한 비밀번호를 첨부해야 한다.

<br>

**7. 그 외 추가 및 수정사항**<br>
`getItemById()`<br>: 해당하는 ID가 없을 경우, Not Found 예외 처리하는 과정을 메서드로 분리<br>

`validPW()`<br>: Password를 검사하는 부분을 메서드로 분리<br>

`ResponseDto`<br>: Controller의 Return Type을 ResponseDto로 수정 후 ResponseBody 출력 형식 message로 변경<br>

`ContentinfoDto`<br>: `ItemController.readOne()`에서 title, description, minPriceWanted, status만 보이게 Dto 설정<br>

`PageinfoDto`<br>: `ItemController.readAll()`에서 id, title, description, minPriceWanted, status만 보이게 Dto 설정<br>
imageUrl -> add @JsonInclude(JsonInclude.Include.NON_NULL) Null 값 일때 미출력<br>

---
<br>
</div>
</details>



<details>
<summary>2023-07-03: 중고 물품 댓글 MVC 구조 </summary>
<div markdown="1">

---
### 2023-07-03
**Add**:
> - CommentController
> - CommentEntity
> - CommentRepository
> - CommentService

<br>

**TODO**:
> POST /items/{itemId}/comments<br>
> GET /items/{itemId}/comments<br>
> PUT /items/{itemId}/comments/{commentId}<br>
> PUT /items/{itemId}/comments/{commentId}/reply<br>
> DELETE /items/{itemId}/comments/{commentId}<br>
---
<br>
</div>
</details>

<details>
<summary>2023-07-04: DAY 2 / 중고 물품 댓글 요구사항 (나중에 채워 넣겠읍니다)</summary>
<div markdown="1">

---
### 2023-07-04
**1. [POST] /items/{itemId}/comments**<br>
**2. [GET] /items/{itemId}/comments**<br>
**3. [PUT] /items/{itemId}/comments/{commentId}**<br>
**4. [PUT] /items/{itemId}/comments/{commentId}/reply**<br>
**5. [DELETE] /items/{itemId}/comments/{commentId}**<br>
**6. 그 외 추가 및 수정사항**<br>
`PageinfoDto`<br>: `ItemPageInfoDto`, `CommentPageInfoDto`로 구분을 위해 자세하게 이름 설정<br>

`PasswordValidatable`<br>: `validPW`를 `ItemEntity`와 `CommentEntity`에서 받을 수 있게 `interface`로 변경<br>

`CommentService - validateCommentByItemId()`<br>: 각 메서드마다 요청 댓글 유무, 대상 댓글이 대상 게시글의 댓글인지 확인하는 과정이 겹쳐서 따로 분리<br>

---
<br>
</div>
</details>