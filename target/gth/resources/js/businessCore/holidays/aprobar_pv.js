$(document).ready(function() {
	listar();

});

var rad = document.myForm.group1;
var prev = null;
for (var i = 0; i < rad.length; i++) {
	rad[i].onclick = function() {
		if (this.value == 1500) {
			listar();
			$("#confirmar").show();
		}
		if (this.value == 1600) {
			listarAprobados();
			$("#confirmar").hide();
		}
		if (this.value == 1700) {
			listarRechazados();
			$("#confirmar").hide();
		}
	};
}

function getSelected() {
	var allVals = [];
	$('#data :checked').each(function() {
		allVals.push($(this).parents("#data tr").find(".sorting_1").text());
	});
	return allVals;
}

function listar() {
	$
			.get(
					"programa_vacaciones/get",
					function(obj) {
						var s = "";
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
									+ "value='"
									+ obj[i].AP_PATERNO
									+ " "
									+ obj[i].AP_MATERNO
									+ ", "
									+ obj[i].NO_TRABAJADOR
									+ "'  onclick='preba(this.value, this.id);'></button>";
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
			d += "</td>";
			d += "</tr>";
		}
		$("#contTable").empty();
		$("#contTable").append(createTable2());
		$("#data").empty();
		$("#data").append(d);
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
						$("#contTable").empty();
						$("#contTable").append(createTable3());
						$("#data").empty();
						$("#data").append(d);
						$("#data-table-row-grouping1").dataTable();
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
	s += " </tr>";
	s += "</thead>";
	s += "<tbody id='data'></tbody>";
	s += "</table>";
	return s;
};
function createTable3() {
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
	s += "<th>Observación</th>";
	s += " </tr>";
	s += "</thead>";
	s += "<tbody id='data'></tbody>";
	s += "</table>";
	return s;
};

var nom_tra = "";
function preba(nombre, idde) {
	var idtr = $('#' + idde).parents("tr").find("td").eq(1).text();
	nom_tra = nombre;
	$("#modal" + idde).remove();
	$("#cuerpo").append(createModal(idde, idtr));
	$("#modal" + idde).openModal();
	$('#textarea' + idde).characterCounter();

};

function openVerObsModal(texto, idde) {
	$("#modal" + idde).remove();
	$("#cuerpo").append(createModalObs(idde, texto));
	$("#modal" + idde).openModal();
};

function createModal(idde, idtr) {
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
			+ "' class='materialize-textarea' length='150'></textarea>\r\n";
	s += "<label for='textarea" + idde + "'>Escriba Observación para: ";
	s += nom_tra;
	s += "</label></div></div></form></div></div>\r\n";
	s += "<div class='modal-footer'>\r\n";
	s += "<button href='#!'";
	s += " class='modal-action modal-close btn waves-effect waves-light' value='"
			+ idde
			+ "' id='"
			+ idtr
			+ "' onclick='observar(this.id, this.value);' >ENVIAR!</button>\r\n";
	s += '<button class="modal-action modal-close waves-effect waves-green btn-flat" data-dismiss="alert" aria-label="Close">'
			+ '<span aria-hidden="true">CANCELAR</span></button>'
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

$("#confirmar")
		.click(
				function() {
					arrid = getSelected();
					var id_arr = arrid;
					var id_det = id_arr.join(",");
					var datos = "id_det=" + id_det;
					var con = new jsConnector();
					con
							.post(
									"vacaciones/programa_vacaciones/guardarAprobar?"
											+ datos,
									null,
									function(data) {
										if (data == 1) {
											Materialize
													.toast(
															'Felicidades!!, ha aprobado a sus trabajadores',
															3000, 'rounded');
											listar();
										} else {
											Materialize
													.toast(
															'UPS!!, No se ha registrado su aprobación, verifique si chequeó los datos!',
															3000, 'rounded');
										}
									});
				});
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
									console.log(receptor);
								});
						Materialize.toast('El trabajador ' + nom_tra
								+ ' ha sido observado(a)', 3000, 'rounded');
						listar();
					} else {
						Materialize.toast(
								'UPS!!, No se ha registrado su observación',
								3000, 'rounded');
					}
				});
	}
}