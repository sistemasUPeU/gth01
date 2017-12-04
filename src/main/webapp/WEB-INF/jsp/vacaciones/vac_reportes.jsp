<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">

<head>

<%@include file="../../../jspf/general.jspf"%>
<link
	href="<c:url value='/resources/js/plugins/dropify/css/dropify.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
<link href="<c:url value='/resources/js/plugins/prism/prism.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="<c:url value='/resources/js/plugins/data-tables/css/jquery.dataTables.min.css'></c:url>"
	rel="stylesheet" type="text/css" />
<link
	href="<c:url value='/resources/js/plugins/chartist-js/chartist.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
</head>
<link href="<c:url value='/resources/css/alertify/alertify.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">
<link
	href="<c:url value='/resources/css/alertify/themes/bootstrap.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">

<link
	href="<c:url value='/resources/css/alertify/themes/default.min.css'/>"
	type="text/css" rel="stylesheet" media="screen,projection">

</head>
<body>
	<%@include file="../../../jspf/header.jspf"%>
	<div id="loader-wrapper">
		<div id="loader"></div>
		<div class="loader-section section-left"></div>
		<div class="loader-section section-right"></div>
	</div>
	<div id="main">
		<div class="wrapper">
			<%@include file="../../../jspf/aside_left.jspf"%>
			<%@include file="../../../jspf/info_puesto.jspf"%>


			<div class="col s6 m12 l6"
				style="margin-left: 30%; margin-right: 30%;">
				<div class="card-panel">
					<h4 class="header2">Filtra por fechas</h4>
					<div class="row">
						<form class="col s12">
							<div class="row">
								<div class="input-field col s5">
									<i class="mdi-action-perm-contact-cal prefix"></i> <input
										type="text" class="datepicker" id="fecha1"> <label
										for="dob">Fecha Inicio</label>
								</div>
								<div class="input-field col s5">
									<i class="mdi-action-perm-contact-cal prefix"></i> <input
										type="text" class="datepicker" id="fecha2">
									<label for="dob">Fecha Fin</label>
								</div>
								<div class="input-field col s2 ">
									<a
										class="btn-floating waves-effect waves-light  cyan darken-2 right" onclick = "mostrarChart()"><i
										class="mdi-action-search center"></i></a>
								</div>

							</div>


						</form>
					</div>
				</div>
			</div>


			<div id="contenedor" style=" height: 300px; margin-left: 15%; margin-right: 15%;">
	
			</div>
			<div id="contenedor1" style=" height: 300px; margin-left: 15%; margin-right: 15%;">
	
			</div>

		</div>
	</div>

	<script
		src="<c:url value='/resources/js/plugins/dropify/js/dropify.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='/resources/js/plugins/alertify/alertify.min.js'></c:url>"
		type="text/javascript"></script>
	<script
		src="<c:url value='https://www.gstatic.com/charts/loader.js'></c:url>"
		type="text/javascript"></script>
	<script type="text/javascript">

	$(document)
	.ready(

			function() {



				});

	



		var cont=1;
		function mostrarChart(){

			

			var fecha1 = parseDate($("#fecha1").val());
			var fecha2 = parseDate($("#fecha2").val());
		
			console.log(fecha1);
			console.log(fecha2);
			
			$.get(gth_context_path+"/reporte/listar",{fecha1:fecha1, fecha2:fecha2},function (data, status){
				
		    	var reg = JSON.parse(data); 

// // 		        $("#tablita").find("tr:gt(0)").remove();
// // 		        $.each(reg, function (index, obj){
// // 		            $("#tablita tr:last").after("<tr><td>"+cont+"</td><td>"+obj.region+"</td><td>"+obj.nroVisitas+"</td></tr>");
// // 		            cont++;
// // 		        }); 
		        console.log(reg);
		    	graficar(reg);
		        
		        
		    });

		}


		function graficar(data){
			
			
			google.charts.load('current', {'packages':['bar']});
			google.charts.setOnLoadCallback(function(){
				drawChart(data);
			});

			google.charts.load('current', {'packages':['corechart']});
			google.charts.setOnLoadCallback(function(){
				dibujar(data);
			});
			
		}


		function drawChart(result) {

		    // Create the data table.
		    var data = new google.visualization.DataTable();
		    data.addColumn('string', 'Departamento');
		    data.addColumn('number', 'Nro de Trabajadores');
		    var dataArray = [];
		    $.each(result, function(i,obj){
		    	dataArray.push([obj.NO_DEP, obj.NRO]);	
		    });
		    
		    
		    data.addRows(dataArray);
		    
		    
		    var options = {
		            chart: {
		              title: 'Reporte de Trabajadores que salieron de vacaciones por Departamento',
		              subtitle: '30/8/2017 12:57 pm',
		            },
				    series: {
				        0: { axis: 'visitas' } // Bind series 0 to an axis named 'distance'.
				        //1: { axis: 'brightness' } // Bind series 1 to an axis named 'brightness'.
				      },
				    axes: {
				        y: {
				          visitas: {label: 'nro trabajadores con vacaciones'} // Left y-axis.
				          //brightness: {side: 'right', label: 'apparent magnitude'} // Right y-axis.
				        }
				      }
		          };

		    
		    var chart = new google.charts.Bar(document.getElementById('contenedor'));

		    chart.draw(data, google.charts.Bar.convertOptions(options));
		    

		    
		    
		    
		    
		    }


		function dibujar(result) {

			
			 var chartDiv = document.getElementById('contenedor1');
			
		    // Create the data table.
		    var data = new google.visualization.DataTable();
		    data.addColumn('string', 'Departamentos');
		    data.addColumn('number', 'Trabajadores con vacaciones');
		    var dataArray = [];
		    $.each(result, function(i,obj){
		    	dataArray.push([obj.NO_DEP, obj.NRO]);	
		    });
		    
		    
		    data.addRows(dataArray);
		    
		    
		    var options = {
		            chart: {
		              title: 'Reporte de Trabajadores con vacaciones por Departamento',
		              subtitle: '04/12/2017 9:45 am',
		            },
		            series: {
		                0: { axis: 'visitas' } // Bind series 0 to an axis named 'distance'.
		                //1: { axis: 'brightness' } // Bind series 1 to an axis named 'brightness'.
		              },
		            axes: {
		                y: {
		                  visitas: {label: 'nro trabajadores con vacaciones'} // Left y-axis.
		                  //brightness: {side: 'right', label: 'apparent magnitude'} // Right y-axis.
		                }
		              }
		          };


	        var chart = new google.visualization.PieChart(document.getElementById('contenedor1'));
	        chart.draw(data, options);
		    
		   
		    
		   

		    }

	    


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
			mes0.pop();
			var mes1 = mes0.join("");

			mes = map[mes1.toLowerCase()];

			month = mes < 10 ? '0' + mes : mes, day = input[0] < 10 ? '0'
					+ input[0] : input[0], newDate = input[2] + '/' + month
					+ '/' + day;

			fecha_extra = day + '/' + month + '/' + input[2];
// 			var inicio = new Date(newDate);
			console.log("fecha_extra: " + fecha_extra);
			//console.log(inicio);

			// 		inicio.getDate() + '/' +
			//             	    (inicio.getMonth() + 1) + '/' + inicio.getFullYear();
			//          fecha_fin = calcular_final(inicio);
			//         	console.log("fecha_fin_return: "+fecha_fin);

			return fecha_extra;
		};













		
	</script>

</body>
</html>