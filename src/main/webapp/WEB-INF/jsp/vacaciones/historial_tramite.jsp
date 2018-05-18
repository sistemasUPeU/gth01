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
		$("#table_contenido").append(createTable());
		$('.tooltipped').tooltip();
	});

	function createTable() {
		var s = '<table id="data-table-row-grouping" class="display centered" cellspacing="0" width="100%">';
		s += '<thead>';
		s += '<tr>';
		s += '<th>Programación</th>';
		s += '<th>Aprobación</th>';
		s += '<th>Papeleta</th>';
		s += '<th>Control Firma</th>';
		s += '<th>Detalle</th>';
		s += ' </tr>';
		s += '</thead>';
		s += '<tbody id="data">';
		s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="Solicitud hecha">1</a></td>';
		s += '<td><a class="btn-floating btn-large pink lighten-2 tooltipped" data-position="top" data-tooltip="Esperando aprobación (Jefe)">2</a></td>';
		s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">3</a></td>';
		s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">4</a></td>';
		s += '<td><a class="btn-floating btn-large waves-effect waves-light blue"><i class="mdi-image-remove-red-eye"></i></a></td>';
		s += '</tbody>';
		s += '</table>';
		return s;
	};
</script>
</html>