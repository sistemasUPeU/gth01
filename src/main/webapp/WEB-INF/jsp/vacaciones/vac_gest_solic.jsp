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

			<section id="content" style="margin-left: 10%; margin-right: 10%;">

				<!--start container-->
				<div class="container">

					<!-- 					<div id="icon-prefixes" class="section"> -->
					<h4 class="header">Datos Generales</h4>
					<div class="row">
						<div class="col s12 m12 l12">
							<div class="row">
								<form class="col s12">
									<div class="row">
										<div class="input-field col s12 l4">
											<i class="mdi-action-account-circle prefix"></i> <input
												id="nombres" type="text" class="validate" disabled>
											<label for="icon_prefix3">Nombres y Apellidos</label>
										</div>
										<div class="input-field col s12 l4">
											<i class="mdi-action-perm-identity prefix"></i> <input
												id="dni" type="tel" class="validate" disabled> <label
												for="icon_telephone">DNI</label>
										</div>
										<div class="input-field col s12 l4">
											<i class="mdi-content-content-paste prefix"></i> <input
												id="tipo" type="tel" class="validate" value="${tipo}"
												disabled> <label for="icon-request">Tipo de
												Solicitud</label>
										</div>
										<div class="input-field col s12 l12">
											<input id="idtrb" type="hidden" class="validate"> <input
												id="idrol" type="hidden" class="validate">
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>

					<!-- 					</div> -->
					<h4 class="header">Programa de Horarios</h4>
					<div class="row">

						<div class="col s12 m12 l12" id="btn-agregar">

							<p>
								<a
									class="btn-floating btn-large waves-effect waves-light green accent-3 left"
									id="agregar"><i class="mdi-content-add left"></i></a>
							</p>

						</div>

					</div>
					<br>
					<div class="row" id="space">
						<div class="col s12 m12 l6">
							<div class="card-panel">
								<h4 class="header2">Vacaciones 1</h4>
								<div class="row">
									<form class="col s12">
										<div class="row">
											<div class="input-field col s6">
												<i class="mdi-action-perm-contact-cal prefix"></i> <input
													type="text" class="datepicker" id="fe_inicio_1"> <label
													for="dob">Fecha Inicio</label>
											</div>
											<div class="input-field col s6">
												<i class="mdi-action-perm-contact-cal prefix"></i> <input
													type="text" class="datepicker" id="fe_final_1" disabled>
												<label for="dob">Fecha Fin</label>
											</div>

										</div>
										<div class="row">
											<div class="col s6 m8">
												<a
													class="btn-floating waves-effect waves-light  cyan darken-2 right"><i
													class="mdi-content-save center"></i></a>
											</div>
											<div class="col s3 m2">
												<a
													class="btn-floating waves-effect waves-light  yellow darken-4 right"><i
													class="mdi-editor-mode-edit center"></i></a>
											</div>

											<div class="col s3 m2">
												<a
													class="btn-floating waves-effect waves-light  red darken-4 right"><i
													class="mdi-action-delete center"></i></a>
											</div>
										</div>

									</form>
								</div>
							</div>
						</div>

					</div>




					<div class="row">

						<div class="col s7 m8 l10">
							<a class="waves-effect waves-light btn right"
								id="print" ><i class="mdi-action-print right" ></i>Imprimir</a>
							<!-- 								<a -->
							<!-- 								class="waves-effect waves-light btn modal-trigger" -->
							<!-- 								href="#modal1">Modal</a> -->
						</div>
						<div class="col s5 m4 l2">
							<a class="btn waves-effect waves-light  cyan darken-2 right"
								onclick="insertar()">Confirmar<i
								class="mdi-navigation-check right"></i></a>

						</div>


					</div>


					<div id="file-upload" class="section">

						<div class="row section"
							style="margin-left: 20%; margin-right: 20%">

							<div class="col s12 m12 l12 center">
								<p>Maximum file upload size 2MB.</p>
								<input type="file" id="input-file-max-fs" class="dropify"
									data-max-file-size="2M" />
							</div>


						</div>
					</div>

					<div class="row">

						<div class="col s7 m8 l12 center">
							<a class="waves-effect waves-light btn center"><i
								class="mdi-file-file-upload right"></i>Guardar documento</a>

						</div>
					</div>
				</div>

			</section>
		</div>


		<div id="modal1" class="modal" style="width:850px; height: 2000px;">
			<div class="modal-content">
			
<!-- 				D:\\RRHH\\GTH\\gth01\\src\\main\\resources\\jasperreports\\request_report.jasper -->
				<object data="" type="application/pdf" width="800" height="600">

				</object>
			</div>
		</div>

	</div>


	<script
		src="<c:url value='/resources/js/plugins/dropify/js/dropify.min.js'></c:url>"
		type="text/javascript"></script>


	<script type="text/javascript">
	function loadProfile(){
		location.href="<%=request.getContextPath()%>/trabajador/profile";
	}
	var divisiones=0;
    $( document ).ready(function() {
      
        	try{
            	console.log(gth_context_path);
        		$.getJSON(gth_context_path + '/components',"opc=usuario",function(objJSON){
        			if(objJSON !== null){
        		
        				var s =objJSON.datos_usuario;
        				var d=objJSON.dni;
        				var t=objJSON.idtrb;
        				var r = objJSON.idrol;
        				console.log(s + d + t);
        				$("#nombres").val("");
        				$("#nombres").val(s);
        				$("#dni").val(d);
        				$("#idtrb").val(t);
        				$("#idrol").val(r);
        				 Materialize.updateTextFields();
        			}else{
        				console.error("No se esta cargando la informaci�n");
        			}


                    var rol =$("#idrol").val();
                    console.log(rol);
                    if(rol == "ROL-0003" ){
        				divisones = 2;
        				
                     }else{
        				if (rol == "ROL-0008"){
        					divisones = 3;
        				}else{
        					console.log("sin nada");
        					divisones = 1;
        					$("#btn-agregar").hide();
        				}

                     }
        			
        		});
        	}catch(e){
        		console.error("error al listar info : "+e);
        	}
        	
            $('.dropify').dropify();

            // Translated
            $('.dropify-fr').dropify({
                messages: {
                    default: 'Glissez-d�posez un fichier ici ou cliquez',
                    replace: 'Glissez-d�posez un fichier ou cliquez pour remplacer',
                    remove:  'Supprimer',
                    error:   'D�sol�, le fichier trop volumineux'
                }
            });

            // Used events
            var drEvent = $('.dropify-event').dropify();

            drEvent.on('dropify.beforeClear', function(event, element){
                return confirm("Do you really want to delete \"" + element.filename + "\" ?");
            });

            drEvent.on('dropify.afterClear', function(event, element){
                alert('File deleted');
            });
       
        });

    var fecha_extra = "";
        $("#print").click(function(){
            $('.modal').openModal();
            var idt= $("#idtrb").val();
            console.log(idt);

// 			$.get("reporte", {
// 				idtr:"TRB-002315"
// 	        },
// 		        function (data, status) {
// 					console.log(data);
					
					$("object").attr("data","reporte?idtr="+idt);
// 					$("embed").attr("src","reporte?idtr=TRB-002315");
// 		        }
// 	    	);
		});


        $("#fe_inicio_1").change(function(){
        	var fei=$("#fe_inicio_1").val();
			
        	console.log(fei);
        	var fecha_inicio = parseDate(fei);
        	console.log("fecha_inicio_return: "+fecha_inicio);
        	
        
        	$('#fe_final_1').pickadate('picker').set('select', calcular_final(fecha_inicio), { format: 'dd/mm/yyyy' }).trigger("change");
       	 Materialize.updateTextFields();
			
        	
        	//var ex = $("#fe_inicio").pickadate({ dateFormat: 'dd/mm/yyyy' });
//         	var date = new Date(fei.pickadate),
//             yr = date.getFullYear(),

//             month = date.getMonth() < 10 ? '0' + date.getMonth() : date.getMonth(),
//             day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate(),
//             newDate = day + '/' + month + '/' + yr;
            
//         	console.log(newDate);
			//console.log(calcular());
        	
            });



        

       // var fecha_fin = new Date();
        function parseDate(input){
        	var map = {enero: 01, febrero: 02, marzo: 03, abril: 04, mayo: 05, junio: 06,
                    julio: 07, agosto: 08, septiembre: 09, octubre: 10, noviembre: 11, diciembre: 12};
         input = input.split(" ");
         var mes0 = input[1].split("");
         console.log(mes0);
         mes0.pop();
         console.log(mes0);
         var mes1=mes0.join("");
         console.log(mes1);

         mes =map[mes1.toLowerCase()];
         console.log("mes:"+mes);

         month = mes < 10 ? '0' + mes : mes,
                 day = input[0] < 10 ? '0' + input[0] : input[0],
                 newDate = input[2] + '/' + month + '/' + day;
// 		var inicio = new Date(input[2],(map[mes1.toLowerCase()] || mes1) - 1, input[0]);
// 		console.log(inicio);
		fecha_extra = day + '/' + month + '/' + input[2];
		var inicio = new Date(newDate);
		console.log("fecha_extra: "+fecha_extra);
		//console.log(inicio);

// 		inicio.getDate() + '/' +
//             	    (inicio.getMonth() + 1) + '/' + inicio.getFullYear();
//          fecha_fin = calcular_final(inicio);
//         	console.log("fecha_fin_return: "+fecha_fin);
          	
          
          return inicio;
            };


            function calcular_final(begin) {
            	 // var hoy = new Date();
            	 console.log("fecha enviada "+begin ); 
            	 console.log("fecha enviada "+begin.getFullYear );           	 
            	  var calculado = new Date();
//             	  console.log("parametro:"+begin.getDate());
//             	  var dia = begin.getDate() + 30;
//             	  console.log("DIA: " +dia);
            	  begin.setDate(begin.getDate() + 30);

            	  var anno=begin.getFullYear();
            	  var mes= begin.getMonth()+1;
            	  var dia= begin.getDate();
            	  mes = (mes < 10) ? ("0" + mes) : mes;
            	  dia = (dia < 10) ? ("0" + dia) : dia;
            	  var fechaFinal = dia+"/"+mes+"/"+anno;

            	  console.log("fecha calculada: "+fechaFinal);
//             	  var fin = calculado.getDate() + '/' +
//             	    (calculado.getMonth() + 1) + '/' + calculado.getFullYear()+
//             	    ' -- ' + calculado.getFullYear() + '/' +
//             	    (calculado.getMonth() + 1) + '/' + calculado.getDate();

            	    return fechaFinal;
            	}

//             function calcular() {
//             	  var hoy = new Date();
//             	 // var dias = parseInt(numero.value);
//             	  var calculado = new Date();
//             	  calculado.setDate(
//             	    hoy.getDate() + 48
//             	  );

//             	  return calculado.getDate() + '/' +
//             	    (calculado.getMonth() + 1) + '/' + calculado.getFullYear() +
//             	    ' -- ' + calculado.getFullYear() + '/' +
//             	    (calculado.getMonth() + 1) + '/' + calculado.getDate();
//             	}


var cont = 2;
	$("#agregar").click(function (){
		console.log(divisiones);
		if(cont<=divisiones){
			var s = '';
			s += '<div class="col s12 m12 l6">';
			s += '<div class="card-panel">';
			s += '<h4 class="header2">Vacaciones ' +cont+'</h4>';
			s += '<div class="row">'
				s += '	<form class="col s12">';
					s += '<div class="row">';
			s += '<div class="input-field col s6">';
			s += '<i class="mdi-action-perm-contact-cal prefix"></i> <input type="text" class="datepicker" onchange="setti(this.id)" id="fe_inicio_'+cont+'"> <label for="dob">Fecha Inicio</label>';
			s += '</div>'
				s += '<div class="input-field col s6">';
				s += '<i class="mdi-action-perm-contact-cal prefix"></i> <input type="text" class="datepicker" id="fe_final_'+cont+'" disabled><label for="dob">Fecha Fin</label>';
				s += '</div>';
			s += '</div>';
			s += '<div class="row"><div class="col s6 m8"><a class="btn-floating waves-effect waves-light  cyan darken-2 right"><i class="mdi-content-save center"></i></a>	</div>';
			s += '<div class="col s3 m2">	<a	class="btn-floating waves-effect waves-light  yellow darken-4 right"><i	class="mdi-editor-mode-edit center"></i></a></div>';
			s += '<div class="col s3 m2"> <a class="btn-floating waves-effect waves-light  red darken-4 right"><i class="mdi-action-delete center"></i></a> </div>';		
			s += '</div></form> </div></div>	</div>';	
			
		
		
			$("#space").append(s);
			cont++;
		}else{
			Materialize.toast('Ya no puede particionar m�s sus vacaciones!', 3000, 'rounded');
			}



			
			 $('.datepicker').pickadate({
				    selectMonths: true, // Creates a dropdown to control month
				    selectYears: 15, // Creates a dropdown of 15 years to control year,
				    today: 'Today',
				    clear: 'Clear',
				    close: 'Ok',
				    closeOnSelect: false // Close upon selecting a date,
				  });
		});


	function setti(id){
		console.log(id);
		console.log("hi everyone");
 		var fei=$("#"+id).val();
		console.log(fei);
 		var array = id.split("_");
 		var num = array[2];
 		console.log(num);
     	var fecha_inicio3 = parseDate(fei);
    	
    	        
     	$('#fe_final_'+num).pickadate('picker').set('select', calcular_final(fecha_inicio3), { format: 'dd/mm/yyyy' }).trigger("change");
     	 Materialize.updateTextFields();
		}



    function getArray_fechas(op) {

        var fechas = [];
//         $('#data :checked').each(function () {
	
	if(op==1){
		for (var i=1; i<cont; i++) {
			parseDate($("#fe_inicio_"+i).val());
// 			console.log($("#fe_inicio_"+i).val());
			console.log(fecha_extra);
		    fechas.push(fecha_extra);
		}
	}
	if(op == 2){
		for (var i=1; i<cont; i++) {
			parseDate($("#fe_final_"+i).val());
// 			console.log($("#fe_inicio_"+i).val());
			console.log(fecha_extra);
		    fechas.push(fecha_extra);
		}

	}
	
//             fecha_i.push();
//         });
//         return allVals;
return fechas;
    }
    var queryArr = [];
    function insertar(){
        var fechas_0=getArray_fechas(1); //fecha inicio
        var fechas_1=getArray_fechas(2); //fecha fin
		var tama�o = fechas_0.length;



        var inicio=fechas_0.join("-");
        var fin=fechas_1.join("-");
console.log("ini: "+inicio);
//         inicio.push(fin);
//         console.log("fin-fe: "+inicio);

        
        var locations = [ 
                fechas_0,                                
               fechas_1
             ];
//         console.log(fechas_0);
//         for (var n=0; n<2; n++) {
// 			queryArr.push(fechas_n);
// 		}
         console.log(locations);
         console.log(locations[0]);
//              queryStr = {locations };
//              queryArr.push(queryStr);
        
//         console.log(queryArr);
//         console.log(queryArr[1]);
//         for(var i=0;i<arrid.length;i++)
		console.log(JSON.stringify(locations));
		console.log(JSON.stringify(locations));
		var array = JSON.stringify(locations);

        /* $.ajax({
        	    type : "GET",
        	    url : "insertar",
        	    data : { "json":JSON.stringify(locations)          */
//         	        myData: {
//         	           "array": array,
//         	           "fechas1": fechas_1
        	         
//         	        },
        	               
        	    /*},
        	    dataType: "json",
        	    contentType:"application/json",
        	    success : function(response) {
        	    	Materialize.toast('Solicitud registrada exitosamente', 3000, 'rounded');
        	    },
        	    error : function(e) {
        	       console.log('Error: ' + e);
        	    }
        	}); */
// var harold={
//         			array1:fechas_0,
//         			array2:fechas_1
//         	};
//      var datos = {"myArray":array};
//      {"inicio": fechas_0,"final": fechas_1}
        	

     var datos="inicio="+inicio;
     datos += "?final="+fin;
console.log("dat: "+datos);
    
var con = new jsConnector();
        	con.post('solicitud/insertar?'+datos,null,function(){
//         	console.log(algo);
        	});

        	
    };

    function aaa(){
$("#aa").submit();
        }


    


	

    </script>
</body>
</html>