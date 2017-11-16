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

	</div>
	<div id="main">
		<div class="wrapper">
			<%@include file="../../../jspf/aside_left.jspf"%>
			<section id="content"></section>
		</div>
		<%@include file="../../../jspf/info_puesto.jspf"%>
		<div id="table-datatables">
			<h4 class="header">Renuncias por Autorizar</h4>

			<div class="row">
				<div class="col s12 m8 l9">
					<table id="data-table-simple" class="responsive-table display"
						cellspacing="0">
						<thead>
							<tr>
								<th>N°</th>
								<th>Mes</th>
								<th>Apellidos y Nombres</th>
								<th>Puesto</th>
								<th>Area</th>
								<th>Departatmento</th>
								<th>Tipo de contrato</th>
								<th>Descripción</th>
								<th>Fecha de inicio</th>
								<th>Dni</th>
								<th>MFL</th>
								<th>Estado</th>
								<th>Operaciones</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th>N° lalallala</th>
								<th>Mes</th>
								<th>Apellidos y Nombres</th>
								<th>Puesto</th>
								<th>Area</th>
								<th>Departatmento</th>
								<th>Tipo de contrato</th>
								<th>Descripción</th>
								<th>Fecha de inicio</th>
								<th>Dni</th>
								<th>MFL</th>
								<th>Estado</th>
								<th>Operaciones</th>
							</tr>
						</tfoot>

						<tbody>
							<tr>
								<td>1</td>
								<td>Carlos</td>
								<td>Edinburgh</td>
								<td>61</td>
								<td>2011/04/25</td>
								<td>$320,800</td>
								<td>$320,800</td>
								<td>$320,800</td>
								<td>$320,800</td>
								<td>$320,800</td>
								<td>$320,800</td>
								<td><a
									class="btn waves-effect waves-effect waves-light yellow darken-4">Pendiente</a></td>
								<td><a href="<c:url value='details'></c:url>"
									class="btn waves-effect waves-light  cyan darken-2">Detalle</a></td>

							</tr>
							<tr>
								<td>2</td>
								<td>Nicole</td>
								<td>Edinburgh</td>
								<td>60</td>
								<td>2011/04/25</td>
								<td>$320,800</td>
								<td>$320,800</td>
								<td>$320,800</td>
								<td>$320,800</td>
								<td>$320,800</td>
								<td>$320,800</td>
								<td><a
									class="btn waves-effect waves-effect waves-light yellow darken-4">Pendiente</a></td>
								<td><a class="btn waves-effect waves-light  cyan darken-2">Detalle</a></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div id="main">
	<div class="col s12 m6">
		<ul class="collapsible collapsible-accordion"
			data-collapsible="accordion">
			<li class="active">
				<div class="collapsible-header active">
					<i class="mdi-toggle-check-box"></i> Requerimientos Notificados
				</div>
				<div class="collapsible-body" style="display: none;">
					<div id="table-datatables">
						<h4 class="header">REQUERIMIENTOS AUTORIZADO</h4>
						<div class="row">
							<div class="col s12 m8 l9">
								<table id="data-table-simple" class="responsive-table display"
									cellspacing="0">
									<tr>
										<th>Mes</th>
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
											<td>DICIEMBRE</td>
											<td>Norma Lucia Riquelme</td>
											<td>Auxiliar de Embalaje</td>
											<td>Distribucion</td>
											<td>Productos Union</td>
											<td>Contrato persona: Tiempo completo</td>
											<td>Entregar y/o enviar documentos. Reglamentos y otros
												al trabajador</td>
											<td>02/10/17 12:47:50</td>
											<td>12458976</td>
											<td>No</td>
											<td><a
												class="btn waves-effect waves-effect waves-light green darken-4">Autorizado</a></td>
										</tr>
										<tr>
											<td>ENERO</td>
											<td>Flor Flores Guillen</td>
											<td>Auxiliar de Embalaje</td>
											<td>Distribucion</td>
											<td>Productos Union</td>
											<td>Contrato persona: Tiempo completo</td>
											<td>Entregar y/o enviar documentos. Reglamentos y otros
												al trabajador</td>
											<td>02/10/17 12:47:50</td>
											<td>12458976</td>
											<td>No</td>
											<td><a
												class="btn waves-effect waves-effect waves-light green darken-4">Autorizado</a></td>
										</tr>

									</tbody>
								</table>

							</div>
						</div>
					</div>


				</div>
			</li>
		</ul>
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
	<%@include file="../../../resources/js/businessCore/jsAutorizar.jspf"%>
<body>
</html>