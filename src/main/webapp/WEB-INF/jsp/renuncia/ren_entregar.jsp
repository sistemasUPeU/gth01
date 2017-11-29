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
		<div class="col s12 m8 l9">
			<div id="data-table-row-grouping col s12 m8 l9"
				class="card-panel display #e3f2fd blue lighten-5"
				style="position: absolute">

				<div class="col s12 m8 l9 contT"></div>
			</div>
		</div>
	</div>

	<div id="modalentregar" class="modal">
		
		<div class="modal-content #e0f7fa cyan lighten-5" style="z-index: 0">

			<div class="card z-depth-2" style="width: 80%; margin-left: 5%">
				<div class="card-image #82b1ff blue accent-1 waves-effect ">

					<hr />
					<div class="price flow-text ">
						<div class="col m12 l12 s12">
							<div id="profile-page-content" class="row">
								<div class="row card-panel">
																	
									<div class="col s12">
										<h4>Comunicar entrega de documentos</h4>
									</div>
									<div class="col s4">
										<input type="text" class=""
											placeholder="Descripcion del motivo del rechazo">
									</div>
									<div class="col s4">
										<label>Fecha:</label>

									</div>
									<div class="col s4">
										<label id="fecha">Fecha</label>
									</div>



								</div>
							</div>
							<div class="row">
								<form class="col s12">
									<div class="row">
										<div class="input-field col s6">
											<input placeholder="Placeholder" id="first_name" type="text"
												class="validate"> <label for="first_name">First
												Name</label>
										</div>
										<div class="input-field col s6">
											<input id="last_name" type="text" class="validate"> <label
												for="last_name">Last Name</label>
										</div>
									</div>
									<div class="row">
										<div class="input-field col s12">
											<input disabled value="I am not editable" id="disabled"
												type="text" class="validate"> <label for="disabled">Disabled</label>
										</div>
									</div>
									<div class="row">
										<div class="input-field col s12">
											<input id="password" type="password" class="validate">
											<label for="password">Password</label>
										</div>
									</div>
									<div class="row">
										<div class="input-field col s12">
											<input id="email" type="email" class="validate"> <label
												for="email">Email</label>
										</div>
									</div>
									<div class="row">
										<div class="col s12">
											This is an inline input field:
											<div class="input-field inline">
												<input id="email" type="email" class="validate"> <label
													for="email" data-error="wrong" data-success="right">Email</label>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col s12">
											This is an inline input field:
											<div class="input-field inline">
												<p id="mensaje1">Señor <span id="nombre"></span> sus documentos ya se encuentran listos para ser entregados,
												por favor se le invita a pasar por la oficina de Gestion de Talentos Humanos (GTH)
												a recoger sus documentos de beneficios sociales en la fecha <span><input type="date">.</span>
												Atentamente GTH,
												Gracias</p>
												
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
					<div class="price-desc white-text">
						
					</div>
				</div>
			</div>

			<div class="modal-footer #1de9b6 teal accent-3" style="z-index: 2">

				<div class="col s6" style="margin-right: 2em;">
					<button type="submit" class="btn waves-effect waves-light indigo"
						id="" onclick="enviarCorreo()">
						Enviar <i class="mdi-content-send right"></i>
					</button>

				</div>
				<div class="col s6" style="margin-right: 2em">
					<a class="btn waves-effect waves-light blue-grey modal-close">
						Cancelar </a>

				</div>
				<!-- 											<a href="#" -->
				<!-- 												class="waves-effect waves-green btn-flat modal-action red modal-close">Cancelar</a> -->




			</div>
		</div>
	</div>


	<%@include file="../../../jspf/footer.jspf"%>
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
		src="<c:url value='/resources/js/businessCore/NotificarRenuncia.js'></c:url>"
		type="text/javascript"></script>
<body>
</html>