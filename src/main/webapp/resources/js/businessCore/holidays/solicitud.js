function loadProfile() {
	location.href = "<%=request.getContextPath()%>/trabajador/profile";
}
var divisiones = 0;


$(window).load(function() {
	console.log("window cargo");
	var id = $("#trab").val();


	var url ="/gth/solicitud/validar";
	var data ="&id=" + id ;

	
	$.getJSON(url, data, function(res, status) {


			console.log("devuelve controller: "+res);

			switch (res) {
			case 0:
				$("#subir").attr("disabled", true);	
				$("#subir").attr("enabled", false);
				$("#print").attr("disabled", true);	
				$("#print").attr("enabled", false);
				$("#confirmar").attr("disabled", true);	
				$("#confirmar").attr("enabled", false);
//				$("#confirmar").off('click');
//				$("#print").off('click');
				Materialize.toast('Aun no ha llegado su tiempo para recibir vacaciones!', 3000, 'rounded', function(){

				});

				break;
			case 1:

//					window.location.href = gth_context_path +"/solicitud/registrar?op=1";
				var datos = "op=1"
//					$.post(gth_context_path+'/solicitud/registrar', datos, function(response) {
//						console.log(response);
//						$("#content").html(response);
//					});
					
					
				$("#tipo").val("Programación");
				Materialize.updateTextFields();
//				var con = new jsConnector();
//				con.post(gth_context_path+'/solicitud/tipo?' + datos, null, function(
//						response) {
//					console.log("respuesta" + response);
//					if (response != null) {
//						console.log("jquery:" + response);
//						Materialize.toast('Vacaciones registrada correctamente!', 3000,
//								'rounded');
//						$("#idvac").val($.trim(response));
//						console.log($("#idvac").val());
//						$('#confirmar').addClass('disabled');
//					} else {
//						Materialize.toast('UPS!!, No se ha registrado sus vacaciones!',
//								3000, 'rounded');
//					}
//				});
				break;
			case 2:

//				window.location.href = gth_context_path +"/";
				Materialize
				.toast(
						'Usted tiene una solicitud en proceso!',
						3000,
						'rounded', function(){
							var datos = "op=3"
							$.get(gth_context_path+'/solicitud/registrar', datos, function(response) {
								console.log(response);
								$("#desktop").html(response);
							});
							
						});
				$("#subir").attr("disabled", true);	
				$("#subir").attr("enabled", false);
				$("#print").attr("disabled", true);	
				$("#print").attr("enabled", false);
				$("#confirmar").attr("disabled", true);	
				$("#confirmar").attr("enabled", false);
				
				break;
			case 3:
//					window.location.href = "http://localhost:8099/gth/solicitud/registrar?op=2";
//					console.log("reprogramacion");
				var datos = "op=2"
					$("#tipo").val("Reprogramación");
//					$.get(gth_context_path+'/solicitud/registrar', datos, function(response) {
//						console.log(response);
//						
//						$("#dashboard").html(response);
//					});
				break;
			}
	});
});

$(document)
		.ready(
				function() {

					$("#otrosdiv").hide();

					$('#fecha').pickadate({
						selectMonths : true, // Creates a dropdown to control
												// month
						selectYears : 15, // Creates a dropdown of 15 years to
											// control
						format : 'dd/mm/yyyy',
					});

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
											if (rol == "ROL-0003") {

												divisiones = 2;

											} else {
												if (rol == "ROL-0008") {
													divisiones = 3;
												} else {
													console.log("sin nada");
													divisiones = 1;
													$("#btn-agregar").hide();
												}

											}

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
			var fefin2 = $("#fe_final_1").val().trim();
			
			console.log(idt +", " + feinicio1);

			$("#request").attr("data",
					gth_context_path + "/solicitud/reporte?idtr=" + idt+"&feinicio1="+feinicio1);

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

	month = mes < 10 ? '0' + mes : mes, day = input[0] < 10 ? '0' + input[0]
			: input[0], newDate = input[2] + '/' + month + '/' + day;

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
	} else {

		$("#" + id).remove();
		cont--;
	}

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

	// $('#fe_final_' + num).pickadate('picker').set('select',
	// calcular_final(fecha_inicio3), {
	// format : 'dd/mm/yyyy'
	// }).trigger("change");
	Materialize.updateTextFields();
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

	var fechas_0 = getArray_fechas(1); // fecha inicio
	var fechas_1 = getArray_fechas(2); // fecha fin

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
	con.post('/solicitud/insertar?' + datos, null, function(
			response) {
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

function aaa() {
	$("#aa").submit();
}