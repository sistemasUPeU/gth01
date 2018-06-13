$(document).ready(function() {

	configurar();
	
	});



	
	function configurar() {
// 		var id = $("#trab").val();


		var url ="/gth/configuraciones/validar";
// 		var data ="&id=" + id ;

		
		$.getJSON(url, null, function(res, status) {


				console.log("devuelve controller: "+res);

				switch (res) {
				case 0:

					Materialize.toast('Debe configurar las proximas vacaciones!', 3000, 'rounded', function(){
//						$.get(gth_context_path+'/configuraciones/', null, function(response) {
//							console.log(response);
//							$("#desktop").html(response);
//							$("#programa_vacaciones input").attr('disabled','disabled');
//							$("#solicitud_vacaciones input").attr('disabled','disabled');
//							$("#modificar input").attr('disabled','disabled');
//						});
					});

					break;
				case 1:

					Materialize.toast('Ya configuro las vacaciones!', 3000, 'rounded', function(){
//						$.get(gth_context_path+'/configuraciones/', null, function(response) {
//							console.log(response);
//							$("#desktop").html(response);
//						});
					});
					
					
					break;
				
				}
		});
	};
	
	
	$("#consolidado").click(function(){
		var alerta = $("#date_cons").val();
		

	alerta = parseDate(alerta);
	var cn = new jsConnector();

	cn.post('configuraciones/insertarConsolidado?fecha=' + alerta, null, function(
			response) {
		console.log(response)
		if (response == 1) {
			console.log("jquery:" + response);
			Materialize.toast('Consolidado creado correctamente, programe el plazo para el envio del programa!', 3000,
					'rounded');
			
			$("#programa_vacaciones input").attr('disabled',false);
			$("#date_cons").attr('disabled',true);
			
			

//			$("#idvac").val($.trim(response));
//			console.log($("#idvac").val());
//			$('#confirmar').addClass('disabled');
		} else {
			Materialize.toast('UPS!!, no se ha creado el consolidado!',
					3000, 'rounded', function(){
				$("#date_cons").val("");
			});
		}
	});
		
	});
	
	
	$("#savePrograma").click(function(){
		var alerta = $("#date_programa").val();
		

	alerta = parseDate(alerta);
	var cn = new jsConnector();

	cn.post('configuraciones/insertarPrograma?fecha=' + alerta, null, function(
			response) {
		console.log(response)
		if (response == 1) {
			console.log("jquery:" + response);
			Materialize.toast('Plazo de entrega del programa de vacaciones configurado correctamente, prosiga', 3000,
					'rounded');
			
			$("#solicitud_vacaciones input").attr('disabled',false);
			$("#programa_vacaciones input").attr('disabled',true);
			
		} else {
			Materialize.toast('UPS!!, no se ha configurado el programa de vacaciones!',
					3000, 'rounded', function(){
				 $("#date_programa").val("");
			});
		}
	});
		
	});
	
	
	$("#saveRequest").click(function(){
		var alerta = $("#date_solicitud").val();
		

	alerta = parseDate(alerta);
	var cn = new jsConnector();

	cn.post('configuraciones/insertarSolicitud?fecha=' + alerta, null, function(
			response) {
		console.log(response)
		if (response == 1) {
			console.log("jquery:" + response);
			Materialize.toast('Plazo de entrega de la solicitud de vacaciones configurado correctamente, prosiga', 3000,
			'rounded');
			
			$("#solicitud_vacaciones input").attr('disabled',true);
			
			

//			$("#idvac").val($.trim(response));
//			console.log($("#idvac").val());
//			$('#confirmar').addClass('disabled');
		} else {
			Materialize.toast('UPS!!, no se ha creado el consolidado!',
					3000, 'rounded', function(){
				 $("#date_solicitud").val("");
			});
		}
	});
		
	});
	
	
	function parseDate(input) {
		var map = {
			enero : 01,
			febrero : 02,
			marzo : 03,
			abril : 04,
			mayo : 05,
			junio : 06,
			julio : 07,
			agosto : 08,
			septiembre : 09,
			octubre : 10,
			noviembre : 11,
			diciembre : 12
		};
		input = input.split(" ");
		var mes0 = input[1].split("");
		console.log(mes0);
		mes0.pop();
		console.log(mes0);
		var mes1 = mes0.join("");
		console.log(mes1);

		mes = map[mes1.toLowerCase()];
		console.log("mes:" + mes);

		month = mes < 10 ? '0' + mes : mes, day = input[0] < 10 ? '0' + input[0]
				: input[0], newDate = input[2] + '/' + month + '/' + day;

		fecha_extra = day + '/' + month + '/' + input[2];
		var inicio = new Date(newDate);
		console.log("fecha_extra: " + fecha_extra);
		

		return fecha_extra;
	};
	
	