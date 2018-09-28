$(document)
		.ready(
				function() {			
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
					listarRegistrados();
					listarAutorizados();					
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
					$("#ProcesarR").click(function(){						
						var idc=$("#idc").val();
						$.get("ProcesarR", {
							idc : idc,
							opc : 4
						}, function(data, status) {
							var detalle = JSON.parse(data);
							if(data==1){
							}else{
							}
							});
					});
					$("#RechazarR").click(function(){
					});
				});

//	LISTAR TODOS LOS TRABAJADORES REGISTRADOS QUE ESTAN EN PROCESO DE RENUNCIA O ABANDONO
function listarRegistrados() {
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
						s += '<td><a class="notificar waves-effect waves-light btn #00e676 green accent-3">Detalle</a>';
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
				$("#data-table-row-grouping tbody").on('click','.notificar',
						function() {
							cantidad = $(this).parents(
									"tr").find("td")
									.eq(0)
									.find(".idc")
									.text();													
							tipon = $(this).parents(
							"tr").find("td")
							.find(".tipon")
							.eq(0)
							.text();
							DetalleRenuncia(cantidad,tipon);
							$("#otros").val(cantidad);												
						});
			});
}

//DISEÑO DE LA TABLA 1
function createTable(idDepartamento, idRol) {
	var Rol = idRol.toString();
	var Departamento = idDepartamento.toString();
	var s = '<table id="data-table-row-grouping" class="display" cellspacing="0" style="width:100%"> ';
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

// LISTAR TODOS LOS TRABAJADORES AUTORIZADOS O ABANDONADOS
function listarAutorizados() {
	$.getJSON(
			gth_context_path + "/renaban/AutorizarR",
			"opc=5",
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
						var p = "";
						var f = "";
						var t = "";
						var ct = "";
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
						s +='<td class="tipon">' +TIPO+'</td>';
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
							responsive:true,
							"pageLength" : 10,
							"bPaginate" : true,
							"ordering": false
							    }
						);				
			});
}

//DISEÑO DE LA TABLA 2
function createTable1(idDepartamento, idRol) {
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
	s += '</tr>';
	s += '</thead>';
	s += '<tbody id="dataReq1">';
	s += '</tbody>';
	s += '</table>';
	return s;
}
var depa="";
var u = "";

// DETALLE PARA AUTORIZAR RENUNCIA O ABANDONO
function DetalleRenuncia(idc,tipo) {
	$.get(gth_context_path +"/renaban/details", {                          
	}, function(data, status) {
		 $("#contenido").html(data);
		 $.get(gth_context_path +"/renaban/AutorizarR",{opc:2,idc:idc},function(data,status){
			 
			 var detalle = JSON.parse(data);
			 $("#idr").val(detalle.ID_RENABAN);	
			 $("#nombres").text(detalle.NOMBRES);	
			 $("#paterno").text(detalle.PATERNO);
				$("#materno").text(detalle.MATERNO);
				$("#fecha_nac").text(detalle.FECHA_NAC);
				$("#fecha_inicio").text(detalle.FECHA_CONTRATO);
				$("#direccion").text(detalle.DOMICILIO);
				$("#departamento").text(detalle.NOM_DEPA);
				$("#area").text(detalle.NOM_AREA);
				$("#seccion").text(detalle.NOM_SECCION);
				$("#puesto").text(detalle.NOM_PUESTO);
				$("#tipo_contrato").text(detalle.TIPO_CONTRATO);
				if(tipon=="RENUNCIA"){
					$("#tipo_doc").text("Carta de Renuncia");
				}else{
					$("#tipo_doc").text("Evidencia de Abandono");
				}				
				if(detalle.ANTECEDENTES!=1){
					$("#ante_poli").text("Si");
				}else{	
					$("#ante_poli").text("No");
				}
				if(detalle.CERTI_SALUD!=0){
					$("#certi_salud").text("Si");
				}else{
					$("#certi_salud").text("No");
				}
				$("#carta").text(detalle.ARCHIVO);
				//AUTORIZAR RENUNCIA O ABANDONO
				$("#autorizarRen").click(function(){
					var idr= $("#idr").val();
					if(tipo=="RENUNCIA"){
						//ALERT DINAMICO PARA RENUNCIA
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
			            	$.get("AutorizarR",{opc:4,tipo:'R',idr:idr},function(data){													 
								if (data==1){
									  swal("Se Autorizó", "la renuncia con éxito", "success");
									  window.setTimeout(function() {							
										  window.location.href = gth_context_path +"/renaban/authorizationR";
										}, 2000);
								}else{
									swal("Error", "Contactarse con el administrador", "error");
								}
				        	});			            				           							 					     	 			        	
			            } else {
			            	window.location.href = gth_context_path +"/renaban/authorizationR";
			            }
			        	});
						//ALERT DINAMICO PARA ABANDONO
					}else{
						swal({
			        		title: "¿Está seguro de autorizar",
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
				            	$.get("AutorizarR",{opc:4,tipo:'A',idr:idr},function(data){														 
									if (data==1){
										  swal("Se Autorizo", "el abandono con éxito", "success");
										  window.setTimeout(function() {							
											  window.location.href = gth_context_path +"/renaban/authorizationR";
											}, 2000);
									}else{
										swal("Error", "Contactarse con el administrador", "error");
									}
					        	});			            				           							 					     	 			        	
				            } else {
				            	window.location.href = gth_context_path +"/renaban/authorizationR";
				            }
				        	});
					}					 
				});
				//RECHAZAR RENUNCIA O ABANDONO
				$("#RechazarRenuncia").click(function(){
					var id= $("#idr").val();
					var idra = $("#idr").val();
					var observaciones = $("#observaciones").val();
					var observacion = $("#observaciones").val();
					if(tipo=="RENUNCIA"){
						//ALERT DINAMICO PARA RECHAZAR RENUNCIA
						swal({
			        		title: "Está seguro de rechazar",
			        		text: "la renuncia de este trabajador!",
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
			            	$.get("AutorizarR",{opc:6,tipo:'R',idr:id,observaciones:observaciones},function(data){													 
								if (data==1){
									  swal("Se ha rechazado", "la renuncia", "success");
									  window.setTimeout(function() {							
										  window.location.href = gth_context_path +"/renaban/authorizationR";
										}, 2000);
								}else{
									swal("Error", "Contactarse con el administrador", "error");
								}
				        	});			            				           							 					     	 			        	
			            } else {
			            	window.location.href = gth_context_path +"/renaban/authorizationR";
			            }
			        	});
					}else{
						//ALERT DINAMICO PARA RECHAZAR ABANDONO
						swal({
			        		title: "Está seguro de rechazar",
			        		text: "el abandono de este trabajador!",
			        		type: "warning",
			        		showCancelButton: true,
			        		confirmButtonColor: '#40D005',
			        		confirmButtonText: 'si, rechaza!',
			        		cancelButtonText: 'No, cancela!',
			        		closeOnConfirm: false,
			        		closeOnCancel: false
			        	},
			        	function(isConfirm){
			            if (isConfirm){
			            	$.get("AutorizarR",{opc:7,tipo:'A',idr:idra,observacion:observacion},function(data){													 
								if (data==1){
									  swal("Se ha rechazado", "el abandono", "success");
									  window.setTimeout(function() {							
										  window.location.href = gth_context_path +"/renaban/authorizationR";
										}, 2000);
								}else{
									swal("Error", "Contactarse con el administrador", "error");
								}
				        	});			            				           							 					     	 			        	
			            } else {
			            	window.location.href = gth_context_path +"/renaban/authorizationR";
			            }
			        	});
					}					 
				});											
				u = "";
				u += '<div class="container" style="width:80%"><img class="materialboxed responsive-img" '
				u += ''
				u += 'src="' + gth_context_path + '/resources/files/'
						+ detalle.ARCHIVO + '" '
				u += 'alt="sample"'
				u += 'data-caption="Esc para volver" ></div>'					
					var c = "";
				c="<embed src='" + gth_context_path + '/renaban/viewdoc?nombre=' + detalle.ARCHIVO+ "' style='width: 90%; height: 540px; ' type='application/pdf'>"				
				var tipod = detalle.ARCHIVO.split(".")[1];
				if (tipod=="pdf"){
					$("#picture_del").html(c);
				}else{
					$("#picture_del").html(u);
				}
				$('.materialboxed').materialbox();
		 });
	});		
}

//FECHA
$('.datepicker').pickadate({
    selectMonths: true, // Creates a dropdown to control month
    selectYears: 15 // Creates a dropdown of 15 years to control year 
  });

window.picker = $('.datepicker').pickadate({
    selectMonths: true, // Creates a dropdown to control month
    selectYears: 100, // Creates a dropdown of 15 years to control year
    format: 'dd/mm/yyyy'    
});
$("#Date").val('SYSDATE');

function id(idc) {
}
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