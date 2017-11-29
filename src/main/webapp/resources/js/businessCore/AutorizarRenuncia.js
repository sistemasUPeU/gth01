$(document)
		.ready(
				function() {

					// $('.modal-trigger').leanModal();
					// alert();
					$
							.getJSON(
									gth_context_path + "/renuncias/AutorizarR",
									"opc=3",
									function(objJson) {
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
												var mesInt = parseInt(fe_creacion
														.getMonth()) + 1;
												var mes = ParsearMes(mesInt);
												var mfl="";
												if(lista[i].VAL_PLAZO=='1'){
													 mfl="Sí"
												}else{
													 mfl="No";
												}
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
												s += '<tr>';
												s += '<td>'
														+ a
														+ '<label  class="idtr" hidden>'
														+ lista[i].ID_CONTRATO
														+ '</label></td>';
												s += '<td>'
														+ mes;
														+ '</td>';
												s += '<td class="">'

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
														+lista[i].FECHA_RENUNCIA+
														 '</td>';
												s += '<td>'
													+lista[i].DNI+
													 '</td>';
												s += '<td>'
													+mfl+
													 '</td>';
												// s += '<td>' + p + '</td>';
												s += '<td>' + lista[i].ESTADO
														+ '</td>';
												s += '<td><button class="notificar waves-effect waves-light btn modal-trigger #00e676 green accent-3" onclick="DetalleRenuncia('+lista[i].ID_CONTRATO+')">Detalle</button>';

												// s += '<td><button
												// class="notificar waves-effect
												// waves-light btn modal-trigger
												// teal"
												// onclick="id("'+lista[i].ID_TRABAJADOR+'")">Adjuntar
												// Carta de
												// Renuncia</button>';;

												// s += '<i
												// class="mdi-action-settings">Notificar</i>';
												// s += '</a>';
												s += '</button>';

												// plazo
												// if
												// (objJson.datos.usuario.ID_DEPARTAMENTO
												// === "DPT-0019") {
												// s += '<td><span
												// class="task-cat '+ct+'">'
												// + t
												// + '</span></td>';
												// }
												// if
												// (objJson.datos.usuario.ID_DEPARTAMENTO
												// === "DPT-0006") {
												//
												// }

												// s += '<td><input
												// placeholder="Código"
												// id="first_name" type="text"
												// class="validate"></td>';
												s += '</tr>';
											}

										} else {
											alert("no hay datos");
											s += "";
										}

										var r = createTable("s", "d");
										$(".contT").empty();
										$(".contT").append(r);
										$("#dataReq").empty();
										$("#dataReq").append(s);
										$("#data-table-row-grouping")
												.DataTable();

										$(".notificar").click(
												function() {

													cantidad = $(this).parents(
															"tr").find("td")
															.eq(0)
															.find(".idtr")
															.text();
													console.log(cantidad);
													
													$("#modal2").openModal();
													$("#otros").val(cantidad);

												
												});
										// $('.dropdown-button')
										// .dropdown(
										// {
										// inDuration : 300,
										// outDuration : 225,
										// constrainWidth : false,
										// hover : true,
										// gutter : 0,
										// belowOrigin : false,
										// alignment : 'left',
										// stopPropagation : false
										// });

									});
					// listar();

				});

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
function DetalleRenuncia(idc) {
	
	alert(idc);
	$.get("AutorizarR", {
		idc : idc,
		opc : 2
	}, function(data, status) {
		// alert(data);
		var detalle = JSON.parse(data);
		console.log(detalle);
		if (detalle.length == 0) {
			// location.reload();
			alert("nada de datos");
		} else {
			alert("BIEN JONAS")
//			$("#fo").hide();
//			$("#detalleR").show();
//			$("#nombres").text(detalle[0].NOMBRES);
//			$("#paterno").text(detalle[0].PATERNO);
//			$("#materno").text(detalle[0].MATERNO);
//			$("#fecha_nac").text(detalle[0].FECHA_NAC);
//			$("#fecha_inicio").text(detalle[0].FECHA_CONTRATO);
//			$("#direccion").text(detalle[0].DOMICILIO);
//			$("#departamento").text(detalle[0].NOM_DEPA);
//			$("#area").text(detalle[0].NOM_AREA);
//			$("#seccion").text(detalle[0].NOM_SECCION);
//			$("#puesto").text(detalle[0].NOM_PUESTO);
//			$("#tipo_contrato").text(detalle[0].TIPO_CONTRATO);
//			$("#idcontrato").val(detalle[0].ID_CONTRATO);
//			$("#dni").val("");
//			$("#dni").focus();
		}

	});

}

function id(idtr) {
	alert(idtr);
}

function ParsearMes(mesint) {
	var mes;
	console.log(mesint);
	switch (mesint) {
	case 01:
		mes = "Enero";
		break;
	case 02:
		mes = "Febrero";
		break;
	case 03:
		mes = "Marzo";
		break;
	case 04:
		mes = "Abril";
		break;
	case 05:
		mes = "Mayo";
		break;
	case 06:
		mes = "Junio";
		break;
	case 07:
		mes = "Julio";
		break;
	case 08:
		mes = "Agosto";
		break;
	case 09:
		mes = "Septiembre";
		break;
	case 10:
		mes = "Octubre";
		break;
	case 11:
		mes = "Noviembre";
		break;
	case 12:
		mes = "Diciembre";
		break;
	}
	return mes;

}