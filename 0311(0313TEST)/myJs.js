function send() {

	if (frm.bun.value == "") {
		alert("대출번호를 입력해주세요");
		return;
	}else if(!/^[0-9]{4}$/.test(frm.bun.value)){
		frm.bun.value = "";
		frm.bun.focus();
		alert("대출번호는 정수 4자리입니다.");
		return;
	}

	if(frm.name.value == ""){
		alert("이름을 입력해주세요.");
		return;
	}

	if(frm.tel.value == ""){
		alert("전화번호를 입력해주세요.");
		return;
	}

	if(frm.good.value == ""){
		alert("성별을 입력해주세요.");
		return;
	}

	if(frm.code.value == ""){
		alert("도서코드를 입력해주세요.");
		return;
	}

	if(frm.date.value == ""){
		alert("대출일자를 입력해주세요");
		return;
	}else if(/^[0-9]{4}-(0[1-9]|1[0-2])-[0-9]{2}$/.test(frm.date.value) ||
						/^[0-9]{4}(0[1-9]|1[1-2])[0-9]{2}$/.test(frm.date.value)){

	}else{
		alert("날짜를 제대로 입력");
		return;
	}
	
	alert("도서 대출 정보가 등록되었습니다.");
	location.href="my.html";
}
	
function resetForm() {
	alert("입력된 모든항목을 삭제합니다.");
	frm.reset();
}

