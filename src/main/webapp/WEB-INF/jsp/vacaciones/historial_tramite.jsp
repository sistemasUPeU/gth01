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

	function listarHistorialTramite(){
		$.get('readallHistorialTramite', function (obj) {
			console.log(obj);
			var s='';
	        var emp = obj[0];
			for (var i = 0; i < obj.length; i++) {
				var ID_PASOS = $.trim(obj[i].ID_PASOS);
				s += '<tr>';
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
				} else if (ID_PASOS == "PAS-000092" && obj[i].FIRMA_SALIDA == 0 && obj[i].URL_PAPELETA != null) {
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
				s += '<td><a class="btn-floating btn-large waves-effect waves-light blue" name="'+obj[i].ID_DET_VACACIONES+'"><i class="mdi-image-remove-red-eye"></i></a></td>';
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