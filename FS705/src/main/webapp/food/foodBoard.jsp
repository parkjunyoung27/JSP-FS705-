<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:if test="${dto eq null }">
	<c:redirect url="foodBoard"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>welcome</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style type="text/css">
/*------------------------------- reset css -------------------------------*/
*{padding:0; margin:0;list-style:none;font-family:'빙그레 메로나체';}
a:link, a:visited{text-decoration:none;color:#333;}

/*------------------------------- wrapper css -------------------------------*/
#wrapper{width:1280px;margin:0 auto;}

/*------------------------------- container css -------------------------------*/
	#container{width:72%;float:left;height:100vh;}
		.boardBox{width:78%;margin:1.7%;float:left;}
			.boardBox h2{width:100%;height:18px;line-height:18px;font-size:18px;font-weight:300;border-bottom:1px solid #eee;padding-bottom:5px;}
				.boardBox h2 img{display:inline-block;width:16px;height:16px;vertical-align:middle;}
			.boardBox .main{width:100%;border-bottom:1px solid #eee;border-right:1px solid #eee;cursor:pointer;transition:0.3s;}
			.boardBox .main:hover{background-color:#D6EAF8;}
				.boardBox ul li{height:35px;line-height:42px;float:left;overflow:hidden;font-size:12px;}
				.boardBox ul li:first-child{width:60%;text-indent:5px;border-left:5px solid #85C1E9;border-radius:5px 0 0 5px;font-size:16px;line-height:35px;}
				.boardBox ul li:nth-child(2){width:17%;}
				.boardBox ul li:nth-child(3){width:10%;}
					.boardBox ul li:nth-child(3) img{height:15px;float:left;padding-top:12px;padding-right:1px;}
				.boardBox ul li:last-child{width:10%;}
 		.writeBox{height:35px; wdith: 50px; line-height:42px;float:right;overflow:hidden;font-size:16px;border:1px solid #eee;c}
</style>
<script>
$(function(){
	//a태그 hover이벤트 tab키 먹히게 하는 기능
	$(".title a").bind("mouseover focus",function(){
		$(this).css({"border":"none"});
		$(this).parents(".main").css({"background-color":"#D6EAF8"});
	});
	$(".title a").bind("mouseout focusout",function(){
		$(this).parents(".main").css({"background-color":"transparent"});
	});
});
</script>
</head>
<body>
<div id="wrapper">
	<c:import url="/header.jsp"/>
	<c:import url="/food/categoryBar.jsp"/>
	<div id="container">	
		<div id="food" class="boardBox">		
			<h2><img alt="맛집" src="./img/food.png"> 맛집</h2>
			<!-- 구간반복지점 -->
			<c:forEach items="${dto }" var="dto">
				<ul class="main" onclick="location.href=''">
					<li class="title"><a href="foodDetail?bno=${dto.bno }">[${dto.subCategory }] ${dto.btitle }  [${dto.foodcommentcount }]</a></li>
					<li>${dto.id }(${dto.name })</li>					
					<li><img alt="좋아요" src="./img/like.png"> ${dto.blike }</li>
					<li>${dto.bdate }</li>
				</ul>
			</c:forEach>
			<!-- 구간반복지점 -->
		<div class="writeBox">
		<a href="./foodWrite">글쓰기</a>	
		</div>
		</div>
	</div>
	<c:import url="/plusBar.jsp"/>
	
	<c:import url="/footer.jsp"/>
</div>
</body>
</html>