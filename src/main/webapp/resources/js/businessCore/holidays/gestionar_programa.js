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
		function abrirModalSolicitud(value, callback) {
			console.log("abriendo modal s " + value);
			cont=1;
			$("#space").html("");
			$("#idtrb").val(value);

			$('.dropify').dropify();

			// 							Translated
			$('.dropify-fr')
					.dropify(
							{
								messages : {
									// default: 'Glissez-déposez un fichier ici ou cliquez',
									replace : 'Glissez-déposez un fichier ou cliquez pour remplacer',
									remove : 'Supprimer',
									error : 'Désolé, le fichier trop volumineux'
								}
							});

			// 							Used events
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
			//funcion que pertenece a solicitud.js
			console.log("a punto de llamar a setDatosUser desde gestionar programa " + value );
			
			$("#modal_gest_programa").openModal();
			console.log("antes de llamar el callback de fecha minima")
			callback(value,setDatosUser);
			

			

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
							gth_context_path + '/vacaciones/gestionar_programa/readallProgramaVacaciones',
							function(obj) {
								var s = '';
								var emp = obj[0];
								for (var i = 0; i < obj.length; i++) {
									s += '<tr>';
									s += '<td class="hide">'
											+ obj[i].ID_DET_VACACIONES
											+ '</td>';
									s += '<td>' + obj[i].AP_PATERNO + ' '
											+ obj[i].AP_MATERNO + ', '
												+ obj[i].NO_TRABAJADOR + '</td>';
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
//									s += '<td><button id="abrir-modal1" value="'
//											+ obj[i].ID_TRABAJADOR
//											+ '" class="waves-effect waves-light btn red" onclick=abrirModalSolicitud(this.value)>&#128197;</button></td>';
									// 									s += '<td><button id="abrir-modal2" class="waves-effect waves-light btn modal-trigger light-blue modal-trigger" href="#modal2">&#10000;</button></td>';
									
									if(obj[i].VALIDACION != 0){
										//no tiene solicitud subida al sistema
										s += '<td style=" text-align: center;" ><a onclick= "abrirModalSolicitud(this.id, setFechaMinima)" class="btn-floating btn-large waves-effect waves-light orange darken-4" id="'+ obj[i].ID_TRABAJADOR+'"><i class="mdi-action-event"></i></a></td>';

									}else{
										s += '<td style=" text-align: center;" ><a onclick= "abrirModalSolicitud(this.id, setFechaMinima)" class="btn-floating btn-large waves-effect waves-light cyan darken-2" id="'+ obj[i].ID_TRABAJADOR+'"><i class="mdi-action-event"></i></a></td>';

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
							gth_context_path + '/vacaciones/gestionar_programa/TrabajadoresConSoliProgramaVacaciones',
							function(obj) {
								console.log(obj);
								var s = '';
								var emp = obj[0];
								for (var i = 0; i < obj.length; i++) {
									s += '<tr>';
									s += '<td id="soli" class="hide solici">'
											+ obj[i].ID_DET_VACACIONES
											+ '</td>';
									s += '<td>' + obj[i].AP_PATERNO + ' '
											+ obj[i].AP_MATERNO + ', '
											+ obj[i].NO_TRABAJADOR + '</td>';
									s += '<td>' + obj[i].NO_AREA + '</td>';
									s += '<td>' + obj[i].NO_SECCION + '</td>';
									s += '<td>' + obj[i].TIPO + '</td>';
									
									if(obj[i].VALIDACION == 3){
										//REVIEW
										s += '<td style=" text-align: center;" ><a onclick= "verdetalle(this.id)" class="btn-floating btn-large waves-effect waves-light green accent-3" id="'+ obj[i].ID_TRABAJADOR +'"><i class="mdi-maps-rate-review"></i></a></td>';
									}else if(obj[i].VALIDACION == 1){
										//REPROGRAMAR
										s += '<td style=" text-align: center;" ><a onclick= "abrirModalSolicitud(this.id, setFechaMinima)" class="btn-floating btn-large waves-effect waves-light cyan darken-2" id="'+ obj[i].ID_TRABAJADOR+'"><i class="mdi-action-cached"></i></a></td>';

									}
									
//									if (obj[i].FECHA_INICIO == null
//											&& obj[i].FECHA_FIN == null) {
//										s += '<td>--</td>';
//										s += '<td>--</td>';
//									} else {
//										s += '<td>' + obj[i].FECHA_INICIO
//												+ '</td>';
//										s += '<td>' + obj[i].FECHA_FIN
//												+ '</td>';
//									}
//									s += '<td><p style="text-align: center;">';
//									s += '<input type="checkbox" id="test'+i+'">';
//									s += '<label for="test'+i+'"></label>';
//									s += '</p></td>';
//									s += '<td><button id="abrir-modal2" name="' + obj[i].ID_DET_VACACIONES + '" class="waves-effect waves-light btn modal-trigger light-blue modal-trigger" href="#modal2">&#10000;</button></td>';
//									s += '</tr>';

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
							gth_context_path + '/vacaciones/gestionar_programa/TrabajadoresAprobados',
							function(obj) {
								var s = '';
								var emp = obj[0];
								for (var i = 0; i < obj.length; i++) {
									s += '<tr>';
//									s += '<td class="hide">'
//											+ obj[i].ID_DET_VACACIONES
//											+ '</td>';
									s += '<td>' + obj[i].AP_PATERNO + ' '
											+ obj[i].AP_MATERNO + ', '
												+ obj[i].NO_TRABAJADOR + '</td>';
									s += '<td>' + obj[i].NO_AREA + '</td>';
									s += '<td>' + obj[i].NO_SECCION + '</td>';

									s += '<td>' + obj[i].TIPO + '</td>';
									s += '<td style=" text-align: center;" ><a onclick= "verdetalleaprobados(this.id)" class="btn-floating btn-large waves-effect waves-light green accent-3" id="'+ obj[i].ID_TRABAJADOR +'"><i class="mdi-av-my-library-books"></i></a></td>';


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
			s += '<th>Tipo solicitud</th>';
			s += '<th>Operaciones</th>';
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
//			s += '<th class="hide">id</th>';
			s += '<th>Apellidos y Nombres</th>';
			s += '<th>Área</th>';
			s += '<th>Sección</th>';
			s += '<th>Tipo solicitud</th>';
			s += '<th>Operaciones</th>';
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
											gth_context_path + "/vacaciones/gestionar_programa/insertProgramaVacaciones?"
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
		
		
		
		
		var lista_ap = new Array(); // lista aprobados
		function verdetalle(idtrab){//ver detalle de trabajadores con solicitud por aprobar o rechazar
			console.log("listo para ver detalle "  + idtrab);
			$('#modal_review').openModal()
			$
			.get(
					gth_context_path + '/vacaciones/gestionar_programa/mostrardetalle', {idtrab : idtrab},
					function(obj) {
						console.log(JSON.stringify(obj[0]));
						var s = '';
						var emp = obj[0];
						
						var m = '';
						m += '<p> <i class="small mdi-social-person small cyan-text text-darken-2"'
							m += 'style="margin-right: 6%; vertical-align: -8px;"></i>' + obj[0].NO_TRABAJADOR
								m += '</p>'
									 m += '<p> <i class="small mdi-action-home small cyan-text text-darken-2"'
										 m += 'style="margin-right: 6%;vertical-align: -8px;"></i>'  +  obj[0].NO_AREA
											 m += '</p>'
								
						$("#column1").html(m);
						var n = '';
						n += '<p>'
							n += '<i'
								n += '	class="small mdi-social-person-outline small cyan-text text-darken-2"'
									n += 'style="margin-right: 6%;vertical-align: -8px;"></i>' + obj[0].AP_PATERNO +' ' + obj[0].AP_MATERNO
									n += '</p>'
									 n += '<p> <i	class="small mdi-action-home small cyan-text text-darken-2"'
										 n += 'style="margin-right: 6%;vertical-align: -8px;"></i> ' + obj[0].NO_SECCION
					
												 n += '</p>	'
						 $("#column2").html(n);
						console.log("bucle adentro")
						
						for (var i in obj) {
							s += '<tr>';
							s += '<td class="hide">'
									+ obj[i].ID_DET_VACACIONES
									+ '</td>';
							s += '<td style="text-align: center;">' + obj[i].FECHA_INICIO +'</td>';
							s += '<td style="text-align: center;">' + obj[i].FECHA_FIN + '</td>';
							s += '<td style="text-align: center;">' + obj[i].DIAS + '</td>';
							s += '<td style="text-align: center;"><p style="text-align: center;">';
//							s += '<input type="checkbox" id="test'+i+'">';
//							s += '<label for="test'+i+'"></label>';
							
//							s += '<input type="checkbox" name="checkbox'+i+'" id="checkbox'+i+'" class="css-checkbox" />'
//							s += '<label  for="checkbox'+i+'" class="css-label radGroup'+i+'"></label>'
							s += ' <div class="switch"> <label>   <input class="switch-class" onchange = "controlCambios(this.value, this.id)" id = "'+i+'" type="checkbox" value = "'+obj[i].ID_DET_VACACIONES+'"> <span class="lever"></span> </label> </div>'
							s += '</p></td>';
						

							s += '</tr>';

						}
						
						$("#tabla-detalle").empty();
						$("#tabla-detalle").append(createTable3());
						$("#data4").empty();
						$("#data4").append(s);
						$('#data-table-row-grouping4').dataTable({
							"pageLength" : 3,
							"bPaginate" : true,
							"bLengthChange" : false,
							"bFilter" : false,
							"bInfo" : false,
							"bAutoWidth" : true,
							"language" : {
								// "lengthMenu": "Display _MENU_ records per page",
								"zeroRecords" : "Nada para mostrar - disculpe",
								"info" : "Mostrando página _pag_ de _pags_",
								"infoEmpty" : "Ningún alumno agregado"
							// "infoFiltered": "(filtered from _MAX_ total records)"
							}
					
							});
						console.log("truinnng")
						$('#data-table-row-grouping4').DataTable().column(4).nodes().to$().find('input[type=checkbox]').prop('checked', true)
						console.log($('#data-table-row-grouping4').DataTable().column(4).nodes().to$().find('input[type=checkbox]'));
						//.to$().find('input[type=checkbox]').prop('checked', $(this).prop('checked'));
						var nodes = $('#data-table-row-grouping4').DataTable().column(4).nodes().to$().find('input[type=checkbox]');
						lista_ap = new Array();
						for(var j =0; j<nodes.length; j++){
							console.log(nodes[j]);
							console.log(nodes[j].value);
							
							lista_ap.push(nodes[j].value);
							console.log(lista_ap)
						}

					});
			
			
		}
		var lista_re = new Array(); //lista rechazados
		
//se encarga de controlar los cambios del switch en el detalle de cada trabajador
		function controlCambios(iddet, id){
			console.log(iddet);
			for( var i in lista_ap){
				lista_ap[i] = lista_ap[i].trim();
		    	
			}	
			if($("#"+id.trim()).is(":checked")) {
			      console.log("Is checked");

			      for( var i in lista_re){
			    	  if(lista_re[i] == iddet.trim()){
			    		  lista_re.splice(i,1);
			    		  
			    	  }
			      }
			      
			      lista_ap.push(iddet.trim());

			    }
			    else {
			      console.log("Is Not checked");

			      lista_re.push(iddet.trim());
			      
			      for( var i in lista_ap){
			    	  if(lista_ap[i] == iddet.trim()){
			    		  lista_ap.splice(i,1);
			    		  
			    	  }
			      }
			    }
			 console.log("lista de rechazados : " + lista_re);
		     console.log("lista de aprobados : " +lista_ap);
			
		
		}
		
		function reprogramar(idtrab){
			console.log("listo para reprogramar "  + idtrab);
			
		}
		
		//Crear tabla del detalle de cada trabajador con solicitud por aprobar		
		function createTable3() {
			var s = '<table id="data-table-row-grouping4" class="display" cellspacing="0" width="100%">';
			s += '<thead>';
			s += '<tr>';
			s += '<th class="hide" >id</th>';
			s += '<th style="text-align: center;">Fecha Inicio</th>';
			s += '<th style="text-align: center;">Fecha Fin</th>';
			s += '<th style="text-align: center;">Total de Días</th>';
			s += '<th style="text-align: center;">Operaciones</th>';
			s += ' </tr>';
			s += '</thead>';
			s += '<tbody id="data4"></tbody>';
			s += '</table>';
			return s;

		};
		
		
	//guarda los cambios realizados en el detalle del trabajador
		function guardarCambios(){
			var tipo = 0;
			var jsonObj = [];
			if(lista_re == ''){
				//aprobacion completa , tipo 1
				console.log("aprueba toda la solicitud   " + lista_ap);
				tipo = 1;
			}else if(lista_ap == ''){
				//rechazo completo , tipo 2
				console.log("debe insertar las observacione  " + lista_re);
				tipo = 2;
			}else{
				//rechazo y aprobacion, tipo 3
				console.log("debe insertar las observacione y aprobaciones  " + lista_re + " , " + lista_ap);
				tipo = 3;
			}
			
			
			var con = new jsConnector();
			console.log(gth_context_path);
//			item = {}
//			item["lista_ap"] = lista_ap;
//			item["lista_re"] = lista_re;
			
			var joiner, joiner1, str = '';
			joiner_ap = lista_ap.join(',');
			joiner_re = lista_re.join(',');
			
			var final = joiner_ap + ',&,'+joiner_re;
			
//			for(var n in lista_ap){
//				t += lista_re[n] + ','
//			}
//			jsonObj.push("-");
//			t += ' - ,';
//			for(var m in lista_re){
////				jsonObj.push(lista_re[m]);
//				t += lista_re[m] + ',';
//			}

			
			
//			var enviar = JSON.stringify(t);

//			var final = JSON.stringify(str);
			con.post('/vacaciones/gestionar_programa/insertarAprobacion', final, 
					function(response){
				console.log(response);
				lista_re = new Array(); //lista rechazados
				lista_ap = new Array(); //lista aprobados
				
				if(response == 1){
					Materialize
					.toast(
							'Bien!, los cambios se guardaron correctamente',
							3000,
							'rounded');
					listarTrabajadoresConSoli();
					listarTrabajadoresAprobados();
				}else{
					Materialize
					.toast(
							'Error!, ha surgido un problema.',
							3000,
							'rounded');
					
					
				}
				
				
			});
			
			
			
		}
		
		
		
		function verdetalleaprobados(idtrab){
			console.log("listo para ver detalle "  + idtrab);
			$('#modal_review_aprobados').openModal()
			$
			.get(
					gth_context_path + '/vacaciones/gestionar_programa/detalleaprobados', {idtrab : idtrab},
					function(obj) {
						console.log(JSON.stringify(obj[0]));
						var s = '';
						var emp = obj[0];
						
						var m = '';
						m += '<p> <i class="small mdi-social-person small cyan-text text-darken-2"'
							m += 'style="margin-right: 6%; vertical-align: -8px;"></i>' + obj[0].NO_TRABAJADOR
								m += '</p>'
									 m += '<p> <i class="small mdi-action-home small cyan-text text-darken-2"'
										 m += 'style="margin-right: 6%;vertical-align: -8px;"></i>'  +  obj[0].NO_AREA
											 m += '</p>'
								
						$("#column1_ap").html(m);
						var n = '';
						n += '<p>'
							n += '<i'
								n += '	class="small mdi-social-person-outline small cyan-text text-darken-2"'
									n += 'style="margin-right: 6%;vertical-align: -8px;"></i>' + obj[0].AP_PATERNO +' ' + obj[0].AP_MATERNO
									n += '</p>'
									 n += '<p> <i	class="small mdi-action-home small cyan-text text-darken-2"'
										 n += 'style="margin-right: 6%;vertical-align: -8px;"></i> ' + obj[0].NO_SECCION
					
												 n += '</p>	'
						 $("#column2_ap").html(n);
						console.log("bucle adentro")
						
						for (var i in obj) {
							s += '<tr>';
							s += '<td class="hide">'
									+ obj[i].ID_DET_VACACIONES
									+ '</td>';
							s += '<td style="text-align: center;">' + obj[i].FECHA_INICIO +'</td>';
							s += '<td style="text-align: center;">' + obj[i].FECHA_FIN + '</td>';
							s += '<td style="text-align: center;">' + obj[i].DIAS + '</td>';
							s += '</tr>';

						}
						
						$("#tabla-detalle_ap").empty();
						$("#tabla-detalle_ap").append(createTable5());
						$("#data5").empty();
						$("#data5").append(s);
						$('#data-table-row-grouping5').dataTable({
							"pageLength" : 3,
							"bPaginate" : true,
							"bLengthChange" : false,
							"bFilter" : false,
							"bInfo" : false,
							"bAutoWidth" : true,
							"language" : {
								// "lengthMenu": "Display _MENU_ records per page",
								"zeroRecords" : "Nada para mostrar - disculpe",
								"info" : "Mostrando página _pag_ de _pags_",
								"infoEmpty" : "Ningún alumno agregado"
							// "infoFiltered": "(filtered from _MAX_ total records)"
							}
					
							});
						console.log("truinnng")
						
					});
		}
		
		function createTable5() {//crea tabla 5 para ver detalle de trabajadores aprobados
			var s = '<table id="data-table-row-grouping5" class="display" cellspacing="0" width="100%">';
			s += '<thead>';
			s += '<tr>';
			s += '<th class="hide" >id</th>';
			s += '<th style="text-align: center;">Fecha Inicio</th>';
			s += '<th style="text-align: center;">Fecha Fin</th>';
			s += '<th style="text-align: center;">Total de Días</th>';
			s += ' </tr>';
			s += '</thead>';
			s += '<tbody id="data5"></tbody>';
			s += '</table>';
			return s;

		};
		
		
		
		