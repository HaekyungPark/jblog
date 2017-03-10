<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
$(function(){
	$("#join-form").submit(function(){
		//회원 가입 폼 유효성 검증(validation)
		//1.이름
		var name = $( "#name" ).val();
		if( name == "" ) {
			alert( "이름이 비어 있습니다." );
			$( "#name" ).focus();
			return false;
		}
		
		//2. 아이디
		var usersId = $( "#usersId" ).val();
		if( usersId == "" ) {
			alert( "아이디가 비어 있습니다." );
			$( "#usersId" ).focus();
			return false;
		}
		
		var isVisible = $("#imgIdChecked").is( ":visible" );
		if( isVisible == false ){
			alert( "아이디 중복 체크를 해 주세요.");
			return false;
		}
		
		//3. 비밀번호
		var password = $( "input[type='password']" ).val();
		if( password == "" ) {
			alert( "비밀번호가 비어 있습니다." );
			$( "input[type='password']" ).focus();
			return false;
		}
		
		//4. 약관 동의
		var isChecked = $( "#agree-prov" ).is( ":checked" );
		if( isChecked == false ) {
			alert( "약관 동의를 해 주세요." );
			return false;
		}
		//return true;
	});
	
	$( "input[type='button']" ).click( function(){
		var usersId = $( "#usersId" ).val();
		if( usersId == "" ) {
			$( "#usersId" ).focus();
			return;
		}
		
		/* ajax 통신 */
		$.ajax( {
		    url : "/jblog/user/checkId?usersId=" + usersId,
		    type: "get",
		    dataType: "json",
		    data: "",
		//  contentType: "application/json",
		    success: function( response ){
		       if( response.result == "fail" ) {
		    	   console.log( "error:" + response.message );
		    	   return;
		       }
		       //통신성공(response.result == "success")
		       if(response.data == "exist"){
					$( "#dialogMessage" ).dialog();
		    	   
		    	   $("#usersId").
		    	   val("").
		    	   focus();
		    	   return;
		       }
		     	//존재하지 않는 경우(사용가능)
		       $( "#imgIdChecked" ).show();
		       $( "input[type='button']" ).hide();
		       },
		       error: function( XHR, status, error ){
			       console.error( status + " : " + error );
			    }
			});
	});
	$( "#usersId" ).change( function(){
		 $( "#imgIdChecked" ).hide();
	      $( "input[type='button']" ).show();
	    
	});
});
</script>
</head>
<body>
	<div class="center-content">
		<c:import url="/WEB-INF/views/include/header.jsp"/>
		<form:form 
					modelAttribute="userVo"
					class="join-form"
					id="join-form" 
					name="joinForm" 
					method="post" 
					action="${pageContext.request.contextPath }/user/join">
			<label class="block-label" for="name">이름</label>
			<form:input path="name" />
			<spring:hasBindErrors name="userVo">
 				<c:if test="${errors.hasFieldErrors('name') }">
					<p style="text-align:left;padding:5px 0; color:red">
      						<strong>
     								<spring:message 
    								code="${errors.getFieldError( 'name' ).codes[0] }" 
    								text="${errors.getFieldError( 'name' ).defaultMessage }" />
      						</strong>
 							</p>
 						</c:if>
			</spring:hasBindErrors>
			
			<label class="block-label" for="blog-id">아이디</label>
			<form:input path="usersId"/>
			<input id="btn-checkid" type="button" value="id 중복체크">
			<img id="imgIdChecked" style="display: none;" src="${pageContext.request.contextPath}/assets/images/check.png">
			<p style="font-weight:bold; text-align:left;padding:5px 0; color:red">
				<form:errors path="usersId" />
			</p>
					
			<label class="block-label" >패스워드</label>
			<form:password path="password" />
			<p style="font-weight:bold; text-align:left;padding:5px 0; color:red">
				<form:errors path="password" />
			</p>
			

			<fieldset>
				<legend>약관동의</legend>
				<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
				<label class="l-float">서비스 약관에 동의합니다.</label>
			</fieldset>

			<input type="submit" value="가입하기">

		</form:form>
	</div>
	<div id="dialogMessage" title="아이디 중복체크" style="display:none">
  		<p>존재하는 아이디 입니다.<br>다른 아이디를 사용해 주세요</p>
	</div>
</body>
</html>