<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="../../../jspf/general.jspf"%>
<title>Insert title here</title>
</head>
<style>
.responsive-object object {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
}

.responsive-object {
	position: relative;
	padding-bottom: 67.5%;
	height: 0;
	margin: 10px 0;
	overflow: hidden;
}
</style>
<body>
	<%@include file="../../../jspf/header.jspf"%>
	<div id="main">
		<div class="wrapper">
			<%@include file="../../../jspf/aside_left.jspf"%>
			<%@include file="../../../jspf/info_puesto.jspf"%>
			<!-- 			<section id="content" class="col m12 l12 s12"> -->



			<div class="container">
				<hr>
				<br>
				<div class="row">
				<div class="input-field col s12 m4 l4">
									<i class="mdi-notification-event-note large left prefix"></i> <input
										id="fecha" ;
										type="text" class="datepicker">
									<label for="fecha">Fecha de la renuncia</label>

								</div>
				
				</div>
				
				<center>
					<div class="form-group">
						<a
							class="btn-floating btn-large waves-effect waves-light green accent-3"
							href="<c:url value="/trabajador/print"/>" target="_blank"><i
							class="mdi-action-print"></i></a> <a
							class="btn-floating btn-large waves-effect waves-light  light-green accent-3"
							href="<c:url value="/"/>"><i class="mdi-content-backspace"></i></a>
					</div>
				</center>
				<hr>
				<br>

				<div class="responsive-object" style="margin-bottom: 4em">
					<center>
						<object type="application/pdf" data=""></object>
					</center>
				</div>

			</div>
			<hr>


		</div>


	</div>
	<div style="position: fixed; width: 100%; bottom: 0; z-index: 5">
		<%@include file="../../../jspf/footer.jspf"%>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			
			prueba1();
		});
		 $('#fecha').pickadate({
		    	selectMonths: true, // Creates a dropdown to control month
		    	selectYears: 15, // Creates a dropdown of 15 years to control
		    	format: 'dd/mm/yyyy',
		      });
		function prueba1() {
			console.log("alert :V");
			$.get(gth_context_path + "/trabajador/print", function(objJson) {
				$("object")
						.attr('data', gth_context_path + "/trabajador/print")
						.hide().show();
				console.log("lo está llenando chico");
				// 				alert(objJson);
				// 				console.log(objJson.hola);
				//		        var men = JSON.parse(data); 
				//alert(prod[0].idproducto);

			});
		}
	</script>
</body>
</html>