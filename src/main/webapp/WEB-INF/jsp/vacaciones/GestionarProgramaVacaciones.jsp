<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
<!-- 			<section id="content"></section> -->
		</div>
		<%@include file="../../../jspf/info_puesto.jspf"%>

		<div class="container">
			<div class="row">
				<!-- <div class="input-field col s6">
					<i class="mdi-action-search prefix"></i> <input
						id="searchTrabajador" type="text" class="validate"
						onkeyup="searchTrabajador()"> <label
						for="searchTrabajador">Trabajador</label>
				</div>
				<div class="input-field col s6">
					<i class="mdi-action-search prefix"></i> <input
						id="searchDepartamento" type="text" class="validate"
						onkeyup="searchDepartamento()"> <label
						for="searchDepartamento">Departamento</label>
				</div>
				<table id="table_trabajador-filtrado"
					class="bordered highlight centered">
					<thead>
						<tr>
							<th>Apellidos y Nombres</th>
							<th>Departamento</th>
							<th>Área</th>
							<th>Sección</th>
							<th>Condición</th>
						</tr>
					</thead>
				</table> -->
				<div id="table_contenido" class="col s12 m12 l12"></div>
				<br> <a id="confirmar_lista"
					class="btn waves-effect waves-light right"><i
					class="mdi-navigation-check"></i> Confirmar</a>
			</div>
			<div id="modal2" class="modal">
				<div class="modal-content">
					<h4>Modificar fecha de inicio y fecha fin</h4>
					<p>Seleccione fecha:</p>
					<div class="row">
						<div class="col s8 m6 l6">

							<p>Seleccione fecha de inicio:</p>
							<input type="text" class="datepicker">
						</div>
						<div class="col s8 m6 l6">
							<p>Seleccione fecha de fin:</p>
							<input type="text" class="datepicker">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<a href="#!"
						class="modal-action modal-close waves-effect waves-green btn-flat">Modificar</a>
				</div>
			</div>
			<input id='username' class='hide' />
		</div>
	</div>

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
	$(document).ready(function() {
		listarTrabajadorFiltrado();
	})


	function getSelected() {
		var allVals = [];
		$('#data :checked').each(
				function() {
					allVals.push($(this).parents("#data tr").find(".sorting_1")
							.text());
				});
		return allVals;
	}
	$("#table_contenido").on("click", "#abrir-modal2", function() {
		$("#modal2").openModal();
	});
	$("#confirmar_lista").click(function() {
		arrid = getSelected();
		console.log(arrid);
		// 				for (var i = 0; i < arrid.length; i++) {
		// 				}
	});


	function listarTrabajadorFiltrado() {
		$
				.get(
						'GestionarProgramaVacaciones/readallProgramaVacaciones',
						function(obj) {
							var s = '';
							var emp =obj[0];
							for (var i = 0; i < obj.length; i++) {
								s += '<tr>';
								s += '<td class="hide">' + obj[i].ID_DET_VACACIONES+ '</td>';
								s += '<td>' + obj[i].NO_TRABAJADOR + ' '
										+ obj[i].AP_PATERNO + ' '
										+ obj[i].AP_MATERNO + '</td>';
								s += '<td>' + obj[i].NO_AREA + '</td>';
								s += '<td>' + obj[i].NO_SECCION + '</td>';
								s += '<td>' + obj[i].FECHA_INICIO + '</td>';
								s += '<td>' + obj[i].FECHA_FIN + '</td>';
								s += '<td><p style="text-align: center;">';
								s += '<input type="checkbox" id="test'+i+'">';
								s += '<label for="test'+i+'"></label>';
								s += '</p></td>';
								s += '<td><button class="waves-effect waves-light btn modal-trigger red" onclick="prueba();">&#128197;</button></td>';
								s += '<td><button id="abrir-modal2" class="waves-effect waves-light btn modal-trigger light-blue modal-trigger" href="#modal2">&#10000;</button></td>';
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
							$("#table_contenido").append(createTable());
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
	// 	function pruebita() {
	// 						$
	// 								.get(
	// 										"programa_vacaciones/get",
	// 										function(obj) {
	// 											for (var i = 0;i < obj.length; i++) {
	// 												if ($("#cuerpo")
	// 														.append(
	// 																createModal($('.getid')
	// 																		.attr('id'))) == obj[i].ID_TRABAJADOR
	// 														+ obj[i].NO_TRABAJADOR) {
	// 													$("#modal2").openModal();
	// 												}
	// 											}za
	// 		$("#cuerpo").append(
	// 				createModal($('#data-table-row-grouping').DataTable().row(
	// 						$(this).parents("tr").find("td").eq(1).find("#idtrab")
	// 								.text())));
	// 		console.log("3");
	// 			$("#data").on('click', '#select', function(e) {
	// 				e.preventDefault();
	// 				$(this).closest("tr").find("td:eq(1)").text()
	// 			})
	//				$('#data-table-row-grouping').DataTable().row(
	//						$(this).closest("tr").find("td:eq(3)").text())
	// 					$('#data-table-row-grouping').DataTable().row($(this).closest("tr"))
	// 					$('#data-table-row-grouping').DataTable().row($(this).closest("tr")).data()['nombre']

	// 		$("#modal2").openModal();
	// 			$('#modal2').modal('open');
	// 										});
	//};

	// 	function createModal(id) {
	// 		var s = '<div id="modal2" class="modal" >'
	// 		s += '<div class="modal-content">'
	// 		s += '<h4>Modificar fecha de inicio y fecha fin</h4>'
	// 		s += '<p>Seleccione fecha:</p>'
	// 		s += '<div class="row">'
	// 		s += '<div class="col s8 m6 l6">'

	// 		s += '<p>Seleccione fecha de inicio:</p>'
	// 		s += '<input type="text" class="datepicker">'
	// 		s += '</div>'
	// 		s += '<div class="col s8 m6 l6">'
	// 		s += '<p>Seleccione fecha de fin:</p>'
	// 		s += '<input type="text" class="datepicker">'
	// 		s += '</div>'
	// 		s += '</div>'
	// 		s += '</div>'
	// 		s += '<div class="modal-footer">'
	// 		s += '<a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Modificar</a>'
	// 		s += '</div>' + '</div>';
	// 		return s;
	// 	};
	function createTable() {
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
		s += '<th>Programar</th>';
		s += '<th>Modificar</th>';
		s += ' </tr>';
		s += '</thead>';
		s += '<tbody id="data"></tbody>';
		s += '</table>';
		return s;

	};
	$("#confirmar_lista").click(
			function() {
				arrid = getSelected();
				var username = $("#username").val();
				var id_arr = arrid;
				var id_det = id_arr.join(",");
				console.log(username);
				console.log(id_det);
				var datos = "username=" + username;
				datos += "&id_det=" + id_det;
				var con = new jsConnector();
				con.post("vacaciones/GestionarProgramaVacaciones/insertProgramaVacaciones?" + datos,
						null, function(data) {
							if (data == 1) {
								Materialize.toast('Felicidades!!, ha aprobado a sus trabajadores', 3000, 'rounded');
							} else {
								Materialize.toast('UPS!!, No se ha registrado su aprobacion, verifique si chequeó los datos!', 3000, 'rounded');
							}
						});
			});
</script>
</body>
</html>