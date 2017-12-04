<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="../../../jspf/general.jspf"%>
<link
		href="<c:url value='/resources/js/plugins/morris-chart/morris.css'></c:url>"
		rel="stylesheet" type="text/css" />
</head>
<body>	
	<%@include file="../../../jspf/header.jspf"%>
	<div id="loader-wrapper">
		<div id="loader"></div>

	</div>
	<div id="main">
		<div class="wrapper">
			<%@include file="../../../jspf/aside_left.jspf"%>
			<%@include file="../../../jspf/info_puesto.jspf"%>
<!-- 			<label>RUTA:</label> <label id="ruta"></label><br> -->
<!-- 			<div id="image" SRC="" WIDTH=140 HEIGHT=210 ALT="Producto1"></div> -->
<%-- 			<a href="<c:url value="/renuncias/mostrardoc1"/>">ver</a> --%>


			<div class="">
				<center>
					<object type="image/jpeg"
						data="http://localhost:8081/gth/renuncias/mostrardoc1" width="500"
						height="650"></object>
				</center>
			</div>
			<section id="content" class="col m12 l12 s12">
			<h1></h1>


			</section>

		</div>
		h1
		<div id="line-example">
		
		</div>

	</div>
	
	<script
		src="<c:url value='/resources/js/plugins/raphael-min.js'></c:url>"></script>
	<script
		src="<c:url value='/resources/js/plugins/morris-chart/morris.min.js'></c:url>"
		type="text/javascript"></script>
	<script>
		
		Morris.Line({
			  element: 'line-example',
			  data: [
			    { y: '2006', a: 100, b: 90 },
			    { y: '2007', a: 75,  b: 65 },
			    { y: '2008', a: 50,  b: 40 },
			    { y: '2009', a: 75,  b: 65 },
			    { y: '2010', a: 50,  b: 40 },
			    { y: '2011', a: 75,  b: 65 },
			    { y: '2012', a: 100, b: 90 }
			  ],
			  xkey: 'y',
			  ykeys: ['a', 'b'],
			  labels: ['Series A', 'Series B']
			});
	</script>
	
<body>
</html>