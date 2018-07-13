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

<!-- //Nuevo link -->

<!-- <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"> -->
<%-- <link href="<c:url value='/resources/css/materialize.min.css'></c:url>" rel="stylesheet" type="text/css" /> --%>
<%-- <link href="<c:url value='/resources/css/prism.css'></c:url>" rel="stylesheet" type="text/css" /> --%>
<%-- <link href="<c:url value='/resources/css/style.css'></c:url>" rel="stylesheet" type="text/css" /> --%>
<%-- <link href="<c:url value='/resources/css/materialize-stepper.min.css'></c:url>" rel="stylesheet" type="text/css" /> --%>
<!-- <link href="js/plugins/dropify/css/dropify.min.css" type="text/css" rel="stylesheet" media="screen,projection"> -->
<%-- <script src="<c:url  value='/resources/js/materialize.min.js'></c:url>" type="text/javascript"></script> --%>
<%-- <link href="<c:url value='/resources/js/plugins/sweetalert/sweetalert.css'/>" --%>
<!-- 	type="text/css" rel="stylesheet" media="screen,projection"> -->

<style>
@media only screen and (min-width: 700px) {
	.remodal {
		max-width: 65%;
	}
	h6 {
		font-size: 16px
	}
}
@media only screen and (max-width: 640px) {
	.remodal {
		min-width: 80%;
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

.btn{
background: #3AD80C;
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
						style="font-family: 'Cormorant Garamond', serif; font-weight: bold">DOCUMENTOS DEL LEGAJO
						</h1>
				</div>
				<div class="divider"></div>

			</section>
			<div class="row"
				style="width: 100%;  max-width:90%">
				<ul class="collapsible popout">
					<li id="autorized" class="active">
						<div class="collapsible-header active">
							<i class="mdi-toggle-check-box"></i> Documentos Entregados
						</div> 
						<div class="card-panel collapsible-body "
							style="display: none;">
							<div class="row" style="padding:1em">
								<div class="contL"></div>
							</div>

							</div>

						</li>
					</ul>
				</div>
	</div>
<!-- 	<div id="first" > -->
<!-- 		<div class="remodal" data-remodal-id="modal1" id="EntregaForm"> -->
<!-- 			<button data-remodal-action="close" class="remodal-close"></button> -->
<!-- 			<div class="col s12"> -->
<!-- 				<h4>Documentos</h4> -->
<!-- 			</div> -->
<!-- 			<div class="row" -->
<!-- 				style="width: 100%;  max-width:90%"> -->
<!-- 				<ul class="collapsible popout"> -->
<!-- 					<li id="autorized" class="active"> -->
<!-- 						<div class="collapsible-header active" > -->
<!-- 							<i class="mdi-toggle-check-box"></i> DOCUMENTOS -->
<!-- 						</div>  -->
<!-- 						<div class="card-panel collapsible-body " -->
<!-- 							style="display: none;"> -->
<!-- 							<div class="row" style="padding:1em"> -->
<!-- 								<div class="contT"></div> -->
<!-- 							</div> -->

<!-- 							</div> -->

<!-- 						</li> -->
<!-- 					</ul> -->
<!-- 				</div> -->
<!-- 			<label id="idtr" hidden=""></label> <label id="idr" hidden=""></label><label id="tipo" hidden=""></label> -->
<!-- 			<button data-remodal-action="cancel" class="remodal-cancel">Cerrar</button> -->
<!-- 		</div> -->
<!-- 	</div> -->

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
		src="<c:url value='/resources/js/businessCore/legajo.js'></c:url>"
		type="text/javascript"></script>
		
<!-- 	link nuevos  -->

<!-- <script src="https://ajax.aspnetcdn.com/ajax/jquery.validate/1.15.0/jquery.validate.min.js"></script> -->
<%-- <script src="<c:url  value='/resources/js/materialize-stepper.min.js'></c:url>" type="text/javascript"></script> --%>
<%-- <script src="<c:url  value='/resources/js/plugins/dropify/css/dropify.min.css'></c:url>" type="text/javascript"></script> --%>
<!-- <script -->
<%-- 		src="<c:url value='/resources/js/plugins/sweetalert/sweetalert.min.js'></c:url>" --%>
<!-- 		type="text/javascript"></script>		 -->
	
</body>
</html>
