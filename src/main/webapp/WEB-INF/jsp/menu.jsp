<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GTH</title>
<%@include file="../../jspf/general.jspf"%>
<link rel="stylesheet" href="<c:url value='resources/css/custom/menu.css'/>" />
<link href="<c:url value='resources/css/layouts/page-center.css" type="text/css'/>" 	rel="stylesheet" media="screen,projection">

</head>
<body>	
	<div id="contMod">
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			listarModulos();
		});
		function listarModulos() {
			var url = "components";
			var data = "opc=modulos";
			$.getJSON(url, data, function(objJson) {
				var list = objJson.lista;
				var s = "";
				if (list.length > 0) {
					for (var i = 0; i < list.length; i++) {
						var idmod = list[i].ID_MODULO;
						var nom = list[i].NO_MODULO;
						var ico = list[i].IC_MODULO;
						var color = list[i].CO_MODULO;
						s += createModulo(idmod, nom, ico, color);
					}
					$("#contMod").empty();
					$("#contMod").append(s);
				}
			});
		}

		function createModulo(idmodulo, nombre, icon, color) {
			var m = idmodulo.split("-");
			var n = parseInt(m[1]);
			var s = '<div class="circulo" style="background: '+color+'" id="'+idmodulo+'" onclick="show(this.id)">';
			s += "<div class='circulo2'>";
			s += '<i class="'+icon+' large icon-demo size-icon"></i>';
			s += '</div>';
			s += '</div>';
			return s;
		}
		
		function show(id){
			try {
				$.getJSON("components?opc=redMod", "idmod=" + id,
						function(objJson) {
							if (objJson.rpta) {
								location.href = 'home';
							}
						});
			} catch (e) {
				console.error(e);
			}
		}
		
	</script>

</body>
</html>