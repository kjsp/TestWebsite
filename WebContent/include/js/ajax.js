var xhr;

function startAjax(param, callback) {
	xhr = new XMLHttpRequest();
	// callback 함수 지정
	xhr.onreadystatechange = callback;
	xhr.open("post", "DispatcherServlet", true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send(param);
}