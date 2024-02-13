<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>SB Admin 2 - Bootstrap Admin Theme</title>

<!-- Bootstrap Core CSS -->
<link href="/resources/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="/resources/vendor/metisMenu/metisMenu.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="/resources/dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="/resources/vendor/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->


</head>
<body>

<script type="text/javascript">

//아이디 중복 여부 체크 Ajax
function fn_idChk(){
	console.log("chekcing id.....");
	$.ajax({
		url: "/member/idCheck",
		type: "POST",
		dataType: "JSON", //서버에서 반환하는 데이터 타입 
		data: {"userid" : $("#userid").val()},//서버에 보내는 데이터 타입 
		success: function(data){
			if (data == 1){
				alert("중복된 아이디입니다.");
			} else if (data == 0) {
				$("#idChk").attr("value", "Y");
				alert("사용 가능한 아이디입니다.");
			}
		}
	}); // end ajax
}; // function end 
	
// 사용자 중복 여부 체크 Ajax 
function fn_userNameChk(){
	console.log("checking userName.....");
	
	$.ajax({
		url: "/member/userNameCheck",
		type: "POST",
		dataType: "JSON", //서버에서 반환하는 데이터 타입 
		data: {"userName" : $("#userName").val()},//서버에 보내는 데이터 타입 
		success: function(data){
			if (data == 1){
				alert("중복된 사용자 이름(별명)입니다.");
			} else if (data == 0) {
				$("#userNameChk").attr("value", "Y");
				alert("사용 가능한 사용자 이름(별명)입니다.");
			}
		}
	}); // end ajx
}; // end function

// 회원 가입 버튼 동작 관련 자바 스크립트 
 	$(document).ready(function(){
 		// 회원 가입 취소 시 
 		$("#CancelBtn").on("click", function(){
 			location.href = "/member/login";
 		});
 			
		// 회원 가입 시  
 		$("#signUpBtn").on("click", function(){
 				
 			// 아이디를 입력하지 않았을 경우  
 			if($("#userid").val()==""){
 				alert("아이디를 입력해주세요.");
 				$("#userid").focus();
 				return false;
 			}
 				
 			// 아이디의 길이가 6~12자 범위에 포함되지 않을 경우 
 			if($("#userid").val().length < 6 || $("#userid").val().length > 12) {
 				alert("아이디는 6~12자 이내로 입력해주세요.")
 				$("#userid").focus();
 				return false;
 			}
 				
 			// 비밀번호를 입력하지 않았을 경우 
 			if($("#userpw").val()==""){
 				alert("비밀번호를 입력해주세요.");
 				$("#userpw").focus();
 				return false;
 			}
 				
 			// 비밀번호의 길이가 8자보다 짧을 경우 
 			if($("#userpw").val().length < 8){
 				alert("비밀번호는 8자 이상 입력해주세요.");
 				$("#userpw").focus();
 				return false;
 			}
 				
 			// 사용자 이름을 입력하지 않았을 경우 
 			if($("#userName").val()==""){
 				alert("사용자 이름을 입력해주세요.");
 				$("#userName").focus();
 				return false;
 			}
 				
 			// 사용자 이름이 10자 보다 길 경우 
 			if($("#userName").val().length > 10){
				alert("사용자 이름은 10자 이내로 입력해주세요.");
				$("#userName").focus();
				return false;
			}
				
			// 이메일 주소를 입력하지 않았을 경우 
	 		if($("#email").val()==""){
				alert("이메일 주소를 입력해주세요.");
				$("#email").focus();
				return false;
			}
				
			// 아이디, 사용자 이름 중복 여부 확인 
			var idChkVal = $("#idChk").val();
			var userNameChkVal = $("#userNameChk").val();
			
			if(idChkVal == "N") {
				alert("아이디 중복 확인 버튼을 눌러주세요.");
				return false;
			}
			
			if(userNameChkVal == "N") {
				alert("사용자 이름(별명) 중복 확인 버튼을 눌러주세요.");
				return false;
			}
			
			// 모두 이상 없을 경우 회원 가입 버튼 동작 
			$("#signUpForm").submit();
			alert("회원가입이 완료되었습니다. 로그인해주세요.");
			
 		}); 
	}); 
 
</script>

</body>
</html>
