$(document).ready(function(){	
	$("#otrosdiv").hide();
	//FECHA
    $('#fecha').pickadate({
    	selectMonths: true, // Creates a dropdown to control month
    	selectYears: 15, // Creates a dropdown of 15 years to control
    	format: 'dd/mm/yyyy',
      });
	if(!alertify.errorAlert){
		  alertify.dialog('errorAlert',function factory(){
		    return{
		            build:function(){
		                var errorHeader = '<span class="fa fa-times-circle fa-2x" '
		                + 'style="vertical-align:middle;color:#e10000;">'
		                + '</span> Error al guardar los datos';
		                this.setHeader(errorHeader);
		            }
		        };
		    },true,'alert');
		}
	//INSERTAR ABANDONO
	$(document).on('confirmation', '.remodal', function () {
		var reg_aban = $('#AbandonoForm')[0];
        var data = new FormData(reg_aban);
        var file=$("#pelon1").val();
        var fecha = $("#fecha").val();
        open();
        //ALERT DINAMICO PARA ABANDONO
        swal({
    		title: "¿Está seguro de confirmar",
    		text: "el abandono de este trabajador?",
    		type: "warning",
    		showCancelButton: true,
    		confirmButtonColor: '#40D005',
    		confirmButtonText: 'Confirmar',
    		cancelButtonText: 'Cancelar',
    		closeOnConfirm: false,
    		closeOnCancel: false
    	},
    	function(isConfirm){
            if (isConfirm){												 
    				if (file!=""&&fecha!=""){
    					$.ajax({
    	                    type: "POST",
    	                    enctype: 'multipart/form-data',
    	                    url: "reg_aban",
    	                    data: data,
    	                    processData: false,
    	                    contentType: false,
    	                    cache: false,
    	                    timeout: 600000,
    	                    success: function (data) {
    	           $("#RegistrarA").prop("disabled", false);
    	           swal("Se ha registrado" ,"el abandono satisfactoriamente", "success");
    				  window.setTimeout(function() {							
    					  window.location.href = gth_context_path+ '/renaban/listaRA';
    					}, 2000);
    	                    },
    	                });					  
    				}else{
    					swal("Error", "Rellene todos los campos", "error");
    				}        				            				           							 					     	 			        	
            } else {
            	window.location.href = gth_context_path+ '/renaban/listaRA';
            }
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
            });           
            $("#detalleA").hide();            
            $("#dni").keypress(function(e) {
    			if (e.which == 13) {
    				$("#buscarDetalle").click();
    			}
    		});            
        });
function open(){
	$(".ajs-header").addClass("#82b1ff  blue accent-1");
	var isOpen = alertify.confirm().isOpen(); 
	 if(isOpen=true){
		 $(".ajs-ok").attr("id","alertyboton");
		 $(".ajs-cancel").attr("id","alertyboton2");
		 $(".alertify .ajs-modal").css("z-index","999999");
	 }
	 $("#alertyboton").addClass("btn waves-effect waves-light #2962ff blue accent-4");
		$("#alertyboton2").addClass("btn waves-effect waves-light #bdbdbd grey lighten-1");
}

// BUSCAR DETALLES DE TRABAJADOR POR DNI
function buscarDetalle(){	
	dni = $("#dni").val();	
	$.get("detalleA",{dni:dni,opc:1},function(data,status){
		var detalle = JSON.parse(data);	
			if(detalle.length==0){
							$("#detalleA").hide();
							$("#fo").show();
							$("#msj").text("El trabajador identificado con DNI: " +dni+ " no tiene un contrato activo o ha Abandondado. Es posible, además, que el usuario no pertenezca a vuestro departamento");
							$("#dni").val("");
						}else{
							$("#fo").hide();
							$("#detalleA").show();
							$("#nombres").text(detalle[0].NOMBRES);
							$("#paternos").text(detalle[0].PATERNO);    
							$("#maternos").text(detalle[0].MATERNO); 
							$("#fecha_nacs").text(detalle[0].FECHA_NAC);  
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
