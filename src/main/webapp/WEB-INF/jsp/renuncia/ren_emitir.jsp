<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="../../../jspf/general.jspf"%>
<title>Insert title here</title>
</head>
<body>
	<%@include file="../../../jspf/header.jspf"%>
	<div id="main">
		<div class="wrapper">
			<%@include file="../../../jspf/aside_left.jspf"%>
			<%@include file="../../../jspf/info_puesto.jspf"%>
			<!-- 			<section id="content" class="col m12 l12 s12"> -->



			<div class="container">
			<center>
				<div class="form-group">
					<a
						class="btn-floating btn-large waves-effect waves-light green accent-3"
						href="<c:url value="/trabajador/print"/>"><i
						class="mdi-action-print"></i></a> <a
						class="btn-floating btn-large waves-effect waves-light  light-green accent-3"
						href="<c:url value="/"/>"><i class="mdi-content-backspace"></i></a>
				</div>
				</center>
				<hr>
				<br>
				<div class="">
                    <center>
					<object type="application/pdf"
						data="http://localhost:8081/gth/trabajador/print" width="500"
						height="650"></object>
					</center>
				</div>


			</div>
			<hr>


		</div>


	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			// 			prueba1();
		});

		function prueba1() {
			console.log("alert :V");
			$.get(gth_context_path + "/trabajador/print", function(objJson) {
				console.log("asdasdasd");
				alert(objJson);
				// 				console.log(objJson.hola);
				//		        var men = JSON.parse(data); 
				//alert(prod[0].idproducto);

			});
		}
	</script>
</body>
</html>