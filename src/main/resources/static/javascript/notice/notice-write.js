//유저 인증 변수선언
const usernameInput = document.getElementById("username");
const passwordInput = document.getElementById("password");
const writeButton = document.getElementById("write-btn");

// 해당 페이지는 notice 글쓰기 페이지 입니다.
document.addEventListener("DOMContentLoaded", function () {
  const submitButton = document.getElementById("submit-button");
  const imageInput = document.getElementById("image");

  submitButton.addEventListener("click", function () {
    const title = document.getElementById("title").value;
    const content = document.getElementById("content").value;

    // 등록 버튼 클릭 이벤트 리스너
    submitButton.addEventListener("click", function () {
      // 등록 페이지로 이동
      window.location.href = "notice-list.html";
    });

    if (!title || !content) {
      alert("제목과 내용을 모두 입력해주세요.");
      return;
    }

    // 이미지 업로드
    const file = imageInput.files[0];

    // 이미지 파일 정보 콘솔에 출력
    console.log("선택한 이미지 파일:", file);

    // 현재 시간을 문자열로 변환하여 작성 시간으로 저장
    const currentTime = new Date().toLocaleString();

    // 작성한 글 데이터를 로컬 스토리지에 저장
    const itemsData = {
      title: title,
      content: content,
      written: "글쓴이", //DB 값으로 uwer name 가져오기
      date: currentTime,
      image: file ? file.name : null, // 이미지가 있을 경우 이미지 파일 이름 저장, 없을 경우 null
    };

    const saveditemss = JSON.parse(localStorage.getItem("itemss")) || []; // 기존에 저장된 글 목록 가져오기
    saveditemss.push(itemsData); // 새로 작성한 글 데이터 추가
    localStorage.setItem("itemss", JSON.stringify(saveditemss)); // 로컬 스토리지에 저장

    // 여기에 서버와 연동하여 데이터를 저장하는 코드를 추가
    // ...

    // 임시로 저장한 데이터 확인
    console.log("제목:", title);
    console.log("내용:", content);
    console.log("작성 시간:", currentTime);
    console.log("글쓴이:", itemsData.written);

    // 게시물 등록 성공 시, 

    if(content != 0){
      alert("게시글이 성공적으로 등록되었습니다.");
    }else{
      alert("게시글 저장하는데 오류가 발생하였습니다.")
    }

    // 입력값 초기화
    document.getElementById("title").value = "";
    document.getElementById("content").value = "";
    imageInput.value = ""; // 이미지 업로드 인풋 초기화

    // free-list.html로 이동
    window.location.href = "notice-list.html";
  });


  // -----------------------------------------------------------------------------------------------------------------------------NOITE 차이
  // 해당 영역에  DB에 넘겨준 값을 이용하여 해당  user가 로그인 시, 버튼이 보이게끔 만들어줘야함
// 글쓰기 버튼 터치 시, 이동 함수 구현
// writeButton.addEventListener("click", function () {
//   window.location.href = "notice-list.html";
// });

// // 지정된 유저인지 아닌지를 예시로 저장
// function validateUserCredentials(username, password) {
//   return username === "a" && password === "1234";
// }

// // 이벤트를 통하여 만약 유저의 정보가 맞다면
// usernameInput.addEventListener("change", handleCredentialsChange);
// passwordInput.addEventListener("change", handleCredentialsChange);

// // 글쓰기 버튼을 보여주는 함수 구현
// function handleCredentialsChange() {
//     const username = usernameInput.value;
//     const password = passwordInput.value;
//     const isValidCredentials = validateUserCredentials(username, password);

//     if (isValidCredentials) {
//       writeButton.style.display = "block"; 
//     }else {
//       writeButton.style.display = "none"; 
//     }
//   };
  // -----------------------------------------------------------------------------------------------------------------------------NOITE 차이
  
  
  // 글쓰기 버튼 클릭 이벤트 리스너
  writeButton.addEventListener("click", function () {
    // 글쓰기 페이지로 이동
    window.location.href = "notice-list.html";
  });
});


// 여기까지 이제 구현
