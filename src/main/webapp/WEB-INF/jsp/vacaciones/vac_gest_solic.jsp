<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
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
</head>
<link href="<c:url value='/resources/css/alertify/alertify.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="<c:url value='/resources/css/alertify/themes/bootstrap.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">

<link
	href="<c:url value='/resources/css/alertify/themes/default.min.css'/>"
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
			<%@include file="../../../jspf/info_puesto.jspf"%>

			<section id="content" style="margin-left: 10%; margin-right: 10%;">

				<!--start container-->
				<div class="container">

					<!-- 					<div id="icon-prefixes" class="section"> -->
					<h4 class="header">Datos Generales</h4>
					<div class="row">
						<div class="col s12 m12 l12">
							<div class="row">
								<form class="col s12">
									<div class="row">
										<div class="input-field col s12 l4">
											<i class="mdi-action-account-circle prefix"></i> <input
												id="nombres" type="text" class="validate" disabled>
											<label for="icon_prefix3">Nombres y Apellidos</label>
										</div>
										<div class="input-field col s12 l4">
											<i class="mdi-action-perm-identity prefix"></i> <input
												id="dni" type="tel" class="validate" disabled> <label
												for="icon_telephone">DNI</label>
										</div>
										<div class="input-field col s12 l4">
											<i class="mdi-content-content-paste prefix"></i> <input
												id="tipo" type="tel" class="validate" value="${tipo}"
												disabled> <label for="icon-request">Tipo de
												Solicitud</label>
										</div>
										<div class="input-field col s12 l12">
											<input id="idtrb" type="hidden" class="validate"> <input
												id="idrol" type="hidden" class="validate"> <input
												id="user" class="hide">
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>

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

					</div>
					<br>
					<div class="row" id="space">
						<div class="col s12 m12 l6">
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
										

											<div class="col s3 m12">
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

						<div class="col s7 m8 l10">
							<a class="waves-effect waves-light btn right" id="print"><i
								class="mdi-action-print right"></i>Imprimir</a>
							<!-- 								<a -->
							<!-- 								class="waves-effect waves-light btn modal-trigger" -->
							<!-- 								href="#modal1">Modal</a> -->
						</div>
						<div class="col s5 m4 l2">
							<a class="btn waves-effect waves-light  cyan darken-2 right"
								onclick="validarCampos()" id="confirmar"> Confirmar<i
								class="mdi-navigation-check right"></i></a>

						</div>


					</div>

					<form method="post"
						action="archivos?${_csrf.parameterName}=${_csrf.token}"
						enctype="multipart/form-data" class="col s12 m8 l11"
						id="documentoForm">

						<div id="file-upload" class="section center">

							<div class="row section"
								style="margin-left: 20%; margin-right: 20%">

								<div class="col s12 m12 l12 center">
									<p class="center">Maximum file upload size 2MB.</p>
									<input type="file" name="file" id="file-input" class="dropify"
										data-max-file-size="10M" /> <input type="text" id="idvac"
										name="idvac" value="VAC-000004" class="hide" />
								</div>


							</div>
						</div>
						<div class="col s6 center" style="margin-right: 2em;">
							<button type="submit"
								class="btn waves-effect waves-light indigo center" id="subir">
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

			</section>
		</div>


		<div id="modal1" class="modal" style="width: 850px; height: 2000px;">
			<div class="modal-content">

				<!-- 				D:\\RRHH\\GTH\\gth01\\src\\main\\resources\\jasperreports\\request_report.jasper -->
				<object data="" type="application/pdf" width="800" height="600">

				</object>
			</div>
		</div>

	</div>


	<script
		src="<c:url value='/resources/js/plugins/dropify/js/dropify.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/plugins/alertify/alertify.min.js'></c:url>"
		type="text/javascript"></script>



	<script type="text/javascript">
	function loadProfile(){
		location.href="<%=request.getContextPath()%>/trabajador/profile";
		}

		var divisiones = 0;
		$(document)
				.ready(

						function() {
							// 							$('#confirmar').removeClass("waves-effect waves-light").addClass('disabled');

							var csrfHeader = $("meta[name='_csrf_header']")
									.attr("content");
							var csrfToken = $("meta[name='_csrf']").attr(
									"content");
							console.log(csrfToken + " / " + csrfHeader);

							// 							$("#hide").val(csrfToken);
							// 							$("#hide").attr("name",csrfHeader);

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
														console.log($("#idrol")
																.val());
														$("#user").val(u);

														Materialize
																.updateTextFields();
													} else {
														console
																.error("No se esta cargando la información");
													}

													var rol = $("#idrol").val();
													console.log(rol);
													if (rol == "ROL-0003") {

														divisiones = 2;

													} else {
														if (rol == "ROL-0008") {
															divisiones = 3;
														} else {
															console
																	.log("sin nada");
															divisiones = 1;
															$("#btn-agregar")
																	.hide();
														}

													}

												});
							} catch (e) {
								console.error("error al listar info : " + e);
							}

							$('.dropify').dropify();

							// Translated
							//             $('.dropify-fr').dropify({
							//                 messages: {
							//                     default: 'Glissez-déposez un fichier ici ou cliquez',
							//                     replace: 'Glissez-déposez un fichier ou cliquez pour remplacer',
							//                     remove:  'Supprimer',
							//                     error:   'Désolé, le fichier trop volumineux'
							//                 }
							//             });

							// Used events
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
			var inicio = new Date(newDate);
			console.log("fecha_extra: " + fecha_extra);
			//console.log(inicio);

			// 		inicio.getDate() + '/' +
			//             	    (inicio.getMonth() + 1) + '/' + inicio.getFullYear();
			//          fecha_fin = calcular_final(inicio);
			//         	console.log("fecha_fin_return: "+fecha_fin);

			return inicio;
		};

		function calcular_final(begin) {

			console.log("fecha enviada " + begin);
			console.log("fecha enviada " + begin.getFullYear);
			var calculado = new Date();

			begin.setDate(begin.getDate() + 29);

			var anno = begin.getFullYear();
			var mes = begin.getMonth() + 1;
			var dia = begin.getDate();
			mes = (mes < 10) ? ("0" + mes) : mes;
			dia = (dia < 10) ? ("0" + dia) : dia;
			var fechaFinal = dia + "/" + mes + "/" + anno;

			console.log("fecha calculada: " + fechaFinal);

			return fechaFinal;
		}

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
								s += '<div class="row">';
								s += '<div class="col s3 m12"> <a class="btn-floating waves-effect waves-light  red darken-4 right"><i class="mdi-action-delete center"></i></a> </div>';
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

		var cofirm = '';
		function validarCampos() {
			console.log("validar");

			for (var i = 1; i < cont; i++) {

				if ($("#fe_inicio_" + i).val() == "") {
					console.log("null");
					alertify.confirm('Validar Solicitud',
							'Verifique que todos los campos estén rellenados',
							function() {
								alertify.success('Ok')
							}, function() {
								alertify.error('Cancel')
							});

					cofirm += '1';
				}
			}

			if (cofirm == "") {
				console.log("campos llenos");
				insertar();
			}
		}

		function insertar() {

			var fechas_0 = getArray_fechas(1); //fecha inicio
			var fechas_1 = getArray_fechas(2); //fecha fin

			var tamaño = fechas_0.length;

			var inicio = fechas_0.join("-");
			var fin = fechas_1.join("-");
			console.log("ini: " + inicio);

			var idt = $("#idtrb").val();
			var tipo = $("#tipo").val();
			var user = $("#user").val();

			var datos = "inicio=" + inicio;
			datos += "&final=" + fin;
			datos += "&idt=" + idt;
			datos += "&tipo=" + tipo;
			datos += "&user=" + user;
			console.log("dat: " + datos);

			var con = new jsConnector();
			con.post('solicitud/insertar?' + datos, null, function(response) {
				if (response == 1) {
					Materialize.toast('Vacaciones registrada correctamente!',
							3000, 'rounded');
					$('#confirmar').addClass('disabled');
				} else {
					Materialize.toast(
							'UPS!!, No se ha registrado sus vacaciones!', 3000,
							'rounded');
				}
			});

		};

		$("#subir")
				.click(
						function(event) {
							// 			event.preventDefault();

							var file = $("#file-input").val();

							var form = $('#documentoForm')[0];

							// Create an FormData object
							var data = new FormData(form);
							console.log(csrfHeader + "  " + csrfToken);
							console.log(file + "  " + form + "  " + data);

							alertify
									.confirm(
											'Confirmar vacaciones',
											'Esta seguro(a) de subir este archivo?',
											function() {
												if (file != "") {
													$
															.ajax({
																type : "POST",
																enctype : 'multipart/form-data',
																url : "archivos",
																data : data,
																processData : false,
																contentType : false,
																cache : false,
																timeout : 600000,
																beforeSend : function(
																		xhr) {
																	xhr
																			.setRequestHeader(
																					csrfHeader,
																					csrfToken);
																},
																success : function(
																		data) {
																	console
																			.log(data);

																	// 																	$("#subir")
																	// 																			.prop(
																	// 																					"disabled",
																	// 																					false);
																},
																error : function(
																		e) {
																	alert(
																			"NADA JONÁS : ",
																			e);
																	$("#subir")
																			.prop(
																					"disabled",
																					false);
																}
															});
												} else {
													alertify
															.error("Suba un documento<br/>");
												}

											},
											function() {
												alertify
														.errorAlert("Error al intentar guardar los datos<br/>");
											});
						});

		function aaa() {
			$("#aa").submit();
		}
	</script>
</body>
</html>