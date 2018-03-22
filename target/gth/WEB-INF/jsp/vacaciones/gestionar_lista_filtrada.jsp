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
				<div id="table_contenido" class="col s12 m12 l12"></div>
				<br> <a id="confirmar_lista" class="btn waves-effect waves-light right"><i
					class="mdi-navigation-check"></i> Confirmar</a>
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
 		listarTrabajadorFiltrado();
	})
	
$("#confirmar_lista").click(function(){
	$('#confirmar_lista').addClass('disabled');
	$.get("confirmarListaFiltrada", function(data, status) {
		console.log(data);
		if(data == 1){
			var $toastContent = $('<span>Lista filtrada correctamente</span>');
			Materialize.toast($toastContent, 10000);	
			listarTrabajadorFiltrado();
			$('#confirmar_lista').removeClass('disabled');
		}
		if(data == 3){
			var $toastContent = $('<span>No existe consolidado activo</span>');
			Materialize.toast($toastContent, 10000);
			$('#confirmar_lista').removeClass('disabled');
		}
		if(data == 0){
			var $toastContent = $('<span>Error interno</span>');
			Materialize.toast($toastContent, 10000);
			$('#confirmar_lista').removeClass('disabled');
		}
	});
});
	
	function listarTrabajadorFiltrado()
	{
		 $.get('readallTrabajadorFiltrado', function (obj) {
			 console.log(obj);
		        var s='';
		        var emp = obj[0];
		        for (var i = 0; i < obj.length; i++) {
					var con = 8;
						if (obj[i].LI_CONDICION == 1) {
							con = "CONTRATADO";
						}
						if (obj[i].LI_CONDICION == 2) {
							con = "EMPLEADO";
						}
						if (obj[i].LI_CONDICION == 3) {
							con = "MISIONERO";
						}
						if (obj[i].LI_CONDICION == 4) {
							con = "PRACTICAS PRE-PROFESIONALES";
						}
						if (obj[i].LI_CONDICION == 5) {
							con = "PRACTICAS PROFESIONALES";
						}
						if (obj[i].LI_CONDICION == 6) {
							con = "CONVENIO LABORAL JUVENIL";
						}
						if (obj[i].LI_CONDICION == 7) {
							con = "CONTRATO";
						}
					s += '<tr>';
		            s += '<td>'+obj[i].AP_PATERNO+' '+obj[i].AP_MATERNO+' '+obj[i].NO_TRABAJADOR+'</td>';
		            s += '<td>'+obj[i].NO_DEP+'</td>';
		            s += '<td>'+obj[i].NO_DEP+'</td>';
		            s += '<td>'+obj[i].NO_AREA+'</td>';
		            s += '<td>'+obj[i].NO_SECCION+'</td>';
		            s += '<td>'+con+'</td>';
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
	    var s = '<table id="data-table-row-grouping" class="display" cellspacing="0" width="100%">';
	    s += '<thead>';
	    s += '<tr>';
	    s += '<th>Apellidos y Nombres</th>';
	    s += '<th>�rea</th>';
	    s += '<th>Departamento</th>';
	    s += '<th>�rea</th>';
	    s += '<th>Secci�n</th>';
	    s += '<th>Condici�n</th>';
	    s += ' </tr>';
	    s += '</thead>';
	    s += '<tbody id="data"></tbody>';
	    s += '</table>';
	    return s;
	    
	};
</script>
</html>