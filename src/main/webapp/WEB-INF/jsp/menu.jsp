<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GTH</title>
<%@include file="../../jspf/general.jspf"%>
<style>
.circulo {
	width: 200px;
	height:200px;
	-moz-border-radius: 50%;
	-webkit-border-radius: 50%;
	border-radius: 50%;
	background: #4527a0 ;
	display: inline-block;
}
.circulo2 {
	width: 100px;
	height:100px;
	-moz-border-radius: 50%;
	-webkit-border-radius: 50%;
	border-radius: 50%;
	margin: 25%;
}
.abc {
	margin-top: 25px;
	margin-right: 5px;
	margin-left: 5px;
	margin-bottom: 25px;
	color:white;
}
</style>
</head>
<body>
	<div id="loader-wrapper">
		<div id="loader"></div>
		<div class="loader-section section-left"></div>
		<div class="loader-section section-right"></div>
	</div>

<div>
	<div class='circulo'>
	<div class='circulo2'>
	<i class="mdi-action-favorite-outline large icon-demo abc"></i>
	</div>
	</div>
	<div class='circulo' style="background: #5e35b1  ;">
	<div class='circulo2'>
	<i class="mdi-action-info large icon-demo abc"></i>
	</div>
	</div>
	<div class='circulo' style="background: #311b92   ;">
	<div class='circulo2'>
	<i class="mdi-action-spellcheck large icon-demo abc"></i>
	</div>
	</div>
</div>

</body>
</html>