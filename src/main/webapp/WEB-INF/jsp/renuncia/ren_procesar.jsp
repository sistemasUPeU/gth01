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
		<div id="table-datatables" class="card-panel">
			<div class="col s12 m8 l9 contT"></div>
		</div>
		<div id="table-datatables">
			<h4 class="header">DataTables example</h4>
			<div class="row">
				<div class="col s12 m8 l9">
					<table id="data-table-simple" class="responsive-table display"
						cellspacing="0">
						<thead>
							<tr>
								<th>Mes</th>
								<th>Foto</th>
								<th>Apellidos y Nombres</th>
								<th>Puesto</th>
								<th>Area</th>
								<th>Departamento</th>
								<th>Tipo de contrato</th>
								<th>Descripcion</th>
								<th>Fecha de inicio</th>
								<th>DNI</th>
								<th>MFL</th>
								<th>Estado</th>
								<th>Operaciones</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th>Mes</th>
								<th>Foto</th>
								<th>Apellidos y Nombres</th>
								<th>Puesto</th>
								<th>Area</th>
								<th>Departamento</th>
								<th>Tipo de contrato</th>
								<th>Descripcion</th>
								<th>Fecha de inicio</th>
								<th>DNI</th>
								<th>MFL</th>
								<th>Estado</th>
								<th>Operaciones</th>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<td>OCTUBRE</td>
								<td>Foto</td>
								<td>Rcopa Inuma Angel</td>
								<td>Auxiliar de Embalaje</td>
								<td>Distribucion</td>
								<td>Productos Union</td>
								<td>Contrato persona: Tiempo completo</td>
								<td>Entregar y/o enviar documentos. Reglamentos y otros al
									trabajador</td>
								<td>02/10/17 12:47:50</td>
								<td>12458976</td>
								<td>No</td>
								<td><a
									class="btn btn-large waves-effect waves-light yellow darken-4">Pendiente</a></td>
								<td><a href="/ren_DetalleP.jsp" class="btn btn-large waves-effect waves-light blue">Detalle</a></td>
<!-- 							<a href="/gth/renuncias/processR" class="waves-effect waves-light"><i class="mdi-notification-sync-problem"></i>  Procesar Renuncia</a> -->
							</tr>
							<tr>
								<td>NOVIEMBRE</td>
								<td>Foto</td>
								<td>Nicole Garcia Guevara</td>
								<td>Auxiliar de Embalaje</td>
								<td>Distribucion</td>
								<td>Productos Union</td>
								<td>Contrato persona: Tiempo completo</td>
								<td>Entregar y/o enviar documentos. Reglamentos y otros al
									trabajador</td>
								<td>02/10/17 12:47:50</td>
								<td>12458976</td>
								<td>No</td>
								<td><a
									class="btn btn-large waves-effect waves-light yellow darken-4">Pendiente</a></td>
								<td><a class="btn btn-large waves-effect waves-light blue">Detalle</a></td>
							</tr>
							<tr>
								<td>DICIEMBRE</td>
								<td>Foto</td>
								<td>Norma Lucia Riquelme</td>
								<td>Auxiliar de Embalaje</td>
								<td>Distribucion</td>
								<td>Productos Union</td>
								<td>Contrato persona: Tiempo completo</td>
								<td>Entregar y/o enviar documentos. Reglamentos y otros al
									trabajador</td>
								<td>02/10/17 12:47:50</td>
								<td>12458976</td>
								<td>No</td>
								<td><a
									class="btn btn-large waves-effect waves-light yellow darken-4">Pendiente</a></td>
								<td><a class="btn btn-large waves-effect waves-light blue">Detalle</a></td>
							</tr>
							<tr>
								<td>ENERO</td>
								<td>Foto</td>
								<td>Flor Flores Guillen</td>
								<td>Auxiliar de Embalaje</td>
								<td>Distribucion</td>
								<td>Productos Union</td>
								<td>Contrato persona: Tiempo completo</td>
								<td>Entregar y/o enviar documentos. Reglamentos y otros al
									trabajador</td>
								<td>02/10/17 12:47:50</td>
								<td>12458976</td>
								<td>No</td>
								<td><a
									class="btn btn-large waves-effect waves-light yellow darken-4">Pendiente</a></td>
								<td><a class="btn btn-large waves-effect waves-light blue">Detalle</a></td>
							</tr>

						</tbody>
					</table>

				</div>
			</div>
		</div>
		<br>
		<div id="table-datatables">
			<div class="row">
				<div class="col s12 m8 l9">
					<table id="data-table-simple" class="responsive-table display"
						cellspacing="0">
						<thead>
							<tr>
								<th>Mes</th>
								<th>Foto</th>
								<th>Apellidos y Nombres</th>
								<th>Puesto</th>
								<th>Area</th>
								<th>Departamento</th>
								<th>Tipo de contrato</th>
								<th>Descripcion</th>
								<th>Fecha de inicio</th>
								<th>DNI</th>
								<th>MFL</th>
								<th>Estado</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th>Mes</th>
								<th>Foto</th>
								<th>Apellidos y Nombres</th>
								<th>Puesto</th>
								<th>Area</th>
								<th>Departamento</th>
								<th>Tipo de contrato</th>
								<th>Descripcion</th>
								<th>Fecha de inicio</th>
								<th>DNI</th>
								<th>MFL</th>
								<th>Estado</th>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<td>OCTUBRE</td>
								<td>Foto</td>
								<td>Rcopa Inuma Angel</td>
								<td>Auxiliar de Embalaje</td>
								<td>Distribucion</td>
								<td>Productos Union</td>
								<td>Contrato persona: Tiempo completo</td>
								<td>Entregar y/o enviar documentos. Reglamentos y otros al
									trabajador</td>
								<td>02/10/17 12:47:50</td>
								<td>12458976</td>
								<td>No</td>
								<td><a class="btn btn-large waves-effect waves-light green">Autorizado</a></td>
							</tr>
							<tr>
								<td>NOVIEMBRE</td>
								<td>Foto</td>
								<td>Nicole Garcia Guevara</td>
								<td>Auxiliar de Embalaje</td>
								<td>Distribucion</td>
								<td>Productos Union</td>
								<td>Contrato persona: Tiempo completo</td>
								<td>Entregar y/o enviar documentos. Reglamentos y otros al
									trabajador</td>
								<td>02/10/17 12:47:50</td>
								<td>12458976</td>
								<td>No</td>
								<td><a class="btn btn-large waves-effect waves-light green">Autorizado</a></td>
							</tr>
							<tr>
								<td>DICIEMBRE</td>
								<td>Foto</td>
								<td>Norma Lucia Riquelme</td>
								<td>Auxiliar de Embalaje</td>
								<td>Distribucion</td>
								<td>Productos Union</td>
								<td>Contrato persona: Tiempo completo</td>
								<td>Entregar y/o enviar documentos. Reglamentos y otros al
									trabajador</td>
								<td>02/10/17 12:47:50</td>
								<td>12458976</td>
								<td>No</td>
								<td><a class="btn btn-large waves-effect waves-light green">Autorizado</a></td>
							</tr>
							<tr>
								<td>ENERO</td>
								<td>Foto</td>
								<td>Flor Flores Guillen</td>
								<td>Auxiliar de Embalaje</td>
								<td>Distribucion</td>
								<td>Productos Union</td>
								<td>Contrato persona: Tiempo completo</td>
								<td>Entregar y/o enviar documentos. Reglamentos y otros al
									trabajador</td>
								<td>02/10/17 12:47:50</td>
								<td>12458976</td>
								<td>No</td>
								<td><a class="btn btn-large waves-effect waves-light green">Autorizado</a></td>
							</tr>

						</tbody>
					</table>

				</div>
			</div>
		</div>
		<br>
		<div class="divider"></div>
	</div>
	<%@include file="../../../jspf/footer.jspf"%>
	<script
		src="<c:url value='/resources/js/plugins/prism/prism.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/plugins/data-tables/js/jquery.dataTables.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/plugins/data-tables/data-tables-script.js'></c:url>"
		type="text/javascript"></script>
	<%-- <%@include file="../../../resources/js/businessCore/jsAutorizar.jspf"%> --%>

</body>
</html>