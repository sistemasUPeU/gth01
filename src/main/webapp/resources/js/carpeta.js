
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
        });

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
<<<<<<< HEAD
        });
=======
            
            $("#detalleR").hide();
       
        });

function buscarDetalle(){	
	dni = $("#dni").val();
	
	$.get("detalleR",{dni:dni,opc:1},function(data,status){
		alert(data);
		var detalle = JSON.parse(data);
		if(detalle.length==0){
			location.reload();
		}else{

			$("#detalleR").show();
			$("#nombres").text(detalle[0].NOMBRES);
			$("#paterno").text(detalle[0].PATERNO);    
			$("#materno").text(detalle[0].MATERNO); 
			$("#fecha_nac").text(detalle[0].FECHA_NAC);  
			$("#fecha_inicio").val(detalle[0].FECHA_CONTRATO);  
			$("#direccion").val(detalle[0].DOMICILIO);  
			$("#departamento").val(detalle[0].NOM_DEPA);
			$("#area").val(detalle[0].NOM_AREA);
			$("#seccion").val(detalle[0].NOM_SECCION);
			$("#puesto").val(detalle[0].NOM_PUESTO);
			$("#tipo_contrato").val(detalle[0].TIPO_CONTRATO);
		}
		  
	});

}

>>>>>>> branch 'modulo-renuncias' of https://github.com/sistemasUPeU/gth01.git
