document.addEventListener("DOMContentLoaded", function () {
  // 상단바 메뉴 경로 함수

  //boardlink, noticelink, logout 변수에 html id, class 값을 가져와 지정해줌
  const boardLink = document.querySelector(".top-menu-board");
  const noticeLink = document.querySelector(".top-menu-notice");
  const logoutLink = document.querySelector(".top-menu-logout");

  // board 메뉴 터치 시, 이동함수
  boardLink.addEventListener("click", function () {
    window.location.href = "board-list.html";
  });

  //notice 메뉴 터치 시, 이동함수
  noticeLink.addEventListener("click", function () {
    window.location.href = "notice-list.html";
  });

  //logout 메뉴 터치 시, 이동함수
  logoutLink.addEventListener("click", function () {
    // 해당 영역에는 로그아웃 경로를 지정해줘야하는데, 아직 모름
  });
});
