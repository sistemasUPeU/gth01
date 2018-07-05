<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="../../../jspf/general.jspf"%>
<link href="https://fonts.googleapis.com/css?family=Cormorant+Garamond"
	rel="stylesheet">
<style>
.z:hover {
	background-color: black;
}
</style>
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
			<%@include file="../../../jspf/info_puesto.jspf"%>

			<div class="">
				<!-- 				<center> -->
				<!-- 					<object type="image/jpeg" -->
				<!-- 						data="/renaban/mostrardoc1" width="500" -->
				<!-- 						height="650"></object> -->
				<!-- 				</center> -->
			</div>
			<div id="content" class="col s12">
				<div class="center" id="renaban">
					<h1
						style="font-family: 'Cormorant Garamond', serif; font-weight: bold">Gestión
						de Renuncias y Abandonos</h1>
					<div class="row">
						<div class="col l6">
							<a class="btn btn-large waves-effect waves-light z"
								href="/gth/renaban/registrationR"><i
								class="large mdi-action-swap-vert-circle"></i> Registrar
								Renuncia</a>
						</div>
						<div class="col l6">
							<a class="btn btn-large waves-effect waves-light z"
								href="/gth/renaban/registrationA"><i
								class="large mdi-action-swap-vert-circle"></i> Registrar
								Abandono</a>
						</div>
					</div>
				</div>



			</div>

		</div>


	</div>
	<script>
	$(document).ready(function(){
		$.get(gth_context_path+"/components",{opc:'puesto'}, function(data){
			if(data.info_puesto.idrol== "ROL-0002"){
				$("#renaban").show();
			}else{
				$("#renaban").hide();
			}
			
		});
		
	});
		
	</script>
<body>
</html>