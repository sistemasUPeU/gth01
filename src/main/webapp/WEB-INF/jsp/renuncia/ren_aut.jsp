<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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


		<div class="row">
			<div id="cuadro1" class="col-sm-12 col-md-12 col-lg-12">
				<div class="col-sm-offset-2 col-sm-8">
					<h3 class="text-center">
						<small class="mensaje"></small>
					</h3>
				</div>
				<div class="table-responsive col-sm-12">
					<table id="tablita" class="table table-bordered table-hover"
						cellspacing="0" width="100%">
						<thead>
							<tr>
								<th>Mes</th>
								<th>Foto</th>
								<th>Nombres y Apellidos</th>
								<th>Puesto</th>
								<th>Area</th>
								<th>Departamento</th>
								<th>Tipo de Contrato</th>
								<th>Fecha Inicio</th>
								<th>DNI</th>
								<th>Estado</th>
								<th>Detalle</th>
							</tr>
						</thead>
					</table>
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
	<%@include file="../../../resources/js/businessCore/jsAutorizar.jspf"%>
<body>
</html>