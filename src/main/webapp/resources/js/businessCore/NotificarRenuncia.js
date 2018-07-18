$(document).ready(function() {
	listarProcesados();
	listarNotificados();
	listarEntregados();
	$('#delivery').bind('click', function() {
        if($(this).hasClass('active')) {
        	var table = $('#data-table-row-grouping2').DataTable();
        	 
        	$('#data-table-row-grouping2').css( 'display', 'table' );
        	 
        	table.responsive.recalc();
        }
    });
	$('#autorized').bind('click', function() {
        if($(this).hasClass('active')) {
        	var table = $('#data-table-row-grouping1').DataTable();
        	 
        	$('#data-table-row-grouping1').css( 'display', 'table' );
        	 
        	table.responsive.recalc();
        }
    });
	
	$('#autorize').bind('click', function() {
        if($(this).hasClass('active')) {
        	var table = $('#data-table-row-grouping').DataTable();
        	 
        	$('#data-table-row-grouping').css( 'display', 'table' );
        	 
        	table.responsive.recalc();
        }
    });
	$('.dropify').dropify(function(event, element){
        return confirm("¿Desea eliminar el archivo \"" + element.filename + "\" ?");
    });
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
});
function show_image(src, width, height, alt) {
    var img = document.createElement("img");
    img.src = src;
    img.width = width;
    img.height = height;
    img.alt = alt;
    document.body.appendChild(img);
}

//LISTAR LOS PROCESADOS DE RENUNCIA Y ABANDONO
function listarProcesados() {
				$.getJSON(
					gth_context_path + "/renaban/listarxd",
					"opc=1",
					function(objJson) {
						var s = "";
						var lista = objJson;
						if (lista.length > 0) {							
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
								s +='<td >' +TIPO+ '<label class="tipon" hidden>'
								+ TIPO
								+ '</label></td>';
								s += '<td><a class="notificar waves-effect waves-light btn #00e676 green accent-3">Notificar</a>';								
								s += '</td>';
								s += '</tr>';
							}
						} else {
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
									tipon = $(this).parents(
									"tr").find("td")
									.find(".tipon")
									.eq(0)
									.text();									
									idr = $(this).parents(
									"tr").find("td")
									.find(".idrenaban")
									.eq(0)
									.text();									
									$("#tipo").text(tipon);
									$("#idr").text(idr);
									verCorreo(idc);
								});
					});
}
//DISEÑO DE LA TABLA 1
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

//LISTAR TODOS LOS NOTIFICADOS 
function listarNotificados() {
				$.getJSON(
					gth_context_path + "/renaban/listarxd",
					"opc=7",
					function(objJson) {
						var s = "";
						var lista = objJson;
						console.log(objJson);
						if (lista.length > 0) {
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
							"ordering": false,
							responsive:true
							    }
						);
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
								});
						
					});
}

function listarEntregados() {
	$
			.getJSON(
					gth_context_path + "/renaban/listarxd",
					"opc=8",
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
								s +='<td>' +TIPO+'<label class= "tipazo" hidden>'
								+ TIPO
								+ '</label></td>';
								s += '</tr>';
							}

						} else {
							//alert("no hay datos");
							s += "";
						}
						var r = createTable2();
						$(".contE").empty();
						$(".contE").append(r);
						$("#dataReq2").empty();
						$("#dataReq2").append(s);
						
						$("#data-table-row-grouping2")
						.DataTable({
							"pageLength" : 5,
							 columnDefs: [
							        { responsivePriority: 1, targets: 0 },
							        { responsivePriority: 2, targets: -1 },
							        { responsivePriority: 3, targets: -2 },
							        { responsivePriority: 4, targets: -3 },
							        { responsivePriority: 5, targets: -4 },
							  
							    ],
							"bPaginate" : true,
							"ordering": false,
							responsive:true
							    }
						);
						
//						jQuery('.dataTable').wrap('<div class="dataTables_scroll" />');

					});
}

//DISEÑO DE LA TABLA 2
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
	s += '</tbody>';
	s += '</table>';
	return s;
}

function createTable2() {
	var s = '<table id="data-table-row-grouping2" class="bordered centered display" cellspacing="0" style="width:100%;" >';
	s += '<thead>';
	s += '<tr>';
	s += '<th>N</th>';     
	s += '<th data-priority="1">Mes</th>';
	s += '<th data-priority="3">Apellidos y Nombres</th>';
	s += '<th data-priority="4">Puesto</th>';
	s += '<th data-priority="5">Area</th>';
	s += '<th data-priority="6">Departamento</th>';
	s += '<th data-priority="7">Tipo de Contrato</th>';
	s += '<th data-priority="8">Descripcion</th>';
	s += '<th data-priority="9">Fecha de registro</th>';
	s += '<th>DNI</th>';
	s += '<th>MFL</th>';
	s += '<th data-priority="2">Tipo</th>';
	s += '</tr>';
	s += '</thead>';
	s += '<tbody id="dataReq2">';
//	s += '<tbody id="dataNot">';
	s += '</tbody>';
	s += '</table>';
	return s;
}
var depa="";
var u = "";

// MOSTRANDO LOS DETALLES DE TRABAJADOR
function verCorreo(idc) {
	var inst = $('[data-remodal-id=modal]').remodal();
	$.get(gth_context_path+"/renaban/listarxd", {
		idc : idc,
		opc : 2
	}, function(data, status) {
		inst.open();
		var detalle = JSON.parse(data);
		$("#correo").text(detalle[0].CORREO);
		$("#idtr").text(detalle[0].ID_TRABAJADOR);
		$("#idr").text(detalle[0].ID_RENABAN);
		$("#nombre").text(detalle[0].NOMBRES + " " + detalle[0].PATERNO + " "+ detalle[0].MATERNO);
	});
}

//ENVIAR CORREO
function enviarCorreo() {
	var msj = $("#mensaje1").text();
	var de = "pruebagth@gmail.com";
	var para = "estefannygarcia@upeu.edu.pe";
	var clave = "GTH123456";
	var mensaje = $("#mensaje2").text();
	var msjs = msj + $("#fecha").val() + mensaje + ".";
	var asunto = "GTH";
	var idrenaban =$("#idr").text();
	var tipo =$("#tipo").text();
	$.get(gth_context_path+"/renaban/listarxd", {
		de : de,
		clave : clave,
		para : para,
		mensaje : msjs,
		asunto : asunto,
		opc : 3
	}, function(data, status) {
		console.log(data);
		if (data == 1) {
			notificar(idrenaban,tipo);			
		} else {

		}
	});
}

//NOTIFICAR RENUNCIA
function notificar(idrab,tipo1) {
	var idra = idrab;
	var tipo1 = tipo1;
	if(tipo1=="RENUNCIA"){
		swal({
    		title: "¿Está seguro de notificar",
    		text: "la renuncia de este trabajador?",
    		type: "warning",
    		showCancelButton: true,
    		confirmButtonColor: '#40D005',
    		confirmButtonText: 'Confirmar',
    		cancelButtonText: 'Cancelar',
    		closeOnConfirm: false,
    		closeOnCancel: false
    	},		        	
    	function(isConfirm){
        if (isConfirm){
        	$.get("listarxd",{opc:6,tipo1:'R',idra:idra},function(data){													 
				if (data==1){
					  swal("Se Notifico", "la renuncia con éxito", "success");
					  window.setTimeout(function() {							
						  window.location.href = gth_context_path +"/renaban/deliveryR";
						}, 2000);
				}else{
					swal("Error", "Contactarse con el administrador", "error");
				}
        	});			            				           							 					     	 			        	
        } else {
        	window.location.href = gth_context_path +"/renaban/deliveryR";
        }
    	});
	}else{
		swal({
    		title: "¿Está seguro de notificar",
    		text: "el abandono de este trabajador?",
    		type: "warning",
    		showCancelButton: true,
    		confirmButtonColor: '#40D005',
    		confirmButtonText: 'Confirmar',
    		cancelButtonText: "Cancelar",
    		closeOnConfirm: false,
    		closeOnCancel: false
    	},
		function(isConfirm){
            if (isConfirm){
            	$.get("listarxd",{opc:6,tipo1:'A',idra:idra},function(data){														 
					if (data==1){
						  swal("Se Notifico", "el abandono con éxito", "success");
						  window.setTimeout(function() {							
							  window.location.href = gth_context_path +"/renaban/deliveryR";
							}, 2000);
					}else{
						swal("Error", "Contactarse con el administrador", "error");
					}
	        	});			            				           							 					     	 			        	
            } else {
            	window.location.href = gth_context_path +"/renaban/deliveryR";
            }
        	});
	}
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
    var fecha1=$("#fecha1").val();
    var fecha2=$("#fecha2").val();
    var fecha3=$("#fecha3").val();
    var fecha4=$("#fecha4").val();
    
    open();
    swal({
		title: "¿Está seguro de confirmar",
		text: "la entrega de documentos?",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: '#40D005',
		confirmButtonText: 'Confirmar',
		cancelButtonText: 'Cancelar',
		closeOnConfirm: false,
		closeOnCancel: false
	},
	function(isConfirm){
        if (isConfirm){												 
				if (liquidacion!=""&&cts!=""&&certificado!=""&&remu!=""){
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
		       swal("Se Entrego", "los documentos con éxito", "success");
				  window.setTimeout(function() {							
					  window.location.href = gth_context_path+ '/renaban/deliveryR';
					}, 2000);
		                },
		            });					  
				}else{
					swal("Error", "Contactarse con el administrador", "error");
				}		            				           							 					     	 			        	
        } else {
        	window.location.href = gth_context_path+ '/renaban/deliveryR';
        }
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

//NUEVO DISEÑO DE ALERTYFY
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
//FECHA
 var fecha = new Date();
	var dd = fecha.getDate();
	var mm = fecha.getMonth(); //January is 0!
	var yyyy = fecha.getFullYear();
	if(dd<10){ 
	    dd='0'+dd;
	} 
	if(mm<10){
	    mm='0'+mm;
	} 
	var today = dd+'/'+mm+'/'+yyyy;
	$("#fecha").val(today);
window.picker = $('.datepicker').pickadate({
	selectMonths : true, // Creates a dropdown to control month
	selectYears : 100, // Creates a dropdown of 15 years to control year
	format : 'dd/mm/yyyy',
	onStart: function ()
    {
		this.set('select', today);
    }
});

function id(ida) {
	alert(ida);
}

function ParsearMes(mesint) {
	var mes;
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
