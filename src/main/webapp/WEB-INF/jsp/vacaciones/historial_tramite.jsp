<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
<style type="text/css">
.center-btn {
	text-align: center
}

div.dataTables_length {
	display: none !important;
}

#confirmar_lista {
	margin-top: 25px;
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
			<section id="content"></section>
		</div>
		<%@include file="../../../jspf/info_puesto.jspf"%>
		<div class="container">
			<div class="row">
				<div id="table_contenido" class="col s12 m12 l12"></div>
			</div>
			<div id="modal" class="modal">
				<div class="modal-content">
					<h4 class="center-align">DETALLE DE VACACIONES</h4>
					<div id="contenedor_detalles">
						<div class="row">
							<table>
								<thead>
									<tr>
										<th> </th>
										<th>Acción</th>
										<th>Estado</th>
										<th>Fecha</th>
										<th> </th>
									</tr>
								</thead>
								<thead><tr>
									<td>
										<a id="b_programacion" class="btn-floating disabled"></i></a>
									</td>
									<td id="t_programacion"> </td>
									<td id="s_programacion"> </td>
									<td id="f_programacion"> </td>
									<td><a id="d_programacion" class="waves-effect waves-light btn disabled"><i class="mdi-content-content-paste"></i></a></td>
								</tr></thead>
								<thead><tr>
									<td rowspan=2>
										<a id="b_aprobacion" class="btn-floating disabled"><i class=""></i></a>
									</td>
									<td rowspan=2>APROBACIÓN:</td>
									<td id="s1_aprobacion"> </td>
									<td id="f1_aprobacion"> </td>
									<td rowspan=2> </td>
								</tr>
								<tr>
									<td id="s2_aprobacion"> </td>
									<td id="f2_aprobacion"> </td>
								</tr></thead>
								<thead><tr>
									<td>
										<a id="b_consolidar" class="btn-floating disabled"><i class=""></i></a>
									</td>
									<td>CONSOLIDAR:</td>
									<td id="s_consolidar"> </td>
									<td id="f_consolidar"> </td>
									<td> </td>
								</tr></thead>
								<thead><tr>
									<td>
										<a id="b_papeleta" class="btn-floating disabled"><i class=""></i></a>
									</td>
									<td>PAPELETA:</td>
									<td id="s_papeleta"> </td>
									<td id="f_papeleta"> </td>
									<td><a id="d_papeleta" class="waves-effect waves-light btn disabled"><i class="mdi-content-content-paste"></i></a></td>
								</tr></thead>
								<thead><tr>
									<td rowspan=2>
										<a id="b_control" class="btn-floating disabled"><i class=""></i></a>
									</td>
									<td rowspan=2>CONTROL FIRMA:</td>
									<td id="s1_control"> </td>
									<td id="f1_control"> </td>
									<td rowspan=2> </td>
								</tr>
								<tr>
								<td id="s2_control"> </td>
								<td id="f2_control"> </td>
								</tr></thead>
							</table>
						</div>
					</div>
				</div>
				<div class="modal-footer">
      				<a href="#!" class="modal-close waves-effect waves-green btn-flat">Cerrar</a>
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
</body>

<script type="text/javascript">
	$(document).ready(function() {
		$('.tooltipped').tooltip();
		listarHistorialTramite();
	});

	$("#table_contenido").on("click", "#open", function() {
		$("#b_programacion").empty();
		$("#t_programacion").empty();
		$("#s_programacion").empty();
		$("#f_programacion").empty();
		$("#d_programacion").removeAttr("href").addClass("disabled");
		$("#b_aprobacion").empty();
		$("#s1_aprobacion").empty();
		$("#s2_aprobacion").empty();
		$("#f1_aprobacion").empty();
		$("#f2_aprobacion").empty();
		$("#b_consolidar").empty();
		$("#s_consolidar").empty();
		$("#f_consolidar").empty();
		$("#b_papeleta").empty();
		$("#s_papeleta").empty();
		$("#f_papeleta").empty();
		$("#d_papeleta").removeAttr("href").addClass("disabled");
		$("#b_control").empty();
		$("#s1_control").empty();
		$("#s2_control").empty();
		$("#f1_control").empty();
		$("#f2_control").empty();
		var fecha;
		var FECHA_MOD_CS;
		var FECHA_MOD_AP1;
		var FECHA_MOD_AP2;
		var FECHA_MOD_CO;
		var FECHA_MOD_PA;
		var FECHA_MOD_FI1;
		var FECHA_MOD_FI2;
		var id = $(this).attr("name");
		console.log(id);
		var j = "";
		var aa = '<i class="mdi-action-label-outline">';
		var bb = '<i class="mdi-content-clear">';
		var cc = '<i class="mdi-action-done">';
		var dd = 'Sin Solicitud';
		var ee = 'Solicitud Hecha';
		var ff = 'SECRETARIA: Sin Aprobar';
		var gg = 'SECRETARIA: Aprobado';
		var hh = 'JEFE: Sin Aprobar';
		var ii = 'JEFE: Aprobado';
		var jj = 'En Proceso (0/2)';
		var kk = 'En Proceso (1/2)';
		var ll = 'En Proceso (2/2)';
		var mm = 'Hecho';
		var nn = 'Sin Entregar';
		var oo = 'Entregado';
		var pp = 'FECHA SALIDA: No Hecho';
		var qq = 'FECHA SALIDA: Hecho';
		var rr = 'FECHA ENTRADA: No Hecho';
		var ss = 'FECHA ENTRADA: Hecho';
		var tt = { year: 'numeric', month: 'numeric', day: 'numeric' };
		var uu = '';
		var vv = '';
		var ww = '';
		var xx = '';
		var yy = '';
		var zz = '';
		if (id == 'undefined') {
			$("#b_programacion").removeClass("disabled").addClass("pink lighten-2");
			$("#b_aprobacion").addClass("disabled");
			$("#b_consolidar").addClass("disabled");
			$("#b_papeleta").addClass("disabled");
			$("#b_control").addClass("disabled");
			$("#b_programacion").append(aa);
			$("#b_aprobacion").append(bb);
			$("#b_consolidar").append(bb);
			$("#b_papeleta").append(bb);
			$("#b_control").append(bb);
			$("#t_programacion").append("PROGRAMACIÓN:");
			$("#s_programacion").append(dd);
		} else {
			$.get('readHistorialTramite', {id:id}, function (obj) {
				console.log(obj);
				for (var i = 0; i < obj.length; i++) {
					var ID_PASOSOS = $.trim(obj[i].ID_PASOS);
					console.log(ID_PASOSOS + " °°°");
					if (ID_PASOSOS == "PAS-000055" && obj[i].EVALUACION == "1" && obj[i].URL_SOLICITUD != null || ID_PASOSOS == "PAS-000055" && obj[i].EVALUACION == "4" && obj[i].URL_SOLICITUD != null) {
						fecha = new Date(obj[i].FECHA_MOD);
						FECHA_MOD_CS = fecha.toLocaleDateString("es-ES", tt);
						console.log(obj[i]);
					}
					if (ID_PASOSOS == "PAS-000055" && obj[i].EVALUACION == "2") {
						fecha = new Date(obj[i].FECHA_MOD);
						FECHA_MOD_AP1 = fecha.toLocaleDateString("es-ES", tt);
						console.log(obj[i]);
					}
					if (ID_PASOSOS == "PAS-000055" && obj[i].EVALUACION == "3") {
						fecha = new Date(obj[i].FECHA_MOD);
						FECHA_MOD_AP2 = fecha.toLocaleDateString("es-ES", tt);
						console.log(obj[i]);
					}
					if (ID_PASOSOS == "PAS-000054" || ID_PASOS == "PAS-000052") { 
						fecha = new Date(obj[i].FECHA_MOD);
						FECHA_MOD_CO = fecha.toLocaleDateString("es-ES", tt);
						console.log(obj[i]);
					}
					if (ID_PASOSOS == "PAS-000090" && obj[i].FIRMA_SALIDA == 0 && obj[i].URL_PAPELETA != null) {
						fecha = new Date(obj[i].FECHA_MOD);
						FECHA_MOD_PA = fecha.toLocaleDateString("es-ES", tt);
						console.log(obj[i]);
					}
					if (ID_PASOSOS == "PAS-000092" && obj[i].FIRMA_SALIDA == 0 && obj[i].URL_PAPELETA != null) {
						fecha = new Date(obj[i].FECHA_MOD);
						FECHA_MOD_PA = fecha.toLocaleDateString("es-ES", tt);
						console.log(obj[i]);
					}
					if (ID_PASOSOS == "PAS-000092" && obj[i].FIRMA_SALIDA == 1 && obj[i].FIRMA_ENTRADA == 0) { 
						fecha = new Date(obj[i].FECHA_MOD);
						FECHA_MOD_FI1 = fecha.toLocaleDateString("es-ES", tt);
						console.log(obj[i]);
					}
					if (ID_PASOSOS == "PAS-000092" && obj[i].FIRMA_ENTRADA == 1) { 
						fecha = new Date(obj[i].FECHA_MOD);
						FECHA_MOD_FI2 = fecha.toLocaleDateString("es-ES", tt);
						console.log(obj[i]);
					}
					console.log(FECHA_MOD_CS, FECHA_MOD_AP1, FECHA_MOD_AP2, FECHA_MOD_CO, FECHA_MOD_PA, FECHA_MOD_FI1, FECHA_MOD_FI2);
					if (obj[i].ESTADO == 1) {
						var ID_PASOS = $.trim(obj[i].ID_PASOS);
						if (ID_PASOS == "PAS-000055" && obj[i].EVALUACION == "1" && obj[i].URL_SOLICITUD == null) {
							$("#b_programacion").removeClass("disabled").addClass("pink lighten-2");
							$("#b_programacion").append(aa);
							$("#b_aprobacion").append(bb);
							$("#b_consolidar").append(bb);
							$("#b_papeleta").append(bb);
							$("#b_control").append(bb);
							$("#s_programacion").append(dd);
							$("#f_programacion").append(obj[i].FECHA_CREACION);
							console.log("PROGRAMACION SS");
						} else if (ID_PASOS == "PAS-000055" && obj[i].EVALUACION == "1" && obj[i].URL_SOLICITUD != null) {
							$("#b_programacion").removeClass("disabled").addClass("green accent-3");
							$("#b_aprobacion").removeClass("disabled").addClass("pink lighten-2");
							$("#d_programacion").removeClass("disabled").addClass("green accent-3");
							$("#d_programacion").attr("href",gth_context_path+'/vacaciones/consolidado/mostrardoc?traba='+obj[i].ID_TRABAJADOR+'&id_det='+obj[i].ID_DET_VACACIONES+'&op=1');
							$("#d_programacion").attr("target","_blank");
							$("#b_programacion").append(cc);
							$("#b_aprobacion").append(aa);
							$("#b_consolidar").append(bb);
							$("#b_papeleta").append(bb);
							$("#b_control").append(bb);
							$("#s_programacion").append(ee);
							$("#s1_aprobacion").append(ff);
							$("#s2_aprobacion").append(hh);
							$("#f_programacion").append(FECHA_MOD_CS);
							console.log("PROGRAMACION CS");
						} else if (ID_PASOS == "PAS-000055" && obj[i].EVALUACION == "4" && obj[i].URL_SOLICITUD == null) {
							$("#b_programacion").removeClass("disabled").addClass("pink lighten-2");
							$("#b_programacion").append(aa);
							$("#b_aprobacion").append(bb);
							$("#b_consolidar").append(bb);
							$("#b_papeleta").append(bb);
							$("#b_control").append(bb);
							$("#s_programacion").append(dd);
							console.log("REPROGRAMACION SS");
						} else if (ID_PASOS == "PAS-000055" && obj[i].EVALUACION == "4" && obj[i].URL_SOLICITUD != null) {
							$("#b_programacion").removeClass("disabled").addClass("green accent-3");
							$("#b_aprobacion").removeClass("disabled").addClass("pink lighten-2");
							$("#d_programacion").removeClass("disabled").addClass("green accent-3");
							$("#d_programacion").attr("href",gth_context_path+'/vacaciones/consolidado/mostrardoc?traba='+obj[i].ID_TRABAJADOR+'&id_det='+obj[i].ID_DET_VACACIONES+'&op=1');
							$("#d_programacion").attr("target","_blank");
							$("#b_programacion").append(cc);
							$("#b_aprobacion").append(aa);
							$("#b_consolidar").append(bb);
							$("#b_papeleta").append(bb);
							$("#b_control").append(bb);
							$("#s_programacion").append(ee);
							$("#s1_aprobacion").append(ff);
							$("#s2_aprobacion").append(hh);
							$("#f_programacion").append(FECHA_MOD_CS);
							console.log("REPROGRAMACION CS");
						} else if (ID_PASOS == "PAS-000055" && obj[i].EVALUACION == "2") {
							$("#b_programacion").removeClass("disabled").addClass("green accent-3");
							$("#b_aprobacion").removeClass("disabled").addClass("pink lighten-2");
							$("#d_programacion").removeClass("disabled").addClass("green accent-3");
							$("#d_programacion").attr("href",gth_context_path+'/vacaciones/consolidado/mostrardoc?traba='+obj[i].ID_TRABAJADOR+'&id_det='+obj[i].ID_DET_VACACIONES+'&op=1');
							$("#d_programacion").attr("target","_blank");
							$("#b_programacion").append(cc);
							$("#b_aprobacion").append(aa);
							$("#b_consolidar").append(bb);
							$("#b_papeleta").append(bb);
							$("#b_control").append(bb);
							$("#s_programacion").append(ee);
							$("#s1_aprobacion").append(gg);
							$("#s2_aprobacion").append(hh);
							$("#f_programacion").append(FECHA_MOD_CS);
							$("#f1_aprobacion").append(FECHA_MOD_AP1);
							console.log("APROBADO SECRETARIA");
						} else if (ID_PASOS == "PAS-000055" && obj[i].EVALUACION == "3") {
							$("#b_programacion").removeClass("disabled").addClass("green accent-3");
							$("#b_aprobacion").removeClass("disabled").addClass("green accent-3");
							$("#d_programacion").removeClass("disabled").addClass("green accent-3");
							$("#d_programacion").attr("href",gth_context_path+'/vacaciones/consolidado/mostrardoc?traba='+obj[i].ID_TRABAJADOR+'&id_det='+obj[i].ID_DET_VACACIONES+'&op=1');
							$("#d_programacion").attr("target","_blank");
							$("#b_consolidar").removeClass("disabled").addClass("pink lighten-2");
							$("#b_programacion").append(cc);
							$("#b_aprobacion").append(cc);
							$("#b_consolidar").append(aa);
							$("#b_papeleta").append(bb);
							$("#b_control").append(bb);
							$("#s_programacion").append(ee);
							$("#s1_aprobacion").append(gg);
							$("#s2_aprobacion").append(ii);
							$("#s_consolidar").append(jj);
							$("#f_programacion").append(FECHA_MOD_CS);
							$("#f1_aprobacion").append(FECHA_MOD_AP1);
							$("#f2_aprobacion").append(FECHA_MOD_AP2);
							console.log("APROBADO JEFE");
						} else if (ID_PASOS == "PAS-000054") {
							$("#b_programacion").removeClass("disabled").addClass("green accent-3");
							$("#b_aprobacion").removeClass("disabled").addClass("green accent-3");
							$("#d_programacion").removeClass("disabled").addClass("green accent-3");
							$("#d_programacion").attr("href",gth_context_path+'/vacaciones/consolidado/mostrardoc?traba='+obj[i].ID_TRABAJADOR+'&id_det='+obj[i].ID_DET_VACACIONES+'&op=1');
							$("#d_programacion").attr("target","_blank");
							$("#b_consolidar").removeClass("disabled").addClass("pink lighten-2");
							$("#b_programacion").append(cc);
							$("#b_aprobacion").append(cc);
							$("#b_consolidar").append(aa);
							$("#b_papeleta").append(bb);
							$("#b_control").append(bb);
							$("#s_programacion").append(ee);
							$("#s1_aprobacion").append(gg);
							$("#s2_aprobacion").append(ii);
							$("#s_consolidar").append(kk);
							$("#f_programacion").append(FECHA_MOD_CS);
							$("#f1_aprobacion").append(FECHA_MOD_AP1);
							$("#f2_aprobacion").append(FECHA_MOD_AP2);
							$("#f_consolidar").append(FECHA_MOD_CO);
							console.log("EN PROCESO 1/2");
						} else if (ID_PASOS == "PAS-000052") {
							$("#b_programacion").removeClass("disabled").addClass("green accent-3");
							$("#b_aprobacion").removeClass("disabled").addClass("green accent-3");
							$("#d_programacion").removeClass("disabled").addClass("green accent-3");
							$("#d_programacion").attr("href",gth_context_path+'/vacaciones/consolidado/mostrardoc?traba='+obj[i].ID_TRABAJADOR+'&id_det='+obj[i].ID_DET_VACACIONES+'&op=1');
							$("#d_programacion").attr("target","_blank");
							$("#b_consolidar").removeClass("disabled").addClass("green accent-3");
							$("#b_papeleta").removeClass("disabled").addClass("pink lighten-2");
							$("#b_programacion").append(cc);
							$("#b_aprobacion").append(cc);
							$("#b_consolidar").append(cc);
							$("#b_papeleta").append(aa);
							$("#b_control").append(bb);
							$("#s_programacion").append(ee);
							$("#s1_aprobacion").append(gg);
							$("#s2_aprobacion").append(ii);
							$("#s_consolidar").append(mm);
							$("#s_papeleta").append(nn);
							$("#f_programacion").append(FECHA_MOD_CS);
							$("#f1_aprobacion").append(FECHA_MOD_AP1);
							$("#f2_aprobacion").append(FECHA_MOD_AP2);
							$("#f_consolidar").append(FECHA_MOD_CO);
							console.log(FECHA_MOD_AP1, FECHA_MOD_AP2, FECHA_MOD_CO);
							console.log("EN PROCESO 2/2 xd");
						} else if (ID_PASOS == "PAS-000090" && obj[i].FIRMA_SALIDA == 0 && obj[i].URL_PAPELETA != null) {
							$("#b_programacion").removeClass("disabled").addClass("green accent-3");
							$("#b_aprobacion").removeClass("disabled").addClass("green accent-3");
							$("#d_programacion").removeClass("disabled").addClass("green accent-3");
							$("#d_programacion").attr("href",gth_context_path+'/vacaciones/consolidado/mostrardoc?traba='+obj[i].ID_TRABAJADOR+'&id_det='+obj[i].ID_DET_VACACIONES+'&op=1');
							$("#d_programacion").attr("target","_blank");
							$("#b_consolidar").removeClass("disabled").addClass("green accent-3");
							$("#b_papeleta").removeClass("disabled").addClass("green accent-3");
							$("#d_papeleta").removeClass("disabled").addClass("green accent-3");
							$("#d_papeleta").attr("href",gth_context_path+'/vacaciones/consolidado/mostrardoc?traba='+obj[i].ID_TRABAJADOR+'&id_det='+obj[i].ID_DET_VACACIONES+'&op=2');
							$("#d_papeleta").attr("target", "_blank");
							$("#b_control").removeClass("disabled").addClass("pink lighten-2");
							$("#b_programacion").append(cc);
							$("#b_aprobacion").append(cc);
							$("#b_consolidar").append(cc);
							$("#b_papeleta").append(cc);
							$("#b_control").append(bb);
							$("#s_programacion").append(ee);
							$("#s1_aprobacion").append(gg);
							$("#s2_aprobacion").append(ii);
							$("#s_consolidar").append(mm);
							$("#s_papeleta").append(oo);
							$("#s1_control").append(pp);
							$("#s2_control").append(rr);
							$("#f_programacion").append(FECHA_MOD_CS);
							$("#f1_aprobacion").append(FECHA_MOD_AP1);
							$("#f2_aprobacion").append(FECHA_MOD_AP2);
							$("#f_consolidar").append(FECHA_MOD_CO);
							$("#f_papeleta").append(FECHA_MOD_PA);
							console.log("PAPELETA ENTREGADA");
						} else if (ID_PASOS == "PAS-000092" && obj[i].FIRMA_SALIDA == 0 && obj[i].URL_PAPELETA != null) {
							$("#b_programacion").removeClass("disabled").addClass("green accent-3");
							$("#b_aprobacion").removeClass("disabled").addClass("green accent-3");
							$("#d_programacion").removeClass("disabled").addClass("green accent-3");
							$("#d_programacion").attr("href",gth_context_path+'/vacaciones/consolidado/mostrardoc?traba='+obj[i].ID_TRABAJADOR+'&id_det='+obj[i].ID_DET_VACACIONES+'&op=1');
							$("#d_programacion").attr("target","_blank");
							$("#b_consolidar").removeClass("disabled").addClass("green accent-3");
							$("#b_papeleta").removeClass("disabled").addClass("green accent-3");
							$("#d_papeleta").removeClass("disabled").addClass("green accent-3");
							$("#d_papeleta").attr("href",gth_context_path+'/vacaciones/consolidado/mostrardoc?traba='+obj[i].ID_TRABAJADOR+'&id_det='+obj[i].ID_DET_VACACIONES+'&op=2');
							$("#d_papeleta").attr("target", "_blank");
							$("#b_control").removeClass("disabled").addClass("pink lighten-2");
							$("#b_programacion").append(cc);
							$("#b_aprobacion").append(cc);
							$("#b_consolidar").append(cc);
							$("#b_papeleta").append(cc);
							$("#b_control").append(bb);
							$("#s_programacion").append(ee);
							$("#s1_aprobacion").append(gg);
							$("#s2_aprobacion").append(ii);
							$("#s_consolidar").append(mm);
							$("#s_papeleta").append(oo);
							$("#s1_control").append(pp);
							$("#s2_control").append(rr);
							$("#f_programacion").append(FECHA_MOD_CS);
							$("#f1_aprobacion").append(FECHA_MOD_AP1);
							$("#f2_aprobacion").append(FECHA_MOD_AP2);
							$("#f_consolidar").append(FECHA_MOD_CO);
							$("#f_papeleta").append(FECHA_MOD_PA);
							console.log("PAPELETA ENTREGADA");
						} else if (ID_PASOS == "PAS-000092" && obj[i].FIRMA_SALIDA == 1 && obj[i].FIRMA_ENTRADA == 0) {
							$("#b_programacion").removeClass("disabled").addClass("green accent-3");
							$("#b_aprobacion").removeClass("disabled").addClass("green accent-3");
							$("#d_programacion").removeClass("disabled").addClass("green accent-3");
							$("#d_programacion").attr("href",gth_context_path+'/vacaciones/consolidado/mostrardoc?traba='+obj[i].ID_TRABAJADOR+'&id_det='+obj[i].ID_DET_VACACIONES+'&op=1');
							$("#d_programacion").attr("target","_blank");
							$("#b_consolidar").removeClass("disabled").addClass("green accent-3");
							$("#b_papeleta").removeClass("disabled").addClass("green accent-3");
							$("#d_papeleta").removeClass("disabled").addClass("green accent-3");
							$("#d_papeleta").attr("href",gth_context_path+'/vacaciones/consolidado/mostrardoc?traba='+obj[i].ID_TRABAJADOR+'&id_det='+obj[i].ID_DET_VACACIONES+'&op=2');
							$("#d_papeleta").attr("target", "_blank");
							$("#b_control").removeClass("disabled").addClass("pink lighten-2");
							$("#b_programacion").append(cc);
							$("#b_aprobacion").append(cc);
							$("#b_consolidar").append(cc);
							$("#b_papeleta").append(cc);
							$("#b_control").append(bb);
							$("#s_programacion").append(ee);
							$("#s1_aprobacion").append(gg);
							$("#s2_aprobacion").append(ii);
							$("#s_consolidar").append(mm);
							$("#s_papeleta").append(oo);
							$("#s1_control").append(qq);
							$("#s2_control").append(rr);
							$("#f_programacion").append(FECHA_MOD_CS);
							$("#f1_aprobacion").append(FECHA_MOD_AP1);
							$("#f2_aprobacion").append(FECHA_MOD_AP2);
							$("#f_consolidar").append(FECHA_MOD_CO);
							$("#f_papeleta").append(FECHA_MOD_PA);
							$("#f1_control").append(FECHA_MOD_FI1);
							console.log("FIRMA SALIDA");
						} else if (ID_PASOS == "PAS-000092" && obj[i].FIRMA_ENTRADA == 1) {
							$("#b_programacion").removeClass("disabled").addClass("green accent-3");
							$("#b_aprobacion").removeClass("disabled").addClass("green accent-3");
							$("#d_programacion").removeClass("disabled").addClass("green accent-3");
							$("#d_programacion").attr("href",gth_context_path+'/vacaciones/consolidado/mostrardoc?traba='+obj[i].ID_TRABAJADOR+'&id_det='+obj[i].ID_DET_VACACIONES+'&op=1');
							$("#d_programacion").attr("target","_blank");
							$("#b_consolidar").removeClass("disabled").addClass("green accent-3");
							$("#b_papeleta").removeClass("disabled").addClass("green accent-3");
							$("#d_papeleta").removeClass("disabled").addClass("green accent-3");
							$("#d_papeleta").attr("href",gth_context_path+'/vacaciones/consolidado/mostrardoc?traba='+obj[i].ID_TRABAJADOR+'&id_det='+obj[i].ID_DET_VACACIONES+'&op=2');
							$("#d_papeleta").attr("target", "_blank");
							$("#b_control").removeClass("disabled").addClass("green accent-3");
							$("#b_programacion").append(cc);
							$("#b_aprobacion").append(cc);
							$("#b_consolidar").append(cc);
							$("#b_papeleta").append(cc);
							$("#b_control").append(cc);
							$("#s_programacion").append(ee);
							$("#s1_aprobacion").append(gg);
							$("#s2_aprobacion").append(ii);
							$("#s_consolidar").append(mm);
							$("#s_papeleta").append(oo);
							$("#s1_control").append(qq);
							$("#s2_control").append(ss);
							$("#f_programacion").append(FECHA_MOD_CS);
							$("#f1_aprobacion").append(FECHA_MOD_AP1);
							$("#f2_aprobacion").append(FECHA_MOD_AP2);
							$("#f_consolidar").append(FECHA_MOD_CO);
							$("#f_papeleta").append(FECHA_MOD_PA);
							$("#f1_control").append(FECHA_MOD_FI1);
							$("#f2_control").append(FECHA_MOD_FI2);
							console.log("FIRMA SALIDA Y ENTRADA");
						} else {
							console.log("PROGRAMACION SS ELSE");
						} 
						$("#t_programacion").append(obj[i].TIPO + ":");
					}
				}
			});
		}
		$("#modal").openModal();
	});

	function listarHistorialTramite(){
		$.get('readallHistorialTramite', function (obj) {
			console.log(obj);
			var s='';
	        var emp = obj[0];
			for (var i = 0; i < obj.length; i++) {
				var ID_PASOS = $.trim(obj[i].ID_PASOS);
				s += '<tr>';
				s += '<td>'+obj[i].FECHA_CREACION+'</td>';
				if (ID_PASOS == "PAS-000055" && obj[i].EVALUACION == "1" && obj[i].URL_SOLICITUD == null) {
					s += '<td><a class="btn-floating btn-large pink lighten-2 tooltipped" data-position="top" data-tooltip="PROGRAMACIÓN: Sin Solicitud">1</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">2</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">3</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">4</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">5</a></td>';
					console.log("PROGRAMACION SS");
				} else if (ID_PASOS == "PAS-000055" && obj[i].EVALUACION == "1" && obj[i].URL_SOLICITUD != null) {
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="PROGRAMACIÓN: Solicitud Hecha">1</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 tooltipped" data-position="top" data-tooltip="SECRETARIA: Sin Aprobar | JEFE: Sin Aprobar">2</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">3</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">4</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">5</a></td>';
					console.log("PROGRAMACION CS");
				} else if (ID_PASOS == "PAS-000055" && obj[i].EVALUACION == "4" && obj[i].URL_SOLICITUD == null) {
					s += '<td><a class="btn-floating btn-large pink lighten-2 tooltipped" data-position="top" data-tooltip="REPROGRAMACIÓN: Sin Solicitud">1</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">2</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">3</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">4</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">5</a></td>';
					console.log("REPROGRAMACION SS");
				} else if (ID_PASOS == "PAS-000055" && obj[i].EVALUACION == "4" && obj[i].URL_SOLICITUD != null) {
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="REPROGRAMACIÓN: Solicitud Hecha">1</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 tooltipped" data-position="top" data-tooltip="SECRETARIA: Sin Aprobar | JEFE: Sin Aprobar">2</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">3</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">4</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">5</a></td>';
					console.log("REPROGRAMACION CS");
				} else if (ID_PASOS == "PAS-000055" && obj[i].EVALUACION == "2") {
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="Solicitud Hecha">1</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 tooltipped" data-position="top" data-tooltip="SECRETARIA: Aprobado | JEFE: Sin Aprobar">2</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">3</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">4</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">5</a></td>';
					console.log("APROBADO SECRETARIA");
				} else if (ID_PASOS == "PAS-000055" && obj[i].EVALUACION == "3") {
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="Solicitud Hecha">1</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="SECRETARIA: Aprobado | JEFE: Aprobado">2</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 tooltipped" data-position="top" data-tooltip="EN PROCESO (0/2)">3</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">4</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">5</a></td>';
					console.log("APROBADO JEFE");
				} else if (ID_PASOS == "PAS-000054") {
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="Solicitud Hecha">1</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="SECRETARIA: Aprobado | JEFE: Aprobado">2</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 tooltipped" data-position="top" data-tooltip="EN PROCESO (1/2)">3</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">4</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">5</a></td>';
					console.log("EN PROCESO 1/2");
				} else if (ID_PASOS == "PAS-000052") {
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="Solicitud Hecha">1</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="SECRETARIA: Aprobado | JEFE: Aprobado">2</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="EN PROCESO (2/2)">3</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 tooltipped" data-position="top" data-tooltip="SIN ENTREGAR">4</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">5</a></td>';
					console.log("EN PROCESO 2/2");
				} else if (ID_PASOS == "PAS-000090" && obj[i].FIRMA_SALIDA == 0 && obj[i].URL_PAPELETA != null) {
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="Solicitud Hecha">1</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="SECRETARIA: Aprobado | JEFE: Aprobado">2</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="EN PROCESO (2/2)">3</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="ENTREGADO">4</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 tooltipped" data-position="top" data-tooltip="FIRMA SALIDA: No Hecho | FIRMA ENTRADA: No Hecho">5</a></td>';
					console.log("PAPELETA ENTREGADA");
				} else if (ID_PASOS == "PAS-000092" && obj[i].FIRMA_SALIDA == 0 && obj[i].URL_PAPELETA != null) {
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="Solicitud Hecha">1</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="SECRETARIA: Aprobado | JEFE: Aprobado">2</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="EN PROCESO (2/2)">3</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="ENTREGADO">4</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 tooltipped" data-position="top" data-tooltip="FIRMA SALIDA: No Hecho | FIRMA ENTRADA: No Hecho">5</a></td>';
					console.log("PAPELETA ENTREGADA");
				} else if (ID_PASOS == "PAS-000092" && obj[i].FIRMA_SALIDA == 1 && obj[i].FIRMA_ENTRADA == 0) {
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="Solicitud Hecha">1</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="SECRETARIA: Aprobado | JEFE: Aprobado">2</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="EN PROCESO (2/2)">3</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="ENTREGADO">4</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 tooltipped" data-position="top" data-tooltip="FIRMA SALIDA: Hecho | FIRMA ENTRADA: No Hecho">5</a></td>';
					console.log("FIRMA SALIDA");
				} else if (ID_PASOS == "PAS-000092" && obj[i].FIRMA_ENTRADA == 1) {
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="Solicitud Hecha">1</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="SECRETARIA: Aprobado | JEFE: Aprobado">2</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="EN PROCESO (2/2)">3</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="ENTREGADO">4</a></td>';
					s += '<td><a class="btn-floating btn-large green accent-3 tooltipped" data-position="top" data-tooltip="FIRMA SALIDA: Hecho | FIRMA ENTRADA: Hecho">5</a></td>';
					console.log("FIRMA SALIDA Y FIRMA ENTRADA");
				} else {
					s += '<td><a class="btn-floating btn-large pink lighten-2 tooltipped" data-position="top" data-tooltip="PROGRAMACIÓN: Sin Solicitud">1</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">2</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">3</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">4</a></td>';
					s += '<td><a class="btn-floating btn-large pink lighten-2 disabled">5</a></td>';
					console.log("PROGRAMACION SS");
				}
				s += '<td><a id="open" class="btn-floating btn-large waves-effect waves-light blue" name="'+obj[i].ID_DET_VACACIONES+'"><i class="mdi-image-remove-red-eye"></i></a></td>';
				s += '</tr>';
			}
	        $("#table_contenido").empty();
	        $("#table_contenido").append(createTable());
	        $("#data").empty();
	        $("#data").append(s);
// 	        $('#data-table-row-grouping').dataTable();
	        $('.tooltipped').tooltip();
	    });
	};
	
	function createTable() {
		var s = '<table id="data-table-row-grouping" class="display centered" cellspacing="0" width="100%">';
		s += '<thead>';
		s += '<tr>';
		s += '<th>Fecha</th>';
		s += '<th>Programación</th>';
		s += '<th>Aprobación</th>';
		s += '<th>Consolidar</th>';
		s += '<th>Papeleta</th>';
		s += '<th>Control Firma</th>';
		s += '<th>Detalle</th>';
		s += '</tr>';
		s += '</thead>';
		s += '<tbody id="data">';
		s += '</tbody>';
		s += '</table>';
		return s;
	};
</script>
</html>