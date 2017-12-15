<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored = "false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<title>GTH</title>
<%@include file="../../../jspf/general.jspf"%>
</head>
<body>
	<%@include file="../../../jspf/header.jspf"%>
	<div id="main">
		<div class="wrapper">
			<%@include file="../../../jspf/aside_left.jspf"%>
			<%@include file="../../../jspf/info_puesto.jspf"%>
			<section id="content" class="col m12 l12 s12">
				<div class="card-panel">
					<h5 class="card-title">
						<strong>CONTRATOS</strong>
					</h5>
					<div class="row">
						<div class="col s4">
							<!-- Cabecera -->
							<div class="chip green accent-4 white-text col s12">
								<center>
									<i class="mdi-action-today"></i> Buscar por fechas
								</center>
							</div>
							<div class="row">
								<div class="col s6">
									<div class="input-field col s12">
										<i class="mdi-action-today prefix"></i> <input id="f_cont"
											type="date" class="datepicker"> <label for="f_cont"
											class="">Fecha de Contratación</label>
									</div>
								</div>
								<div class="col s6">
									<div class="input-field col s12">
										<i class="mdi-action-today prefix"></i> <input id="f_f_cont"
											type="date" class="datepicker"> <label for="f_f_cont"
											class="">Fecha fin de Contratación</label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col s6">
									<div class="input-field col s12">
										<i class="mdi-action-today prefix"></i> <input id="f_ini"
											type="date" class="datepicker"> <label for="f_ini"
											class="">Fecha de Inicio</label>
									</div>
								</div>
								<div class="col s6">
									<div class="input-field col s12">
										<i class="mdi-action-today prefix"></i> <input id="f_fin"
											type="date" class="datepicker"> <label for="f_fin"
											class="">Fecha Fin</label>
									</div>
								</div>
							</div>
							<div class="col s6 offset-s3">
								<div class="input-field col s12">
									<i class="mdi-action-today prefix"></i> <input id="f_fin"
										type="date" class="datepicker"> <label for="f_fin"
										class="">Fecha Fin</label>
								</div>
							</div>

						</div>
						<div class="col s4">
							<div class="chip green accent-4 white-text col s12">
								<center>
									<i class="mdi-action-work"></i> Datos de Empleado
								</center>
							</div>
							<div class="row">
								<div class="col s6">
									<div class="input-field col s12">
										<select>
											<option value="" disabled selected>Seleccionar Dirección</option>
											<option value="1">Option 1</option>
											<option value="2">Option 2</option>
											<option value="3">Option 3</option>
											<option value="4">Option 4</option>
										</select> <label>DIRECCIÓN</label>
									</div>
								</div>
								<div class="col s6">
									<div class="input-field col s12">
										<select>
											<option value="" disabled selected>Seleccionar Departamento</option>
											<option value="1">Option 1</option>
											<option value="2">Option 2</option>
											<option value="3">Option 3</option>
											<option value="4">Option 4</option>
										</select> <label>DEPARTAMENTO</label>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col s6">
									<div class="input-field col s12">
										<select>
											<option value="" disabled selected>Seleccionar Área</option>
											<option value="1">Option 1</option>
											<option value="2">Option 2</option>
											<option value="3">Option 3</option>
											<option value="4">Option 4</option>
										</select> <label>ÁREA</label>
									</div>
								</div>
								<div class="col s6">
									<div class="input-field col s12">
										<select>
											<option value="" disabled selected>Seleccionar Sección</option>
											<option value="1">Option 1</option>
											<option value="2">Option 2</option>
											<option value="3">Option 3</option>
											<option value="4">Option 4</option>
										</select> <label>SECCIÓN</label>
									</div>
								</div>
							</div>
							<div class="col s6 offset-s3">
								<div class="input-field col s12">
									<select>
										<option value="" disabled selected>Seleccionar Puesto</option>
										<option value="1">Option 1</option>
										<option value="2">Option 2</option>
										<option value="3">Option 3</option>
										<option value="4">Option 4</option>
									</select> <label>PUESTO</label>
								</div>
							</div>
						</div>
						<div class="col s4">
							<div class="chip green accent-4 white-text col s12">
								<center>
									<i class="mdi-social-person"></i> Datos de Trabajador
								</center>
							</div>
							<div class="row">
								<div class="col s6">
									<div class="input-field col s12">
										<i class="mdi-social-person-outline prefix"></i> <input
											id="icon_prefix3" type="text" class="validate"> <label
											for="icon_prefix3">Nombres y/o Apellidos</label>
									</div>
								</div>
								<div class="col s6">
									<div class="input-field col s12">
										<i class="mdi-editor-attach-money prefix"></i> <input
											id="icon_prefix3" type="text" class="validate"> <label
											for="icon_prefix3">Sueldo</label>
									</div>
								</div>
							</div>
						</div>
					</div>
					<center>
						<div class="row">
							<a class="waves-effect waves-light  btn green accent-4"><i
								class="mdi-action-search left"></i>BUSCAR</a> <a
								class="waves-effect waves-light  btn grey darken-3"><i
								class="mdi-navigation-refresh left"></i>LIMPIAR</a>
						</div>
					</center>

				</div>
			</section>

		</div>


	</div>

	<%@include file="../../../jspf/footer.jspf"%>
<body>
</html>