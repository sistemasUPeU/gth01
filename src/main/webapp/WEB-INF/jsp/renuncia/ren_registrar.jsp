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
	<link
	href="<c:url value='/resources/js/plugins/dropify/css/dropify.min.css'/>"
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
		<div id="table-datatables">
		<div class="wrapper">
			<section id="content" class="col m12 l12 s12"> </section>
			<div class="row">
				<div class="container">
					<form class="col s12">
						<div class="row">
							<div class="input-field col s3"></div>
							<div class="input-field col s5"> 
								<h5>Ingrese DNI del trabajador</h5>
								<input placeholder="DNI" id="dni" type="text"
									class="validate" onKeypress="if (event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;" maxlength=8>
							</div>
							<div class="input-field col s4">
								<p>
									<a
										class="btn btn-large waves-effect waves-light yellow darken-4" onclick="buscarDetalle()">Buscar</a>
								</p>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<div class="container" style="margin-bottom:3em">
		<div id="detalleR">

			<section id="content" class="col m12 l12 s12">
			<div id="profile-page-content" class="row">
				<div class="row card-panel">
					<div class="col l1 m1 s6">
						<img src="<c:url value="/resources/img/user.png"/>" alt=""
							class="circle responsive-img valign profile-image">
					</div>
					<div class="col l3 m3 s6">
						<button class="btn green accent-3 waves-effect waves-light">
							<i class="mdi-image-camera-alt left"></i>Cambiar Foto
						</button>
						<h6 class="light italic black-text">
							<strong><h6 >Nombres : </strong><span id="nombres"></span><br> <strong></h6><h6>Apellido
									Paterno : </strong><span id="paterno"></span><br> <strong></h6><h6>Apellido
									Materno : </strong><span id="materno"></span><br> <strong></h6><h6>Fecha
									de Nacimiento : </strong><span id="fecha_nac"></span> </h6>
						</h6>
					</div>
				</div>
			</div>
			</section>
			<form class="col s12">
				<div class="row">
					<div class="input-field col s6">
						<h5>Fecha de Inicio:</h5>
					</div>
					<div class="input-field col s4">
						<input placeholder="fecha_inicio" id="fecha_inicio" type="text"
							class="validate" disabled>
					</div>
				</div>
			</form>
			<form class="col s12">
				<div class="row">
					<div class="input-field col s6">
						<h5>Dirección:</h5>
					</div>
					<div class="input-field col s4">
						<input placeholder="Dirección" id="direccion" type="text"
							class="validate" disabled>
					</div>
				</div>
			</form>
			<form class="col s12">
				<div class="row">
					<div class="input-field col s6">
						<h5>Departamento:</h5>
					</div>
					<div class="input-field col s4">
						<input placeholder="Departamento" id="departamento" type="text"
							class="validate" disabled>
					</div>
				</div>
			</form>
			<form class="col s12">
				<div class="row">
					<div class="input-field col s6">
						<h5>Area:</h5>
					</div>
					<div class="input-field col s4">
						<input placeholder="Area" id="area" type="text"
							class="validate" disabled>
					</div>
				</div>
			</form>
			<form class="col s12">
				<div class="row">
					<div class="input-field col s6">
						<h5>Sección:</h5>
					</div>
					<div class="input-field col s4">
						<input placeholder="sección" id="seccion" type="text"
							class="validate" disabled>
					</div>
				</div>
			</form>
			<form class="col s12">
				<div class="row">
					<div class="input-field col s6">
						<h5>Puesto:</h5>
					</div>
					<div class="input-field col s4">
						<input placeholder="puesto" id="puesto" type="text"
							class="validate" disabled>
					</div>
				</div>
			</form>
			
			<form class="col s12">
				<div class="row">
					<div class="input-field col s6">
						<h5>Tipo de contrato:</h5>
					</div>
					<div class="input-field col s4">
						<input placeholder="tito de contrato" id="tipo_contrato" type="text"
							class="validate" disabled>
					</div>
				</div>
			</form>
	
			</div>
			<div class="col s12 m8 l9" style='display:none'>
				<p>
					<a class="waves-effect waves-light btn modal-trigger  teal "
						href="#modal3">Adjuntar Carta de Renuncia</a>
				</p>
				<div id="modal3" class="modal">
					<div class="modal-content teal white-text">
						<div class="divider"></div>
						<div class="row section">
							<div class="col s12 m4 l3">
								<p>Subir archivo</p>
							</div>
							<div class="col s12 m8 l9">
								<input type="file"  class="dropify"
									data-height="500" />
							</div>
						</div>
					</div>
					<div class="modal-footer  teal lighten-4">
						 <a href="#"
							class="waves-effect waves-red btn-flat modal-action modal-close">Enviar</a>
						<a href="#"
							class="waves-effect waves-green btn-flat modal-action modal-close">Cancelar</a>
					</div>
				</div>
			</div> 
		</div>
		<hr />
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
<!-- 	<script -->
<%-- 		src="<c:url value='/resources/js/plugins/jquery-1.11.2.min.js'></c:url>" --%>
<!-- 		type="text/javascript"></script> -->
		<script 
        src="<c:url  value='/resources/js/carpeta.js'></c:url>"
        type="text/javascript" ></script>
        <script 
        src="<c:url  value='/resources/js/plugins/dropify/js/dropify.min.js'></c:url>"
        type="text/javascript" ></script>
	<%@include file="../../../resources/js/businessCore/jsAutorizar.jspf"%>
</body>
</html>