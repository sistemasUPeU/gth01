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
						<li class="tab col s3"><a href="#test002" class="">Con
								Solicitud</a></li>
						<li class="tab col s3"><a class="active" href="#test003"
							class="">Lista de Aprobados</a></li>
					</ul>
				</div>

				<div class="col s12">
					<div id="test001" class="col s12" style="display: block;">
						<div id="table_contenido1"></div>
					</div>
					<div id="test002" class="col s12" style="display: block;">
						<div id="table_contenido"></div>
						<div id="nocargando" class="center-btn row">
							<a id="confirmar_lista" class="btn waves-effect waves-light"><i
								class="mdi-navigation-check"></i> Confirmar</a>
							<!-- 						<p> -->
							<!-- 						<a -->
							<!-- 							class="btn btn-large waves-effect waves-light light-green darken-4" -->
							<!-- 							id="confirmar-aprob" type="submit">Cuaderno de vacaciones</a> -->
							<!-- 					</p> -->
						</div>
						<div id="cargando" class="center-btn">
							<div class="preloader-wrapper small active">
								<div class="spinner-layer spinner-green-only">
									<div class="circle-clipper left">
										<div class="circle"></div>
									</div>
									<div class="gap-patch">
										<div class="circle"></div>
									</div>
									<div class="circle-clipper right">
										<div class="circle"></div>
									</div>
								</div>
							</div>
							<br> <label>CONFIRMANDO LISTA</label>
						</div>
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
			<div id="modal1" class="modal">
				<div class="modal-content">
					<!--start container-->
					<div class="container">
						<!-- 					</div> -->
						<h4 class="header">Programa de Horarios</h4>
						<div class="row">

							<div class="col s12 m12 l12" id="btn-agregar">

								<p>
									<a
										class="btn-floating btn-large waves-effect waves-light green accent-3 left"
										id="agregar"><i class="mdi-content-add left"></i></a>
								</p>

							</div>

							<input id="idtrb" class="hide"> <input id="tiposolicitud"
								class="hide"> <input id="idvacaciones" class="hide">

						</div>
						<br>
						<div class="row" id="space">
							<div class="col s12 m12 l12">
								<div class="card-panel">
									<h4 class="header2">Vacaciones 1</h4>
									<div class="row">
										<form class="col s12">
											<div class="row">
												<div class="input-field col s6">
													<i class="mdi-action-perm-contact-cal prefix"></i> <input
														type="text" class="datepicker" id="fe_inicio_1"> <label
														for="dob">Fecha Inicio</label>
												</div>
												<div class="input-field col s6">
													<i class="mdi-action-perm-contact-cal prefix"></i> <input
														type="text" class="datepicker" id="fe_final_1" disabled>
													<label for="dob">Fecha Fin</label>
												</div>

											</div>
											<div class="row">
												<div class="col s10 m12">
													<a
														class="btn-floating waves-effect waves-light  red darken-4 right"><i
														class="mdi-action-delete center"></i></a>
												</div>
											</div>

										</form>
									</div>
								</div>
							</div>

						</div>




						<div class="row">

							<div class="col s7 m8 l5">
								<a class="waves-effect waves-light btn right" id="printRequest"><i
									class="mdi-action-print right"></i>Imprimir</a>
								<!-- 								<a -->
								<!-- 								class="waves-effect waves-light btn modal-trigger" -->
								<!-- 								href="#modal1">Modal</a> -->
							</div>
							<div class="col s5 m4 l5">
								<a class="btn waves-effect waves-light  cyan darken-2 right"
									onclick="insertar()" id="confirmarSolicitud" disabled>Confirmar<i
									class="mdi-navigation-check right"></i></a>

							</div>


						</div>

						<form method="post"
							action="/gth/solicitud/archivos?${_csrf.parameterName}=${_csrf.token}"
							enctype="multipart/form-data" class="col s12 m8 l11"
							id="uploadForm">

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
								<button type="submit"
									class="btn waves-effect waves-light center" id="saveFile">
									Enviar <i class="mdi-content-send right"></i>
								</button>
								<%-- 							<input type="hidden" name="${_csrf.parameterName}" --%>
								<%-- 							value="${_csrf.token}" /> --%>

							</div>
						</form>



					</div>

				</div>
				<!--     <div class="modal-footer"> -->
				<!--       <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Agree</a> -->
				<!--     </div> -->
			</div>
			<input id='username' class='hide' />
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
		var divisiones = 0;
		$(document)
				.ready(
						function() {

							try {
								$
										.getJSON(
												gth_context_path
														+ '/components',
												"opc=usuario",
												function(objJSON) {
													if (objJSON !== null) {

														var s = objJSON.datos_usuario;
														var d = objJSON.dni;
														var t = objJSON.idtrb;
														var r = objJSON.idrol;
														var u = objJSON.username;
														console.log(s + d + t);
														$("#nombres").val("");
														$("#nombres").val(s);
														$("#dni").val(d);
														$("#idtrb").val(t);
														$("#idrol").val(r);
														$("#username").val(u);

														Materialize
																.updateTextFields();
													} else {
														console
																.error("No se esta cargando la información");
													}

													var rol = $("#idrol").val();
													console.log(rol);
													if (rol == "ROL-0003") {
														divisones = 2;

													} else {
														if (rol == "ROL-0008") {
															divisones = 3;
														} else {
															console
																	.log("sin nada");
															divisones = 1;
															$("#btn-agregar")
																	.hide();
														}

													}

												});
							} catch (e) {
								console.error("error al listar info : " + e);
							}

							$('.dropify').dropify();

							// 							Translated
							$('.dropify-fr')
									.dropify(
											{
												messages : {
													// default: 'Glissez-déposez un fichier ici ou cliquez',
													replace : 'Glissez-déposez un fichier ou cliquez pour remplacer',
													remove : 'Supprimer',
													error : 'Désolé, le fichier trop volumineux'
												}
											});

							// 							Used events
							var drEvent = $('.dropify-event').dropify();

							drEvent
									.on(
											'dropify.beforeClear',
											function(event, element) {
												return confirm("Do you really want to delete \""
														+ element.filename
														+ "\" ?");
											});

							drEvent.on('dropify.afterClear', function(event,
									element) {
								alert('File deleted');
							});

						});

		var fecha_extra = "";
		var fecha_recontraextra = "";
		$("#print").click(function() {
			$('.modal').openModal();
			var idt = $("#idtrb").val();
			console.log(idt);

			$("object").attr("data", "reporte?idtr=" + idt);

		});

		$("#fe_inicio_1").change(
				function() {
					var fei = $("#fe_inicio_1").val();

					console.log(fei);
					var fecha_inicio = parseDate(fei);
					console.log("fecha_inicio_return: " + fecha_inicio);

					$('#fe_final_1').pickadate('picker').set('select',
							calcular_final(fecha_inicio), {
								format : 'dd/mm/yyyy'
							}).trigger("change");
					Materialize.updateTextFields();

				});

		// var fecha_fin = new Date();
		function parseDate(input) {
			var map = {
				enero : 01,
				febrero : 02,
				marzo : 03,
				abril : 04,
				mayo : 05,
				junio : 06,
				julio : 07,
				agosto : 08,
				septiembre : 09,
				octubre : 10,
				noviembre : 11,
				diciembre : 12
			};
			input = input.split(" ");
			var mes0 = input[1].split("");
			console.log(mes0);
			mes0.pop();
			console.log(mes0);
			var mes1 = mes0.join("");
			console.log(mes1);

			mes = map[mes1.toLowerCase()];
			console.log("mes:" + mes);

			month = mes < 10 ? '0' + mes : mes, day = input[0] < 10 ? '0'
					+ input[0] : input[0], newDate = input[2] + '/' + month
					+ '/' + day;

			fecha_extra = day + '/' + month + '/' + input[2];
			fecha_recontraextra = day + ',' + month + ',' + input[2];
			var inicio = new Date(newDate);
			console.log("fecha_extra: " + fecha_extra);
			//console.log(inicio);

			// 		inicio.getDate() + '/' +
			//             	    (inicio.getMonth() + 1) + '/' + inicio.getFullYear();
			//          fecha_fin = calcular_final(inicio);
			//         	console.log("fecha_fin_return: "+fecha_fin);

			return inicio;
		};

		var cont = 2;
		$("#agregar")
				.click(
						function() {
							console.log(divisiones);
							if (cont <= divisiones) {
								var s = '';
								s += '<div class="col s12 m12 l6">';
								s += '<div class="card-panel">';
								s += '<h4 class="header2">Vacaciones ' + cont
										+ '</h4>';
								s += '<div class="row">'
								s += '	<form class="col s12">';
								s += '<div class="row">';
								s += '<div class="input-field col s6">';
								s += '<i class="mdi-action-perm-contact-cal prefix"></i> <input type="text" class="datepicker" onchange="setti(this.id)" id="fe_inicio_'
										+ cont
										+ '"> <label for="dob">Fecha Inicio</label>';
								s += '</div>'
								s += '<div class="input-field col s6">';
								s += '<i class="mdi-action-perm-contact-cal prefix"></i> <input type="text" class="datepicker" id="fe_final_'+cont+'" disabled><label for="dob">Fecha Fin</label>';
								s += '</div>';
								s += '</div>';
								s += '<div class="row"><div class="col s6 m8"><a class="btn-floating waves-effect waves-light  cyan darken-2 right"><i class="mdi-content-save center"></i></a>	</div>';
								s += '<div class="col s3 m2">	<a	class="btn-floating waves-effect waves-light  yellow darken-4 right"><i	class="mdi-editor-mode-edit center"></i></a></div>';
								s += '<div class="col s3 m2"> <a class="btn-floating waves-effect waves-light  red darken-4 right"><i class="mdi-action-delete center"></i></a> </div>';
								s += '</div></form> </div></div>	</div>';

								$("#space").append(s);
								cont++;
							} else {
								Materialize
										.toast(
												'Ya no puede particionar más sus vacaciones!',
												3000, 'rounded');
							}

							$('.datepicker').pickadate({
								selectMonths : true, // Creates a dropdown to control month
								selectYears : 15, // Creates a dropdown of 15 years to control year,
								today : 'Today',
								clear : 'Clear',
								close : 'Ok',
								closeOnSelect : false
							// Close upon selecting a date,
							});
						});

		function setti(id) {
			console.log(id);
			console.log("hi everyone");
			var fei = $("#" + id).val();
			console.log(fei);
			var array = id.split("_");
			var num = array[2];
			console.log(num);
			var fecha_inicio3 = parseDate(fei);

			$('#fe_final_' + num).pickadate('picker').set('select',
					calcular_final(fecha_inicio3), {
						format : 'dd/mm/yyyy'
					}).trigger("change");
			Materialize.updateTextFields();
		}

		function getArray_fechas(op) {

			var fechas = [];
			//         $('#data :checked').each(function () {

			if (op == 1) {
				for (var i = 1; i < cont; i++) {
					parseDate($("#fe_inicio_" + i).val());
					// 			console.log($("#fe_inicio_"+i).val());
					console.log(fecha_extra);
					fechas.push(fecha_extra);
				}
			}
			if (op == 2) {
				for (var i = 1; i < cont; i++) {
					parseDate($("#fe_final_" + i).val());
					console.log(fecha_extra);
					fechas.push(fecha_extra);
				}

			}

			return fechas;
		}

		function insertar() {
			var fechas_0 = getArray_fechas(1); //fecha inicio
			var fechas_1 = getArray_fechas(2); //fecha fin
			fechas_1.push("05/05/17");
			fechas_0.push("12/12/17");
			var tamaño = fechas_0.length;

			var inicio = fechas_0.join("-");
			var fin = fechas_1.join("-");
			console.log("ini: " + inicio);
			var idt = $("#idtrb").val();
			var tipo = $("#tiposolicitud").val();
			var user = $("#username").val();

			var datos = "inicio=" + inicio;
			datos += "&final=" + fin;
			datos += "&idt=" + idt;
			datos += "&tipo=" + tipo;
			datos += "&user=" + user;
			console.log("dat: " + datos);

			var con = new jsConnector();
			con.post('solicitud/insertar?' + datos, null, function(response) {
				console.log("jquery:" + response);
				if (response != null) {
					console.log("jquery:" + response);
					Materialize.toast('Vacaciones registrada correctamente!',
							3000, 'rounded');
					$("#idvacaciones").val($.trim(response));
					console.log($("#idvacaciones").val());
					$('#confirmar').addClass('disabled');
				} else {
					Materialize.toast(
							'UPS!!, No se ha registrado sus vacaciones!', 3000,
							'rounded');
				}
			});

		};
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

</body>
</html>

