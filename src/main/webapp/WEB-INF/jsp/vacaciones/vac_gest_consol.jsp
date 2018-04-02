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
			<div class="row">
				<div class="row">
					<div class="col s12">
						<ul class="tabs tab-demo z-depth-1" style="width: 100%;">
							<li class="tab col s3"><a class="active" href="#test001">Consolidado
									sin aprobar</a></li>
							<li class="tab col s3"><a href="#test002" class="">Consolidado
									aprobado</a></li>
						</ul>
					</div>
					<div class="col s12">
						<div id="test001" class="col s12" style="display: block;">
							<div id="sinAprobar" class="container">
								<div id="contTable"></div>

								<br> <a class="btn waves-effect waves-light right"
									id="confirmar"><i class="mdi-navigation-check"></i>
									Confirmar</a>

								<div class="row">
									<div id="table_contenido" class="col s12 m12 l12"></div>
								</div>
							</div>
						</div>
						<div id="test002" class="col s12" style="display: none;">
							<div class="input-field col s6">
								<a class="waves-effect waves-light btn modal-trigger"
									href="#modal1" id="imprimirCartas">Imprimir</a>
							</div>
							<div id="cuerpoAprobado" class="container">
								<div id="contTableAprobado"></div>

								<div class="row">
									<div id="table_contenido_aprobado" class="col s12 m12 l12"></div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="container">
					<div id="modal" class="modal">
						<div class="modal-content">
							<h4>Solicitud</h4>
							<form method="post"
								action="/gth/vacaciones/consolidado/subirArchivo?${_csrf.parameterName}=${_csrf.token}"
								enctype="multipart/form-data" class="" id="documentoForm">
								<div class="row">
									<div id="verbtnSolicitudsinAprobar">
										<div class="col s6">
											<div class="file-field input-field">
												<div class="btn">
													<span>Solicitud</span> <input type="file">
												</div>
												<div class="file-path-wrapper">
													<input class="file-path validate" type="text">
												</div>
											</div>
										</div>

										<div class="col s6">
											<br> <a id="guardarSolicitud"
												class="btn waves-effect waves-light modal-action modal-close"><i
												class="mdi-content-save"></i>Guardar</a>
										</div>
									</div>
									<div class="col s6">
										<button class="btn waves-effect waves-light"
											style="display: none;" id="btnsubirsolicitud">Subir
											Solicitud</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>

				<div id="modalAprobado" class="modal">
					<div class="modal-content">
						<h4>Control de firmas</h4>

						<div class="row">
							<div id="contenedor_fechas"></div>

							<div class="col s4" style="text-align: center;">
								<br> <br>
								<button id="guardar" type="submit"
									class="btn waves-effect waves-light modal-action modal-close">
									<i class="mdi-content-save"></i>Guardar
								</button>
							</div>
						</div>
						<div class="row">
							<form method="post"
								action="/gth/vacaciones/consolidado/subirArchivo?${_csrf.parameterName}=${_csrf.token}"
								enctype="multipart/form-data" class="" id="documentoForm">
								<div id="verbtnPapeleta">
									<div class="col s6">
										<div class="file-field input-field">
											<div class="btn">
												<span>Papeleta</span> <input type="file">
											</div>
											<div class="file-path-wrapper">
												<input class="file-path validate" type="text">
											</div>
										</div>
									</div>
								</div>
								<div class="col s6">
									<br>
									<button class="btn waves-effect waves-light"
										style="display: none;" id="btnsubirpapeleta">Subir
										Papeleta</button>
								</div>
							</form>

							<div id="verbtnSolicitud"></div>

						</div>
					</div>
				</div>

				<div id="modal1" class="modal">
					<div class="modal-content">
						<object type="application/pdf"
							data="http://localhost:8081/gth/vacaciones/consolidado/reporte"
							id="requestjs"> </object>
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
		src="<c:url value='/resources/js/businessCore/holidays/gest_consol.js'></c:url>"
		type="text/javascript"></script>
</body>
</html>