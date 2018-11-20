<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../../jspf/general.jspf"%>
<link
	href="<c:url value='/resources/js/plugins/dropify/css/dropify.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">

<link href="<c:url value='/resources/js/plugins/prism/prism.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="<c:url value='/resources/js/plugins/data-tables/css/jquery.dataTables.min.css'></c:url>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value='/resources/js/plugins/chartist-js/chartist.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
	


<style type="text/css">

[type="checkbox"]+label:before {

	border: 0px
}

[type="checkbox"]:checked+label:before {
	display: none
}

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
			<!-- 			<section id="content"></section> -->
		</div>
		<%@include file="../../../jspf/info_puesto.jspf"%>

		<div class="container">
			<center>
				<div class="chip red lighten-2 black-text"
					style="height: 50px; margin-bottom: 30px;">
					<h5>Gestionar Programa de Vacaciones</h5>
				</div>
			</center>
			<div class="row">
				<div class="col s12">
					<ul class="tabs tab-demo z-depth-1" style="width: 100%;">
						<li class="tab col s3"><a href="#test001">Sin solicitud</a></li>
						<li class="tab col s3"><a class="active" href="#test002" class="">Con
								Solicitud</a></li>
						<li class="tab col s3"><a  href="#test003"
							class="">Lista de Aprobados</a></li>
					</ul>
				</div>

				<div class="col s12">
					<div id="test001" class="col s12" style="display: block;">
						<div id="table_contenido1"></div>
					</div>
					<div id="test002" class="col s12" style="display: block;">
						<div id="table_contenido"></div>
<!-- 						<div id="nocargando" class="center-btn row"> -->
<!-- 							<a id="confirmar_lista" class="btn waves-effect waves-light"><i -->
<!-- 								class="mdi-navigation-check"></i> Confirmar</a> -->
<!-- 													<p> -->
<!-- 													<a -->
<!-- 														class="btn btn-large waves-effect waves-light light-green darken-4" -->
<!-- 														id="confirmar-aprob" type="submit">Cuaderno de vacaciones</a> -->
<!-- 												</p> -->
<!-- 						</div> -->
<!-- 						<div id="cargando" class="center-btn"> -->
<!-- 							<div class="preloader-wrapper small active"> -->
<!-- 								<div class="spinner-layer spinner-green-only"> -->
<!-- 									<div class="circle-clipper left"> -->
<!-- 										<div class="circle"></div> -->
<!-- 									</div> -->
<!-- 									<div class="gap-patch"> -->
<!-- 										<div class="circle"></div> -->
<!-- 									</div> -->
<!-- 									<div class="circle-clipper right"> -->
<!-- 										<div class="circle"></div> -->
<!-- 									</div> -->
<!-- 								</div> -->
<!-- 							</div> -->
<!-- 							<br> <label>CONFIRMANDO LISTA</label> -->
<!-- 						</div> -->
					</div>
					<div id="test003" class="col s12" style="display: block;">
						<div id="table_contenido3"></div>
					</div>
				</div>

				<!-- 				<div id="table_contenido" class="col s12 m12 l12"></div> -->
				<br>

			</div>
			<div id="modal2" class="modal" style="width: 850px; height: 2000px;">
				<div class="modal-content">
					<h3>Modificar fecha de inicio y fecha fin</h3>
					<button id="iddet" class="hide" value=""></button>
					<div class="row">
						<div class="col s8 m6 l6">
							<p>Seleccione fecha de inicio:</p>
							<input id="fec_in" type="text" class="datepicker">
						</div>
						<div class="col s8 m6 l6">
							<p>Seleccione fecha de fin:</p>
							<input id="fec_fi" type="text" class="datepicker" disabled>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<a id="fec_up" href="#!"
						class="modal-action modal-close waves-effect waves-green btn-flat">Modificar</a>
				</div>
			</div>
			<div id="modal_gest_programa" class="modal" style="width:1000px;">
			
<%-- 			<%@include file="../../../jspf/request.jspf"%> --%>
			
<!-- 				<iframe src="/gth/vacaciones/solicitud" style="height:450px;width:100%;"></iframe> -->
					<div class="container" style="width: 90%">

			<!-- 					<div id="icon-prefixes" class="section"> -->

					<div class="col s6 m8 l8">
					<div class="row card-panel">
						<div class="input-field col s12 l6">
							<i class="mdi-action-account-circle prefix"></i> <input
								id="nombres_modal" type="text" class="validate" disabled> <label
								for="icon_prefix3">Nombres y Apellidos</label>
						</div>
						<div class="input-field col s12 l3">
							<i class="mdi-action-perm-identity prefix"></i> <input id="dni_modal"
								type="number" class="validate" disabled> <label
								for="icon_telephone">DNI</label>
						</div>
						<div class="input-field col s12 l3">
							<i class="mdi-content-content-paste prefix"></i> <input id="tipo"
								type="text" class="validate"
								<%-- 							 value="${tipo}"  --%>
							disabled> <label
								for="icon-request">Tipo solicitud</label>
						</div>

						<input id="idtrb_modal" class="hide"> <input id="idrol_modal"
							class="hide"> <input id="user_modal" class="hide">

					</div>
				</div>
		
		
		
				<div class="container" style="width: 60%" >
				<div id="card-alert" class=" card cyan darken-2 center" >
					<div class="card-content white-text" id="message_date"
						style="margin: 2px;"></div>
				
				</div>
				
				</div>


			<h4 class="header">Programa de Horarios</h4>
				<div class="row">
					<div class="col s12 m4 l5" id="btn-agregar" style="margin-bottom:10px;" >
						<p>
							<button
								class="btn-floating btn-large waves-effect waves-light green accent-3 left"
								id="agregar"><i class="mdi-content-add left"></i></button>
						</p>

					</div>
						<div class="col s12 m5 l4 " style="margin-bottom:10px;">
                                <div class="card" style="margin:0;">
                                    <div class="card-content  green white-text" style="padding-top: 16px; padding-bottom: 3px;">
                                        <p class="card-stats-title center"><i class="mdi-action-info-outline"></i> Información </p>
                                        <h6 class="card-stats-number center" style="margin:18px">La primera partición deber ser de 7,8 o 15 días</h6>
                                    </div>
                                   
                                </div>
                        </div>
					<div class="col s12 m3 l3 right" style="margin-bottom:10px;">
                                <div class="card" style="margin:0;">
                                    <div class="card-content  green white-text" style="padding-top: 16px; padding-bottom: 3px;">
                                        <p class="card-stats-title center"><i class="mdi-action-event"></i> Total de días</p>
                                        <h4 class="card-stats-number center" id="numdias"></h4>
                                    </div>
                                   
                                </div>
                            </div>

				</div>

				<div class="row" id="space">
				</div>




				<div class="row">

					<div class="col s7 m8 l9">
						<button class="waves-effect waves-light btn right" id="print">
							<i class="mdi-action-print right"></i>Imprimir
						</button>
						<!-- 								<a -->
						<!-- 								class="waves-effect waves-light btn modal-trigger" -->
						<!-- 								href="#modal1">Modal</a> -->
					</div>
					<div class="col s5 m4 l3">
						<button class="btn waves-effect waves-light  cyan darken-2 right"
							onclick="confirmarFechas()" id="confirmar" style="padding-left: 20px; padding-right: 20px;">
							Confirmar<i class="mdi-navigation-check right"></i>
						</button>

					</div>


				</div>

				<form method="post"
					action="/gth/solicitud/archivos?${_csrf.parameterName}=${_csrf.token}"
					enctype="multipart/form-data" class="col s12 m8 l11"
					id="documentoForm">

					<div id="file-upload" class="section center">

						<div class="row section"
							style="margin-left: 20%; margin-right: 20%">

							<div class="col s12 m12 l12 center">
								<input type="file" name="file" id="file-input" class="dropify"
									data-max-file-size="10M" data-errors-position="outside" required/> <input
									type="text" id="idvac" name="idvac" value="" class="hide" />
							</div>


						</div>
					</div>



					<div class="col s12 center">
						<button type="submit" class="btn waves-effect waves-light center"
							id="subir">
							Enviar <i class="mdi-content-send right"></i>
						</button>
						<%-- 							<input type="hidden" name="${_csrf.parameterName}" --%>
						<%-- 							value="${_csrf.token}" /> --%>

					</div>
				</form>

				<!-- 					<div class="row"> -->
				<!-- 						<div class="col s7 m8 l12 center"> -->
				<!-- 							<a class="waves-effect waves-light btn center" id="subir"><i -->
				<!-- 								class="mdi-file-file-upload right"></i>Guardar documento</a> -->
				<!-- 						</div> -->
				<!-- 					</div> -->



			</div>
			<br>
			

				
				
				
				<!--     <div class="modal-footer"> -->
				<!--       <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Agree</a> -->
				<!--     </div> -->
			</div>
			<input id='username' class='hide' />
		
			<div class="hide" id="hide_link_pdf"></div>
		
		
			<div id="modal_review" class="modal modal-fixed-footer" >
				<div class="modal-content" id="show_request">
					<div class="row">

							<div class="col s3 offset-s3 " id="column1">
			
							</div>
							<div class="col s5 m5 l5 " id="column2">
		
							</div>

					</div>
					<div id="tabla-detalle">
					
					</div>
				</div>
				<div class="modal-footer">
					<a id="fec_up" href="#!"
						class="modal-action modal-close waves-effect waves-green btn-flat" onclick="guardarCambios()">Guardar Cambios</a>
				</div>
			</div>
			
			<div id="modal_review_aprobados" class="modal modal-fixed-footer" >
				<div class="modal-content" id="show_request">
					<div class="row">

							<div class="col s3 offset-s3 " id="column1_ap">
			
							</div>
							<div class="col s5 m5 l5 " id="column2_ap">
		
							</div>

					</div>
					<div id="tabla-detalle_ap">
					
					</div>
				</div>
				<div class="modal-footer">
					<a id="fec_up" href="#!"
						class="modal-action modal-close waves-effect waves-green btn-flat" >Cerrar</a>
				</div>
			</div>
		
		
		
		
		
		</div>
	</div>
	<script
		src="<c:url value='/resources/js/plugins/dropify/js/dropify.min.js'></c:url>"
		type="text/javascript">
		$(document).ready(function() {
			//listar();
			READALL();
			listarTrabajadoresConSoli();

		});
		
		
	</script>

	<script type="text/javascript">
		function loadProfile() {
			//location.href="<%=request.getContextPath()%>/trabajador/profile";
		}
		
	</script>
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
		src="<c:url value='/resources/js/businessCore/holidays/gestionar_programa.js'></c:url>"
		type="text/javascript"></script>
	
	<script
	src="<c:url value='/resources/js/businessCore/holidays/solicitud.js'></c:url>"
	type="text/javascript"></script>


</body>
</html>

