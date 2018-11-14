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
	href="<c:url value='/resources/js/plugins/chartist-js/chartist.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
</head>
<link href="<c:url value='/resources/css/alertify/alertify.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">


<link
	href="<c:url value='/resources/css/alertify/themes/default.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">

<!-- <link -->
<%-- 	href="<c:url value='/resources/js/plugins/datepick/datepickk.min.css'/>" --%>
<!-- 	type="text/css" rel="stylesheet" media="screen,projection"> -->

<!-- <script -->
<%-- 	src="<c:url value='/resources/js/plugins/datepick/datepickk.min.js'></c:url>" --%>
<!-- 	type="text/javascript"></script> -->
<!-- <link -->
<%-- 	href="<c:url value='/resources/js/plugins/datepick/doc.css'/>" --%>
<!-- 	type="text/css" rel="stylesheet" media="screen,projection"> -->

<!-- 	<link -->
<%-- 	href="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/highlight.js/8.7/styles/zenburn.min.css'/>" --%>
<!-- 	type="text/css" rel="stylesheet" media="screen,projection"> -->
<!-- 	<link -->
<%-- 	href="<c:url value='https://fonts.googleapis.com/css?family=Raleway:400,600'/>" --%>
<!-- 	type="text/css" rel="stylesheet" media="screen,projection"> -->
<!-- 	<link -->
<%-- 	href="<c:url value='https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css'/>" --%>
<!-- 	type="text/css" rel="stylesheet" media="screen,projection"> -->


<!-- 				<script -->
<%-- 		src="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/highlight.js/8.7/highlight.min.js'></c:url>" --%>
<!-- 		type="text/javascript"></script> -->


</head>


<body>
	<%-- 	<%@include file="../../../jspf/header.jspf"%> --%>
	<!-- 	<div id="loader-wrapper"> -->
	<!-- 		<div id="loader"></div> -->
	<!-- 		<div class="loader-section section-left"></div> -->
	<!-- 		<div class="loader-section section-right"></div> -->
	<!-- 	</div> -->
	<!-- 		<div id="main"> -->
	<!-- 		<div class="wrapper"> -->
	<%-- 			<%@include file="../../../jspf/aside_left.jspf"%> --%>
	<%-- 			<%@include file="../../../jspf/info_puesto.jspf"%> --%>

	<!-- 			<section id="content" style="margin-left: 10%; margin-right: 10%;"> -->

	<!--start container-->
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

		<div class="container" style="width: 80%">
			<center>
				<div class="chip red lighten-2 black-text"
					style="height: 50px; margin-bottom: 30px;">
					<h5>Gestionar Solicitud</h5>
				</div>
			</center>

			<!-- 					<div id="icon-prefixes" class="section"> -->
			<h4 class="header">Datos Generales</h4>

			<div class="row">
				<div class="col s12 m9 l8">
					<div class="row card-panel">



						<div class="input-field col s12 l6">
							<i class="mdi-action-account-circle prefix"></i> <input
								id="nombres" type="text" class="validate" disabled> <label
								for="icon_prefix3">Nombres y Apellidos</label>
						</div>
						<div class="input-field col s12 l3">
							<i class="mdi-action-perm-identity prefix"></i> <input id="dni"
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

						<input id="idtrb" class="hide"> <input id="idrol"
							class="hide"> <input id="user" class="hide">





					</div>
				</div>
				<div class="col s12 m3 l4" style="line-height: 215%;">
					<div id="card-alert" class="card cyan lighten-1">
						<div class="card-content white-text" id="message_date"
							style="height: 115px;"></div>
					
					</div>
				</div>
			</div>

			<h4 class="header">Programa de Horarios</h4>

				<div class="row">

					<div class="col s12 m6 l6" id="btn-agregar">

						<p>
							<button
								class="btn-floating btn-large waves-effect waves-light green accent-3 left"
								id="agregar"><i class="mdi-content-add left"></i></button>
						</p>

					</div>
										<div class="col s12 m3 l4 ">
                                <div class="card" style="margin:0;">
                                    <div class="card-content  green white-text" style="padding-top: 16px; padding-bottom: 3px;">
                                        <p class="card-stats-title center"><i class="mdi-action-info-outline"></i> Información </p>
                                        <h6 class="card-stats-number center" style="margin:18px">La primera partición deber ser de 7,8 o 15 días</h6>
                                    </div>
                                   
                                </div>
                            </div>
					<div class="col s12 m3 l2 right">
                                <div class="card" style="margin:0;">
                                    <div class="card-content  green white-text" style="padding-top: 16px; padding-bottom: 3px;">
                                        <p class="card-stats-title center"><i class="mdi-action-event"></i> Total de días</p>
                                        <h4 class="card-stats-number center" id="numdias"></h4>
                                    </div>
                                   
                                </div>
                            </div>

				</div>

				<div class="row" id="space">
<!-- 					<div class="col s12 m12 l6"> -->
<!-- 						<div class="card-panel" style="padding-top: 10px; padding-bottom: 10px;"> -->


<!-- 							<div class="row holiday" style="margin-bottom: 0px;"> -->
<!-- 								<div class="col s3 m12"> -->
<!-- 									<h4 class="header2"><b>Vacaciones N° 1</b></h4> -->
<!-- 								</div> -->
<!-- 								<div class="input-field col s6"> -->
<!-- 									<i class="mdi-action-perm-contact-cal prefix"></i> <input -->
<!-- 										type="text" class="datepicker" id="fe_inicio_1"> <label -->
<!-- 										for="fe_inicio_1">Fecha Inicio</label> -->
<!-- 								</div> -->
<!-- 								<div class="input-field col s6"> -->
<!-- 									<i class="mdi-action-perm-contact-cal prefix"></i> <input -->
<!-- 										type="text" class="datepicker" onchange="settifinal(this.id)" id="fe_final_1"> -->
<!-- 									<label for="fe_final_1">Fecha Fin</label> -->
<!-- 								</div> -->


<!-- 							</div> -->
<!-- 						</div> -->
<!-- 					</div> -->

				</div>




				<div class="row">

					<div class="col s7 m8 l10">
						<button class="waves-effect waves-light btn right" id="print">
							<i class="mdi-action-print right"></i>Imprimir
						</button>
						<!-- 								<a -->
						<!-- 								class="waves-effect waves-light btn modal-trigger" -->
						<!-- 								href="#modal1">Modal</a> -->
					</div>
					<div class="col s5 m4 l2">
						<button class="btn waves-effect waves-light  cyan darken-2 right"
							onclick="confirmarFechas()" id="confirmar">
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
									data-max-file-size="10M" data-errors-position="outside" /> <input
									type="text" id="idvac" name="idvac" value="" class="hide" />
							</div>


						</div>
					</div>



					<div class="col s6 center" style="margin-right: 2em;">
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

			<!-- 			</section> -->
			<!-- 		</div> -->
			<div id="modal1" class="modal" style="width: 850px; height: 2000px;">
				<div class="modal-content" id="show_request">

					<!-- 				D:\\RRHH\\GTH\\gth01\\src\\main\\resources\\jasperreports\\request_report.jasper -->
					<!-- 				<object id="request" data="" type="application/pdf" width="800" -->
					<!-- 					height="600"> </object> -->
				</div>
			</div>
			<br>
			
		
		
		</div>
		<!-- 		</div> -->


		<script
			src="<c:url value='/resources/js/plugins/dropify/js/dropify.min.js'></c:url>"
			type="text/javascript"></script>
		<script
			src="<c:url value='/resources/js/plugins/alertify/alertify.min.js'></c:url>"
			type="text/javascript"></script>
		<script
			src="<c:url value='/resources/js/businessCore/holidays/solicitud.js'></c:url>"
			type="text/javascript"></script>

		<!-- 	<script -->
		<%-- 		src="<c:url value='/resources/js/plugins/datepick/doc.js'></c:url>" --%>
		<!-- 		type="text/javascript"></script> -->
		<!-- 			<script -->
		<%-- 		src="<c:url value='/resources/js/plugins/datepick/demo.js'></c:url>" --%>
		<!-- 		type="text/javascript"></script> -->
		<!-- 			<script -->
		<%-- 		src="<c:url value='https://buttons.github.io/buttons.js'></c:url>" --%>
		<!-- 		type="text/javascript"></script> -->
</body>
</html>