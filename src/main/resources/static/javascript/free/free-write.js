document.addEventListener("DOMContentLoaded", function () {
    const submitButton = document.getElementById("submit-button");
    const imageInput = document.getElementById("image");

    submitButton.addEventListener("click", function () {
        const title = document.getElementById("title").value;
        const content = document.getElementById("content").value;

        // 등록 버튼 클릭 이벤트 리스너
        submitButton.addEventListener("click", function () {
            // 등록 페이지로 이동
            window.location.href = "/board/save";
        });

        if (!title || !content) {
            alert("제목과 내용을 모두 입력해주세요.");
            return;
        }

        // 이미지 업로드를 위한 코드
        const file = imageInput.files[0]; // 선택된 파일 가져오기

        // 이미지 파일 정보 콘솔에 출력
        console.log("선택한 이미지 파일:", file);

        // 현재 시간을 문자열로 변환하여 작성 시간으로 저장
        const currentTime = new Date().toLocaleString();

        // 작성한 글 데이터를 로컬 스토리지에 저장
        const postData = {
            title: title,
            content: content,
            author: "사용자 이름", // 여기에 사용자 이름을 가져와서 저장할 수도 있습니다.
            date: currentTime,
            image: file ? file.name : null, // 이미지가 있을 경우 이미지 파일 이름 저장, 없을 경우 null
        };

        const savedPosts = JSON.parse(localStorage.getItem("posts")) || []; // 기존에 저장된 글 목록 가져오기
        savedPosts.push(postData); // 새로 작성한 글 데이터 추가
        localStorage.setItem("posts", JSON.stringify(savedPosts)); // 로컬 스토리지에 저장

        // 여기에 서버와 연동하여 데이터를 저장하는 코드를 추가
        // ...

        // 임시로 저장한 데이터 확인
        console.log("제목:", title);
        console.log("내용:", content);
        console.log("작성 시간:", currentTime);
        console.log("글쓴이:", postData.author);

        // 데이터를 저장한 후에 다른 페이지로 이동하거나 알림 메시지를 띄울 수도 있습니다.
        alert("게시글이 성공적으로 등록되었습니다.");

        // 입력값 초기화
        document.getElementById("title").value = "";
        document.getElementById("content").value = "";
        imageInput.value = ""; // 이미지 업로드 인풋 초기화

        // free-list.html로 이동
        window.location.href = "/board/list";
    });

    // 글쓰기 버튼 클릭 이벤트 리스너
    writeButton.addEventListener("click", function () {
        // 글쓰기 페이지로 이동
        window.location.href = "/board/save";
    });
});
