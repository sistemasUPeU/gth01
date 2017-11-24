<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="../../../jspf/general.jspf"%>
</head>
<body>
	<%@include file="../../../jspf/header.jspf"%>
	<div id="main">
		<div class="wrapper">
			<%@include file="../../../jspf/aside_left.jspf"%>
			<%@include file="../../../jspf/info_puesto.jspf"%>
			<label>RUTA:</label> <label id="ruta"></label><br>
			<div id="image" SRC="" WIDTH=140 HEIGHT=210 ALT="Producto1"></div>
			<a href="<c:url value="/renuncias/mostrardoc1"/>">ver</a>


			<div class="">
				<center>
					<object type="image/jpeg"
						data="http://localhost:8081/gth/renuncias/mostrardoc1" width="500"
						height="650"></object>
				</center>
			</div>
			<section id="content" class="col m12 l12 s12">
			<h1>PA TODOS</h1>


			</section>

		</div>


	</div>
	
<body>
</html>