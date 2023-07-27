document.addEventListener("DOMContentLoaded", function () {
  // 로컬스토리지에서 저장된 게시글 데이터 가져오기
  const storeditemss = JSON.parse(localStorage.getItem("itemss")) || [];

  // 현재 페이지 URL에서 게시글 인덱스 값을 가져오기
  const urlParams = new URLSearchParams(window.location.search);
  const itemsIndex = parseInt(urlParams.get("index"));

  // 해당 인덱스에 해당하는 게시글 데이터 가져오기
  const items = storeditemss[itemsIndex];

  // 게시글 데이터가 존재하면 표시하기
  if (items) {
    // 제목 표시
    const itemsTitleElement = document.getElementById("items-title");
    itemsTitleElement.textContent = items.title;

    // 작성일자 표시
    const itemsDateElement = document.getElementById("items-date");
    itemsDateElement.textContent = items.date;

    // 글쓴이 표시
    const itemswrittenElement = document.getElementById("items-written");
    itemswrittenElement.textContent = items.written;

    // 내용 표시
    const itemsContentElement = document.getElementById("items-content");
    itemsContentElement.textContent = items.content;

    // 이미지 표시
    const itemsImageElement = document.getElementById("items-image");
    if (items.image) {
      // 이미지 파일이 있는 경우 이미지를 표시
      itemsImageElement.src = items.image;
      itemsImageElement.style.display = "block"; // 이미지 요소를 화면에 표시
    } else {
      // 이미지 파일이 없는 경우 이미지 요소를 숨김
      itemsImageElement.style.display = "none";
    }
  }

  // 글쓰기 버튼 클릭 이벤트 리스너
  const writeBtn = document.getElementById("write-btn");
  writeBtn.addEventListener("click", function () {
    // 글쓰기 페이지로 이동
    window.location.href = "notice-write.html";
  });

  // 목록 버튼 동적
  const listBtn = document.getElementById("list-btn");
  listBtn.addEventListener("click", function () {
    // 목록 페이지로 이동
    window.location.href = "notice-list.html";
  });

  //수정 버튼
  const editButton = document.getElementById("modify-btn");
  editButton.addEventListener("click", function () {
    // Redirect to the notice-edit.html page and pass the index as a URL parameter
    window.location.href = `notice-edit.html?index=${itemsIndex}`;
  });
});

// 삭제 버튼 동작
document.addEventListener("DOMContentLoaded", function () {
  // 삭제 버튼 ID 값 호출
  const deleteBtn = document.getElementById("delete-btn");

  // Index 값을 이용하여 해당 번호 게시글 검색
  const urlParams = new URLSearchParams(window.location.search);
  const itemsIndex = parseInt(urlParams.get("index"));

  // 해당 스토리지 접근
  const storeditemss = JSON.parse(localStorage.getItem("itemss")) || [];

  // 인덱스값 저장받을 변수
  const items = storeditemss[itemsIndex];

  // 데이터가 있다면 버튼 동작 시, 해당 인덱스 값 삭제
  if (items) {
    deleteBtn.addEventListener("click", function () {
      const confirmDelete = confirm("게시글을 삭제하시겠습니까?");
      if (confirmDelete) {
        storeditemss.splice(itemsIndex, 1);
        localStorage.setItem("itemss", JSON.stringify(storeditemss));
        //삭제 후 노티스 목록 페이지로 디옫
        window.location.href = "notice-list.html";
      }
    });
  } else {
    //찾을 수 없다면
    alert("삭제 할 게시글이 존재하지 않습니다.");
    // 노티스 목록 페이지로 이동
    window.location.href = "notice-list.html";
  }
});
