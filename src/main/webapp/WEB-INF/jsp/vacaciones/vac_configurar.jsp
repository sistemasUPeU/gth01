<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
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
<link
	href="<c:url value='/resources/css/stepper/materialize-stepper.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
</head>
<body>
	<%-- 	<%@include file="../../../jspf/header.jspf"%> --%>
	<!-- 	<div id="loader-wrapper"> -->
	<!-- 		<div id="loader"></div> -->
	<!-- 		<div class="loader-section section-left"></div> -->
	<!-- 		<div class="loader-section section-right"></div> -->
	<!-- 	</div> -->
	<!-- 	<div id="main"> -->
	<!-- 		<div class="wrapper"> -->
	<%-- 			<%@include file="../../../jspf/aside_left.jspf"%> --%>
	<%-- 			<%@include file="../../../jspf/info_puesto.jspf"%> --%>
	<!-- 			<section id="content"> -->
	<div class="container">

		<div class="row">
			<div class="col s12 m12 l6" style="margin-left: 25%;">
				<br>
				<div class="card-panel">
					<h4 class="header">Programar aviso de lista filtrada</h4>
					<div class="row">
						<form class="col s12">
							<div class="row">
								<div class="input-field col s6">
									<i class="mdi-action-today prefix"></i> <input type="date"
										class="datepicker" id="date_cons"> <label for="dob">Fecha
										de Aviso</label>
								</div>
								<div class="col s4 m4">
									<a
										class="btn-floating btn-large waves-effect waves-light  yellow darken-4 right"><i
										class="mdi-editor-mode-edit center"></i></a>
								</div>
								<div class="col s2 m2">
									<a
										class="btn-floating btn-large waves-effect waves-light  cyan darken-2 right "
										id="consolidado"><i class="mdi-content-save center"></i></a>
								</div>

							</div>
						</form>
					</div>
				</div>
			</div>


		</div>

		<div class="row">
			<div class="col s12 m12 l6" id="programa_vacaciones">
				<div class="card-panel">
					<h4 class="header">Plazo de envío de Programa de vacaciones</h4>
					<div class="row">
						<form class="col s12">
							<div class="row">
								<div class="input-field col s6">
									<i class="mdi-action-today prefix"></i> <input type="date"
										class="datepicker" id="date_programa"> <label
										for="dob">Plazo de Envío</label>
								</div>
								<div class="col s12 m6 l6">
									<a
										class="btn-floating btn-large waves-effect waves-light  cyan darken-2 right "
										id="savePrograma"><i class="mdi-content-save center"></i></a>
								</div>


							</div>
						</form>
					</div>
				</div>
			</div>

			<div class="col s12 m12 l6" id="solicitud_vacaciones">
				<div class="card-panel">
					<h4 class="header">Plazo de envío de Solicitud de vacaciones</h4>
					<div class="row">
						<form class="col s12">
							<div class="row">
								<div class="input-field col s6">
									<i class="mdi-action-today prefix"></i> <input type="date"
										class="datepicker" id="date_solicitud"> <label
										for="dob">Plazo de Envío</label>
								</div>
								<div class="col s12 m6 l6">
									<a
										class="btn-floating btn-large waves-effect waves-light  cyan darken-2 right "
										id="saveRequest"><i class="mdi-content-save center"></i></a>
								</div>


							</div>
						</form>
					</div>
				</div>
			</div>


		</div>
		<div class="row">


			<div class="col s12 m12 l6" id="modificar">
				<div class="card-panel">
					<h4 class="header">Modificar Fechas</h4>


					<div class="row">

						<div class=" col s6">
							<div class="switch">
								<label> <input type="checkbox"> <span
									class="lever"></span> Programa de Vacaciones
								</label>
							</div>
						</div>

						<div class="col s6">
							<div class="switch">
								<label> <input type="checkbox"> <span
									class="lever"></span> Solicitud de Vacaciones
								</label>
							</div>
						</div>




					</div>
					<div class="row">

						<div class="col s12 m12 l12">
							<div class="row">
								<form class="col s12">
									<div class="row">
										<div class="input-field col s4">
											<i class="mdi-action-search prefix"></i> <input
												id="searchDepartamento" type="text" class="validate"
												onkeyup="searchDepartamento()"> <label
												for="searchDepartamento">Departamento</label>
										</div>
										<div class="input-field col s4">
											<i class="mdi-action-account-balance prefix"></i> <input
												id="direccion" type="tel" class="validate"> <label
												for="direccion">Dirección</label>
										</div>
										<div class="input-field col s4">
											<i class="mdi-action-account-circle prefix"></i> <input
												id="jefe" type="text" class="validate"> <label
												for="jefe">Jefe de Departamento</label>
										</div>



									</div>
									<br>
									<div class="row">
										<div class="col s12 m12 l12">
											<div class="row">
												<div class="input-field col s4 m8">
													<i class="mdi-action-today prefix"></i> <input type="date"
														class="datepicker"> <label for="dob">Plazo
														de Envío</label>
												</div>
												<div class=" input-field col s3 m2">
													<a
														class="btn-floating btn-large waves-effect waves-light  yellow darken-4 right"><i
														class="mdi-editor-mode-edit center"></i></a>
												</div>

												<div class="input-field col s3 m2">
													<a
														class="btn-floating btn-large waves-effect waves-light  cyan darken-2 right "><i
														class="mdi-content-save center"></i></a>
												</div>


											</div>

										</div>


									</div>
								</form>
							</div>
						</div>

					</div>
					<br>

				</div>
			</div>

			<div class="col s12 m12 l6">


				<div class="card-panel">
					<h4 class="header">Modificar privilegios del trabajador</h4>

					<div class="row">
						<div class="input-field col s6">
							<h4 class="header2">Por Trabajador</h4>
						</div>
						<div class="input-field col s6">
							<i class="mdi-action-search prefix"></i> <input
								id="searchTrabajador" type="text" class="validate"
								onkeyup="searchTrabajador()"> <label
								for="searchTrabajador">Trabajador</label>
						</div>

						<br>
					</div>
					<div class="row">

						<div class="col s12 m12 l12">
							<div class="row">
								<form class="col s12">
									<div class="row">
										<div class="input-field col s3">
											<i class="mdi-action-account-circle prefix"></i> <input
												id="icon_prefix3" type="text" class="validate"> <label
												for="icon_prefix3">Nombres y Apellidos</label>
										</div>
										<div class="input-field col s3">
											<i class="mdi-action-account-balance prefix"></i> <input
												id="seccion" type="tel" class="validate"> <label
												for="seccion">Sección</label>
										</div>
										<div class="input-field col s3">
											<i class="mdi-action-account-balance prefix"></i> <input
												id="area" type="tel" class="validate"> <label
												for="area">Área</label>
										</div>
										<div class="input-field col s3">
											<i class="mdi-action-account-balance prefix"></i> <input
												id="departamento" type="tel" class="validate"> <label
												for="departamento">Departamento</label>
										</div>
									</div>
									<br>
									<div class="row">
										<div class="col s12 m12 l12">
											<div id="input-select" class="row">

												<div class="col s12 m6 l6">
													<div class="input-field col s12 m12 l12">
														<label>Nro de partición de vacaciones</label> <select>
															<option value="" disabled selected>Choose your option</option>
															<option value="1">Option 1</option>
															<option value="2">Option 2</option>
															<option value="3">Option 3</option>
														</select>
													</div>
												</div>

												<div class="col s3 m2">
													<a
														class="btn-floating btn-large waves-effect waves-light  yellow darken-4 right"><i
														class="mdi-editor-mode-edit center"></i></a>
												</div>
												<div class="col s3 m2">
													<a
														class="btn-floating btn-large waves-effect waves-light red right"><i
														class="mdi-content-clear center"></i></a>
												</div>
												<div class="col s3 m2">
													<a
														class="btn-floating btn-large waves-effect waves-light  cyan darken-2 right "><i
														class="mdi-content-save center"></i></a>
												</div>
											</div>

										</div>


									</div>
								</form>
							</div>
						</div>
					</div>


				</div>
			</div>



		</div>

		<div class="section grey lighten-5">
			<div class="container">
				<div class="row">
					<div class="col l6 m10 s12 offset-l3 offset-m1">
						<h3 class="light center-align blue-text">Sign up form</h3>
						<div class="card">
							<div class="card-content">

								<ul data-method="GET" class="stepper linear">
									<li class="step active">
										<div class="step-title waves-effect waves-dark">E-mail</div>
										<div class="step-content">
											<div class="row">
												<div class="input-field col s6">
													<input id="email" name="email" type="email"
														class="validate" required> <label for="first_name">Your
														e-mail</label>
												</div>
											</div>
											<div class="step-actions">
												<button class="waves-effect waves-dark btn next-step"
													data-feedback="anyThing">Continue</button>
											</div>
										</div>
									</li>
									<li class="step">
										<div class="step-title waves-effect waves-dark">Step 2</div>
										<div class="step-content">
											<div class="row">
												<div class="input-field col s6">
													<input id="password" name="password" type="password"
														class="validate" required> <label for="password">Your
														password</label>
												</div>
											</div>
											<div class="step-actions">
												<button class="waves-effect waves-dark btn next-step">CONTINUE</button>
												<button
													class="waves-effect waves-dark btn-flat previous-step">BACK</button>
											</div>
										</div>
									</li>
									<li class="step">
										<div class="step-title waves-effect waves-dark">Callback</div>
										<div class="step-content">
											Unfortunately callback loading screen is not working in here
											=( I'll make a github page soon!
											<div class="step-actions">
												<button class="waves-effect waves-dark btn next-step"
													data-feedback="anyThing">ENDLESS CALLBACK!</button>
											</div>
										</div>
									</li>
								</ul>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>


	</div>
	<!-- 			</section> -->
	<!-- 		</div> -->

	<!-- 	</div> -->
	
	<script
		src="<c:url value='https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.1/jquery.validate.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/businessCore/holidays/configurar.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/plugins/stepper/materialize-stepper.min.js'></c:url>"
		type="text/javascript"></script>


	<script>
		$(function() {
			$('.stepper').activateStepper();
		});
		function anyThing() {
			setTimeout(function() {
				$('.stepper').nextStep();
			}, 1500);
		}
	</script>
</body>
</html>