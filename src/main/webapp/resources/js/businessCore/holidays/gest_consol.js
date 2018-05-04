$(document).ready(function() {
	listarSinAprobar();
	listarAprobado();
});

function getSelected() {
	var allVals = [];
	$('#data :checked').each(function() {
		allVals.push($(this).parents("#data tr").find("#id_det").text());
	});
	return allVals;
}

var z;

$("#modalAprobado").on("click", ".check", function() {
	if ($(this).attr("name") == 0) {
		$(this).removeClass('pink lighten-2');
		$(this).addClass('green accent-3');
		$(this).attr("name", "1");
		$(this).find("#i").removeClass('mdi-navigation-close');
		$(this).find("#i").addClass('mdi-navigation-check');
	} else if ($(this).attr("name") == 1) {
		$(this).removeClass('green accent-3');
		$(this).addClass('pink lighten-2');
		$(this).attr("name", "0");
		$(this).find("#i").removeClass('mdi-navigation-check');
		$(this).find("#i").addClass('mdi-navigation-close');
	}
});

$("#guardar").click(
		function() {
			var con = new jsConnector();
			console.log(z);
			if (z == 1) {
				var a, p, q, idtra, idvac;
				$(".idtra").each(function() {
					if ($(this).val() == 1) {
						idtra = $(this).attr("name");
					}
				});
				$(".idvac").each(function() {
					if ($(this).val() == 1) {
						idvac = $(this).attr("name");
					}
				});
				$(".det").each(function() {
					if ($(this).val() == 1) {
						a = $(this).attr("name");
					}
				});
				$(".check").each(function() {
					if ($(this).val() == 1) {
						p = $(this).attr("name");
					}
					if ($(this).val() == 2) {
						q = $(this).attr("name");
					}
				});
				console.log("***");
				console.log(a);
				console.log(p);
				console.log(q);
				console.log("***");
				con.post('vacaciones/consolidado/updateFirma?' + "id=" + a
						+ "&inicio=" + p + "&fin=" + q, null, function(data) {
					console.log(data);
					if (data == 1) {
						if (p == 0 && q == 0) {
							Materialize.toast(
									'Ninguna firma se ha actualizado!', 3000,
									'rounded');
						} else {
							Materialize.toast(
									'Firma actualizada correctamente!', 3000,
									'rounded');
						}
					} else {
						Materialize.toast(
								'No se obtuvieron datos, consulte con su jefe',
								3000, 'rounded');
					}
				});
			}
		});

$("#contTable")
		.on(
				"click",
				"#openModal",
				function() {
					var id = $(this).attr("name");
					console.log(id);
					var datos = "id=" + id;
					var con = new jsConnector();
					con
							.post(
									'vacaciones/consolidado/readFechas?'
											+ datos,
									null,
									function(obj) {
										console.log(obj);
										var btnSolicitud = '';
										var traba = '';
										var id_det = '';
										for (var i = 0; i < obj.length; i++) {
											traba = obj[i].ID_TRABAJADOR;
											id_det = obj[i].ID_DET_VACACIONES;
											btnSolicitud += '<div class="col s12"><center><div class="row"><a class="waves-effect waves-light btn-large" id="verSolicitud" href="'
													+ gth_context_path
													+ '/vacaciones/consolidado/mostrardoc?traba='
													+ obj[i].ID_TRABAJADOR
													+ '&id_det='
													+ obj[i].ID_DET_VACACIONES
													+ '&op=1" target="_blank" >Ver Solicitud</a></div></center></div>';
										}
										$("#btnsubirsolicitud").hide();
										$
												.get(
														"consolidado/getUrl",
														{
															traba : traba,
															id_det : id_det
														},
														function(data) {
															console.log(data);
															if (data[0].URL_SOLICITUD != null) {
																$(
																		"#verbtnSolicitudsinAprobar")
																		.empty();
																$(
																		"#verbtnSolicitudsinAprobar")
																		.append(
																				btnSolicitud);
															} else if (data[0].URL_SOLICITUD == null) {
																$(
																		"#verbtnSolicitudsinAprobar")
																		.empty();
																$(
																		"#verbtnSolicitudsinAprobar")
																		.append(
																				'<div class="col s12" id="file-input-field" ><div class="file-field input-field"><div class="btn"><span>Solicitud</span> <input type="file" name="file" id="file-input-s"></div><div class="file-path-wrapper"><input class="file-path validate" type="text"></div></div>'
																						+ '</div><br> '
																						+ '<input type="text" id="idvac" name="idvac" value="'
																						+ data[0].ID_VACACIONES
																						+ '" class="hide" />'
																						+ '<input type="text" id="id_det" name="id_det" value="'
																						+ data[0].ID_DET_VACACIONES
																						+ '" class="hide" />'
																						+ '<input type="text" id="value" name="value" value="0" class="hide" />');
																$(
																		"#file-input-s")
																		.change(
																				function() {
																					console
																							.log("está vacío");
																					$(
																							"#file-input-field")
																							.show(
																									0)
																							.delay(
																									500)
																							.hide(
																									0);
																					$(
																							"#btnsubirsolicitud")
																							.hide(
																									0)
																							.delay(
																									500)
																							.show(
																									0);
																				});
															}
														});
									});
					$("#modal").openModal();
				});
$("#contTableAprobado")
		.on(
				"click",
				"#open",
				function() {
					$("#btnsubirpapeleta").hide();
					var id = $(this).attr("name");
					console.log(id);
					var datos = "id=" + id;
					var con = new jsConnector();
					con
							.post(
									'vacaciones/consolidado/readFechas?'
											+ datos,
									null,
									function(obj) {
										console.log(obj);
										$("#contenedor_fechas").empty();
										var j = '';
										var btnSolicitud = '';
										var btnPapeleta = '';
										var traba = '';
										var k = 0;
										var fechas = document
												.getElementById("contenedor_fechas");
										var n_n = 0;
										for (var i = 0; i < obj.length; i++) {
											k = k + 1;
											j += '<div class="col s3">';
											j += '<p>Fecha Inicio</p>';
											j += '<input value="'
													+ obj[i].FECHA_INICIO
													+ '" disabled type="text">';
											j += '</div>';
											j += '<div class="col s1">';
											j += '<br> <br>';
											n_n = n_n + 1;
											if (obj[i].FIRMA_SALIDA == 0) {
												j += '<button class="btn-floating waves-effect waves-light pink lighten-2 check" value="'
														+ n_n
														+ '" name="'
														+ obj[i].FIRMA_SALIDA
														+ '">';
												j += '<i class="mdi-navigation-close" id="i"></i>';
												j += '</button>';
												j += '</div>';
												j += '<div class="col s3">';
												j += '<p>Fecha Fin</p>';
												j += '<input value="'
														+ obj[i].FECHA_FIN
														+ '" disabled type="text">';
												j += '</div>';
												j += '<div class="col s1">';
												j += '<br> <br>';
												n_n = n_n + 1;
												j += '<button class="btn-floating waves-effect waves-light pink lighten-2 check" disabled value="'
														+ n_n
														+ '"  name="'
														+ obj[i].FIRMA_ENTRADA
														+ '">';
												j += '<i class="mdi-navigation-close" id="i"></i>';
												j += '</button>';
											} else if (obj[i].FIRMA_SALIDA == 1) {
												j += '<button class="btn-floating waves-effect waves-light green accent-3 check" disabled value="'
														+ n_n
														+ '" name="'
														+ obj[i].FIRMA_SALIDA
														+ '">';
												j += '<i class="mdi-navigation-check" id="i"></i>';
												j += '</button>';
												j += '</div>';
												j += '<div class="col s3">';
												j += '<p>Fecha Fin</p>';
												j += '<input value="'
														+ obj[i].FECHA_FIN
														+ '" disabled type="text">';
												j += '</div>';
												j += '<div class="col s1">';
												j += '<br> <br>';
												n_n = n_n + 1;
												if (obj[i].FIRMA_ENTRADA == 0) {
													j += '<button class="btn-floating waves-effect waves-light pink lighten-2 check" value="'
															+ n_n
															+ '"  name="'
															+ obj[i].FIRMA_ENTRADA
															+ '">';
													j += '<i class="mdi-navigation-close" id="i"></i>';
													j += '</object>';
												} else if (obj[i].FIRMA_ENTRADA == 1) {
													j += '<button class="btn-floating waves-effect waves-light green accent-3 check" disabled value="'
															+ n_n
															+ '"  name="'
															+ obj[i].FIRMA_ENTRADA
															+ '">';
													j += '<i class="mdi-navigation-check" id="i"></i>';
													j += '</object>';
												}
											}
											j += '</div>';
											j += '<button class="hide det" value="'
													+ k
													+ '" name="'
													+ obj[i].ID_DET_VACACIONES
													+ '"></button>';
											j += '<button class="hide idtra" value="'
													+ k
													+ '" name="'
													+ obj[i].ID_TRABAJADOR
													+ '"></button>';
											j += '<button class="hide idvac" value="'
													+ k
													+ '" name="'
													+ obj[i].ID_VACACIONES
													+ '"></button>';
											traba = obj[i].ID_TRABAJADOR;
											id_det = obj[i].ID_DET_VACACIONES;
											btnSolicitud += '<div class="col s6"><center><div class="row"><a class="waves-effect waves-light btn-large" id="verSolicitud" href="'
													+ gth_context_path
													+ '/vacaciones/consolidado/mostrardoc?traba='
													+ obj[i].ID_TRABAJADOR
													+ '&id_det='
													+ obj[i].ID_DET_VACACIONES
													+ '&op=1" target="_blank" >Ver Solicitud</a></div></center></div>';
											btnPapeleta += '<div class="col s6"><br><center><div class="row"><a class="waves-effect waves-light btn-large" href="'
													+ gth_context_path
													+ '/vacaciones/consolidado/mostrardoc?traba='
													+ obj[i].ID_TRABAJADOR
													+ '&id_det='
													+ obj[i].ID_DET_VACACIONES
													+ '&op=2" target="_blank" >Ver Papeleta</a></div></center></div>';
										}
										fechas.innerHTML += j;
										z = obj.length;
										$("#verbtnSolicitud").empty();
										$("#verbtnSolicitud").append(
												btnSolicitud);
										$
												.get(
														"consolidado/getUrl",
														{
															traba : traba,
															id_det : id_det
														},
														function(data) {
															console.log(data);
															if (data[0].URL_PAPELETA != null) {
																$(
																		"#verbtnPapeleta")
																		.empty();
																$(
																		"#verbtnPapeleta")
																		.append(
																				btnPapeleta);
															} else if (data[0].URL_PAPELETA == null) {
																$(
																		"#verbtnPapeleta")
																		.empty();
																$(
																		"#verbtnPapeleta")
																		.append(
																				'<div class="col s6"><div class="file-field input-field" id="file-input-field-p" ><div class="btn" ><span>Papeleta</span> <input type="file" name="file" id="file-input-p" /></div><div class="file-path-wrapper"><input type="text" class="file-path validate" />'
																						+ '</div></div></div>'
																						+ '<input type="text" id="idvac" name="idvac" value="'
																						+ data[0].ID_VACACIONES
																						+ '" class="hide" />'
																						+ '<input type="text" id="id_det" name="id_det" value="'
																						+ data[0].ID_DET_VACACIONES
																						+ '" class="hide" />'
																						+ '<input type="text" id="value" name="value" value="1" class="hide" />');
																$(
																		"#file-input-p")
																		.change(
																				function() {
																					console
																							.log("está vacío");
																					$(
																							"#file-input-field-p")
																							.show(
																									0)
																							.delay(
																									500)
																							.hide(
																									0);
																					$(
																							"#btnsubirpapeleta")
																							.hide(
																									0)
																							.delay(
																									500)
																							.show(
																									0);
																				});
															}
														});
									});
					$("#modalAprobado").openModal();
				});

function listarSinAprobar() {
	$
			.get(
					"consolidado/getSinAprobar",
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
							s += "</td><td>";
							s += obj[i].AP_PATERNO + " " + obj[i].AP_MATERNO;
							s += ", ";
							s += obj[i].NO_TRABAJADOR;
							s += "</td><td>";
							s += obj[i].NU_DOC;
							s += "</td><td>";
							s += obj[i].NO_DEP;
							s += "</td><td>";
							s += obj[i].FECHA_INICIO;
							s += "</td><td>";
							s += obj[i].FECHA_FIN;
							s += "</td><td>";
							s += obj[i].NU_VAC;
							s += "</td><td>";
							s += con;
							s += "</td>";
							s += '<td><button id="openModal" class="btn-floating waves-effect waves-light light-blue accent-4" href="#modal" name="'
									+ obj[i].ID_DET_VACACIONES + '">';
							s += '<i class="mdi-image-remove-red-eye"></i></button></td>';
							if (obj[i].URL != null) {
								s += "<td><p style='text-align: center;'>";
								s += "<input type='checkbox' class='checkBoxClass' id='test"
										+ i + "'>";
								s += " <label for='test" + i + "'></label>\r\n";
								s += "</p></td>\r\n";
							} else if (obj[i].URL == null) {
								s += "<td><p style='text-align: center;'>";
								s += "<input type='checkbox' id='indeterminate test"
										+ i + "' disabled>";
								s += " <label for='test" + i + "'></label>\r\n";
								s += "</p></td>\r\n";
							}
							s += "</tr>";

						}
						$("#contTable").empty();
						$("#contTable").append(createTable1());
						$("#data").empty();
						$("#data").append(s);
						$("#data-table-row-grouping").dataTable();

						for (var i = 0; i < obj.length; i++) {
							if (obj[i].URL != null) {
								$("#ckbCheckAll").click(
										function() {
											$(".checkBoxClass").prop('checked',
													$(this).prop('checked'));
										});
							}
						}
					});

};

function listarAprobado() {
	$
			.get(
					"consolidado/getAprobado",
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
							s += "</td><td>";
							s += obj[i].AP_PATERNO + " " + obj[i].AP_MATERNO;
							s += ", ";
							s += obj[i].NO_TRABAJADOR;
							s += "</td><td>";
							s += obj[i].NU_DOC;
							s += "</td><td>";
							s += obj[i].NO_DEP;
							s += "</td><td>";
							s += obj[i].FECHA_INICIO;
							s += "</td><td>";
							s += obj[i].FECHA_FIN;
							s += "</td><td>";
							s += obj[i].NU_VAC;
							s += "</td><td>";
							s += con;
							s += "</td>";
							s += '<td><button id="open" class="btn-floating waves-effect waves-light light-blue accent-4" href="#modal" name="'
									+ obj[i].ID_DET_VACACIONES + '">';
							s += '<i class="mdi-image-remove-red-eye"></i></button></td>';
							s += "</tr>";

						}
						$("#contTableAprobado").empty();
						$("#contTableAprobado").append(createTableAprobado());
						$("#data2").empty();
						$("#data2").append(s);
						$("#data-table-row-grouping-aprobado").dataTable();
					});
};

function createTable1() {
	var s = "<table id='data-table-row-grouping' class='display bordered highlight centered' >";
	s += "<thead>";
	s += "<tr>";
	s += "<th class='hide' >det_vac</th>";
	s += "<th>Nombres</th>";
	s += "<th>DNI</th>";
	s += "<th>Departamento</th>";
	s += "<th>Fecha-Entrada</th>";
	s += "<th>Fecha-Salida</th>";
	s += "<th>Dias Totales</th>";
	s += "<th>Condición</th>";
	s += "<th>Ver Detalle</th>";
	s += "<th>";
	s += "<p style='text-align: center;'>";
	s += "<input type='checkbox'  id='ckbCheckAll'>";
	s += " <label for='ckbCheckAll'>Aprobar</label>\r\n";
	s += "</p></th>\r\n";
	s += " </tr>";
	s += "</thead>";
	s += "<tbody id='data'></tbody>";
	s += "</table>";
	return s;

};

function createTableAprobado() {
	var s = "<table id='data-table-row-grouping-aprobado' class='display bordered highlight centered' >";
	s += "<thead>";
	s += "<tr>";
	s += "<th class='hide' >det_vac</th>";
	s += "<th>Nombres</th>";
	s += "<th>DNI</th>";
	s += "<th>Departamento</th>";
	s += "<th>Fecha-Entrada</th>";
	s += "<th>Fecha-Salida</th>";
	s += "<th>Dias Totales</th>";
	s += "<th>Condición</th>";
	s += "<th>Ver Detalle</th>";
	s += " </tr>";
	s += "</thead>";
	s += "<tbody id='data2'></tbody>";
	s += "</table>";
	return s;

};

$("#confirmar")
		.click(
				function() {
					arrid = getSelected();
					var id_arr = arrid;
					var id_det = id_arr.join(",");
					var datos = "id_det=" + id_det;
					console.log(id_arr);
					var con = new jsConnector();
					con
							.post(
									"vacaciones/consolidado/guardarAprobarConsolidado?"
											+ datos,
									null,
									function(data) {
										if (data == 1) {
											con.post(
													"vacaciones/consolidado/enviarCorreoAprobarConsolidado?"
															+ datos, null,
													function(receptor) {
														console.log(receptor);
													});
											Materialize
													.toast(
															'Felicidades!!, ha aprobado a sus trabajadores',
															3000, 'rounded');
											listarSinAprobar();
											listarAprobado();
										} else {
											Materialize
													.toast(
															'UPS!!, No se ha registrado su aprobacion, verifique si chequeó los datos!',
															3000, 'rounded');
										}
									});

				});

$("#print").click(
		function() {
			$('#modal1').openModal();
			$("#request").attr("data",
					gth_context_path + "/vacaciones/consolidado/reporte?");
		});