<%@ page language="java" contentType="text/html; charset=UTF-8"
	%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../../../jspf/general.jspf"%>
<link href="https://fonts.googleapis.com/css?family=Dosis"
	rel="stylesheet">
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

<link href="<c:url value='/resources/css/alertify.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">

<link href="<c:url value='/resources/css/renuncias.css'></c:url>" />
<link href="https://fonts.googleapis.com/css?family=Poiret+One"
	rel="stylesheet">
<link href="<c:url value='/resources/css/remodal.css'/>" type="text/css"
	rel="stylesheet" media="screen,projection">
<link href="<c:url value='/resources/css/remodal-default-theme.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
<style>
@media only screen and (min-width: 641px) {
	.remodal {
		max-width: 75%;
	}
	h6 {
		font-size: 16px
	}
}

@media only screen and (max-width: 640px) {
	.remodal {
		min-width: 100%;
	}
	h5 {
		font-size: 14px
	}
	h1 {
		font-size: 16px
	}
	h6 {
		font-size: 14px
	}
}
.ajs-message.ajs-custom {
	color: #31708f;
	background-color: #d9edf7;
	border-color: #31708f;
	z-index: 999999
}
</style>
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
							<div class="divider"></div>
				<section id="content" class="col m12 l12 s12">
				<div class="container">
					<h1
						style="font-family: 'Poiret One', cursive; font-weight: bold; margin-left: 1em">Registrar
						Renuncia</h1>
				</div>
				<div class="divider"></div>

				</section>
				<div class="row">                                                                                                                          
					<div class="container">
						<form class="col s12">
							<div class="row">
								<div class="input-field col s3"></div>
								<div class="input-field col s5">
									<h5>Ingrese DNI del trabajador</h5>

									<input placeholder="DNI" id="dni" type="text" class="validate"
										onKeypress="if (event.keyCode < 45 || event.keyCode > 57) event.returnValue = false;"
										maxlength=8 autofocus>
								</div>
								<div class="input-field col s4">
									<p>
										<a
											class="btn btn-large waves-effect waves-light yellow darken-4"
											id="buscarDetalle" onclick="buscarDetalle()">Buscar</a>
									</p>
								</div>
							</div>

						</form>
						<div id="fo" class="col s12" hidden>
							<div id="msj" class="card-panel #0091ea light-blue accent-4" style="color:white"></div>
						</div>



					</div>
				</div>
			</div>
			<div class="container" style="margin-bottom: 4em">
				<div id="detalleR" style="display: none">
					<div class="row">
						<div class="col s2">
							<br>
						</div>
						<div class="col s8">
							<div class="plans-container" id="plans">
								<div class="card " style="box-shadow: 0px 0px 20px 20px #888888">
									<div class="card-image #607d8b blue-grey waves-effect ">
										<div class="card-title">
											<h5>DETALLES DEL TRABAJADOR</h5>
										</div>
										<div class="price flow-text ">
											<div class="col m12 l12 s12">
												<div id="profile-page-content" class="row">
													<div class="row card-panel white">
														<div class="col l2 m2 s6">
															<img src="<c:url value="/resources/img/user.png"/>"
																alt=""
																class="circle responsive-img valign profile-image">
														</div>
														<div class="col l3 m6 s6">
															<input type="hidden" id="idt" />
															<h6 class="light italic black-text">
																<strong>Nombres :</strong><span id="nombres"></span><br>
															</h6>
															<h6 class="light italic black-text">
																<strong> Apellido Paterno : </strong><span id="paternos"></span><br>
															</h6>
															<h6 class="light italic black-text">
																<strong> Apellido Materno : </strong><span id="maternos"></span><br>
															</h6>
															<h6 class="light italic black-text">
																<strong> Fecha de Nacimiento : </strong><span
																	id="fecha_nacs"></span>
															</h6>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="price-desc white-text ">
											<form class="col s12 ">
												<div class="row">
													<div class="input-field col s6  ">
														<h6>Fecha de Inicio:</h6>
													</div>
													<div class="input-field col s4 ">
														<h6>
															<span id="fecha_inicio"></span><br>
														</h6>
													</div>
												</div>
											</form>
											<form class="col s12">
												<div class="row">
													<div class="input-field col s6 ">
														<h6>Dirección:</h6>
													</div>
													<div class="input-field col s4 ">
														<h6>
															<span id="direccion"></span><br>
														</h6>
													</div>
												</div>
											</form>
											<form class="col s12">
												<div class="row">
													<div class="input-field col s6 ">
														<h6>Departamento:</h6>
													</div>
													<div class="input-field col s4 ">
														<h6>
															<span id="departamento"></span><br>
														</h6>
													</div>
												</div>
											</form>

											<form class="col s12">
												<div class="row">
													<div class="input-field col s6 ">
														<h6>Area:</h6>
													</div>
													<div class="input-field col s4 ">
														<h6>
															<span id="area"></span><br>
														</h6>
													</div>
												</div>
											</form>
											<form class="col s12">
												<div class="row">
													<div class="input-field col s6">
														<h6>Sección:</h6>
													</div>
													<div class="input-field col s4 ">
														<h6>
															<span id="seccion"></span><br>
														</h6>
													</div>
												</div>
											</form>
											<form class="col s12">
												<div class="row">
													<div class="input-field col s6 ">
														<h6>Puesto:</h6>
													</div>
													<div class="input-field col s4 ">
														<h6>
															<span id="puesto"></span><br>
														</h6>
													</div>
												</div>
											</form>

											<form class="col s12">
												<div class="row">
													<div class="input-field col s6 ">
														<h6>Tipo de contrato:</h6>
													</div>
													<div class="input-field col s4 ">
														<h6>
															<span id="tipo_contrato"></span><br>
														</h6>
													</div>
												</div>
											</form>
										</div>
									</div>
									<div class="card-content"></div>
								</div>
								</section>
							</div>
							<div class="col s2">
								<br>
							</div>
						</div>
						<p class="center">
							<a
								class="waves-effect btn btn-large z #0091ea light-blue accent-4 "
								data-remodal-target="modal">Adjuntar Carta de Renuncia</a>
						</p>
						<div class="card-action center-align">
							<div id="first">
								<div class="remodal" data-remodal-id="modal">
									<button data-remodal-action="close" class="remodal-close"></button>

									<!-- 								<div class="remodal" data-remodal-id="modal" style="width: 75%"> -->
									<!-- 									<button data-remodal-action="close" class="remodal-close" -->
									<!-- 										data-remodal-options="modifier:'width:75%'"></button> -->

									<h1> Registrar Renuncia </h1>
									<form method="POST"
										enctype="multipart/form-data" class="col s12 m8 l11"
										id="RenunciaForm">
										<input type="hidden" name="idcontrato" id="idcontrato" value="">
										<div class="row section">
											<div class="col s12">
												<div class="row">
													<div class="input-field col s5">
														<h6>Ingrese el motivo de la renuncia:</h6>
													</div>
													<div class="input-field col s2">
														<h6>
															Fecha de entrega <br>de carta de renuncia:
														</h6>
													</div>
													<div class="input-field col s5">
														<h6>Adjunte la carta de renuncia:</h6>
													</div>
												</div>
											</div>
											<div class="col s12">
												<div id="mot" class="input-field col s5">
													<select id="motivo" multiple>

													</select>
												</div>
												<div class="input-field col s2">
													<label for=""></label> <input type="text" name="fecha"
														id="fecha" class="datepicker">
													<!-- 														 <input type="text"  id="fechap"> -->

												</div>
												<div class="input-field col s5">

													<input type="file" name="file" class="dropify" id="pelon1"
														data-height="300" />
												</div>
												<div class="row" id="otrosdiv">
													<div class="col s12">
														<div class="row">
															<div class="input-field col s6">
																<textarea id="otros" class="materialize-textarea"></textarea>
																<label for="otros">Ingrese el motivo en
																	particular</label>
															</div>
														</div>
													</div>
												</div>
												<input type="hidden" id="array_motivos" />
											</div>
										</div>
										<br>
										<button data-remodal-action="cancel" class="remodal-cancel">Cancelar</button>
										<button data-remodal-action="confirm" class="remodal-confirm"
											type="submit" id="RegistrarR">Confirmar</button>
								
								</form>
							</div>
							</div>

						</div>

					</div>
				</div>
			</div>
			<hr />
			</div>
			</div>
			<!-- 	<script -->
			<%-- 		src="<c:url value='/resources/js/plugins/prism/prism.js'></c:url>" --%>
			<!-- 		type="text/javascript"></script> -->

			<!-- 	<script -->
			<%-- 		src="<c:url value='/resources/js/plugins/jquery-1.11.2.min.js'></c:url>" --%>
			<div style="position: fixed; width: 100%; bottom: 0; z-index: 5">
				<%@include file="../../../jspf/footer.jspf"%>
			</div>
			<!-- 		type="text/javascript"></script> -->

			<script
				src="<c:url  value='/resources/js/RegistrarRenuncia.js'></c:url>"
				type="text/javascript"></script>
			<script
				src="<c:url  value='/resources/js/plugins/dropify/js/dropify.min.js'></c:url>"
				type="text/javascript"></script>
			<script
				src="<c:url  value='/resources/js/plugins/alertify/alertify.min.js'></c:url>"
				type="text/javascript"></script>

			<script src="<c:url  value='/resources/js/remodal.min.js'></c:url>"
				type="text/javascript"></script>

			<%-- 	<%@include file="../../../resources/js/businessCore/jsAutorizar.jspf"%> --%>
</body>
</html>