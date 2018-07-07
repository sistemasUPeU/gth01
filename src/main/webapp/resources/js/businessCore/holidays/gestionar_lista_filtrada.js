$(document).ready(function() {
	listarTrabajadorFiltrado();
	$("#cargando").hide();
})

//CONFIRMA LISTA Y MUESTRA MENSAJE SEGÚN EL ESTADO QUE SE DEVUELVE DEL BACK-END
$("#confirmar_lista").click(function() {
	$("#nocargando").hide();
	$("#cargando").show();
	$.get("filtrar/confirmarListaFiltrada",function(data, status) {
		if (data == 1) {
			var $toastContent = $('<span>Lista filtrada correctamente</span>');
			Materialize.toast($toastContent, 10000);
			listarTrabajadorFiltrado();
		}
		if (data == 2) {
			var $toastContent = $('<span>Lista de trabajadores filtrados nulo</span>');
			Materialize.toast($toastContent, 10000);
		}
		if (data == 3) {
			var $toastContent = $('<span>No existe consolidado activo</span>');
			Materialize.toast($toastContent, 10000);
		}
		if (data == 0) {
			var $toastContent = $('<span>Error interno</span>');
			Materialize.toast($toastContent, 10000);
		}
		$("#nocargando").show();
		$("#cargando").hide();
	});
});

//LISTAR Y LLENAR A LOS TRABAJADORES CON 12 MESES EN LAS TABLAS + CONDICION DE TRABAJO 
function listarTrabajadorFiltrado() {
	$.get('filtrar/readallTrabajadorFiltrado', function(obj) {
		var s = '';
		var emp = obj[0];
		for (var i = 0; i < obj.length; i++) {
			if (obj[i].LI_CONDICION == 1) {
				con = "CONTRATADO";
			}
			if (obj[i].LI_CONDICION == 2) {
				con = "EMPLEADO";
			}
			if (obj[i].LI_CONDICION == 3) {
				con = "MISIONERO";
			}
			if (obj[i].LI_CONDICION == 4) {
				con = "PRACTICAS PRE-PROFESIONALES";
			}
			if (obj[i].LI_CONDICION == 5) {
				con = "PRACTICAS PROFESIONALES";
			}
			if (obj[i].LI_CONDICION == 6) {
				con = "CONVENIO LABORAL JUVENIL";
			}
			if (obj[i].LI_CONDICION == 7) {
				con = "CONTRATO";
			}
			s += '<tr>';
			s += '<td>' + obj[i].AP_PATERNO + ' ' + obj[i].AP_MATERNO + ' ' + obj[i].NO_TRABAJADOR + '</td>';
			s += '<td>' + obj[i].NO_DEP + '</td>';
			s += '<td>' + obj[i].NO_DEP + '</td>';
			s += '<td>' + obj[i].NO_AREA + '</td>';
			s += '<td>' + obj[i].NO_SECCION + '</td>';
			s += '<td>' + con + '</td>';
			s += '</tr>';
		}
		$("#table_contenido").empty();
		$("#table_contenido").append(createTable());
		$("#data").empty();
		$("#data").append(s);
		$('#data-table-row-grouping').dataTable();
	});
};

//CREAR TABLA PARA LLENAR A LOS TRABAJADORES QUE CUMPLEN 12 MESES
function createTable() {
	var s = '<table id="data-table-row-grouping" class="display" cellspacing="0" width="100%">';
	s += '<thead>';
	s += '<tr>';
	s += '<th>Apellidos y Nombres</th>';
	s += '<th>\u00C1rea</th>';
	s += '<th>Departamento</th>';
	s += '<th>\u00C1rea</th>';
	s += '<th>Secci\u00F3n</th>';
	s += '<th>Condici\u00F3n</th>';
	s += ' </tr>';
	s += '</thead>';
	s += '<tbody id="data"></tbody>';
	s += '</table>';
	return s;
};