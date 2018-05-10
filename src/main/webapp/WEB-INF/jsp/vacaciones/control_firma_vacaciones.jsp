<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
<style type="text/css">
.center-btn {
	text-align: center
}

div.dataTables_length {
	display: none !important;
}

#confirmar_lista {
	margin-top: 25px;
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
			<section id="content"></section>
		</div>
		<%@include file="../../../jspf/info_puesto.jspf"%>

		<div class="container">
			<div class="row">
				<div id="table_contenido" class="col s12 m12 l12"></div>
			</div>

			<div id="modal" class="modal">
				<div class="modal-content">
					<h4>Control de firmas</h4>
					<div class="row">
						<div id="contenedor_fechas"></div>
						<div class="col s4">
							<br> <a id="guardar"
								class="btn waves-effect waves-light modal-action modal-close"><i
								class="mdi-content-save"></i> Guardar</a>
						</div>
						<div class="col s8">
			<!-- ------------------------------------------------------------------------------------- -->
							<form method="post" action="/gth/solicitud/papeleta?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data" id="documentoForm">
								<div class="file-field input-field">
									<div id="lobo" class="btn">
										<span>File</span> <input id="file-input" type="file" name="file">
									</div>
									<div class="file-path-wrapper">
										<input class="file-path validate" type="text">
										<input type="text" id="idvac" name="idvac" value="" class="hide" />
									</div>
								</div>
							</form>
			<!-- ------------------------------------------------------------------------------------- -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script
		src="<c:url value='/resources/js/plugins/prism/prism.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/plugins/data-tables/js/jquery.dataTables.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/plugins/data-tables/data-tables-script.js'></c:url>"
		type="text/javascript"></script>
</body>

<script type="text/javascript">
	$(document).ready(function() {
		listarControlFirmas();
	})

	var z;

	$("#modal").on("click", ".check", function() {
		if ($(this).attr("name") == 0 && $(this).attr("id") == "nolisto1") {
			$(this).removeClass('pink lighten-2');
			$(this).addClass('green accent-3');
			$(this).attr("name","1");
			$(this).find("#i").removeClass('mdi-navigation-close');
			$(this).find("#i").addClass('mdi-navigation-check');
		} else
		if ($(this).attr("name") == 1 && $(this).attr("id") == "nolisto1") {
			$(this).removeClass('green accent-3');
			$(this).addClass('pink lighten-2');
			$(this).attr("name","0");
			$(this).find("#i").removeClass('mdi-navigation-check');
			$(this).find("#i").addClass('mdi-navigation-close');
		}
		if ($(this).attr("name") == 0 && $(this).attr("id") == "nolisto2") {
			if ($("#nolisto1").attr("name") == "1" || $("#listo1").attr("name") == "1") {
				$(this).removeClass('pink lighten-2');
				$(this).addClass('green accent-3');
				$(this).attr("name","1");
				$(this).find("#i").removeClass('mdi-navigation-close');
				$(this).find("#i").addClass('mdi-navigation-check');	
			} else
			if ($("#nolisto1").attr("name") == "0") {
				console.log("maincraaa");
				Materialize
				.toast(
						'Cambio no valido, debes confirmar la fecha inicial!',
						3000,
						'rounded');
			}
		} else
		if ($(this).attr("name") == 1 && $(this).attr("id") == "nolisto2") {
			$(this).removeClass('green accent-3');
			$(this).addClass('pink lighten-2');
			$(this).attr("name","0");
			$(this).find("#i").removeClass('mdi-navigation-check');
			$(this).find("#i").addClass('mdi-navigation-close');
		}
		if ($(this).attr("name") == 1 && $(this).attr("id") == "listo1" || $(this).attr("id") == "listo2") {
			Materialize
			.toast(
					'No puedes modificar esta fecha, porque ya fue confirmada!',
					3000,
					'rounded');
		}
	});

	$("#lobo").click(function() {
        $("#idvac").val($("#iddet").attr("name"));
	});
	
	$("#guardar").click(function() {
		console.log(z);
		if (z == 1) {
			var a, p,q;
			$(".det").each(function(){
				if ($(this).val() == 1) {
	            	a = $(this).attr("name");
				}
	        });
			$(".check").each(function(){
	            if ($(this).val() == 1) {
	            	p = $(this).attr("name");
		        }
	            if ($(this).val() == 2) {
	            	q = $(this).attr("name");
		        }
	        });
	        console.log("***");
	        console.log(a);
	        console.log(p);
	        console.log(q);
	        console.log("***");
	        $.get('updateFirma', {id : a, inicio : p, fin : q}, function (data) {
				console.log(data);
				if(data==1){
					Materialize
					.toast(
							'Firma actualizada correctamente!',
							3000,
							'rounded');
					}else {
						Materialize
						.toast(
								'No se actualizaron las firmas, consulte con su jefe!',
								3000,
								'rounded');
						}
		    });
		}
		if (z == 2) {
			var a,b, p,q,r,s;
			$(".det").each(function(){
				if ($(this).val() == 1) {
	            	a = $(this).attr("name");
				}
				if ($(this).val() == 2) {
		            b = $(this).attr("name");
				}
	        });
			$(".check").each(function(){
				if ($(this).val() == 1) {
	            	p = $(this).attr("name");
		        }
	            if ($(this).val() == 2) {
	            	q = $(this).attr("name");
		        }
	            if ($(this).val() == 3) {
	            	r = $(this).attr("name");
		        }
	            if ($(this).val() == 4) {
	            	s = $(this).attr("name");
		        }
	        });
			console.log("***");
	        console.log(a);
	        console.log(b);
	        console.log(p);
	        console.log(q);
	        console.log(r);
	        console.log(s);
	        console.log("***");
	        $.get('updateFirma', {id : a, inicio : p, fin : q}, function (data) {
				console.log(data);
		    });
	        $.get('updateFirma', {id : b, inicio : r, fin : s}, function (data) {
				console.log(data);
		    });
		}
		if (z == 3) {
			var a,b,c, p,q,r,s,t,u;
			$(".det").each(function(){
				if ($(this).val() == 1) {
	            	a = $(this).attr("name");
				}
				if ($(this).val() == 2) {
		            b = $(this).attr("name");
				}
				if ($(this).val() == 3) {
		            c = $(this).attr("name");
				}
	        });
			$(".check").each(function(){
				if ($(this).val() == 1) {
	            	p = $(this).attr("name");
		        }
	            if ($(this).val() == 2) {
	            	q = $(this).attr("name");
		        }
	            if ($(this).val() == 3) {
	            	r = $(this).attr("name");
		        }
	            if ($(this).val() == 4) {
	            	s = $(this).attr("name");
		        }
	            if ($(this).val() == 5) {
	            	t = $(this).attr("name");
		        }
	            if ($(this).val() == 6) {
	            	u = $(this).attr("name");
		        }
	        });
			console.log("***");
	        console.log(a);
	        console.log(b);
	        console.log(c);
	        console.log(p);
	        console.log(q);
	        console.log(r);
	        console.log(s);
	        console.log(t);
	        console.log(u);
	        console.log("***");
	        $.get('updateFirma', {id : a, inicio : p, fin : q}, function (data) {
				console.log(data);
		    });
	        $.get('updateFirma', {id : b, inicio : r, fin : s}, function (data) {
				console.log(data);
		    });
	        $.get('updateFirma', {id : c, inicio : t, fin : u}, function (data) {
				console.log(data);
		    });
		}
	});
	
	$("#table_contenido").on("click", "#open", function() {
		var id = $(this).attr("name");
		console.log(id);
		$.get('readFirma', {id : id}, function (obj) {
			console.log(obj);
			$("#contenedor_fechas").empty();
	    	var j='';
	    	var k = 0;
	 		var fechas = document.getElementById("contenedor_fechas");
	 		var n_n = 0;
	        for (var i = 0; i < obj.length; i++) {
		        k = k + 1;
				j += '<div class="col s4">';
	            j += '<p>Fecha Inicio</p>';
	            j += '<input value="'+obj[i].FECHA_INICIO+'" type="text">';
	            j += '</div>';
		        j += '<div class="col s3">';
				j += '<br> <br>';
				n_n = n_n + 1;
				if (obj[i].FIRMA_SALIDA == 0) {
	            	j += '<button id="nolisto1" class="btn-floating waves-effect waves-light pink lighten-2 check" value="'+n_n+'" name="'+obj[i].FIRMA_SALIDA+'">';
	            	j += '<i class="mdi-navigation-close" id="i"></i>';
		            j += '</button>';
				} else
				if (obj[i].FIRMA_SALIDA == 1) {
		            j += '<button id="listo1" class="btn-floating waves-effect waves-light green accent-3 check" value="'+n_n+'" name="'+obj[i].FIRMA_SALIDA+'">';
		            j += '<i class="mdi-navigation-check" id="i"></i>';
		            j += '</button>';
				}
	            j += '</div>';
	            j += '<div class="col s4">';
	            j += '<p>Fecha Fin</p>';
	            j += '<input value="'+obj[i].FECHA_FIN+'" type="text">';
	            j += '</div>';
		        j += '<div class="col s1">';
				j += '<br> <br>';
				n_n = n_n + 1;
				if (obj[i].FIRMA_ENTRADA == 0) {
	            	j += '<button id="nolisto2" class="btn-floating waves-effect waves-light pink lighten-2 check" value="'+n_n+'"  name="'+obj[i].FIRMA_ENTRADA+'">';
	            	j += '<i class="mdi-navigation-close" id="i"></i>';
	            	j += '</button>';
				} else
				if (obj[i].FIRMA_ENTRADA == 1) {
		            j += '<button id="listo2" class="btn-floating waves-effect waves-light green accent-3 check" value="'+n_n+'"  name="'+obj[i].FIRMA_ENTRADA+'">';
		            j += '<i class="mdi-navigation-check" id="i"></i>';
		            j += '</button>';
				}
	            j += '</div>';
	            j += '<button id="iddet" class="hide det" value="'+k+'" name="'+obj[i].ID_DET_VACACIONES+'"></button>';
			}
	        fechas.innerHTML += j;
			z = obj.length;
	    });
		$("#modal").openModal();
	});
	
	function listarControlFirmas(){
		$.get('readallControlFirma', function (obj) {
	        var s='';
	        var emp = obj[0];
	        for (var i = 0; i < obj.length; i++) {
				s += '<tr>';
	            s += '<td>'+obj[i].AP_PATERNO+' '+obj[i].AP_MATERNO+' '+obj[i].NO_TRABAJADOR+'</td>';
	            s += '<td>'+obj[i].FECHA_INICIO+'</td>';
	            s += '<td>'+obj[i].FECHA_FIN+'</td>';
		        s += '<td><button id="open" class="btn-floating waves-effect waves-light light-blue accent-4" href="#modal" name="'+obj[i].ID_VACACIONES+'">';
				s += '<i class="mdi-image-remove-red-eye"></i></button>';
	            s += '</tr>';
			}
	        $("#table_contenido").empty();
	        $("#table_contenido").append(createTable());
	        $("#data").empty();
	        $("#data").append(s);
	        $('#data-table-row-grouping').dataTable();
	    });
	};

	function createTable() {
		var s = '<table id="data-table-row-grouping" class="display centered" cellspacing="0" width="100%">';
		s += '<thead>';
		s += '<tr>';
		s += '<th>Apellidos y Nombres</th>';
		s += '<th>Fecha Inicio</th>';
		s += '<th>Fecha Fin</th>';
		s += '<th>Control</th>';
		s += ' </tr>';
		s += '</thead>';
		s += '<tbody id="data"></tbody>';
		s += '</table>';
		return s;
	};

	$("#guardar").click(function(event) {
		console.log("SALE PUES");
		var csrfHeader = $("meta[name='_csrf_header']").attr("content");
		var csrfToken = $("meta[name='_csrf']").attr("content");
		console.log(csrfToken + " / " + csrfHeader);
		var file = $("#file-input").val();
		var form = $('#documentoForm')[0];
		var data;
		console.log(file + "  " + form + "  " + data);
		if (file != "") {
			$.ajax({
				type : "POST",
				enctype : 'multipart/form-data',
				url : gth_context_path + "/solicitud/papeleta",
				data : data,
				processData : false,
				contentType : false,
				cache : false,
				timeout : 600000,
				beforeSend : function(xhr, data) {
					xhr.setRequestHeader(csrfHeader, csrfToken);
					console.log("data: "+data);
					if (data == "1") {
						console.log("success: " + data);
						Materialize.toast('Papeleta subida correctamente', 3000, 'rounded');
					} else if (data == "0") {
						console.log("error: " + data);
						Materialize.toast('Se excedió el tamaño máximo de papeleta permitido', 3000, 'rounded');
					}
				}
			});
		} else {
			Materialize.toast('No hay papeleta', 3000, 'rounded');
		}
	});
</script>
</html>