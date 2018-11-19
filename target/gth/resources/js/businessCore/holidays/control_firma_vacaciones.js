$(document).ready(function() {
	listarControlFirmas();
})

//VARIABLES GLOBALES
var z;
var idtrab;
var vali = 66;
var ti, to;

//SCRIPT PARA CAMBIAR ESTADO DE FECHA INICIO Y FIN
$("#modal").on("click",".check",function() {
	if ($(this).attr("name") == 0 && $(this).attr("id") == "nolisto1") {
		var file = $("#file-input").val();
		if (file != "") {
			if ($(".check").hasClass("disabled") != true) {
				$(this).removeClass('pink lighten-2');
				$(this).addClass('green accent-3');
				$(this).attr("name", "1");
				$(this).find("#i").removeClass('mdi-navigation-close');
				$(this).find("#i").addClass('mdi-navigation-check');
				vali = 77;
			} else {
				Materialize.toast('Guarde la papeleta para continuar', 3000, 'rounded');
			}
		} else {
			Materialize.toast('Es necesario la papeleta para continuar', 3000, 'rounded');
		}
	} else if ($(this).attr("name") == 1 && $(this).attr("id") == "nolisto1") {
		$(this).removeClass('green accent-3');
		$(this).addClass('pink lighten-2');
		$(this).attr("name", "0");
		$(this).find("#i").removeClass('mdi-navigation-check');
		$(this).find("#i").addClass('mdi-navigation-close');
	}
	if ($(this).attr("name") == 0 && $(this).attr("id") == "nolisto2") {
		if ($("#nolisto1").attr("name") == "1" || $("#listo1").attr("name") == "1") {
			$(this).removeClass('pink lighten-2');
			$(this).addClass('green accent-3');
			$(this).attr("name", "1");
			$(this).find("#i").removeClass('mdi-navigation-close');
			$(this).find("#i").addClass('mdi-navigation-check');
		} else if ($("#nolisto1").attr("name") == "0") {
			Materialize.toast('Cambio no valido, debes confirmar la fecha inicial!', 3000, 'rounded');
		}
	} else if ($(this).attr("name") == 1 && $(this).attr("id") == "nolisto2") {
		$(this).removeClass('green accent-3');
		$(this).addClass('pink lighten-2');
		$(this).attr("name", "0");
		$(this).find("#i").removeClass('mdi-navigation-check');
		$(this).find("#i").addClass('mdi-navigation-close');
	}
	if ($(this).attr("name") == 1 && $(this).attr("id") == "listo1" || $(this).attr("id") == "listo2") {
		Materialize.toast('No puedes modificar esta fecha, porque ya fue confirmada!', 3000, 'rounded');
	}
});

//RESCATAR ID DETALLE
$("#zorro").on("click", "#lobo", function() {
	$("#idvac").val($("#iddet").attr("name"));
});

//GUARDAR ESTADO DE FECHAS
$("#guardar").click(function() {
	var con = new jsConnector();
	var a, p, q;
	var fsm = 0;
	$(".det").each(function() {
		if ($(this).val() == 1) {
			a = $(this).attr("name");
		}
	});
	var compro = 5;
	$(".check").each(function() {
		if ($(this).val() == 1) {
			p = $(this).attr("name");
		}
		if ($(this).val() == 2) {
			q = $(this).attr("name");
		}
		if ($(this).attr('id') == 'nolisto1') {
			fsm = 1;
			compro = 6; 
		}
		if ($(this).attr('id') == 'nolisto2' && compro == 5) {
			fsm += 1;
		}
	});
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	var file = $("#file-input").val();
	var form = $('#documentoForm')[0];
	var data = new FormData(form);
	if (z == 6) {
		//SUBIR PAPELETA
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
				},
				success : function(data) {
					if (data == "0") {
						Materialize.toast('Se excedi\u00F3 el tamaño m\u00E1ximo de papeleta permitido', 3000, 'rounded');
					} else {
						Materialize.toast('Papeleta subida correctamente', 3000, 'rounded');
					}
				},
				error : function(e) {
					Materialize.toast('No se subi\u00F3 la papeleta, consulte con su jefe', 3000, 'rounded');
				}
			});
		} else {
			Materialize.toast('No hay papeleta', 3000, 'rounded');
		}
		$.get('controlar/updatePapeletaFirma', {id : a}, function(data) {
		});
	}
	if (vali == 77) {
		fsm = 3;
		vali = 66;
	}
	//ENVIA FECHAS AL BACK-END
	if (p == ti && q == to) {
		Materialize.toast('Ninguna firma se ha actualizado!', 3000, 'rounded');
	} else {
		con.post('vacaciones/controlar/updateFirma?' + "id=" + a + "&inicio=" + p + "&fin=" + q + "&fsm=" + fsm, null, function(data) {
			if (data == 1) {
				Materialize.toast('Firma actualizada correctamente!', 3000, 'rounded');
			} else {
				Materialize.toast('No se obtuvieron datos, consulte con su jefe', 3000, 'rounded');
			}
		});
	}
});

//ABRE MODAL Y LO LLENA CON LAS FECHAS DE VACACIONES
$("#table_contenido").on("click", "#open", function() {
	var id = $(this).attr("name");
	idtrab = $(this).attr("title");
	$.get('controlar/readFirma', {id : id}, function(obj) {
		ti = obj[0].FIRMA_SALIDA;
		to = obj[0].FIRMA_ENTRADA;
		$("#contenedor_fechas").empty();
		var j = '';
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
			} else if (obj[i].FIRMA_SALIDA == 1) {
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
			} else if (obj[i].FIRMA_ENTRADA == 1) {
				j += '<button id="listo2" class="btn-floating waves-effect waves-light green accent-3 check" value="'+n_n+'"  name="'+obj[i].FIRMA_ENTRADA+'">';
				j += '<i class="mdi-navigation-check" id="i"></i>';
				j += '</button>';
			}
			j += '</div>';
			j += '<button id="iddet" class="hide det" value="'+k+'" name="'+obj[i].ID_DET_VACACIONES+'"></button>';
		}
		fechas.innerHTML += j;
		z = obj.length;
		var btnPapeleta = '';
		if (obj[0].URL != null) {
			z = 7;
			$("#zorro").empty();
			btnPapeleta += '<br><a class="waves-effect waves-light btn" id="verPapeleta" href="'
				+ gth_context_path + '/vacaciones/consolidado/mostrardoc?traba='
				+ idtrab + '&id_det=' + obj[0].ID_DET_VACACIONES
				+ '&op=2" target="_blank" >Ver Papeleta</a></div></center></div>';
			$("#zorro").append(btnPapeleta);
		} else {
			z = 6;
			$("#zorro").empty();
			btnPapeleta += '<form method="post" action="/gth/solicitud/papeleta?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data" id="documentoForm">'
				+ '<div class="file-field input-field">' + '<div id="lobo" class="btn">'
				+ '<span>File</span> <input id="file-input" type="file" name="file">'
				+ '</div>' + '<div class="file-path-wrapper">'
				+ '<input class="file-path validate" type="text">'
				+ '<input type="text" id="idvac" name="idvac" value="" class="hide" />'
				+ '</div></div></form>';
			$("#zorro").append(btnPapeleta);
			$(".check").addClass("disabled");
			$(".check").attr("disabled");
		}
	});
	$("#modal").openModal();
});

//LLENA TABLA DE FIRMAS CON LOS DATOS DE TRABAJADORES FILTRADOS
function listarControlFirmas() {
	$.get('controlar/readallControlFirma', function(obj) {
		var s = '';
		var emp = obj[0];
		for (var i = 0; i < obj.length; i++) {
			s += '<tr>';
			s += '<td>' + obj[i].AP_PATERNO + ' ' + obj[i].AP_MATERNO + ' ' + obj[i].NO_TRABAJADOR + '</td>';
			s += '<td>' + obj[i].FECHA_INICIO + '</td>';
			s += '<td>' + obj[i].FECHA_FIN + '</td>';
			s += '<td><button id="open" class="btn-floating waves-effect waves-light light-blue accent-4" href="#modal" title="'+obj[i].ID_TRABAJADOR+'" name="'+obj[i].ID_DET_VACACIONES+'">';
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

//CREAR TABLA PARA LLENAR A LOS TRABAJADORES QUE YA PASARON POR CONSOLIDADO
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