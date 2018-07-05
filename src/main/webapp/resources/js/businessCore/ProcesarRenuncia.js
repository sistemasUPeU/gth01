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

					
					// $('.modal-trigger').leanModal();
					// alert();

					listarAutorizados();
					listarProcesados();
					
					$("#ProcesarR").click(function(){						
						var idc=$("#idc").val();
						$.get("ProcesarR", {
							idc : idc,
							opc : 4
						}, function(data, status) {
							 //alert(data);
							var detalle = JSON.parse(data);
							
							console.log(detalle);
							if(data==1){
								alert("BUENA JONAS")
							}else{
								alert("NADA JONAS");
							}
							
							});						
					});					
					$("#RechazarR").click(function(){
						//alert("rechaza");
						
					});
				});


//	LISTAR TODOS LOS TRABAJADORES AUTORIZADOS
function listarAutorizados() {
		$.getJSON(
				gth_context_path + "/renaban/ProcesarR",
				"opc=3",
				function(objJson) {
					var s = "";
					var lista = objJson;
//					console.log(objJson);
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
									+ '</label></td>';
							s += '<td>'
									+ mes;
									+ '</td>';
							s += '<td class="">'
//							var p = "";
//							var f = "";
//							var t = "";
//							var ct = "";
//							(Motivo === 1) ? p = "Trabajador Nuevo"
//									: ((Motivo === 2) ? p = "Renovación"
//											: p = "No Registrado");
//							(MFL === 1) ? f = "Si"
//									: f = "No";
//							(plazo === 1) ? t = "Cumplió Plazo"
//									: t = "No Cumplió";
//							(plazo === 1) ? ct = "green accent-3"
//									: ct = "red darken-1";
							

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
							// s += '<td>' + p + '</td>';
//							s += '<td><a class="blue-text accent-4" href="#"><b>' + lista[i].ESTADO
//									+ '</b></a></td>';
							s +='<td >' +TIPO+ '<label class="tipon" hidden>'
							+ TIPO
							+ '</label></td>';
							s += '<td><a class="notificar waves-effect waves-light btn #00e676 green accent-3">Detalle</a>';
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
									"ordering": false
									}
							);

					$("#data-table-row-grouping tbody").on('click','.notificar',
							function() {

								cantidad = $(this).parents(
										"tr").find("td")
										.eq(0)
										.find(".idc")
										.text();
//								console.log(cantidad);
								
								
								tipon = $(this).parents(
								"tr").find("td")
								.find(".tipon")
								.eq(0)
								.text();
//								console.log("esto es tipon"+tipon);

								DetalleRenuncia(cantidad,tipon);

								$("#otros").val(cantidad);	
						
							
							});
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

// LISTAR TODOS LOS TRABAJADORES PROCESADOS
function listarProcesados() {
	$.getJSON(
			gth_context_path + "/renaban/ProcesarR",
			"opc=5",
			function(objJson) {
				var s = "";
				var lista = objJson;
//				console.log(objJson);
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
						// s += '<td>' + p + '</td>';
//						s += '<td>' + lista[i].ESTADO
//								+ '</td>';
						s +='<td class="tipon">' +TIPO+'</td>';
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
				
				jQuery('.dataTable').wrap('<div class="dataTables_scroll" />');

//				$("#data-table-row-grouping1")
//						.DataTable();


//				$(".dataTables_scrollHeadInner").css({"width":"1358px;","padding-right": "0px;"});
//				
//				$(".table ").css({"width":"1358px","margin-left": "0px;"});
//				$(".notificar").click(
//						function() {
//
//							cantidad = $(this).parents(
//									"tr").find("td")
//									.eq(0)
//									.find(".idc")
//									.text();
//							console.log(cantidad);
//							
//							
//
//							DetalleRenuncia(cantidad,tipon);
//
//							$("#otros").val(cantidad);
//
//						
//						});
			

			});
}



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



// DETALLE PARA PROCESAR RENUNCIA
function DetalleRenuncia(idc,tipo) {
	$.get("processDetails", {                          
	}, function(data, status) {
		 $("#contenido").html(data);
		 $.get("ProcesarR",{opc:2,idc:idc},function(data,status){	
			 var detalle = JSON.parse(data);
			 $("#idr").val(detalle[0].ID_RENABAN);	
			 $("#nombres").text(detalle[0].NOMBRES);	
			 $("#paterno").text(detalle[0].PATERNO);
				$("#materno").text(detalle[0].MATERNO);
				$("#fecha_nac").text(detalle[0].FECHA_NAC);
				$("#fecha_inicio").text(detalle[0].FECHA_CONTRATO);
				$("#direccion").text(detalle[0].DOMICILIO);
				$("#departamento").text(detalle[0].NOM_DEPA);
				$("#area").text(detalle[0].NOM_AREA);
				$("#seccion").text(detalle[0].NOM_SECCION);
				$("#puesto").text(detalle[0].NOM_PUESTO);
				$("#tipo_contrato").text(detalle[0].TIPO_CONTRATO);
				if(tipon=="RENUNCIA"){
					$("#tipo_doc").text("Carta de Renuncia");
				}else{
					$("#tipo_doc").text("Evidencia de Abandono");
				}
				
				if(detalle[0].ANTECEDENTES!=1){
					$("#ante_poli").text("Si");
				}else{	
					$("#ante_poli").text("No");
				}
				if(detalle[0].CERTI_SALUD!=0){
					$("#certi_salud").text("Si");
				}else{
					$("#certi_salud").text("No");
				}
				$("#carta").text(detalle[0].ARCHIVO);

				$("#procesarRen").click(function(){
					var idr= $("#idr").val();
					if(tipo=="RENUNCIA"){
						alertify.confirm('Confirmar autorización', 'Está seguro(a) de procesar la renuncia de este trabajador?', function(){
							 $.get("ProcesarR",{opc:4,tipo:'R',idr:idr},function(data){
								 window.location.href = gth_context_path +"/renaban/processR";					 
				        	});
							 
					     	} , function(){ 
					     		
					        });
					}else{
						alertify.confirm('Confirmar autorización', 'Está seguro(a) de procesar el abandono de este trabajador?', function(){
							 $.get("ProcesarR",{opc:4,tipo:'A',idr:idr},function(data){
								 window.location.href = gth_context_path +"/renaban/processR";					 								
//				        		 alert(data);
//				        		 alert("Abandono")
				        	});
							 
					     	} , function(){ 
					     		
					        });
					}
					 
				});

//				$("#RechazarRenuncia").click(function(){
//					var id= $("#idr").val();
//					var idra = $("#idr").val();
//					var observaciones = $("#observaciones").val();
//					var observacion = $("#observaciones").val();
//					 alert(tipon);
//					if(tipo=="RENUNCIA"){
//						alertify.confirm('Confirmar Rechazo de autorización', 'Esta seguro(a) de rechazar la renuncia o abandono de este trabajador?', function(){
//							 $.get("ProcesarR",{opc:6,tipo:'R',idr:id,observaciones:observaciones},function(data){
//
//								 alert("Rechazo Renuncia");			        	
//
////								 alert("Rechazo Renuncia");
////								 alert("BIEN Nicole");
////				        		 alert(data);
////				        		 alert(id);
////				        		 alert(observaciones);				        	
//
//				        		 window.location.href = gth_context_path +"/renaban/processR";
//				        	});
//							 
//					     	} , function(){ 
//					        	
//					        });
//					}else{
//						alertify.confirm('Confirmar Rechazo de autorización', 'Esta seguro(a) de rechazar la renuncia o abandono de este trabajador?', function(){
//							 $.get("ProcesarR",{opc:7,tipo:'A',idr:idra,observacion:observacion},function(data){
////								 alert("Rechazo Abandono")
////								 alert("BIEN Nicole");
////				        		 alert(data);
//								
////				        		 alert(idra);
////				        		 alert(observacion);
//
//				        		 window.location.href = gth_context_path +"/renaban/processR";
//							   	});
//							 
//						     	} , function(){ 
//						        	
//						        });
//						}
//						 
//					});
				var idr = detalle[0].ID_RENABAN;			
				$.get("ProcesarR",{idr:idr,opc:8,tipo:tipon},function(data){

						if(data==1){
									
							$("#RECHAZAR").css("display","none");
									
									
									
						}else{
							$("#RECHAZAR").css("display","block");
							}
					});	
						
				u = "";
				u += '<div class="container" style="width:80%"><img class="materialboxed responsive-img" '
				u += ''
				u += 'src="' + gth_context_path + '/resources/files/'
						+ detalle[0].ARCHIVO + '" '
				u += 'alt="sample"'
				u += 'data-caption="Esc para volver" ></div>'

					
					var c = "";
				c="<embed src='" + gth_context_path + '/renaban/viewdoc?nombre=' + detalle[0].ARCHIVO+ "' style='width: 90%; height: 540px; ' type='application/pdf'>"

				console.log("	archivo "+detalle[0].ARCHIVO);
				var tipod = detalle[0].ARCHIVO.split(".")[1];
				if (tipod=="pdf"){
					$("#picture_del").html(c);
				}else{
					$("#picture_del").html(u);
				}
				$('.materialboxed').materialbox();			
		});
	});
}



window.picker = $('.datepicker').pickadate({
    selectMonths: true, // Creates a dropdown to control month
    selectYears: 100, // Creates a dropdown of 15 years to control year
    format: 'dd/mm/yyyy'    
});
$("#Date").val('SYSDATE');

function id(idc) {
	alert(idc);
}

function ParsearMes(mesint) {
	var mes;
	console.log(mesint);
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
