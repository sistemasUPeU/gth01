<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">

<head>
<%@include file="../../../jspf/general.jspf"%>

<link href="<c:url value='/resources/js/plugins/prism/prism.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
<link href="<c:url value='/resources/css/dataTables.min.css'></c:url>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value='/resources/css/responsive.dataTables.min.css'></c:url>"
	rel="stylesheet" type="text/css" />

<link href="<c:url value='/resources/css/alertify.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">

<link href="https://fonts.googleapis.com/css?family=Cormorant+Garamond"
	rel="stylesheet">
<style>
div.dataTables_wrapper {
	width: auto;
	margin: 0 auto;
}

.dataTables_scroll {
	overflow: auto;
}

.display {
	width: 100%;
}

table.dataTable tbody th, table.dataTable tbody td {
	white-space: nowrap;
}
</style>
</head>

<body class="">
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
		<section id="content" class="col m12 l12 s12">
			<div class="center">
				<h1
					style="font-family: 'Cormorant Garamond', serif; font-weight: bold">Verificar
					Renuncias</h1>
			</div>
			<div class="divider"></div>

		</section>
		<div id="contenido">
			<div class="row" style="width: 100%; max-width: 90%">
				<ul class="collapsible popout">
					<li id="autorize" class="active">
						<div class="collapsible-header active">
							<i class="mdi-social-notifications-on"></i> Renuncias Pendientes
						</div>
						<div class="collapsible-body" style="display: none;">
							<div class="row" style="padding: 1em">
								<div class="contT"></div>
							</div>
						</div>
					</li>
					<li id="autorized" class="">
						<div class="collapsible-header">
							<i class="mdi-toggle-check-box"></i> Renuncias Autorizados
						</div>
						<div class="card-panel collapsible-body " style="display: none;">
							<div class="row" style="padding: 1em">
								<div class="contP"></div>
							</div>

						</div>

					</li>
				</ul>
			</div>
		</div>
	</div>

	<div style="position: fixed; width: 100%; bottom: 0; z-index: 5">
		<%@include file="../../../jspf/footer.jspf"%>
	</div>

	<script
		src="<c:url value='/resources/js/plugins/prism/prism.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/jquery.dataTables.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/dataTables.responsive.min.js'></c:url>"
		type="text/javascript"></script>

	<%-- 		<%@include file="../../../resources/js/businessCore/jsAutorizar.jspf"%> --%>
	<script
		src="<c:url  value='/resources/js/plugins/dropify/js/dropify.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url  value='/resources/js/plugins/alertify/alertify.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/businessCore/VerificarRen.js'></c:url>"
		type="text/javascript"></script>
	<!-- 		<script -->
	<%-- 		src="<c:url value='/resources/js/businessCore/Procesado.js'></c:url>" --%>
	<!-- 		type="text/javascript"></script> -->
</body>
</html>
