<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>

<%@include file="../../../jspf/general.jspf"%>
<link
	href="<c:url value='/resources/js/plugins/dropify/css/dropify.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
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

			<section id="content"
				style="margin-left: 10%; margin-right: 10%;">

				<!--start container-->
				<div class="container">

					<div id="icon-prefixes" class="section">
						<h4 class="header">Datos Generales</h4>
						<div class="row">
							<div class="col s12 m12 l12">
								<div class="row">
									<form class="col s12">
										<div class="row">
											<div class="input-field col s12 l4">
												<i class="mdi-action-account-circle prefix"></i> <input
													id="icon_prefix3" type="text" class="validate"> <label
													for="icon_prefix3">Nombres y Apellidos</label>
											</div>
											<div class="input-field col s12 l4">
												<i class="mdi-action-perm-identity prefix"></i> <input
													id="icon_telephone" type="tel" class="validate"> <label
													for="icon_telephone">DNI</label>
											</div>
											<div class="input-field col s12 l4">
												<i class="mdi-content-content-paste prefix"></i> <input
													id="icon-request" type="tel" class="validate"> <label
													for="icon-request">Tipo de Solicitud</label>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>

					</div>

					<div class="row">
						<h4 class="header">Programa de Horarios</h4>
						<div class="col s12 m12 l12">

							<p>
								<a
									class="btn-floating btn-large waves-effect waves-light green accent-3 left"><i
									class="mdi-content-add left"></i></a>
							</p>

						</div>

					</div>
					<br>
					<div class="row">
						<div class="col s12 m12 l6">
							<div class="card-panel">
								<h4 class="header2">Vacaciones 1</h4>
								<div class="row">
									<form class="col s12">
										<div class="row">
											<div class="input-field col s6">
												<i class="mdi-action-perm-contact-cal prefix"></i> <input
													type="date" class="datepicker"> <label for="dob">Fecha
													Inicio</label>
											</div>
											<div class="input-field col s6">
												<i class="mdi-action-perm-contact-cal prefix"></i> <input
													type="date" class="datepicker"> <label for="dob">Fecha
													Fin</label>
											</div>

										</div>
										<div class="row">
											<div class="col s9 m10">
												<a
													class="btn-floating waves-effect waves-light  cyan darken-2 right"><i
													class="mdi-content-save center"></i></a>
											</div>
											<div class="col s3 m2">
												<a
													class="btn-floating waves-effect waves-light  yellow darken-4 right"><i
													class="mdi-editor-mode-edit center"></i></a>
											</div>
										</div>

									</form>
								</div>
							</div>
						</div>
					</div>




					<div class="row">

						<div class="col s7 m8 l10">
							<a class="waves-effect waves-light btn right"><i
								class="mdi-action-print right"></i>Imprimir</a>

						</div>
						<div class="col s5 m4 l2">
							<a class="btn waves-effect waves-light  cyan darken-2 right">Confirmar<i
								class="mdi-navigation-check right"></i></a>

						</div>


					</div>


					<div id="file-upload" class="section">

						<div class="row section"
							style="margin-left: 20%; margin-right: 20%">

							<div class="col s12 m12 l12 center">
								<p>Maximum file upload size 2MB.</p>
								<input type="file" id="input-file-max-fs" class="dropify"
									data-max-file-size="2M" />
							</div>


						</div>
					</div>

					<div class="row">

						<div class="col s7 m8 l12 center">
							<a class="waves-effect waves-light btn center"><i
								class="mdi-file-file-upload right"></i>Subir documento</a>

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


				<!--end container-->
			</section>
		</div>




		<script
			src="<c:url value='/resources/js/plugins/dropify/js/dropify.min.js'></c:url>"
			type="text/javascript"></script>
		<script type="text/javascript">
        $(document).ready(function(){
            // Basic
            $('.dropify').dropify();

            // Translated
            $('.dropify-fr').dropify({
                messages: {
                    default: 'Glissez-déposez un fichier ici ou cliquez',
                    replace: 'Glissez-déposez un fichier ou cliquez pour remplacer',
                    remove:  'Supprimer',
                    error:   'Désolé, le fichier trop volumineux'
                }
            });

            // Used events
            var drEvent = $('.dropify-event').dropify();

            drEvent.on('dropify.beforeClear', function(event, element){
                return confirm("Do you really want to delete \"" + element.filename + "\" ?");
            });

            drEvent.on('dropify.afterClear', function(event, element){
                alert('File deleted');
            });
        });
    </script>
</body>
</html>