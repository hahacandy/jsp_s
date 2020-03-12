	window.onload = function () {
		autoInputDate();
	}
	
	function autoInputDate() {
		var today = new Date();
		
		var today_year = today.getFullYear();
		var today_month = today.getMonth()+1;
		var today_day = today.getDate();
		
		var today = today_year + "" + today_month + "" + today_day;	
		
		frm.date.value = today;
	}
	
	function resetForm() {
		frm.reset();
		autoInputDate();
	}