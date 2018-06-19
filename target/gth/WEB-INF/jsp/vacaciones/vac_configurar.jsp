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

	<%@include file="../../../jspf/header.jspf"%>
	<div id="loader-wrapper">
		<div id="loader"></div>
		<div class="loader-section section-left"></div>
		<div class="loader-section section-right"></div>
	</div>
	<div id="main">
		<div class="wrapper">
			<%@include file="../../../jspf/aside_left.jspf"%>

		</div>
		<%@include file="../../../jspf/info_puesto.jspf"%>


		<div class="container">

			<div id="basic-tabs" class="section">
				<h4 class="header">Configuraciones básicas</h4>
				<div class="row">

					<div class="col s12 m12 l12">

						<div class="row">
							<div class="col s12">
								<ul class="tabs tab-demo z-depth-1">
									<li class="tab col s3"><a class="active" href="#test1">LISTA
											FILTRADA</a></li>
									<li class="tab col s3"><a href="#test2">PROGRAMAR
											PLAZOS</a></li>
									<li class="tab col s3"><a href="#modificar">MODIFICAR
											PLAZOS</a></li>
									<li class="tab col s3"><a href="#test4">OTORGAR
											PRIVILEGIOS</a></li>
									<!-- 									<li class="tab col s3"><a href="#test5">Test 5</a></li> -->
								</ul>
							</div>

							<div class="col s12" id="test1">
								<br>
								<div class="container" style="width: 50%">
									<div class="card-panel">
										<h4 class="header" style="text-align: center;">Programar
											aviso de lista filtrada</h4>
										<div class="row">

											<!-- 											<form> -->


											<div class="container" style="width: 80%">
												<div class="input-field col s7">
													<i class="mdi-action-today prefix"></i> <input type="date"
														class="datepicker" id="date_cons"> <label
														for="dob">Fecha de Aviso</label>
												</div>
												<div class="col s3">
													<a
														class="btn-floating btn-large waves-effect waves-light  yellow darken-4 right"><i
														class="mdi-editor-mode-edit center"></i></a>
												</div>
												<div class="col s2">
													<a
														class="btn-floating btn-large waves-effect waves-light  cyan darken-2 right "
														id="consolidado"><i class="mdi-content-save center"></i></a>
												</div>

											</div>


											<!-- 											</form> -->
										</div>

									</div>
								</div>
							</div>



							<div id="test2">


								<div class="col s12 m12 l6" id="programa_vacaciones">
									<br>
									<div class="card-panel">
										<h5 class="header" style="text-align: center;">Plazo de
											envío de Programa de vacaciones</h5>
										<br>

										<form>
											<div class="container" style="width: 70%">
												<div class="row">
													<div class="input-field col s8">
														<i class="mdi-action-today prefix"></i> <input type="date"
															class="datepicker" id="date_programa"> <label
															for="dob">Plazo de Envío</label>
													</div>
													<div class="col s12 m6 l4">
														<a
															class="btn-floating btn-large waves-effect waves-light  cyan darken-2 right "
															id="savePrograma"><i class="mdi-content-save center"></i></a>
													</div>


												</div>
											</div>
										</form>

									</div>
								</div>

								<div class="col s12 m12 l6" id="solicitud_vacaciones">
									<br>
									<div class="card-panel">
										<h5 class="header" style="text-align: center;">Plazo de
											envío de Solicitud de vacaciones</h5>
										<br>
										<form>
											<div class="container" style="width: 70%">
												<div class="row">
													<div class="input-field col s8">
														<i class="mdi-action-today prefix"></i> <input type="date"
															class="datepicker" id="date_solicitud"> <label
															for="dob">Plazo de Envío</label>
													</div>
													<div class="col s12 m6 l4">
														<a
															class="btn-floating btn-large waves-effect waves-light  cyan darken-2 right "
															id="saveRequest"><i class="mdi-content-save center"></i></a>
													</div>


												</div>
											</div>
										</form>

									</div>
								</div>


							</div>

							<div class="col s12" id="modificar">
								<br>
								<div class="container" style="width: 70%">
									<div class="card-panel">
										<br>
										<div class="container" style="width: 90%">
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
												<div class="input-field col s4">
													<i class="mdi-action-search prefix"></i> <input
														id="searchDepartamento" type="text" class="validate"
														onkeyup="searchDepartamento()"> <label
														for="searchDepartamento">Departamento</label>
												</div>
												<!-- 															<div class="input-field col s4"> -->
												<!-- 																<i class="mdi-action-account-balance prefix"></i> <input -->
												<!-- 																	id="direccion" type="tel" class="validate"> <label -->
												<!-- 																	for="direccion">Dirección</label> -->
												<!-- 															</div> -->

											</div>

											<div class="row">


												<div class="input-field col s5">
													<i class="mdi-action-account-circle prefix"></i> <input
														id="jefe" type="text" class="validate"> <label
														for="jefe">Jefe de Departamento</label>
												</div>
												<div class="input-field col s3">
													<i class="mdi-action-today prefix"></i> <input type="date"
														class="datepicker"> <label for="dob">Plazo
														de Envío</label>
												</div>
												<div class=" input-field col s2">
													<a
														class="btn-floating btn-large waves-effect waves-light  yellow darken-4 right"><i
														class="mdi-editor-mode-edit center"></i></a>
												</div>

												<div class="input-field col s2">
													<a
														class="btn-floating btn-large waves-effect waves-light  cyan darken-2 right "><i
														class="mdi-content-save center"></i></a>
												</div>

											</div>





										</div>

									</div>
								</div>
							</div>



							<div class="col s12" id="test4">

								<br>
								<div class="container" style="width: 80%">
									<div class="card-panel">
										<!-- 									<h4 class="header">Modificar privilegios del trabajador</h4> -->
										<div class="container" style="width: 90%">
											<div class="row">
												<!-- 										<div class="input-field col s6"> -->
												<!-- 											<h4 class="header2">Por Trabajador</h4> -->
												<!-- 										</div> -->
												<div class="input-field col s6">
													<i class="mdi-action-search prefix"></i> <input
														id="searchTrabajador" type="text" class="validate"
														onkeyup="searchTrabajador()"> <label
														for="searchTrabajador">Trabajador</label>
												</div>


											</div>

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

											<div class="row">

												<div id="input-select">

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
									</div>
								</div>
							</div>

						</div>

					</div>
				</div>
			</div>




		</div>

	</div>




	<script
		src="<c:url value='/resources/js/businessCore/holidays/configurar.js'></c:url>"
		type="text/javascript"></script>
</body>
</html>