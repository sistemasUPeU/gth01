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
//    show_image('C:/Users/Deyvis Garcia/eclipse-workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/gth/WEB-INF/29791352_2077305459260623_8230612622916918394_n.jpg',456,456,'Google Logo');

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
//						console.log(objJson);
						if (lista.length > 0) {
							
							// alert("si hay datos causita c:");	
							for (var i = 0; i < lista.length; i++) {
								var a = parseInt(i) + 1;
								var MFL = parseInt(lista[i].ES_MFL);
								var Motivo = parseInt(lista[i].LI_MOTIVO);
								var plazo = parseInt(lista[i].VAL_PLAZO);
								var fe_creacion = new Date(
										lista[i].FECHA_RENABAN);
								var mesInt = parseInt(fe_creacion
										.getMonth()) + 1;
								var mes = ParsearMes(mesInt);
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
								s += '<tr>';
								s += '<td>'
										+ a
										+ '<label  class="idc" hidden>'
										+ lista[i].ID_CONTRATO
										+ '</label><label class="idrenaban" hidden>'
										+ lista[i].ID_RENABAN
										+ '</label></td>';
								s += '<td>'
										+ mes;
										+ '</td>';
								s += '<td class="">'
								

								+ lista[i].PATERNO + ' ' + lista[i].MATERNO
								+ ' ' + lista[i].NOMBRES + '</td>';
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
										+ lista[i].DESCRIPCION
										+ '</a></td>';
								s += '<td>'
										+lista[i].FECHA_RENABAN+
										 '</td>';
								s += '<td>'
									+lista[i].DNI+
									 '</td>';
								s += '<td>'
									+mfl+
									 '</td>';
								// s += '<td>' + p + '</td>';
//								s += '<td><a class="blue-text accent-4" href="#"><b>' + lista[i].ESTADO
//										+ '</b></a></td>';
								s +='<td >' +TIPO+ '<label class="tipon" hidden>'
								+ TIPO
								+ '</label></td>';
								s += '<td><a class="notificar waves-effect waves-light btn #00e676 green accent-3">Notificar</a>';
								
								s += '</td>';
								s += '</tr>';
							}

						} else {
							//alert("no hay datos");
							s += "";
						}

						var r = createTable("s", "d");
						$(".contT").empty();
						$(".contT").append(r);
						$("#dataReq").empty();
						$("#dataReq").append(s);
						
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
								"ordering": false,
								
								}
						);

						$(".notificar").click(
								function() {

									idc = $(this).parents("tr").find("td")
											.eq(0).find(".idc").text();
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
function createTable(idDepartamento, idRol) {
	var Rol = idRol.toString();
	var Departamento = idDepartamento.toString();
	var s = '<table id="data-table-row-grouping" class="display responsive" cellspacing="0" style="width:100%"> ';
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
function modalon (){
	var s = '<input type="hidden" name="idcontrato" id="idcontrato" value="">';
	s+= ' <div class="input-field col s6">';
	s+= ' <input name="renabancito" id="renabancito" value="" type="hidden"/>';
	s+= ' <input name ="tipo" id="tipo" value="" type="hidden"/>';
	s+= ' <input name="nom_file" id="nom_file" value="" type="hidden"/>';
	s+= ' <label for=""></label> <input type="text" name="fecha"';
	s+=		' id="fecha" class="datepicker">';
	s+= '</div>'
	s+= '<div class="input-field col s6">'
	s+= '<input type="file" name="file" class="dropify" id="pelon1"'
	s+= 'data-height="300" />'
	s+= '</div>'
		
		return s;
	
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
							// alert("si hay datos causita c:");

							for (var i = 0; i < lista.length; i++) {
								var a = parseInt(i) + 1;
								var MFL = parseInt(lista[i].ES_MFL);
								var Motivo = parseInt(lista[i].LI_MOTIVO);
								var plazo = parseInt(lista[i].VAL_PLAZO);
								var fe_creacion = new Date(
										lista[i].FECHA_RENABAN);
								var mesInt = parseInt(fe_creacion
										.getMonth()) + 1;
								var mes = ParsearMes(mesInt);
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
								s += '<tr>';
								s += '<td>'
										+ a
										+ '<label  class="idc" hidden>'
										+ lista[i].ID_CONTRATO
										+ '</label>'
										+ '<label class="tipo" hidden>'
										+ lista[i].TIPO
										+ '</label>'
										+ '<label  class="idr" hidden>'
										+ lista[i].ID_RENABAN
										+ '</label>'
										+ '<label  class="idt" hidden>'
										+ lista[i].ID_TRABAJADOR
										+ '</label>'
										+'<label class="idrenaban" hidden>'
								+ lista[i].ID_RENABAN
								+ '</label></td>';
								s += '<td>'
										+ mes;
										+ '</td>';
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
										+ lista[i].DESCRIPCION
										+ '</a></td>';
								s += '<td>'
										+lista[i].FECHA_RENABAN+
										 '</td>';
								s += '<td>'
									+lista[i].DNI+
									 '</td>';
								s += '<td>'
									+mfl+
									 '</td>';
								s +='<td>' +TIPO+'<label class= "tipon" hidden>'
								+ TIPO
								+ '</label></td>';
								s += '<td><a class="entregar waves-effect waves-light btn #00e676 green accent-3" data-remodal-target="modal1">ENTREGAR</a>';
								
								s += '</td>';
								s += '</tr>';
							}

						} else {
							//alert("no hay datos");
							s += "";
						}

						var r = createTable1("", "");
						$(".contP").empty();
						$(".contP").append(r);
						$("#dataReq1").empty();
						$("#dataReq1").append(s);
						
						$("#data-table-row-grouping1")
						.DataTable({
							"pageLength" : 10,
							"bPaginate" : true,
							"ordering": false
							    }
						);
						
//						$(".InsertarLegajo").click(
//								function() {
//
//									idc = $(this).parents("tr").find("td")
//											.eq(0).find(".idtr").text();
//									console.log(idc);
//									
//									tipon = $(this).parents(
//									"tr").find("td")
//									.find(".tipon")
//									.eq(0)
//									.text();
//									console.log("esto es tipon"+tipon);
//									
//									idr = $(this).parents(
//									"tr").find("td")
//									.find(".idrenaban")
//									.eq(0)
//									.text();
//									console.log("esto es idrenaban"+idr);
//									
//									$("#tipo").text(tipon);
//									$("#idr").text(idr);
//									verCorreo(idc);
//
//									// $("#otros").val(cantidad);
//
//									// otros
//								});

		
						$(".entregar").click(
								function() {

									idc = $(this).parents("tr").find("td")
											.eq(0).find(".idc").text();
									
									idt = $(this).parents("tr").find("td")
									.eq(0).find(".idt").text();
									
									idr = $(this).parents("tr").find("td")
									.eq(0).find(".idr").text();
									
									tipo = $(this).parents("tr").find("td")
									.eq(0).find(".tipo").text();
									
									$("#idc").val(idc);
									$("#idt").val(idt);
									$("#idra").val(idr);
									$("#tipon").val(tipo);
									console.log($("#idc").val()+" "+$("#idt").val()+" idrenabaaan "+$("#idra").val()+" tipoooo "+$("#tipon").val());
//									alert(idc);
//									Entregar(idc);

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
						jQuery('.dataTable').wrap('<div class="dataTables_scroll" />');

					});
}


function createTable1(idDepartamento, idRol) {
	var Rol = idRol.toString();
	var Departamento = idDepartamento.toString();
	var s = '<table id="data-table-row-grouping1" class="bordered centered display" cellspacing="0" style="width:100%;" >';
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
	s += '<th>Fecha de registro</th>';
	s += '<th>DNI</th>';
	s += '<th>MFL</th>';
	s += '<th>Tipo</th>';
	s += '<th>Opcion</th>';
	s += '</tr>';
	s += '</thead>';
	s += '<tbody id="dataReq1">';
//	s += '<tbody id="dataNot">';
	s += '</tbody>';
	s += '</table>';
	return s;
}

var depa="";
var u = "";

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

		$("#nombre").text(detalle[0].NOMBRES + " " + detalle[0].PATERNO + " "
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
if(!alertify.errorAlert){
	  alertify.dialog('errorAlert',function factory(){
	    return{
	            build:function(){
	                var errorHeader = '<span class="fa fa-times-circle fa-2x" '
	                +    'style="vertical-align:middle;color:#e10000;">'
	                + '</span> Error al guardar los datos';
	                this.setHeader(errorHeader);
	            }
	        };
	    },true,'alert');
	}
//INSERTAR AL LEGAJO
$("#entredoc").click(function (event) {
	event.preventDefault();
    var form = $('#EntregaForm')[0];
    var data = new FormData(form);
    var liquidacion=$("#liquidacion").val();
    var cts=$("#cts").val();
    var certificado=$("#certificado").val();
    var remu=$("#remu").val();    
    open();
    alert("llego documentos:");
    alertify.confirm('Confirmar entrega de docuemntos', 'Esta seguro(a) de confirmar'+ 
    		' la entrega de documentos?', function(){    	
    	if(liquidacion!=""&&cts!=""&&certificado!=""&&remu!=""){
        	$.ajax({
                type: "POST",
                enctype: 'multipart/form-data',
                url: "holamundo",
                data: data,
                processData: false,
                contentType: false,
                cache: false,
                timeout: 600000,
                success: function (data) {
       $("#entredoc").prop("disabled", false);
       
       $( "#card-alert" ).fadeIn(1200, function() {
		    // Animation complete.
    	   listarNotificados();
			window.setTimeout(function() {
				
			    $("#card-alert").fadeTo(1000, 0).slideUp(800, function(){
			        $(this).hide(); 
			        window.location.href = gth_context_path+ '/renaban/deliveryR';
			    });
			}, 2000);
		  });
//       alertify.notify('Se ha registrado la renuncia satisfactoriamente.'+ 
//    		   'Redireccionando a reportes...', 'custom', 2,
//					function() {
//                  	window.location.href = gth_context_path+ '/renaban/deliveryR';
//					});
                },
                error: function (e) {
                	alertify
               .errorAlert("Ha ocurrido un problema, comuníquese con el administradord" +
               		" el sistema.<br/>");
                }
            });
        }else{          	
        	alertify
            .errorAlert("Rellene todos los campos<br/>");
        }      	
    	}
    , function(){        	     	
    });
});
function open(){
	$(".ajs-header").addClass("#82b1ff  blue accent-1");
	var isOpen = alertify.confirm().isOpen(); 
	 if(isOpen=true){
		 $(".ajs-ok").attr("id","alertyboton");
		 $(".ajs-cancel").attr("id","alertyboton2");
		 $(".alertify .ajs-modal").css("z-index","999999");
	 }
	 $("#alertyboton").addClass("btn waves-effect waves-light #2962ff blue accent-4");
		$("#alertyboton2").addClass("btn waves-effect waves-light #bdbdbd grey lighten-1");
}
//INSERTAR LEGAJO
function NotificarR() {
//	alert(jfksdf);
	var msj = $("#liquidacion").text();
	var de = "pruebagth@gmail.com";
//	var para = $("#correo").text();
	var para = "estefannygarcia@upeu.edu.pe";
	var clave = "GTH123456";
	var mensaje = $("#mensaje2").text();
	var msjs = msj + $("#fecha").val() + mensaje + ".";
	var asunto = "GTH";
	var idrenaban =$("#idr").text();
	var tipo =$("#tipo").text();
	alert(mensaje);
	 console.log(msjs);
	$.get(gth_context_path+"/renaban/entregar", {
		de : de,
		clave : clave,
		para : para,
		mensaje : msjs,
		asunto : asunto,
//		foto:foto,
		opc : 2
	}, function(data, status) {
		console.log(data);
		// $("#modalnotificar").closeModal();
		if (data == 1) {
			alert("SE MANDO");
			InsertarLegajo(idle);
//			listarAutorizados();     
			// insertarLegajo();
		} else {
			alert(" NOOOOOOOOOOOOO SE MANDO");
		}

	});
}

//ENTREGAR DOCUMENTOS
function entregar(idle) {
	var idle = idle;
//	var tipo1 = tipo1;
	alert("Insertar legajo entrooooooooo");
	alert(idle);
//	alert(tipo1);
	alertify.confirm('Confirmar Notificación', 'Está seguro(a) de entregar los documentos?', function(){
		 $.get("entregar",{opc:3,idle:idle},function(data){
			 window.location.href = gth_context_path +"/renaban/deliveryR";					 
			
   		 alert(data);
   	});
		 
    	} , function(){ 
    		
       });
}

//NOTIFICAR RENUNCIA
function notificar(idrab,tipo1) {
//	var inst = $('[data-remodal-id=modal]').remodal();
	var idra = idrab;
	var tipo1 = tipo1;
//	alert(idrab);
//	alert(tipo1);
	if(tipo1=="RENUNCIA"){
		alertify.confirm('Confirmar Notificación', 'Está seguro(a) de notificar la renuncia de este trabajador?', function(){
			 $.get("listarxd",{opc:6,tipo1:'R',idra:idra},function(data){
				 window.location.href = gth_context_path +"/renaban/deliveryR";					 
				
//        		 alert(data);
        	});
			 
	     	} , function(){ 
	     		
	        });
	}else{
		alertify.confirm('Confirmar Notificación', 'Está seguro(a) de notificar el abandono de este trabajador?', function(){
			 $.get("listarxd",{opc:6,tipo1:'A',idra:idra},function(data){
				 window.location.href = gth_context_path +"/renaban/deliveryR";					 
				
//        		 alert(data);
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
//nuevo diseño
function someFunction() {
    setTimeout(function(){$('#feedbacker').nextStep();},1000);
 }

 function someOtherFunction() {
     return true;
 }

 $(document).ready(function(){
    $('.toc-wrapper').pushpin({ top: $('.toc-wrapper').offset().top, offset: 77 });
    $('.scrollspy').scrollSpy();
    $('.stepper').activateStepper();
 });
 
// FECHA
$('.datepicker').pickadate({
	selectMonths : true, // Creates a dropdown to control month
	selectYears : 15, // Creates a dropdown of 15 years to control
	format : 'dd/mm/yyyy'
});

window.picker = $('.datepicker').pickadate({
	selectMonths : true, // Creates a dropdown to control month
	selectYears : 100, // Creates a dropdown of 15 years to control year
	format : 'dd/mm/yyyy'
});
$("#Date").val('SYSDATE');

function id(ida) {
	alert(ida);
}
