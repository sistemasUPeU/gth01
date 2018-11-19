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
			<center>
				<div class="chip red lighten-2 black-text"
					style="height: 50px; margin-bottom: 30px;">
					<h5>Historial de Trámites</h5>
				</div>
			</center>
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
										<th></th>
										<th>Acción</th>
										<th>Estado</th>
										<th>Fecha</th>
										<th></th>
									</tr>
								</thead>
								<thead>
									<tr>
										<td><a id="b_programacion" class="btn-floating disabled"></i></a>
										</td>
										<td id="t_programacion"></td>
										<td id="s_programacion"></td>
										<td id="f_programacion"></td>
										<td><a id="d_programacion"
											class="waves-effect waves-light btn disabled"><i
												class="mdi-content-content-paste"></i></a></td>
									</tr>
								</thead>
								<thead>
									<tr>
										<td rowspan=2><a id="b_aprobacion"
											class="btn-floating disabled"><i class=""></i></a></td>
										<td rowspan=2>APROBACIÓN:</td>
										<td id="s1_aprobacion"></td>
										<td id="f1_aprobacion"></td>
										<td rowspan=2></td>
									</tr>
									<tr>
										<td id="s2_aprobacion"></td>
										<td id="f2_aprobacion"></td>
									</tr>
								</thead>
								<thead>
									<tr>
										<td><a id="b_consolidar" class="btn-floating disabled"><i
												class=""></i></a></td>
										<td>CONSOLIDAR:</td>
										<td id="s_consolidar"></td>
										<td id="f_consolidar"></td>
										<td></td>
									</tr>
								</thead>
								<thead>
									<tr>
										<td><a id="b_papeleta" class="btn-floating disabled"><i
												class=""></i></a></td>
										<td>PAPELETA:</td>
										<td id="s_papeleta"></td>
										<td id="f_papeleta"></td>
										<td><a id="d_papeleta"
											class="waves-effect waves-light btn disabled"><i
												class="mdi-content-content-paste"></i></a></td>
									</tr>
								</thead>
								<thead>
									<tr>
										<td rowspan=2><a id="b_control"
											class="btn-floating disabled"><i class=""></i></a></td>
										<td rowspan=2>CONTROL FIRMA:</td>
										<td id="s1_control"></td>
										<td id="f1_control"></td>
										<td rowspan=2></td>
									</tr>
									<tr>
										<td id="s2_control"></td>
										<td id="f2_control"></td>
									</tr>
								</thead>
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
	<script
		src="<c:url value='/resources/js/businessCore/holidays/historial_tramite.js'></c:url>"
		type="text/javascript"></script>
</body>
</html>