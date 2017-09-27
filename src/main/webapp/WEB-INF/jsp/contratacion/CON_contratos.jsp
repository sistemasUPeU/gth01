<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">

<head>
<title>GTH</title>
<%@include file="../../../jspf/general.jspf"%>
<link
	href="<c:url value='resources/js/plugins/jvectormap/jquery-jvectormap.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="<c:url value='resources/js/plugins/chartist-js/chartist.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">

</head>

<body>
	<%@include file="../../../jspf/header.jspf"%>

	<div id="main">
		<div class="wrapper">

			<%@include file="../../../jspf/aside_left.jspf"%>

			<section id="content"></section>




		</div>


	</div>

	<%@include file="../../../jspf/footer.jspf"%>
<body>
</html>