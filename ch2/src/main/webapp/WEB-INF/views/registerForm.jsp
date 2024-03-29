<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <style>
        * { box-sizing:border-box; }

        form {
            width:400px;
            height:600px;
            display : flex;
            flex-direction: column;
            align-items:center;
            position : absolute;
            top:50%;
            left:50%;
            transform: translate(-50%, -50%) ;
            border: 1px solid rgb(89,117,196);
            border-radius: 10px;
        }

        .input-field {
            width: 300px;
            height: 40px;
            border : 1px solid rgb(89,117,196);
            border-radius:5px;
            padding: 0 10px;
            margin-bottom: 10px;
        }
        label {
            width:300px;
            height:30px;
            margin-top :4px;
        }

        button {
            background-color: rgb(89,117,196);
            color : white;
            width:300px;
            height:50px;
            font-size: 17px;
            border : none;
            border-radius: 5px;
            margin : 20px 0 30px 0;
        }

        .title {
            font-size : 50px;
            margin: 40px 0 30px 0;
        }

        .msg {
            height: 30px;
            text-align:center;
            font-size:16px;
            color:red;
            margin-bottom: 20px;
        }
        .sns-chk {
            margin-top : 5px; 
        }
    </style>
    <title>Register</title>
</head>
<c:url value='/register/save' var="url"/>
<body>
   <%-- <form action="<c:url value='/register/save'/>" method="post" onsubmit="return formCheck(this)"> --%>
   <%--@elvariable id="user" type="com.fastcampus.ch2.User"--%>
   <form:form modelAttribute="user" action="${url}">
    <div class="title">Register</div>
    <div id="msg" class="msg">
    	<%-- <b>${URLDecoder.decode(param.msg, "utf-8")}</b> --%>
    	<form:errors path="id"/>
    </div> 
    <label for="id">아이디</label>
    <input class="input-field" type="text" name="id" id="id" placeholder="8~12자리의 영대소문자와 숫자 조합">
    <label for="pwd">비밀번호</label>
    <input class="input-field" type="password" name="pwd" id="pwd" placeholder="8~12자리의 영대소문자와 숫자 조합">
    <label for="name">이름</label>
    <input class="input-field" type="text" name="name" id="name" placeholder="홍길동">
    <label for="hobby">취미</label>
    <input class="input-field" type="text" name="hobby" id="hobby" placeholder="독서#피아노#운동">
    <label for="birth">생일</label>
    <input class="input-field" type="text" name="birth" id="birth" placeholder="2020-12-31">
    <div class="sns-chk">
        <label><input type="checkbox" name="sns" value="facebook"/>페이스북</label>
        <label><input type="checkbox" name="sns" value="kakaotalk"/>카카오톡</label>
        <label><input type="checkbox" name="sns" value="instagram"/>인스타그램</label>
    </div>
    <input id="now" type="hidden" name="join">
    <button>회원 가입</button>
   </form:form>
   <%-- </form> --%>
   <script>
       function formCheck(frm) {
           if (frm.id.value.length < 3) {
               setMessage('id의 길이는 3이상이어야 합니다.', frm.id);
               return false;
           }

           if (frm.pwd.value.length < 3) {
               setMessage('pwd의 길이는 3이상이어야 합니다.', frm.pwd);
               return false;
           }

           const now = new Date();
           const y = now.getFullYear();
           const m = now.getMonth() + 1;
           const d = now.getDate();
           document.getElementById("now").value = y + '/' + (m < 10 ? '0' + m : m) + '/' + (d < 10 ? '0' + d : d);

           return true;
       }

       function setMessage(msg, element) {
           document.getElementById("msg").innerHTML = `<i class="fa fa-exclamation-circle"> ${'${msg}'}</i>`;
           // Template Literal과 EL을 구분하기 위해 Template Literal을 사용시 ${'${TL}'} 형태로 작성한다.
           if (element) {
               element.select();	// select() : 블록 지정 이벤트
           }
       }
   </script>
</body>
</html>