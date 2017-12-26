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
				<div class="row">
					<div class="col s12">
						<ul class="tabs tab-demo z-depth-1" style="width: 100%;">
							<li class="tab col s3"><a class="active" href="#test001">Test
									1</a></li>
							<li class="tab col s3"><a href="#test002" class="">Test
									2</a></li>
						</ul>
					</div>
					<div class="col s12">
						<div id="test001" class="col s12" style="display: block;">
							<div class="input-field col s6">
								<a class="waves-effect waves-light btn-large"
									onclick="window.print()"><i class="mdi-action-print right"></i>Imprimir</a>
							</div>
							<div id="cuerpo" class="container">
								<div id="contTable" style=""></div>

								<br> <a class="btn waves-effect waves-light right"
									id="confirmar"><i class="mdi-navigation-check"></i>
									Confirmar</a>

								<div class="row">
									<div id="table_contenido" class="col s12 m12 l12"></div>
								</div>
							</div>
						</div>
						<div id="test002" class="col s12" style="display: none;">
							
						</div>
					</div>
				</div>

				<div class="container">

					<div id="modal" class="modal">
						<div class="modal-content">
							<h4>Control de firmas</h4>
							<div class="row">
								<div id="contenedor_fechas"></div>
								<div class="col s4" style="text-align: center;">
									<br> <br> <a id="guardar"
										class="btn waves-effect waves-light modal-action modal-close"><i
										class="mdi-content-save"></i> Guardar</a>
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

		var z;

		$("#modal").on("click", ".check", function() {
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

		$("#guardar")
				.click(
						function() {
							var con = new jsConnector();
							console.log(z);
							if (z == 1) {
								var a, p, q;
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
								con
										.post(
												'vacaciones/consolidado/updateFirma?'
														+ "id=" + a
														+ "&inicio=" + p
														+ "&fin=" + q,
												null,
												function(data) {
													console.log(data);
													if (data == 1) {
														if (p == 0 && q == 0) {
															Materialize
																	.toast(
																			'Ninguna firma se ha actualizado!',
																			3000,
																			'rounded');
														} else {
															Materialize
																	.toast(
																			'Firma actualizada correctamente!',
																			3000,
																			'rounded');
														}
													} else {
														Materialize
																.toast(
																		'No se actualizaron las firmas, consulte con su jefe!',
																		3000,
																		'rounded');
													}
												});
							}
							if (z == 2) {
								var a, b, p, q, r, s;
								$(".det").each(function() {
									if ($(this).val() == 1) {
										a = $(this).attr("name");
									}
									if ($(this).val() == 2) {
										b = $(this).attr("name");
									}
								});
								$(".check").each(function() {
									if ($(this).val() == 1) {
										p = $(this).attr("name");
									}
									if ($(this).val() == 2) {
										q = $(this).attr("name");
									}
									if ($(this).val() == 3) {
										r = $(this).attr("name");
									}
									if ($(this).val() == 4) {
										s = $(this).attr("name");
									}
								});
								console.log("***");
								console.log(a);
								console.log(b);
								console.log(p);
								console.log(q);
								console.log(r);
								console.log(s);
								console.log("***");
								$.get('updateFirma', {
									id : a,
									inicio : p,
									fin : q
								}, function(data) {
									console.log(data);
								});
								$.get('updateFirma', {
									id : b,
									inicio : r,
									fin : s
								}, function(data) {
									console.log(data);
								});
							}
							if (z == 3) {
								var a, b, c, p, q, r, s, t, u;
								$(".det").each(function() {
									if ($(this).val() == 1) {
										a = $(this).attr("name");
									}
									if ($(this).val() == 2) {
										b = $(this).attr("name");
									}
									if ($(this).val() == 3) {
										c = $(this).attr("name");
									}
								});
								$(".check").each(function() {
									if ($(this).val() == 1) {
										p = $(this).attr("name");
									}
									if ($(this).val() == 2) {
										q = $(this).attr("name");
									}
									if ($(this).val() == 3) {
										r = $(this).attr("name");
									}
									if ($(this).val() == 4) {
										s = $(this).attr("name");
									}
									if ($(this).val() == 5) {
										t = $(this).attr("name");
									}
									if ($(this).val() == 6) {
										u = $(this).attr("name");
									}
								});
								console.log("***");
								console.log(a);
								console.log(b);
								console.log(c);
								console.log(p);
								console.log(q);
								console.log(r);
								console.log(s);
								console.log(t);
								console.log(u);
								console.log("***");
								$.get('updateFirma', {
									id : a,
									inicio : p,
									fin : q
								}, function(data) {
									console.log(data);
								});
								$.get('updateFirma', {
									id : b,
									inicio : r,
									fin : s
								}, function(data) {
									console.log(data);
								});
								$.get('updateFirma', {
									id : c,
									inicio : t,
									fin : u
								}, function(data) {
									console.log(data);
								});
							}
						});

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
												var k = 0;
												var fechas = document
														.getElementById("contenedor_fechas");
												var n_n = 0;
												for (var i = 0; i < obj.length; i++) {
													k = k + 1;
													j += '<div class="col s3">';
													j += '<p>Fecha Inicio</p>';
													j += '<input value="'+obj[i].FECHA_INICIO+'" disabled type="text">';
													j += '</div>';
													j += '<div class="col s1">';
													j += '<br> <br>';
													n_n = n_n + 1;
													if (obj[i].FIRMA_SALIDA == 0) {
														j += '<button class="btn-floating waves-effect waves-light pink lighten-2 check" value="'+n_n+'" name="'+obj[i].FIRMA_SALIDA+'">';
														j += '<i class="mdi-navigation-close" id="i"></i>';
														j += '</button>';
														j += '</div>';
														j += '<div class="col s3">';
														j += '<p>Fecha Fin</p>';
														j += '<input value="'+obj[i].FECHA_FIN+'" disabled type="text">';
														j += '</div>';
														j += '<div class="col s1">';
														j += '<br> <br>';
														n_n = n_n + 1;
														j += '<button class="btn-floating waves-effect waves-light pink lighten-2 check" disabled value="'+n_n+'"  name="'+obj[i].FIRMA_ENTRADA+'">';
														j += '<i class="mdi-navigation-close" id="i"></i>';
														j += '</button>';
													} else if (obj[i].FIRMA_SALIDA == 1) {
														j += '<button class="btn-floating waves-effect waves-light green accent-3 check" disabled value="'+n_n+'" name="'+obj[i].FIRMA_SALIDA+'">';
														j += '<i class="mdi-navigation-check" id="i"></i>';
														j += '</button>';
														j += '</div>';
														j += '<div class="col s3">';
														j += '<p>Fecha Fin</p>';
														j += '<input value="'+obj[i].FECHA_FIN+'" disabled type="text">';
														j += '</div>';
														j += '<div class="col s1">';
														j += '<br> <br>';
														n_n = n_n + 1;
														if (obj[i].FIRMA_ENTRADA == 0) {
															j += '<button class="btn-floating waves-effect waves-light pink lighten-2 check" value="'+n_n+'"  name="'+obj[i].FIRMA_ENTRADA+'">';
															j += '<i class="mdi-navigation-close" id="i"></i>';
															j += '</button>';
														} else if (obj[i].FIRMA_ENTRADA == 1) {
															j += '<button class="btn-floating waves-effect waves-light green accent-3 check" disabled value="'+n_n+'"  name="'+obj[i].FIRMA_ENTRADA+'">';
															j += '<i class="mdi-navigation-check" id="i"></i>';
															j += '</button>';
														}
													}
													j += '</div>';
													j += '<button class="hide det" value="'+k+'" name="'+obj[i].ID_DET_VACACIONES+'"></button>';
												}

												fechas.innerHTML += j;
												z = obj.length;
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
									s += '<td><button id="open" class="btn-floating waves-effect waves-light light-blue accent-4" href="#modal" name="'+obj[i].ID_VACACIONES+'">';
									s += '<i class="mdi-image-remove-red-eye"></i></button></td>';
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
											"vacaciones/consolidado/guardarAprovarConsolidado?"
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