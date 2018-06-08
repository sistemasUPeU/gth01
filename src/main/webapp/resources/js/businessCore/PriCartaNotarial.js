$(document)
		.ready(
				function() {
					if (!alertify.errorAlert) {
						alertify
								.dialog(
										'errorAlert',
										function factory() {
											return {
												build : function() {
													var errorHeader = '<span class="fa fa-times-circle fa-2x" '
															+ 'style="vertical-align:middle;color:#e10000;">'
															+ '</span> Error al guardar los datos';
													this.setHeader(errorHeader);
												}
											};
										}, true, 'alert');
					}

					// $('.modal-trigger').leanModal();
					// alert();

					listarAutorizados();
					// listarProcesados();

					$("#ProcesarR").click(function() {
						var idc = $("#idc").val();
						$.get("ProcesarR", {
							idc : idc,
							opc : 4
						}, function(data, status) {
							alert(data);
							var detalle = JSON.parse(data);

							console.log(detalle);
							if (data == 1) {
								alert("BUENA JONAS")

							} else {
								alert("NADA JONAS");
							}
						});
					});

					$("#RechazarR").click(function() {
						alert("rechaza");
					});

				});

function listarRegistrados() {
	$.getJSON(
			gth_context_path + "/renaban/primerEnvio",
			"opc=3",
			function(objJson) {
				var s = "";
				var lista = objJson;
//				console.log(objJson);
				if (lista.length > 0) {
					// alert("si hay datos causita c:");	
					for (var i = 0; i < lista.length; i++) {
						var a = parseInt(i) + 1;
						var MFL = parseInt(lista[i].ES_MFL);
						var Motivo = parseInt(lista[i].LI_MOTIVO);
						var plazo = parseInt(lista[i].VAL_PLAZO);
						var fe_creacion = new Date(
								lista[i].FECHA_RENABAN);
						var mesInt = parseInt(fe_creacion
								.getMonth()) + 1;
						var mes = ParsearMes(mesInt);
						var mfl="";
						if(lista[i].VAL_PLAZO=='1'){
							 mfl="Sí"
						}else{
							 mfl="No";
						}
						var TIPO="";
						if(lista[i].TIPO=='R'){
							 TIPO="RENUNCIA"
						}else{
							 TIPO="ABANDONO";
						}
						s += '<tr>';
						s += '<td>'
								+ a
								+ '<label  class="idc" hidden>'
								+ lista[i].ID_CONTRATO
								+ '</label></td>';
						s += '<td>'
								+ mes;
								+ '</td>';
						s += '<td class="">'
						var p = "";
						var f = "";
						var t = "";
						var ct = "";
						(Motivo === 1) ? p = "Trabajador Nuevo"
								: ((Motivo === 2) ? p = "Renovación"
										: p = "No Registrado");
						(MFL === 1) ? f = "Si"
								: f = "No";
						(plazo === 1) ? t = "Cumplió Plazo"
								: t = "No Cumplió";
						(plazo === 1) ? ct = "green accent-3"
								: ct = "red darken-1";
						

								+ lista[i].PATERNO
								+ ' '
								+ lista[i].MATERNO
								+ ' '
								+ lista[i].NOMBRES
								+ '</td>';
						s += '<td>'
								+ lista[i].NOM_PUESTO
								+ '</td>';
						s += '<td>' + lista[i].NOM_AREA
								+ '</td>';
						s += '<td>' + lista[i].NOM_DEPA
								+ '</td>';
						s += '<td>'
								+ lista[i].TIPO_CONTRATO
								+ '</td>';
						s += '<td><a class="green-text accent-3" href="#">'
								+ lista[i].DESCRIPCION
								+ '</a></td>';
						s += '<td>'
								+lista[i].FECHA_RENABAN+
								 '</td>';
						s += '<td>'
							+lista[i].DNI+
							 '</td>';
						s += '<td>'
							+mfl+
							 '</td>';
						// s += '<td>' + p + '</td>';
//						s += '<td><a class="blue-text accent-4" href="#"><b>' + lista[i].ESTADO
//								+ '</b></a></td>';
						s +='<td >' +TIPO+ '<label class="tipon" hidden>'
						+ TIPO
						+ '</label></td>';
						s += '<td><a class="notificar waves-effect waves-light btn #00e676 green accent-3">Detalle</a>';
						s += '</td>';
						s += '</tr>';
					}

				} else {
					//alert("no hay datos");
					s += "";
				}

				var r = createTable("s", "d");
				$(".contT").empty();
				$(".contT").append(r);
				$("#dataReq").empty();
				$("#dataReq").append(s);

				$("#data-table-row-grouping")
						.DataTable(
								{
								    responsive: true,
								    columnDefs: [
								        { responsivePriority: 1, targets: 0 },
								        { responsivePriority: 2, targets: -1 }
								    ],
								"pageLength" : 5,
								"bPaginate" : true,
								"ordering": false
								}
						);

				$("#data-table-row-grouping tbody").on('click','.notificar',
						function() {

							cantidad = $(this).parents(
									"tr").find("td")
									.eq(0)
									.find(".idc")
									.text();
//							console.log(cantidad);
							
							
							tipon = $(this).parents(
							"tr").find("td")
							.find(".tipon")
							.eq(0)
							.text();
							console.log("esto es tipon"+tipon);

							DetalleRenuncia(cantidad,tipon);

							$("#otros").val(cantidad);	
					
						
						});
			});
}
// Detalle de Carta Notarial
function DetalleAbandono(ida) {

	// $("#modal2").openModal();
	// $.get("details",{},function(data){
	// alert(data);
	// });
	$
			.get(
					"firstLetter",
					{},
					function(data, status) {
						// alert(data);
						// alert("BIEN JONAS");
						// $("#contenido").html("");
						$("#contenido").html(data);
						$
								.get(
										"primerEnvio",
										{
											opc : 2,
											ida : ida
										},
										function(data, status) {
											// alert(data);
											var detalle = JSON.parse(data);
											$("#idr")
													.val(detalle[0].ID_RENABAN);
											$("#nombres").text(
													detalle[0].NOMBRES);
											$("#paterno").text(
													detalle[0].PATERNO);
											$("#materno").text(
													detalle[0].MATERNO);
											$("#fecha_nac").text(
													detalle[0].FECHA_NAC);
											$("#fecha_inicio").text(
													detalle[0].FECHA_CONTRATO);
											$("#direccion").text(
													detalle[0].DOMICILIO);
											$("#departamento").text(
													detalle[0].NOM_DEPA);
											$("#area")
													.text(detalle[0].NOM_AREA);
											$("#seccion").text(
													detalle[0].NOM_SECCION);
											$("#puesto").text(
													detalle[0].NOM_PUESTO);
											// $("#centro_costo").tex(detalle[0].CENTRO_COSTO);
											$("#tipo_contrato").text(
													detalle[0].TIPO_CONTRATO);
											$("#correo")
													.text(detalle[0].CORREO);
											if (detalle[0].ANTECEDENTES != 1) {
												$("#antecedentes_policiales")
														.text("Si");
											} else {
												$("#antecedentes_policiales")
														.text("No");
											}
											// var archi = detalle[0].ARCHIVO;
											if (detalle[0].CERTI_SALUD != 0) {
												$("#certificado_salud").text(
														"Si");
											} else {
												$("#certificado_salud").text(
														"No");
											}
											// var img =
											// document.getElementById("carta")
											$("#carta")
													.text(detalle[0].ARCHIVO);
											$("#pricarta")
											$("#RechazarRenuncia").click(function(){
												var id= $("#idr").val();
												var observaciones = $("#observaciones").val();					
												 alertify.confirm('Confirmar Justificacion ', 'Esta seguro(a) de rechazar la renuncia de este trabajador?', function(){
													 $.get("primerEnvio",{opc:6,idr:id,observaciones:observaciones},function(data){
//														 alert("BIEN Nicole");
//										        		 alert(data);
//										        		 alert(id);
//										        		 alert(observaciones);
										        		 window.location.href = gth_context_path +"/renaban/PrimerEnvio";
										        	});
													 
											     	} , function(){ 
											        	
											        });
											});
										});

						// if (data.length == 0) {
						// // location.reload();
						// alert("nada de datos");
						// } else {
						//			
						// $("#nomes").text(detalle[0].NOMBRES);
						//			
						// $.get("/mostrardoc1",{
						// archi: archi
						// },function(data){
						// alert(data);
						// })
						// //
						//			
						//		
						//
						// }

					});

}

function enviarCorreo() {
//	alert(jfksdf);
	var msj = $("#mensaje1").text();
	var de = "pruebagth@gmail.com";
//	var para = $("#correo").text();
	var para = "yanetpalacios@upeu.edu.pe";
	var clave = "GTH123456";
	var mensaje = $("#cartaNotarial").val().replace(/C:\\fakepath\\/i, '')
//	var foto = $("#cartaNotarial").val().replace(/C:\\fakepath\\/i, '');
//	if (foto.search (/ C: fakepath /)) {foto = foto.replace ('C: fakepath', ''); 
//	$("#cartaNotarial").val (dominio + '/ pics /' + foto); } else {$("#cartaNotarial").val (dominio + '/ pics /' + foto); }
	var msjs = msj + $("#fecha").val() + mensaje + ".";
	var asunto = "GTH";
	alert(msjs);
	 console.log(msjs);
	$.get(gth_context_path+"/renaban/primerEnvio", {
		de : de,
		clave : clave,
		para : para,
		mensaje : msjs,
		asunto : asunto,
//		foto:foto,
		opc : 7
	}, function(data, status) {
		console.log(data);
		// $("#modalnotificar").closeModal();
		if (data == 1) {
			alert("SE MANDO");
			listarAutorizados();
			// insertarLegajo();
		} else {
			alert(" NOOOOOOOOOOOOO SE MANDO");
		}

	});
}

//$(function() {
//    $("input:file").change(function (){
//      var foto = $(this).val().replace("C:\\fakepath\\", "");
//    });
// });

function notificarAbandono() {
	var idr = $("#idr").text();
	$.get("primerEnvio", {
		idr : idr,
		opc : 8
	}, function(data, status) {
		console.log(data);
		$("#modalnotificar").closeModal();
		if (data == 1) {
			alert("NOTIFICADO :v");
			listarNotificados();
			listarAutorizados();
		} else {
			alert(" NOOOOOOOOOOOOO SE MANDO");
		}

	});
}

function listarNotificados() {
	$
			.getJSON(
					gth_context_path + "/renaban/primerEnvio",
					"opc=7",
					function(objJson) {
						// alert(objJson);
						var s = "";
						var lista = objJson;
						console.log(objJson);
						if (lista.length > 0) {
							// alert("si hay datos causita c:");

							for (var i = 0; i < lista.length; i++) {
								var a = parseInt(i) + 1;
								var MFL = parseInt(lista[i].ES_MFL);
								var Motivo = parseInt(lista[i].LI_MOTIVO);
								var plazo = parseInt(lista[i].VAL_PLAZO);
								var fe_creacion = new Date(
										lista[i].FECHA_RENUNCIA);
								var mesInt = parseInt(fe_creacion.getMonth()) + 1;
								var mes = ParsearMes(mesInt);
								var mfl = "";
								if (lista[i].VAL_PLAZO == '1') {
									mfl = "Sí"
								} else {
									mfl = "No";
								}
								var TIPO = "";
								if (lista[i].TIPO == 'A') {
									TIPO = "RENUNCIA"
								} else {
									TIPO = "ABANDONO";
								}
								var p = "";
								var f = "";
								var t = "";
								var ct = "";
								(Motivo === 1) ? p = "Trabajador Nuevo"
										: ((Motivo === 2) ? p = "Renovación"
												: p = "No Registrado");
								(MFL === 1) ? f = "Si" : f = "No";
								(plazo === 1) ? t = "Cumplió Plazo"
										: t = "No Cumplió";
								(plazo === 1) ? ct = "green accent-3"
										: ct = "red darken-1";
								s += '<tr>';
								s += '<td>' + a + '<label  class="ida" hidden>'
										+ lista[i].ID_CONTRATO
										+ '</label></td>';
								s += '<td>' + mes;
								+'</td>';
								s += '<td class="">'

								+ lista[i].PATERNO + ' ' + lista[i].MATERNO
										+ ' ' + lista[i].NOMBRES + '</td>';
								s += '<td>' + lista[i].NOM_PUESTO + '</td>';
								s += '<td>' + lista[i].NOM_AREA + '</td>';
								s += '<td>' + lista[i].NOM_DEPA + '</td>';
								s += '<td>' + lista[i].TIPO_CONTRATO + '</td>';
								s += '<td><a class="green-text accent-3" href="#">'
										+ lista[i].DESCRIPCION + '</a></td>';
								s += '<td>' + lista[i].FECHA_RENUNCIA + '</td>';
								s += '<td>' + lista[i].DNI + '</td>';
								s += '<td>' + mfl + '</td>';
								// s += '<td>' + p + '</td>';
								s += '<td>' + lista[i].ESTADO + '</td>';
								s += '<td>' + TIPO + '</td>';
								s += '<td><button class="notificar waves-effect waves-light btn modal-trigger #00e676 green accent-3" >Detalle</button>';
								s += '</button>';
								s += '</tr>';
							}

						} else {
							// alert("no hay datos");
							s += "";
						}

						var r = createTable("s", "d");
						$(".contT").empty();
						$(".contT").append(r);
						$("#dataReq").empty();
						$("#dataReq").append(s);
						$("#data-table-row-grouping").DataTable();

						$(".notificar").click(
								function() {

									cantidad = $(this).parents("tr").find("td")
											.eq(0).find(".ida").text();
									console.log(cantidad);

									DetalleAbandono(cantidad);

									$("#otros").val(cantidad);
								});
					});
}

function createTable(idDepartamento, idRol) {
	var Rol = idRol.toString();
	var Departamento = idDepartamento.toString();
	var s = '<table id="data-table-row-grouping" class="display" cellspacing="0" width="100%" style="position:relative;font-size:14px"';
	s += 'cellspacing="0">';
	s += '<thead>';
	s += '<tr>';
	s += '<th>N</th>';
	s += '<th>Mes</th>';
	s += '<th>Apellidos y Nombres</th>';
	s += '<th>Puesto</th>';
	s += '<th>Area</th>';
	s += '<th>Departamento</th>';
	s += '<th>Tipo de Contrato</th>';
	s += '<th>Descripcion</th>';
	s += '<th>Fecha de renuncia</th>';
	s += '<th>DNI</th>';
	s += '<th>MFL</th>';
	s += '<th>Estado</th>';
	s += '<th>Tipo</th>';
	s += '<th>Opcion</th>';
	if (Departamento === "DPT-0019") {
		s += '<th>¿Cumplió Plazos?</th>';
		if (Rol === "ROL-0006") {
			s += '<th>¿Contrato Elaborado?</th>';
			s += '<th>¿Firmo Contrato?</th>';
			s += '<th>Enviar a Rem.</th>';
			s += '<th>¿Contrato Subido?<</th>';
		}
	}
	if (Rol === "ROL-0009") {
		s += '<th>Código APS</th>';
	}
	if (Rol === "ROL-0007" || Rol === "ROL-0001") {
		s += '<th>Código Huella</th>';
	}
	s += '</tr>';
	s += '</thead>';
	s += '<tbody id="dataReq">';
	s += '</tbody>';
	s += '</table>';
	return s;
}
$('.datepicker').pickadate({
	selectMonths : true, // Creates a dropdown to control month
	selectYears : 15
// Creates a dropdown of 15 years to control year
});

window.picker = $('.datepicker').pickadate({
	selectMonths : true, // Creates a dropdown to control month
	selectYears : 100, // Creates a dropdown of 15 years to control year
	format : 'dd/mm/yyyy'
});
$("#Date").val('SYSDATE');

function id(ida) {
	alert(ida);
}

function ParsearMes(mesint) {
	var mes;
	console.log(mesint);
	switch (mesint) {
	case 01:
		mes = "ENE";
		break;
	case 02:
		mes = "FEB";
		break;
	case 03:
		mes = "MAR";
		break;
	case 04:
		mes = "ABR";
		break;
	case 05:
		mes = "MAY";
		break;
	case 06:
		mes = "JUN";
		break;
	case 07:
		mes = "JUL";
		break;
	case 08:
		mes = "AGO";
		break;
	case 09:
		mes = "SET";
		break;
	case 10:
		mes = "OCT";
		break;
	case 11:
		mes = "NOV";
		break;
	case 12:
		mes = "DIC";
		break;
	}
	return mes;

}