<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
			<%@include file="../../../jspf/info_puesto.jspf"%>
			<section id="content">
				<div class="container">

					<div class="row">
						<div class="col s12 m12 l6">
							<br>
							<div class="card-panel">
								<h4 class="header">Programar aviso de lista filtrada</h4>
								<div class="row">
									<form class="col s12">
										<div class="row">
											<div class="input-field col s6">
												<i class="mdi-action-today prefix"></i> <input type="date"
													class="datepicker"> <label for="dob">Fecha
													de Aviso</label>
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
									</form>
								</div>
							</div>
						</div>

						<div class="col s12 m12 l6">
							<div class="card-panel">
								<h4 class="header">Plazo de envío de Programa de vacaciones</h4>
								<div class="row">
									<form class="col s12">
										<div class="row">
											<div class="input-field col s6">
												<i class="mdi-action-today prefix"></i> <input type="date"
													class="datepicker"> <label for="dob">Plazo
													de Envío</label>
											</div>
											<div class="col s12 m6 l6">
												<div class="input-field col s12 l12">
													<select multiple id="sel">
														<option value="" disabled selected>Choose your option</option>
														<option value="0">Seleccionar Todos</option>
														<option value="1">Option 1</option>
														<option value="2">Option 2</option>
														<option value="3">Option 3</option>
													</select> <label>Materialize Multiple Select</label>
												</div>
											</div>
											<div class="col s3 m8">
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
									</form>
								</div>
							</div>
						</div>

					</div>
					<div class="row">

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
															id="icon_telephone" type="tel" class="validate">
														<label for="icon_telephone">Sección</label>
													</div>
													<div class="input-field col s3">
														<i class="mdi-action-account-balance prefix"></i> <input
															id="icon_telephone" type="tel" class="validate">
														<label for="icon_telephone">Área</label>
													</div>
													<div class="input-field col s3">
														<i class="mdi-action-account-balance prefix"></i> <input
															id="icon_telephone" type="tel" class="validate">
														<label for="icon_telephone">Departamento</label>
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


						<div class="col s12 m12 l6">
							<div class="card-panel">
								<h4 class="header">Plazo de envío de solicitud por Departamento</h4>


								<div class="row">

									<div class="input-field col s6">
										<p>
											<input type="checkbox" id="test5" /> <label for="test5">Todos
												los Departamentos</label>
										</p>
									</div>

									<div class="input-field col s6">
										<i class="mdi-action-search prefix"></i> <input
											id="searchDepartamento" type="text" class="validate"
											onkeyup="searchDepartamento()"> <label
											for="searchDepartamento">Departamento</label>
									</div>


								</div>
								<div class="row">

									<div class="col s12 m12 l12">
										<div class="row">
											<form class="col s12">
												<div class="row">
													<div class="input-field col s4">
														<i class="mdi-action-account-circle prefix"></i> <input
															id="icon_prefix3" type="text" class="validate"> <label
															for="icon_prefix3">Jefe de Departamento</label>
													</div>
													<div class="input-field col s4">
														<i class="mdi-action-account-balance prefix"></i> <input
															id="icon_telephone" type="tel" class="validate">
														<label for="icon_telephone">Departamento</label>
													</div>
													<div class="input-field col s4">
														<i class="mdi-action-account-balance prefix"></i> <input
															id="icon_telephone" type="tel" class="validate">
														<label for="icon_telephone">Dirección</label>
													</div>

												</div>
												<br>
												<div class="row">
													<div class="col s12 m12 l12">
														<div class="row">
															<div class="input-field col s6">
																<i class="mdi-action-today prefix"></i> <input
																	type="date" class="datepicker"> <label
																	for="dob">Fecha Límite de Envío</label>
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






					<!-- Floating Action Button -->
					<div class="fixed-action-btn" style="bottom: 50px; right: 19px;">
						<a class="btn-floating btn-large"> <i class="mdi-action-stars"></i>
						</a>
						<ul>
							<li><a href="css-helpers.html" class="btn-floating red"><i
									class="large mdi-communication-live-help"></i></a></li>
							<li><a href="app-widget.html"
								class="btn-floating yellow darken-1"><i
									class="large mdi-device-now-widgets"></i></a></li>
							<li><a href="app-calendar.html" class="btn-floating green"><i
									class="large mdi-editor-insert-invitation"></i></a></li>
							<li><a href="app-email.html" class="btn-floating blue"><i
									class="large mdi-communication-email"></i></a></li>
						</ul>
					</div>
					<!-- Floating Action Button -->





				</div>




			</section>
		</div>

	</div>
</body>
</html>