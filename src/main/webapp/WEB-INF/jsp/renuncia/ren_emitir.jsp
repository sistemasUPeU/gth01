<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="../../../jspf/general.jspf"%>
<link href="<c:url value='/resources/css/style.min.css'></c:url>"
	rel="stylesheet" type="text/css" />
<title>Renuncias</title>
</head>
<style>
.responsive-object object {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
}

.responsive-object {
	position: relative;
	padding-bottom: 67.5%;
	height: 0;
	margin: 10px 0;
	overflow: hidden;
}
</style>
<body>
	<%@include file="../../../jspf/header.jspf"%>
	<div id="main">
		<div class="wrapper">
			<%@include file="../../../jspf/aside_left.jspf"%>
			<%@include file="../../../jspf/info_puesto.jspf"%>
			<!-- 			<section id="content" class="col m12 l12 s12"> -->


			<hr>
			<div class="container">
				
				<br>
				<d iv class="row">
				<div class="input-field">
					<i class="mdi-content-create large left prefix"></i>
					<textarea id="motivos" class="materialize-textarea" length="500"
						maxlength=500></textarea>
					<label for="motivos">Redacte los motivos de su renuncia</label>
				</div>

				<div class="input-field col s12 m4 l4">
					<i class="mdi-notification-event-note large left prefix"></i> <input
						id="fecha" ;
										type="text" class="datepicker"> <label
						for="fecha">Fecha de la renuncia</label>

				</div>
				<a class="btn-large waves-effect waves-light green accent-3"
					target="_blank" onclick=prueba1()><i
					class="mdi-editor-insert-drive-file"></i> Generar Documento</a>
			</div>
			<!-- 			<div id="card-alert" class="card orange"> -->
			<!-- 				<div class="card-content white-text"> -->
			<!-- 					<p> -->
			<!-- 						<i class="mdi-alert-warning"></i> WARNING : Bandwidth limit -->
			<!-- 						exceeded -->
			<!-- 					</p> -->
			<!-- 				</div> -->
			<!-- 				<button type="button" class="close white-text" data-dismiss="alert" -->
			<!-- 					aria-label="Close"> -->
			<!-- 					<span aria-hidden="true">×</span> -->
			<!-- 				</button> -->
			<!-- 			</div> -->
			<div class="row">
				<!--DARK-->
				<div class="col s12 m8 l8">
					<div id="card-alert" class="card orange lighten-5" style="display:none">
						<div class="card-content orange-text">
							<p>
								<i class="mdi-alert-warning"></i> ADVERTENCIA : Rellene los dos espacios
							</p>
						</div>
<!-- 						<button type="button" class="close orange-text" -->
<!-- 							data-dismiss="alert" aria-label="Close"> -->
<!-- 							<span aria-hidden="true">×</span> -->
<!-- 						</button> -->
					</div>

				</div>
			</div>


			
			<hr>
			<br>
			<center >
				<div class="form-group" style="display:none" id="opciones">
					<a
						class="btn-floating btn-large waves-effect waves-light green accent-3"
						href="" target="_blank" id="printea"><i
						class="mdi-action-print"></i></a> <a
						class="btn-floating btn-large waves-effect waves-light  light-green accent-3"
						href="<c:url value="/"/>"><i class="mdi-content-backspace"></i></a>
				</div>
			</center>
			<div class="responsive-object" style="margin-bottom: 4em"
				style="display:none">
				<center>
					<object type="application/pdf" data=""></object>
				</center>
			</div>

		</div>
		<hr>


	</div>


	</div>
	<div style="position: fixed; width: 100%; bottom: 0; z-index: 5">
		<%@include file="../../../jspf/footer.jspf"%>
	</div>
	<script type="text/javascript">
		var date_modified = "";
		var add = 0;
		$(document).ready(function() {
// 			prueba1();
			
			
			$.get(gth_context_path +"/trabajador/limiteR",null,function(data){
				add = parseInt(data);
				console.log("esto es "+add);
// 				var dat = new Date();
// 				var myNewDate = new Date(dat);
// 				myNewDate.setDate(myNewDate.getDate() + fecha);
// 				console.log("Esta es!: "+myNewDate);
// 				date_modified = formatoFecha(myNewDate);
// 				console.log("supuestamente modificado: "+date_modified);
				$('#fecha').pickadate({
			    	selectMonths: true, // Creates a dropdown to control month
			    	selectYears: 15, // Creates a dropdown of 15 years to control
			    	format: 'dd/mm/yyyy',
			    	min: add,
			    	onSet: function(context) {
			    	    console.log('Just set stuff:', context)
			    	    console.log(date_modified);
			    	  },
			      });
				
			});
			
			
		});
		 
		
		function prueba1() {
			
			console.log("alert :V");
			var fecha = $("#fecha").val();
			var argumento = $("#motivos").val();
			
			if(fecha!=""&&argumento!=""){
				$.get(gth_context_path + "/trabajador/carta_de_renuncia",{fecha:fecha,argumento:argumento}, function(objJson) {
					if(objJson !=""){
						$("object").css("display","block");
						$("object")
						.attr('data', gth_context_path + "/trabajador/carta_de_renuncia?fecha="+fecha+"&argumento="+argumento+"")
						.hide().show();
				
						$("#printea").attr('href','<c:url value="'+gth_context_path + '/trabajador/carta_de_renuncia?fecha='+fecha+'&argumento='+argumento+'"/>');
						$("#opciones").hide().show();
						
						$("#fecha").val("");
						$("#motivos").val("");
					}
					
					// 				alert(objJson);
					// 				console.log(objJson.hola);
					//		        var men = JSON.parse(data); 
					//alert(prod[0].idproducto);
				});
			}else{
				$( "#card-alert" ).fadeTo(0, 500).show( "slow", function() {
				    // Animation complete.
					window.setTimeout(function() {
						
					    $("#card-alert").fadeTo(500, 0).slideUp(800, function(){
					        $(this).hide(); 
					    });
					}, 4000);
				  });
				
				
				
			}
			
			
		}
		
		
		function formatoFecha(fecha){
// 			var today = new Date();
			var dd = fecha.getDate();
			var mm = fecha.getMonth()+1; //January is 0!

			var yyyy = fecha.getFullYear();
			if(dd<10){ 
			    dd='0'+dd;
			} 
			if(mm<10){
			    mm='0'+mm;
			} 
			var today = dd+'/'+mm+'/'+yyyy;
			return today;
		}
	</script>
</body>
</html>
