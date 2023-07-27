document.addEventListener("DOMContentLoaded", function () {
    // 로컬스토리지에서 저장된 게시글 데이터 가져오기
    const storedPosts = JSON.parse(localStorage.getItem("posts")) || [];

    // 현재 페이지 URL에서 게시글 인덱스 값을 가져오기
    const urlParams = new URLSearchParams(window.location.search);
    const postIndex = parseInt(urlParams.get("index"));

    // 해당 인덱스에 해당하는 게시글 데이터 가져오기
    const post = storedPosts[postIndex];

    // 게시글 데이터가 존재하면 표시하기
    if (post) {
        // 제목 표시
        const postTitleElement = document.getElementById("post-title");
        postTitleElement.textContent = post.title;

        // 작성일자 표시
        const postDateElement = document.getElementById("post-date");
        postDateElement.textContent = post.date;

        // 글쓴이 표시
        const postAuthorElement = document.getElementById("post-author");
        postAuthorElement.textContent = post.author;

        // 내용 표시
        const postContentElement = document.getElementById("post-content");
        postContentElement.textContent = post.content;

        // // 이미지 표시
        // const postImageElement = document.getElementById("post-image");
        // if (post.image) {
        //   // 이미지 파일이 있는 경우 이미지를 표시
        //   postImageElement.src = post.image;
        //   postImageElement.style.display = "block"; // 이미지 요소를 화면에 표시
        // } else {
        //   // 이미지 파일이 없는 경우 이미지 요소를 숨김
        //   postImageElement.style.display = "none";
        // }
    } else {
        // 게시글 데이터가 없을 경우 "게시글이 없습니다." 메시지 표시
        const postTitleElement = document.getElementById("post-title");
        postTitleElement.textContent = "게시글이 없습니다.";

        const postDateElement = document.getElementById("post-date");
        postDateElement.textContent = "";

        const postAuthorElement = document.getElementById("post-author");
        postAuthorElement.textContent = "";

        const postContentElement = document.getElementById("post-content");
        postContentElement.textContent = "";

        // // 이미지 요소 숨김
        // const postImageElement = document.getElementById("post-image");
        // postImageElement.style.display = "none";
    }

    // 글쓰기 버튼 클릭 이벤트 리스너
    const writeBtn = document.getElementById("write-btn");
    writeBtn.addEventListener("click", function () {
        // 글쓰기 페이지로 이동
        window.location.href = "free/free-write.html";
    });

    // 목록 버튼 클릭 이벤트 리스너
    const listBtn = document.getElementById("list-btn");
    listBtn.addEventListener("click", function () {
        // 목록 페이지로 이동
        window.location.href = "free/free-list.html";
    });
});
