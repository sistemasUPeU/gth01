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
		<link href="js/plugins/prism/prism.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="js/plugins/perfect-scrollbar/perfect-scrollbar.css" type="text/css" rel="stylesheet" media="screen,projection">
  <link href="js/plugins/chartist-js/chartist.min.css" type="text/css" rel="stylesheet" media="screen,projection">
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
		
		
		
		
		
		<h1 style="font-family: 'Dosis', sans-serif;text-decoration:underline" class="center">REPORTE DE RENUNCIAS POR MOTIVOS GENERALES</h1>	
		<div>
		
		
		</div>
		<div class="row center" style="width:75%">
			<div class="col s6 center">
				<h6 class="col s6">Desde:</h6>			
				<input class="col s6" type="date" id="fecha1" class="dropify" style="width:50%">
			</div>
			<div class="col s6 center">
				<h6 class="col s6">Hasta:</h6>		
				<input type="date" class="col s6" id="fecha2" class="dropify" style="width:50%">
			</div>
		</div>
		<div class="center">		
			<button id="buscar">Buscar</button>		
		</div>

		<div class="container">
			<div id="line-example">
		</div>
		
		
		</div>
		
		<div class="divider"></div>
            <!--chart dashboard start-->
            
                
	
	<script
		src="<c:url value='/resources/js/plugins/raphael-min.js'></c:url>"></script>
	<script
		src="<c:url value='/resources/js/plugins/morris-chart/morris.min.js'></c:url>"
		type="text/javascript"></script>
	<script>
		$(document).ready(function(){
			$("#buscar").click(function(){
				var fecha1 = $("#fecha1").val();
				var fecha2 = $("#fecha2").val();
				$.get("graficando",{fecha1:fecha1,fecha2:fecha2,opc:1},function(data){
					alert(data);
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