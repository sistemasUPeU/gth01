<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="es">
<head>
<%@include file="../../../jspf/general.jspf"%>
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
	<script type="text/javascript">
		// Load the Visualization API and the corechart package.
		google.charts.load('current', {
			'packages' : [ 'corechart' ]
		});

		// Set a callback to run when the Google Visualization API is loaded.
		google.charts.setOnLoadCallback(drawChart);

		// Callback that creates and populates a data table,
		// instantiates the pie chart, passes in the data and
		// draws it.
		function drawChart() {

			// Create the data table.
			var data = new google.visualization.DataTable();
			data.addColumn('string', 'Topping');
			data.addColumn('number', 'Slices');
			data.addRows([ [ 'Mushrooms', 3 ], [ 'Onions', 1 ],
					[ 'Olives', 1 ], [ 'Zucchini', 1 ], [ 'Pepperoni', 2 ] ]);

			// Set chart options
			var options = {
				'title' : 'GTH - REPORTES',
				'width' : 600,
				'height' : 500
			};

			// Instantiate and draw our chart, passing in some options.
			var chart = new google.visualization.PieChart(document
					.getElementById('chart_div'));
			chart.draw(data, options);
		}
	</script>
<body>
</html>