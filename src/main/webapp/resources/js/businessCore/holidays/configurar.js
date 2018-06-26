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

function activar(valor) {

	var select = $("#" + valor).prop('checked');

	$("input:checkbox:not('#" + valor + "')").each(function() {
		$(this).prop('checked', false);
	})

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

													var d = '<li id="'+lista_departamento[i].ID_DEPARTAMENTO
													.trim()+'" onclick=buscarDepartamento(this.id)><span>'
															+ array[0]
															+ '<span class="highlight">'
															+ search_term
															+ '</span>'
															+ array[1]
															+ '</span></li>'
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
	$("#autocomplete").val(id);
	$("#lista").html("");
}
