<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
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
			<p><h4 class="header">Programacion Vacaciones</h4></p>

			<div class="row">
				<div class="col s12 m8 l9">
					<table id="data-table-simple" class="responsive-table display"
						cellspacing="0">
						<thead>
							<tr>
								<th>N°</th>
								<th>Nombres y Apellidos</th>
								<th>Area</th>
								<th>Seccion</th>
								<th>Fecha-Inicio</th>
								<th>Fecha-Fin</th>
								<th>Modificar</th>
								<td>Programar</td>
								<td>Aprobar </td>
								
							</tr>
						</thead>

						<tbody>
							<tr>
								<td>1</td>
								<td>Carlos</td>
								<td>Edinburgh</td>
								<td>61</td>
								<td>2011/04/25</td>
								<td>2011/04/25</td>
 <td>                   <!-- Modal Trigger -->
  <a class="waves-effect waves-light btn modal-trigger light-blue" href="#modal2">&#9998;</a>

  <!-- Modal Structure -->
  <div id="modal2" class="modal" style="height: 90%;">
    <div class="modal-content">
      <h4>Modificar fecha de inicio y fecha fin</h4>
      <p>Seleccione fecha:</p>
      <div class="row">
      <div class="col s8 m6 l6">
       <p>Seleccione fecha de inicio:</p>
        <input type="text" class="datepicker">
        </div>
        <div class="col s8 m6 l6">
       <p>Seleccione fecha de fin:</p>
        <input type="text" class="datepicker">
        </div>
        </div>
    </div>
    <div class="modal-footer">
      <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Modificar</a>
    </div>
  </div></td>
                        <td>                   <!-- Modal Trigger -->
  <a class="waves-effect waves-light btn modal-trigger light-pink" href="#modal1">&#128198;</a>

  <!-- Modal Structure -->
  <div id="modal1" class="modal"> 
    <div class="modal-content">
      
      <div class="row">
    <form class="col s12">
      <div class="row">
        <div class="input-field col s6">
          <input disabled value="Programacion/Reprogramacion" id="disabled" type="text" class="validate">
          <label for="disabled">Tipo</label>
        </div>
      </div>
      <div class="row">
        <div class="input-field col s6">
          <input id="text" type="text" class="validate">
          <label for="text">Nombres y Apellidos:</label>
        </div>
        <div class="input-field col s6">
          <input id="dni" type="text" class="validate">
          <label for="dni">DNI:</label>
        </div>
      </div>
      <h5>Programación de Horarios</h5>
      <div class="row">
        <div class="input-field col s12">
          <input id="email" type="email" class="validate">
          <label for="email">Email</label>
        </div>
      </div>
      <div class="row">
        <div class="col s12">
          This is an inline input field:
          <div class="input-field inline">
            <input id="email" type="email" class="validate">
            <label for="email" data-error="wrong" data-success="right">Email</label>
          </div>
        </div>
      </div>
    </form>
  </div>
    </div>
    <div class="modal-footer">
      <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">Agree</a>
    </div>
  </div></td>
                          <td>         <p>
                      <input type="checkbox" id="test5">
                      <label for="test5"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">Aprobar</font></font></label>
                    </p> </td>
							</tr>
					
						</tbody>
					</table>
				</div>
				
			</div>
			
		</div>
	<center>
            <div class="row">
            <div class="col s24 m12 l6 ">
                          <p><a class="btn waves-effect waves-light indigo">Cuaderno de Vacaciones</a></p>
             </div>
             <div class="col s24 m12 l6">
                          <p><a class="btn waves-effect waves-light light-green darken-4">Confirmar</a></p>
                        </div>
                        </div></center>	
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

</body>
</html>