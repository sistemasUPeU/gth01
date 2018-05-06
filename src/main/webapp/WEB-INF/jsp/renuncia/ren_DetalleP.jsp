<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../../../jspf/general.jspf"%>
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
</style>

<html>
<head>
</head>
<body class="#e8f5e9 green lighten-5">
	<div class="row">
		<div class="col s2">
			<br>
		</div>
		<div class="col s8">
			<section class="plans-container" id="plans">
				<div class="card " style="box-shadow: 0px 0px 20px 20px #888888">
					<div class="card-image #607d8b blue-grey waves-effect ">
						<div class="card-title">
							<h5>DETALLES DEL TRABAJADOR</h5>
						</div>

						<div class="price flow-text ">
							<div class="col m12 l12 s12">
								<div id="profile-page-content" class="row">
									<div class="row card-panel white">
										<div class="col l2 m2 s6">
											<img src="<c:url value="/resources/img/user.png"/>" alt=""
												class="circle responsive-img valign profile-image">
										</div>
										<div class="col l3 m6 s6">
											<input type="hidden" id="idt" />
											<input id="idr" type="hidden" />
											<h6 class="light italic black-text">
												<strong>Nombres :</strong><span id="nombres"></span><br>
											</h6>
											<h6 class="light italic black-text">
												<strong> Apellido Paterno : </strong><span id="paterno"></span><br>
											</h6>
											<h6 class="light italic black-text">
												<strong> Apellido Materno : </strong><span id="materno"></span><br>
											</h6>
											<h6 class="light italic black-text">
												<strong> Fecha de Nacimiento : </strong><span id="fecha_nac"></span>
											</h6>
										</div>
									</div>
								</div>
							</div>

						</div>
						<div class="price-desc white-text">
							<form class="col s12 ">
								<div class="row">
									<div class="input-field col s6  ">
										<h6>Fecha de Inicio:</h6>
									</div>
									<div class="input-field col s4 ">
										<h6>
											<span id="fecha_inicio"></span><br>
										</h6>
									</div>
								</div>
							</form>
							<form class="col s12">
								<div class="row">
									<div class="input-field col s6 ">
										<h6>Direcci�n:</h6>
									</div>
									<div class="input-field col s4 ">
										<h6>
											<span id="direccion"></span><br>
										</h6>
									</div>
								</div>
							</form>
							<form class="col s12">
								<div class="row">
									<div class="input-field col s6 ">
										<h6>Departamento:</h6>
									</div>
									<div class="input-field col s4 ">
										<h6>
											<span id="departamento"></span><br>
										</h6>
									</div>
								</div>
							</form>

							<form class="col s12">
								<div class="row">
									<div class="input-field col s6 ">
										<h6>Area:</h6>
									</div>
									<div class="input-field col s4 ">
										<h6>
											<span id="area"></span><br>
										</h6>
									</div>
								</div>
							</form>
							<form class="col s12">
								<div class="row">
									<div class="input-field col s6">
										<h6>Secci�n:</h6>
									</div>
									<div class="input-field col s4 ">
										<h6>
											<span id="seccion"></span><br>
										</h6>
									</div>
								</div>
							</form>
							<form class="col s12">
								<div class="row">
									<div class="input-field col s6 ">
										<h6>Puesto:</h6>
									</div>
									<div class="input-field col s4 ">
										<h6>
											<span id="puesto"></span><br>
										</h6>
									</div>
								</div>
							</form>

							<form class="col s12">
								<div class="row">
									<div class="input-field col s6 ">
										<h6>Tipo de contrato:</h6>
									</div>
									<div class="input-field col s4 ">
										<h6>
											<span id="tipo_contrato"></span><br>
										</h6>
									</div>
								</div>
							</form>

						</div>
					</div>
					<!-- 					<div class="card-content"> -->
					<!-- 						<form class="col s12"> -->
					<!-- 							<div class="row"> -->
					<!-- 								<div class="input-field col s6" style="text-align: center"> -->
					<!-- 									<h6>Carta de Renuncia:</h6> -->
					<!-- 								</div> -->
					<!-- 								<div class="input-field col s6"> -->
					<!-- 									<div class="material-placeholder"> -->
					<!-- 																			<img materialboxed class="materialboxed" -->
					<!-- 																				data-caption="A picture of some deer and tons of trees" -->
					<!-- 																				width="250" " style="z-index: 4" -->
					<%-- 										
<%-- 									 										src="<c:url value="/resources/img/carta de renuncia.png"/>" /> --%>

					<!-- 										<span id="carta"> <img materialboxed -->
					<!-- 											class="materialboxed" -->
					<!-- 											data-caption="A picture of some deer and tons of trees" -->
					<!-- 											width="250" " style="z-index: 4" id="carta" /> -->
					<!-- 										</span> -->

					<!-- 									</div> -->
					<!-- 								</div> -->
					<!-- 							</div> -->
					<!-- 						</form> -->
					<!-- 					</div> -->
					<div class="card-content"></div>
				</div>
			</section>
		</div>
		<div class="col s2">
			<br>
		</div>
	</div>

	<form action="col s12">
		<div class="row">
			<div class="input-field col s6 center">
				<a
					class="btn btn-large waves-effect waves-light light-green darken-4 "
					id="modalP">Procesar</a>
			</div>
			<div class="input-field col s6 center">
				<a
					class="waves-effect waves-light btn modal-trigger btn-large red darken-4 "
					id="modalR">Rechazar</a>
			</div>
		</div>
	</form>
	<!-- 	</div> -->
	<br>
	<br>
	<br>


	<div id="first">
		<div class="remodal" data-remodal-id="modalR">
			<button data-remodal-action="close" class="remodal-close"></button>
			<h4 class="light italic black-text">Motivos de Rechazo</h4>

			<hr>
			
				<div class="row">
					<div class="col s12">
						<div class="input-field col s7">
							<h5 class="light italic black-text">Descripci�n de rechazo:</h5>
							<div class="input-field col s12">
								<textarea id="observaciones" class="materialize-textarea"></textarea>
							</div>
						</div>
					</div>
				</div>
	
			<button data-remodal-action="cancel" class="remodal-cancel"  >Cancelar</button>
			<button data-remodal-action="confirm" class="remodal-confirm"
				 id="RechazarRenuncia">Enviar</button>
		</div>
	</div>









	<div style="position: fixed; width: 100%; bottom: 0; z-index: 5">
		<%@include file="../../../jspf/footer.jspf"%>
	</div>
	<script src="<c:url  value='/resources/js/remodal.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/businessCore/ProcesarRenuncia.js'></c:url>"
		type="text/javascript"></script>
	
	<%-- 	<script src="<c:url  value='/resources/js/remodal.min.js'></c:url>" --%>
	<!-- 		type="text/javascript"></script> -->
</body>
</body>
</html>