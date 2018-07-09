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
					
					
					$("#ProcesarR").click(function(){						
						var idc=$("#idc").val();
						$.get("ProcesarR", {
							idc : idc,
							opc : 4
						}, function(data, status) {
//							 alert(data);
							var detalle = JSON.parse(data);
							
//							console.log(detalle);
							if(data==1){
//								alert("BUENA JONAS")
								
							}else{
//								alert("NADA JONAS");
							}
							});
					});
					
					$("#RechazarR").click(function(){
//						alert("rechaza");
					});
						
					

				});

function listarRegistrados() {
	$.getJSON(
			gth_context_path + "/renaban/primerEnvio",
			"opc=3",
			function(objJson) {
				var s = "";
				var lista = objJson;
//				console.log(lista[0].PATERNO);
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
//						s += '<td><a class="blue-text accent-4" href="#"><b>' + lista[i].ESTADO
//								+ '</b></a></td>';
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
								"ordering": false,
								
								}
						);
				
				$(".notificar").click(
						function() {
							
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
							
							cantidad = $(this).parents(
									"tr").find("td")
									.eq(0)
									.find(".idc")
									.text();
							console.log(cantidad);
							
							$("#tipo").text(tipon);
							$("#idr").text(idr);
							DetalleAbandono(cantidad,tipon);
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
// Detalle de Carta Notarial
function DetalleAbandono(ida) {
	$
			.get(
					"firstLetter",
					{},
					function(data, status) {
						// alert(data);
						// alert("BIEN JONAS");
						// $("#contenido").html("");
						$("#contenido").html(data);
						$
								.get(
										"primerEnvio",
										{
											opc : 2,
											ida : ida
										},
										function(data, status) {
											// alert(data);
											var detalle = JSON.parse(data);
											$("#idr")
													.val(detalle[0].ID_RENABAN);
											$("#nombres").text(
													detalle[0].NOMBRES);
											$("#paterno").text(
													detalle[0].PATERNO);
											$("#materno").text(
													detalle[0].MATERNO);
											$("#fecha_nac").text(
													detalle[0].FECHA_NAC);
											$("#fecha_inicio").text(
													detalle[0].FECHA_CONTRATO);
											$("#direccion").text(
													detalle[0].DOMICILIO);
											$("#departamento").text(
													detalle[0].NOM_DEPA);
											$("#area")
													.text(detalle[0].NOM_AREA);
											$("#seccion").text(
													detalle[0].NOM_SECCION);
											$("#puesto").text(
													detalle[0].NOM_PUESTO);
											// $("#centro_costo").tex(detalle[0].CENTRO_COSTO);
											$("#tipo_contrato").text(
													detalle[0].TIPO_CONTRATO);
											$("#correo")
													.text(detalle[0].CORREO);
											if (detalle[0].ANTECEDENTES != 1) {
												$("#antecedentes_policiales")
														.text("Si");
											} else {
												$("#antecedentes_policiales")
														.text("No");
											}
											// var archi = detalle[0].ARCHIVO;
											if (detalle[0].CERTI_SALUD != 0) {
												$("#certificado_salud").text(
														"Si");
											} else {
												$("#certificado_salud").text(
														"No");
											}
											// var img =
											// document.getElementById("carta")
											$("#carta")
													.text(detalle[0].ARCHIVO);
											$("#pricarta")
											$("#RechazarPrimeraCarta").click(function(){
												var idra= $("#idr").val();
												var observacion = $("#observaciones").val();	
												alert(tipon);
												 alertify.confirm('Confirmar Justificacion ', 'Esta seguro(a) de rechazar la renuncia de este trabajador?', function(){
													 $.get("primerEnvio",{opc:6,tipo:'A',idr:idra,observacion:observacion},function(data){
														 alert("BIEN Nicole");
//										        		 alert(data);
//										        		 alert(id);
										        		 alert(observacion);
										        		 window.location.href = gth_context_path +"/renaban/PrimerEnvio";
										        	});
													 
											     	} , function(){ 
											        	
											        });
											});
										});

						// if (data.length == 0) {
						// // location.reload();
						// alert("nada de datos");
						// } else {
						//			
						// $("#nomes").text(detalle[0].NOMBRES);
						//			
						// $.get("/mostrardoc1",{
						// archi: archi
						// },function(data){
						// alert(data);
						// })
						// //
						//			
						//		
						//
						// }

					});

}

//PRIMERA CARTA NOTARIAL
function enviarCorreo() {
//	alert(jfksdf);
	var msj = $("#mensaje1").text();
	var de = "pruebagth@gmail.com";
//	var para = $("#correo").text();
	var para = "estefannygarcia@upeu.edu.pe";
	var clave = "GTH123456";
//	var mensaje = $("#cartaNotarial").val();
	var mensaje = $("#cartaNotarial").val().replace(/C:\\fakepath\\/i, '');
	var msjs = msj + $("#fecha").val() + mensaje + ".";
	var asunto = "GTH";
	var idrenaban =$("#idr").text();
	var tipo =$("#tipo").text();
	alert(mensaje);
	 console.log(msjs);
	$.get(gth_context_path+"/renaban/primerEnvio", {
		de : de,
		clave : clave,
		para : para,
		mensaje : msjs,
		asunto : asunto,
//		foto:foto,
		opc : 7
	}, function(data, status) {
		console.log(data);
		// $("#modalnotificar").closeModal();
		if (data == 1) {
			alert("SE MANDO");
			notificar(idr,tipon);
//			listarAutorizados();
			// insertarLegajo();
		} else {
			alert(" NOOOOOOOOOOOOO SE MANDO");
		}

	});
}

function notificar(idrab,tipo1) {
	var idra = idrab;
	var tipo1 = tipo1;
	
//	alert("Entro a notificar");
//	alert(idra);
//	alert(tipo1);
	alertify.confirm('Confirmar Notificación', 'Está seguro(a) de notificar la renuncia de este trabajador?', function(){
		 $.get("primerEnvio",{opc:8,tipo1:'A',idra:idra},function(data){
			 window.location.href = gth_context_path +"/renaban/PrimerEnvio";					 
			
   		 alert(data);
   	});
		 
    	} , function(){ 
    		
       });
}

//LISTAR TODOS LOS TRABAJADORES PROCESADOS
function listarAutorizados() {
	$.getJSON(
			gth_context_path + "/renaban/primerEnvio",
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
								+ '</label><label class="idrenaban" hidden>'
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
						s += '<td><a class="notificar1 waves-effect waves-light btn #00e676 green accent-3">Segunda Carta Notarial</a>';
						
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
				
				$(".notificar1").click(
						function() {
							
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
							
							cantidad = $(this).parents(
									"tr").find("td")
									.eq(0)
									.find(".idc")
									.text();
							console.log(cantidad);
							
							$("#tipo").text(tipon);
							$("#idr").text(idr);
							SegundaCarta(cantidad,tipon);
						});
				
				jQuery('.dataTable').wrap('<div class="dataTables_scroll" />');
			

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
	s += '<th>Opcion</th>';
	s += '</tr>';
	s += '</thead>';
	s += '<tbody id="dataReq1">';
	s += '</tbody>';
	s += '</table>';
	return s;
}

var depa="";
var u = "";
//SEGUNDA CARTA NOTARIAL
function SegundaCarta(ids) {
	$
			.get(
					"segundoEnvio",
					{},
					function(data, status) {
						// alert(data);
						// alert("BIEN JONAS");
						// $("#contenido").html("");
						$("#contenido").html(data);
						$
								.get(
										"SegundoEnvio",
										{
											opc : 2,
											ids : ids
										},
										function(data, status) {
											// alert(data);
											var detalle = JSON.parse(data);
											$("#idr")
													.val(detalle[0].ID_RENABAN);
											$("#nombres").text(
													detalle[0].NOMBRES);
											$("#paterno").text(
													detalle[0].PATERNO);
											$("#materno").text(
													detalle[0].MATERNO);
											$("#fecha_nac").text(
													detalle[0].FECHA_NAC);
											$("#fecha_inicio").text(
													detalle[0].FECHA_CONTRATO);
											$("#direccion").text(
													detalle[0].DOMICILIO);
											$("#departamento").text(
													detalle[0].NOM_DEPA);
											$("#area")
													.text(detalle[0].NOM_AREA);
											$("#seccion").text(
													detalle[0].NOM_SECCION);
											$("#puesto").text(
													detalle[0].NOM_PUESTO);
											// $("#centro_costo").tex(detalle[0].CENTRO_COSTO);
											$("#tipo_contrato").text(
													detalle[0].TIPO_CONTRATO);
											$("#correo")
													.text(detalle[0].CORREO);
											if (detalle[0].ANTECEDENTES != 1) {
												$("#antecedentes_policiales")
														.text("Si");
											} else {
												$("#antecedentes_policiales")
														.text("No");
											}
											// var archi = detalle[0].ARCHIVO;
											if (detalle[0].CERTI_SALUD != 0) {
												$("#certificado_salud").text(
														"Si");
											} else {
												$("#certificado_salud").text(
														"No");
											}
											// var img =
											// document.getElementById("carta")
											$("#carta")
													.text(detalle[0].ARCHIVO);
											$("#pricarta")
											$("#RechazarCarta").click(function(){
												var idra= $("#idr").val();
												var observacion = $("#observaciones").val();
												alert(tipon);
												 alertify.confirm('Confirmar Justificacion ', 'Esta seguro(a) de rechazar la renuncia de este trabajador?', function(){
													 $.get("SegundoEnvio",{opc:3,tipo:'A',idr:idra,observacion:observacion},function(data){
														 alert("BIEN Nicole Segunda carta ");
//										        		 alert(data);
//										        		 alert(id);
										        		 alert(observacion);
										        		 window.location.href = gth_context_path +"/renaban/PrimerEnvio";
										        	});
													 
											     	} , function(){ 
											        	
											        });
											});
										});

						// if (data.length == 0) {
						// // location.reload();
						// alert("nada de datos");
						// } else {
						//			
						// $("#nomes").text(detalle[0].NOMBRES);
						//			
						// $.get("/mostrardoc1",{
						// archi: archi
						// },function(data){
						// alert(data);
						// })
						// //
						//			
						//		
						//
						// }

					});

}

//SEGUNDA CARTA NOTARIAL
function enviarCorreo1() {
//	alert(jfksdf);
	var msj = $("#mensaje1").text();
	var de = "pruebagth@gmail.com";
//	var para = $("#correo").text();
	var para = "estefannygarcia@upeu.edu.pe";
	var clave = "GTH123456";
//	var mensaje = $("#cartaNotarial").val();
	var mensaje = $("#cartaNotarial").val().replace(/C:\\fakepath\\/i, '');
	var msjs = msj + $("#fecha").val() + mensaje + ".";
	var asunto = "GTH";
	var idrenaban =$("#idr").text();
	var tipo =$("#tipo").text();
	alert(mensaje);
	 console.log(msjs);
	$.get(gth_context_path+"/renaban/primerEnvio", {
		de : de,
		clave : clave,
		para : para,
		mensaje : msjs,
		asunto : asunto,
//		foto:foto,
		opc : 7
	}, function(data, status) {
		console.log(data);
		// $("#modalnotificar").closeModal();
		if (data == 1) {
			alert("SE MANDO");
			notificar1(idr,tipon);
//			listarAutorizados();
			// insertarLegajo();
		} else {
			alert(" NOOOOOOOOOOOOO SE MANDO");
		}

	});
}
//SEGUNDA CARTA NOTARIAL
function notificar1(idrab,tipo2) {
	var idra = idrab;
	var tipo2 = tipo2;
	alert("Entro a a la segunda carta notarial");
	alert(idra);
	alert(tipo2);
	alertify.confirm('Confirmar Notificación', 'Está seguro(a) de enviar la segunda carta notarial al trabajador?', function(){
		 $.get("SegundoEnvio",{opc:1,tipo2:'A',idra:idra},function(data){
			 window.location.href = gth_context_path +"/renaban/PrimerEnvio";					 
			
   		 alert(data);
   	});
		 
    	} , function(){ 
    		
       });
}

$('.datepicker').pickadate({
	selectMonths : true, // Creates a dropdown to control month
	selectYears : 15
// Creates a dropdown of 15 years to control year
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

function ParsearMes(mesint) {
	var mes;
	console.log(mesint);
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