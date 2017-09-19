<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>GTH</title>
<%@include file="../../jspf/general.jspf"%>
<style>
.circulo {
	width: 200px;
	height: 200px;
	-moz-border-radius: 50%;
	-webkit-border-radius: 50%;
	border-radius: 50%;
	display: inline-block;
	cursor: pointer;
}

.circulo:hover {
	-webkit-transform: scale(1.3);
	-ms-transform: scale(1.3);
	transform: scale(1.3);
}

.circulo2 {
	width: 100px;
	height: 100px;
	-moz-border-radius: 50%;
	-webkit-border-radius: 50%;
	border-radius: 50%;
	margin: 25%;
}

.size-icon {
	margin-top: 25px;
	margin-right: 5px;
	margin-left: 5px;
	margin-bottom: 25px;
	color: white;
}
</style>
</head>
<body>
	<div id="loader-wrapper">
		<div id="loader"></div>
		<div class="loader-section section-left"></div>
		<div class="loader-section section-right"></div>
	</div>

	<div id="contMod">
		<div class='circulo' style="background: red">
			<div class='circulo2'>
				<i class="mdi-social-person-add large icon-demo size-icon"></i>
			</div>
		</div>
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
			var s = '<div class="circulo" style="background: '+color+'">';
			s += "<div class='circulo2'>";
			s += '<i class="'+icon+' large icon-demo size-icon"></i>';
			s += '</div>';
			s += '</div>';
			console.log(s);
			return s;
		}

		function enterMod(a) {
			var m = a.toString();
			var id;
			if (m === "10" || m === "11") {
				id = "MOD-00" + m;
			} else {
				id = "MOD-000" + m;
			}
			try {
				$.get("components?opc=redMod", "idmod=" + id,
						function(objJson) {
							if (objJson.rpta) {
								location.href = 'index';
							}
						});
			} catch (e) {
				console.error(e);
			}
		}
	</script>

</body>
</html>