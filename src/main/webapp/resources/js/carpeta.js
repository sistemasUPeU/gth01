
$(document).ready(function(){
	
	
	 $('#pelon').val("asdasdas");
	
	// FORMATEAR EL DATEPICKER DEL MATERIALIZE
	  $('#pelon').pickadate({
		    selectMonths: false, // Creates a dropdown to control month
		    selectYears: 15, // Creates a dropdown of 15 years to control
								// year,
		    today: 'Today',
		    clear: 'Clear',
		    close: 'Ok',
		    closeOnSelect: true // Close upon selecting a date,
		  });
	
	
	
	
	
            // Basic
			
            $('.dropify').dropify(function(event, element){
                return confirm("Do you really want to delete \"" + element.filename + "\" ?");
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
                return confirm("Do you really want to delete \"" + element.filename + "\" ?");
            });

            drEvent.on('dropify.afterClear', function(event, element){
                alert('File deleted');
            });
            
            $("#detalleR").hide();
            
            $("#dni").keypress(function(e) {
    			if (e.which == 13) {
    				$("#buscarDetalle").click();
    			}
    		});
            
            
            
            
            
        	
        	
        
            $.get("detalleR",{opc:2},function(data,status){
            	var mot = JSON.parse(data);
            	$('#motivo').html("");
            	$('#motivo').append("<option value='' disabled selected>Elija una o varias opciones</option>");
            	$.each(mot,function(key,val){
            		// alert(val.ID_MOTIVO);
// $("#motivo").first().after("<option
// id='"+val.ID_MOTIVO+"'>"+val.NO_MOTIVO+"</option>");
            		
            		$('#motivo').append($("<option></option>")
                               .attr("value",val.ID_MOTIVO)
                               .text(val.NO_MOTIVO));  $('#motivo').material_select();
            	});
            	 
            });
            $("#motivo option:selected").prop("selected",false);
            $("#mot").change(function(){
            	alert($("#motivo").val());
            	
            	$("#motivo option:selected").change(function () {
        
        		
         	   if($this.val()=='MOT-000007'){
         		   $("#other").show();
         	   }
         	  
         	}); 
            	
// $("#motivo option:selected").each(function () {
//            		
// var $this = $(this);
// if($this.val()!=""){
// if ($this.length) {
// var selText = $this.val();
// alert(selText);
// }
// }
// if($this.val()=='MOT-000007'){
// $("#other").show();
// }
//             	  
// });
            });
           
       
        });
// Mostrando los detalles del trabajador
function buscarDetalle(){	
	dni = $("#dni").val();
	
	$.get("detalleR",{dni:dni,opc:1},function(data,status){
		// alert(data);
		var detalle = JSON.parse(data);
		console.log(detalle);
		if(detalle.length==0){
			location.reload();
		}else{
			$("#detalleR").show();
//			alert(detalle[0].ID_TRABAJADOR);
			$("#idt").text(detalle[0].ID_TRABAJADOR);
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
			$("#idtr").val(detalle[0].ID_TRABAJADOR);
			$("#dni").val("");
			$("#dni").focus();
//			var idtra = 	$("#idtr").val();
//			console.log(idtra);
// $.get("detalleR",{idtr:idtra,opc:4},function(data,status){
// // console.log(data);
// var mot = JSON.parse(data);
// console.log(mot[0].NO_ARCHIVO+"."+mot[0].TI_ARCHIVO);
// var url = mot[0].NO_ARCHIVO+"."+mot[0].TI_ARCHIVO;
// $(".dropify-filename-inner").text(url);
// });
		}
		  
	});

}

function Pruebita() {
  alert("Hola Pelon c:");
 var x = $(".dropify-filename-inner").text();
  alert(x);
 var m = x.split(".");
 var no_archivo = m[0];
  alert(no_archivo);
 var ti_archivo = m[1];
 alert(ti_archivo);
// var idtr = $("#idtra").text();
// // alert(idtr);
$.get("detalleR",{idtr:idtr,no_arch:no_archivo,ti_arch:ti_archivo,opc:3},function(data,status){
 alert(data);
});
  
  
  
//	var idtr = "TRB-003651";
//	var x = $("#file").val();
//	$.get("detalleR",{idtr:idtr,file:x,opc:3},function(data,status){
//		 alert(data);		  
//	});
// console.log(x);
 var data = new FormData();
 $.each(jQuery('#pelon1')[0].files, function(i, file) {
     data.append('file-'+i, file);
 });
 //So now you have a FormData object, ready to be sent along with the XMLHttpRequest.

 $.ajax({
     url: 'form',
     data: data,
     cache: false,
     contentType: false,
     processData: false,
     method: 'POST',
     type: 'POST', // For jQuery < 1.9
     success: function(data){
         alert(data);
     }
 });
}



function Prueba() {
// //alert("Hola Pelon c:");
// var x = $(".dropify-filename-inner").text();
// //alert(x);
// var m = x.split(".");
// var no_archivo = m[0];
// //alert(n);
// var ti_archivo = m[1];
// var idtr = $("#idtra").text();
// //alert(idtr);
// $.get("detalleR",{idtr:idtr,no_arch:no_archivo,ti_arch:ti_archivo,opc:3},function(data,status){
// alert(data);
// });
//	alert();
var idtr = $("#idtr").val();
//var x = $("#file").val();
$.get("uploaded",{idtr:idtr},function(data,status){
	 alert(data);	
	 $("#hola").data(data);
});
// console.log(x);
}







