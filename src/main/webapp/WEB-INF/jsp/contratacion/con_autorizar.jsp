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
			<div class="col s12 m8 l9">
				<table id="data-table-simple" class="responsive-table display" cellspacing="0">
					<thead>
						<tr>
							<th>N°</th>
							<th>Acción</th>
							<th>Mes</th>
							<th>Foto</th>
							<th>Apellidos y Nombres</th>
							<th>Puesto</th>
							<th>Area</th>
							<th>Departamento</th>
							<th>Requerimiento</th>
							<th>Descripción</th>
							<th>Fecha de Creación</th>
							<th>Motivo</th>
							<th>MFL</th>
							<th>¿Cumplió Plazos?</th>
							<th>Código Huella</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th>N°</th>
							<th>Acción</th>
							<th>Mes</th>
							<th>Foto</th>
							<th>Apellidos y Nombres</th>
							<th>Puesto</th>
							<th>Area</th>
							<th>Departamento</th>
							<th>Requerimiento</th>
							<th>Descripción</th>
							<th>Fecha de Creación</th>
							<th>Motivo</th>
							<th>MFL</th>
							<th>¿Cumplió Plazos?</th>
							<th>Código Huella</th>
						</tr>
					</tfoot>
					<tbody>
						
						<tr>
							<td>1</td>
							<td><button class="btn-floating "><i class="mdi-action-settings"></i></button></td>
							<td>Octubre</td>
							<td>43</td>
							<td>2013/02/01</td>
							<td>$75,650</td>
							<td>Jennifer Acosta</td>
							<td>asddasdsaads</td>
							<td>Octubre</td>
							<td>43</td>
							<td>2013/02/01</td>
							<td>$75,650</td>
							<td>2013/02/01</td>
							<td>$75,650</td>
							<td>$75,650</td>
						</tr>
						
						<tr>
							<td>2</td>
							<td><button class="btn-floating "><i class="mdi-action-settings"></i></button></td>
							<td>Octubre</td>
							<td>43</td>
							<td>2013/02/01</td>
							<td>$75,650</td>
							<td>Jennifer Acosta</td>
							<td>asddassdasda</td>
							<td>Octubre</td>
							<td>43</td>
							<td>2013/02/01</td>
							<td>$75,650</td>
							<td>2013/02/01</td>
							<td>$75,650</td>
							<td>$75,650</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
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
		<script
		src="<c:url value='/resources/js/plugins/chartist-js/chartist.min.js'></c:url>"
		type="text/javascript"></script>
		<script type="text/javascript">
        /*Show entries on click hide*/
        $(document).ready(function(){
            $(".dropdown-content.select-dropdown li").on( "click", function() {
                var that = this;
                setTimeout(function(){
                if($(that).parent().hasClass('active')){
                        $(that).parent().removeClass('active');
                        $(that).parent().hide();
                }
                },100);
            });
        });
    </script>
<body>
</html>