document.addEventListener("DOMContentLoaded", function () {
    const boardListContainer = document.getElementById("board-list"); // 게시글 목록을 보여줄 컨테이너 요소
    const writeButton = document.querySelector(".write-button"); // 글쓰기 버튼

    // 글쓰기 버튼 클릭 이벤트 리스너
    writeButton.addEventListener("click", function () {
        // 글쓰기 페이지로 이동
        window.location.href = "/board/save";
    });

    // 로컬스토리지에서 저장된 게시글 데이터 가져오기
    const storedPosts = JSON.parse(localStorage.getItem("posts")) || [];

    // 저장된 게시글 데이터를 이용하여 리스트 페이지에 게시글 목록 추가
    storedPosts.forEach((post, index) => {
        const boardListItem = document.createElement("div");
        boardListItem.classList.add("board-list");

        // 게시글의 index, 제목, 날짜, 글쓴이를 보여주는 요소 생성
        const postIndex = document.createElement("div");
        postIndex.textContent = index + 1;

        const postTitle = document.createElement("div");
        postTitle.textContent = post.title;

        // 제목을 클릭했을 때 해당 게시글의 인덱스를 URL 파라미터로 전달하여 free-description.html로 이동
        postTitle.addEventListener("click", function () {
            window.location.href = `free-description.html?index=${index}`;
        });

        const postDate = document.createElement("div");
        postDate.textContent = post.date;

        const postAuthor = document.createElement("div");
        postAuthor.textContent = post.author;

        // 생성한 요소들을 게시글 목록에 추가
        boardListItem.appendChild(postIndex);
        boardListItem.appendChild(postTitle);
        boardListItem.appendChild(postDate);
        boardListItem.appendChild(postAuthor);

        // 게시글 목록 컨테이너에 생성한 게시글 요소를 추가
        boardListContainer.appendChild(boardListItem);
    });
});
