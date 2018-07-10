$(document).ready(function() {
	$('.tooltipped span').css("z-index","9999999");
	$('.tooltipped').tooltip({delay: 50});	 
	listarRegistrados();
	listarDerivados();	
	$('#autorized').bind('click', function() {
        if($(this).hasClass('active')) {
        	var table = $('#data-table-row-grouping2').DataTable();        	 
        	$('#data-table-row-grouping2').css( 'display', 'table' );        	 
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
	if(!alertify.errorAlert){
		  alertify.dialog('errorAlert',function factory(){
		    return{
		            build:function(){
		                var errorHeader = '<span class="fa fa-times-circle fa-2x" '
		                + 'style="vertical-align:middle;color:#e10000;">'
		                + '</span> Error al guardar los datos';
		                this.setHeader(errorHeader);
		            }
		        };
		    },true,'alert');
		}
});
//LISTAR REGISTRADOS DE RENUNCIA Y ABANDONO
function listarRegistrados() {
			$.getJSON(
					gth_context_path + "/renaban/detalleR",
					"opc=6",
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
								var mesInt = parseInt(fe_creacion.getMonth()) + 1;
								var mes = ParsearMes(mesInt);
								var mfl = "";
								if (lista[i].VAL_PLAZO == '1') {
									mfl = "Sí"
								} else {
									mfl = "No";
								}
								var TIPO = "";
								if (lista[i].TIPO == 'R') {
									TIPO = "RENUNCIA"
								} else {
									TIPO = "ABANDONO";
								}
								s += '<tr>';
								s += '<td>' + a + '<label  class="idc" hidden>'
										+ lista[i].ID_CONTRATO
										+ '</label></td>';
								s += '<td>' + mes;
								+'</td>';
								s += '<td class="">'
								+ lista[i].PATERNO + ' ' + lista[i].MATERNO
										+ ' ' + lista[i].NOMBRES + '</td>';
								s += '<td>' + lista[i].NOM_PUESTO + '</td>';
								s += '<td>' + lista[i].NOM_AREA + '</td>';
								s += '<td>' + lista[i].NOM_DEPA + '</td>';
								s += '<td>' + lista[i].TIPO_CONTRATO + '</td>';
								s += '<td><a class="green-text accent-3" href="#">'
										+ lista[i].DESCRIPCION + '</a></td>';
								s += '<td>' + lista[i].FECHA_RENABAN + '</td>';
								s += '<td>' + lista[i].DNI + '</td>';
								s += '<td>' + mfl + '</td>';
								s += '<td>'
										+ ((lista[i].JUSTIFICACION == undefined) ? "-"
												: lista[i].JUSTIFICACION)
										+ '</td>';
								s += '<td>'
										+ ((lista[i].RECHAZO == undefined) ? "-"
												: lista[i].RECHAZO) + '</td>';
								s += '<td >' + TIPO
										+ '<label class="tipon" hidden>' + TIPO
										+ '</label></td>';
								s += '<td style="width:25%">'
								s += '	<div class ="row"><div class="col s4"><a onclick="crearModal(\''
										+ lista[i].ID_RENABAN
										+ '\') " data-remodal-target="modal" '
								s += '	class="btn-floating btn waves-effect waves-light accent-3 orange modal-trigger tooltipped " data-position="bottom" data-delay="50" data-tooltip="I am tooltip"'
								s += '	>'
								s += '	<i class="mdi-content-create"></i>'
								s += '</a></div>'
								s+= '<div class="col s4"><a class="btn-floating waves-effect waves-light #ff9100 red accent-3 btn tooltipped" data-position="bottom" data-delay="50" data-tooltip="I am tooltip" onclick="eliminar(\''
										+ lista[i].ID_RENABAN + '\',\''
										+ lista[i].ARCHIVO+'\')">'
								s += '<i class="mdi-action-delete"></i></a></div>'
								s+= '<div class="col s4"><a class="btn-floating waves-effect waves-light #64dd17 light-green accent-4 btn tooltipped" data-position="bottom" data-delay="50" data-tooltip="I am tooltip" onclick="aceptarRenaban(\''
										+ lista[i].ID_RENABAN + '\', \''
										+TIPO+'\')">'
								s += '<i class="mdi-navigation-check"></i></a></div></div></td>';
								s += '</tr>';
							}
						} else {
							s += "";
						}
						var r = createTable();
						$(".contT").empty();
						$(".contT").append(r);
						$("#dataReq").empty();
						$("#dataReq").append(s);
						$("#data-table-row-grouping").DataTable({
							responsive : true,
							columnDefs : [ {
								responsivePriority : 1,
								targets : 0
							}, {
								responsivePriority : 2,
								targets : -1
							} ],
							"pageLength" : 2,
							"bPaginate" : true,
							"ordering": false							 
						});
						$("#data-table-row-grouping tbody").on(
								'click',
								'.notificar',
								function() {
									cantidad = $(this).parents("tr").find("td")
											.eq(0).find(".idc").text();
									tipon = $(this).parents("tr").find("td")
											.find(".tipon").eq(0).text();
									DetalleRenuncia(cantidad, tipon);
									$("#otros").val(cantidad);
									$("#tipo").val(tipon);
								});
					});
}

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

function crearModal(id) {
	$(document).on('opening', '.remodal', function() {
		var m = modalon();
		$("#modalon").empty();
		$("#modalon").html(m);
		$("#pelon1").val("");
	});
	$.getJSON(gth_context_path + "/renaban/detalleR",
			{opc:8,idrenaban:id},
			function(data){
				fechon= data[0].FECHA_CARTA;
				var fecha = new Date(fechon);
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
				var newDate = new Date(yyyy,mm,dd);
				$("#renabancito").val(id);
				$("#idcontrato").val(data[0].ID_CONTRATO);
				$("#tipo").val(data[0].TIPO);
				$("#nom_file").val(data[0].ARCHIVO);
			    $('#fecha').pickadate({
					selectMonths : true, // Creates a dropdown to control month
					selectYears : 15, // Creates a dropdown of 15 years to control
					format : 'dd/mm/yyyy',
					onStart: function() {
						this.set('select', newDate);
					}
				});
			   var archivo = gth_context_path + '/resources/files/'
				+ data[0].ARCHIVO			   
			   $('.dropify').dropify( {
				   "defaultFile": archivo,
		            "messages": {
		                    default: 'Arrastre un archivo o haga clic aquí',
		                    replace: 'Arrastre un archivo o haga clic en reemplazar',
		                    remove: 'Eliminar',
		                    error: 'Lo sentimos, el archivo demasiado grande'
		                }		          
				});
	});			
}

$(document).on('confirmation', '.remodal', function () {
	var file=$("#pelon1").val();
	var renaban = $('#UpdatingR')[0];
	//Obtener el formulario
	var idrenaban = $("#renabancito").val();
	var fecha = $("#fecha").val();
	// Crear un objeto formData para capturar los valores indiviuales del formulario
    var data = new FormData(renaban);
//    console.log(data);
    var nom_archivo = $("#nom_file").val();
	open();
    alertify.confirm('Actualizar renuncia o abandono', '¿Desea actualizar este registro?', function(){
    	console.log("archivo: "+file + " fecha "+ fecha)
    	if(file!=""&&fecha!=""){
        	$.ajax({
                type: "POST",
                enctype: 'multipart/form-data',
                url: "updateR",
                data: data,
                processData: false,
                contentType: false,
                cache: false,
                timeout: 600000,
                success: function (data) {
                	$("#RegistrarA").prop("disabled", false);
                    alertify.notify('Actualizando...', 'custom', 1,
							function() {
                    			listarRegistrados();
                    			$( "#card-alert2" ).fadeTo(1500, 1, function() {
                    			    // Animation complete.
                    				window.setTimeout(function() {
                    					
                    				    $("#card-alert2").fadeTo(1000, 0).slideUp(800, function(){
                    				        $(this).hide(); 
                    				    });
                    				}, 2000);
                    			  });
							});
                },
                error: function (e) {
                    alertify
                    .errorAlert("Error al intentar guardar los datos. Consulte con el administrador del sistema.<br/>");                   
                }
            });
        }else{
        	if(nom_archivo==""){
        		alertify
                .errorAlert("Rellene todos los campos<br/>");
        	}else{
        		console.log("fecha: "+fecha +" idrenaban: "+idrenaban);
        		$.get("detalleR",{fecha:fecha,renabancito:idrenaban,opc:10},function(data){
        			 if(data==1){
        				 alertify.notify('Actualizando...', 'custom', 1,
     							function() {
                         			listarRegistrados();
                         			$( "#card-alert2" ).fadeTo(1500, 1, function(){
                         			    // Animation complete.
                         				window.setTimeout(function() {                        					
                         				    $("#card-alert2").fadeTo(1000, 0, function(){
                         				        $(this).hide(); 
                         				    });
                         				}, 2000);
                         			  });
     							});
        			 }
        		});
        	}        	
        }    	        	
    	}
    , function(){      	
    });
});

//ELIMINAR RENUNCIA Y ABANDONO
function eliminar(id,archivo) {
	swal({
		title: "¿Está seguro de eliminar",
		text: "la renuncia o abandono de este trabajador?",
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
        	$.get(gth_context_path + "/renaban/detalleR", {
				opc : 7,
				idr : id,
				archivo:archivo
			}, function(data){													 
				if (data==1){
								window.setTimeout(function() {
									swal("Eliminado","", "success");
									window.location.href = gth_context_path +"/renaban/listaRA";									
								}, 2000);
				}else{
					swal("Error", "al intentar eliminar los datos", "error");
				}
        	});			            				           							 					     	 			        	
        } else {
        	window.location.href = gth_context_path +"/renaban/listaRA";
        }
    	});
}

//DERIVAR RENUNCIAS Y ABANDONO
function aceptarRenaban(idrab,tipo){
	var idra= idrab;
	var tipo= tipo;
	if(tipo=="RENUNCIA"){
		//ALERT PARA RENUNCIA
		swal({
    		title: "¿Está seguro de autorizar",
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
            	$.get("detalleR",{opc:9,tipo:'R',idra:idra},function(data){													 
					if (data==1){
						swal("Se derivó", "la renuncia con éxito", "success");																
								window.setTimeout(function() {							
									window.location.href = gth_context_path +"/renaban/listaRA";
								}, 2000);
					}else{
						swal("Error", "Contactarse con el administrador", "error");
					}
	        	});			            				           							 					     	 			        	
            } else {
            	window.location.href = gth_context_path +"/renaban/listaRA";
            }
        	});
	}else{
		//ALERT PARA ABANDONO
		swal({
    		title: "¿Está seguro de autorizar",
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
            	$.get("detalleR",{opc:9,tipo:'A',idra:idra},function(data){													 
					if (data==1){
						  
						swal("Se derivó", "el abandono con éxito", "success");																
						window.setTimeout(function() {							
							window.location.href = gth_context_path +"/renaban/listaRA";
						}, 2000);
					}else{
						swal("Error", "Contactarse con el administrador", "error");
					}
	        	});			            				           							 					     	 			        	
            } else {
            	window.location.href = gth_context_path +"/renaban/listaRA";
            }
        	});
	}
}

//DISEÑO DE TABLA 1
function createTable() {
	var s = '<table id="data-table-row-grouping" class="display centered responsive" cellspacing="0" style="width:100%"> ';
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
	s += '<th>JUSTIFICACION</th>';
	s += '<th>RECHAZO</th>';
	s += '<th data-priority="2">Tipo</th>';
	s += '<th data-priority="1">Opciones</th>';
	s += '</tr>';
	s += '</thead>';
	s += '<tbody id="dataReq">';
	s += '</tbody>';
	s += '</table>';
	return s;
}

//DISEÑO DE LA TABLA 2
function createTable2() {
	var s = '<table id="data-table-row-grouping2" class="display centered" cellspacing="0" style="width:100%"> ';
	s += '<thead>';
	s += '<tr>';
	s += '<th>N</th>';
	s += '<th data-priority="2">Mes</th>';
	s += '<th data-priority="3">Apellidos y Nombres</th>';
	s += '<th data-priority="4">Puesto</th>';
	s += '<th data-priority="5">Area</th>';
	s += '<th data-priority="6">Departamento</th>';
	s += '<th>Tipo de Contrato</th>';
	s += '<th>Descripcion</th>';
	s += '<th>Fecha de registro</th>';
	s += '<th>DNI</th>';
	s += '<th>MFL</th>';	
	s += '<th data-priority="1">Tipo</th>';
	s += '</tr>';
	s += '</thead>';
	s += '<tbody id="dataReq2">';
	s += '</tbody>';
	s += '</table>';
	return s;
}
//LISTAR DERIVADOS
function listarDerivados() {
	$.getJSON(
			gth_context_path + "/renaban/AutorizarR",
			"opc=3",
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
						s += '</tr>';
					}
				} else {
					s += "";
				}
				var r = createTable2();
				$(".contP").empty();
				$(".contP").append(r);
				$("#dataReq2").empty();
				$("#dataReq2").append(s);
				$("#data-table-row-grouping2")
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
			});
}

//FECHA
function ParsearMes(mesint) {
	var mes;
	switch (mesint) {
	case 01:
		mes = "ENE";
		break;
	case 02:
		mes = "FEB";
		break;
	case 03:
		mes = "MAR";
		break;
	case 04:
		mes = "ABR";
		break;
	case 05:
		mes = "MAY";
		break;
	case 06:
		mes = "JUN";
		break;
	case 07:
		mes = "JUL";
		break;
	case 08:
		mes = "AGO";
		break;
	case 09:
		mes = "SET";
		break;
	case 10:
		mes = "OCT";
		break;
	case 11:
		mes = "NOV";
		break;
	case 12:
		mes = "DIC";
		break;
	}
	return mes;
}