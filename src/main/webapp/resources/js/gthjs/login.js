$(document).ready(function() {
	console.log("asdsad");
});

function validarLogin() {
	console.log("asdsad");
    var usuario = $("#txtUsuario").val();
    var clave = $("#txtClave").val();
    $.ajax({
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
    });
}