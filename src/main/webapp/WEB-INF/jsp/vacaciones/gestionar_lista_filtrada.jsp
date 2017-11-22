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
				<div class="input-field col s6">
					<i class="mdi-action-search prefix"></i> <input
						id="searchTrabajador" type="text" class="validate"
						onkeyup="searchTrabajador()"> <label
						for="searchTrabajador">Trabajador</label>
				</div>
				<div class="input-field col s6">
					<i class="mdi-action-search prefix"></i> <input
						id="searchDepartamento" type="text" class="validate"
						onkeyup="searchDepartamento()"> <label
						for="searchDepartamento">Departamento</label>
				</div>
				<table id="table_trabajador-filtrado"
					class="bordered highlight centered">
					<thead>
						<tr>
							<th>Apellidos y Nombres</th>
							<th>Departamento</th>
							<th>Área</th>
							<th>Sección</th>
							<th>Condición</th>
						</tr>
					</thead>
				</table>
				<br> <a class="btn waves-effect waves-light right"><i
					class="mdi-navigation-check"></i> Confirmar</a>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
	$(document).ready(function() {
		console.log("si");
		readAllTF();
	})

	function searchTrabajador() {
		var input, filter, table, tr, td, i;
		input = document.getElementById("searchTrabajador");
		filter = input.value.toUpperCase();
		table = document.getElementById("table_trabajador-filtrado");
		tr = table.getElementsByTagName("tr");

		for (i = 0; i < tr.length; i++) {
			td = tr[i].getElementsByTagName("td")[0];
			if (td) {
				if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
					tr[i].style.display = "";
				} else {
					tr[i].style.display = "none";
				}
			}
		}
	}

	function searchDepartamento() {
		var input, filter, table, tr, td, i;
		input = document.getElementById("searchDepartamento");
		filter = input.value.toUpperCase();
		table = document.getElementById("table_trabajador-filtrado");
		tr = table.getElementsByTagName("tr");

		for (i = 0; i < tr.length; i++) {
			td = tr[i].getElementsByTagName("td")[2];
			if (td) {
				if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
					tr[i].style.display = "";
				} else {
					tr[i].style.display = "none";
				}
			}
		}
	}

	function readAllTF() {
		console.log("x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x");
		console.log("READALL PEDIDOS");
		console.log("x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x-x");
		console.log("");
		$.get("readallTrabajadorFiltrado", function(data, status) {
			$("#table_trabajador-filtrado").find("tr:gt(0)").remove();
			$("#table_trabajador-filtrado thead:last").after(
					"<tbody id='table-body'></tbody>");
			var lista = document.getElementById("table-body");
			for ( var i in data) {
				//$("#table_trabajador-filtrado tbody:last").after("<tr><td>"+data[i].AP_PATERNO+" "+data[i].AP_MATERNO+" "+data[i].NO_TRABAJADOR+"</td><td>"+data[i].NO_DEP+"</td><td>"+data[i].NO_AREA+"</td><td>"+data[i].NO_SECCION+"</td><td>"+data[i].LI_CONDICION+"</td></tr>");
				lista.innerHTML += "<tr><td>" + data[i].AP_PATERNO + " "
						+ data[i].AP_MATERNO + " " + data[i].NO_TRABAJADOR
						+ "</td><td>" + data[i].NO_DEP + "</td><td>"
						+ data[i].NO_AREA + "</td><td>" + data[i].NO_SECCION
						+ "</td><td>" + data[i].LI_CONDICION + "</td></tr>";
			}
		});
	}
</script>

</html>