<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
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

	</div>
	<div id="main">
		<div class="wrapper">
			<%@include file="../../../jspf/aside_left.jspf"%>
			<section id="content"></section>
		</div>
		<%@include file="../../../jspf/info_puesto.jspf"%>
		<div id="table-datatables">
			<h4 class="header">Aprobar Programa de Vacaciones</h4>

			<div id="cuerpo" class="row">
				<div id="contTable" class="col s12 m12 l9"></div>

				<!-- Modal Structure -->

				<!-- End of Modal Structure -->
				<input id='usuario' class='hide' />
				<div class="col s24 m12 l6">
					<p>
						<a
							class="btn btn-large waves-effect waves-light light-green darken-4"
							id="enviar" type="submit">Confirmar</a>
					</p>
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
		// 		$("#enviar").click(
		// 				function(e) {
		// 					alert('hola');
		// 					e.preventDefault();
		// 					alert(e.preventDefault());
		// 					var currentRow = $(this).closest("tr");
		// 					alert(currentRow);
		// 					var data = $('#data-table-row-grouping').dataTable().row(currentRow).data();
		// 					alert(data);
		// 					console.log(data['det_vac']);
		// 					alert(data['det_vac']);
		// 				});
		$('#enviar').click(function() {
			arrid = getSelected();
			alert("Planilla programada! " + arrid + $('#usuario').val());

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
		$("#enviar").click(function() {
			arrid = getSelected();
			console.log(arrid);
			// 				for (var i = 0; i < arrid.length; i++) {
			// 				}
		});

		function listar() {
			$
					.get(
							"programa_vacaciones/get",
							function(obj) {
								var s = "";
								var element = "";
								var emp = obj[0];
								var con = "";
								var array = [];
								for ( var c in obj) {
									if (obj[c].LI_CONDICION == 1) {
										con = "CONTRATADO";
									}
									if (obj[c].LI_CONDICION == 2) {
										con = "EMPLEADO";
									}
									if (obj[c].LI_CONDICION == 3) {
										con = "MISIONERO";
									}
									if (obj[c].LI_CONDICION == 4) {
										con = "MFL, Practicas Pre -- Profesionales";
									}
									if (obj[c].LI_CONDICION == 5) {
										con = "MFL, Practicas Profesionales";
									}
									if (obj[c].LI_CONDICION == 6) {
										con = "MFL, CLJ, Convenio laboral Juvenil";
									}
									if (obj[c].LI_CONDICION == 7) {
										con = "MFL -- Contrato";
									}
								}
								for (var i = 0; i < obj.length; i++) {
									s += "<tr><td class='hide' id='id_det' >";
									s += obj[i].ID_DET_VACACIONES;
									s += "</td><td id='idtrab'>";
									s += obj[i].ID_TRABAJADOR;
									s += "</td><td>";
									s += obj[i].AP_PATERNO;
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
									s += "<input type='checkbox' id='test"+i+"'>";
									s += " <label for='test"+i+"'></label>\r\n";
									s += "</p></td>\r\n";
									s += "<td>";
									s += "<button id='"
											+ obj[i].ID_TRABAJADOR
											+ obj[i].NO_TRABAJADOR
											+ "' class=' waves-effect waves-light btn modal-trigger light-blue getid'  class='checkBoxClass' value='"
// 											+ "id:"
											+ obj[i].ID_DET_VACACIONES
// 											+ " "
// 											+ "nombre:"
// 											+ obj[i].NO_TRABAJADOR
											+ "'  onclick='preba();'>&#128065;</button>";
									s += "</td>";
									s += "</tr>";
								}
								$
										.getJSON(
												gth_context_path
														+ '/components',
												"opc=usuario",
												function(objJSON) {
													if (objJSON !== null) {
														var q = '';
														q = objJSON.usuario;
														$("#usuario").val('');
														$("#usuario").val(q);
													} else {
														console
																.error("No se esta cargando la información");
													}
												});
								$("#contTable").empty();
								$("#contTable").append(createTable00001());
								$("#data").empty();
								$("#data").append(s);
								// 								$("#data-table-row-grouping").dataTable();
								$("#data-table-row-grouping").dataTable({
									"columnDefs" : [ {
										"targets" : 2,
										"data" : "nombre"
									} ]
								});

								$("#ckbCheckAll").click(
										function() {
											$(".checkBoxClass").prop('checked',
													$(this).prop('checked'));
										});

								// 								$('#enviar').click(function() {
								// 									console.log(array);
								// 								});

								// 								function getSelected() {
								// 									var allVals = [];
								// 									$('#data :checked').each(
								// 											function() {
								// 												allVals.push($(this).parents(
								// 														"#data tr").find(
								// 														".sorting_1").text());
								// 											});
								// 									return allVals;
								// 								}
								// 								$("#enviar").click(function() {
								// 									arrid = getSelected();
								// 									console.log(arrid)
								// 									for (var i = 0; i < arrid.length; i++) {
								// 									}
								// 								});
							});

		};

		function createTable00001() {
			var s = "<table id='data-table-row-grouping' class='display' cellspacing='0' width='100%'>";
			s += "<thead>";
			s += "<tr>";
			s += "<th class='hide' >det_vac</th>";
			s += "<th>N°</th>";
			s += "<th>Nombres</th>";
			s += "<th>Sección</th>";
			s += "<th>Dias Totales</th>";
			s += "<th>DNI</th>";
			s += "<th>FEC INI</th>";
			s += "<th>FEC FIN</th>";
			s += "<th>Condicion</th>";
			s += "<th>Aprobar</th>";
			s += "<th>Observacion</th>";
			s += " </tr>";
			s += "</thead>";
			s += "<tbody id='data'></tbody>";
			s += "</table>";
			return s;

		};
		function preba() {
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
			// 											}
			$("#cuerpo").append(
					createModal(
							$('#data-table-row-grouping').DataTable().row(
							$(this).parents(
							"tr").find("td")
							.eq(1)
							.find("#idtrab")
							.text())));
			// 			$("#data").on('click', '#select', function(e) {
			// 				e.preventDefault();
			// 				$(this).closest("tr").find("td:eq(1)").text()
			// 			})
//				$('#data-table-row-grouping').DataTable().row(
//						$(this).closest("tr").find("td:eq(3)").text())
			// 					$('#data-table-row-grouping').DataTable().row($(this).closest("tr"))
			// 					$('#data-table-row-grouping').DataTable().row($(this).closest("tr")).data()['nombre']
			
			$("#modal2").openModal();
			// 										});
		};

		function createModal(id) {
			var s = "<td><div id='modal2' class='modal'>\r\n";
			s += "<div class='modal-content'>\r\n";
			s += "						<center>\r\n";
			s += "	<h4>Observacion</h4>\r\n";
			s += "						</center>\r\n";
			s += "	<div class='row'>\r\n";
			s += "	<form class='col s12'>\r\n";
			s += "	<div class='row'>\r\n";
			s += "	<div class='input-field col s12'>\r\n";
			s += "<textarea id='textarea1' class='materialize-textarea'></textarea>\r\n";
			s += "	<label for='textarea1'>Escriba Observacion para: ";
			s += id;
			s += "</label>\r\n";
			s += "	</div>\r\n";
			s += "								</div>\r\n";
			s += "</form>\r\n";
			s += "						</div>\r\n";
			s += "</div>\r\n";
			s += "					<div class='modal-footer'>\r\n";
			s += "	<a href='#!'";
			s += "	class='modal-action modal-close waves-effect waves-green btn-flat'>OK!</a>\r\n";
			s += "</div>\r\n" + "				</div></td>";
			return s;
		};
		
		$("#enviar").click(function(){
			alert("sfdfd");
			var usuario = $("#usuario").val();
			var id_det = $(".getid").val();
			
			$.post("programa_vacaciones/guardarAprovar",{usuario:usuario,id_det:id_det},function (data) {
		         if(data=='1'){
		        	 alert("insertado");                          
		         }
		     });
		});
	</script>
	<%@include file="../../../resources/js/businessCore/jsAutorizar.jspf"%>

</body>
</html>