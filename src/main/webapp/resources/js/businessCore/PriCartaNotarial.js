$(document)
		.ready(
				function() {
					
					var drEvent = $('.dropify').dropify();
					$('.stepper').activateStepper({
						
						showFeedbackLoader: true
					});
					
					var fecha = new Date();
					var dd = fecha.getDate();
					var mm = fecha.getMonth(); //January is 0!

					var yyyy = fecha.getFullYear();
					if (dd < 10) {
						dd = '0' + dd;
					}
					if (mm < 10) {
						mm = '0' + mm;
					}
					var today = dd + '/' + mm + '/' + yyyy;
					$('#fecha').pickadate({
						selectMonths : true, // Creates a dropdown to control month
						selectYears : 15, // Creates a dropdown of 15 years to control
						format : 'dd/mm/yyyy',
						onStart : function() {
							this.set('select', today);
						}
					});
				
					listarRegistrados();
					listarAutorizados();
					listarDerivados();
					
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
					$('#delivery').bind('click', function() {
				        if($(this).hasClass('active')) {
				        	var table = $('#data-table-row-grouping2').DataTable();				        	 
				        	$('#data-table-row-grouping2').css( 'display', 'table' );				        	 
				        	table.responsive.recalc();
				        }
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
						s += '<td><a class="notificar waves-effect waves-light btn #00e676 green accent-3">PRIMER ENVÍO</a>';
						
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
							
							idc = $(this).parents(
									"tr").find("td")
									.eq(0)
									.find(".idc")
									.text();
							console.log(idc);
							
//							$("#tipo").text(tipon);
//							$("#idr").text(idr);
//							DetalleAbandono(cantidad,tipon);
							location.href = gth_context_path+ '/renaban/firstNLetter?idc='+idc;

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
//						var diferencia =diferenciaHoras(lista[i].ID_RENABAN);
						var idrenaban = lista[i].ID_RENABAN;
						var diferencia='';
				
						    $.ajax({
						    	type: 'GET',
						        url: gth_context_path + "/renaban/primerEnvio?opc=9&idrenaban="+lista[i].ID_RENABAN,
						        //PERMITE RECONOCER LOS VALORES DEL SUCCESS AFUERA DEL AJAX
						        async: false,
						        success:function(data){
						        	if(parseInt(data)>144){
						    			diferencia='<a class="notificar1 waves-effect waves-light btn #00e676 green accent-3">SEGUNDO ENVÍO</a>';
						    		}else{
						    			diferencia='<a class="justificar waves-effect waves-light btn #00e676 red accent-3">JUSTIFICAR</a>';
						    		}
						        }
						    });
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
						s += '<td>'+diferencia+'</td>';
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
							"pageLength" : 5,
							"bPaginate" : true,
							"ordering": false,
							responsive:true,
							 columnDefs: [
							        { responsivePriority: 1, targets: 0 },
							        { responsivePriority: 2, targets: -1 }
							    ]
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
							
							idc = $(this).parents(
									"tr").find("td")
									.eq(0)
									.find(".idc")
									.text();
							console.log("Este idcontrato: "+idc);
							
//							$("#tipo").text(tipon);
//							$("#idr").text(idr);
							SegundaCarta(idc);
						});
				
				$(".justificar").click(
						function() {

							idr = $(this).parents(
							"tr").find("td")
							.find(".idrenaban")
							.eq(0)
							.text();
							console.log("esto es idrenaban"+idr);
							
							idc = $(this).parents(
									"tr").find("td")
									.eq(0)
									.find(".idc")
									.text();
							console.log("Este idcontrato: "+idc);
							
//							$("#tipo").text(tipon);
//							$("#idr").text(idr);
							ModalJustAbandono1(idr);
						});
				
			

			});
}

function createTable1(idDepartamento, idRol) {
	var Rol = idRol.toString();
	var Departamento = idDepartamento.toString();
	var s = '<table id="data-table-row-grouping1" class="display" cellspacing="0" style="width:100%"> ';
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
	s += '<tbody id="dataReq1">';
	s += '</tbody>';
	s += '</table>';
	return s;
}

function listarDerivados() {
	$.getJSON(
			gth_context_path + "/renaban/SegundoEnvio",
			"opc=4",
			function(objJson) {
				var s = "";
				var lista = objJson;
//				console.log(objJson);
				if (lista.length > 0) {
					// alert("si hay datos causita c:");

					for (var i = 0; i < lista.length; i++) {
//						var diferencia =diferenciaHoras(lista[i].ID_RENABAN);
						var idrenaban = lista[i].ID_RENABAN;
						var diferencia='';
				
						    $.ajax({
						    	type: 'GET',
						        url: gth_context_path + "/renaban/primerEnvio?opc=9&idrenaban="+lista[i].ID_RENABAN,
						        //PERMITE RECONOCER LOS VALORES DEL SUCCESS AFUERA DEL AJAX
						        async: false,
						        success:function(data){
						        	if(parseInt(data)>144){
						    			diferencia='<a class="notificar1 waves-effect waves-light btn #00e676 green accent-3">SEGUNDO ENVÍO</a>';
						    		}else{
						    			diferencia='<a class="justificar waves-effect waves-light btn #00e676 red accent-3">JUSTIFICAR</a>';
						    		}
						        }
						    });
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
						s += '<td>'+diferencia+'</td>';
						s += '</tr>';
					}

				} else {
					//alert("no hay datos");
					s += "";
				}

				var r = createTable2("", "");
				$(".contE").empty();
				$(".contE").append(r);
				$("#dataReq2").empty();
				$("#dataReq2").append(s);

				$("#data-table-row-grouping2")
						.DataTable({
							"pageLength" : 5,
							"bPaginate" : true,
							"ordering": false,
							responsive:true,
							 columnDefs: [
							        { responsivePriority: 1, targets: 0 },
							        { responsivePriority: 2, targets: -1 }
							    ]
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
							
							idc = $(this).parents(
									"tr").find("td")
									.eq(0)
									.find(".idc")
									.text();
							console.log("Este idcontrato: "+idc);
							
//							$("#tipo").text(tipon);
//							$("#idr").text(idr);
							SegundaCarta(idc);
						});
				
				$(".justificar").click(
						function() {

							idr = $(this).parents(
							"tr").find("td")
							.find(".idrenaban")
							.eq(0)
							.text();
							console.log("esto es idrenaban"+idr);
							
							idc = $(this).parents(
									"tr").find("td")
									.eq(0)
									.find(".idc")
									.text();
							console.log("Este idcontrato: "+idc);
							
//							$("#tipo").text(tipon);
//							$("#idr").text(idr);
							ModalJustAbandono1(idr);
						});
				
			

			});
}


function createTable2(idDepartamento, idRol) {
	var s = '<table id="data-table-row-grouping2" class="bordered centered display" cellspacing="0" style="width:100%;" >';
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
	s += '<tbody id="dataReq2">';
	s += '</tbody>';
	s += '</table>';
	return s;
}

var depa="";
var u = "";
//SEGUNDA CARTA NOTARIAL
function SegundaCarta(ids) {
	var inst = $('[data-remodal-id=modal]').remodal();
	inst.open();	
	$.get("SegundoEnvio",
			{opc : 2,ids : ids},function(data, status) {
											// alert(data);
				var detalle = JSON.parse(data);
											$("#pelon1").val("");
											$("#idrenaban")
													.val(detalle[0].ID_RENABAN);
											$("#mensaje").val("Señor(a) "+detalle[0].NOMBRES+" "+detalle[0].PATERNO+" "+detalle[0].MATERNO+ ", se le hace presente el siguiente documento para indicarle que usted ha sido despedido por abandono de trabajo. Usted tiene 6 días a partir del envío de el presente correo para justificarse en Recursos Humanos")
											$("#correo").focus().val(detalle[0].CORREO);
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
function anyThing() {
	setTimeout(function() {
		$('.stepper').nextStep();
	}, 1000);
}

function id(ida) {
	alert(ida);
}

function ModalJustAbandono1(idr){
	var inst = $('[data-remodal-id=modalon]').remodal();
	inst.open();	
	$("#idrenaban2").val(idr);
}

function JustificarAbandono1(){
	var idr = $("#idrenaban2").val();
	var observacion = $("#observaciones").val();
	swal({
		title: "¿Está seguro de justificar",
		text: "el abandono de este trabajador?",
		type: "warning",
		showCancelButton: true,
		confirmButtonColor: '#40D005',
		confirmButtonText: 'Confirmar',
		cancelButtonText: 'Cancelar',
		closeOnConfirm: false,
		closeOnCancel: true
	},
	function(isConfirm){
    if (isConfirm){
    	$.get(gth_context_path +"/renaban/primerEnvio",{opc:6,idr:idr,observacion:observacion},function(data){
    		if(data==1){
    			swal("Se ha justificado", "el abandono", "success");
				  window.setTimeout(function() {							
					  window.location.href = gth_context_path +"/renaban/PrimerEnvio";
					}, 2000);
    		}
    	});
    }});
    
	
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