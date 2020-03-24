function signoutChk() {
    var elementToken = document.querySelector('meta[name="_csrf"]');
    var token = elementToken && elementToken.getAttribute("content");
    var elementHeader = document.querySelector('meta[name="_csrf_header"]');
    var header = elementHeader && elementHeader.getAttribute("content");
    
    let ret = confirm('카카오 계정과 서비스 연동이 해제됩니다.\n연동 해제 이후 데이터 복구가 불가능합니다.');
    if(ret) {
    	const xhr = new XMLHttpRequest();
    	xhr.open("DELETE", "/oauth/user/", true);
    	xhr.setRequestHeader(header, token);
    	xhr.send();
    	
    	xhr.onload = function () {
    		if(xhr.status == 200 || xhr.status == 201) {
    			alert("정상적으로 연동해제 되었습니다.");
    			location.href = "/";
    		} else {
    			alert("에러가 발생했습니다. 잠시 후 다시 시도해주세요.");
    		}
    	}
    }
}