<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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

#confirmar_lista {
	margin-top: 25px;
}
</style>
</head>
<body>
	<%@include file="../../../jspf/header.jspf"%>
	<div id="loader-wrapper">
		<div id="loader"></div>
		<div class="loader-section section-left"></div>
		<div class="loader-section section-right"></div>
	</div>
	<div id="main">
		<div class="wrapper">
			<%@include file="../../../jspf/aside_left.jspf"%>
			<section id="content"></section>
		</div>
		<%@include file="../../../jspf/info_puesto.jspf"%>

		<div class="container">
			<center>
				<div class="chip red lighten-2 black-text"
					style="height: 50px; margin-bottom: 30px;">
					<h5>Control de Firmas de Vacaciones</h5>
				</div>
			</center>
			<div class="row">
				<div id="table_contenido" class="col s12 m12 l12"></div>
			</div>

			<div id="modal" class="modal">
				<div class="modal-content">
					<h4>Control de firmas</h4>
					<div class="row">
						<div id="contenedor_fechas"></div>
						<div class="col s4">
							<br> <a id="guardar"
								class="btn waves-effect waves-light modal-action modal-close"><i
								class="mdi-content-save"></i> Guardar</a>
						</div>
						<div id="zorro" class="col s8"></div>
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
		src="<c:url value='/resources/js/businessCore/holidays/control_firma_vacaciones.js'></c:url>"
		type="text/javascript"></script>
</body>
</html>