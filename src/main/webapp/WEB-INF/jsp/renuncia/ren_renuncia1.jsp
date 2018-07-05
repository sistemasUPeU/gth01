<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


<%@include file="../../../jspf/general.jspf"%>
<link href="https://fonts.googleapis.com/css?family=Dosis"
	rel="stylesheet">
<link
	href="<c:url value='/resources/js/plugins/morris-chart/morris.css'></c:url>"
	rel="stylesheet" type="text/css" />
<link href="js/plugins/prism/prism.css" type="text/css" rel="stylesheet"
	media="screen,projection">
<link href="js/plugins/perfect-scrollbar/perfect-scrollbar.css"
	type="text/css" rel="stylesheet" media="screen,projection">
<link href="js/plugins/chartist-js/chartist.min.css" type="text/css"
	rel="stylesheet" media="screen,projection">
</head>
<body>
	<%@include file="../../../jspf/header.jspf"%>
	<div id="loader-wrapper">
		<div id="loader"></div>

	</div>
	<div id="main">
		<div class="wrapper">
			<%@include file="../../../jspf/aside_left.jspf"%>
			<%@include file="../../../jspf/info_puesto.jspf"%>

			<section id="content" class="col m12 l12 s12">
			<h1></h1>


			</section>

		</div>





		<h1
			style="font-family: 'Dosis', sans-serif; text-decoration: underline"
			class="center">REPORTE DE RENUNCIAS POR MOTIVOS GENERALES</h1>
		<div></div>
		<div class="row center" style="width: 75%">
			<div class="col s6 center">
				<h6 class="col s6">Desde:</h6>
				<input class="col s6" type="date" id="fecha1" class="dropify"
					placeholder="Presione aqui" style="width: 50%">
			</div>
			<div class="col s6 center">
				<h6 class="col s6">Hasta:</h6>
				<input type="date" class="col s6" id="fecha2" class="dropify"
					placeholder="Presione aqui" style="width: 50%">
			</div>
		</div>
		<div class="center">
			<button id="buscar"
				class="btn btn-large waves-effect waves-light lime darken-4">Buscar</button>
		</div>

		<div class="container">
			<div id="line-example"></div>
		</div>
		<div class="container"
			style="position: relative; height: 60vh; width: 60vw">
			<div id="lon"></div>
			<div id="my"></div>
			<div id="lin"></div>
			<div id="lineal"></div>

		</div>

		<div class="divider"></div>
		<!--chart dashboard start-->



		<script
			src="<c:url value='/resources/js/plugins/raphael-min.js'></c:url>"></script>
		<script
			src="<c:url value='/resources/js/plugins/chartjs/Chart.js'></c:url>"
			type="text/javascript"></script>
		<script
			src="<c:url value='/resources/js/plugins/morris-chart/morris.min.js'></c:url>"
			type="text/javascript"></script>
		<script>
		$(document).ready(function(){
			var num =0;
			var cont =0;
			$("#buscar").click(function(){
				var fecha1 = $("#fecha1").val();
				var fecha2 = $("#fecha2").val();
				
					$.get("graficando",{fecha1:fecha1,fecha2:fecha2,opc:1},function(data){
						//alert(data);
							$("#fecha1").val("");
				     		$("#fecha2").val("");
							var motivo = [];
							var numero = [];
					        var reg = JSON.parse(data);			      
					        $.each(reg, function (index, obj){
					        	cont++;
					        	//alert(obj.NUMERO);
					        	numero.push(obj.NUMERO);
					        	motivo.push(obj.MOTIVO);
					        	//alert(regi);alert(visit);
					        	if(obj.numero==0){
					        		num++;
					        	}			        	
					        	if(num==7){
					        		alertify.myAlert("No se han encontrado registros");
					        	}          
					        });
					   		$("#lon").html('');
				    		$("#lon").append("<br><h2 style='text-align:center;font-family: 'Dosis', sans-serif; text-decoration:underline'><b> Gráfico de barras </b></h2>");		 
				    		$("#lin").html('');
				    		$("#lin").append("<br><h2 style='text-align:center;font-family: 'Dosis', sans-serif;text-align:center'><b> Gráfico de Líneas</b></h2>");
				    		
				    		$("#lineal").html('');
				    		$("#lineal").append("<canvas id='linealChart' width='85%' height='50%'></canvas>");
				 	        var ctxt = document.getElementById("linealChart").getContext('2d');
				 	        
				     		var linealChart = new Chart(ctxt, {
				     		    type: 'line',
				     		    data: {
				     		    	labels : motivo ,
				     		        datasets: [{
				     		        	label : '# de renuncias',
										data : numero ,
										backgroundColor : [
												'rgba(54, 162, 235, 0.2)',
												],
										borderColor : [ 
												'rgba(54, 162, 235, 1)',
												],
										borderWidth : 3,
				     		            // disable for a single dataset
				     		        }]
				     		    },
				     		    options: {
				     		         // disable for all datasets
				     		    }
				     		});
				     		$("#my").html('');
				     		 $("#my").append("<canvas id='myChart' ></canvas>");
				            var ctx = document.getElementById("myChart").getContext('2d');
				          
				    		var myChart = new Chart(ctx,
				    				{
				    					type : 'bar',
				    					data : {
				    						labels : motivo ,
				    						datasets : [ {
				    							label : '# de renuncias',
				    							data : numero ,
				    							backgroundColor : [ 'rgba(255, 99, 132, 0.2)',
				    									'rgba(54, 162, 235, 0.2)',
				    									'rgba(255, 206, 86, 0.2)',
				    									'rgba(75, 192, 192, 0.2)',
				    									'rgba(153, 102, 255, 0.2)',
				    									'rgba(255, 159, 64, 0.2)',
				    									'rgba(255, 159, 64, 0.2)'],
				    							borderColor : [ 'rgba(255,99,132,1)',
				    									'rgba(54, 162, 235, 1)',
				    									'rgba(255, 206, 86, 1)',
				    									'rgba(75, 192, 192, 1)',
				    									'rgba(153, 102, 255, 1)',
				    									'rgba(255, 159, 64, 1)' ],
				    							borderWidth : 2
				    						} ]
				    					},
				    					options : {
				    						scales : {
				    							yAxes : [ {
				    								ticks : {
				    									beginAtZero : true
				    								}
				    							} ]
				    						}
				    					}
				    				});
							
						
			     		
					});
					
				
				
				
				
			});
			
			$('#fecha1').pickadate({
		    	selectMonths: true, // Creates a dropdown to control month
		    	selectYears: 15, // Creates a dropdown of 15 years to control
		    	format: 'dd/mm/yyyy',
		      });
			
			$('#fecha2').pickadate({
		    	selectMonths: true, // Creates a dropdown to control month
		    	selectYears: 15, // Creates a dropdown of 15 years to control
		    	format: 'dd/mm/yyyy',
		      });
			
		});
		Morris.Line({
			  element: 'line-example',
			  data: [
			    { y: '2006', a: 100, b: 90 },
			    { y: '2007', a: 75,  b: 65 },
			    { y: '2008', a: 50,  b: 40 },
			    { y: '2009', a: 75,  b: 65 },
			    { y: '2010', a: 50,  b: 40 },
			    { y: '2011', a: 75,  b: 65 },
			    { y: '2012', a: 100, b: 90 }
			  ],
			  xkey: 'y',
			  ykeys: ['a', 'b'],
			  labels: ['Series A', 'Series B']
			});
	</script>

		<body>
</html>