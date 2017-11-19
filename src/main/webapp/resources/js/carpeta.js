
$(document).ready(function(){
            // Basic
			
            $('.dropify').dropify();

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
            var drEvent = $('.dropify-event').dropify();

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
            		//alert(val.ID_MOTIVO);
//            		$("#motivo").first().after("<option id='"+val.ID_MOTIVO+"'>"+val.NO_MOTIVO+"</option>");
            		
            		$('#motivo').append($("<option></option>")
                               .attr("value",val.ID_MOTIVO)
                               .text(val.NO_MOTIVO));  $('#motivo').material_select();
            	});
            	 
            });
            $("#motivo option:selected").prop("selected",false);
            $("#mot").change(function(){
            	alert($("#motivo").val());
            	
            	$("#motivo option:selected").change(function () {
            		$("#motivo > option").attr("selected",false);
        		
         	   if($this.val()=='MOT-000007'){
         		   $("#other").show();
         	   }
         	  
         	}); 
            	
//            	$("#motivo option:selected").each(function () {
//            		
//             	   var $this = $(this);
//             	   if($this.val()!=""){
//             		  if ($this.length) {
//                   	    var selText = $this.val();
//                   	    alert(selText);
//                   	   }
//             	   }
//             	   if($this.val()=='MOT-000007'){
//             		   $("#other").show();
//             	   }
//             	  
//             	});    
            });
           
       
        });
//Mostrando los detalles del trabajador 
function buscarDetalle(){	
	dni = $("#dni").val();
	
	$.get("detalleR",{dni:dni,opc:1},function(data,status){
		//alert(data);
		var detalle = JSON.parse(data);
		if(detalle.length==0){
			location.reload();
		}else{
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
			$("#dni").val("");
			$("#dni").focus();
		}
		  
	});

}


