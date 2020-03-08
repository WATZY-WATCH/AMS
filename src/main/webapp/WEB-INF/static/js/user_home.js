const mainMenus = document.querySelectorAll(".main-menu");

for(let menu of mainMenus) {
	menu.onclick = function(e) {
		location.href = e.target.children[0].href;
	}
}