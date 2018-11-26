<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
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

<style>
.autocomplete-content {
	margin-left: 3rem;
	width: 92%;
	width: calc(100% - 3rem);
}

/* Autocomplete */
.autocomplete-content {
	margin-top: -15px;
	display: block;
	opacity: 1;
	position: static;
}

.autocomplete-content li .highlight {
	/* 	color: #44f4; */
	color: #4cd6cb;
}

.autocomplete-content li img {
	height: 40px;
	width: 40px;
	margin: 5px 15px;
}

.dropdown-cont {
	box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0
		rgba(0, 0, 0, 0.12);
}

.dropdown-cont {
	background-color: #fff;
	/* 	margin: 0; */
	/* 	display: block; */
	min-width: 100px;
	max-height: 650px;
	overflow-y: auto;
	opacity: 1;
	position: absolute;
	z-index: 999;
	will-change: width, height;
}

.dropdown-cont li {
	clear: both;
	color: rgba(0, 0, 0, 0.87);
	cursor: pointer;
	min-height: 50px;
	line-height: 3rem;
	width: 100%;
	text-align: left;
	text-transform: none;
}

.dropdown-cont li:hover, .dropdown-content li.active, .dropdown-content li.selected
	{
	background-color: #eee;
}

.dropdown-cont li.active.selected {
	background-color: #e1e1e1;
}

.dropdown-cont li.divider {
	min-height: 0;
	height: 1px;
}

.dropdown-cont li>a, .dropdown-content li>span {
	font-size: 16px;
	color: #26a69a;
	display: block;
	line-height: 22px;
	padding: 14px 16px;
}

.dropdown-cont li>span>label {
	top: 1px;
	left: 3px;
	height: 18px;
}

.dropdown-cont li>a>i {
	height: inherit;
	line-height: inherit;
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

		</div>
		<%@include file="../../../jspf/info_puesto.jspf"%>


		<div class="container">
			<center>
				<div class="chip red lighten-2 black-text"
					style="height: 50px; margin-bottom: 30px;">
					<h5>Configurar Vacaciones</h5>
				</div>
			</center>




			<div id="basic-tabs" class="section">

				<div class="row">

					<div class="col s12 m12 l12">

						<div class="row">
							<div class="col s12">
								<ul class="tabs tab-demo z-depth-1">
									<li class="tab col s4" id="first_tab"><a class="active"
										href="#test1">CONFIGURACION DE INICIO </a></li>
									<!-- 									<li class="tab col s4"><a href="#test2">PROGRAMAR -->
									<!-- 											PLAZOS</a></li> -->
									<li class="tab col s4" id="second_tab"><a
										href="#modificar">GESTIONAR PLAZOS</a></li>
									<!-- 									<li class="tab col s3"><a href="#test4">OTORGAR -->
									<!-- 											PRIVILEGIOS</a></li> -->
									<!-- 									<li class="tab col s3"><a href="#test5">Test 5</a></li> -->
								</ul>
							</div>

							<div class="col s12" id="test1">
								<br>
								<div class="container" style="width: 90%">
									<form id="form_consolidado" action="#">
										<div class="row">
											<div class="col s12 m6 l6 ">
												<div class="card-panel center">
													<h6 class="header" style="text-align: center;">Gestionar
														Consolidado</h6>
													<br>
													<div class="row">

														<!-- 											<form> -->
														<div class="container" style="width: 80%">
															<div class="switch center">
																<label> Desactivado <input class="switch-class"
																	onchange="controlarConsolidado()" id="switch_activator"
																	type="checkbox"> <span class="lever"></span>Activado
																</label>
															</div>
															<br>

															<div class="input-field col s12">
																<i class="mdi-file-folder-open prefix"></i> <input
																	type="text" id="name_cons" name ="name_co" required> <label for="name_co">Nombre
																	consolidado</label> <input class="hide" type="text"
																	id="id_cons_hide">
															</div>

<!-- 															<div class="col s3"> -->
<!-- 																<button -->
<!-- 																	class="btn-floating btn-large waves-effect waves-light  yellow darken-4 right " -->
<!-- 																	id="editarDias"> -->
<!-- 																	<i class="mdi-editor-border-color center"></i> -->
<!-- 																</button> -->
<!-- 															</div> -->
<!-- 															<div class="col s3"> -->
<!-- 																<button -->
<!-- 																	class="btn-floating btn-large waves-effect waves-light  cyan darken-2 right " -->
<!-- 																	id="consolidado"> -->
<!-- 																	<i class="mdi-content-save center"></i> -->
<!-- 																</button> -->
<!-- 															</div> -->

														</div>
														<!-- 											</form> -->
													</div>

												</div>

											</div>

											<div class="col s12 m6 l6 ">
												<div class="card-panel">
													<div class="container" style="width: 70%">
														<div class="row">
															<h6 class="header" style="text-align: left;">Plazo
																de envío de Solicitud</h6>
															<div class="input-field col s12">
																<i class="mdi-action-today prefix"></i> <input
																	type="date" class="datepicker" id="date_solicitud" name="soli" required>
																<label for="soli">Fecha solicitud inicial</label>
															</div>
															<h6 class="header" style="text-align: left;">Plazo
																de envío de Programa</h6>
															<div class="input-field col s12">
																<i class="mdi-action-today prefix"></i> <input
																	type="date" class="datepicker" id="date_programa" name="program" required>
																<label for="program">Fecha programa inicial</label>
															</div>
															<h6 class="header" style="text-align: left;">Número
																de días mínimos</h6>
															<div class="input-field col s6">
																<i class="mdi-image-exposure prefix"></i> <input
																	type="number" id="minimal_day" name="dias" required> <label
																	for="dias">Nro de días</label>
															</div>
															<div class="col s3">
																<button
																	class="btn-floating btn-large waves-effect waves-light  yellow darken-4 right "
																	id="editarDias" onclick="cambiarNroDias(); return false">
																	<i class="mdi-editor-border-color center"></i>
																</button>
															</div>
															<div class="col s3">
																<button
																	class="btn-floating btn-large waves-effect waves-light  cyan darken-2 right "
																	id="saveDias" onclick="saveNewDias(); return false">
																	<i class="mdi-content-save center"></i>
																</button>
															</div>


														</div>
													</div>

												</div>
											</div>
										</div>

										<div class="col s12 center">
											<button
												class="waves-effect waves-light btn-large cyan darken-2 "
												id="createConsolidado" type="submit">
												<i class="mdi-content-save right"></i>Crear consolidado
											</button>
											<!-- 										<button -->
											<!-- 											class="btn-large waves-effect waves-light  cyan darken-2 " -->
											<!-- 											id="saveRequest"> Crear Consolidado  -->
											<!-- 											<i class="mdi-content-save center"></i> -->
											<!-- 										</button> -->
										</div>
									</form>
								</div>
							</div>



						</div>

						<div class="col s12" id="modificar">
							<br>
							<div class="container" style="width: 75%">



								<div class="card-panel">
									<br>
									<div class="container" style="width: 90%" id="cambiar_plazos">
										<div class="row">
											<div class=" col s6">
												<div class="switch">
													<label> <input type="checkbox" id="1"
														onclick="activar(this.id)"> <span class="lever"></span>
														Programa de Vacaciones
													</label>
												</div>
											</div>

											<div class="col s6">
												<div class="switch">
													<label> <input type="checkbox" id="2"
														onclick="activar(this.id)"> <span class="lever"></span>
														Solicitud de Vacaciones
													</label>
												</div>
											</div>
										</div>

										<div class="row">

											<div class="input-field col s13" id='hey'>
												<i class="mdi-action-search prefix"></i> <input type="text"
													id="autocomplete" class="autocomplete"> <label
													for="autocomplete" id="auto_dep">Departamento</label>
											</div>
											<input type="text" id="iddep" class="hide">
										</div>


										<div class="row">


											<div class="input-field col s5">
												<i class="mdi-action-account-circle prefix"></i> <input
													id="jefe" type="text" class="validate"> <label
													for="jefe">Jefe de Departamento</label>
											</div>
											<div class="input-field col s4">
												<i class="mdi-action-today prefix"></i> <input type="date"
													class="datepicker" id='date_edit'> <label for="dob">Plazo
													de Envío</label>
											</div>
											<div class=" input-field col s1">
												<button onclick="editarPlazo()"
													class="btn-floating btn-large waves-effect waves-light  yellow darken-4 right">
													<i class="mdi-editor-mode-edit center"></i>
												</button>
											</div>

											<div class="input-field col s1">
												<button onclick="guardarPlazo()"
													class="btn-floating btn-large waves-effect waves-light  cyan darken-2 right ">
													<i class="mdi-content-save center"></i>
												</button>
											</div>
											<div class="input-field col s1">
												<button onclick="cancelarPlazo()"
													class="btn-floating btn-large waves-effect waves-light  red darken-2 right ">
													<i class=" mdi-action-delete center"></i>
												</button>
											</div>

										</div>





									</div>

								</div>


								<div class="row">

									<div id="data_change_end_date" class="col s12 m12 l12"></div>

								</div>

							</div>
						</div>



						<!-- 						<div class="col s12" id="test4"> -->

						<!-- 							<br> -->
						<!-- 							<div class="container" style="width: 80%"> -->
						<!-- 								<div class="card-panel"> -->
						<!-- 																		<h4 class="header">Modificar privilegios del trabajador</h4> -->
						<!-- 									<div class="container" style="width: 90%"> -->
						<!-- 										<div class="row"> -->
						<!-- 																					<div class="input-field col s6"> -->
						<!-- 																						<h4 class="header2">Por Trabajador</h4> -->
						<!-- 																					</div> -->
						<!-- 											<div class="input-field col s6"> -->
						<!-- 												<i class="mdi-action-search prefix"></i> <input -->
						<!-- 													id="searchTrabajador" type="text" maxlength="8" -->
						<!-- 													pattern="/^([0-9])*$/" onkeyup="searchTrabajador()"> -->
						<!-- 												<label for="searchTrabajador">DNI Trabajador</label> <label -->
						<!-- 													id="alerta" style="color: red; top: 72px"></label> -->
						<!-- 											</div> -->

						<!-- 											<div class="col s6"> -->
						<!-- 												<button -->
						<!-- 													class="btn-large waves-effect waves-light  #00e676 center" -->
						<!-- 													onclick="buscarPorDni()"> -->
						<!-- 													<i class="mdi-action-search large center" -->
						<!-- 														style="font-size: 30px"></i> -->
						<!-- 												</button> -->
						<!-- 											</div> -->

						<!-- 										</div> -->

						<!-- 										<div class="row"> -->
						<!-- 											<div class="input-field col s6"> -->
						<!-- 												<i class="mdi-action-account-circle prefix"></i> <input -->
						<!-- 													type="text" id="name" disabled> <label -->
						<!-- 													for="icon_prefix3">Nombres y Apellidos</label> -->
						<!-- 											</div> -->
						<!-- 											<div class="input-field col s3"> -->
						<!-- 												<i class="mdi-action-account-balance prefix"></i> <input -->
						<!-- 													id="seccion" type="text" disabled> <label -->
						<!-- 													for="seccion">Sección</label> -->
						<!-- 											</div> -->
						<!-- 											<div class="input-field col s3"> -->
						<!-- 												<i class="mdi-action-account-balance prefix"></i> <input -->
						<!-- 													id="area" type="text" disabled> <label for="area">Área</label> -->
						<!-- 											</div> -->

						<!-- 										</div> -->

						<!-- 										<div class="row"> -->
						<!-- 											<div class="input-field col s4"> -->
						<!-- 												<i class="mdi-action-account-balance prefix"></i> <input -->
						<!-- 													id="departamento" type="text" disabled> <label -->
						<!-- 													for="departamento">Departamento</label> <input -->
						<!-- 													id="idtrabajador" type="text" class="hide"> -->

						<!-- 											</div> -->
						<!-- 											<div id="input-select"> -->

						<!-- 												<div class="col s12 m4"> -->
						<!-- 													<div class="input-field"> -->
						<!-- 														<label>Nro de partición de vacaciones</label> <select -->
						<!-- 															id="select_option">
															<option value="0" disabled selected>Selecciona
																una opción</option>
															<option value="1">1 vacaciones</option>
															<option value="2">2 vacaciones</option>
															<option value="3">3 vacaciones</option> -->
						<!-- 														</select> -->
						<!-- 													</div> -->
						<!-- 												</div> -->

						<!-- 																								<div class="col s3 m2"> -->
						<!-- 																									<button -->
						<!-- 																										class="btn-floating btn-large waves-effect waves-light  yellow darken-4 right"><i -->
						<!-- 																										class="mdi-editor-mode-edit center"></i></button> -->
						<!-- 																								</div> -->
						<!-- 												<div class="col s2 m2"> -->
						<!-- 													<button -->
						<!-- 														class="btn-floating btn-large waves-effect waves-light red right" -->
						<!-- 														onclick="cancelarPrivilegio()"> -->
						<!-- 														<i class="mdi-content-clear center"></i> -->
						<!-- 													</button> -->
						<!-- 												</div> -->
						<!-- 												<div class="col s2 m2"> -->
						<!-- 													<button -->
						<!-- 														class="btn-floating btn-large waves-effect waves-light  cyan darken-2 right " -->
						<!-- 														onclick="guardarPrivilegio()"> -->
						<!-- 														<i class="mdi-content-save center"></i> -->
						<!-- 													</button> -->
						<!-- 												</div> -->
						<!-- 											</div> -->




						<!-- 										</div> -->

						<!-- 									</div> -->
						<!-- 								</div> -->

						<!-- 								<div class="row"> -->

						<!-- 									<div id="data_change" class="col s12 m12 l12"></div> -->

						<!-- 								</div> -->


						<!-- 							</div> -->
						<!-- 						</div> -->

					</div>

				</div>
			</div>







		</div>




	</div>



	<script
		src="<c:url value='/resources/js/businessCore/holidays/configurar.js'></c:url>"
		type="text/javascript"></script>

	<!-- 	<script -->
	<%-- 		src="<c:url value='/resources/js/jquery.materialize-autocomplete.min.js'></c:url>" --%>
	<!-- 		type="text/javascript"></script> -->
	<script
		src="<c:url value='/resources/js/plugins/data-tables/js/jquery.dataTables.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/plugins/data-tables/data-tables-script.js'></c:url>"
		type="text/javascript"></script>
	<script>
		var v = '<ul id="singleDropdown" class="dropdown-content ac-dropdown"'
		v+='style="width: 702px; position: absolute; top: 60px; left: 11.25px; opacity: 1; display: none;">'
		v += '<li class="ac-item" data-id="1" data-text="Abe"><a'
		v += 'href="javascript:void(0)">Abe</a></li>'
		v += '<li class="ac-item" data-id="2" data-text="Ari"><a'
		v += 'href="javascript:void(0)">Ari</a></li>'
		v += '	</ul>'

		$("#single").append(v);

		// 		$(function() {
		// 			var single = $('#departamento').materialize_autocomplete({
		// 				multiple : {
		// 					enable : false
		// 				},
		// 				dropdown : {
		// 					el : '#singleDropdown'
		// 				},
		// 				limit : 4
		// 			});

		// 			var multiple = $('#multipleInput').materialize_autocomplete({
		// 				multiple : {
		// 					enable : true
		// 				},
		// 				appender : {
		// 					el : '.ac-users'
		// 				},
		// 				dropdown : {
		// 					el : '#multipleDropdown'
		// 				}
		// 			});

		// 			// 			 data : {
		// 			// 						"Ingenieria de Sistemas": null,
		// 			// 						"Ingenieria Ambiental":null,
		// 			// 	 					"Ingenieria de Alimentos" : null,
		// 			// 	 					"Administracion de Empresas" : null,
		// 			// 	 					"Ingenieria Industrial" : null
		// 			// 						},

		// 			var resultCache = {
		// 				'A' : [ {
		// 					id : '1',
		// 					text : 'Abe'
		// 				}, {
		// 					id : '2',
		// 					text : 'Ari'
		// 				} ],
		// 				'B' : [ {
		// 					id : 'Baz',
		// 					text : 'Baz'
		// 				} ],
		// 				// 				'BA' : [ {
		// 				// 					id : 'Baz',
		// 				// 					text : 'Baz'
		// 				// 				} ],
		// 				'BAZ' : [ {
		// 					id : 'Baz',
		// 					text : 'Baz'
		// 				} ],
		// 				'AB' : [ {
		// 					id : 'Abe',
		// 					text : 'Abe'
		// 				} ],
		// 				'ABE' : [ {
		// 					id : 'Abe',
		// 					text : 'Abe'
		// 				} ],
		// 				'AR' : [ {
		// 					id : 'Ari',
		// 					text : 'Ari'
		// 				} ],
		// 				'ARI' : [ {
		// 					id : 'Ari',
		// 					text : 'Ari'
		// 				} ]
		// 			};
		// 			console.log(resultCache);
		// 			single.resultCache = resultCache;
		// 			multiple.resultCache = resultCache;
		// 		});
	</script>
</body>
</html>