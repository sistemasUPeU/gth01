<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
				<table id="table" class="bordered highlight centered">
					<thead>
						<tr>
							<th>Apellidos</th>
							<th>Nombres</th>
							<th>Fecha Inicio</th>
							<th>Fecha Fin</th>
							<th>Operaciones</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>Eclair</td>
							<td>Alvin</td>
							<td>20/08/2017</td>
							<td>20/09/2017</td>
							<td><button
									class="btn-floating waves-effect waves-light light-blue accent-4 trabajador_detalle modal-trigger"
									href="#modal" name="1">
									<i class='mdi-image-remove-red-eye'></i>
								</button></td>
						</tr>
						<tr>
							<td>Jellybean</td>
							<td>Alan</td>
							<td>20/08/2017</td>
							<td>20/09/2017</td>
							<td><button
									class="btn-floating waves-effect waves-light light-blue accent-4 trabajador_detalle modal-trigger"
									href="#modal" name="2">
									<i class='mdi-image-remove-red-eye'></i>
								</button></td>
						</tr>
						<tr>
							<td>Lollipop</td>
							<td>Jonathan</td>
							<td>20/08/2017</td>
							<td>20/09/2017</td>
							<td><button
									class="btn-floating waves-effect waves-light light-blue accent-4 trabajador_detalle modal-trigger"
									href="#modal" name="3">
									<i class='mdi-image-remove-red-eye'></i>
								</button></td>
						</tr>
					</tbody>
				</table>
			</div>

			<div id="modal" class="modal">
				<div class="modal-content">
					<h4>Control de firmas</h4>
					<div class="row">
						<div class="col s4">
							<p>Fecha Inicio</p>
							<input type="text" class="datepicker">
						</div>
						<div class="col s1">
							<br>
							<br>
							<button
								class="btn-floating waves-effect waves-light light-green darken-3">
								<i class="mdi-navigation-check"></i>
							</button>
						</div>
						<div class="col s2"></div>
						<div class="col s4">
							<p>Fecha Final</p>
							<input type="text" class="datepicker">
						</div>
						<div class="col s1">
							<br>
							<br>
							<button
								class="btn-floating waves-effect waves-light red darken-3">
								<i class="mdi-navigation-close"></i>
							</button>
						</div>
						<div class="col s4">
							<p>Fecha Inicio</p>
							<input type="text" class="datepicker">
						</div>
						<div class="col s1">
							<br>
							<br>
							<button
								class="btn-floating waves-effect waves-light red darken-3">
								<i class="mdi-navigation-close"></i>
							</button>
						</div>
						<div class="col s2"></div>
						<div class="col s4">
							<p>Fecha Final</p>
							<input type="text" class="datepicker">
						</div>
						<div class="col s1">
							<br>
							<br>
							<button
								class="btn-floating waves-effect waves-light red light-green darken-3">
								<i class="mdi-navigation-check"></i>
							</button>
						</div>
						<div class="col s4">
							<br> <a
								class="btn waves-effect waves-light light-green darken-3  modal-action modal-close"><i
								class="mdi-content-save"></i> Guardar</a>
						</div>
						<div class="col s8">
							<div class="file-field input-field">
								<div class="btn">
									<span>File</span> <input type="file">
								</div>
								<div class="file-path-wrapper">
									<input class="file-path validate" type="text">
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>