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
			<h4 class="header">Aprobar Programa de Vacaciones</h4>

			<div class="row">
				<div class="col s12 m8 l9">
					<table id="data-table-simple" class="responsive-table display"
						cellspacing="0">
						<thead>
							<tr>
								<th>N°</th>
								<th>Nombres</th>
								<th>Seccion</th>
								<th>Dias Totales</th>
								<th>Condicion</th>
								<th>Aprobar</th>
								<th>Observacion</th>
							</tr>
						</thead>

						<tbody>
							<tr>
								<td>1</td>
								<td>Carlos</td>
								<td>Edinburgh</td>
								<td>61</td>
								<td>2011/04/25</td>
								<td> 
								   <p>
                      <input type="checkbox" id="test5">
                      <label for="test5"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">Aprobar</font></font></label>
                    </p> </td><td>
  <a class="waves-effect waves-light btn modal-trigger light-blue" href="#modal2">&#128065;</a>  <!-- Modal Structure -->
  <div id="modal2" class="modal">
    <div class="modal-content">
      <center><h4>Observacion</h4></center>
       <div class="row">
    <form class="col s12">
      <div class="row">
        <div class="input-field col s12">
          <textarea id="textarea1" class="materialize-textarea"></textarea>
          <label for="textarea1">Escriba Observacion</label>
        </div>
      </div>
    </form>
  </div>
    </div>
    <div class="modal-footer">
      <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">OK!</a>
    </div>
  </div>
  </td>

							</tr>
							<tr>
								<td>2</td>
								<td>Nicole</td>
								<td>Edinburgh</td>
								<td>60</td>
								<td>2011/04/25</td>
								<td> 
								   <p>
                      <input type="checkbox" id="test6">
                      <label for="test6"><font style="vertical-align: inherit;"><font style="vertical-align: inherit;">Aprobar</font></font></label>
                    </p> </td><td>
  <a class="waves-effect waves-light btn modal-trigger light-blue" href="#modal2">&#128065;</a>  <!-- Modal Structure -->
  <div id="modal2" class="modal">
    <div class="modal-content">
      <center><h4>Observacion</h4></center>
       <div class="row">
    <form class="col s12">
      <div class="row">
        <div class="input-field col s12">
          <textarea id="textarea1" class="materialize-textarea"></textarea>
          <label for="textarea1">Escriba Observacion</label>
        </div>
      </div>
    </form>
  </div>
    </div>
    <div class="modal-footer">
      <a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat">OK!</a>
    </div>
  </div>
  </td>
  </td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="col s24 m12 l6">
                          <p><a class="btn btn-large waves-effect waves-light light-green darken-4">Confirmar</a></p>
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
	<%@include file="../../../resources/js/businessCore/jsAutorizar.jspf"%>
<body>

</body>
</html>