$.get("generar_papeleta/validar", function(res) {
	if (res != null) {
		$("#cont_request").empty();
		$("#cont_request").append(
				'<center><div style="height: 1200px;">'
						+ '<object id="request" type="application/pdf" data="'
						+ gth_context_path
						+ '/vacaciones/generar_papeleta/reporte?" width="90%" '
						+ 'height="100%"></object></div></center>')
	}
});
