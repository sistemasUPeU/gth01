$(document).ready(function() {
	
	listarNotificados();
//	listarDocumentos();
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
					gth_context_path + "/renaban/Legajo",
					"opc=1",
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
										+ lista[i].AP_PATERNO
										+ ' '
										+ lista[i].AP_MATERNO
										+ ' '
										+ lista[i].NO_TRABAJADOR
										+ '</td>';
								s += '<td>'
										+ lista[i].FE_DESDE
										+ '</td>';
								s += '<td><a class="green-text accent-3" href="#">'
										+ lista[i].FE_HASTA
										+ '</a></td>';
								s += '<td>'
									+lista[i].NU_DOC+
									 '</td>';
								s +='<td>' +TIPO+'<label class= "tipon" hidden>'
								+ TIPO
								+ '</label></td>';								
								s += '<td><a class="legajo  waves-effect waves-light btn #00e676 green accent-3" data-remodal-target="modal1">LEGAJO</a>';								
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
									console.log(idl);
									listarDocumentos(idl);
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
	s += '<th data-priority="4">Apellidos y Nombres</th>';
	s += '<th>Fecha de Inicio</th>';
	s += '<th>Fecha de Final</th>';
	s += '<th>DNI</th>';
	s += '<th data-priority="2">Tipo</th>';
	s += '<th data-priority="1">Opcion</th>';	
	s += '</tr>';
	s += '</thead>';
	s += '<tbody id="dataReq1">';
	s += '</tbody>';
	s += '</table>';
	return s;
}
var depa="";
var u = "";

function listarDocumentos(idl) {
	var inst = $('[data-remodal-id=modal1]').remodal();
	inst.open();
	$.get(
		gth_context_path + "/renaban/Legajo",{
		opc:2,idl:idl},
		function(objJson) {
			var s = "";
			var lista = JSON.parse( objJson);
			alert("TABLA DOCUMENTOS" + "   "+idl)
			console.log(objJson);
			if (lista.length > 0) {					
				for (var i = 0; i < lista.length; i++) {
					var a = parseInt(i) + 1;
					var MFL = parseInt(lista[i].ES_MFL);
					var Motivo = parseInt(lista[i].LI_MOTIVO);
					var plazo = parseInt(lista[i].VAL_PLAZO);
					var imagen="";
					var u = "";
					u += '<div class="container" style="width:80%"><img class="materialboxed responsive-img" '
						u += ''
						u += 'src="' + gth_context_path + '/resources/files/'
								+ lista[i].NO_ARCHIVO + '" '
						u += 'alt="sample"'
						u += 'data-caption="Esc para volver" ></div>'					
							var c = "";
						c="<embed src='" + gth_context_path + '/renaban/viewdoc?nombre=' + lista[i].NO_ARCHIVO+ "' style='width: 90%; height: 540px; ' type='application/pdf'>"				
						var tipod = lista[i].NO_ARCHIVO.split(".")[1];
						if (tipod=="pdf"){
							imagen=c;
						}else{
							imagen=u;
						}
						$('.materialboxed').materialbox();
					var fe_creacion = new Date(
							lista[i].FECHA_RENABAN);
					var mesInt = parseInt(fe_creacion
							.getMonth()) + 1;
					var mes = ParsearMes(mesInt);
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
						+ '<label  class="idte" hidden>'
						+ lista[i].ID_DETALLE_LEGAJO
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
					+ lista[i].AP_PATERNO + ' ' + lista[i].AP_MATERNO
					+ ' ' + lista[i].NO_TRABAJADOR + '</td>';
					s += '<td>'
							+ lista[i].ID_CONTRATO
							+ '</td>';
					
					s += '<td>' +
								imagen
							+ '</td>';
					
					s += '<td>' + lista[i].FECHA_REGISTRO
							+ '</td>';
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
		});
}
function createTable(idDepartamento, idRol) {
	var Rol = idRol.toString();
	var Departamento = idDepartamento.toString();
	var s = '<table id="data-table-row-grouping" class="bordered centered display" cellspacing="0" style="width:100%;" >';
	s += '<thead>';
	s += '<tr>';
	s += '<th>N</th>';     
	s += '<th>Apellidos y Nombres</th>';
	s += '<th>DESCRIPCION</th>';
	s += '<th data-priority="2">ARCHIVO</th>';
	s += '<th>FECHA DE REGISTRO</th>';
	s += '</tr>';
	s += '</thead>';
	s += '<tbody id="dataReq">';
	s += '</tbody>';
	s += '</table>';
	return s;
}
var depa="";
var u = "";
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
