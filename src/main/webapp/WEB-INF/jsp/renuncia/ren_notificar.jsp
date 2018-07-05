<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="../../../jspf/general.jspf"%>
<link href="https://fonts.googleapis.com/css?family=Dosis"
	rel="stylesheet">	
<link href="<c:url value='/resources/js/plugins/prism/prism.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="<c:url value='/resources/js/plugins/data-tables/css/jquery.dataTables.min.css'></c:url>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value='/resources/js/plugins/chartist-js/chartist.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="<c:url value='/resources/js/plugins/dropify/css/dropify.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
<link href="<c:url value='/resources/css/renuncias.css'></c:url>" />
<link href="https://fonts.googleapis.com/css?family=Poiret+One"
	rel="stylesheet">
<link href="<c:url value='/resources/css/remodal.css'/>" type="text/css"
	rel="stylesheet" media="screen,projection">
<link href="<c:url value='/resources/css/remodal-default-theme.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="<c:url value='/resources/css/dataTables.min.css'></c:url>" 
	rel="stylesheet" type="text/css" />
	<link
	href="<c:url value='/resources/css/responsive.dataTables.min.css'></c:url>"
	rel="stylesheet" type="text/css" />

<link href="<c:url value='/resources/css/alertify.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">

<link href="https://fonts.googleapis.com/css?family=Cormorant+Garamond"
	rel="stylesheet">
<style>
@media only screen and (min-width: 641px) {
	.remodal {
		max-width: 75%;
	}
	h6 {
		font-size: 16px
	}
}
@media only screen and (max-width: 640px) {
	.remodal {
		min-width: 100%;
	}
	h5 {
		font-size: 14px
	}
	h1 {
		font-size: 16px
	}
	h6 {
		font-size: 14px
	}
}
.ajs-message.ajs-custom {
	color: #31708f;
	background-color: #d9edf7;
	border-color: #31708f;
	z-index: 999999
}
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
					<li id="autorize" class="active">
						<div class="collapsible-header active">
							<i class="mdi-social-notifications-on"></i> Verificar Abandonos Pendientes
						</div>
						<div class="collapsible-body"
							style="display: none;">	
							<div class="row" style="padding:1em">
								<div class="contT"></div>
							</div>			
						</div>
					</li>
					<li id="autorized" class="">
						<div class="collapsible-header" >
							<i class="mdi-toggle-check-box"></i> Verificar Abandonos Autorizados
						</div> 
						<div class="card-panel collapsible-body "
							style="display: none;">
							<div class="row" style="padding:1em">
								<div class="contP"></div>
							</div>

							</div>

						</li>
					</ul>
				</div>

	</div>

	<div id="first">
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
								<p id="liquidacion">Hoja de liquidacion</p>
								<input type="file" name="archivo" id="input-file-now"
									class="dropify" data-default-file="" data-height="300" />
							</div>
							<div class=" input-field col s6">
								<p id="cts">Carta CTS</p>
								<input type="file" name="archivo" id="input-file-now"
									class="dropify" data-default-file="" data-height="300" />
							</div>
						</div>
					</div>
					<div class="col s12">
						<div class=" input-field col s6">
							<p id="certificado">Certificado de Trabajo</p>
							<input type="file" name="archivo" id="input-file-now"
								class="dropify" data-default-file="" data-height="300" />
						</div>
						<div class=" input-field col s6">
							<p id="remu">Reporte de Remuneraciones</p>
							<input type="file" name="archivo" id="input-file-now"
								class="dropify" data-default-file="" data-height="300" />
						</div>
					</div>
					<input id="not_idr" type="hidden" name="not_idr">
				</div>

				<button data-remodal-action="cancel" class="remodal-cancel">Cancelar</button>
				<button data-remodal-action="confirm" class="remodal-confirm"
					onclick="NotificarR()">Enviar</button>
				<div class="col s4"></div>
				<input type="hidden" id="array_motivos" />
			</form>
		</div>
 

	</div>

	<div style="position: fixed; width: 100%; bottom: 0; z-index: 5">
		<%@include file="../../../jspf/footer.jspf"%>
	</div>

	<script
		src="<c:url value='/resources/js/plugins/prism/prism.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/jquery.dataTables.min.js'></c:url>"
		type="text/javascript"></script>
		<script
		src="<c:url value='/resources/js/dataTables.responsive.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url  value='/resources/js/plugins/dropify/js/dropify.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url  value='/resources/js/plugins/alertify/alertify.min.js'></c:url>"
		type="text/javascript"></script>
		<script src="<c:url  value='/resources/js/remodal.min.js'></c:url>"
		type="text/javascript">			
	</script>
	<script
		src="<c:url value='/resources/js/plugins/data-tables/js/jquery.dataTables.min.js'></c:url>"
		type="text/javascript"></script>
		<script
		src="<c:url value='/resources/js/plugins/data-tables/data-tables-script.js'></c:url>"
		type="text/javascript"></script>
		<script
		src="<c:url value='/resources/js/businessCore/NotificarRenuncia.js'></c:url>"
		type="text/javascript"></script>
	
</body>
</html>
