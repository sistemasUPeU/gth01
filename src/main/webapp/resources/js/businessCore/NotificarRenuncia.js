$(document).ready(function() {
	// PRUEBAJONAS();
	listarProcesados();
	listarNotificados();
	$('.dropify').dropify(function(event, element){
        return confirm("¿Desea eliminar el archivo \"" + element.filename + "\" ?");
    });

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
    var drEvent = $('#pelon1').dropify();

    drEvent.on('dropify.beforeClear', function(event, element){
        return confirm("¿Desea eliminar el archivo \"" + element.filename + "\" ?");
    });

    drEvent.on('dropify.afterClear', function(event, element){
        alert('Archivo eliminado');
    });
    show_image('C:/Users/Deyvis Garcia/eclipse-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/gth/WEB-INF/29791352_2077305459260623_8230612622916918394_n.jpg',456,456,'Google Logo');

});

function show_image(src, width, height, alt) {
//	alert();
    var img = document.createElement("img");
    img.src = src;
    img.width = width;
    img.height = height;
    img.alt = alt;

    // This next line will just add it to the <body> tag
    document.body.appendChild(img);
}


function listarProcesados() {
	$
			.getJSON(
					gth_context_path + "/renaban/listarxd",
					"opc=1",
					function(objJson) {
						var s = "";
						var lista = objJson;
						console.log(objJson);
						if (lista.length > 0) {
							// alert("si hay datos causita c:");

							for (var i = 0; i < lista.length; i++) {
								var a = parseInt(i) + 1;
								var MFL = parseInt(lista[i].ES_MFL);
								var Motivo = parseInt(lista[i].LI_MOTIVO);
								var plazo = parseInt(lista[i].VAL_PLAZO);
								var fe_creacion = new Date(
										lista[i].FECHA_RENABAN);
								var mesint = parseInt(fe_creacion.getMonth()) + 1;
								// console.log(mesint);

								// console.log(ddd(mesint));
								var mes = ParsearMes(mesint);
								var mfl="";
								if(lista[i].VAL_PLAZO=='1'){
									 mfl="Sí"
								}else{
									 mfl="No";
								}
								var TIPO="";
								if(lista[i].TIPO=='R'){
									 TIPO="RENUNCIA"
								}else{
									 TIPO="ABANDONO";
								}
								var p = "";
								var f = "";
								var t = "";
								var ct = "";
								(Motivo === 1) ? p = "Trabajador Nuevo"
										: ((Motivo === 2) ? p = "Renovación"
												: p = "No Registrado");
								(MFL === 1) ? f = "Si" : f = "No";
								(plazo === 1) ? t = "Cumplió Plazo"
										: t = "No Cumplió";
								(plazo === 1) ? ct = "green accent-3"
										: ct = "red darken-1";
								s += '<tr>';
								s += '<td>' + a
										+ '<label  class="idtr" hidden>'
										+ lista[i].ID_CONTRATO
										+ '</label><label class="idrenaban" hidden>'
										+ lista[i].ID_RENABAN
										+ '</label></td>';
								s += '<td>' + mes + '</td>';
								s += '<td class="">'

									+ lista[i].PATERNO
									+ ' '
									+ lista[i].MATERNO
									+ ' '
									+ lista[i].NOMBRES
									+ '</td>';
							s += '<td>'
									+ lista[i].NOM_PUESTO
									+ '</td>';
							s += '<td>' + lista[i].NOM_AREA
									+ '</td>';
							s += '<td>' + lista[i].NOM_DEPA
									+ '</td>';
							s += '<td>'
									+ lista[i].TIPO_CONTRATO
									+ '</td>';
								s += '<td><a class="green-text accent-3" href="#">'
										+ lista[i].DESCRIPCION + '</a></td>';
								s += '<td>'
									+lista[i].FECHA_RENABAN+
									 '</td>';
								s += '<td>'
									+lista[i].DNI+
									 '</td>';
								s += '<td>'
									+mfl+
									 '</td>';
								s +='<td >' +TIPO+ '<label class="tipon" hidden>'
								+ TIPO
								+ '</label></td>';
								s += '<td><button class="notificar waves-effect waves-light btn modal-trigger #00e676 green accent-3">Notificar</button>';

								s += '</button>';

								s += '</tr>';
							}

						} else {
							console.log("no hay datos");
							s += "";
						}

						var r = createTable("s", "d");
						$(".contT").empty();
						$(".contT").append(r);
						$("#dataReq").empty();
						$("#dataReq").append(s);
//						$("#data-table-row-grouping").DataTable();
						$("#data-table-row-grouping")
						.DataTable(
								{
								    responsive: true,
								    columnDefs: [
								        { responsivePriority: 1, targets: 0 },
								        { responsivePriority: 2, targets: -1 }
								    ],
								"pageLength" : 5,
								"bPaginate" : true,
								"ordering": false
								}
						);

						$(".notificar").click(
								function() {

									idc = $(this).parents("tr").find("td")
											.eq(0).find(".idtr").text();
									console.log(idc);
									
									tipon = $(this).parents(
									"tr").find("td")
									.find(".tipon")
									.eq(0)
									.text();
									console.log("esto es tipon"+tipon);
									
									idr = $(this).parents(
									"tr").find("td")
									.find(".idrenaban")
									.eq(0)
									.text();
									console.log("esto es idrenaban"+idr);
									
									$("#tipo").text(tipon);
									$("#idr").text(idr);
									verCorreo(idc);

									// $("#otros").val(cantidad);

									// otros
								});
						// $('.dropdown-button')
						// .dropdown(
						// {
						// inDuration : 300,
						// outDuration : 225,
						// constrainWidth : false,
						// hover : true,
						// gutter : 0,
						// belowOrigin : false,
						// alignment : 'left',
						// stopPropagation : false
						// });

					});
}

function listarNotificados() {
	$
			.getJSON(
					gth_context_path + "/renaban/listarxd",
					"opc=7",
					function(objJson) {
						var s = "";
						var lista = objJson;
						console.log(objJson);
						if (lista.length > 0) {
							// alert("si hay datos NOTIFICADOS causita c:");

							for (var i = 0; i < lista.length; i++) {
								var a = parseInt(i) + 1;
								var MFL = parseInt(lista[i].ES_MFL);
								var Motivo = parseInt(lista[i].LI_MOTIVO);
								var plazo = parseInt(lista[i].VAL_PLAZO);
								var fe_creacion = new Date(
										lista[i].FECHA_RENABAN);
								var mesint = parseInt(fe_creacion.getMonth()) + 1;
								console.log(mesint);

								// console.log(ddd(mesint));
								var mes = ParsearMes(mesint);
								var p = "";
								var f = "";
								var t = "";
								var ct = "";
								(Motivo === 1) ? p = "Trabajador Nuevo"
										: ((Motivo === 2) ? p = "Renovación"
												: p = "No Registrado");
								(MFL === 1) ? f = "Si" : f = "No";
								(plazo === 1) ? t = "Cumplió Plazo"
										: t = "No Cumplió";
								(plazo === 1) ? ct = "green accent-3"
										: ct = "red darken-1";
								s += '<tr>';
								s += '<td>' + a
										+ '<label  class="idr" hidden>'
										+ lista[i].ID_RENABAN
										+ '</label></td>';
								s += '<td>' + mes + '</td>';
								s += '<td class="">'

								+ lista[i].MATERNO + ' ' + lista[i].MATERNO
										+ ' ' + lista[i].NOMBRES + '</td>';
								s += '<td>' + lista[i].NOM_PUESTO + '</td>';
								s += '<td>' + lista[i].NOM_AREA + '</td>';
								s += '<td>' + lista[i].NOM_DEPA + '</td>';
								s += '<td>' + lista[i].TIPO_CONTRATO + '</td>';
								s += '<td><a class="green-text accent-3" href="#">'
										+ lista[i].DESCRIPCION + '</a></td>';
								s += '<td>' + fe_creacion.getDate() + "/"
										+ mesint + "/"
										+ fe_creacion.getFullYear() + '</td>';
								s += '<td>' + lista[i].ESTADO + '</td>';
								s += '<td><button class="entregar waves-effect waves-light btn modal-trigger #00e676 green accent-3">ENTREGAR</button>';

								s += '</button>';

								s += '</tr>';
							}

						} else {
							console.log("no hay datos");
							s += "";
						}

						var r = createTable2("s", "d");
						$(".conN").empty();
						$(".conN").append(r);
						$("#dataNot").empty();
						$("#dataNot").append(s);
						$("#data-table-row-grouping1").DataTable();
		
						$(".entregar").click(
								function() {

									idc = $(this).parents("tr").find("td")
											.eq(0).find(".idr").text();
//									alert(idc);
									Entregar(idc);

									// $("#otros").val(cantidad);

									// otros
								});
						// $('.dropdown-button')
						// .dropdown(
						// {
						// inDuration : 300,
						// outDuration : 225,
						// constrainWidth : false,
						// hover : true,
						// gutter : 0,
						// belowOrigin : false,
						// alignment : 'left',
						// stopPropagation : false
						// });

					});
}

function createTable(idDepartamento, idRol) {
	var Rol = idRol.toString();
	var Departamento = idDepartamento.toString();
	var s = '<table id="data-table-row-grouping" class="display responsive" cellspacing="0" style="width:100%"> ';
	s += 'cellspacing="0">';
	s += '<thead>';
	s += '<tr>';
	s += '<th>N</th>';
	s += '<th data-priority="3">Mes</th>';
	s += '<th data-priority="4">Apellidos y Nombres</th>';
	s += '<th data-priority="5">Puesto</th>';
	s += '<th data-priority="6">Area</th>';
	s += '<th data-priority="7">Departamento</th>';
	s += '<th>Tipo de Contrato</th>';
	s += '<th>Descripcion</th>';
	s += '<th>Fecha de registro</th>';
	s += '<th>DNI</th>';
	s += '<th>MFL</th>';	
	s += '<th data-priority="2">Tipo</th>';
	s += '<th data-priority="1">Opcion</th>';
	s += '</tr>';
	s += '</thead>';
	s += '<tbody id="dataReq">';
	s += '</tbody>';
	s += '</table>';
	return s;
}

function createTable2(idDepartamento, idRol) {
	var Rol = idRol.toString();
	var Departamento = idDepartamento.toString();
	var s = '<table id="data-table-row-grouping1" class="display" cellspacing="0" width="100%" style="position:relative;font-size:14px"';
	s += 'cellspacing="0">';
	s += '<thead>';
	s += '<tr>';
	s += '<th>N</th>';
	s += '<th>Mes</th>';
	s += '<th>Apellidos y Nombres</th>';
	s += '<th>Puesto</th>';
	s += '<th>Area</th>';
	s += '<th>Departamento</th>';
	s += '<th>Tipo de Contrato</th>';
	s += '<th>Descripcion</th>';
	s += '<th>Fecha de Inicio</th>';
	s += '<th>Estado</th>';
	s += '<th>Opcion</th>';
	if (Departamento === "DPT-0019") {
		s += '<th>¿Cumplió Plazos?</th>';
		if (Rol === "ROL-0006") {
			s += '<th>¿Contrato Elaborado?</th>';
			s += '<th>¿Firmo Contrato?</th>';
			s += '<th>Enviar a Rem.</th>';
			s += '<th>¿Contrato Subido?<</th>';
		}
	}
	if (Rol === "ROL-0009") {
		s += '<th>Código APS</th>';
	}
	if (Rol === "ROL-0007" || Rol === "ROL-0001") {
		s += '<th>Código Huella</th>';
	}
	s += '</tr>';
	s += '</thead>';
	s += '<tbody id="dataNot">';
	s += '</tbody>';
	s += '</table>';
	return s;
}

function ParsearMes(mesint) {
	var mes;
	// console.log(mesint);
	switch (mesint) {
	case 01:
		mes = "Enero";
		break;
	case 02:
		mes = "Febrero";
		break;
	case 03:
		mes = "Marzo";
		break;
	case 04:
		mes = "Abril";
		break;
	case 05:
		mes = "Mayo";
		break;
	case 06:
		mes = "Junio";
		break;
	case 07:
		mes = "Julio";
		break;
	case 08:
		mes = "Agosto";
		break;
	case 09:
		mes = "Septiembre";
		break;
	case 10:
		mes = "Octubre";
		break;
	case 11:
		mes = "Noviembre";
		break;
	case 12:
		mes = "Diciembre";
		break;
	}
	return mes;
}

// MOSTRANDO LOS DETALLES DE TRABAJADOR
function verCorreo(idc) {
	var inst = $('[data-remodal-id=modal]').remodal();

	// alert(idc);
	// dni = $("#dni").val();
	$.get(gth_context_path+"/renaban/listarxd", {
		idc : idc,
		opc : 2
	}, function(data, status) {
		// console.log(data);
		inst.open();
		var detalle = JSON.parse(data);
		console.log(detalle);
		$("#correo").text(detalle[0].CORREO);
		$("#idtr").text(detalle[0].ID_TRABAJADOR);
		$("#idr").text(detalle[0].ID_RENABAN);

		$("#nombre").text(
				detalle[0].NOMBRES + " " + detalle[0].PATERNO + " "
						+ detalle[0].MATERNO);

	});

}

//ENVIAR CORREO
function enviarCorreo() {
	// alert();
	var msj = $("#mensaje1").text();
	var de = "pruebagth@gmail.com";
//	var para = $("#correo").text();
	var para = "estefannygarcia@upeu.edu.pe";
	var clave = "GTH123456";
	var mensaje = $("#mensaje2").text();
	var msjs = msj + $("#fecha").val() + mensaje + ".";
	var asunto = "GTH";
	var idrenaban =$("#idr").text();
	var tipo =$("#tipo").text();
	// console.log(msjs);
	$.get(gth_context_path+"/renaban/listarxd", {
		de : de,
		clave : clave,
		para : para,
		mensaje : msjs,
		asunto : asunto,
		opc : 3
	}, function(data, status) {
		console.log(data);
		// $("#modalnotificar").closeModal();
		if (data == 1) {
			// alert("SE MANDO");
			notificar(idrenaban,tipo);
			// insertarLegajo();
			
		} else {
			// alert(" NOOOOOOOOOOOOO SE MANDO");
		}

	});
}

//INSERTAR LEGAJO
function insertarLegajo() {
	var inst = $('[data-remodal-id=modal]').remodal();
	var msj = $("#mensaje1").text();
	var de = "pruebagth@gmail.com";
	var para = "jonathanromero@upeu.edu.pe";
	var mensaje = $("#mensaje2").text();
	var detalle = msj + $("#fecha").val() + mensaje + ".";
	var asunto = "GTH";
	var otros = de + " para: " + para;
	var idtr = $("#idtr").text();
	// console.log(msjs);
	$.get(gth_context_path+"/renaban/listarxd", {
		idtr : idtr,
		otros : otros,
		detalle : detalle,
		opc : 4
	}, function(data, status) {
		console.log(data);
		inst.close();
		if (data == 1) {
			// alert("SE MANDO");
		} else {
			// alert(" NOOOOOOOOOOOOO SE MANDO");
		}

	});
}

//NOTIFICAR RENUNCIA
function notificar(idrab,tipo1) {
//	var inst = $('[data-remodal-id=modal]').remodal();
	var idra = idrab;
	var tipo1 = tipo1;
	alert(idrab);
	alert(tipo1);
	if(tipo1=="RENUNCIA"){
		alertify.confirm('Confirmar Notificación', 'Está seguro(a) de notificar la renuncia de este trabajador?', function(){
			 $.get("listarxd",{opc:6,tipo1:'R',idra:idra},function(data){
				 window.location.href = gth_context_path +"/renaban/deliveryR";					 
				
        		 alert(data);
        	});
			 
	     	} , function(){ 
	     		
	        });
	}else{
		alertify.confirm('Confirmar Notificación', 'Está seguro(a) de notificar el abandono de este trabajador?', function(){
			 $.get("listarxd",{opc:6,tipo1:'A',idra:idra},function(data){
				 window.location.href = gth_context_path +"/renaban/deliveryR";					 
				
        		 alert(data);
        	});
			 
	     	} , function(){ 
	     		
	        });
	}
//	$.get(gth_context_path+"/renuncias/listarxd", {
//		idr : idr,
//		tipo1:tipo1,
//		opc : 6
//	}, function(data, status) {
//		console.log(data);
//		
//		if (data == 1) {
//			inst.close();
//			$.toast({
//			    heading: 'Correcto!',
//			    text: 'Notificado correctamente',
//			    showHideTransition: 'fade',
//			    icon: 'success'
//			})
//			 alert("NOTIFICADO :v");
//			listarNotificados();
//			listarProcesados();
//		} else {
//			 alert(" NOOOOOOOOOOOOO SE MANDO");
//		}
//
//	});
}

function Entregar(idc) {
	
	var inst = $('[data-remodal-id=modal1]').remodal();

//	alert(idc);
	$("#not_idr").val(idc);
//	var idr = $("#idr").text();
	

	$.get(gth_context_path+"/renaban/entregar", {
		idr : idc,
		opc : 1
	}, function(data, status) {
		console.log(data);
		
		$("#not_idr").val(idc);
		inst.open();

//		$("#modalentregar").openModal();
		
	});
}

// FECHA
$('.datepicker').pickadate({
	selectMonths : true, // Creates a dropdown to control month
	selectYears : 15, // Creates a dropdown of 15 years to control
	format : 'dd/mm/yyyy'
});
