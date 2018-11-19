function jsConnector() {
	this.get = function(url, data, _callback) {
		connect('GET', url, data, _callback);
	};
	this.post = function(url, data, _callback) {
		connect('POST', url, data, _callback);
	};

}

function connect(type, url, data, _callback) {
	var csrfHeader = $("meta[name='_csrf_header']").attr("content");
	var csrfToken = $("meta[name='_csrf']").attr("content");
	console.log("data " + data);

//	var enviar = JSON.parse(array);
	$.ajax({
		url : gth_context_path + "/" + url,
		type : type,
		contentType : 'application/json',
		data : data,
		dataType : 'json',
		timeout : 2000,
		beforeSend : function(xhr) {
			xhr.setRequestHeader(csrfHeader, csrfToken);
		},
		success : function(result) {
			try {
				_callback(result);
			} catch (e) {
				console.log(e);
			}
		},
		error : function() {
			/*errorMessage({
				title : message.connector.title,
				content : message.connector.error
			});*/
			//console.log(message.connector.title, message.connector.error);
			console.error("Error al conectar : ");
		}
	});
}