function checkInput() {
	if(frm.code.value == ""){
		alert("제품코드를 입력해주세요.");
		frm.code.focus();
		return false;
	}
	
	if(frm.pname.value == ""){
		alert("제품이름을 입력해주세요.");
		frm.pname.focus();
		return false;
	}
	
	if(frm.cost.value == ""){
		alert("제품원가를 입력해주세요.");
		frm.cost.focus();
		return false;
	}
	
	if(frm.pnum.value == ""){
		alert("목표수량을 입력해주세요.");
		frm.pnum.focus();
		return false;
	}
	
	if(frm.jnum.value == ""){
		alert("재고수량을 입력해주세요.");
		frm.jnum.focus();
		return false;
	}
	
	if(frm.sale.value == ""){
		alert("출고가를 입력해주세요.");
		frm.sale.focus();
		return false;
	}
	
	return true;
}

function checkinputCode(){
	if(frm.code.value == ""){
		alert("제품코드를 입력해주세요.");
		frm.code.focus();
		return false;
	}
	return true;
}