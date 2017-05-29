var getUrl = window.location;
var baseUrl = getUrl .protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[1];


function deleteTerm(id) {
	var msg = $("#msg_delete").val();
	idVal = id.substring(4, id.length - 1);
	var result = confirm(msg);
	if(result){
		$('#termID').val(idVal);
		$('#mainTerm').attr("action",baseUrl+"/DeleteTerm.do");
		$('#mainTerm').submit();
	}	
	return false;
}

function deleteEduProgram(id) {
	var msg = $("#msg_delete").val();
	idVal = id.substring(4, id.length - 1);
	var result = confirm(msg);
	if(result){
		$('#eduProgramID').val(idVal);
		$('#mainEduProgram').attr("action",baseUrl+"/DeleteEduProgram.do");
		$('#mainEduProgram').submit();
	}	
	return false;
}

function setTBTerm(id) {
	var id = id.substring(4, id.length - 1);
	$.ajax({
		type :  "GET",
		contentType : "application/json",
		url : baseUrl+"/setTermTB.do",
		data : {
			id : id
		},
		dataType : 'json',
		success : function(json) {
			$('#termNumber').val(json.termNumber);
			$('#termID').val(json.termID);
			$('#id').text(json.termID);
		},
		error: function(ts) { alert(ts.responseText) }
	})
}
function refreshTermTB() {
	$('#termNumber').val('');
	$('#termID').val(0);
	$("#id").text($('#add').val());
}

function refreshEduProgramTB() {
	$('#eduProgramID').val(0);
	$('#eduProgramName').val('');
	$("#sid").text($('#add').val());
}

function setTBEduProgram(id) {
	var id = id.substring(4, id.length - 1);
	$.ajax({
		type :  "GET",
		contentType : "application/json",
		url : baseUrl+"/setEduProgramTB.do",
		data : {
			id : id
		},
		dataType : 'json',
		success : function(json) {
			$('#eduProgramID').val(json.eduProgramID);
			$('#eduProgramName').val(json.eduProgramName);
			$('#sid').text(json.eduProgramID);
		},
		error: function(ts) { alert(ts.responseText) }
	})
}

function termPaging(page) {
	window.location.href = baseUrl+"/term/"+page;
}

function eduProgramPaging(page) {
	window.location.href = baseUrl+"/eduprogram/"+page;
}