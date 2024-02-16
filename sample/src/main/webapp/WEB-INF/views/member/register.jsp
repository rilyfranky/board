<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<%@include file="../includes/header.jsp"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Register</title>

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
    
<style>
.id_ok{
color:#008000;
display: none;
}

.id_already{
color:#6A82FB; 
display: none;
}
</style>
    


</head>

<script type="text/javascript">
	$(document).ready(function(){
		
		var csrfHeaderName ="${_csrf.headerName}";
		var csrfTokenValue="${_csrf.token}";
		
		
		$(".cancel").on("click", function(){
			location.href = "/customLogin";
		})
		
    	
		//입력 체크
		$("#submit").on("click", function(){
		
			
			if($("#userid").val()==""){
				alert("아이디를 입력해주세요");
				$("#userid").focus();
				return false;
			}
			
			if($("#userpw").val()==""){
				alert("비밀번호를 입력해주세요");
				$("#userpw").focus();
				return false;
			}
			
			if($("#username").val()==""){
				alert("성명을 입력해주세요"	);
				$("#username").focus();
				return false;
			}
			
			$("<input>").attr("type", "hidden")
            .attr("name", csrfHeaderName)
            .attr("value", csrfTokenValue)
            .appendTo("form");
	
		});
	});
	
	

</script>
<script type="text/javascript">
	var csrfHeaderName ="${_csrf.headerName}";
	var csrfTokenValue="${_csrf.token}";

	function checkId(){
		var userid = $('#userid').val();
		$.ajax({
			url:'/member/checkId',
			type:'post',
			data:{userid:userid},
			dataType:'json',
			beforeSend: function(xhr) {
	           xhr.setRequestHeader(csrfHeaderName, csrfTokenValue);
	        },
			success:function(result){
				  if(result!=0){
				        $('.id_ok').css("display", "none");
				        $('.id_already').css("display", "inline-block");
				        alert("중복된 아이디입니다.");
				        $('#userid').val('');
				    } else {
				        $('.id_ok').css("display", "inline-block");
				        $('.id_already').css("display", "none");
				    }
			},
			error:function(){
				alert("에러입니다.");
			}
		});
	};
</script>
<body>


	<section id="container">
	<form action="/member/register" method="post">
	
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
	
		<div class="form-group has-feedback">
			<label class="control-label" for="userid">아이디</label> <input
				class="form-control" type="text" id="userid" name="userid" onblur="checkId()" />
			<span class="id_ok">사용 가능한 아이디입니다.</span>
			<span class="id_already">중복된 아이디입니다.</span>
		</div>
		<div class="form-group has-feedback">
			<label class="control-label" for="userpw">패스워드</label> <input
				class="form-control" type="text" id="userpw" name="userpw" />
		</div>
		<div class="form-group has-feedback">
			<label class="control-label" for="username">이름</label> <input
				class="form-control" type="text" id="username" name="username" />
		</div>
		<div class="form-group has-feedback">
			<button class="control-label" type="submit" id="submit">회원가입</button>
			<button class="cancel btn btn-danger" type="button">취소</button>
		</div>
	</form>
	</section>


</body>
</html>

<%@include file="../includes/footer.jsp"%>