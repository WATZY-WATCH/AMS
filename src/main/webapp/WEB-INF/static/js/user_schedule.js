const scheduleTable = document.querySelectorAll(".schedule > ul");
const listChilds = document.querySelectorAll(".schedule-list > li");

for(let i=0; i<listChilds.length; i++) {
	let cls = listChilds[i].classList;
	let regExp = /schedule-[0-9]/;
	for(let j=0; j<cls.length; j++) {
		if(regExp.test(cls[j])) {
			let idx = cls[j].split("-")[1];
			scheduleTable[idx].appendChild(listChilds[i]);
		}
	}
}