<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<%@include file="../../../jspf/general.jspf"%>
<link href="<c:url value='/resources/js/plugins/prism/prism.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="<c:url value='/resources/js/plugins/data-tables/css/jquery.dataTables.min.css'></c:url>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value='/resources/js/plugins/chartist-js/chartist.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
<style type="text/css">
.center-btn {
	text-align: center
}

div.dataTables_length {
	display: none !important;
}

</style>
</head>

<body>
	<%@include file="../../../jspf/header.jspf"%>
	<div id="loader-wrapper">
		<div id="loader"></div>

	</div>
	<div id="main">
		<div class="wrapper">
			<%@include file="../../../jspf/aside_left.jspf"%>
			<section id="content"></section>
		</div>
		<%@include file="../../../jspf/info_puesto.jspf"%>
		<div id="table-datatables">
			<center>
				<div class="chip red lighten-2 black-text"
					style="height: 50px; margin-bottom: 30px;">
					<h5>Aprobar Programa de Vacaciones</h5>
				</div>
			</center>
			<!-- 			<h4 class="header">Aprobar Programa de Vacaciones</h4> -->
			<!-- 			<form action="#" name="myForm"> -->
			<!-- 				<p> -->
			<!-- 					<input name="group1" type="radio" id="test1500" value="1500" /> <label -->
			<!-- 						for="test1500">Sin Aprobar</label> <input name="group1" -->
			<!-- 						type="radio" id="test1600" value="1600" /> <label for="test1600">Aprobados</label> -->
			<!-- 					<input name="group1" type="radio" id="test1700" value="1700" /> <label -->
			<!-- 						for="test1700">Observados</label> -->
			<!-- 				</p> -->
			<!-- 			</form> -->
			<div id="cuerpo" class="container">
				<div class="col s12">
					<ul class="tabs tab-demo z-depth-1" style="width: 100%;">
						<li class="tab col s3"><a href="#test004" class="active">Sin Aprobar</a></li>
						<li class="tab col s3"><a href="#test005" class="">Aprobados</a></li>
						<li class="tab col s3"><a href="#test006"
							class="">Observados</a></li>
					</ul>
				</div>
				<div class="col s12">
					<div id="test004" class="col s12" style="display: block;">
						<div id="contTable"></div>
						<div class="col s24 m12 l6">
							<p>
								<a
									class="btn btn-large waves-effect waves-light light-green darken-4"
									id="confirmar" type="submit">Confirmar</a>
							</p>
						</div>
					</div>
					<div id="test005" class="col s12" style="display: block;">
						<div id="contTable1"></div>
					</div>
					<div id="test006" class="col s12" style="display: block;">
						<div id="contTable3"></div>
					</div>
				</div>


			</div>

		</div>

	</div>

	<script
		src="<c:url value='/resources/js/plugins/prism/prism.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/plugins/data-tables/js/jquery.dataTables.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/plugins/data-tables/data-tables-script.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/businessCore/holidays/aprobar_pv.js'></c:url>"
		type="text/javascript"></script>
</body>
</html>