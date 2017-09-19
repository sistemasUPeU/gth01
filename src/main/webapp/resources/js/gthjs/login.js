$(document).ready(function() {
});

function validarLogin() {
	console.log("asdsad");
	var url = 'valida';
    var usuario = $("#txtUsuario").val();
    var clave = $("#txtClave").val();
    var data = "username="+usuario+"&clave="+clave;
    $.getJSON(url,data,function(objJson){
    	console.log(objJson);
        if (objJson.status) {
            if (objJson.rpta) {
                console.log(objJson);
                location.href = 'menu';
                
            } 

        }
    });
    /*$.ajax({
        type: 'POST',
        url: 'valida',
        data: "username="+usuario+"&clave="+clave,
        success: function (objJson) {
            console.log(objJson);
            if (objJson.status) {
                if (objJson.rpta) {
                    console.log(objJson);
                    location.href = 'menu';
                    
                } 

            } 
        }
    });*/
}