$(document).ready(function(){
//	  $('#modal3').modal({
//	      dismissible: false, // Modal can be dismissed by clicking outside of the modal
//	      ready: function(modal, trigger) { // Callback for Modal open. Modal and trigger parameters available.
//	        alert("Ready");
//	        console.log(modal, trigger);
//	      },
//	      complete: function() { alert('Closed'); } // Callback for Modal close
//	    }
//	  );
	
	
	
	$("#fo").hide();
    $('#fecha').pickadate({
    	selectMonths: true, // Creates a dropdown to control month
    	selectYears: 15, // Creates a dropdown of 15 years to control
    	closeOnSelect: true, // Close upon selecting a date,
    	format: 'dd/mm/yyyy'
    		
// onStart: function() {
// alert('Hello there :)')
// },
      });
	  
// $("#RegistrarR").click(function () {
// alert("Insertando motivos...");
// insertarMotivos();
// });
	if(!alertify.errorAlert){
		  // define a new errorAlert base on alert
		  alertify.dialog('errorAlert',function factory(){
		    return{
		            build:function(){
		                var errorHeader = '<span class="fa fa-times-circle fa-2x" '
		                +    'style="vertical-align:middle;color:#e10000;">'
		                + '</span> Error al guardar los datos';
		                this.setHeader(errorHeader);
		            }
		        };
		    },true,'alert');
		}
	$("#RegistrarR").click(function (event) {
		event.preventDefault();
 

        // Get form
        var form = $('#RenunciaForm')[0];

		// Create an FormData object
        var data = new FormData(form);

		// If you want to add an extra field for the FormData
// data.append("CustomField", "This is some extra data, testing");
        var file=$("#pelon1").val();
        var fecha = $("#fecha").val();
        console.log(fecha);
        var array = $("#array_motivos").val();

		// disabled the submit button
// $("#RegistrarR").prop("disabled", true);
        alertify.confirm('Confirmar renuncia', '¿Está seguro(a) de confirmar la renuncia de este trabajador?', function(){
        	if(file!=""&&fecha!=""&&array!=""){
            	$.ajax({
                    type: "POST",
                    enctype: 'multipart/form-data',
                    url: "form",
                    data: data,
                    processData: false,
                    contentType: false,
                    cache: false,
                    timeout: 600000,
                    success: function (data) {
//                    	alert(data);
                    	insertarMotivos();
//                        alert("BIEN JONÁS : ", data);
//                        $('#modal3').modal('close');
                        $("#RegistrarR").prop("disabled", false);
                    },
                    error: function (e) {
                        alert("NADA JONÁS : ", e);
                        $("#RegistrarR").prop("disabled", false);
                    }
                });
            }else{          	
            	alertify
                .errorAlert("Error al guardar los datos <br/>");
            }
        	
        	
        	
        	alertify.success('Se ha registrado la renuncia satisfactoriamente') }
        , function(){ 
        	        	
        });
    });
			
            $('.dropify').dropify(function(event, element){
                return confirm("¿Desea eliminar el archivo \"" + element.filename + "\" ?");
            });

            // Translated
            $('.dropify-fr').dropify({
                messages: {
                    default: 'Glissez-déposez un fichier ici ou cliquez',
                    replace: 'Glissez-déposez un fichier ou cliquez pour remplacer',
                    remove:  'Supprimer',
                    error:   'Désolé, le fichier trop volumineux'
                }
            });

            // Used events
            var drEvent = $('#pelon1').dropify();

            drEvent.on('dropify.beforeClear', function(event, element){
                return confirm("¿Desea eliminar el archivo \"" + element.filename + "\" ?");
            });

            drEvent.on('dropify.afterClear', function(event, element){
                alert('Archivo eliminado');
            });
            
            $("#detalleR").hide();
            
            $("#dni").keypress(function(e) {
    			if (e.which == 13) {
    				$("#buscarDetalle").click();
    			}
    		});
            
            $('#motivo').material_select();
            $("#motivo option:selected").prop("selected",false);
            $('#motivo').change(function(){
            	// CON EL SIGUIENTE SCRIPT EVITAMOS QUE EL SELECT MULTIPLE
				// MANTENGA VALORES DESELECCIONADOS
                var newValuesArr = [],
                    select = $(this),
                    ul = select.prev();
                ul.children('li').toArray().forEach(function (li, i) {
                    if ($(li).hasClass('active')) {
                    	var item = select.children('option').toArray()[i].value;                       
                        newValuesArr.push(item);                     
                        if(item=='MOT-000007'){
                        	$("#other").show();
                        }else{
                      		$("#other").hide();
                      	}
                        
                        
                    }
                    $("#array_motivos").val(newValuesArr);
                });
                select.val(newValuesArr);
                // ---------------------------------
               // alert($("#motivo").val());
            });
       
            $.get("detalleR",{opc:2},function(data,status){
            	var mot = JSON.parse(data);
            	$('#motivo').html("");
            	$('#motivo').append("<option value='' disabled selected>Elija una o varias opciones</option>");
            	$.each(mot,function(key,val){
 
            		
            		$('#motivo').append($("<option></option>")
                               .attr("value",val.ID_MOTIVO)
                               .text(val.NO_MOTIVO));  $('#motivo').material_select();
            	});
            	 
            });
        });
// Mostrando los detalles del trabajador
function buscarDetalle(){	
	dni = $("#dni").val();
	
	$.get("detalleR",{dni:dni,opc:1},function(data,status){
//		 alert(data);
		var detalle = JSON.parse(data);
		console.log(detalle);
		if(detalle.length==0){
//			location.reload();
			$("#detalleR").hide();
			$("#fo").show();
			$("#msj").text("El trabajador identificado con DNI: " +dni+ " no tiene un contrato activo o ha renunciado... :'v");
			$("#dni").val("");
		}else{
			$("#fo").hide();
			$("#detalleR").show();
			$("#nombres").text(detalle[0].NOMBRES);
			$("#paterno").text(detalle[0].PATERNO);    
			$("#materno").text(detalle[0].MATERNO); 
			$("#fecha_nac").text(detalle[0].FECHA_NAC);  
			$("#fecha_inicio").text(detalle[0].FECHA_CONTRATO);  
			$("#direccion").text(detalle[0].DOMICILIO);  
			$("#departamento").text(detalle[0].NOM_DEPA);
			$("#area").text(detalle[0].NOM_AREA);
			$("#seccion").text(detalle[0].NOM_SECCION);
			$("#puesto").text(detalle[0].NOM_PUESTO);
			$("#tipo_contrato").text(detalle[0].TIPO_CONTRATO);
			$("#idcontrato").val(detalle[0].ID_CONTRATO);
			$("#dni").val("");
			$("#dni").focus();
		}
		  
	});

}

//function Pruebita() {
//  alert("Hola Pelon c:");
// var x = $(".dropify-filename-inner").text();
//  alert(x);
// var m = x.split(".");
// var no_archivo = m[0];
//  alert(no_archivo);
// var ti_archivo = m[1];
// alert(ti_archivo);
//$.get("detalleR",{idtr:idtr,no_arch:no_archivo,ti_arch:ti_archivo,opc:3},function(data,status){
//	alert(status);
// alert(data);
//});
  
  
  
// var idtr = "TRB-003651";
// var x = $("#file").val();
// $.get("detalleR",{idtr:idtr,file:x,opc:3},function(data,status){
// alert(data);
// });
// console.log(x);

//}



//function Prueba() {
//var idtr = $("#idtr").val();
//// var x = $("#file").val();
//$.get("uploaded",{idtr:idtr},function(data,status){
//	 alert(data);	
//	 $("#hola").data(data);
//});
//// console.log(x);
//}


function insertarMotivos(){
//	alert("Motivos: "+$("#array_motivos").val());
	var array = $("#array_motivos").val();
	$.ajax("crearR",{
		data:{
			'array':array
		},
		type:'GET',
		success:function(data){
//			alert(data);
//			window.location.assign(data);
			alert();
//			limpiar();
	
		}
	});
}

limpiar();

function limpiar(){

	
}





