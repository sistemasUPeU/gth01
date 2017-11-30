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
	href="<c:url value='/resources/js/plugins/data-tables/css/dataTables.material.min.css'></c:url>"
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
		<div class="col s12 m8 l9">
			<div id="data-table-row-grouping col s12 m8 l9"
				class="card-panel display #e3f2fd blue lighten-5"
				style="position: absolute">

				<div class="col s12 m8 l9 contT"></div>
			</div>
		</div>
	</div>

    <br>
	
	<div class="col s12 m12" style="position: absolute; display:none">
		<ul class="collapsible collapsible-accordion"
			data-collapsible="accordion">
			<li class="active" style="position: relative">
				<div class="collapsible-header active">
					<i class="mdi-toggle-check-box"></i> Requerimientos Notificados
				</div>
				<div class="collapsible-body" style="display: none;">
					<div id="table-datatables">
						<h4 class="header">REQUERIMIENTOS AUTORIZADO</h4>
						<div class="row">
							<div class="col s12 m8 l9"></div>	
						</div>
					</div>


				</div>
			</li>
		</ul>
	</div>
	
	<div style="position: fixed; width: 100%; bottom: 0;">
		<%@include file="../../../jspf/footer.jspf"%>
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
	<%-- 		<%@include file="../../../resources/js/businessCore/jsAutorizar.jspf"%> --%>

	<script
		src="<c:url value='/resources/js/businessCore/ProcesarRenuncia.js'></c:url>"
		type="text/javascript"></script>
<body>
</html>