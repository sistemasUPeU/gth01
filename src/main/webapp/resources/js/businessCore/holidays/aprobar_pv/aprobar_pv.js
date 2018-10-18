$(document).ready(function() {
	listarAnteriorCollapsible();
	listarAprobados();
	listarRechazados();
	$("#cargando").hide();
});

function getSelected() {
	var allVals = [];
	$('#data :checked').each(function() {
		allVals.push($(this).parents("#data tr").find(".sorting_1").text());
	});
	return allVals;
}

function listarAnterior() {
	$
			.get(
					"programa_vacaciones/get",
					function(obj) {
						var s = "";
						var emp = obj[0];

						for (var i = 0; i < obj.length; i++) {
							if (obj[i].EVALUACION == "1") {
								ojalafuncione(obj[i].ID_VACACIONES);
							}
						}

						function ojalafuncione(ids_vacs) {
							for (var j = 0; j < obj.length; j++) {
								findWithAttr(obj, 'ID_VACACIONES', ids_vacs);
							}
						}
						for (var i = 0; i < obj.length; i++) {
							if (obj[i].EVALUACION == "1") {
								ojalafuncione(obj[i].ID_VACACIONES);
							}
						}
						function findWithAttr(array, attr, value) {
							for (var i = 0; i < array.length; i += 1) {
								if (array[i][attr] === value) {
									obj.splice(i, 1);
								}
							}
						}

						for (var i = 0; i < obj.length; i++) {
							if (obj[i].EVALUACION == "1") {
								ojalafuncione(obj[i].ID_VACACIONES);
							}
						}

						for (var i = 0; i < obj.length; i++) {
							var con = "";
							if (obj[i].LI_CONDICION == 1) {
								con = "CONTRATADO";
							}
							if (obj[i].LI_CONDICION == 2) {
								con = "EMPLEADO";
							}
							if (obj[i].LI_CONDICION == 3) {
								con = "MISIONERO";
							}
							if (obj[i].LI_CONDICION == 4) {
								con = "MFL, Practicas Pre -- Profesionales";
							}
							if (obj[i].LI_CONDICION == 5) {
								con = "MFL, Practicas Profesionales";
							}
							if (obj[i].LI_CONDICION == 6) {
								con = "MFL, CLJ, Convenio laboral Juvenil";
							}
							if (obj[i].LI_CONDICION == 7) {
								con = "MFL -- Contrato";
							}
							s += "<tr><td class='hide' id='id_det' >";
							s += obj[i].ID_DET_VACACIONES;
							s += "</td><td class='hide' >";
							s += obj[i].ID_TRABAJADOR;
							s += "</td><td>";
							s += obj[i].AP_PATERNO + " " + obj[i].AP_MATERNO;
							s += ", ";
							s += obj[i].NO_TRABAJADOR;
							s += "</td><td>";
							s += obj[i].NO_SECCION;
							s += "</td><td>";
							s += obj[i].NU_VAC;
							s += "</td><td>";
							s += obj[i].NU_DOC;
							s += "</td><td>";
							s += obj[i].FECHA_INICIO;
							s += "</td><td>";
							s += obj[i].FECHA_FIN;
							s += "</td><td>";
							s += con;
							s += "</td><td>";
							s += obj[i].TIPO;
							s += "</td>";
							s += "<td><p style='text-align: center;'>";
							s += "<input type='checkbox' class='checkBoxClass' id='test"
									+ i + "'>";
							s += " <label for='test" + i + "'></label>\r\n";
							s += "</p></td>\r\n";
							s += "<td>";
							s += "<button id='"
									+ obj[i].ID_DET_VACACIONES
									+ "' class='waves-effect waves-light btn modal-trigger light-blue getid mdi-image-remove-red-eye' "
									+ "value='" + obj[i].AP_PATERNO + " "
									+ obj[i].AP_MATERNO + ", "
									+ obj[i].NO_TRABAJADOR
									+ "'  onclick='preba(this.value, this.id,'"
									+ obj[i].ID_VACACIONES + "');'></button>";
							s += "</td>";
							s += "</tr>";
						}

						$("#contTable").empty();
						$("#contTable").append(createTable1());
						$("#data").empty();
						$("#data").append(s);
						$("#data-table-row-grouping").dataTable();
					});

};

function listarAprobados() {
	$.get("programa_vacaciones/getAprobados", function(obj) {
		var d = "";
		var emp = obj[0];
		for (var i = 0; i < obj.length; i++) {
			var con = "";
			if (obj[i].LI_CONDICION == 1) {
				con = "CONTRATADO";
			}
			if (obj[i].LI_CONDICION == 2) {
				con = "EMPLEADO";
			}
			if (obj[i].LI_CONDICION == 3) {
				con = "MISIONERO";
			}
			if (obj[i].LI_CONDICION == 4) {
				con = "MFL, Practicas Pre -- Profesionales";
			}
			if (obj[i].LI_CONDICION == 5) {
				con = "MFL, Practicas Profesionales";
			}
			if (obj[i].LI_CONDICION == 6) {
				con = "MFL, CLJ, Convenio laboral Juvenil";
			}
			if (obj[i].LI_CONDICION == 7) {
				con = "MFL -- Contrato";
			}
			d += "<tr><td>";
			d += obj[i].AP_PATERNO + " "
			d += obj[i].AP_MATERNO;
			d += ", ";
			d += obj[i].NO_TRABAJADOR;
			d += "</td><td>";
			d += obj[i].NO_SECCION;
			d += "</td><td>";
			d += obj[i].NU_VAC;
			d += "</td><td>";
			d += obj[i].NU_DOC;
			d += "</td><td>";
			d += obj[i].FECHA_INICIO;
			d += "</td><td>";
			d += obj[i].FECHA_FIN;
			d += "</td><td>";
			d += con;
			d += "</td><td>";
			d += obj[i].TIPO;
			d += "</td>";
			d += "</tr>";
		}
		$("#contTable1").empty();
		$("#contTable1").append(createTable2());
		$("#data1").empty();
		$("#data1").append(d);
		$("#data-table-row-grouping1").dataTable();
	});

};
function listarRechazados() {
	$
			.get(
					"programa_vacaciones/getRechazados",
					function(obj) {
						var d = "";
						var emp = obj[0];
						for (var i = 0; i < obj.length; i++) {
							var con = "";
							if (obj[i].LI_CONDICION == 1) {
								con = "CONTRATADO";
							}
							if (obj[i].LI_CONDICION == 2) {
								con = "EMPLEADO";
							}
							if (obj[i].LI_CONDICION == 3) {
								con = "MISIONERO";
							}
							if (obj[i].LI_CONDICION == 4) {
								con = "MFL, Practicas Pre -- Profesionales";
							}
							if (obj[i].LI_CONDICION == 5) {
								con = "MFL, Practicas Profesionales";
							}
							if (obj[i].LI_CONDICION == 6) {
								con = "MFL, CLJ, Convenio laboral Juvenil";
							}
							if (obj[i].LI_CONDICION == 7) {
								con = "MFL -- Contrato";
							}
							d += "<tr><td>";
							d += obj[i].AP_PATERNO + " " + obj[i].AP_MATERNO;
							d += ", ";
							d += obj[i].NO_TRABAJADOR;
							d += "</td><td>";
							d += obj[i].NO_SECCION;
							d += "</td><td>";
							d += obj[i].NU_VAC;
							d += "</td><td>";
							d += obj[i].NU_DOC;
							d += "</td><td>";
							d += obj[i].FECHA_INICIO;
							d += "</td><td>";
							d += obj[i].FECHA_FIN;
							d += "</td><td>";
							d += con;
							d += "</td><td>";
							d += obj[i].TIPO;
							d += "</td>";
							d += "<td>";
							d += "<button id='"
									+ obj[i].ID_DET_VACACIONES
									+ "' class='waves-effect waves-light btn modal-trigger light-blue getid mdi-image-remove-red-eye' value='"
									+ obj[i].TEXTO
									+ "'  onclick='openVerObsModal(this.value, this.id);'></button>";
							d += "</td>";
							d += "</tr>";
						}
						$("#contTable3").empty();
						$("#contTable3").append(createTable3());
						$("#data3").empty();
						$("#data3").append(d);
						$("#data-table-row-grouping3").dataTable();
					});

};

function createTable1() {
	var s = "<table id='data-table-row-grouping' class='display bordered highlight centered' >";
	s += "<thead>";
	s += "<tr>";
	s += "<th class='hide' >det_vac</th>";
	s += "<th class='hide' >N°</th>";
	s += "<th>Nombres</th>";
	s += "<th>Sección</th>";
	s += "<th>Dias Totales</th>";
	s += "<th>DNI</th>";
	s += "<th>FEC INI</th>";
	s += "<th>FEC FIN</th>";
	s += "<th>Condición</th>";
	s += "<th>Tipo de Solicitud</th>";
	s += "<th>Aprobar</th>";
	s += "<th>Observación</th>";
	s += "</tr>";
	s += "</thead>";
	s += "<tbody id='data'></tbody>";
	s += "</table>";
	return s;

};
function createTable2() {
	var s = "<table id='data-table-row-grouping1' class='display bordered highlight centered' >";
	s += "<thead>";
	s += "<tr>";
	s += "<th>Nombres</th>";
	s += "<th>Sección</th>";
	s += "<th>Dias Totales</th>";
	s += "<th>DNI</th>";
	s += "<th>FEC INI</th>";
	s += "<th>FEC FIN</th>";
	s += "<th>Condición</th>";
	s += "<th>Tipo de Solicitud</th>";
	s += " </tr>";
	s += "</thead>";
	s += "<tbody id='data1'></tbody>";
	s += "</table>";
	return s;
};
function createTable3() {
	var s = "<table id='data-table-row-grouping3' class='display bordered highlight centered' >";
	s += "<thead>";
	s += "<tr>";
	s += "<th>Nombres</th>";
	s += "<th>Sección</th>";
	s += "<th>Dias Totales</th>";
	s += "<th>DNI</th>";
	s += "<th>FEC INI</th>";
	s += "<th>FEC FIN</th>";
	s += "<th>Condición</th>";
	s += "<th>Tipo de Solicitud</th>";
	s += "<th>Observación</th>";
	s += " </tr>";
	s += "</thead>";
	s += "<tbody id='data3'></tbody>";
	s += "</table>";
	return s;
};

var nom_tra = "";
var check;
function preba(nombre, idde, idvac) {
	check = document.getElementById("calendar_frame").contentWindow.obj_observados;
	var texto;
	for (var i = 0; i < check.length; i++) {
		if (check[i].id_det_vac == idde) {
			texto = check[i].mensaje;
		}
	}
	var idtr = $('#' + idde).parents("tr").find("td").eq(1).text();
	nom_tra = nombre;
	$("#modal" + idde).remove();
	$("#cuerpo").append(createModal(idde, idtr, idvac, texto));
	$("#modal" + idde).openModal();
	$('#textarea' + idde).characterCounter();
};

function openVerObsModal(texto, idde) {
	$("#modal" + idde).remove();
	$("#cuerpo").append(createModalObs(idde, texto));
	$("#modal" + idde).openModal();
};

function createModal(idde, idtr, idvac, texto) {
	var s = "<td><div id='modal" + idde + "' class='modal'>\r\n";

	s += "<div class='modal-content'>\r\n";
	s += "<center>\r\n";
	s += "<h4>Observación</h4>\r\n";
	s += "</center>\r\n";
	s += "<div class='row'>\r\n";
	s += "<form class='col s12'>\r\n";
	s += "<div class='row'>\r\n";
	s += "<div class='input-field col s12'>\r\n";
	s += "<textarea id='textarea" + idde
			+ "' class='materialize-textarea' length='150'>" + texto
			+ "</textarea>\r\n";
	s += "<label for='textarea" + idde + "'>Escriba Observación para: ";
	s += nom_tra;
	s += "</label></div></div></form></div></div>\r\n";
	s += "<div class='modal-footer'>\r\n";
	s += '<button class="modal-action modal-close waves-effect waves-green btn-flat" value="'
			+ idde
			+ '" id="'
			+ idvac
			+ '" onclick="eliminarTexto(this.value, this.id);">'
			+ 'Eliminar</button>';
	s += '<button class="modal-action modal-close btn waves-effect waves-light" value="'
			+ idde
			+ '" id="'
			+ idvac
			+ '" onclick="guardarTexto(this.value, this.id);">'
			+ 'Guardar</button>';
	s += "</div>\r\n" + "</div></td>";
	return s;
};

function createModalObs(idde, texto) {
	var s = "<td><div id='modal" + idde + "' class='modal'>\r\n";
	s += "<div class='modal-content'>\r\n";
	s += "<center>\r\n";
	s += "<h4>Observación</h4>\r\n";
	s += "</center>\r\n";
	s += "<div class='row'>\r\n";
	s += "<form class='col s12'>\r\n";
	s += "<div class='row'>\r\n";
	s += "<div class='input-field col s12'>\r\n";
	s += "<p>" + texto + "</p>";
	s += "</div></div></form></div></div>\r\n";
	s += "<div class='modal-footer'>\r\n";
	s += "<button href='#!'";
	s += " class='modal-action modal-close waves-effect waves-green btn-flat'>OK!</button>\r\n";
	s += "</div>\r\n" + "</div></td>";
	return s;
};

function aprobar(id_det) {
	$("#nocargando").hide();
	$("#cargando").show();
	var datos = "id_det=" + id_det;
	var con = new jsConnector();
	con
			.post(
					"vacaciones/programa_vacaciones/guardarAprobar?" + datos,
					null,
					function(data) {
						if (data == 1) {
							Materialize
									.toast(
											'Felicidades!!, ha aprobado la programación de vacaciones',
											3000, 'rounded');
							listarAnteriorCollapsible();
							listarAprobados();
							listarRechazados();
						} else {
							Materialize
									.toast(
											'NO se ha aprobado nigún programa de vacaciones',
											3000, 'rounded');
						}
						$("#nocargando").show();
						$("#cargando").hide();
					});
}

function observar(idtr, id_det) {
	var obs = $(".hiddendiv").text();
	var extra = obs.length - 150;
	if (obs.length > 150) {
		obs = "";
		Materialize.toast('Observación no enviada, ha escrito ' + extra
				+ ' carácter(es) extra', 3000, 'rounded');
	} else {
		var idte = $("#trab").val();
		var datos = "id_det=" + id_det;
		datos += "&text=" + obs.trim();
		datos += "&emisor=" + idte;
		datos += "&receptor=" + idtr;
		var con = new jsConnector();
		con.post("vacaciones/programa_vacaciones/guardarObservar?" + datos,
				null, function(data) {
					if (data == 1 && obs.length <= 150) {
						con.post(
								"vacaciones/programa_vacaciones/enviarObservacion?"
										+ datos, null, function(receptor) {
									Materialize.toast(
											'Correo enviado exitosamente',
											3000, 'rounded');
								});
						Materialize.toast('El trabajador ' + nom_tra
								+ ' ha sido observado(a)', 3000, 'rounded');
						listarAnteriorCollapsible();
						listarAprobados();
						listarRechazados();
					} else {
						Materialize.toast(
								'UPS!!, No se ha registrado su observación',
								3000, 'rounded');
					}
				});
	}
}

function guardarTexto(id_det, idvac) {
	check = document.getElementById("calendar_frame").contentWindow.obj_observados;
	var obs = $(".hiddendiv").text();
	var extra = obs.length - 150;
	if (obs.length > 150) {
		obs = "";
		Materialize.toast('Observación no Guardada, ha escrito ' + extra
				+ ' carácter(es) extra', 3000, 'rounded');
	} else {
		for (var i = 0; i < check.length; i++) {
			if (check[i].id_det_vac == id_det) {
				check[i].estado = "1";
				check[i].mensaje = obs;
			}
		}
		Materialize.toast('Observación escrita está guardada', 3000, 'rounded');
	}
	document.getElementById("calendar_frame").contentWindow
			.calendarProperties(idvac);
}

function eliminarTexto(id_det, idvac) {
	check = document.getElementById("calendar_frame").contentWindow.obj_observados;
	for (var i = 0; i < check.length; i++) {
		if (check[i].id_det_vac == id_det) {
			check[i].estado = "0";
			check[i].mensaje = "";
			Materialize.toast('Observación escrita está eliminada', 3000,
					'rounded');
		}
	}

	document.getElementById("calendar_frame").contentWindow
			.calendarProperties(idvac);
}

var arr_apr = new Array();
var arr_obs = new Array();
var arr_idtr = new Array();
var count_apr = 0;
var count_idtr = 0;
var count_obs = 0;
function recorrerDatos() {
	check = document.getElementById("calendar_frame").contentWindow.obj_observados;

	for (var i = 0; i < check.length; i++) {
		if (check[i].estado == 1) {
			arr_obs[count_obs] = check[i].id_det_vac;
			arr_idtr[count_idtr] = check[i].idtr;
			count_obs++;
			count_idtr++;
		} else if (check[i].estado == 0 && check[i].fecha_fin != "") {
			arr_apr[count_apr] = check[i].id_det_vac;
			count_apr++;
		}
	}

	for (var i = 0; i < arr_obs.length; i++) {
		observar(arr_idtr[i], arr_obs[i]);
	}

	aprobar(arr_apr);
}

function listarAnteriorCollapsible() {
	$
			.get(
					"programa_vacaciones/getNombres",
					function(obj) {
						var s = "";

						for (var i = 0; i < obj.length; i++) {
							var con = "";
							if (obj[i].LI_CONDICION == 1) {
								con = "CONTRATADO";
							}
							if (obj[i].LI_CONDICION == 2) {
								con = "EMPLEADO";
							}
							if (obj[i].LI_CONDICION == 3) {
								con = "MISIONERO";
							}
							if (obj[i].LI_CONDICION == 4) {
								con = "MFL, Practicas Pre -- Profesionales";
							}
							if (obj[i].LI_CONDICION == 5) {
								con = "MFL, Practicas Profesionales";
							}
							if (obj[i].LI_CONDICION == 6) {
								con = "MFL, CLJ, Convenio laboral Juvenil";
							}
							if (obj[i].LI_CONDICION == 7) {
								con = "MFL -- Contrato";
							}
							s += '<li><div class="collapsible-header" id="'
									+ obj[i].ID_VACACIONES
									+ '" onclick="showCalendar(this.id)">';
							s += obj[i].AP_PATERNO + " " + obj[i].AP_MATERNO;
							s += ", ";
							s += obj[i].NO_TRABAJADOR;
							s += '</div>';
							s += '<div class="collapsible-body">';
							s += '<p>Sección: ';
							s += obj[i].NO_SECCION;
							s += '</p>';
							s += '<p>Dias totales: ';
							s += '30'
							s += '</p>';
							s += '<p>DNI: ';
							s += obj[i].NU_DOC;
							s += '</p>';
							s += '<p>Condición: ';
							s += con;
							s += '</p>';
							s += '<p>Tipo de solicitud: ';
							s += obj[i].TIPO;
							s += '</p>';
							s += '<center>';
							s += '<p>';
							s += '<button class="btn waves-effect waves-light" onclick="recorrerDatos()">Confirmar';
							s += '</button>';
							s += '</p>';
							s += '</center>';
							s += '</div></li>';
						}
						$("#listarAnteriorCollapsible").empty();
						$("#listarAnteriorCollapsible").append(s);
						$('.collapsible').collapsible();
					});

};

jQuery.ajax({
	type : 'GET',
	data : jQuery(this).serialize(),
	url : 'programa_vacaciones/getCalendar',
	success : function(data, textStatus) {
		jQuery('#calendar_frame').contents().find('body').html(data);
	},
	error : function(XMLHttpRequest, textStatus, errorThrown) {
	}
});

var frame = document.getElementById('calendar_frame');
var script = frame.contentWindow.document.createElement('script');
script.type = 'text/javascript';
script.src = "/gth/resources/js/jquery-1.11.2.min.js";
frame.contentWindow.document.body.appendChild(script);

var frame = document.getElementById('calendar_frame');
var script = frame.contentWindow.document.createElement('script');
script.type = 'text/javascript';
script.src = "/gth/resources/js/businessCore/holidays/aprobar_pv/calendar.js";
frame.contentWindow.document.body.appendChild(script);

var frame = document.getElementById('calendar_frame');
var script = frame.contentWindow.document.createElement('script');
script.type = 'text/javascript';
script.src = "/gth/resources/js/plugins/datepickk-master/dist/datepickk.js";
frame.contentWindow.document.body.appendChild(script);

var btncargarcalendar;
function showCalendar(id_vac) {
	document.getElementById("divcollapsible").setAttribute("class",
			"col s12 m5 l5");
	$("#containerframe").attr("class", "col s12 m7 l7");
	btncargarcalendar = window.frames["calendar_frame"].document
			.getElementById("btncargarcalendar");

	btncargarcalendar
			.setAttribute("onclick", "multiFunction('" + id_vac + "')");
	btncargarcalendar.click();
	var lastHeight = parseFloat($("#listarAnteriorCollapsible").css('height'));
	if (lastHeight != collapsibleHeight) {
		collapsibleHeight = lastHeight + 507;
		$("#containerframe").css('height', collapsibleHeight + 'px');
	} else if (lastHeight == collapsibleHeight) {
		$("#containerframe").css('height', lastHeight + 'px');
	}
}
var collapsibleHeight;