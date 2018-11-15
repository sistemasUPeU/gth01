var obj_observados = new Array();

var fecha_ini = new Array();
var fecha_fin = new Array();
var highlight = new Array();
var tooltip = new Array();
var calendarContainer = new Array();

var datePicker;

function calendarProperties(id_vac) {
	$('#alldatepicker').empty();
	var date_number;
	$
			.get(
					"programa_vacaciones/get?id_vac=" + id_vac,
					function(obj) {
						for (var i = 0; i < obj.length; i++) {
							var year_i, month_i, day_i, year_f, month_f, day_f;
							year_i = obj[i].FECHA_INICIO.split("/")[0];
							month_i = obj[i].FECHA_INICIO.split("/")[1] - 1;
							day_i = obj[i].FECHA_INICIO.split("/")[2];
							year_f = obj[i].FECHA_FIN.split("/")[0];
							month_f = obj[i].FECHA_FIN.split("/")[1] - 1;
							day_f = obj[i].FECHA_FIN.split("/")[2];

							if (obj_observados[i].estado == "1") {
								highlight[i] = {
									start : new Date(obj[i].FECHA_INICIO),
									end : new Date(obj[i].FECHA_FIN),
									backgroundColor : '#e27373',
									color : '#fff',
									legend : 'Obs.'
								};
							} else {
								highlight[i] = {
									start : new Date(year_i, month_i, day_i),
									end : new Date(year_f, month_f, day_f),
									backgroundColor : '#00e676',
									color : '#fff',
									legend : 'Prog.'
								};
							}
							var afa1 = 2 * i;
							tooltip[afa1] = {
								date : new Date(year_i, month_i, day_i),
								text : 'Inicio de programación'
							}
							afa1 = afa1 + 1;
							tooltip[afa1] = {
								date : new Date(year_f, month_f, day_f),
								text : 'Fin de programación'
							};

							fecha_ini[i] = parseInt(year_i
									+ (month_i + 1 > 9 ? '' : '0')
									+ (parseInt(month_i) + 1) + day_i);
							fecha_fin[i] = parseInt(year_f
									+ (month_f + 1 > 9 ? '' : '0')
									+ (parseInt(month_f) + 1) + day_f);

							obj_observados[i].fecha_ini = obj[i].FECHA_INICIO;
							obj_observados[i].fecha_fin = obj[i].FECHA_FIN;
							obj_observados[i].id_det_vac = obj[i].ID_DET_VACACIONES;
							obj_observados[i].nombre = obj[i].AP_PATERNO + " "
									+ obj[i].AP_MATERNO + ", "
									+ obj[i].NO_TRABAJADOR;
							obj_observados[i].idtr = obj[i].ID_TRABAJADOR;

							if (i == 0) {
								months[cm].month = month_i;
								months[cm].year = year_i;
								cm++;
								if (obj[i].FECHA_FIN.split("/")[1] != obj[i].FECHA_INICIO
										.split("/")[1]) {
									months[cm].month = month_f;
									months[cm].year = year_f;
									cm++;
								}
							} else {
								if (obj[i].FECHA_INICIO.split("/")[1] != obj[i - 1].FECHA_FIN
										.split("/")[1]) {
									months[cm].month = month_i;
									months[cm].year = year_i;
									cm++;
								} else if (obj[i].FECHA_FIN.split("/")[1] != obj[i].FECHA_INICIO
										.split("/")[1]) {
									months[cm].month = month_f;
									months[cm].year = year_f;
									cm++;
								}
							}
						}
						createMonthCalendar(id_vac, obj);

					});
	highlight = [];
	tooltip = [];
	index_obs = 0;
	createMonth();
}

var months = new Array();
var cm = 0;
function createMonth() {
	for (var i = 0; i < 17; i++) {
		months[i] = {
			"month" : "",
			"year" : ""
		};
	}
	cm = 0;
	return months, cm;
}

function createMonthCalendar(id_vac, obj) {
	for (var i = 0; i < months.length - 1; i++) {
		if (months[i].year != "") {
			$("#alldatepicker").append(createDatePickerContainer(i));
			$("#datePicker" + i).append(calendarPropertiesContainer(i));
			datePicker = new Datepickk({

				container : document.querySelector('#calendarContainer' + i),
				daynames : false,
				inline : true,
				onSelect : function(checked) {
					var state = (checked) ? 'selected' : 'unselected';
					var date_slctd = new Date(this);
					var day_slctd = date_slctd.getDate().toString();
					var month_slctd = (date_slctd.getMonth() + 1).toString();
					var year_slctd = date_slctd.getFullYear().toString();
					date_number = parseInt(year_slctd
							+ (month_slctd > 9 ? '' : '0') + month_slctd
							+ (day_slctd > 9 ? '' : '0') + day_slctd);
					conseguirIntervalo(date_number, obj);
				},
				minDate : new Date(months[i].year, months[i].month, 1),
				maxDate : new Date(months[i].year, months[i].month + 1, 0)
			});

			for (var k = 0; k < highlight.length; k++) {
				datePicker.highlight = [ highlight[k] ];
			}
			for (var k = 0; k < tooltip.length; k++) {
				datePicker.tooltips = [ tooltip[k] ];
			}
		}
	}
}

function createDatePickerContainer(num) {
	s = '<div id="datePicker' + num
			+ '" class="col s12 m6 l3 xl3" style="height: 390px;"></div>';
	return s;
}

function calendarPropertiesContainer(num) {
	s = '<div id="calendarContainer' + num + '" class="col s12"></div>';
	return s;
}

var year_intervalo = new Array();
var month_intervalo = new Array();
var day_intervalo = new Array();

function cleanObsData() {
	for (var i = 0; i < 17; i++) {
		obj_observados[i] = {
			"fecha_ini" : "",
			"fecha_fin" : "",
			"estado" : "0",
			"mensaje" : "",
			"nombre" : "",
			"id_det_vac" : "",
			"idtr" : ""
		};
	}
}

function conseguirIntervalo(selected_date, obj) {
	for (var p = 0; p < fecha_fin.length; p++) {

		if (fecha_ini[p] <= selected_date && selected_date <= fecha_fin[p]) {
			year_intervalo[0] = fecha_ini[p].toString().substring(0, 4);
			year_intervalo[1] = fecha_fin[p].toString().substring(0, 4);
			month_intervalo[0] = fecha_ini[p].toString().substring(4, 6);
			month_intervalo[1] = fecha_fin[p].toString().substring(4, 6);
			day_intervalo[0] = fecha_ini[p].toString().substring(6, 8);
			day_intervalo[1] = fecha_fin[p].toString().substring(6, 8);
		}
	}

	var date_ini = year_intervalo[0] + "/" + month_intervalo[0] + "/"
			+ day_intervalo[0];
	var date_fin = year_intervalo[1] + "/" + month_intervalo[1] + "/"
			+ day_intervalo[1];
	var date_ini_num = parseInt(year_intervalo[0] + month_intervalo[0]
			+ day_intervalo[0]);
	var date_fin_num = parseInt(year_intervalo[1] + month_intervalo[1]
			+ day_intervalo[1]);

	for (var i = 0; i < obj.length; i++) {
		if (obj_observados[i].fecha_ini == date_ini
				&& date_ini_num <= selected_date
				&& date_fin_num >= selected_date) {
			parent.preba(obj_observados[i].nombre,
					obj_observados[i].id_det_vac, obj[i].ID_VACACIONES);
		}
	}
}

function multiFunction(idvac) {
	cleanObsData();
	calendarProperties(idvac);
	fecha_fin = [];
	fecha_ini = [];
}