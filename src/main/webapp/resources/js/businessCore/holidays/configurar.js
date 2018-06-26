$(document).ready(
		function() {
			listar_departamento();
			var url = "/gth/configuraciones/validar";
			// var data ="&id=" + id ;

			$.getJSON(url, null, function(res, status) {

				console.log("devuelve controller: " + res);

				switch (res) {
				case 0:

					Materialize.toast(
							'Debe configurar las proximas vacaciones!', 3000,
							'rounded', function() {
								// $.get(gth_context_path+'/configuraciones/',
								// null, function(response) {
								// console.log(response);
								// $("#desktop").html(response);
								// $("#programa_vacaciones
								// input").attr('disabled','disabled');
								// $("#solicitud_vacaciones
								// input").attr('disabled','disabled');
								// $("#modificar
								// input").attr('disabled','disabled');
								// });
							});
					$("#date_cons").attr("disabled", false);
					$("#date_cons").attr("enabled", true);
					$("#consolidado").attr("disabled", false);
					$("#consolidado").attr("enabled", true);
					$("#date_solicitud").attr("disabled", false);
					$("#date_solicitud").attr("enabled", true);
					$("#date_programa").attr("disabled", false);
					$("#date_programa").attr("enabled", true);
					$("#saveRequest").attr("disabled", false);
					$("#saveRequest").attr("enabled", true);
					$("#date_edit").attr("disabled", true);
					$("#date_edit").attr("enabled", false);
					$("#jefe").attr("disabled", true);
					$("#jefe").attr("enabled", false);

					break;
				case 1:

					Materialize.toast('Ya configuró las vacaciones!', 3000,
							'rounded', function() {
								// $.get(gth_context_path+'/configuraciones/',
								// null, function(response) {
								// console.log(response);
								// $("#desktop").html(response);
								// });
							});

					$("#date_cons").attr("disabled", true);
					$("#date_cons").attr("enabled", false);
					$("#consolidado").attr("disabled", true);
					$("#consolidado").attr("enabled", false);
					$("#date_solicitud").attr("disabled", true);
					$("#date_solicitud").attr("enabled", false);
					$("#date_programa").attr("disabled", true);
					$("#date_programa").attr("enabled", false);
					$("#saveRequest").attr("disabled", true);
					$("#saveRequest").attr("enabled", false);
					$("#date_edit").attr("disabled", true);
					$("#date_edit").attr("enabled", false);
					$("#jefe").attr("disabled", true);
					$("#jefe").attr("enabled", false);

					break;

				}
			});

		});

var lista_departamento = null;
var eleccion=0;
var key_to_change=0;
var dni="";
var nro_vacaciones=0;


function activar(valor) {

	console.log(key_to_change);
	console.log(valor);
	if(key_to_change==0){
		eleccion=valor;
		$("input:checkbox:not('#" + valor + "')").each(function() {
			console.log("entro a cambiar");
			$(this).prop('checked', false);
		})
	}else{
		
		alertify.alert('Mensaje de alerta', 'Usted ya no puede variar esto al menos que cancele los cambios o los guarde', function(){ 
			alertify.success('Ok');
			if(!$("#"+valor).is(":checked")){
// $("input:checkbox:not('#check" + valor+"')").prop('checked', false);
				$("#"+valor).prop('checked', true);
				console.log("no esta chequeado");
			}else{
				$("#"+valor).prop('checked', false);
				
				console.log("esta chequeado");
			};
			
			
		});
	}
	

};

$("#consolidado")
		.click(
				function() {
					var alerta = $("#date_cons").val();

					alerta = parseDate(alerta);
					var cn = new jsConnector();

					cn
							.post(
									'configuraciones/insertarConsolidado?fecha='
											+ alerta,
									null,
									function(response) {
										console.log(response)
										if (response == 1) {
											console.log("jquery:" + response);
											Materialize
													.toast(
															'Consolidado creado correctamente, programe el plazo para el envio del programa!',
															3000, 'rounded');

											$("#programa_vacaciones input")
													.attr('disabled', false);
											$("#date_cons").attr('disabled',
													true);

											// $("#idvac").val($.trim(response));
											// console.log($("#idvac").val());
											// $('#confirmar').addClass('disabled');
										} else {
											Materialize
													.toast(
															'UPS!!, no se ha creado el consolidado!',
															3000,
															'rounded',
															function() {
																$("#date_cons")
																		.val("");
															});
										}
									});

				});

$("#savePrograma")
		.click(
				function() {
					var alerta = $("#date_programa").val();

					alerta = parseDate(alerta);
					var cn = new jsConnector();

					cn
							.post(
									'configuraciones/insertarPrograma?fecha='
											+ alerta,
									null,
									function(response) {
										console.log(response)
										if (response == 1) {
											console.log("jquery:" + response);
											Materialize
													.toast(
															'Plazo de entrega del programa de vacaciones configurado correctamente, prosiga',
															3000, 'rounded');

											$("#solicitud_vacaciones input")
													.attr('disabled', false);
											$("#programa_vacaciones input")
													.attr('disabled', true);

										} else {
											Materialize
													.toast(
															'UPS!!, no se ha configurado el programa de vacaciones!',
															3000,
															'rounded',
															function() {
																$(
																		"#date_programa")
																		.val("");
															});
										}
									});

				});

$("#saveRequest")
		.click(
				function() {

					var solicitud = $("#date_solicitud").val();
					var programa = $("#date_programa").val();

					if (solicitud == "" || programa == "") {
						alertify.alert('Error', 'Hay campos vacíos',
								function() {
									alertify.success('Ok');
								});
					} else {
						console.log("campos llenos");
						solicitud = parseDate(solicitud);
						programa = parseDate(programa);
						var cn = new jsConnector();
						var data = "?fecha_solicitud=" + solicitud;
						data += "&fecha_programa=" + programa;
						cn
								.post(
										'configuraciones/insertarSolicitudPrograma'
												+ data,
										function(response) {
											console.log(response)
											if (response == 1) {
												console.log("jquery:"
														+ response);
												Materialize
														.toast(
																'Plazo de entrega de la solicitud y el programa de vacaciones se configuró correctamente',
																3000, 'rounded');

												$("#solicitud_vacaciones input")
														.attr('disabled', true);

												// $("#idvac").val($.trim(response));
												// console.log($("#idvac").val());
												// $('#confirmar').addClass('disabled');
											} else {
												Materialize
														.toast(
																'UPS!!, no se ha creado el consolidado!',
																3000,
																'rounded',
																function() {
																	$(
																			"#date_solicitud")
																			.val(
																					"");
																});
											}
										});
					}

				});

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

	return fecha_extra;
};

function listar_departamento() {

	$.get(gth_context_path + "/configuraciones/departamento", null, function(
			response) {
		console.log(response)
		lista_departamento = response;
		for ( var i in lista_departamento) {

			console.log(lista_departamento[i].NO_DEP.trim());
		}

	});

}

$('#autocomplete')
		.keyup(
				function() {
					
					var c = '<ul class="autocomplete-content dropdown-cont" id="lista"></ul>'
						$("#hey").append(c);
					$("#lista").html("");
					console.log(1);
					var search_term = $('#autocomplete').val().trim()
							.toUpperCase();
					console.log(search_term);
					// var search_term = "oo"; // search term
					var search = new RegExp(search_term, "i");
					var cont = 0;
					if (search_term != "") {
						var arr = jQuery
								.grep(
										lista_departamento,
										function(value, i) {

											console.log(i)

											value = lista_departamento[i].NO_DEP
													.trim().toUpperCase();
											console.log(value)
											console.log(search.test(value))
											
											if (cont < 5) {
												if (search.test(value) == true) {
													
													console.log(value);
													var array = value
															.split(search_term)
													var longitud = array.length;
// array = array.filter(Boolean);
console.log(array);
													
												
											var d = '<li id="'+lista_departamento[i].ID_DEPARTAMENTO
													.trim()+','+lista_departamento[i].NO_DEP.trim()+','+lista_departamento[i].NO_TRABAJADOR.trim()+','+lista_departamento[i].AP_PATERNO.trim()+','+lista_departamento[i].FECHA_PROGRAMA.trim()+','+lista_departamento[i].FECHA_SOLICITUD.trim()+'" onclick=buscarDepartamento(this.id)><span>'
															

													
												
														for (var i=0; i<longitud; i++){
															
														if(array[i]==""){
						
												if(i!=long){
									
														d += '<span class="highlight">'
																														d += search_term
																														d += '</span>'
							}
													
														}else{
														
															var long = longitud -1
															
															if(i==long){
					
																d += array[i]
																console.log(d)
															}else{
				
																d += array[i]
																d += '<span class="highlight">'
																																d += search_term
																																d += '</span>'
				
															}
														
														
														}
														}
													
													
													
													
													
													
													
													
													$("#lista").append(d);
													cont++;
												}
											} else {

											}

											return "ok";

											// var c = '<ul
											// class="autocomplete-content
											// dropdown-cont"><li><span><span
											// class="highlight">Ap</span>ple</span></li><li><span><span
											// class="highlight">Micro</span>soft</span></li></ul>'

										});
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
					} else {
						// var c = '<ul class="autocomplete-content
						// dropdown-cont" id="lista"></ul>'
						$("#lista").html("");
					}

					// console.log("arreglo .... "+arr);

				});


function buscarDepartamento(id){
	
	console.log(id);
	var array = id.split(",");
	console.log(array);
	$("#autocomplete").val(array[1].trim());
	$("#jefe").val(array[2].trim() +', '+ array[3].trim());
	$("#iddep").val(array[0].trim())
	if(eleccion==1){
		$('#date_edit').pickadate('picker').set('select',
				array[4].trim(), {
					format : 'dd/mm/yyyy'
				}).trigger("change");
		key_to_change=1;
	}else if(eleccion==2){
		$('#date_edit').pickadate('picker').set('select',
				array[5].trim(), {
					format : 'dd/mm/yyyy'
				}).trigger("change");
		key_to_change=1;
	}else{
		alertify.alert('Error', 'Debe seleccionar el tipo de plazo a modificar: solicitud o programa.', function(){ alertify.success('Ok'); });
		$("#autocomplete").val("");
		$("#jefe").val("");
	}
	
	Materialize.updateTextFields();
	$("#lista").html("");
}


function editarPlazo(){
	
	$("#date_edit").attr("disabled", false);
	$("#date_edit").attr("enabled", true);
}

function guardarPlazo(){
	var date = $("#date_edit").val();
	var fecha_nueva = parseDate(date);
	console.log(fecha_nueva);
	var iddep= $("#iddep").val();
	var tipo = eleccion;
	$.get(gth_context_path + "/configuraciones/insertarNuevoPlazo", {"iddepartamento": iddep, "fecha":fecha_nueva, "tipo":tipo}, function(response){
		console.log(response);
		if(response==1){
			alertify.alert('Excelente!', 'Los cambios se guardaron correctamente', function(){ 
				alertify.success('Ok');
				$("#jefe").val("");
				$("#date_edit").val("");
				$("#iddep").val("");
				$("#autocomplete").val("");
				$("#date_edit").attr("disabled", true);
				$("#date_edit").attr("enabled", false);
				Materialize.updateTextFields();
				key_to_change=0;
			});
		}else{
			alertify.alert('Mensaje de alerta', 'Hubo un error al intentar guardar los cambios', function(){ 
				alertify.success('Ok');
				
			});
		}
	});
	
	
	key_to_change=0;
	
	
}

function cancelarPlazo(){
	
	alertify.confirm('¿Está seguro de cancelar los cambios realizados?', function(){ alertify.success('Ok') 
		$("#autocomplete").val("");
	$("#jefe").val("");
	$("#date_edit").val("");
	$("#iddep").val("");
	Materialize.updateTextFields();
		}, function(){ 
		
		alertify.error('Cancel')});
	key_to_change=0;
}



function searchTrabajador(){
	dni = $("#searchTrabajador").val();
	console.log(dni);

	if (!/^([0-9])*$/.test(dni)){
		$("#alerta").html("El valor " + dni + " no es un número");
	}else{
		$("#alerta").html("");
	}
}

function buscarPorDni(){

	if(dni.length == 8){
		$.get(gth_context_path + "/configuraciones/buscarTrabajador", {"dni": dni}, function(response){
			console.log(response);
			var res = JSON.parse(response);
			for(var i in res) {
			    console.log(res[i].VA_PRIVILEGIO);
			    $("#name").val(+res[i].AP_PATERNO + +res[i].AP_MATERNO +", "+ res[i].NO_TRABAJADOR);
			    $("#seccion").val(res[i].NO_SECCION);
			    $("#area").val(res[i].NO_AREA);
			    $("#departamento").val(res[i].NO_DEP);
			    $("#idtrabajador").val(res[i].ID_TRABAJADOR);
			    if(res[i].VA_PRIVILEGIO == null){
			    	console.log("indefinido");
			    	var rol = res[i].ID_ROL;
				
					if (rol == "ROL-0003") {

						nro_vacaciones = 2;

					} else {
						if (rol == "ROL-0008") {
							nro_vacaciones = 3;
						} else {
							console.log("sin nada");
							nro_vacaciones = 1;
							
						}

					}
					
					$("#select_option").val(nro_vacaciones);
					$("#select_option").material_select()
			    }else{
			    	$("#select_option").val(res[i].VA_PRIVILEGIO);
					$("#select_option").material_select()
			    	
			    }
			   
			}
			 Materialize.updateTextFields();
		});
	}else{
		alertify.alert('Mensaje de alerta', 'Por favor ingreso un dni válido', function(){ 
			alertify.success('Ok');
			
		});
	}

}


function guardarPrivilegio(){
	var idtrabajador = $("#idtrabajador").val();
	var valor = $("#select_option").val();
	$.get(gth_context_path + "/configuraciones/guardarPrivilegio", {"idtrabajador": idtrabajador , "valor":valor}, function(response){
		console.log(response);
	
		if(response==1){
			alertify.alert('Excelente!', 'Los cambios se guardaron correctamente', function(){ 
				alertify.success('Ok');
				cancelarPrivilegio();
				Materialize.updateTextFields();
				
			});
		}else{
			alertify.alert('Mensaje de alerta', 'Hubo un error al intentar guardar los cambios', function(){ 
				alertify.success('Ok');
				
			});
		}

	});
}


function cancelarPrivilegio(){
	$("#searchTrabajador").val("");
	$("#name").val("");
    $("#seccion").val("");
    $("#area").val("");
    $("#departamento").val("");
    $("#idtrabajador").val("");
    Materialize.updateTextFields();
    $("#select_option").val(0);
	$("#select_option").material_select()
}




