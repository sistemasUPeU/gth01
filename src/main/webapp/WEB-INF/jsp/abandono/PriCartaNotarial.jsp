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
<link
	href="<c:url value='/resources/js/plugins/dropify/css/dropify.min.css'/>"
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
					Abandonos</h1>
			</div>
			<div class="divider"></div>

		</section>
		<div id="contenido">
			<div class="row" style="width: 100%; max-width: 90%">
				<ul class="collapsible popout">
					<li id="autorize" class="active">
						<div class="collapsible-header active">
							<i class="mdi-social-notifications-on"></i> Verificar Abandonos
							Pendientes
						</div>
						<div class="collapsible-body" style="display: none;">
							<div class="row" style="padding: 1em">
								<div class="contT"></div>
							</div>
						</div>
					</li>
					<li id="autorized" class="">
						<div class="collapsible-header">
							<i class="mdi-toggle-check-box"></i> Verificar Abandonos
							Autorizados
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
		<div id="modalnotificar" class="modal modal-fixed-footer"
			style="width: 80%; height: 100%; border: 5px solid black">
			<div class="modal-header">
				<div class="center">
					<h4 style="font-family: 'Dosis', sans-serif;">Adjuntar
						Documento Notarial</h4>
				</div>
			</div>
			<div class="modal-content"
				style="padding: 0 !important; overflow-y: hidden">
				<div class="row section">
					<div class="col s12">
						<div class="row center">
							<div class="input-field col s6">
								<h5 style="font-family: 'Dosis', sans-serif;">Correo:</h5>
							</div>
							<div class="input-field col s2 ">
								<h6>
									<span id="correo"></span><br>
								</h6>
							</div>
						</div>
					</div>
				</div>
				<div class="row section">
					<div class="col s12">
						<div class="row">
							<div class="input-field col s5">
								<h5 style="font-family: 'Dosis', sans-serif;">Fecha de
									entrega de la primera carta notarial:</h5>
							</div>
							<br>
							<div class="input-field col s7">
								<h5 style="font-family: 'Dosis', sans-serif;">Adjunte el
									documento:</h5>
							</div>

						</div>
					</div>
					<div class="col s12">
						<div class="input-field col s5">
							<label for=""></label> <input type="date" name="fecha" id="fecha"
								class="datepicker">
						</div>
						<br>
						<div class="input-field col s7">
							<!--                             <p id="mensaje2"> -->
							<!-- 							<br> <span>Atentamente GTH,</span><br> <span>Gracias</span> -->
							<!-- 						</p> -->

							<input type="file" name="file" class="dropify" id="cartaNotarial"
								data-height="300" />

						</div>
					</div>
				</div>
			</div>

			<div class="modal-footer  teal lighten-1  darken-2"
				style="z-index: 5; position: fixed">
				<div class="row center">
					<div class="col s6" style="margin-right: 2em;">
						<button type="submit"
							class="btn waves-effect waves-light green indigo" id=""
							onclick="enviarCorreo()">
							Registrar primer envio<i class="mdi-content-send right"></i>
						</button>

					</div>
					<div class="col s6">
						<a class="btn waves-effect waves-light red modal-close">
							Cancelar </a>

					</div>
				</div>
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
		src="<c:url value='/resources/js/businessCore/PriCartaNotarial.js'></c:url>"
		type="text/javascript"></script>
	<!-- 		<script -->
	<%-- 		src="<c:url value='/resources/js/businessCore/Procesado.js'></c:url>" --%>
	<!-- 		type="text/javascript"></script> -->
</body>
</html>
