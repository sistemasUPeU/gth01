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
				<!-- 				<table id="table" class="bordered highlight centered"> -->
				<!-- 					<thead> -->
				<!-- 						<tr> -->
				<!-- 							<th>Apellidos</th> -->
				<!-- 							<th>Nombres</th> -->
				<!-- 							<th>Fecha Inicio</th> -->
				<!-- 							<th>Fecha Fin</th> -->
				<!-- 							<th>Operaciones</th> -->
				<!-- 						</tr> -->
				<!-- 					</thead> -->
				<!-- 					<tbody> -->
				<!-- 						<tr> -->
				<!-- 							<td>Eclair</td> -->
				<!-- 							<td>Alvin</td> -->
				<!-- 							<td>20/08/2017</td> -->
				<!-- 							<td>20/09/2017</td> -->
				<!-- 							<td><button -->
				<!-- 									class="btn-floating waves-effect waves-light light-blue accent-4 trabajador_detalle modal-trigger" -->
				<!-- 									href="#modal" name="1"> -->
				<!-- 									<i class='mdi-image-remove-red-eye'></i> -->
				<!-- 								</button></td> -->
				<!-- 						</tr> -->
				<!-- 						<tr> -->
				<!-- 							<td>Jellybean</td> -->
				<!-- 							<td>Alan</td> -->
				<!-- 							<td>20/08/2017</td> -->
				<!-- 							<td>20/09/2017</td> -->
				<!-- 							<td><button -->
				<!-- 									class="btn-floating waves-effect waves-light light-blue accent-4 trabajador_detalle modal-trigger" -->
				<!-- 									href="#modal" name="2"> -->
				<!-- 									<i class='mdi-image-remove-red-eye'></i> -->
				<!-- 								</button></td> -->
				<!-- 						</tr> -->
				<!-- 						<tr> -->
				<!-- 							<td>Lollipop</td> -->
				<!-- 							<td>Jonathan</td> -->
				<!-- 							<td>20/08/2017</td> -->
				<!-- 							<td>20/09/2017</td> -->
				<!-- 							<td><button -->
				<!-- 									class="btn-floating waves-effect waves-light light-blue accent-4 trabajador_detalle modal-trigger" -->
				<!-- 									href="#modal" name="3"> -->
				<!-- 									<i class='mdi-image-remove-red-eye'></i> -->
				<!-- 								</button></td> -->
				<!-- 						</tr> -->
				<!-- 					</tbody> -->
				<!-- 				</table> -->
				<div id="table_contenido" class="col s12 m12 l12"></div>
			</div>

			<div id="modal" class="modal">
				<div class="modal-content">
					<h4>Control de firmas</h4>
					<div class="row">
<!-- 						<div class="col s4"> -->
<!-- 							<p>Fecha Inicio</p> -->
<!-- 							<input type="text" class="datepicker"> -->
<!-- 						</div> -->
<!-- 						<div class="col s1"> -->
<!-- 							<br> <br> -->
<!-- 							<button -->
<!-- 								class="btn-floating waves-effect waves-light light-green darken-3"> -->
<!-- 								<i class="mdi-navigation-check"></i> -->
<!-- 							</button> -->
<!-- 						</div> -->
<!-- 						<div class="col s2"></div> -->
<!-- 						<div class="col s4"> -->
<!-- 							<p>Fecha Final</p> -->
<!-- 							<input type="text" class="datepicker"> -->
<!-- 						</div> -->
<!-- 						<div class="col s1"> -->
<!-- 							<br> <br> -->
<!-- 							<button -->
<!-- 								class="btn-floating waves-effect waves-light red darken-3"> -->
<!-- 								<i class="mdi-navigation-close"></i> -->
<!-- 							</button> -->
<!-- 						</div> -->
<!-- 						<div class="col s4"> -->
<!-- 							<p>Fecha Inicio</p> -->
<!-- 							<input type="text" class="datepicker"> -->
<!-- 						</div> -->
<!-- 						<div class="col s1"> -->
<!-- 							<br> <br> -->
<!-- 							<button -->
<!-- 								class="btn-floating waves-effect waves-light red darken-3"> -->
<!-- 								<i class="mdi-navigation-close"></i> -->
<!-- 							</button> -->
<!-- 						</div> -->
<!-- 						<div class="col s2"></div> -->
<!-- 						<div class="col s4"> -->
<!-- 							<p>Fecha Final</p> -->
<!-- 							<input type="text" class="datepicker"> -->
<!-- 						</div> -->
<!-- 						<div class="col s1"> -->
<!-- 							<br> <br> -->
<!-- 							<button -->
<!-- 								class="btn-floating waves-effect waves-light red light-green darken-3"> -->
<!-- 								<i class="mdi-navigation-check"></i> -->
<!-- 							</button> -->
<!-- 						</div> -->
						<div id="contenedor_fechas"></div>
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
		console.log("si");
		listarControlFirmas();
	})

	$("#table_contenido").on("click", "#open", function() {
		$.get('readFirma', function (obj) {
			console.log(obj);
	    	var j='';
	        //var emp = obj[0];
	        //
// 	        $.get("readallTrabajadorFiltrado", function(data, status) {
// 	 			$("#table_trabajador-filtrado").find("tr:gt(0)").remove();
// 	 			$("#table_trabajador-filtrado thead:last").after(
// 	 					"<tbody id='table-body'></tbody>");
// 	 			var lista = document.getElementById("table-body");
// 	 			for ( var i in data) {
// 	 				//$("#table_trabajador-filtrado tbody:last").after("<tr><td>"+data[i].AP_PATERNO+" "+data[i].AP_MATERNO+" "+data[i].NO_TRABAJADOR+"</td><td>"+data[i].NO_DEP+"</td><td>"+data[i].NO_AREA+"</td><td>"+data[i].NO_SECCION+"</td><td>"+data[i].LI_CONDICION+"</td></tr>");
// 	 				lista.innerHTML += "<tr><td>" + data[i].AP_PATERNO + " "
// 	 						+ data[i].AP_MATERNO + " " + data[i].NO_TRABAJADOR
// 	 						+ "</td><td>" + data[i].NO_DEP + "</td><td>"
// 	 						+ data[i].NO_AREA + "</td><td>" + data[i].NO_SECCION
// 	 						+ "</td><td>" + data[i].LI_CONDICION + "</td></tr>";
// 	 			}
// 	 		});
	 		//
	 		var fechas = document.getElementById("contenedor_fechas");
	        for (var i = 0; i < obj.length; i++) {
				j += '<div class="col s4">';
	            j += '<p>Fecha Inicio</p>';
	            j += '<input value="'+obj[i].FECHA_INICIO+'" type="text">';
	            j += '</div>';
		        j += '<div class="col s3">';
				j += '<br> <br>';
	            j += '<button class="btn-floating waves-effect waves-light red light-green darken-3">';
	            j += '<i class="mdi-navigation-check"></i>';
	            j += '</button>';
	            j += '</div>';
	            j += '<div class="col s4">';
	            j += '<p>Fecha Fin</p>';
	            j += '<input value="'+obj[i].FECHA_FIN+'" type="text">';
	            j += '</div>';
		        j += '<div class="col s1">';
				j += '<br> <br>';
	            j += '<button class="btn-floating waves-effect waves-light red light-green darken-3">';
	            j += '<i class="mdi-navigation-check"></i>';
	            j += '</button>';
	            j += '</div>';
	           
			}
	        fechas.innerHTML += j;
// 	    	$('#select_clientes > option[value="'+data.idclientes+'"]').attr('selected', 'selected');
// 	    	$('#select_articulos > option[value="'+data.idarticulos+'"]').attr('selected', 'selected');
// 	        $("#input_precio-edit").val(data.precio);
// 	        $("#input_cantidad-edit").val(data.cantidad);
// 	        $("#input_importe-edit").val(data.importe);
	    });
		//$("#modal").modal("show");
		$("#modal").openModal();
	});
	
	function listarControlFirmas(){
	$.get('readallControlFirma', function (obj) {
	        var s='';
	        var emp = obj[0];
	        for (var i = 0; i < obj.length; i++) {
				s += '<tr>';
	            s += '<td>'+obj[i].AP_PATERNO+' '+obj[i].AP_MATERNO+' '+obj[i].NO_TRABAJADOR+'</td>';
	            s += '<td>'+obj[i].FECHA_INICIO+'</td>';
	            s += '<td>'+obj[i].FECHA_FIN+'</td>';
		        s += '<td><button id="open" class="btn-floating waves-effect waves-light light-blue accent-4" href="#modal" name="'+obj[i].ID_VACACIONES+'">';
				s += '<i class="mdi-image-remove-red-eye"></i></button>';
	            s += '</tr>';
			}
	        $("#table_contenido").empty();
	        $("#table_contenido").append(createTable());
	        $("#data").empty();
	        $("#data").append(s);
	        $('#data-table-row-grouping').dataTable();
	    });
	};

	function createTable() {
		var s = '<table id="data-table-row-grouping" class="display centered" cellspacing="0" width="100%">';
		s += '<thead>';
		s += '<tr>';
		s += '<th>Apellidos y Nombres</th>';
		s += '<th>Fecha Inicio</th>';
		s += '<th>Fecha Fin</th>';
		s += '<th>Control</th>';
		s += ' </tr>';
		s += '</thead>';
		s += '<tbody id="data"></tbody>';
		s += '</table>';
		return s;
	};
</script>
</html>