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
<style type="text/css">
.center-btn {
	text-align: center
}

div.dataTables_length {
	display: none !important;
}

#confirmar_lista {
	margin-top: 25px;
}
</style>
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
				<div id="table_contenido" class="col s12 m12 l12"></div>
			</div>
			<div id="modal" class="modal">
				<div class="modal-content">
					<h4 class="center-align">DETALLE DE VACACIONES</h4>
					<div id="contenedor_detalles">
						<div class="row">
							<table>
								<thead>
									<tr>
										<th> </th>
										<th>Acción</th>
										<th>Estado</th>
										<th>Fecha</th>
										<th> </th>
									</tr>
								</thead>
								<thead><tr>
									<td>
										<a id="b_programacion" class="btn-floating disabled"><i class=""></i></a>
									</td>
									<td id="t_programacion"> </td>
									<td id="s_programacion"> </td>
									<td id="f_programacion"> </td>
									<td><a id="d_programacion" class="waves-effect waves-light btn"><i class="mdi-content-content-paste"></i></a></td>
								</tr></thead>
								<thead><tr>
									<td rowspan=2>
										<a id="b_aprobacion" class="btn-floating disabled"><i class=""></i></a>
									</td>
									<td rowspan=2>APROBACIÓN:</td>
									<td id="s1_aprobacion"> </td>
									<td id="f_aprobacion" rowspan=2> </td>
									<td rowspan=2> </td>
								</tr>
								<tr>
									<td id="s2_aprobacion"> </td>
								</tr></thead>
								<thead><tr>
									<td>
										<a id="b_consolidar" class="btn-floating disabled"><i class=""></i></a>
									</td>
									<td>CONSOLIDAR:</td>
									<td id="s_consolidar"> </td>
									<td id="f_consolidar"> </td>
									<td> </td>
								</tr></thead>
								<thead><tr>
									<td>
										<a id="b_papeleta" class="btn-floating disabled"><i class=""></i></a>
									</td>
									<td>PAPELETA:</td>
									<td id="s_papeleta"> </td>
									<td id="f_papeleta"> </td>
									<td><a id="d_papeleta" class="waves-effect waves-light btn disabled"><i class="mdi-content-content-paste"></i></a></td>
								</tr></thead>
								<thead><tr>
									<td rowspan=2>
										<a id="b_control" class="btn-floating disabled"><i class=""></i></a>
									</td>
									<td rowspan=2>CONTROL FIRMA:</td>
									<td id="s1_control"> </td>
									<td id="f_control" rowspan=2> </td>
									<td rowspan=2> </td>
								</tr>
								<tr><td id="s2_control"> </td></tr></thead>
<!-- 								<thead><tr> -->
<!-- 									<td> -->
<!-- 										<a id="" class="btn-floating green accent-3"><i class="mdi-action-done"></i></a> -->
<!-- 									</td> -->
<!-- 									<td>PROGRAMACIÓN:</td> -->
<!-- 									<td>Solicitud Hecha</td> -->
<!-- 									<td>04/06/2018</td> -->
<!-- 									<td><a class="waves-effect waves-light btn"><i class="mdi-content-content-paste"></i></a></td> -->
<!-- 									<td> </td> -->
<!-- 								</tr></thead> -->
<!-- 								<thead><tr> -->
<!-- 									<td rowspan=2> -->
<!-- 										<a id="" class="btn-floating green accent-3"><i class="mdi-action-done"></i></a> -->
<!-- 									</td> -->
<!-- 									<td rowspan=2>APROBACIÓN:</td> -->
<!-- 									<td>SECRETARIA: Aprobado</td> -->
<!-- 									<td rowspan=2>04/06/2018</td> -->
<!-- 									<td rowspan=2> </td> -->
<!-- 								</tr> -->
<!-- 								<tr> -->
<!-- 									<td>JEFE: Aprobado</td> -->
<!-- 								</tr></thead> -->
<!-- 								<thead><tr> -->
<!-- 									<td> -->
<!-- 										<a id="" class="btn-floating pink lighten-2"><i class="mdi-action-label-outline"></i></a> -->
<!-- 									</td> -->
<!-- 									<td>CONSOLIDAR:</td> -->
<!-- 									<td>EN PROCESO (1/2)</td> -->
<!-- 									<td>04/06/2018</td> -->
<!-- 									<td> </td> -->
<!-- 								</tr></thead> -->
<!-- 								<thead><tr> -->
<!-- 									<td> -->
<!-- 										<a id="" class="btn-floating pink lighten-2 disabled"><i class="mdi-content-clear"></i></a> -->
<!-- 									</td> -->
<!-- 									<td>PAPELETA:</td> -->
<!-- 									<td>SIN ENTREGAR</td> -->
<!-- 									<td>04/06/2018</td> -->
<!-- 									<td><a class="waves-effect waves-light btn disabled"><i class="mdi-content-content-paste"></i></a></td> -->
<!-- 									<td> </td> -->
<!-- 								</tr></thead> -->
<!-- 								<thead><tr> -->
<!-- 									<td rowspan=2> -->
<!-- 										<a id="" class="btn-floating pink lighten-2 disabled"><i class="mdi-content-clear"></i></a> -->
<!-- 									</td> -->
<!-- 									<td rowspan=2>CONTROL FIRMA:</td> -->
<!-- 									<td>FECHA SALIDA: No Hecho</td> -->
<!-- 									<td rowspan=2>04/06/2018</td> -->
<!-- 									<td rowspan=2> </td> -->
<!-- 								</tr> -->
<!-- 								<tr><td>FECHA ENTRADA: No Hecho</td></tr></thead> -->
							</table>
						</div>
					</div>
				</div>
				<div class="modal-footer">
      				<a href="#!" class="modal-close waves-effect waves-green btn-flat">Cerrar</a>
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
</body>

<script type="text/javascript">
	$(document).ready(function() {
		$('.tooltipped').tooltip();
		listarHistorialTramite();
	});

	$("#table_contenido").on("click", "#open", function() {
		var id = "DEV-00003";
		$.get('readHistorialTramite', {id:id}, function (obj) {
			console.log(obj);
			console.log("si");
		});
		$("#modal").openModal();
	});

	function listarHistorialTramite(){
		$.get('readallHistorialTramite', function (obj) {
			console.log(obj);
			var s='';
	        var emp = obj[0];
			for (var i = 0; i < obj.length; i++) {
				var ID_PASOS = $.trim(obj[i].ID_PASOS);
				s += '<tr>';
				s += '<td>'+obj[i].FECHA_CREACION+'</td>';
				if (ID_PASOS == "PAS-000055" && obj[i].EVALUACION == "1" && obj[i].URL_SOLICITUD == null) {
					s += '<td><a class="btn-floating btn-large pink lighten-2 tooltipped" data-position="top" data-tooltip="PROGRAMACIÓN: Sin Solicitud">1</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">2</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">3</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">4</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">5</a></td>';
					console.log("PROGRAMACION SS");
				} else if (ID_PASOS == "PAS-000055" && obj[i].EVALUACION == "1" && obj[i].URL_SOLICITUD != null) {
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="PROGRAMACIÓN: Solicitud Hecha">1</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 tooltipped" data-position="top" data-tooltip="SECRETARIA: Sin Aprobar | JEFE: Sin Aprobar">2</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">3</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">4</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">5</a></td>';
					console.log("PROGRAMACION CS");
				} else if (ID_PASOS == "PAS-000055" && obj[i].EVALUACION == "4" && obj[i].URL_SOLICITUD == null) {
					s += '<td><a class="btn-floating btn-large pink lighten-2 tooltipped" data-position="top" data-tooltip="REPROGRAMACIÓN: Sin Solicitud">1</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">2</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">3</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">4</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">5</a></td>';
					console.log("REPROGRAMACION SS");
				} else if (ID_PASOS == "PAS-000055" && obj[i].EVALUACION == "4" && obj[i].URL_SOLICITUD != null) {
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="REPROGRAMACIÓN: Solicitud Hecha">1</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 tooltipped" data-position="top" data-tooltip="SECRETARIA: Sin Aprobar | JEFE: Sin Aprobar">2</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">3</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">4</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">5</a></td>';
					console.log("REPROGRAMACION CS");
				} else if (ID_PASOS == "PAS-000055" && obj[i].EVALUACION == "2") {
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="Solicitud Hecha">1</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 tooltipped" data-position="top" data-tooltip="SECRETARIA: Aprobado | JEFE: Sin Aprobar">2</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">3</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">4</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">5</a></td>';
					console.log("APROBADO SECRETARIA");
				} else if (ID_PASOS == "PAS-000055" && obj[i].EVALUACION == "3") {
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="Solicitud Hecha">1</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="SECRETARIA: Aprobado | JEFE: Aprobado">2</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 tooltipped" data-position="top" data-tooltip="EN PROCESO (0/2)">3</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">4</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">5</a></td>';
					console.log("APROBADO JEFE");
				} else if (ID_PASOS == "PAS-000054") {
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="Solicitud Hecha">1</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="SECRETARIA: Aprobado | JEFE: Aprobado">2</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 tooltipped" data-position="top" data-tooltip="EN PROCESO (1/2)">3</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">4</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">5</a></td>';
					console.log("EN PROCESO 1/2");
				} else if (ID_PASOS == "PAS-000052") {
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="Solicitud Hecha">1</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="SECRETARIA: Aprobado | JEFE: Aprobado">2</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="EN PROCESO (2/2)">3</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 tooltipped" data-position="top" data-tooltip="SIN ENTREGAR">4</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">5</a></td>';
					console.log("EN PROCESO 2/2");
				} else if (ID_PASOS == "PAS-000090" && obj[i].FIRMA_SALIDA == 0 && obj[i].URL_PAPELETA != null) {
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="Solicitud Hecha">1</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="SECRETARIA: Aprobado | JEFE: Aprobado">2</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="EN PROCESO (2/2)">3</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="ENTREGADO">4</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 tooltipped" data-position="top" data-tooltip="FIRMA SALIDA: No Hecho | FIRMA ENTRADA: No Hecho">5</a></td>';
					console.log("PAPELETA ENTREGADA");
				} else if (ID_PASOS == "PAS-000092" && obj[i].FIRMA_SALIDA == 1 && obj[i].FIRMA_ENTRADA == 0) {
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="Solicitud Hecha">1</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="SECRETARIA: Aprobado | JEFE: Aprobado">2</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="EN PROCESO (2/2)">3</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="ENTREGADO">4</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 tooltipped" data-position="top" data-tooltip="FIRMA SALIDA: Hecho | FIRMA ENTRADA: No Hecho">5</a></td>';
					console.log("FIRMA SALIDA");
				} else if (ID_PASOS == "PAS-000092" && obj[i].FIRMA_ENTRADA == 1) {
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="Solicitud Hecha">1</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="SECRETARIA: Aprobado | JEFE: Aprobado">2</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="EN PROCESO (2/2)">3</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="ENTREGADO">4</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="FIRMA SALIDA: Hecho | FIRMA ENTRADA: Hecho">5</a></td>';
					console.log("FIRMA SALIDA Y FIRMA ENTRADA");
				} else {
					s += '<td><a class="btn-floating btn-large pink lighten-2 tooltipped" data-position="top" data-tooltip="PROGRAMACIÓN: Sin Solicitud">1</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">2</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">3</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">4</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">5</a></td>';
					console.log("PROGRAMACION SS");
				}
				s += '<td><a id="open" class="btn-floating btn-large waves-effect waves-light blue" name="'+obj[i].ID_DET_VACACIONES+'"><i class="mdi-image-remove-red-eye"></i></a></td>';
				s += '</tr>';
			}
	        $("#table_contenido").empty();
	        $("#table_contenido").append(createTable());
	        $("#data").empty();
	        $("#data").append(s);
// 	        $('#data-table-row-grouping').dataTable();
	        $('.tooltipped').tooltip();
	    });
	};
	
	function createTable() {
		var s = '<table id="data-table-row-grouping" class="display centered" cellspacing="0" width="100%">';
		s += '<thead>';
		s += '<tr>';
		s += '<th>Fecha</th>';
		s += '<th>Programación</th>';
		s += '<th>Aprobación</th>';
		s += '<th>Consolidar</th>';
		s += '<th>Papeleta</th>';
		s += '<th>Control Firma</th>';
		s += '<th>Detalle</th>';
		s += '</tr>';
		s += '</thead>';
		s += '<tbody id="data">';
		s += '</tbody>';
		s += '</table>';
		return s;
	};
</script>
</html>