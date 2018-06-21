$(document).ready(function(){
		$("#otrosdiv").hide();
	
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
		                +    'style="vertical-align:middle;color:#e10000;">'
		                + '</span> Error al guardar los datos';
		                this.setHeader(errorHeader);
		            }
		        };
		    },true,'alert');
		}
	$("#RegistrarR").click(function (event) {
		event.preventDefault();
        var form = $('#RenunciaForm')[0];
        var data = new FormData(form);
        var file=$("#pelon1").val();
        var fecha = $("#fecha").val();
        console.log(fecha);
        var array = $("#array_motivos").val();       
        if (!$("#otrosdiv").hasClass("hide")) {
		} else {
		}      
        open();
        alertify.confirm('Confirmar renuncia', 'Esta seguro(a) de confirmar la'+ 
        		'renuncia de este trabajador?', function(){    	
        	if(file!=""&&fecha!=""&&array!=""){
            	$.ajax({
                    type: "POST",
                    enctype: 'multipart/form-data',
                    url: "reg_ren",
                    data: data,
                    processData: false,
                    contentType: false,
                    cache: false,
                    timeout: 600000,
                    success: function (data) {
           $("#RegistrarR").prop("disabled", false);
           insertarMotivos();
           alertify.notify('Se ha registrado la renuncia satisfactoriamente.'+ 
        		   'Redireccionando a reportes...', 'custom', 2,
    					function() {
                      	window.location.href = gth_context_path+ '/renaban/listaRA';
    					});
                    },
                    error: function (e) {
                    	alertify
                   .errorAlert("Ha ocurrido un problema, comuníquese con el administradord" +
                   		" el sistema.<br/>");
                    }
                });
            }else{          	
            	alertify
                .errorAlert("Rellene todos los campos<br/>");
            }      	
        	}
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
                        
                        	$("#otrosdiv").show();
                        }else{
// $("#otrosdiv").removeClass("show");
                      		$("#otrosdiv").hide();
                      	}                        
                    }
                    if(newValuesArr.length==0){
                    	$("#otrosdiv").hide();
                    	
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

// MOSTRANDO LOS DETALLES DE TRABAJADOR AL FILTRAR POR DNI
function buscarDetalle(){	
	dni = $("#dni").val();
	
	$.get("detalleR",{dni:dni,opc:1},function(data,status){
		var detalle = JSON.parse(data);
//		console.log(detalle);
			if(detalle.length==0){
							$("#detalleR").hide();
							$("#fo").show();
							$("#msj").text("El trabajador identificado con DNI: " +dni+ " no tiene un contrato activo o se encuentre en un proceso de renuncia. Es posible, además, que el usuario no pertenezca a vuestro departamento");
							$("#dni").val("");
						}else{						
							$("#fo").hide();
							$("#detalleR").show();
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

//SE INSERTA LOS MOTIVOS DE LA RENUNCIA
function insertarMotivos(){
// alert("Motivos: "+$("#array_motivos").val());
	var array = $("#array_motivos").val();
	$.ajax("detalleR",{
		data:{
			'opc' : 3,
			'array':array
		},
		type:'GET',
		success:function(data){
			otros = $("#otros").val();
			if (otros=="") {
				// alert("NO VAS A INSERTAR OTROS XD");
			} else {
				insertarOtros(otros);
			}
	
		}
	});
}

//SI EL MOTIVO ES OTRO QUE NO APARECE EN LA LISTA, SE INSERTA LO SIGUIENTE
function insertarOtros(otros){
	$.get("detalleR",{otros:otros,opc:5},function(data,status){
		// alert(data);
	});
}
