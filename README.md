# **백엔드 스쿨 5기 미니 개인프로젝트 - ♻️멋사마켓♻️**

```
요즘 사람들이 많이 사용하고 있는 🥕당근마켓, 중고나라 등의 중고 제품 거래 플랫폼을 만들어보는 미니 프로젝트입니다.
사용자가 중고 물품을 자유롭게 올리고, 댓글을 통해 소통하며, 최종적으로 구매 제안에 대하여
수락할 수 있는 형태의 중고 거래 플랫폼의 백엔드를 만들어봅시다.
```

<br><br>

## 📁 REST API
### 1. 물품 관리
<details>
<summary><b><u>중고 물품 관리 API</u></b></summary><br>

<details><summary>1️⃣</summary></details>
<details><summary>2️⃣</summary></details>
<details><summary>3️⃣</summary></details>
<details><summary>4️⃣</summary></details>
<details><summary>5️⃣</summary></details>
<details><summary>6️⃣</summary></details>
<details><summary>7️⃣</summary></details>

</details>

### 2. 물품 댓글
<details>
<summary><b><u>중고 물품 댓글 API</u></b></summary><br>

<details><summary>1️⃣</summary></details>
<details><summary>2️⃣</summary></details>
<details><summary>3️⃣</summary></details>
<details><summary>4️⃣</summary></details>
<details><summary>5️⃣</summary></details>
<details><summary>6️⃣</summary></details>
<details><summary>7️⃣</summary></details>

</details>

### 3. 구매 제안
<details>
<summary><b><u>구매 제안</u></b></summary><br>

<details><summary>1️⃣</summary></details>
<details><summary>2️⃣</summary></details>
<details><summary>3️⃣</summary></details>
<details><summary>4️⃣</summary></details>
<details><summary>5️⃣</summary></details>
<details><summary>6️⃣</summary></details>
<details><summary>7️⃣</summary></details>

</details>

<br><br>

## 📜 History

<details>
<summary>✨ <u><b>2023-06-29</b></u>: Repository 생성, DTO 추가, SalesItem MVC 구조</summary>

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
</details>



<details>
<summary>✨ <u><b>2023-06-30</b></u>: ResponseDTO 추가, TODO 구현</summary>

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
</details>



<details>
<summary>✨ <u><b>2023-07-01</b></u>: TODO [ PUT /items/{itemId}/image ] 구현</summary>

---
### 2023-07-01
**TODO**:
> PUT /items/{itemId}/image
---
</details>



<details>
<summary>✨ <u><b>2023-07-03</b></u>: DAY 1 / 중고 물품 관리 요구사항, 중고 물품 댓글 MVC 구조</summary>

---
### 2023-07-03

<details>
<summary><u><b>DAY 1 / 중고 물품 관리 요구사항</b></u></summary>

**1️⃣ <u>[POST] /items</u>**<br>
`ItemController.create()`, `ItemService.createItem()`<br>: 누구든지 중고 거래를 목적으로 물품에 대한 정보를 등록할 수 있다.<br>

`ItemEntity - @NotNull`<br>: 이때 반드시 포함되어야 하는 내용은 **제목, 설명, 최소 가격, 작성자**이다.<br>

`ItemService.validPW()`<br>: 또한 사용자가 물품을 등록할 때, 비밀번호 항목을 추가해서 등록한다.<br>

`ItemService.createItem()`<br>: 최초로 물품이 등록될 때, 중고 물품의 상태는 **판매중** 상태가 된다.<br>

<br><br>

**2️⃣ <u>[GET] /items?page={page}&limit={limit}</u>**<br>
`ItemService.readItemsPaged()`, `Return Type Page<ItemPageInfoDto>`<br>: 등록된 물품 정보는 누구든지 열람할 수 있다.<br> 페이지 단위 조회가 가능하다.<br>

`ItemController.readAll()`, `ItemController.readOne()`<br>: 전체 조회, 단일 조회 모두 가능하다.<br>

<br><br>

**3️⃣ <u>[GET] /items/{itemId}</u>**<br>
`ItemController.readOne()`<br>: 전체 조회, 단일 조회 모두 가능하다.<br>

<br><br>

**4️⃣ <u>[PUT] /items/{itemId}</u>**<br>
`ItemController.update()`, `ItemService.updateItem()`<br>: 등록된 물품 정보는 수정이 가능하다.
<br>

`ItemService.validPW()`<br>: 이때, 물품이 등록될 때 추가한 비밀번호를 첨부해야 한다.

<br><br>

**5️⃣ <u>[DELETE] /items/{itemId}</u>**<br>
`ItemController.delete()`, `ItemService.deleteItem()`<br>: 등록된 물품 정보는 삭제가 가능하다.<br>

`ItemService.validPW()`<br>: 이때, 물품이 등록될 때 추가한 비밀번호를 첨부해야 한다.

<br><br>

**6️⃣ <u>[PUT] /items/{itemId}/image</u>**<br>
`ItemController.uploadImage()`, `ItemService.uploadItemImage()`<br>: 등록된 물품 정보에 이미지를 첨부할 수 있다.<br>

`ItemService.validPW()`<br>: 이때, 물품이 등록될 때 추가한 비밀번호를 첨부해야 한다.

<br><br>

**7️⃣ <u>그 외 추가 및 수정사항</u>**<br>
`getItemById()`<br>: 해당하는 ID가 없을 경우, Not Found 예외 처리하는 과정을 메서드로 분리<br>

`validPW()`<br>: Password를 검사하는 부분을 메서드로 분리<br>

`ResponseDto`<br>: Controller의 Return Type을 ResponseDto로 수정 후 ResponseBody 출력 형식 message로 변경<br>

`ContentinfoDto`<br>: `ItemController.readOne()`에서 title, description, minPriceWanted, status만 보이게 Dto 설정<br>

`PageinfoDto`<br>: `ItemController.readAll()`에서 id, title, description, minPriceWanted, status만 보이게 Dto 설정<br>
imageUrl -> add @JsonInclude(JsonInclude.Include.NON_NULL) Null 값 일때 미출력<br>

<br>
</details>



<details>
<summary><u><b>중고 물품 댓글 MVC 구조</b></u></summary>

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

</details>

---
</details>



<details>
<summary>✨ <u><b>2023-07-04</b></u>: DAY 2 / 중고 물품 댓글 요구사항</summary>

---
### 2023-07-04
**1️⃣ <u>[POST] /items/{itemId}/comments</u>**<br>
`CommentController.createComment()`, `CommentService.postComment()`<br>: 등록된 물품에 대한 질문을 위하여 댓글을 등록할 수 있다.<br>

`CommentEntity - @NotNull`<br>: 이때 반드시 포함되어야 하는 내용은 대상 물품, 댓글 내용, 작성자이다.<br>

`PasswordValidatable.validatePassword()`, `CommentEntity - @Override`<br>: 또한 댓글을 등록할 때, 비밀번호 항목을 추가해서 등록한다.<br>

<br><br>

**2️⃣ <u>[GET] /items/{itemId}/comments</u>**<br>
`CommentController.readAllComment()`, `CommentService.getCommentsPaged()`<br>: 등록된 댓글은 누구든지 열람할 수 있다.<br>

`CommentService.getCommentsPaged()`, `Return Type Page<CommentPageInfoDto>`<br>: 페이지 단위 조회가 가능하다.<br>

<br><br>

**3️⃣ <u>[PUT] /items/{itemId}/comments/{commentId}</u>**<br>
`CommentController.updateComment()`, `CommentService.modifiedComment()`<br>: 등록된 댓글은 수정이 가능하다.<br>

`PasswordValidatable.validatePassword()`, `CommentEntity - @Override`<br>: 이때, 댓글이 등록될 때 추가한 비밀번호를 첨부해야 한다.<br>

<br><br>

**4️⃣ <u>[DELETE] /items/{itemId}/comments/{commentId}</u>**<br>
`CommentController.delete()`, `CommentService.deleteComment()`<br>: 등록된 댓글은 삭제가 가능하다.<br>
`PasswordValidatable.validatePassword()`, `CommentEntity - @Override`<br>: 이때, 댓글이 등록될 때 추가한 비밀번호를 첨부해야 한다.<br>

<br><br>

**5️⃣ <u>[PUT] /items/{itemId}/comments/{commentId}/reply</u>**<br>
`CommentPageInfoDto`<br>: 댓글에는 초기에 비워져 있는 **답글** 항목이 존재한다.<br> ↳ 그래서 다른 Column과 다르게 `@NotNull`을 붙이지 않았다. 대신 `imageUrl`의 `null` 값을 숨길 때 처럼 `@JsonInclude(JsonInclude.Include.NON_NULL)`을 붙였다.<br>

`CommentPageInfoDto`<br>: 답글은 댓글에 포함된 공개 정보이다.<br> ↳ 이 요구사항 때문에 위에서 언급한 `@JsonInclude(JsonInclude.Include.NON_NULL)`도 추가하지 않을까 하다가 `null` 값일 경우, 답글이 보이지 않는 경우가 더 많다고 생각해서 유지하였다.<br>

`CommentService.modifiedReply()`<br>: 만약 댓글이 등록된 대상 물품을 등록한 사람일 경우, 물품을 등록할 때 사용한 비밀번호를 첨부할 경우 답글 항목을 수정할 수 있다.<br>
↳ 이 부분은 아래 토글을 열어 코드를 참고해주세요.

<details>
<summary>📄 <u>CommentService.java - modifiedReply()</u></summary>

```java
public class CommentService {
    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final CommentRepository commentRepository;

    // Post, Modifying Reply
    public void modifiedReply(Long commentId, Long itemId, CommentDto comments) {
        CommentEntity commentEntity = validateCommentByItemId(commentId, itemId);
        ItemEntity itemEntity = itemService.getItemById(itemId);

        // 1. 답글 작성자 != 물품 등록 작성자 -> 예외 처리
        // 댓글에 답글을 달 수 있는 사용자는 물품 정보를 등록한 사용자 뿐
        if (!itemEntity.getWriter().equals(comments.getWriter()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        // 2. 물품 등록 작성자 == 답글 작성자 라는건 위의 예외에서 증명
        // 만약 댓글이 등록된 대상 물품을 등록한 사람일 경우
        // -> 물품 등록 == 댓글 == 답글 다 같은 작성자이다.
        if (commentEntity.getWriter().equals(comments.getWriter())) {
            // 물품을 등록할 때 사용한 비밀번호를 첨부할 경우 답글 항목을 수정할 수 있다.
            // 물품 등록 비밀번호 != 답글 비밀번호 -> 예외 처리
            itemEntity.validatePassword(comments.getPassword());
        }
        // Save Reply
        commentEntity.setReply(comments.getReply());
        CommentDto.fromEntity(commentRepository.save(commentEntity));
    }
}
```
</details>

<br><br>

**6️⃣ <u>그 외 추가 및 수정사항</u>**<br>
`PageinfoDto`<br>: `ItemPageInfoDto`, `CommentPageInfoDto`로 구분을 위해 자세하게 이름 설정<br> `dto/mapping`으로 경로 설정

`PasswordValidatable`<br>: `validPW`를 `ItemEntity`와 `CommentEntity`에서 받을 수 있게 `interface`로 변경<br> 각 `Entity`에서 `implements PasswordValidatable`하고 난 후, `@Override`할 수 있게 변경

`CommentService - validateCommentByItemId()`<br>: 각 메서드마다 요청 댓글 유무, 대상 댓글이 대상 게시글의 댓글인지 확인하는 과정이 겹쳐서 따로 분리<br>

---
<br>
</details>




<details>
<summary>✨ <u><b>2023-07-04</b></u>: DAY 3 / 구매 제한 요구사항 (수정중)</summary>

---
### 2023-07-04

<details>
<summary><u><b>중고 물품 댓글 MVC 구조</b></u></summary>

**Add**:
> - Controller
> - Entity
> - Repository
> - Service

<br>

**TODO**:
> POST /items/{itemId}/proposal<br>
> GET /items/{itemId}/proposals?writer=Lim123&password=qwerty1234&page=1<br>
> PUT /items/{itemId}/proposals/{proposalId}<br>
> DELETE /items/{itemId}/proposals/{proposalId}<br>
> PUT /items/{itemId}/proposals/{proposalId}<br>

</details>

---
<br>
</details>