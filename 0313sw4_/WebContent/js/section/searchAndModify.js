window.onload = function () {
		document.getElementById("subject").innerHTML = "생산관리 조회 & 수정 화면";
	}
	
	function searchProduct() {
		if(checkinputCode()){
			frm.mode.value = "search";
			frm.submit();
		}
	}
	
	function modifyProduct() {
		if(checkInput()){
			frm.mode.value = "modify";
			frm.submit();
		}
	}
	
	function deleteProduct() {
		
		var result = confirm("해당 제품을 삭제할까요?");
		
		if(result){
			if(checkinputCode()){
				frm.mode.value = "delete";
				frm.submit();
			}
		}
	}