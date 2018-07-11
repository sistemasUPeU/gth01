$(document).ready(function() {
	listarProcesados();
//	listarNotificados();
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

//LISTAR LOS PROCESADOS DE RENUNCIA Y ABANDONO
function listarProcesados() {
				$.getJSON(
					gth_context_path + "/renaban/Legajo",
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
								s += '<td><a class="legajo waves-effect waves-light btn #00e676 green accent-3">LEGAJO</a>';								
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
						$(".legajo").click(
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
									verdoc(idc);
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
function verdoc(idc) {
	var inst = $('[data-remodal-id=modal]').remodal();
	$.get(gth_context_path+"/renaban/listarxd", {
		idc : idc,
		opc : 3
	}, function(data, status) {
		inst.open();
//		var detalle = JSON.parse(data);
//		$("#correo").text(detalle[0].CORREO);
//		$("#idtr").text(detalle[0].ID_TRABAJADOR);
//		$("#idr").text(detalle[0].ID_RENABAN);
//		$("#nombre").text(detalle[0].NOMBRES + " " + detalle[0].PATERNO + " "+ detalle[0].MATERNO);
	});
}
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