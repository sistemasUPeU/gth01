function connect(type, url, data) {
	console.log("hay algo");
    var csrfHeader = $("meta[name='_csrf_header']").attr("content");
    var csrfToken = $("meta[name='_csrf']").attr("content");
    $.ajax({
        url: gth_context_path + url,
        type: type,
        contentType: 'application/json',
        data: data,
        dataType: 'json',
        timeout: 2000,
        beforeSend: function (xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function (result) {
            try {
            	console.log(result);
                callback = result;
            } catch (e) {
                console.log(e);
            }
        },
        error: function (e) {
        	console.log(e);
            callback = false;
        }
    });
    return callback;
}