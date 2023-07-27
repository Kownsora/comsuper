// notice-edit.js

//index값으로 스토리지에 저장되어있는 값들을 불러옴
document.addEventListener("DOMContentLoaded", function () {
  const storeditemss = JSON.parse(localStorage.getItem("itemss")) || [];

  const urlParams = new URLSearchParams(window.location.search);
  const itemsIndex = parseInt(urlParams.get("index"));
  const items = storeditemss[itemsIndex];

  //사용할 hrml id나 class 값들을 소환시켜고 따로 변수로 지정
  if (items) {
    const editTitleElement = document.getElementById("edit-title");
    const editContentElement = document.getElementById("edit-content");
    const saveButton = document.getElementById("save-button");

    // 위에서 선언해준 title 값은 itmes,content에 있는 값이라는 선언을 해주고
    editTitleElement.value = items.title;
    editContentElement.value = items.content;

    // 수정 완료 버튼 누르면
    saveButton.addEventListener("click", function () {
      const editedTitle = editTitleElement.value;
      const editedContent = editContentElement.value;
      if (editedTitle && editedContent) {
        // 로컬스토리지에 수정된 타이틀과 컨텐트 값을 다시 저장해줌
        storeditemss[itemsIndex].title = editedTitle;
        storeditemss[itemsIndex].content = editedContent;
        localStorage.setItem("itemss", JSON.stringify(storeditemss));
        alert("수정이 완료되었습니다.");

        // 리액트해주고 변경한 값들을 다시 content html에 넘겨준다.
        window.location.href = `notice-content.html?index=${itemsIndex}`;
      } else {
        alert("제목과 내용을 모두 입력해주세요.");
      }
    });
  }
});
