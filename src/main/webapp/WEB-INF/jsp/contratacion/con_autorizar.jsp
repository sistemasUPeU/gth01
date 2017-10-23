<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">

<head>
<%@include file="../../../jspf/general.jspf"%>
<link href="<c:url value='/resources/js/plugins/prism/prism.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="<c:url value='/resources/js/plugins/data-tables/css/jquery.dataTables.min.css'></c:url>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value='/resources/js/plugins/chartist-js/chartist.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
</head>

<body>
	<%@include file="../../../jspf/header.jspf"%>
	<div id="loader-wrapper">
		<div id="loader"></div>
		<div class="loader-section section-left"></div>
		<div class="loader-section section-right"></div>
	</div>
	<div id="main">
		<div class="wrapper">
			<%@include file="../../../jspf/aside_left.jspf"%>
			<section id="content"></section>
		</div>
		<%@include file="../../../jspf/info_puesto.jspf"%>
		<div id="table-datatables" class="card-panel">
			<div class="col s12 m8 l9 contT"></div>
		</div>
	</div>
	<%@include file="../../../jspf/footer.jspf"%>
	<script
		src="<c:url value='/resources/js/plugins/prism/prism.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/plugins/data-tables/js/jquery.dataTables.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/plugins/data-tables/data-tables-script.js'></c:url>"
		type="text/javascript"></script>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$
									.getJSON(
											gth_context_path
													+ "/autorizacion/listas",
											"opc=con_autorizar",
											function(objJson) {
												var lista = objJson.List_id_Autorizacion;
												if (lista.length > 0) {
													console.log(lista);
													var s = "";
													for (var i = 0; i < lista.length; i++) {
														var a = parseInt(i) + 1;
														var MFL = parseInt(lista[i].ES_MFL);
														var Motivo = parseInt(lista[i].LI_MOTIVO);
														var plazo = parseInt(lista[i].VAL_PLAZO);
														var fe_creacion = new Date(
																lista[i].FE_CREACION);
														var p = "";
														var f = "";
														var t = "";
														var ct = "";
														(Motivo === 1) ? p = "Trabajador Nuevo"
																: ((Motivo === 2) ? p = "Renovación"
																		: p = "No Registrado");
														(MFL === 1) ? f = "Si"
																: f = "No";
														(plazo === 1) ? t = "Cumplio Plazo"
																: t = "No Cumplio";
														(plazo === 1) ? ct = "green accent-3"
																: ct = "red darken-1";
														s += '<tr>';
														s += '<td>' + a
																+ '</td>';
														s += '<td><button class="btn dropdown-button green accent-3" data-activates="dropdown1">';
														s += '<i class="mdi-action-settings"></i>';
														s += '</button>';

														s += '<ul id="dropdown1" class="dropdown-content">';
														s += '<li><a href="#!" class="-text">Proceso</a>';
														s += '</li>';
														s += '<li><a href="#!" class="-text">Horario</a>';
														s += '</li>';
														s += '<li><a href="#!" class="-text">Documentos</a>';
														s += '</li>';
														s += '<li><a href="#!" class="-text">Comentario</a>';
														s += '</li>';
														s += '<li><a href="#!" class="-text">Contrato</a>';
														s += '</li>';
														s += '<li class="divider"></li>';
														s += '<li><a href="#!" class="-text">Autorizar</a>';
														s += '</li>';
														s += '</ul></td>';

														s += '<td>'
																+ lista[i].MES_PLAZO
																+ '</td>';
														s += '<td class=""><img width="30"  height="30" src="<c:url value="/resources/img/user.png"/>" alt="Usuario"></td>';
														s += '<td>'
																+ lista[i].AP_MATERNO
																+ ' '
																+ lista[i].AP_MATERNO
																+ ' '
																+ lista[i].NO_TRABAJADOR
																+ '</td>';
														s += '<td>'
																+ lista[i].NO_PUESTO
																+ '</td>';
														s += '<td>'
																+ lista[i].NO_AREA
																+ '</td>';
														s += '<td>'
																+ lista[i].NO_DEP
																+ '</td>';
														s += '<td>'
																+ lista[i].NO_REQ
																+ '</td>';
														s += '<td><a class="green-text accent-3" href="#">'
																+ lista[i].DE_PASOS
																+ '</a></td>';
														s += '<td>'
																+ new Date(
																		lista[i].FE_CREACION)
																+ '</td>';
														s += '<td>' + p
																+ '</td>';
														s += '<td>' + f
																+ '</td>';
														s += '<td><span class="task-cat '+ct+'">'
																+ t
																+ '</span></td>';
														s += '<td><input placeholder="Código" id="first_name" type="text" class="validate"></td>';
														s += '</tr>';
													}
													var r = createTable("DPT-0019","ROL-0007");
													$(".contT").empty();
													$(".contT").append(r);
													$("#dataReq").empty();
													$("#dataReq").append(s);
													$("#data-table-simple")
															.DataTable();
													$('.dropdown-button')
															.dropdown(
																	{
																		inDuration : 300,
																		outDuration : 225,
																		constrainWidth : false, // Does not change width of dropdown to that of the activator
																		hover : true, // Activate on hover
																		gutter : 0, // Spacing from edge
																		belowOrigin : false, // Displays dropdown below the button
																		alignment : 'left', // Displays dropdown with edge aligned to the left of button
																		stopPropagation : false
																	// Stops event propagation
																	});

												} else {
													console.log("no hay datos");
												}
											});
						});

		function createTable(idDepartamento, idRol) {
			var Rol = idRol.toString();
			var Departamento = idDepartamento.toString();
			var s = '<table id="data-table-simple" class="responsive-table display light italic"';
				s+='cellspacing="0">';
			s += '<thead>';
			s += '<tr>';
			s += '<th>N°</th>';
			s += '<th>Acción</th>';
			s += '<th>Mes</th>';
			s += '<th>Foto</th>';
			s += '<th>Apellidos y Nombres</th>';
			s += '<th>Puesto</th>';
			s += '<th>Area</th>';
			s += '<th>Departamento</th>';
			s += '<th>Requerimiento</th>';
			s += '<th>Descripción</th>';
			s += '<th>Fecha de Creación</th>';
			s += '<th>Motivo</th>';
			s += '<th>MFL</th>';
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
	</script>
<body>
</html>