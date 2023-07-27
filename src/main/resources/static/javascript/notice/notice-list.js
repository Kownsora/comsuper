document.addEventListener("DOMContentLoaded", function () {
  //html notice 목록과 버튼 가져오기
  const NoticeListContainer = document.getElementById("notice-list");
  const writeButton = document.querySelector(".write-button");

  //아래는 글쓰기 권한이 있는 경우에만 글쓰기 버튼이 보이도록 해주는 함수
  // function isLoggedIn() {
  //   return true;
  // }

  // // 권한 허용자인지 체크
  // function hasWritePermission() {
  //   return userPermissions.isAdmin; // 값을 리턴해주고 권한이 있으면 보여주고 없으면 안보여주게 끔
  // }

  // // 글쓰기 버튼 클릭시, 동작하는 함수
  // if (isLoggedIn() && hasWritePermission()) {
  //   // 글쓰기 버튼 권한자 글쓰기 버튼 보여주기
  //   writeButton.style.display = "block";
  // } else {
  //   //글쓰기 버튼 감추기
  //   writeButton.style.display = "none";
  // }

  // 글쓰기 버튼 클릭시, 동작하는 함수
  writeButton.addEventListener("click", function () {
    // 글쓰기 페이지로 이동
    window.location.href = "notice-write.html";
  });

  // 로컬스토리지에서 저장된 게시글 데이터 가져오기
  const storeditemss = JSON.parse(localStorage.getItem("itemss")) || [];

  // 한 페이지당 보여줄 게시글 수
  const itemssPerPage = 10;

  // 현재 페이지 번호
  let currentPage = 1;

  // 게시글 목록을 보여주는 함수
  function showitemss() {
    // 이전에 추가한 게시글 목록 제거
    NoticeListContainer.innerHTML = "";

    // 현재 페이지에 해당하는 게시글들을 가져오기
    const startIdx = (currentPage - 1) * itemssPerPage;
    const endIdx = startIdx + itemssPerPage;
    const pageitemss = storeditemss.slice(startIdx, endIdx);

    // 가져온 게시글들을 화면에 추가
    pageitemss.forEach((items, index) => {
      const noticeListItem = document.createElement("div");
      noticeListItem.classList.add("notice-list");

      // itemsIndex값에 index가 추가될때마다 startIndex 값에 +1 처리
      const itemsIndex = document.createElement("div");
      itemsIndex.textContent = startIdx + index + 1;

      const itemsTitle = document.createElement("div");
      itemsTitle.textContent = items.title;

      // 타이틀 클릭 시, 처리하는 함수
      itemsTitle.addEventListener("click", function () {
        //타이틀 터치 시, 상세페이지로 이동 해야하니 index 값을 이용하여 해당 index 번호를 불러온다.
        window.location.href = `notice-content.html?index=${startIdx + index}`;
      });

      //현재 날짜 변수로 가져오기
      const itemsDate = document.createElement("div");
      itemsDate.textContent = items.date;

      //하단 버튼들 변수로 가져오기
      const itemswritten = document.createElement("div");
      itemswritten.textContent = items.written;

      // 생성한 요소들을 게시글 목록에 추가 (appendChild는 안으로 감싸준다. 약간 상속 개념)
      noticeListItem.appendChild(itemsIndex);
      noticeListItem.appendChild(itemsTitle);
      noticeListItem.appendChild(itemsDate);
      noticeListItem.appendChild(itemswritten);

      // 게시글 목록 컨테이너에 생성한 게시글 요소를 추가
      NoticeListContainer.appendChild(noticeListItem);
    });
  }

  // 페이지네이션 버튼 클릭 이벤트 리스너
  const prevButton = document.querySelector(".prev-button");
  const nextButton = document.querySelector(".next-button");

  //이전 버튼 클릭 시, 페이지 값 -1 감소
  prevButton.addEventListener("click", function () {
    if (currentPage > 1) {
      currentPage--;
      showitemss();
    }
  });

  //다음 버튼 클릭 시, 목록개수 길이만큼(10개) 다음페이지 가져오기
  nextButton.addEventListener("click", function () {
    if (currentPage < Math.ceil(storeditemss.length / itemssPerPage)) {
      currentPage++;
      showitemss();
    }
  });

  // 초기 페이지네이션 상태에서 첫 번째 페이지 게시글 보여주기
  showitemss();
});
