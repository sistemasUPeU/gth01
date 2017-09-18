$(document).ready(function() {
	alert("asdasd");
});

function validarLogin() {
    var formLogin = $(".formLogin");
    /*var txtStatus = $(".texto_box_h");
    $(".txtClave").addClass("ui-autocomplete-loading");
    $(".btnIngresar").hide();
    $(".box_cargando").hide();*/
    $.ajax({
        type: 'POST',
        url: 'valida',
        data: formLogin.serialize(),
        success: function (objJson) {
            console.log(objJson);
            if (objJson.status) {
                if (objJson.rpta) {
                    console.log(objJson);
                    location.href = 'menu';
                } else {
                    txtStatus.text(objJson.message);
                    //$(".box_cargando").show();
                    $(".txtClave").removeClass("ui-autocomplete-loading");
                    //$(".btnIngresar").show();
                }

            } else {
                txtStatus.text(objJson.message);
                //$(".box_cargando").show();
                $(".txtClave").removeClass("ui-autocomplete-loading");
                //$(".btnIngresar").show();
            }
        },
        error: function (xhr, ajaxOptions, thrownError) {
            txtStatus.text(xhr.status + " : " + thrownError);
            //$(".box_cargando").show();
            $(".txtClave").removeClass("ui-autocomplete-loading");
            //$(".btnIngresar").show();
        }
    });
}