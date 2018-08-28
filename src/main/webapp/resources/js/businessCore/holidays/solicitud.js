function loadProfile() {
	location.href = "<%=request.getContextPath()%>/trabajador/profile";
}

var fecha_minima;
var fecha_minima_format;
$(window)
		.load(
				function() {
					console.log("window cargo");
					var id = $("#trab").val();

					var url = "/gth/solicitud/validar";
					var data = "&id=" + id;
					var solicitud = 0;

					$
							.getJSON(
									url,
									data,
									function(res, status) {

										console.log("devuelve controller: "
												+ res);

										switch (res) {
										case 0:
											$("#subir").attr("disabled", true);
											$("#subir").attr("enabled", false);
											$("#print").attr("disabled", true);
											$("#print").attr("enabled", false);
											$("#confirmar").attr("disabled",
													true);
											$("#confirmar").attr("enabled",
													false);
											// $("#confirmar").off('click');
											// $("#print").off('click');
											Materialize
													.toast(
															'Aun no ha llegado su tiempo para recibir vacaciones!',
															3000, 'rounded',
															function() {

															});

											break;
										case 1:

											// window.location.href =
											// gth_context_path
											// +"/solicitud/registrar?op=1";
											var datos = "op=1"
											// $.post(gth_context_path+'/solicitud/registrar',
											// datos, function(response) {
											// console.log(response);
											// $("#content").html(response);
											// });

											$("#tipo").val("Programación");
											Materialize.updateTextFields();
											// var con = new jsConnector();
											// con.post(gth_context_path+'/solicitud/tipo?'
											// + datos, null, function(
											// response) {
											// console.log("respuesta" +
											// response);
											// if (response != null) {
											// console.log("jquery:" +
											// response);
											// Materialize.toast('Vacaciones
											// registrada correctamente!', 3000,
											// 'rounded');
											// $("#idvac").val($.trim(response));
											// console.log($("#idvac").val());
											// $('#confirmar').addClass('disabled');
											// } else {
											// Materialize.toast('UPS!!, No se
											// ha registrado sus vacaciones!',
											// 3000, 'rounded');
											// }
											// });
											break;
										case 2:

											// window.location.href =
											// gth_context_path +"/";
											Materialize
													.toast(
															'Usted tiene una solicitud en proceso!',
															3000,
															'rounded',
															function() {
																var datos = "op=3"
																$
																		.get(
																				gth_context_path
																						+ '/solicitud/registrar',
																				datos,
																				function(
																						response) {
																					console
																							.log(response);
																					$(
																							"#desktop")
																							.html(
																									response);
																				});

															});

											$("#print").attr("disabled", true);
											$("#print").attr("enabled", false);
											$("#confirmar").attr("disabled",
													true);
											$("#confirmar").attr("enabled",
													false);

											$
													.getJSON(
															gth_context_path
																	+ "/solicitud/existenciasolicitud",
															data,
															function(response) {
																console
																		.log("existencia solicitud "
																				+ response
																				+ " :: "
																				+ response.response);
																$("#idvac")
																		.val(
																				response.idvac);
																if (response.response == 1) {
																	console
																			.log("deshabilitando el boton print");
																	$("#subir")
																			.attr(
																					"disabled",
																					true);
																	$("#subir")
																			.attr(
																					"enabled",
																					false);
																} else {
																	Materialize
																			.toast(
																					'Por favor suba su solicitud firmada.',
																					3000,
																					'rounded',
																					function() {

																					});
																}
															});

											break;
										case 3:
											// window.location.href =
											// "http://localhost:8099/gth/solicitud/registrar?op=2";
											// console.log("reprogramacion");
											var datos = "op=2"
											$("#tipo").val("Reprogramación");
											Materialize.updateTextFields();
											// $.get(gth_context_path+'/solicitud/registrar',
											// datos, function(response) {
											// console.log(response);
											//						
											// $("#dashboard").html(response);
											// });
											break;
										case 4:
											$("#subir").attr("disabled", true);
											$("#subir").attr("enabled", false);
											$("#print").attr("disabled", true);
											$("#print").attr("enabled", false);
											$("#confirmar").attr("disabled",
													true);
											$("#confirmar").attr("enabled",
													false);

											$("#fe_inicio_1").attr("disabled",
													true);
											$("#fe_inicio_1").attr("enabled",
													false);
											$("#agregar")
													.attr("disabled", true);
											$("#agregar")
													.attr("enabled", false);

											// $("#confirmar").off('click');
											// $("#print").off('click');
											Materialize
													.toast(
															'Disculpe, aún el analista debe realizar algunas configuraciones. Gracias por su paciencia.',
															3000, 'rounded',
															function() {

															});

											break;
										}
									});
				});
var divisiones = 0;
var privilegios_vacaciones;
var nro_dias = 0;
var arraydias = []
$(document)
		.ready(
				function() {

					$("#otrosdiv").hide();

					fecha_minima = new Date();

					fecha_minima.setDate(fecha_minima.getDate() + 45);// suma
					// 46
					// dias
					
					
					console.log(fecha_minima + " , "
							+ fecha_minima.getFullYear() + " , "
							+ fecha_minima.getDate() + " , "
							+ fecha_minima.getMonth())
					var anno = fecha_minima.getFullYear();
					var mes = fecha_minima.getMonth() + 1;
					var dia = fecha_minima.getDate();

					console.log(anno + " , " + mes + " , " + dia)
					
					
					if(dia >2){
						console.log("el dia es mayor a 2")
						mes ++;
						dia=1
						mes = (mes < 10) ? ("0" + mes) : mes;
						dia = (dia < 10) ? ("0" + dia) : dia;
						fecha_minima_format = dia + "/" + mes + "/" + anno;
						fecha_minima = new Date(anno,mes-1,dia)
						console.log("nueva fecha minima long format " + fecha_minima);
						console.log("fecha minima: " + fecha_minima_format);
						var message = ""

						message += '	<p>'
						message = '	<i class="mdi-action-info-outline"></i> <b>INFO : </b>  Recuerda que la fecha mínima'
						message += ' para tus vacaciones es el <b style="font-size: 19px;">'
								+ fecha_minima_format + '</b>'
						message += '</p>'

						$("#message_date").html(message);
					}else{
						mes = (mes < 10) ? ("0" + mes) : mes;
						dia = (dia < 10) ? ("0" + dia) : dia;
						fecha_minima_format = dia + "/" + mes + "/" + anno;
						console.log("fecha minima: " + fecha_minima_format);
						var message = ""

						message += '	<p>'
						message = '	<i class="mdi-action-info-outline"></i> <b>INFO : </b>  Recuerda que la fecha mínima'
						message += ' para tus vacaciones es el <b style="font-size: 19px;">'
								+ fecha_minima_format + '</b>'
						message += '</p>'

						$("#message_date").html(message);
					}
					
					

					// $('#confirmar').removeClass("waves-effect
					// waves-light").addClass('disabled');

					var csrfHeader = $("meta[name='_csrf_header']").attr(
							"content");
					var csrfToken = $("meta[name='_csrf']").attr("content");
					console.log(csrfToken + " / " + csrfHeader);

					// $("#hide").val(csrfToken);
					// $("#hide").attr("name",csrfHeader);

					try {
						$
								.getJSON(
										gth_context_path + '/components',
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
												console.log($("#idrol").val());
												$("#user").val(u);

												Materialize.updateTextFields();
											} else {
												console
														.error("No se esta cargando la información");
											}

											var rol = $("#idrol").val();
											console.log(rol);
											var privilegios;
											$
													.get(
															gth_context_path
																	+ "/solicitud/mostrarpriv",
															function(response) {
																console
																		.log("privilegios de vacaciones "
																				+ response);
																privilegios_vacaciones = response;
																console.log(privilegios_vacaciones);
																if (rol == "ROL-0003") {

																	divisiones = 2;

																} else {
																	if (rol == "ROL-0008") {
																		divisiones = 3;
																	} else {
																		console.log("sin nada");
																		divisiones = 1;
																		console.log(privilegios_vacaciones);
																		if(privilegios_vacaciones == 0){
																			console.log("privilegios sonnnnnnnnnnnnnn 0"  );
																			$("#btn-agregar")
																			.hide();
																		}
																		
																	}

																}
															});
//											if (privilegios == 0) {
												
//											} else {
//												divisiones = privilegios;
//											}

										});
					} catch (e) {
						console.error("error al listar info : " + e);
					}

					$('.dropify').dropify();

					// Translated
					// $('.dropify-fr').dropify({
					// messages: {
					// default: 'Glissez-déposez un fichier ici ou cliquez',
					// replace: 'Glissez-déposez un fichier ou cliquez pour
					// remplacer',
					// remove: 'Supprimer',
					// error: 'Désolé, le fichier trop volumineux'
					// }
					// });

					// Used events
					var drEvent = $('.dropify-event').dropify();

					drEvent.on('dropify.beforeClear', function(event, element) {
						return confirm("Do you really want to delete \""
								+ element.filename + "\" ?");
					});

					drEvent.on('dropify.afterClear', function(event, element) {
						alert('File deleted');
					});

				});

var fecha_extra = "";

$("#print").click(
		function() {

			$('.modal').openModal();
			var idt = $("#idtrb").val();
			parseDate($("#fe_inicio_1").val().trim());
			var feinicio1 = fecha_extra;
			parseDate($("#fe_final_1").val().trim());
			var fefin2 = fecha_extra;

			console.log(idt + ", " + feinicio1 + ", " + fefin2);
			var b = "";
			// b = "<embed src='"
			// + gth_context_path
			// + '/solicitud/reporte?idtr='+ idt
			// // + "&feinicio1="
			// // + feinicio1
			// // + "&fefin1="
			// // + fefin2
			// // +
			// "' style='width: 100%; height: 600px; ' type='application/pdf'>"
			// $("#request").attr("data",
			// gth_context_path + "/solicitud/reporte?idtr=" +
			// idt+"&feinicio1="+feinicio1);

			// b = "<embed src='"
			// + gth_context_path
			// + '/solicitud/reporte?idtr='+ idt
			// // + "&feinicio1="
			// // + feinicio1
			// // + "&fefin1="
			// // + fefin2
			// // +
			// "' style='width: 100%; height: 600px; ' type='application/pdf'>"

			b = '<object id="request" type="application/pdf" data="'
					+ gth_context_path + '/solicitud/reporte?idtr=' + idt
					+ '" style="width: 100%; height: 600px;"></object>'

			$("#show_request").html(b);

		});

$("#fe_inicio_1").change(
		function() {
			var fei = $("#fe_inicio_1").val();

			console.log(fei);
			var fecha_inicio = parseDate(fei);
			console.log("fecha_inicio_return: " + fecha_inicio);

			$('#fe_final_1').pickadate('picker').set('select',
					calcular_final(fecha_inicio,arraydias[0]), {
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

	month = mes < 10 ? '0' + mes : mes;
	day = input[0] < 10 ? '0' + input[0] : input[0];
	newDate = input[2] + '/' + month + '/' + day;

	fecha_extra = day + '/' + month + '/' + input[2];
	var inicio = new Date(newDate);
	console.log("fecha_extra: " + fecha_extra);
	// console.log(inicio);

	// inicio.getDate() + '/' +
	// (inicio.getMonth() + 1) + '/' + inicio.getFullYear();
	// fecha_fin = calcular_final(inicio);
	// console.log("fecha_fin_return: "+fecha_fin);

	return inicio;
};

function calcular_final(begin, sumadias) {

	console.log("fecha enviada " + begin);
	console.log("fecha enviada " + begin.getFullYear() + " , "
			+ begin.getDate() + " , " + begin.getMonth());
if(begin.getDate()<3){
	if (begin >= fecha_minima) {
		console.log("correcto"  + sumadias);
		sumadias = sumadias-1
		begin.setDate(begin.getDate() + sumadias);// suma 30 dias
		console.log(begin + " , " + begin.getMonth());
		var anno = begin.getFullYear();
		var mes = begin.getMonth() + 1;
		var dia = begin.getDate();

		console.log(anno + " , " + mes + " , " + dia)
		mes = (mes < 10) ? ("0" + mes) : mes;
		dia = (dia < 10) ? ("0" + dia) : dia;
		var fechaFinal = dia + "/" + mes + "/" + anno;

		console.log("fecha calculada: " + fechaFinal);

		return fechaFinal;
	} else {
		console.log("fecha incorrecta");

		Materialize.toast('Por favor elija una fecha a partir de '
				+ fecha_minima_format, 3000, 'rounded');

	}
}else{
	alertify.alert('Mensaje de alerta', 'La fecha seleccionada debe ser a inicios de cada mes', function(){});
	
}
	

}

var cont = 2;
$("#agregar")
		.click(
				function() {
					
					console.log("yo soy divisiones" + divisiones +" , privilegios_vacaciones" + privilegios_vacaciones);
					if(privilegios_vacaciones != 0){
						divisiones = privilegios_vacaciones;
						
					}
					console.log("nuevas divisiones" + divisiones);
					if (cont <= divisiones) {
						var s = '';
						s += '<div class="col s12 m12 l6" id="' + cont + '">';
						s += '<div class="card-panel">';

						s += '<div class="row" style="    margin-bottom: 0px;">'
						s += '<div class="col s3 m6">'
						s += '<h4 class="header2">Vacaciones ' + cont + '</h4>';
						s += '</div>'
						s += '<div class="col s3 m6">'
						s += '<button onclick="eliminarVacaciones('
								+ cont
								+ ');" class="btn-floating waves-effect waves-light  red darken-4 right"><i class="mdi-content-clear center"></i></button>'
						s += '</div>'
						s += '</div>'

						s += '<div class="row" style="    margin-bottom: 0px;">';
						s += '<div class="input-field col s6">';
						s += '<i class="mdi-action-perm-contact-cal prefix"></i> <input type="text" class="datepicker" onchange="setti(this.id)" id="fe_inicio_'
								+ cont
								+ '"> <label for="dob">Fecha Inicio</label>';
						s += '</div>'
						s += '<div class="input-field col s6">';
						s += '<i class="mdi-action-perm-contact-cal prefix"></i> <input type="text" class="datepicker" id="fe_final_'
								+ cont
								+ '" disabled><label for="dob">Fecha Fin</label>';
						s += '</div>';
						s += '</div>';
						s += '</div>	</div>';

						$("#space").append(s);
						daysperdivision(2)
						cont++;
					} else {
						Materialize.toast(
								'Ya no puede particionar más sus vacaciones!',
								3000, 'rounded');
					}

					$('.datepicker').pickadate({
						selectMonths : true, // Creates a dropdown to control
						// month
						selectYears : 5, // Creates a dropdown of 15 years to
						// control year,
						today : 'Today',
						clear : 'Clear',
						close : 'Ok',
						closeOnSelect : false
					// Close upon selecting a date,
					});
				});

function eliminarVacaciones(id) {
	
	if (id == 2 && cont == 4) {

		$("#3").remove();
		cont--
		daysperdivision(1)
	} else {

		$("#" + id).remove();
		cont--;
		daysperdivision(1)
	}
	
	cleandata()

}
//calcular los dias por cada division

function daysperdivision(addorremove){
	arraydias = []
if(addorremove == 1){
	var conta=cont-1
	console.log("nro divisiones actuales " + conta)
	if(conta>3){
		arraydias = [7,7,7,9]
	}else{
		nro_dias=30/conta
		for(var i = 0; i<conta ; i++){
			arraydias[i] = nro_dias;
		}
	}
	
	console.log("array dias " + arraydias)
}else{
	console.log("nro divisiones actuales " + cont)
	if(cont>3){
		arraydias = [7,7,7,9]
	}else{
		nro_dias=30/cont
		for(var i = 0; i<cont ; i++){
			arraydias[i] = nro_dias;
		}
	}
	
	console.log("array dias " + arraydias)
}
	
	

}


function cleandata(){
	for (var i = 1; i < cont; i++) {
		$("#fe_inicio_" + i).val("");

	}
	for (var i = 1; i < cont; i++) {
		$("#fe_final_" + i).val("");

	}
	Materialize.updateTextFields();
}

function setti(id) {
	console.log(id);
	console.log("hi everyone");
	var fei = $("#" + id).val();
	console.log(fei);
	var array = id.split("_");
	var num = array[2];
	console.log(num);
	var fecha_inicio3 = parseDate(fei);
console.log("probando si array dias llega " + arraydias + arraydias[0] + num)

	if(validarCruceFechas(id) == true){

	$('#fe_final_' + num).pickadate('picker').set('select',
			calcular_final(fecha_inicio3,arraydias[num-1]), {
				format : 'dd/mm/yyyy'
			}).trigger("change");
	
	Materialize.updateTextFields();
	}
}


function validarCruceFechas(idbase){
	var fechaprobar = $("#" + idbase).val();
	var separar = idbase.split("_");
	console.log(separar);
	console.log(parseInt(separar[2])-1);
	var numero = parseInt(separar[2])-1;
	var fechabase = $("#fe_final_" + numero).val();
	console.log(fechabase);
	var fechaparse = parseDate(fechabase);
	var fechaparse1 = parseDate(fechaprobar);
	
	if(fechaparse<fechaparse1){
		console.log(fechaparse + " dddd "+ fechaparse1);
		console.log("correcto")
		return true;
	}else{
		alertify.alert('Mensaje de alerta', 'La fecha seleccionada debe ser mayor a las anteriores, por favor, cámbiela', function(){});
		return false;
	}
}


function getArray_fechas(op) {

	var fechas = [];
	// $('#data :checked').each(function () {

	if (op == 1) {
		for (var i = 1; i < cont; i++) {
			parseDate($("#fe_inicio_" + i).val());
			// console.log($("#fe_inicio_"+i).val());
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

function validarCampos() {
	console.log("validar");
	var cofirm = '';
	for (var i = 1; i < cont; i++) {

		if ($("#fe_inicio_" + i).val() == "" || $("#fe_final_" + i).val() == "") {
			console.log("null");
			alertify.confirm('Validar Solicitud',
					'Verifique que todos los campos estén rellenados',
					function() {
						alertify.success('Ok')
					}, function() {
						alertify.error('Cancel')
					});
			cofirm += '1';
			break;
		}
	}
	
	for (var i = 1; i < cont-1; i++) {

		if ($("#fe_final_" + i).val() > $("#fe_inicio_" + i+1).val()) {
			console.log("error, en la segunda fecha inicio");
			cofirm += '1';
			break;
		}

	}
	
	if (cofirm == "") {
		console.log("campos llenos");
		insertar();
	}
	
}

function insertar() {

	var fechas_0 = getArray_fechas(1); // fecha inicio
	var fechas_1 = getArray_fechas(2); // fecha fin

	console.log(fechas_0 + " , " + fechas_1);
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
	con.post('/solicitud/insertar?' + datos, null, function(response) {
		if (response != null) {
			console.log("jquery:" + response);
			Materialize.toast('Vacaciones registrada correctamente!', 3000,
					'rounded');
			$("#idvac").val($.trim(response));
			console.log($("#idvac").val());
			$('#confirmar').addClass('disabled');
		} else {
			Materialize.toast('UPS!!, No se ha registrado sus vacaciones!',
					3000, 'rounded');
		}
	});

};

$("#subir")
		.click(
				function(event) {
					// event.preventDefault();
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
														url : gth_context_path
																+ "/solicitud/archivos",
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
														success : function(data) {
															console.log(data);

														},
														error : function(e) {
															alert(
																	"NADA pepe : ",
																	e);
															$("#subir").prop(
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
