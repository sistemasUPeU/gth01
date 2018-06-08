<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../../../jspf/general.jspf"%>
<link href="https://fonts.googleapis.com/css?family=Dosis"
	rel="stylesheet">
<link href="<c:url value='/resources/js/plugins/prism/prism.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
<link href="<c:url value='/resources/css/dataTables.min.css'></c:url>"
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
<link href="https://fonts.googleapis.com/css?family=Poiret+One"
	rel="stylesheet">
<link href="<c:url value='/resources/css/remodal.css'/>" type="text/css"
	rel="stylesheet" media="screen,projection">
<link href="<c:url value='/resources/css/remodal-default-theme.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
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
</style>
</head>
<body class="#e8f5e9 green lighten-5">
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

		<div class="wrapper">
			<section id="content" class="col m12 l12 s12">
			<div class="container">
				<h1
					style="font-family: 'Poiret One', cursive; font-weight: bold; margin-left: 1em">Renuncias
					y Abandonos</h1>
			</div>
			<div class="divider"></div>

			</section>
			<div id="contenido">
				<div id="card-alert" class="card green" style="display:none; ">
                      <div class="card-content white-text">
                        <p><i class="mdi-navigation-check"></i> Se ha derivado la renuncia o abandono a Recursos Humanos.</p>
                      </div>
                    </div>
                    <div id="card-alert2" class="card orange" style="display:none; ">
                      <div class="card-content white-text">
                        <p><i class="mdi-alert-warning"></i> Se ha actualizado el registro.</p>
                      </div>
                    </div>
                    <div id="card-alert3" class="card red" style="display:none; ">
                      <div class="card-content white-text">
                        <p><i class="mdi-alert-error"></i> Se ha eliminado el registro.</p>
                      </div>
                    </div>
				<div class="row" style="width: 100%; max-width: 90%">
					<ul class="collapsible popout">
						<li id="autorize" class="active">
							<div class="collapsible-header active">
								<i class="mdi-social-notifications-on"></i> Registro de
								Renuncias y Abandonos
							</div>
							<div class="collapsible-body" style="display: none;">
								<div class="row" style="padding: 1em">
									<div class="contT"></div>
								</div>
							</div>
						</li>
					</ul>
				</div>
				<div class="remodal" data-remodal-id="modal">
									<button data-remodal-action="close" class="remodal-close"></button>
									<h1>Actualizar Renuncia o Abandono</h1>
									<form method="post" 
										enctype="multipart/form-data" class="col s12 m8 l11"
										id="UpdatingR">
										
										<div class="row section">
											<div class="col s12">
												<div class="row">
													<div class="input-field col s6">
														<h6>
															Fecha de entrega de documento:<br>
														</h6>
													</div>
													<div class="input-field col s6">
														<h6>Documento:</h6>
													</div>

												</div>
											</div>
											<div class="col s12" id="modalon">
												
											</div>
										</div>
										<br>
										<button data-remodal-action="cancel" class="remodal-cancel">Cancelar</button>
										<button data-remodal-action="confirm" class="remodal-confirm"
											type="submit" id="ActualizarR">Confirmar</button>
								</form>
							</div>
			</div>

		</div>

	</div>

	<div style="position: fixed; width: 100%; bottom: 0; z-index: 5">
		<%@include file="../../../jspf/footer.jspf"%>
	</div>
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
		type="text/javascript"></script>
	<script
		src="<c:url  value='/resources/js/businessCore/renaban.js'></c:url>"
		type="text/javascript"></script>

	<%-- 	<%@include file="../../../resources/js/businessCore/jsAutorizar.jspf"%> --%>
</body>
</html>