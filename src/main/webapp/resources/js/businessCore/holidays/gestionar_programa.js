$(document).ready(function() {
			listarTrabajadorFiltrado();
			listarTrabajadoresConSoli();
			listarTrabajadoresAprobados();
			$("#cargando").hide();
		})
		function getSelected() {
			var allVals = [];
			$('#data :checked').each(function() {
				allVals.push($(this).parents("#data tr").find(".solici").html());
			});
			return allVals;
		}
		$("#table_contenido").on(
				"click",
				"#abrir-modal2",
				function() {
					var id_det_vac = $(this).attr("name");
					console.log(id_det_vac);
					$("#iddet").val(id_det_vac);
					$.get('readFechaMod', {
						id : id_det_vac
					}, function(data) {
						console.log("fechas: " + data[0].FECHA_INICIO + " y "
								+ data[0].FECHA_FIN);
						$('#fec_in').pickadate('picker').set('select',
								data[0].FECHA_INICIO, {
									format : 'dd/mm/yyyy'
								}).trigger("change");
						$('#fec_fi').pickadate('picker').set('select',
								data[0].FECHA_FIN, {
									format : 'dd/mm/yyyy'
								}).trigger("change");
						console.log(data);
					});
					$("#modal2").openModal();
				});
		function abrirModalSolicitud(value) {
			console.log("abriendo modal s " + value);
			$("#modal1").openModal();
			$("#idtrb").val(value);

			var idtrbselected = value;

			$.getJSON(gth_context_path + "/solicitud/validar", {
				id : idtrbselected
			}, function(res, status) {

				console.log("devuelve controller: " + res);

				switch (res) {

				case 1:

					//						window.location.href = gth_context_path +"/solicitud/registrar?op=1";
					var datos = "op=1"
					console.log("Programacion");
					$("#tiposolicitud").val("Programacion");
					$("#tipo").val("Programación");
					Materialize.updateTextFields();

					break;
				case 2:

					//					window.location.href = gth_context_path +"/";
					Materialize.toast('Usted tiene una solicitud en proceso!',
							3000, 'rounded', function() {
								var datos = "op=3"
								$.get(
										gth_context_path
												+ '/solicitud/registrar',
										datos, function(response) {
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
					//						window.location.href = "http://localhost:8099/gth/solicitud/registrar?op=2";
					//						console.log("reprogramacion");
					var datos = "op=2"
					console.log("Reprogramacion");
					$("#tiposolicitud").val("Reprogramación");
					$("#tipo").val("Reprogramación");
					//						$.get(gth_context_path+'/solicitud/registrar', datos, function(response) {
					//							console.log(response);
					//							
					//							$("#dashboard").html(response);
					//						});
					break;
				}
			});

		}

		$("#confirmar_lista").click(function() {
			arrid = getSelected();
			console.log(arrid);
			// 				for (var i = 0; i < arrid.length; i++) {
			// 				}
		});
//Listar trabajadores que no han hecho solicitud
		function listarTrabajadorFiltrado() {
			$
					.get(
							'GestionarProgramaVacaciones/readallProgramaVacaciones',
							function(obj) {
								var s = '';
								var emp = obj[0];
								for (var i = 0; i < obj.length; i++) {
									s += '<tr>';
									s += '<td class="hide">'
											+ obj[i].ID_DET_VACACIONES
											+ '</td>';
									s += '<td>' + obj[i].NO_TRABAJADOR + ' '
											+ obj[i].AP_PATERNO + ' '
											+ obj[i].AP_MATERNO + '</td>';
									s += '<td>' + obj[i].NO_AREA + '</td>';
									s += '<td>' + obj[i].NO_SECCION + '</td>';
									if (obj[i].FECHA_INICIO == null
											&& obj[i].FECHA_FIN == null) {
										s += '<td>--</td>';
										s += '<td>--</td>';
									} else {
										s += '<td>' + obj[i].FECHA_INICIO
												+ '</td>';
										s += '<td>' + obj[i].FECHA_FIN
												+ '</td>';
									}
									// 									s += '<td><p style="text-align: center;">';
									// 									s += '<input type="checkbox" id="test'+i+'">';
									// 									s += '<label for="test'+i+'"></label>';
									// 									s += '</p></td>';
									s += '<td><button id="abrir-modal1" value="'
											+ obj[i].ID_TRABAJADOR
											+ '" class="waves-effect waves-light btn red" onclick=abrirModalSolicitud(this.value)>&#128197;</button></td>';
									// 									s += '<td><button id="abrir-modal2" class="waves-effect waves-light btn modal-trigger light-blue modal-trigger" href="#modal2">&#10000;</button></td>';
									s += '</tr>';

								}
								$
										.getJSON(
												gth_context_path
														+ '/components',
												"opc=usuario",
												function(objJSON) {
													if (objJSON !== null) {
														var q = '';
														q = objJSON.username;
														$("#username").val('');
														$("#username").val(q);
													} else {
														console
																.error("No se esta cargando la información");
													}
												});
								$("#table_contenido1").empty();
								$("#table_contenido1").append(createTable());
								$("#data1").empty();
								$("#data1").append(s);
								$('#data-table-row-grouping1').dataTable();
								$("#ckbCheckAll").click(
										function() {
											$(".checkBoxClass").prop('checked',
													$(this).prop('checked'));
										});

							});
		};
//Listar trabajadores con solicitud
		function listarTrabajadoresConSoli() {
			$
					.get(
							'GestionarProgramaVacaciones/TrabajadoresConSoliProgramaVacaciones',
							function(obj) {
								console.log(obj);
								var s = '';
								var emp = obj[0];
								for (var i = 0; i < obj.length; i++) {
									s += '<tr>';
									s += '<td id="soli" class="hide solici">'
											+ obj[i].ID_DET_VACACIONES
											+ '</td>';
									s += '<td>' + obj[i].NO_TRABAJADOR + ' '
											+ obj[i].AP_PATERNO + ' '
											+ obj[i].AP_MATERNO + '</td>';
									s += '<td>' + obj[i].NO_AREA + '</td>';
									s += '<td>' + obj[i].NO_SECCION + '</td>';
									if (obj[i].FECHA_INICIO == null
											&& obj[i].FECHA_FIN == null) {
										s += '<td>--</td>';
										s += '<td>--</td>';
									} else {
										s += '<td>' + obj[i].FECHA_INICIO
												+ '</td>';
										s += '<td>' + obj[i].FECHA_FIN
												+ '</td>';
									}
									s += '<td><p style="text-align: center;">';
									s += '<input type="checkbox" id="test'+i+'">';
									s += '<label for="test'+i+'"></label>';
									s += '</p></td>';
									s += '<td><button id="abrir-modal2" name="' + obj[i].ID_DET_VACACIONES + '" class="waves-effect waves-light btn modal-trigger light-blue modal-trigger" href="#modal2">&#10000;</button></td>';
									s += '</tr>';

								}
								$
										.getJSON(
												gth_context_path
														+ '/components',
												"opc=usuario",
												function(objJSON) {
													if (objJSON !== null) {
														var q = '';
														q = objJSON.username;
														$("#username").val('');
														$("#username").val(q);
													} else {
														console
																.error("No se esta cargando la información");
													}
												});
								$("#table_contenido").empty();
								$("#table_contenido").append(createTable1());
								$("#data").empty();
								$("#data").append(s);
								$('#data-table-row-grouping').dataTable();
								$("#ckbCheckAll").click(
										function() {
											$(".checkBoxClass").prop('checked',
													$(this).prop('checked'));
										});

							});
		};
//Listar trabajadores aprobados
		function listarTrabajadoresAprobados() {
			$
					.get(
							'GestionarProgramaVacaciones/TrabajadoresAprobados',
							function(obj) {
								var s = '';
								var emp = obj[0];
								for (var i = 0; i < obj.length; i++) {
									s += '<tr>';
									s += '<td class="hide">'
											+ obj[i].ID_DET_VACACIONES
											+ '</td>';
									s += '<td>' + obj[i].NO_TRABAJADOR + ' '
											+ obj[i].AP_PATERNO + ' '
											+ obj[i].AP_MATERNO + '</td>';
									s += '<td>' + obj[i].NO_AREA + '</td>';
									s += '<td>' + obj[i].NO_SECCION + '</td>';
									if (obj[i].FECHA_INICIO == null
											&& obj[i].FECHA_FIN == null) {
										s += '<td>--</td>';
										s += '<td>--</td>';
									} else {
										s += '<td>' + obj[i].FECHA_INICIO
												+ '</td>';
										s += '<td>' + obj[i].FECHA_FIN
												+ '</td>';
									}

									s += '</tr>';

								}
								$
										.getJSON(
												gth_context_path
														+ '/components',
												"opc=usuario",
												function(objJSON) {
													if (objJSON !== null) {
														var q = '';
														q = objJSON.username;
														$("#username").val('');
														$("#username").val(q);
													} else {
														console
																.error("No se esta cargando la información");
													}
												});
								$("#table_contenido3").empty();
								$("#table_contenido3").append(createTable2());
								$("#data3").empty();
								$("#data3").append(s);
								$('#data-table-row-grouping3').dataTable();
								$("#ckbCheckAll").click(
										function() {
											$(".checkBoxClass").prop('checked',
													$(this).prop('checked'));
										});

							});
		};
//Crear tabla de trabajadores sin solicitud		
		function createTable() {
			var s = '<table id="data-table-row-grouping1" class="display" cellspacing="0" width="100%">';
			s += '<thead>';
			s += '<tr>';
			s += '<th class="hide">id</th>';
			s += '<th>Apellidos y Nombres</th>';
			s += '<th>Área</th>';
			s += '<th>Sección</th>';
			s += '<th>Fecha Inicio</th>';
			s += '<th>Fecha Fin</th>';
			// 			s += '<th>Aprobar</th>';
			s += '<th>Programar</th>';
			// 			s += '<th>Modificar</th>';
			s += ' </tr>';
			s += '</thead>';
			s += '<tbody id="data1"></tbody>';
			s += '</table>';
			return s;

		};
//Crear tabla de los trabajadores con solicitud		
		function createTable1() {
			var s = '<table id="data-table-row-grouping" class="display" cellspacing="0" width="100%">';
			s += '<thead>';
			s += '<tr>';
			s += '<th class="hide">id</th>';
			s += '<th>Apellidos y Nombres</th>';
			s += '<th>Área</th>';
			s += '<th>Sección</th>';
			s += '<th>Fecha Inicio</th>';
			s += '<th>Fecha Fin</th>';
			s += '<th>Aprobar</th>';
			s += '<th>Modificar</th>';
			s += ' </tr>';
			s += '</thead>';
			s += '<tbody id="data"></tbody>';
			s += '</table>';
			return s;

		};
//Crear tabla de trabajadores aprobados		
		function createTable2() {
			var s = '<table id="data-table-row-grouping3" class="display" cellspacing="0" width="100%">';
			s += '<thead>';
			s += '<tr>';
			s += '<th class="hide">id</th>';
			s += '<th>Apellidos y Nombres</th>';
			s += '<th>Área</th>';
			s += '<th>Sección</th>';
			s += '<th>Fecha Inicio</th>';
			s += '<th>Fecha Fin</th>';
			s += ' </tr>';
			s += '</thead>';
			s += '<tbody id="data3"></tbody>';
			s += '</table>';
			return s;

		};
//Confirmar lista de trabajadores con solicitus listos para ser aprobados		
		$("#confirmar_lista")
				.click(
						function() {
							$("#nocargando").hide();
							$("#cargando").show();
							arrid = getSelected();
							var id_arr = arrid;
							var id_det = id_arr.join(",");
							console.log(username);
							console.log(id_det);
							var datos = "&id_det=" + id_det;
							datos += "&id_det=" + id_det;
							var con = new jsConnector();
							con

									.post(
											"vacaciones/GestionarProgramaVacaciones/insertProgramaVacaciones?"
													+ datos,
											null,
											function(data) {
												if (data == 1) {
													Materialize
															.toast(
																	'Felicidades!!, ha aprobado a sus trabajadores',
																	3000,
																	'rounded');
													listarTrabajadorFiltrado();
													listarTrabajadoresConSoli();
													listarTrabajadoresAprobados();
												} else {
													Materialize
															.toast(
																	'UPS!!, No se ha registrado su aprobacion, verifique si chequeó los datos!',
																	3000,
																	'rounded');
												}
												$("#nocargando").show();
												$("#cargando").hide();
											});

						});

		$("#fec_up").click(
				function() {
					var f = new Date();
					parseDate($("#fec_in").val());
					var fec_in = fecha_extra;
					var fec_ac = f.getDate() + "/" + (f.getMonth() + 1) + "/"
							+ f.getFullYear();
					parseDate($("#fec_fi").val());
					var fec_fi = fecha_extra;
					var id = $("#iddet").val();
					console.log(fec_in + " y " + fec_fi + " y " + id + " fea "
							+ fec_ac);
					parseDate(fec_in);
					var fechai = new Date(fecha_recontraextra);
					parseDate(fec_ac);
					var fechaa = new Date(fecha_recontraextra);
					console.log(fechai + fechaa);
					if (fec_in > fec_ac) {
						$.get('updateFechaMod', {
							id : id,
							inicio : fec_in,
							fin : fec_fi
						}, function(data) {
							console.log(data);
							listarTrabajadoresConSoli();
							Materialize.toast('Fecha modificada correctamente',
									3000, 'rounded');
						});
					} else {
						Materialize.toast('Escoge una fecha correcta!', 3000,
								'rounded');
					}
				});

		$("#fec_up").click(
				function() {
					var f = new Date();
					parseDate($("#fec_in").val());
					var fec_in = fecha_extra;
					var fec_ac = f.getDate() + "/" + (f.getMonth() + 1) + "/"
							+ f.getFullYear();
					parseDate($("#fec_fi").val());
					var fec_fi = fecha_extra;
					var id = $("#iddet").val();
					console.log(fec_in + " y " + fec_fi + " y " + id + " fea "
							+ fec_ac);
					if (fec_in > fec_ac) {
						$.get('updateFechaMod', {
							id : id,
							inicio : fec_in,
							fin : fec_fi
						}, function(data) {
							console.log(data);
							listarTrabajadoresConSoli();
						});
						Materialize.toast('Fecha modificada correctamente',
								3000, 'rounded');
					} else {
						Materialize.toast('Escoge una fecha correcta!', 3000,
								'rounded');
					}
				});

		$("#fec_in").change(
				function() {
					var fe_i = $("#fec_in").val();
					console.log(fe_i);
					var fecha_inicio = parseDate(fe_i);
					console.log("fecha_inicio_return: " + fecha_inicio);
					$('#fec_fi').pickadate('picker').set('select',
							calcular_final(fecha_inicio), {
								format : 'dd/mm/yyyy'
							}).trigger("change");
					Materialize.updateTextFields();
				});

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