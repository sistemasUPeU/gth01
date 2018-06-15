<%@ page language="java" contentType="text/html; charset=UTF-8"
	%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<%@include file="../../../jspf/general.jspf"%>
<link href="https://fonts.googleapis.com/css?family=Dosis"
	rel="stylesheet">
<link href="<c:url value='/resources/js/plugins/prism/prism.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="<c:url value='/resources/js/plugins/data-tables/css/jquery.dataTables.min.css'></c:url>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value='/resources/css/dataTables.min.css'></c:url>" 
	rel="stylesheet" type="text/css" />
	<link
	href="<c:url value='/resources/css/responsive.dataTables.min.css'></c:url>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value='/resources/js/plugins/chartist-js/chartist.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="<c:url value='/resources/js/plugins/dropify/css/dropify.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">

<link href="<c:url value='/resources/css/alertify.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">

<link href="<c:url value='/resources/css/renuncias.css'></c:url>" />
<link href="https://fonts.googleapis.com/css?family=Cormorant+Garamond"
	rel="stylesheet">
<link href="<c:url value='/resources/css/remodal.css'/>" type="text/css"
	rel="stylesheet" media="screen,projection">
<link href="<c:url value='/resources/css/remodal-default-theme.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
	
<style>
	div.dataTables_wrapper{
		width:auto;
        margin: 0 auto;
    }
    
    .dataTables_scroll
{
    overflow:auto;
}
    
    
    .display{
	width:100%;
}
table.dataTable tbody th,
table.dataTable tbody td {
    white-space: nowrap;
}
</style>
</head>

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

		<section id="content" class="col m12 l12 s12">
				<div class="center">
					<h1
						style="font-family: 'Cormorant Garamond', serif; font-weight: bold">Notificar Renuncias y 
						 Abandonos</h1>
				</div>
				<div class="divider"></div>

			</section>
		<div class="row"
				style="width: 100%;  max-width:90%">
			<ul class="collapsible popout">
				<li class="active">
					<div class="collapsible-header active">
						<i class="mdi-social-notifications-on"></i> Renuncias y Abandonos por Notificar
					</div>
					<div class="collapsible-body"
							style="display: none;">	
							<div class="row" style="padding:1em">
								<div class="contT"></div>
							</div>			
						</div>
<!-- 					<div id="data-table-row-grouping col s12 m8 l9" -->
<!-- 						class="card-panel collapsible-body display #e3f2fd blue lighten-5" -->
<!-- 						style="display: none;"> -->
<!-- 						<div class="col s12 m8 l9 contT"></div> -->
<!-- 					</div> -->
				</li>
				<li>
					<div class="collapsible-header active">
						<i class="mdi-toggle-check-box"></i> Entregar documento de Renuncias y Abandono
					</div>
					<div id="tabla-notificada "
						class="card-panel collapsible-body "
							style="display: none;">
						<div class="col s12 m8 l9 conN"></div>
					</div>
				</li>
			</ul>
		</div>
	</div>

	<div id="first">
		<!-- 		<div id="modalnotificar" class="modal modal-fixed-footer" -->
		<!-- 			style="width: 70%; border: 5px solid black"> -->
		<!-- 			<div class="card z-depth-2" style="width: 80%; margin-left: 5%"> -->
		<!-- 				<div class="row card-panel"> -->
		<div class="remodal" data-remodal-id="modal">
			<button data-remodal-action="close" class="remodal-close"></button>
			<h4 class="light italic black-text">Motivos de Rechazo</h4>
			<div class="col s12">
				<h4>Notificar entrega de documentos</h4>
			</div>
			<div class="col s12">
				<span>Correo:</span> <span id="correo"></span>
			</div>
			<br>
			<div class="col s12">
				<p id="men">
				<p id="mensaje1">
					Señor <span id="nombre"></span> sus documentos ya se encuentran
					listos para ser entregados, por favor se le invita a pasar por la
					oficina de Gestion de Talentos Humanos (GTH) a recoger sus
					documentos de beneficios sociales en la fecha
				</p>
				<span> <input type="date" id="fecha" style="width: 20%"
					class="datepicker"></span>

				<p id="mensaje2">
					<br> <span>Atentamente GTH,</span><br> <span>Gracias</span>
				</p>
			</div>
			<label id="idtr" hidden=""></label> <label id="idr" hidden=""></label><label id="tipo" hidden=""></label>
			<button data-remodal-action="cancel" class="remodal-cancel">Cancelar</button>
			<button data-remodal-action="confirm" class="remodal-confirm" id=""
				onclick="enviarCorreo()">Enviar</button>
		</div>

	</div>


	<div id="first">
		<div class="remodal" data-remodal-id="modal1">
			<button data-remodal-action="close" class="remodal-close"></button>
			<!-- 			<div id="modalentregar" class="modal modal-fixed-footer" -->
			<!-- 				style="width: 60%; height: 80%; border: 5px solid black"> -->
			<!-- 				<div class="modal-header #1de9b6 teal lighten-1"> -->
			<h4 style="font-family: 'Dosis', sans-serif;">Entregar
				Documentos</h4>

			<form method="post"
				action="<%=request.getContextPath()%>/renuncias/holamundo"
				enctype="multipart/form-data">
				<!-- 					<div class="modal-content #e0f7fa cyan lighten-5" -->
				<!-- 						style="z-index: 0"> -->
				<div class="row section">
					<div class="col s12">
						<div class="row">
							<div class=" input-field col s6">
								<p>Hoja de liquidacion</p>
								<input type="file" name="archivo" id="input-file-now"
									class="dropify" data-default-file="" data-height="300" />
							</div>
							<div class=" input-field col s6">
								<p>Carta CTS</p>
								<input type="file" name="archivo" id="input-file-now"
									class="dropify" data-default-file="" data-height="300" />
							</div>
						</div>
					</div>
					<div class="col s12">
						<div class=" input-field col s6">
							<p>Certificado de Trabajo</p>
							<input type="file" name="archivo" id="input-file-now"
								class="dropify" data-default-file="" data-height="300" />
						</div>
						<div class=" input-field col s6">
							<p>Reporte de Remuneraciones</p>
							<input type="file" name="archivo" id="input-file-now"
								class="dropify" data-default-file="" data-height="300" />
						</div>
					</div>
					<input id="not_idr" type="hidden" name="not_idr">
				</div>

				<button data-remodal-action="cancel" class="remodal-cancel">Cancelar</button>
				<button data-remodal-action="confirm" class="remodal-confirm"
					id="NotificarR">Enviar</button>
				<div class="col s4"></div>
				<input type="hidden" id="array_motivos" />
			</form>
		</div>
 

	</div>

	<div style="position: fixed; width: 100%; bottom: 0;">
		<%@include file="../../../jspf/footer.jspf"%>
	</div>
	<script src="<c:url  value='/resources/js/remodal.min.js'></c:url>"
		type="text/javascript">
		
	</script>
	<script
		src="<c:url value='/resources/js/plugins/prism/prism.js'></c:url>"
		type="text/javascript"></script>
		<script
		src="<c:url value='/resources/js/businessCore/NotificarRenuncia.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/plugins/data-tables/js/jquery.dataTables.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/plugins/data-tables/data-tables-script.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url  value='/resources/js/plugins/dropify/js/dropify.min.js'></c:url>"
		type="text/javascript"></script>
		<script
				src="<c:url  value='/resources/js/plugins/alertify/alertify.min.js'></c:url>"
				type="text/javascript"></script>
		<script src="<c:url  value='/resources/js/remodal.min.js'></c:url>"
				type="text/javascript"></script>
		
	
</body>
</html>
