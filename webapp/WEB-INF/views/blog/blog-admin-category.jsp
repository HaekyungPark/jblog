<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
var count = 1;
var isEnd = false;
var render = function(vo){
	var html = "<tr id='tr-"+ vo.cno + "'>"+
			   "<td>"+ count + "</td>" +
	   		   "<td>" + vo.name  + "</td>" +
	   		   "<td>  10  </td>" +
	           "<td>" + vo.description  + "</td>" +
	           "<td><img src='${pageContext.request.contextPath}/assets/images/delete.jpg' data-no='"+ vo.cno + "'></td>" + 
	           "</tr>";
	           count++;
		$( ".admin-cat" ).append( html );

}

var fetchList = function(){
	if( isEnd == true ) {
		return;
	}
	$.ajax( {
		url : "/jblog/api/category/list" ,
		type: "get",
	    dataType: "json",
	    data: "id=${authUser.usersId }",
	//  contentType: "application/json",
	    success: function( response ){
	    	if( response.result != "success" ) {
	    		console.log( response.message );
	    		return;
	    	} 
	    	
	    	if( response.data.length == 0 ) {
	    		isEnd = true;
	    		return;	
	    	}
	    	
	    	$( response.data ).each( function(index, vo){
	    		render(vo);
	    	});
	    },
	    error: function( XHR, status, error ){
	       console.error( status + " : " + error );
	   	}
  });
}
$(function(){
	$( "#write-form" ).submit( function(event){
		// 폼의 submit 기본 이벤트 처리를 막는다.
		event.preventDefault();
		
		/* ajax 입력 */
		$.ajax( {
			url : "/jblog/api/category/add",
			type: "post",
		    dataType: "json",
		    data: "name=" + $("input[name='name']").val() + "&" + 
		          "description=" + $("input[name='description']").val()+
		          "&"+"blogId="+ $("input[name='blogId']").val(),
		    success: function( response ){
				console.log( response );
				render( response.data, true );
		    },
		    error: function( XHR, status, error ){
		       console.error( status + " : " + error );
		   	}
	    });
		return false;
	});
	//삭제 버튼 클릭 이벤트 매핑(Live Event Mapping)
	$( document ).on( "click", ".admin-cat tr td img", function(event){
		event.preventDefault();
		var $a = $(this);
		var cno = $a.attr( "data-no" );
  	  /* ajax 통신(삭제) */
		  $.ajax( {
			url : "/jblog/api/category/delete",
			type: "post",
		    dataType: "json",
		    data: "cno=" + cno,
		    success: function( response ){
		    	if( response.result != "success" ) {
		    		console.log( response.message );
		    		dialogDeleteForm.dialog( "close" );
		    		return;
		    	}
		    	
		    	$("#tr-"+cno).remove();
		    	count--;
		    },
		    error: function( XHR, status, error ){
		       console.error( status + " : " + error );
		   	}
	    });	  
	});
	
	//첫 페이지 로딩
	fetchList();

	    	    	  
	});

</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/blog_header.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<ul class="admin-menu">
					<li><a
						href="${pageContext.request.contextPath }/${authUser.usersId }/admin/basic">기본설정</a></li>
					<li class="selected">카테고리</li>
					<li><a
						href="${pageContext.request.contextPath }/${authUser.usersId }/admin/write">글작성</a></li>
				</ul>
				
				<table class="admin-cat">
						<tr>
							<th>번호</th>
							<th>카테고리명</th>
							<th>포스트 수</th>
							<th>설명</th>
							<th>삭제</th>
						</tr>
				</table>

				<h4 class="n-c">새로운 카테고리 추가</h4>
				<form id="write-form"
					action=""
					method="post">
					<input type="hidden" name="blogId" value="${authUser.usersId }">
					<table id="admin-cat-add">
						<tr>
							<td class="t">카테고리명</td>
							<td><input type="text" name="name"></td>
						</tr>
						<tr>
							<td class="t">설명</td>
							<td><input type="text" name="description"></td>
						</tr>
						<tr>
							<td class="s">&nbsp;</td>
							<td><input type="submit" value="카테고리 추가"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/footer.jsp" />
	</div>
</body>
</html>