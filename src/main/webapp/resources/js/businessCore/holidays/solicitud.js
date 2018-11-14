function loadProfile() {
	location.href = "<%=request.getContextPath()%>/trabajador/profile";
}

var fecha_minima;
var fecha_minima_format;
var cont = 1;
$(window)
		.load(
				function() {
					
					console.log("window cargo");
					$("#numdias").text(0);
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
											
								
											var datos = "op=1"
									

											$("#tipo").val("Programación");
											
											$("#subir").attr("disabled", true);
											$("#subir").attr("enabled", false);
											$("#print").attr("disabled", true);
											$("#print").attr("enabled", false);
											
											var card = crearCard(cont,"","",1,1);
											$("#space").append(card);
											cont++;
											console.log(cont)
											Materialize.updateTextFields();
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
											$("#fe_inicio_1").change(function() {
												var fei = $("#fe_inicio_1").val();

												console.log(fei);
												var fecha_inicio = parseDate(fei);
												console.log("fecha_inicio_return: " + fecha_inicio);
//												if(validarFechaMinima(fecha_inicio)){
//													console.log("ok");
//												}else{
//													alertify.alert('Mensaje de alerta', 'Recuerde que sus vacaciones deben ser después del ' + fecha_minima_format, function(){});
//													$("#fe_inicio_1").val("");
//												}
											//establecer la fecha final
//												$('#fe_final_1').pickadate('picker').set('select',
//														calcular_final(fecha_inicio,arraydias[0]), {
//															format : 'dd/mm/yyyy'
//														}).trigger("change");
//												Materialize.updateTextFields();
												calcularTotalDias();
											});
											
											


											break;
										case 1:
											
											var datos = "op=2"
											$("#tipo").val("Reprogramación");
											Materialize.updateTextFields();
											
											
											//listar los detalles tanto observados como aprobados
											
											$.get(gth_context_path+ '/solicitud/getdetails',
													function(response) {
														console.log(response.length);
														if(response.length >0){
															listarVacaciones(response,2);//va a listar considerando los detalles de vacaciones observados
															//getJsonFechas();
														}
														
													});
											
											
											
											break;
										case 2:

											
											$("#subir").attr("disabled", true);
											$("#subir").attr("enabled", false);
											$("#print").attr("disabled", true);
											$("#print").attr("enabled", false);
											$("#confirmar").attr("disabled",
													true);
											$("#confirmar").attr("enabled",
													false);
											$("#agregar").attr("disabled", true);
											$("#agregar").attr("enabled", false);
											// $("#confirmar").off('click');
											// $("#print").off('click');
											Materialize
													.toast(
															'Aun no ha llegado su tiempo para recibir vacaciones!',
															3000, 'rounded',
															function() {
																
															});
											
											
											

											break;
										case 3:
											Materialize
											.toast(
													'Usted tiene una solicitud en proceso!',
													3000,
													'rounded',
													function() {
													});
											

											$.get(gth_context_path+ '/solicitud/getdetails',
													function(response) {
														console.log(response.length);
														if(response.length >0){
															var tipo = response[0].TIPO.toLowerCase();
															tipo = keepFirstCapitalLetter(tipo);
															$("#tipo").val(tipo);
															
															listarVacaciones(response,2);//va a listar considerando los detalles de vacaciones observados
															//getJsonFechas();
														}
														
													});

									$("#print").attr("disabled", true);
									$("#print").attr("enabled", false);
									$("#confirmar").attr("disabled",
											true);
									$("#confirmar").attr("enabled",
											false);
									$("#agregar").attr("disabled", true);
									$("#agregar").attr("enabled", false);

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
															$("#print").attr("enabled", true);
															$("#print").attr("disabled", false);
														}
													});
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
var jsonObj = []

$(document)
		.ready(
				function() {

					$("#otrosdiv").hide();

					fecha_minima = new Date();

					fecha_minima.setDate(fecha_minima.getDate() + 45);// suma
					
					
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
						dia = fecha_minima.getDate()
						dia = (dia < 10) ? ("0" + dia) : dia;
						fecha_minima_format = dia + "/" + fecha_minima.getMonth() +1 + "/" + fecha_minima.getFullYear();
						
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
						fecha_minima = new Date(anno,mes-1,dia)
						console.log("nueva fecha minima long format " + fecha_minima);
						dia = fecha_minima.getDate()
						dia = (dia < 10) ? ("0" + dia) : dia;
						fecha_minima_format = dia + "/" + fecha_minima.getMonth() +1 + "/" + fecha_minima.getFullYear();
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
//											$
//													.get(
//															gth_context_path
//																	+ "/solicitud/mostrarpriv",
//															function(response) {
//																console
//																		.log("privilegios de vacaciones "
//																				+ response);
//																privilegios_vacaciones = response;
//																console.log(privilegios_vacaciones);
//																if (rol == "ROL-0003") {
//
//																	divisiones = 2;
//
//																} else {
//																	if (rol == "ROL-0008") {
//																		divisiones = 3;
//																	} else {
//																		console.log("sin nada");
//																		divisiones = 1;
//																		console.log(privilegios_vacaciones);
//																		if(privilegios_vacaciones == 0){
//																			console.log("privilegios sonnnnnnnnnnnnnn 0"  );
//																			$("#btn-agregar")
//																			.hide();
//																		}
//																		
//																	}
//
//																}
//															});
//											
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

var fecha_insert = "";

$("#print").click(
		function() {

			$('.modal').openModal();
			var idt = $("#idtrb").val();
			parseDate($("#fe_inicio_1").val().trim());
			var feinicio1 = fecha_insert;
			parseDate($("#fe_final_1").val().trim());
			var fefin2 = fecha_insert;

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
					+ gth_context_path + '/solicitud/generardocumento?idtr=' + idt
					+ '" style="width: 100%; height: 600px;"></object>'

			$("#show_request").html(b);

		});



// var fecha_fin = new Date();
function parseDate(input) {
	var inicio;
	console.log("parseando date " + input);
//	if(input === '' || typeof input === 'undefined'){
//		alertify.alert('Mensaje de alerta', 'Existe una fecha sin completar', function(){});
//		
//	}else{
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

			fecha_insert = day + '/' + month + '/' + input[2];
			inicio = new Date(newDate);
			console.log("fecha_insert: " + fecha_insert);
			console.log("fecha parseada " + inicio);
			return inicio;
//	}
	
	
	// console.log(inicio);

	// inicio.getDate() + '/' +
	// (inicio.getMonth() + 1) + '/' + inicio.getFullYear();
	// fecha_fin = calcular_final(inicio);
	// console.log("fecha_fin_return: "+fecha_fin);

	
};

function calcular_final(begin, sumadias) {

	console.log("fecha enviada " + begin);
	console.log("fecha enviada " + begin.getFullYear() + " , "
			+ begin.getDate() + " , " + begin.getMonth());
//if(begin.getDate()<3){
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
//}else{
//	alertify.alert('Mensaje de alerta', 'La fecha seleccionada debe ser a inicios de cada mes', function(){});
//	
//}
	

}


$("#agregar")
		.click(
				function() {
					
					
					prev_cont = cont-2;
					
					//var campos_vacios_todos = validarCampos();
					if(validarCampos()){
						var json = getJsonFechas();
						console.log(prev_cont)
						if(json[prev_cont].FEINICIO != "" && json[prev_cont].FEFINAL != ""){
							console.log("esta lleno, procede");
							//calcularTotalDias()
							var card = crearCard(cont,"","",1,1);
							$("#space").append(card);
							console.log("contando voy contando "+cont)
							cont++;
						}else{
							
							alertify.alert('Mensaje de alerta', 'Antes de crear una nueva partición, debe completar la anterior.', function(){});
						}
					}
					
					
					
//					console.log("yo soy divisiones" + divisiones +" , privilegios_vacaciones" + privilegios_vacaciones);
//					if(privilegios_vacaciones != 0){
//						divisiones = privilegios_vacaciones;
//						
//					}
//					console.log("nuevas divisiones" + divisiones);
//					if (cont <= divisiones) {

						//var card = crearCard(cont,"","");
						//$("#space").append(card);
						//daysperdivision(2)
						//cont++;
//					} else {
//						Materialize.toast(
//								'Ya no puede particionar más sus vacaciones!',
//								3000, 'rounded');
//					}

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

function crearCard(numero, fechainicio, fechafin, obs, mode){
	//si la obs es 1 es porque este detalle vacaciones ha sido observado, y tiene evaluacion 4
	//mode 1 es la creacion del card manualmente
	//mode 2 es la creacion del card, cuando se lista desde la base de datos, como para reprogramacion o solicitud en proceso
	console.log("crear card function, estado del card, hab o desh " + mode)
	var s = '';
	s += '<div class="col s12 m12 l6" id="' + numero + '">';
	s += '<div class="card-panel" style="padding-top: 10px; padding-bottom: 10px;">';

	s += '<div class="row holiday" style="    margin-bottom: 0px;">'
	s += '<div class="col s3 m6">'
	s += '<h4 class="header2"><b>Vacaciones N° ' + numero + '</b></h4>';
	s += '</div>'
//		if(numero != 1){
			s += '<div class="col s3 m6">'
				s += '<button style="margin-top: 7px;" onclick="eliminarVacaciones('
						+ numero
						+ ');" class="btn-floating waves-effect waves-light  red darken-4 right"'
						if(obs==1 && mode ==1){
							s += '><i class="mdi-content-clear center"></i></button>'
						}else{
							if(obs!=1 || mode == 0){
								s += 'disabled><i class="mdi-content-clear center"></i></button>'
							}
							
						}
						
						
				s += '</div>'
//		}

	s += '</div>'

	s += '<div class="row" style="    margin-bottom: 0px;">';
	s += '<div class="input-field col s6">';
	s += '<i class="mdi-action-perm-contact-cal prefix"></i> <input type="text" class="datepicker" value="'+fechainicio+'"'
	if (numero!=1){
		s += 'onchange="settinicio(this.id)"'
	}
	
	s += 'id="fe_inicio_'
			+ numero; 
	if(obs==1 && mode ==1){
		s += '"> <label for="dob">Fecha Inicio</label>';
	}else{
		s += '" disabled> <label for="dob">Fecha Inicio</label>';
	}
	
	s += '</div>'
	s += '<div class="input-field col s6">';
	s += '<i class="mdi-action-perm-contact-cal prefix"></i> <input type="text"  value="'+fechafin+'" onchange="settifinal(this.id)" class="datepicker" id="fe_final_'
			+ numero
			
	if(obs==1 && mode ==1){
		s += '"><label for="dob">Fecha Fin</label>';
	}else{
		s += '"disabled ><label for="dob">Fecha Fin</label>';
	}
			
	s += '</div>';
	s += '</div>';
	s += '</div>	</div>';
	return s;
}

function eliminarVacaciones(id) {

	var jsonfechas =getJsonFechas();
	del = id -1;
	jsonfechas.splice(del,1)
	var limite = cont-2;
	console.log(limite + " , " + jsonfechas.length)
	
	var previous_cont = cont-1;
	if (jsonfechas.length == limite){
		$("#"+id).remove();
		cont = 1;
	}else{
		console.log("tamano ideal del array de fechas ees >>> " + cont + " obtenido " + jsonfechas.length);
	}
	console.log(id + " , " + previous_cont + " , " + cont )
	if(id == 1 && previous_cont==1){
		//crea un card de vacaciones cuando esta comenzando a solicitud sus vacaciones, es decir, la programacion natural
		var card = crearCard(cont,"","",1,1);
		$("#space").append(card);
		cont++;
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
	}else{
		cleandata()
		listarVacaciones(jsonfechas,1)
		calcularTotalDias()
	}

	
	

	
//	if (id == 2 && cont == 4) {
//
//		$("#3").remove();
//		cont--;
//		daysperdivision(1)
//	} else {
//
//		$("#" + cont-1).remove();
//		cont--;
//		daysperdivision(1)
//	}
	


}
//calcular los dias por cada division
var conta;
function daysperdivision(addorremove){
	arraydias = []
if(addorremove == 1){
	conta=cont-1
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

function settinicio(id) {
	console.log(id);

	var fei = $("#" + id).val();
	console.log(fei);
	var array = id.split("_");
	var num = array[2];
	console.log(num);
	var fecha_ini_ma = parseDate(fei);
	
	
//	if(validarFechaMinima(fecha_ini_ma)){
		console.log("ok");
		var fechas = getJsonFechas();	
//		console.log("validando cruce >>> fecha mayor> " + fecha_ini_ma + " fecha menor >> " + fechamenor);

		if(validarjson_fechas()){
			var pos = num-2;

			
			var fechamenor = parseDate(fechas[pos].FEFINAL);
			if(fechamenor<fecha_ini_ma){
				
				console.log("correcto >> la fecha " +  fechamenor+  " es menor que  " + fecha_ini_ma )
				
			}else{
				alertify.alert('Mensaje de alerta', 'La fecha seleccionada debe ser mayor a la anterior, por favor, cámbiela', function(){});
				$("#" + id).val("");
				
			}
		}else{
			alertify.alert('Mensaje de alerta', 'Una o más fechas anteriores están vacías.', function(){});
			$("#" + id).val("");
		}
		
		calcularTotalDias();
//	}else{
//		alertify.alert('Mensaje de alerta', 'Recuerde que sus vacaciones deben ser después del ' + fecha_minima_format, function(){});
//		$("#" + id).val("");
//	}
	

	
	
}

function settifinal(id){
	console.log("validar fecha final " + id);
	var fefi = $("#" + id).val();
	

	var array = id.split("_");
	var num = array[2];
	console.log(num);
	var fecha_fin_ma = parseDate(fefi);
	
//	if(validarFechaMinima(fecha_fin_ma)){
		console.log("ok");
		var fechas = getJsonFechas();
		var pos = num-1;
		console.log(fefi);
		console.log(fechas)
		console.log(pos)
		console.log("setti final " + fechas[pos].FEINICIO);
		if(fechas[pos].FEINICIO ==""){
			alertify.alert('Mensaje de alerta', 'Una o más fechas anteriores están vacías.', function(){});
			$("#" + id).val("");
		}else{
			var fechamenor = parseDate(fechas[pos].FEINICIO);
			console.log("validando cruce >>> fecha mayor> " + fecha_fin_ma + " fecha menor >> " + fechamenor);
		
			if(validarjson_fechas()){
				if(fechamenor<=fecha_fin_ma){
					
					console.log("correcto >> la fecha " +  fechamenor+  " es menor que  " + fecha_fin_ma )
					
				}else{
					alertify.alert('Mensaje de alerta', 'La fecha seleccionada debe ser mayor a la anterior, por favor, cámbiela' , function(){});
					$("#" + id).val("");
				}
			}else{
				alertify.alert('Mensaje de alerta', 'Una o más fechas anteriores están vacías.', function(){});
				$("#" + id).val("");
			}
			
		}
		calcularTotalDias();
//	}else{
//		alertify.alert('Mensaje de alerta', 'Recuerde que sus vacaciones deben ser después del ' + fecha_minima_format, function(){});
//		$("#" + id).val("");
//	}
	
	
	
	



	
	
}

//valida que los campos anteriores a este card esten llenos
function validarjson_fechas(){
	var response = true;
	numiter = jsonObj.length - 1;
	for(var i in jsonObj) {
	    console.log(jsonObj[i].FEFINAL);  // (o el campo que necesites)
	    if(i == numiter){
	    	break;
	    }else{
		    if(jsonObj[i].FEFINAL == ""){
		    	console.log(jsonObj[i].FEFINAL);
		    	console.log("error en fefinal, pos > " + i )
		    	if (jsonObj[i].FEINICIO == ""){
		    		console.log(jsonObj[i].FEINICIO);
		    		response = false;
		    		console.log("error en feinicio, pos > " + i )
		    		break;
		    	}
		    	response = false;
		    	break;
		    }else{
		    	if (jsonObj[i].FEINICIO == ""){
		    		console.log(jsonObj[i].FEINICIO);
		    		response = false;
		    		console.log("error en feinicio, pos > " + i )
		    		break;
		    	}else{
		    		response = true;
		    	}
		    	
		    }
	    }

	}
	
	
	return response;
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
			console.log(fecha_insert);
			fechas.push(fecha_insert);
		}
	}
	if (op == 2) {
		for (var i = 1; i < cont; i++) {
			parseDate($("#fe_final_" + i).val());
			console.log(fecha_insert);
			fechas.push(fecha_insert);
		}

	}

	return fechas;
}


function intervalonegativo(){
	var back = true;
	var json_fechas = getJsonFechas();
	for(var i in json_fechas){
		if(json_fechas[i].numdias < 0){
			alertify.alert('Mensaje de alerta', 'Alguna fecha inicio es mayor a la fecha fin de sus vacaciones, por favor verifique.', function(){});
		
			back = false
			break;
			
		}else{
			back = true;
		}
		
	}
	return back;
}


function confirmarFechas(){
//	var validacion = validarCampos();
	if (validarCampos()) {
		console.log("campos llenos");
//		var json_fechas = getJsonFechas();
//		
//		var total = 0;
//		for(var i in json_fechas){
//			if(json_fechas[i].numdias < 0){
//				alertify.alert('Mensaje de alerta', 'Alguna de las fechas de inicio es mayor a la fecha fin de sus vacaciones, por favor verifique.', function(){});
//				total = 0;
//				break;
//				
//			}else{
//				total = total + json_fechas[i].numdias;
//			}
//			
//		}
		
		if(intervalonegativo()){
			var total = 0;
			for(var i in jsonObj){
				total = total + jsonObj[i].numdias;

			}
			if(total == 30){
				console.log("dias totales igual a 30");
				if(feinimayorafefin()){
					console.log("todo conforme");
					if(fefinmayorfeini()){
						console.log("vamos a INSERTAR");
						
						insertar();
					}
					
				}
			}else{
				if(total<30){
					alertify.alert('Mensaje de alerta', 'Complete sus programación de vacaciones hasta los 30 días.', function(){});
				}else{
					alertify.alert('Mensaje de alerta', 'Su programación de vacaciones a excedido los 30 días', function(){});
				}
				
			}
		}
		
		

		
	}
}
//validar que todos los campos esten llenos
function validarCampos() {
	console.log("validar");
	var cofirm = true;
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
			cofirm = false;
			break;
		}
	}

	console.log("response validarCampos() >>> " + confirm)
	return cofirm;
	
	
}



function fefinmayorfeini(){
	var json = JSON.stringify(jsonObj);
	console.log(json + " , " + jsonObj.length);
	for(var i=0 ; i<jsonObj.length; i++){
		var init = jsonObj[i].FEINICIO;
		var end = jsonObj[i].FEFINAL;
		console.log("fechas >> " + init + " >> " + end );
		if(parseDate(init) <= parseDate(end)){
			res_bol = true;
		}else{
			alertify.alert('Mensaje de alerta', 'Alguna fecha final es menor que la fecha de inicio, por favor verifique.', function(){});
			res_bol = false;
		}
	}
	return res_bol;
}

function feinimayorafefin(){
	//validando que todas las fechas inicio sean mayores a las ultimas fechas fin. aqui me quede
	var resp = true;
	for (var i = 1; i < cont-1; i++) {
		console.log("validando en todas las casillas que inicio sea mayor a final > " + i + " >> " + cont);
		fe_inicio_sig = i + 1;
		if (parseDate($("#fe_final_" + i).val()) > parseDate($("#fe_inicio_" + fe_inicio_sig).val())) {
			console.log("error, en la segunda fecha inicio");
			alertify.alert('Mensaje de alerta', 'Alguna fecha inicio es menor que la fecha final anterior, por favor verifique.', function(){});
			//alertify.alert('Mensaje de alerta', 'Antes de crear una nueva partición, debe completar la anterior.', function(){});
			resp = false;
			break;
		}
	}
	return resp;
}


function insertar() {

	var fechas_0 = getArray_fechas(1); // fecha inicio
	var fechas_1 = getArray_fechas(2); // fecha fin

	console.log("array de fechas" + fechas_0 + " , " + fechas_1);
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
			$("#subir").attr("disabled", false);
			$("#subir").attr("enabled", true);
			$("#print").attr("disabled", false);
			$("#print").attr("enabled", true);
		} else {
			Materialize.toast('UPS!!, No se ha registrado sus vacaciones!',
					3000, 'rounded');
		}
	});

};


function listarVacaciones(fechajson, mode){
	//mode 1 es el modo de listar por programacion, usando el json generado en el mismo script
	//mode 2 u otro modo es la lista de vacaciones que viene de la base de datos
	var json = JSON.stringify(fechajson);
	console.log(json + " , " + jsonObj.length);
	console.log("tamano json" + fechajson.length + " , " + fechajson[0].FEINICIO + " , " + fechajson[0].FEFINAL + "  , ");
	$("#space").html("");
	console.log("mode listar vacaciones " + mode);
//	cont = fechajson.length
	if (mode == 1){
		for(var j=0; j< fechajson.length; j++){
			var order = j+1;
			console.log("estado del card "+ fechajson[j].ESTADO);
			var card = crearCard(order,fechajson[j].FEINICIO, fechajson[j].FEFINAL,1,fechajson[j].ESTADO)
			
			$("#space").append(card);
			console.log("contando voy contando "+cont)
			cont++
			//daysperdivision(2);
			Materialize.updateTextFields();
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
		}
	}else{
		for(var j=0; j< fechajson.length; j++){
			var order = j+1;
			
			if(fechajson[j].EVALUACION == 4){
				console.log("evaluacion 4")
				
				var card = crearCard(order,"","", 1, 1)

			}else{
				var card = crearCard(order,"","", 0 , 0)
				
			}
			
			$("#space").append(card);
			console.log("contando voy contando "+cont)
			cont++
			console.log("contando voy contando "+cont)
			//daysperdivision(2);
			
			
			
			Materialize.updateTextFields();
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
			
			$('#fe_inicio_' + order).pickadate('picker').set('select',
					fechajson[j].FEINICIO, {
						format : 'dd/mm/yyyy'
					})
			console.log("colocando fecha orden final " + order)
//			estabecer la fecha final
			$('#fe_final_' + order).pickadate('picker').set('select',
					fechajson[j].FEFINAL, {
						format : 'dd/mm/yyyy'
					})
		}
	}
	

	
	
	
	
	
}


function getJsonFechas(){
	console.log("entra get json fechas " + cont);
	jsonObj = [];
	var corredor = 1;
	var numiter = cont - 1
	
	console.log("cont " + cont + "numiter "+numiter);
	for(var i=0 ; i < numiter; i++){
		console.log(corredor)
		var feinicio = $("#fe_inicio_"+corredor).val();
		var fefinal = $("#fe_final_"+corredor).val();
		item = {}
		
		item["FEINICIO"] = feinicio;
		item["FEFINAL"] = fefinal;
		if(feinicio == "" || fefinal == ""){
			item["numdias"] = 0;
		}else{
			console.log("antes de calcularintervalo dias" + feinicio);
			item["numdias"] = calcularIntervaloDias(parseDate(feinicio), parseDate(fefinal));
		}
		if($("#fe_inicio_"+corredor).is(":disabled") == true){
			item["ESTADO"]= 0;
		}else{
			item["ESTADO"]= 1;
		}
		
		
		
//		if(i == cont-1){
//			
//		}
//		if(feinicio == ""){
//			alertify.alert('Mensaje de alerta', 'Asegúrese de completar la fecha anterior por favor.', function(){});
//			$("#fe_inicio_"+i).val("");
//			$("#fe_final_"+i).val("");
//		}else if(fefinal == "") {
			
//			console.log(feinicio + " , " + fefinal);
//			item["feinicio"] = feinicio;
//			item["fefinal"] = fefinal;
//			item["numdias"] = "";
			
//		} else{
//			item["feinicio"] = feinicio;
//			item["fefinal"] = fefinal;
//			item["numdias"] = calcularIntervaloDias(parseDate(feinicio), parseDate(fefinal));
//		}	
		jsonObj.push(item)
		corredor++;
	}
	var json = JSON.stringify(jsonObj);
	console.log(json + " , " + jsonObj.length);
	
	return jsonObj
}

function calcularIntervaloDias(feinicio , fefinal){
	var numdias = fefinal - feinicio;
	console.log("fecha inicio >> " + feinicio + " fecha final " + fefinal  + "  total de dias >> " + numdias + " en dias >> " + Math.floor(numdias/(1000*60*60*24)));
	numdias = Math.floor(numdias/(1000*60*60*24)) + 1;
	return numdias;
}

function calcularTotalDias(){
	
//	var json_fechas = getJsonFechas();
	
	var total = 0;
	for(var i in jsonObj){
		total = total + jsonObj[i].numdias;

	}
	$("#numdias").text(total);
	
}

function validarFechaMinima(fecha_registro){
	console.log(fecha_minima_format + " , " + fecha_registro)
	var fecha_min = new Date(fecha_minima_format);
	console.log(fecha_min + " , " + fecha_registro)
	if(fecha_registro<fecha_min){
		return false
	}else{
		return true;
	}

}

function keepFirstCapitalLetter(s) {
	  return s.replace(/^.{1}/g, s[0].toUpperCase());
}





