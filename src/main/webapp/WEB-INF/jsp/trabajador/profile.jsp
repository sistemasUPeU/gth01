<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<%@include file="../../../jspf/general.jspf"%>
</head>
<body>
	<%@include file="../../../jspf/header.jspf"%>
	<div id="main">
		<div class="wrapper">
			<%@include file="../../../jspf/aside_left.jspf"%>
			<%@include file="../../../jspf/info_puesto.jspf"%>
			<section id="content" class="col m12 l12 s12">
				<div id="profile-page-content" class="row">
					<div class="row card-panel">
						<div class="col l1 m1 s6">
							<img src="<c:url value="/resources/img/user.png"/>" alt=""
								class="circle responsive-img valign profile-image">
						</div>
						<div class="col l3 m3 s12">
							<button class="btn green accent-3 waves-effect waves-light">
								<i class="mdi-image-camera-alt left"></i>Cambiar Foto
							</button>
							<h6 class="light italic black-text">
								<strong><label>Nombres : </label></strong> Leandro Jair<br>
								<strong><label>Apellido Paterno : </label></strong> Burgos<br>
								<strong><label>Apellido Materno : </label></strong> Robles<br>
								<strong><label>Fecha de Nacimiento : </label></strong>
								26/10/1998
							</h6>
						</div>

						<div class="col l4 m4 s12">
						
						</div>
					</div>

					<div class="col s12">
						<ul class="tabs tab-demo z-depth-1">
							<li class="tab col s3"><a class="active" href="#test1">Información
									Contractual</a></li>
							<li class="tab col s3"><a href="#test2" class="">General</a>
							</li>
							<li class="tab col s3"><a href="#test3" class="">Académico</a>
							</li>
							<li class="tab col s3"><a href="#test4" class="">Aspecto
									Social</a></li>
							<li class="tab col s3"><a href="#test4" class="">Familiares</a>
							</li>
							<li class="tab col s3"><a href="#test4" class="">Documentos</a>
							</li>
							<li class="tab col s3"><a href="#test4" class="">Evaluación</a>
							</li>
						</ul>
					</div>
				</div>
			</section>

		</div>


	</div>

	<%@include file="../../../jspf/footer.jspf"%>
	<script src="<c:url value='/resources/js/businessCore/connection.js'></c:url>"
	type="text/javascript"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		var connector = new jsConnector();
		connector.post("trabajador/Form_Reg", JSON.stringify({idTrabajador : 1}), function (result){
			console.log(result);
		});
	});
	</script>
<body>
</html>