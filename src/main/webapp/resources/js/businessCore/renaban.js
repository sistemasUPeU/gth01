$(document).ready(function(){
	listarRegistrados() 
});

//function listarRenaban(){
//	$.get("/liston","",function(){
//		
//	});
//}

function listarRegistrados() {
	$.getJSON(
			gth_context_path + "/renaban/detalleR",
			"opc=6",
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
						s += '<td>'
							+((lista[i].JUSTIFICACION==undefined) ? "-": lista[i].JUSTIFICACION)+
							 '</td>';
						s += '<td>'
							+((lista[i].RECHAZO==undefined) ? "-": lista[i].RECHAZO)+
							 '</td>';
						// s += '<td>' + p + '</td>';
//						s += '<td><a class="blue-text accent-4" href="#"><b>' + lista[i].ESTADO
//								+ '</b></a></td>';
						s +='<td >' +TIPO+ '<label class="tipon" hidden>'
						+ TIPO
						+ '</label></td>';
						s += '<td style="width:22%">'
							s += '	<div class ="row"><div class="col s6"><a onclick="crearModal(\''+lista[i].ID_RENABAN+'\') " data-remodal-target="modal" ' 
							s += '	class="btn-floating btn waves-effect waves-light accent-3 orange modal-trigger"'
							s += '	>'
							s += '	<i class="mdi-content-create"></i>'
							s+= '</a></div><div class="col s6"><a class="btn-floating waves-effect waves-light #ff9100 red accent-3" onclick="eliminar(\''+lista[i].ID_RENABAN+'\')">'
							s+= 	'<i class="mdi-action-delete"></i></a></div></div></td>';
						s += '</tr>';
					}

				} else {
					//alert("no hay datos");
					s += "";
				}

				var r = createTable();
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
								}
						);

				$("#data-table-row-grouping tbody").on('click','.notificar',
						function() {

							cantidad = $(this).parents(
									"tr").find("td")
									.eq(0)
									.find(".idc")
									.text();
							console.log(cantidad);
							
							
							tipon = $(this).parents(
							"tr").find("td")
							.find(".tipon")
							.eq(0)
							.text();
							console.log("esto es tipon"+tipon);

							DetalleRenuncia(cantidad,tipon);

							$("#otros").val(cantidad);	
							
							$("#tipo").val(tipon);
						});
			});
}

function crearModal(id){
	alert(id);
}

function eliminar(id){
//	alert(id);
	alertify.confirm('Confirmación de acción', '¿Está seguro(a) que desea eliminar este registro?', function(){
		$.get(gth_context_path + "/renaban/detalleR",{opc:7,idr:id},function(data){
			if(data==1){
				
				alertify.notify('Eliminando...', 'custom', 1, function(){listarRegistrados() });
			}else{
				
				alertify.error('Error al intentar eliminar los datos');
			}
		});
	}
   , function(){ });
}

function createTable() {
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
	s += '<th>JUSTIFICACION</th>';
	s += '<th>RECHAZO</th>';
	s += '<th data-priority="2">Tipo</th>';
	s += '<th data-priority="1">Opcion</th>';
	s += '</tr>';
	s += '</thead>';
	s += '<tbody id="dataReq">';
	s += '</tbody>';
	s += '</table>';
	return s;
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