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
			<section id="content"></section>
		</div>
		<%@include file="../../../jspf/info_puesto.jspf"%>

		<div class="container">
			<div class="row">
				<!-- 				<div class="input-field col s6"> -->
				<!-- 					<i class="mdi-action-search prefix"></i> <input -->
				<!-- 						id="searchTrabajador" type="text" class="validate" -->
				<!-- 						onkeyup="searchTrabajador()"> <label -->
				<!-- 						for="searchTrabajador">Trabajador</label> -->
				<!-- 				</div> -->
				<div class="input-field col s6">
					<a class="waves-effect waves-light btn-large"
						onclick="window.print()"><i class="mdi-action-print right"></i>Imprimir</a>
				</div>
				<div id="cuerpo" class="container">
					<div id="contTable" style="margin: 0 1% 0 1%;"></div>

					<br> <a class="btn waves-effect waves-light right"
						id="confirmar"><i class="mdi-navigation-check"></i> Confirmar</a>
				</div>

				<div id="modal" class="modal">
					<div class="modal-content">
						<h4>Control de firmas</h4>
						<div class="row">
							<div id="contenedor_fechas"></div>
							<div class="col s4">
								<br> <a id="guardar"
									class="btn waves-effect waves-light light-green darken-3  modal-action modal-close"><i
									class="mdi-content-save"></i>Guardar</a>
							</div>
							<div class="col s6">
								<div class="file-field input-field">
									<div class="btn">
										<span>Solicitud</span> <input type="file">
									</div>
									<div class="file-path-wrapper">
										<input class="file-path validate" type="text">
									</div>
								</div>
							</div>
							<div class="col s6">
								<div class="file-field input-field">
									<div class="btn">
										<span>Papeleta</span> <input type="file">
									</div>
									<div class="file-path-wrapper">
										<input class="file-path validate" type="text">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
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
			listar();

		});

		function getSelected() {
			var allVals = [];
			$('#data :checked').each(
					function() {
						allVals.push($(this).parents("#data tr").find(
								".sorting_1").text());
					});
			return allVals;
		}

		$("#contTable")
				.on(
						"click",
						"#open",
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
												$("#contenedor_fechas").empty();
												var j = '';
												var fechas = document
														.getElementById("contenedor_fechas");
												for (var i = 0; i < obj.length; i++) {
													j += '<div class="col s4">';
													j += '<p>Fecha Inicio</p>';
													j += '<input value="'+obj[i].FECHA_INICIO+'" disabled type="text">';
													j += '</div>';
													j += '<div class="col s4">';
													j += '<p>Fecha Fin</p>';
													j += '<input value="'+obj[i].FECHA_FIN+'" disabled type="text">';
													j += '</div>';

												}
												fechas.innerHTML += j;
											});
							$("#modal").openModal();
						});

		function listar() {
			$
					.get(
							"consolidado/get",
							function(obj) {
								var s = "";
								var emp = obj[0];
								for (var i = 0; i < obj.length; i++) {
									s += "<tr><td class='hide' id='id_det' >";
									s += obj[i].ID_DET_VACACIONES;
									s += "</td><td>";
									s += obj[i].AP_PATERNO + " "
											+ obj[i].AP_MATERNO;
									s += ", ";
									s += obj[i].NO_TRABAJADOR;
									s += "</td><td>";
									s += obj[i].FECHA_INICIO;
									s += "</td><td>";
									s += obj[i].FECHA_FIN;
									s += "</td><td>";
									s += obj[i].NU_VAC;
									s += "</td>";
									s += '<td><button id="open" class="btn-floating waves-effect waves-light light-blue accent-4 material-icons dp48" href="#modal" name="'+obj[i].ID_DET_VACACIONES+'">&#119946;</button></td>';
									s += "<td><p style='text-align: center;'>";
									s += "<input type='checkbox' class='checkBoxClass' id='test"+i+"'>";
									s += " <label for='test"+i+"'></label>\r\n";
									s += "</p></td>\r\n";
									s += "</tr>";

								}
								$("#contTable").empty();
								$("#contTable").append(createTable00001());
								$("#data").empty();
								$("#data").append(s);
								$("#data-table-row-grouping").dataTable();

								$("#ckbCheckAll").click(
										function() {
											$(".checkBoxClass").prop('checked',
													$(this).prop('checked'));
										});
							});

		};

		function createTable00001() {
			var s = "<table id='data-table-row-grouping' class='display bordered highlight centered' >";
			s += "<thead>";
			s += "<tr>";
			s += "<th class='hide' >det_vac</th>";
			s += "<th>Nombres</th>";
			s += "<th>Fecha-Entrada</th>";
			s += "<th>Fecha-Salida</th>";
			s += "<th>Dias Totales</th>";
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

		function preba() {
			$("#cuerpo").append(createModal());
			$("#modal2").openModal();
		};

		function createModal() {
			var s = "<div id=\"modal2\" class=\"modal\">\r\n";
			s += "<div class=\"modal-content\">\r\n";
			s += "<h4>Modificar fecha de inicio y fecha fin</h4>\r\n";
			s += "<p>Seleccione fecha:</p>\r\n";
			s += "<div class=\"row\">\r\n";
			s += "<div class=\"col s8 m6 l6\">\r\n";
			s += "<p>Seleccione fecha de inicio:</p>\r\n";
			s += "<input type=\"text\" class=\"datepicker\">\r\n";
			s += "</div>\r\n";
			s += "<div class=\"col s8 m6 l6\">\r\n";
			s += "<p>Seleccione fecha de fin:</p>\r\n";
			s += "<input type=\"text\" class=\"datepicker\">\r\n";
			s += "</div>\r\n";
			s += "</div>\r\n";
			s += "</div>\r\n";
			s += "<div class=\"modal-footer\">\r\n";
			s += "<a href=\"#!\"\r\n";
			s += "	class=\"modal-action modal-close waves-effect waves-green btn-flat\">Modificar</a>\r\n";
			s += "</div>\r\n" + "</div>";
			return s;
		}

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
											"vacaciones/consolidado/guardarAprovarConsolidado123?"
													+ datos,
											null,
											function(data) {
												if (data == 1) {
													Materialize
															.toast(
																	'Felicidades!!, ha aprobado a sus trabajadores',
																	3000,
																	'rounded');
													listar();
												} else {
													Materialize
															.toast(
																	'UPS!!, No se ha registrado su aprobacion, verifique si chequeó los datos!',
																	3000,
																	'rounded');
												}
											});
						});

		$(document).ready(function() {

			$("#imprimir").click(function() {
				var pdf = new jsPDF('portrait', 'pt', 'a3');

				var options = {
					pagesplit : true
				};
				// var width = 400;
				pdf.addHTML($("#Hola"), options, function() {
					pdf.save('HOLA.pdf');
				});
			});
		});
	</script>
</body>
</html>