<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<%@include file="../../../jspf/general.jspf"%>
<link
		href="<c:url value='/resources/js/plugins/morris-chart/morris.css'></c:url>"
		rel="stylesheet" type="text/css" />
</head>
<body>
	<%@include file="../../../jspf/header.jspf"%>
	
	<div id="main">
		<div class="wrapper">
			<%@include file="../../../jspf/aside_left.jspf"%>
			<%@include file="../../../jspf/info_puesto.jspf"%>
			<section id="content" class="col m12 l12 s12">
				<div class="card-panel">
					<h1>Hola JONAS</h1>
					<div id="chart_div"></div>
				</div>

			</section>

		</div>


	</div>

	<%@include file="../../../jspf/footer.jspf"%>
	<script src="<c:url value='/resources/js/plugins/loader.js'></c:url>"
		type="text/javascript"></script>
	<div id="myfirstchart" style="height: 250px;"></div>
	<script
		src="<c:url value='/resources/js/plugins/jquery-1.11.2.min.js'></c:url>"></script>
	<script
		src="<c:url value='/resources/js/plugins/raphael-min.js'></c:url>"></script>
	<script
		src="<c:url value='/resources/js/plugins/morris-chart/morris.min.js'></c:url>"
		type="text/javascript"></script>
	<div id="line-example"></div>
	<script>
// 		new Morris.Line({
// 			// ID of the element in which to draw the chart.
// 			element : 'myfirstchart',
// 			// Chart data records -- each entry in this array corresponds to a point on
// 			// the chart.
// 			data : [ {
// 				year : '2008',
// 				value : 20
// 			}, {
// 				year : '2009',
// 				value : 10
// 			}, {
// 				year : '2010',
// 				value : 5
// 			}, {
// 				year : '2011',
// 				value : 5
// 			}, {
// 				year : '2012',
// 				value : 20
// 			} ],
// 			// The name of the data record attribute that contains x-values.
// 			xkey : 'year',
// 			// A list of names of data record attributes that contain y-values.
// 			ykeys : [ 'value' ],
// 			// Labels for the ykeys -- will be displayed when you hover over the
// 			// chart.
// 			labels : [ 'Value' ]
// 		});
		
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
	<!-- 	<script type="text/javascript"> -->
	// // Load the Visualization API and the corechart package. //
	google.charts.load('current', { // 'packages' : [ 'corechart' ] // });

	// // Set a callback to run when the Google Visualization API is
	loaded. // google.charts.setOnLoadCallback(drawChart); // // Callback
	that creates and populates a data table, // // instantiates the pie
	chart, passes in the data and // // draws it. // function drawChart() {

	// // Create the data table. // var data = new
	google.visualization.DataTable(); // data.addColumn('string',
	'Topping'); // data.addColumn('number', 'Slices'); // data.addRows([ [
	'Mushrooms', 3 ], [ 'Onions', 1 ], // [ 'Olives', 1 ], [ 'Zucchini', 1
	], [ 'Pepperoni', 2 ] ]); // // Set chart options // var options = { //
	'title' : 'GTH - REPORTES', // 'width' : 600, // 'height' : 500 // };

	// // Instantiate and draw our chart, passing in some options. // var
	chart = new google.visualization.PieChart(document //
	.getElementById('chart_div')); // chart.draw(data, options); // }
	<!-- 	</script> -->
<body>
</html>