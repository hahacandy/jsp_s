window.onload = function () {
	autoInputDate();
}

function autoInputDate() {
	var today = new Date();
	
	var today_year = today.getFullYear();
	var today_month = today.getMonth()+1;
	var today_day = today.getDate();
	
	var today = today_year + "" + today_month + "" + today_day;	
	
	frm.regdate.value = today;
}

function resetForm() {
	frm.reset();
	autoInputDate();
}

function writeForm() {
	
	if(frm.bun.value == "") {
		alert("번호를 입력해주세요");
		return;
	}else if(!/^[0-9]{4}$/.test(frm.bun.value)){
		alert("번호를 제대로 입력해주세요");
		return;
	}
	
	if(frm.name.value == "") {
		alert("이름을 입력해주세요");
		return;
	} 
	
	
	var genderChecked = false;
	for(var i=0; i<frm.gender.length; i++){
		if(frm.gender[i].checked){
			genderChecked = true;
		}
	}
	if(!genderChecked){
		alert("성별을 선택해주세요.");
		return;
	}
	
	if(frm.kor.value == "") {
		alert("국어점수를 입력해주세요.");
		return;
	}else if(frm.kor.value > 100 || frm.kor.value < 0){
		alert("국어점수의 입력이 잘못되었습니다.");
		return;
	}
	
	if(frm.eng.value == "") {
		alert("영어점수를 입력해주세요.");
		return;
	}else if(frm.eng.value > 100 || frm.kor.value < 0){
		alert("영어점수의 입력이 잘못되었습니다.");
		return;
	}
	
	if(frm.mat.value == "") {
		alert("수학점수를 입력해주세요.");
		return;
	}else if(frm.mat.value > 100 || frm.kor.value < 0){
		alert("수학점수의 입력이 잘못되었습니다.");
		return;
	}
	
	
	frm.submit();
}