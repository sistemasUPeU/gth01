$
		.get(
				"generar_papeleta/validar",
				function(res) {
					if (res != '') {
						// alert('if ' + res);
						// console.log('if ' + res);
						$("#cont_request").empty();
						$("#cont_request")
								.append(
										'<center><div style="height: 1200px;">'
												+ '<object id="request" type="application/pdf" data="'
												+ gth_context_path
												+ '/vacaciones/generar_papeleta/reporte?" width="90%" '
												+ 'height="100%"></object></div></center>')
					} else {
						// alert('else');
						$("#cont_request").empty();
						$("#cont_request")
								.append(
										'<center><h1>'
												+ '<div class="container">Su papeleta no está disponible por el momento, espere '
												+ '&#128521;</div>'
												+ '</h1></center>');
					}
				});
