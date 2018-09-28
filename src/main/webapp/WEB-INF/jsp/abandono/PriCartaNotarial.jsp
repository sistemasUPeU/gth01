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
<link
	href="<c:url value='/resources/js/plugins/dropify/css/dropify.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
<link href="<c:url value='/resources/css/remodal.css'/>" type="text/css"
	rel="stylesheet" media="screen,projection">
<link href="<c:url value='/resources/css/remodal-default-theme.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">

<link
	href="<c:url value='/resources/js/plugins/sweetalert/sweetalert.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
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

@media only screen and (min-width: 641px) {
	.remodal {
		max-width: 900px;
	}
}

textarea.materialize-textarea {
	min-height: 10rem;
}

@media only screen and (min-width: 993px) ul.stepper.horizontal
	.step-actions {
	position
	:fixed
	

}
}
</style>
<link
	href="<c:url value='/resources/css/materialize-stepper.min.css'></c:url>"
	rel="stylesheet" type="text/css" />
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
					style="font-family: 'Cormorant Garamond', serif; font-weight: bold">Gestión de Abandono de Trabajo</h1>
			</div>
			<div class="divider"></div>

		</section>
		<div id="contenido">
			<div class="row" style="width: 100%; max-width: 90%">
				<ul class="collapsible popout">
					<li id="autorize" class="active">
						<div class="collapsible-header active">
							<i class="mdi-social-notifications-on"></i> Envío de Primera Carta Notarial
						</div>
						<div class="collapsible-body" style="display: none;">
							<div class="row" style="padding: 1em">
								<div class="contT"></div>
							</div>
						</div>
					</li>
					<li id="autorized" class="">
						<div class="collapsible-header">
							<i class="mdi-toggle-check-box"></i>Justificación 1º Carta - Envío de Segunda Carta Notarial
						</div>
						<div class="card-panel collapsible-body " style="display: none;">
							<div class="row" style="padding: 1em">
								<div class="contP"></div>
							</div>

						</div>

					</li>
					<li id="delivery" class="">
						<div class="collapsible-header">
							<i class="mdi-toggle-check-box"></i>Justificación 2º Carta - Abandono de Trabajo derivados
						</div>
						<div class="card-panel collapsible-body " style="display: none;">
							<div class="row" style="padding: 1em">
								<div class="contE"></div>
							</div>

						</div>

					</li>
				</ul>
			</div>
		</div>
		<div class="remodal" data-remodal-id="modal"
			style="min-height: 80%; width: 75%">
			<button data-remodal-action="close" class="remodal-close"></button>
			<div class="col s12">
				<h4>Envío de la segunda carta notarial</h4>
			</div>
			<br> <label id="idtr" hidden=""></label> <label id="idr"
				hidden=""></label><label id="tipo" hidden=""></label>
			<form method="POST"
				action="<%=request.getContextPath()%>/renaban/SendLetter"
				enctype="multipart/form-data" id="CartaForm">
				<div class="section scrollspy" id="horizontal-stepper">
					<div class="row">
						<div class="section col s12 m12 l12">
							<div class="col s12">
								<div class="card">
									<div class="card-content">
										<ul class="stepper horizontal" id="horizontal"
											style="min-height: 580px">
											<li class="step active">
												<div data-step-label="Mensaje"
													class="step-title waves-effect waves-dark"
													style="width: 11em">Paso 1</div>
												<div class="step-content">
													<div class="row">
														<div class="input-field col s12">
															<i class="mdi-communication-email prefix"></i> <input
																type="email" value="" id="correo" name="correo"
																data-msg="Indique un correo válido" required /> <label
																for="correo">Correo</label>
														</div>
														<div class="input-field col s12">
															<i class="mdi-action-spellcheck prefix"></i> <input
																type="text" id="asunto" name="asunto" class="validate"
																value="DESPIDO POR ABANDONO DE TRABAJO"
																data-msg="Es necesario colocar el asunto del mensaje"
																required /> <label for="mensaje">Asunto</label>
														</div>
														<div class="input-field col s12">
															<i class="mdi-editor-border-color prefix"></i>
															<textarea id="mensaje" class="materialize-textarea"
																style="padding: 0.4rem 0;" name="mensaje"
																style=" overflow-y: scroll;" length=500 maxlength=500
																data-msg="Redacte el mensaje" class="validate" required>
														</textarea>
															<label for="mensaje">Mensaje</label>
															<p id="mensaje2" style="margin-left: 3em">
																<br> <span>Atentamente Recursos Humanos -
																	Universidad Peruana Unión,</span><br> <span>Gracias</span>
															</p>
														</div>
													</div>
													<div class="step-actions">
														<button class="waves-effect waves-dark btn  next-step"
															data-feedback=anyThing id="nexto" class="btn">Siguiente</button>
													</div>
												</div>
											</li>
											<li class="step">
												<div data-step-label="Documento"
													class="step-title waves-effect waves-dark"
													style="width: 11em">Paso 2</div>
												<div class="step-content" style="padding: 0">
													<div class="row">
														<!-- 														-----OJO CON ESTOS ID's------- -->
														<input type="hidden" id="idrenaban" name="idrenaban"
															value="" /> <input type="hidden" id="idcontrato"
															name="idcontrato" value="" /> <input type="hidden"
															id="idtrabajador" name="idtrabajador" value="" />
														<!-- 														------------------------------ -->
														<div class="input-field col s6">
															<input type="file" name="file" class="dropify"
																id="pelon1" data-height="362" class="validate"
																data-msg="Suba la carta notarial escaneada" required />
														</div>
														<div class="input-field col s6" style="padding-top: 3em">
															<span> <input type="date" id="fecha" name="fecha"
																class="datepicker" class="validate"
																data-msg="Indique la fecha" required></span> <label
																for="fecha">Fecha de entrega
																de la carta notarial</label>
														</div>

													</div>

													<div class="step-actions">
														<button class="waves-effect waves-dark btn green"
															data-position="bottom" data-delay="50"
															data-tooltip="I am tooltip" type="submit" id="entredoc">ENVIAR</button>
														<button
															class="waves-effect waves-dark btn-flat previous-step">ATRAS</button>
														<!--                                            <button class="waves-effect waves-dark btn blue" type="submit">cancelar</button> -->

													</div>
												</div>
											</li>

											<!-- 													<div class="preloader-wrapper" -->
											<!-- 														style="display: none; margin-top: -14em; margin-left: 45%"> -->
											<!-- 														<div class="spinner-layer spinner-blue-only"> -->
											<!-- 															<div class="circle-clipper left"> -->
											<!-- 																<div class="circle"></div> -->
											<!-- 															</div> -->
											<!-- 															<div class="gap-patch"> -->
											<!-- 																<div class="circle"></div> -->
											<!-- 															</div> -->
											<!-- 															<div class="circle-clipper right"> -->
											<!-- 																<div class="circle"></div> -->
											<!-- 															</div> -->
											<!-- 														</div> -->
											<!-- 													</div> -->
										</ul>
										<div>
											<div id="card-alert" class="card green"
												style="display: none; margin-top: -2em">
												<div class="card-content white-text">

													<p>
														<i class="mdi-navigation-check"></i> Se ha enviado el
														correo exitosamente.
													</p>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
		<div id="second">
			<div class="remodal" data-remodal-id="modalon">
				<button data-remodal-action="close" class="remodal-close"></button>
				<h4 class="light italic black-text">Justificación del abandono de trabajo</h4>

				<hr>

				<div class="row">
					<div class="col s12">
						<div class="input-field col s12">
							<input type="hidden" id="idrenaban2"/>
							<h5 class="light italic black-text">Descripción:</h5>
							<div class="input-field col s12">
								<i class="mdi-editor-border-color prefix"></i>
								<textarea id="observaciones" class="materialize-textarea"></textarea>
								<label for="observaciones">Redacte la justificación</label>
							</div>
						</div>
					</div>
				</div>

				<button data-remodal-action="cancel" class="remodal-cancel">Cancelar</button>
				<button data-remodal-action="confirm" class="remodal-confirm"
					onclick="JustificarAbandono1()">Enviar</button>
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

	<script
		src="<c:url  value='/resources/js/plugins/dropify/js/dropify.min.js'></c:url>"
		type="text/javascript"></script>
	<script src="<c:url  value='/resources/js/remodal.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/plugins/jquery-validation/jquery.validate.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url  value='/resources/js/materialize-stepper.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/plugins/sweetalert/sweetalert.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/businessCore/PriCartaNotarial.js'></c:url>"
		type="text/javascript"></script>
	<!-- 		<script -->
	<%-- 		src="<c:url value='/resources/js/businessCore/Procesado.js'></c:url>" --%>
	<!-- 		type="text/javascript"></script> -->
</body>
</html>
