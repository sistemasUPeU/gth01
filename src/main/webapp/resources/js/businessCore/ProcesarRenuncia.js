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
								lista[i].FECHA_RENUNCIA);
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
						(Motivo === 1) ? p = "Trabajador Nuevo"
								: ((Motivo === 2) ? p = "Renovación"
										: p = "No Registrado");
						(MFL === 1) ? f = "Si"
								: f = "No";
						(plazo === 1) ? t = "Cumplió Plazo"
								: t = "No Cumplió";
						(plazo === 1) ? ct = "green accent-3"
								: ct = "red darken-1";
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
						s += '<td>'
								+lista[i].FECHA_RENUNCIA+
								 '</td>';
						s += '<td>'
							+lista[i].DNI+
							 '</td>';
						s += '<td>'
							+mfl+
							 '</td>';
						// s += '<td>' + p + '</td>';
						s += '<td>' + lista[i].ESTADO
								+ '</td>';
						s +='<td>' +TIPO+'</td>';
						s += '<td><button class="notificar waves-effect waves-light btn modal-trigger #00e676 green accent-3" >Detalle</button>';
						s += '</button>';
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
//				$("#data-table-row-grouping")
//						.DataTable();

				$(".notificar").click(
						function() {

							cantidad = $(this).parents(
									"tr").find("td")
									.eq(0)
									.find(".idc")
									.text();
							console.log(cantidad);

							DetalleRenuncia(cantidad);

							$("#otros").val(cantidad);					
						});
			});
}
function createTable(idDepartamento, idRol) {
	var Rol = idRol.toString();
	var Departamento = idDepartamento.toString();
	var s = '<table id="data-table-row-grouping" class="display" cellspacing="0" width="100%" style="position:relative;font-size:14px"';
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
	s += '<th>Fecha de renuncia</th>';
	s += '<th>DNI</th>';
	s += '<th>MFL</th>';
	s += '<th>Estado</th>';
	s += '<th>Tipo</th>';
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
	s += '<tbody id="dataReq">';
	s += '</tbody>';
	s += '</table>';
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
				console.log(objJson);
				if (lista.length > 0) {
					// alert("si hay datos causita c:");

					for (var i = 0; i < lista.length; i++) {
						var a = parseInt(i) + 1;
						var MFL = parseInt(lista[i].ES_MFL);
						var Motivo = parseInt(lista[i].LI_MOTIVO);
						var plazo = parseInt(lista[i].VAL_PLAZO);
						var fe_creacion = new Date(
								lista[i].FECHA_RENUNCIA);
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
						(Motivo === 1) ? p = "Trabajador Nuevo"
								: ((Motivo === 2) ? p = "Renovación"
										: p = "No Registrado");
						(MFL === 1) ? f = "Si"
								: f = "No";
						(plazo === 1) ? t = "Cumplió Plazo"
								: t = "No Cumplió";
						(plazo === 1) ? ct = "green accent-3"
								: ct = "red darken-1";
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
						s += '<td>'
								+lista[i].FECHA_RENUNCIA+
								 '</td>';
						s += '<td>'
							+lista[i].DNI+
							 '</td>';
						s += '<td>'
							+mfl+
							 '</td>';
						// s += '<td>' + p + '</td>';
						s += '<td>' + lista[i].ESTADO
								+ '</td>';
						s +='<td>' +TIPO+'</td>';
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
//				$("#data-table-row-grouping1")
//						.DataTable();

				$(".notificar").click(
						function() {

							cantidad = $(this).parents(
									"tr").find("td")
									.eq(0)
									.find(".idc")
									.text();
							console.log(cantidad);

							DetalleRenuncia(cantidad);

							$("#otros").val(cantidad);

						
						});
			

			});
}



function createTable1(idDepartamento, idRol) {
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
	s += '<th>Fecha de renuncia</th>';
	s += '<th>DNI</th>';
	s += '<th>MFL</th>';
	s += '<th>Estado</th>';
	s += '<th>Tipo</th>';
//	s += '<th>Opcion</th>';
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
	s += '<tbody id="dataReq1">';
	s += '</tbody>';
	s += '</table>';
	return s;
}

var depa="";

// DETALLE PARA PROCESAR RENUNCIA
function DetalleRenuncia(idc) {
	
//	$("#modal2").openModal();	
//	$.get("details",{},function(data){
//		alert(data);
//	});
	$.get("processDetails", {                          
	}, function(data, status) {
		//alert(data);		
//		 alert("BIEN JONAS");
//		 $("#contenido").html("");
		 $("#contenido").html(data);
		 $.get("ProcesarR",{opc:2,idc:idc},function(data,status){	
//			 alert(data);
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
//				$("#centro_costo").tex(detalle[0].CENTRO_COSTO);
				$("#tipo_contrato").text(detalle[0].TIPO_CONTRATO);
				if(detalle[0].ANTECEDENTES!=1){
					$("#antecedentes_policiales").text("Si");
				}else{	
					$("#antecedentes_policiales").text("No");
				}
//				var archi = detalle[0].ARCHIVO;
				if(detalle[0].CERTI_SALUD!=0){
					$("#certificado_salud").text("Si");
				}else{
					$("#certificado_salud").text("No");
				}
//				var img = document.getElementById("carta")
				$("#carta").text(detalle[0].ARCHIVO);
				$("#modalP").click(function(){
					var idr= $("#idr").val();
					//alert(idr);
					 alertify.confirm('Confirmar autorización', 'Esta seguro(a) de autorizar la renuncia de este trabajador?', function(){
						 $.get("ProcesarR",{opc:4,idr:idr},function(data){
//							 alert("BIEN Nicole");
//							 alert(idr);
							 window.location.href = gth_context_path +"/renaban/processR";					 
							
//			        		 alert(data);
			        	});
						 
				     	} , function(){ 
				     		
				        });
				});
				$("#modalR").click(function(){
					var id= $("#idr").val();
					var observaciones = $("#observaciones").val();					
					 alertify.confirm('Confirmar Rechazo de autorización', 'Esta seguro(a) de rechazar la renuncia de este trabajador?', function(){
						 $.get("ProcesarR",{opc:6,idr:id,observaciones:observaciones},function(data){
			        		 alert(data);
			        		 window.location.href = gth_context_path +"/renaban/processR";
			        	});
						 
				     	} , function(){ 
				        	
				        });
				});
		 });
		     
	
//		if (data.length == 0) {
//			// location.reload();
//			alert("nada de datos");
//		} else {
//			
//			$("#nomes").text(detalle[0].NOMBRES);
//			
//			$.get("/mostrardoc1",{
//				archi: archi
//			},function(data){
//				alert(data);
//			})
////			
//			
//		
//
//		}

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