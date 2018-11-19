<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>

<link
	href="<c:url value='/resources/js/plugins/datepickk-master/dist/datepickk.css'/>"
	type="text/css" rel="stylesheet">
<link
	href="<c:url value='/resources/js/plugins/datepickk-master/dist/grid.css'/>"
	type="text/css" rel="stylesheet">
</head>

<body>
	<button id="btncargarcalendar" class="" style="display: none;">mostrar</button>
	<div class="row" id="alldatepicker">
		<div id="datePicker" class="row"
			style="height: 400px; width: 100%; max-width: 300px;"></div>
	</div>
</body>
</html>