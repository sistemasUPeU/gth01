$(document).ready(function() {
	
	listarRenuncia();
//	listarDocTra();
//	$('.dropify').dropify(function(event, element){
//        return confirm("¿Desea eliminar el archivo \"" + element.filename + "\" ?");
//    });
//    $('.dropify-fr').dropify({
//        messages: {
//            default: 'Glissez-déposez un fichier ici ou cliquez',
//            replace: 'Glissez-déposez un fichier ou cliquez pour remplacer',
//            remove:  'Supprimer',
//            error:   'Désolé, le fichier trop volumineux'
//        }
//    });
    // Used events
//    var drEvent = $('#pelon1').dropify();
//    drEvent.on('dropify.beforeClear', function(event, element){
//        return confirm("¿Desea eliminar el archivo \"" + element.filename + "\" ?");
//    });
//    drEvent.on('dropify.afterClear', function(event, element){
//        alert('Archivo eliminado');
//    });
});


//LISTAR TODOS LOS NOTIFICADOS 

$("#re").click(function(){
	listarRenuncia();
});
$("#ab").click(function(){
	listarAbandono();
});
function listarRenuncia() {
	$.getJSON(
			gth_context_path + "/renaban/ConRenaban",
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
										+ '</label>'
										+ '<label  class="idl" hidden>'
										+ lista[i].ID_LEGAJO
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
								s += '<td class="">'
										+ lista[i].PATERNO
										+ ' '
										+ lista[i].MATERNO
										+ ' '
										+ lista[i].NOMBRES
										+ '</td>';
								s += '<td class="expand" id="procesos" >'
									+'<span class="responsiveExpander">'
									+'<h1><span class="circulo"><i class="mdi-action-https"></i></span><h1>'
									+'</span>'
//										+ lista[i].NO_PROCESO
										+ '</td>';
								s += '<td><a class="green-text accent-3" href="#">'
										+ lista[i].NOM_DEPA
										+ '</a></td>';
								s += '<td>'
									+lista[i].DNI+
									 '</td>';
								s += '<td>'
									+lista[i].ESTADO+
									 '</td>';
								
								s +='<td>' +TIPO+'<label class= "tipon" hidden>'
								+ TIPO
								+ '</label></td>';															
								s += '</td>';
								s += '</tr>';
							}
						} else {
							s += "";
						}
						var r = createTable1("s", "d");
						$(".contP").empty();
						$(".contP").append(r);
						$("#dataReq1").empty();
						$("#dataReq1").append(s);						
						$("#data-table-row-grouping1")
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
						$(".legajo").click(
								function() {
									idc = $(this).parents("tr").find("td")
											.eq(0).find(".idc").text();
									idl = $(this).parents("tr").find("td")
									.eq(0).find(".idl").text();	
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
									console.log(idc);
									listarDocumentos(idc);
								});
					});
}

//DISEÑO DE LA TABLA 1
function createTable1(idDepartamento, idRol) {
	var Rol = idRol.toString();
	var Departamento = idDepartamento.toString();
	var s = '<table id="data-table-row-grouping1" class="display responsive" cellspacing="0" style="width:100%"> ';
	s += '<thead>';
	s += '<tr>';
	s += '<th>N</th>';
	s += '<th data-priority="1">Apellidos y Nombres</th>';
	s += '<th data-priority="2" id="procesos">Proceso</th>';
	s += '<th>Departamento</th>';
	s += '<th>DNI</th>';
	s += '<th data-priority="3">Estado</th>';
	s += '<th data-priority="1">Tipo</th>';
	s += '</tr>';
	s += '</thead>';
	s += '<tbody id="dataReq1">';
	s += '</tbody>';
	s += '</table>';
	return s;
}
var depa="";
var u = "";

//LISTAR ABANDONO
function listarAbandono() {
	$.getJSON(
			gth_context_path + "/renaban/ConRenaban",
			"opc=2",
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
										+ '</label>'
										+ '<label  class="idl" hidden>'
										+ lista[i].ID_LEGAJO
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
								s += '<td class="">'
										+ lista[i].PATERNO
										+ ' '
										+ lista[i].MATERNO
										+ ' '
										+ lista[i].NOMBRES
										+ '</td>';
								s += '<td class="expand" id="procesos" >'
//										+ lista[i].NO_PROCESO
										+ '</td>';
								s += '<td><a class="green-text accent-3" href="#">'
										+ lista[i].NOM_DEPA
										+ '</a></td>';
								s += '<td>'
									+lista[i].DNI+
									 '</td>';
								s += '<td>'
									+lista[i].ESTADO+
									 '</td>';
								
								s +='<td>' +TIPO+'<label class= "tipon" hidden>'
								+ TIPO
								+ '</label></td>';															
								s += '</td>';
								s += '</tr>';
							}
						} else {
							s += "";
						}
						var r = createTable("s", "d");
						$(".contA").empty();
						$(".contA").append(r);
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
						$(".legajo").click(
								function() {
									idc = $(this).parents("tr").find("td")
											.eq(0).find(".idc").text();
									idl = $(this).parents("tr").find("td")
									.eq(0).find(".idl").text();	
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
									console.log(idc);
									listarDocumentos(idc);
								});
					});
}

//DISEÑO DE LA TABLA ABANDONO
function createTable(idDepartamento, idRol) {
	var Rol = idRol.toString();
	var Departamento = idDepartamento.toString();
	var s = '<table id="data-table-row-grouping" class="display responsive" cellspacing="0" style="width:100%"> ';
	s += '<thead>';
	s += '<tr>';
	s += '<th>N</th>';
	s += '<th data-priority="4">Apellidos y Nombres</th>';
	s += '<th data-priority="2" id="procesos">Proceso</th>';
	s += '<th>Departamento</th>';
	s += '<th>DNI</th>';
	s += '<th data-priority="1">Estado</th>';
	s += '<th data-priority="1">Tipo</th>';
	s += '</tr>';
	s += '</thead>';
	s += '<tbody id="dataReq">';
	s += '</tbody>';
	s += '</table>';
	return s;
}
var depa="";
var u = "";
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