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
				<a class="waves-effect waves-light btn-large" onclick="window.print()"><i class="mdi-action-print right"></i>Imprimir</a>
				</div>
				<table id="tablita" class="bordered highlight centered">
					<thead>
					<form>
						<tr>
							<th>Nombres</th>
							<th>Fecha-Entrada</th>
							<th>Fecha-Salida</th>
							<th>Total Dias</th>
							<th>Ver Detalle</th>
							<th><input type="button" onclick="marcar(this.form)" /> Aprobar</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>Eclair</td>
							<td>Alvin</td>
							<td>Hornato</td>
							<td>null</td>
							<td>null</td>
							<td>Aprob</td>
							<input type="checkbox"/>
						</tr>
						<tr>
							<td>Jellybean</td>
							<td>Alan</td>
							<td>Limpieza</td>
							<td>null</td>
							<td>null</td>
							<td>null</td>
						</tr>
						<tr>
							<td>Lollipop</td>
							<td>Jonathan</td>
							<td>Hornato</td>
							<td>null</td>
							<td>null</td>
							<td>null</td>
						</tr>
					</tbody>
					</form>
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
	})
	
	function searchTrabajador() {
		var input, filter, table, tr, td, i;
		input = document.getElementById("searchTrabajador");
		filter = input.value.toUpperCase();
		table = document.getElementById("tablita");
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

	$(document).ready(function(){

		$("#imprimir").click(function(){
		    var pdf = new jsPDF('portrait', 'pt', 'a3');

		    var options = {
		         pagesplit: true
		    };
		   // var width = 400;
		    pdf.addHTML($("#Hola"), options, function() {
		            pdf.save('HOLA.pdf');
		        }
		    ); 
		});
		});
	$('document').ready(function(){
		   $("#checkTodos").change(function () {
		      $("input:checkbox").prop('checked', $(this).prop("checked"));
		  });
		});
</script>

</html>