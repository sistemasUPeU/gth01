<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
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
<style type="text/css">
.center-btn {
	text-align: center
}

div.dataTables_length {
	display: none !important;
}

#containerframe {
	padding: 0;
}
</style>
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
			<center>
				<div class="chip red lighten-2 black-text"
					style="height: 50px; margin-bottom: 30px;">
					<h5>Aprobar Programa de Vacaciones</h5>
				</div>
			</center>

			<div id="cuerpo">
				<ul class="tabs tab-demo z-depth-1" style="width: 100%;">
					<li class="tab col s3"><a href="#test004" class="active">Sin
							Aprobar</a></li>
					<li class="tab col s3"><a href="#test005" class="">Aprobados</a></li>
					<li class="tab col s3"><a href="#test006" class="">Observados</a></li>
				</ul>
				<div class="row">
					<div id="test004">
						<div id="divcollapsible">
							<ul class="collapsible" id="listarAnteriorCollapsible">
							</ul>
						</div>
						<div class="hide" id="containerframe">
							<iframe id="calendar_frame" name="calendar_frame" frameborder="0"
								style="width: 100%;" height="100%" allowfullscreen></iframe>
						</div>
					</div>
					<div id="test005" class="col s12">
						<div id="contTable1"></div>
					</div>
					<div id="test006" class="col s12">
						<div id="contTable3"></div>
					</div>
				</div>


			</div>
			<div id="modal_programa_aprobados" class="modal modal-fixed-footer" >
				<div class="modal-content" id="show_request">
					<div class="row">

							<div class="col s3 offset-s3 " id="column1_proap">
			
							</div>
							<div class="col s5 m5 l5 " id="column2_proap">
		
							</div>

					</div>
					<div id="table_detail">
					
					</div>
				</div>
				<div class="modal-footer">
					<a id="fec_up" href="#!"
						class="modal-action modal-close waves-effect waves-green btn-flat" >Cerrar</a>
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
		src="<c:url value='/resources/js/businessCore/holidays/aprobar_pv/aprobar_pv.js'></c:url>"
		type="text/javascript"></script>
</body>
<script>
	
</script>
</html>