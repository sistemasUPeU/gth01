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
<link
	href="<c:url value='/resources/js/plugins/dropify/css/dropify.min.css'/>"
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
								<th>Foto</th>
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
								<th>N°</th>
								<th>Mes</th>
								<th>Foto</th>
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
								<td class=""><img width="30" height="30"
									src="<c:url value='main/webapp/resources/img/user.png'></c:url> "
									alt="Usuario"></td>
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
									class="btn waves-effect waves-effect waves-light yellow darken-4">Notificado</a></td>
								<td>
									<div class="col s12 m8 l9">
										<p>
											<a class="waves-effect waves-light btn modal-trigger  teal "
												href="#modal3">Detalle</a>
										</p>
										<div id="modal3" class="modal">
											<div class="modal-content teal white-text">
												<div class="divider"></div>
												<div class="row section">
													<div class="col s12 m6 l3">
														<p>Subir archivo</p>
													</div>
													<div class="col s12 m6 l3">
														<input type="file" id="input-file-now-custom-2"
															class="dropify" data-height="500" />
													</div>
													<div class="col s12 m6 l3">
														<input type="file" id="input-file-now-custom-2"
															class="dropify" data-height="500" />
													</div>
													<div class="col s12 m6 l3">
														<input type="file" id="input-file-now-custom-2"
															class="dropify" data-height="500" />
													</div>
												</div>
											</div>
											<div class="modal-footer  black lighten-4 black darken-4">
												<a href="#"
													class="waves-effect waves-light btn modal-action  green modal-close ">Enviar</a>
												<a href="#"
													class="waves-effect waves-light btn modal-action  red modal-close">Cancelar</a>
											</div>
										</div>
									</div>
								</td>

							</tr>
							<tr>
								<td>2</td>
								<td>Nicole</td>
								<td class=""><img width="30" height="30"
									src="<c:url value='main/webapp/resources/img/user.png'></c:url> "
									alt="Usuario"></td>
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
									class="btn waves-effect waves-effect waves-light yellow darken-4">Notificado</a></td>
								<td>
									<div class="col s12 m8 l9">
										<p>
											<a class="waves-effect waves-light btn modal-trigger  teal "
												href="#modal3">Detalle</a>
										</p>
										<div id="modal3" class="modal">
											<div class="modal-content teal white-text">
												<div class="divider"></div>
												<div class="row section">
													<div class="col s12 m4 l3">
														<p>Subir archivo</p>
													</div>
													<div class="col s12 m8 l9">
														<input type="file" id="input-file-now-custom-2"
															class="dropify" data-height="500" />
													</div>
												</div>
											</div>
											<div class="modal-footer  teal lighten-4">
												<a href="#"
													class="waves-effect waves-red btn-flat modal-action modal-close">Enviar</a>
												<a href="#"
													class="waves-effect waves-green btn-flat modal-action modal-close">Cancelar</a>
											</div>
										</div>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div id="main">
		<p style="color: #FF0000";>
		<h4 class="header">Renuncias Autorizados</h4>
		</p>

		<div class="row">
			<div class="col s12 m8 l9">
				<table id="data-table-simple" class="responsive-table display"
					cellspacing="0">
					<thead>
						<tr>
							<th>N°</th>
							<th>Mes</th>
							<th>Foto</th>
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
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>1</td>
							<td>Carlos</td>
							<td class=""><img width="30" height="30"
								src="<c:url value='main/webapp/resources/img/user.png'></c:url> "
								alt="Usuario"></td>
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
								class="btn waves-effect waves-effect waves-light green darken-4">Entregado</a></td>
						</tr>
						<tr>
							<td>1</td>
							<td>Nicole</td>
							<td class=""><img width="30" height="30"
								src="<c:url value='main/webapp/resources/img/user.png'></c:url> "
								alt="Usuario"></td>
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
								class="btn waves-effect waves-effect waves-light green darken-4">Entregado</a></td>
						</tr>
					</tbody>
				</table>
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
	<!-- 	<script -->
	<%-- 		src="<c:url value='/resources/js/plugins/jquery-1.11.2.min.js'></c:url>" --%>
	<!-- 		type="text/javascript"></script> -->
	<script src="<c:url  value='/resources/js/carpeta.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url  value='/resources/js/plugins/dropify/js/dropify.min.js'></c:url>"
		type="text/javascript"></script>
	<%@include file="../../../resources/js/businessCore/jsAutorizar.jspf"%>
<body>
</html>