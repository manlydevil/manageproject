var getUrl = window.location;
var baseUrl = getUrl .protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[1];

function editClass(name) {
	name = name.substring(0, name.length - 1);
	$('#textClass').val(name);
} 

function editSubject(name) {
	name = name.substring(0, name.length - 1);
	$('#textSubject').val(name);
} 

function deleteClass(classID) {
	var msg = $("#msg_delete").val();
	idVal = classID.substring(9, classID.length - 1);
	var result = confirm(msg);
	if(result){
		$('#classID').val(idVal);
		$('#mainClass').attr("action",baseUrl+"/DeleteClass.do");
		$('#mainClass').submit();
	}	
	return false;
}

function deleteSubject(subjectID) {
	var msg = $("#msg_delete").val();
	idVal = subjectID.substring(11, subjectID.length - 1);
	var result = confirm(msg);
	if(result){
		$('#subjectID').val(idVal);
		$('#mainSubject').attr("action",baseUrl+"/DeleteSubject.do");
		$('#mainSubject').submit();
	}	
	return false;
}

function setTBClass(id) {
	var id = id.substring(4, id.length - 1);
	$.ajax({
		type :  "GET",
		contentType : "application/json",
		url : baseUrl+"/setClassTB.do",
		data : {
			id : id
		},
		dataType : 'json',
		success : function(json) {
			$('#className').val(json.className);
			$('#classID').val(json.classID);
			$('#id').text(json.classID);
		},
		error: function(ts) { alert(ts.responseText) }
	})
}
function refreshClassTB() {
	$('#className').val('');
	$('#classID').val(0);
	$("#id").text($('#add').val());
}

function refreshSubjectTB() {
	$('#subjectName').val('');
	$('#subjectID').val(0);
	$('#subjectCode').val('');
	$("#sid").text($('#add').val());
}

function setTBSubject(id) {
	var id = id.substring(4, id.length - 1);
	$.ajax({
		type :  "GET",
		contentType : "application/json",
		url : baseUrl+"/setSubjectTB.do",
		data : {
			id : id
		},
		dataType : 'json',
		success : function(json) {
			$('#subjectID').val(json.subjectID);
			$('#subjectCode').val(json.subjectCode);
			$('#subjectName').val(json.subjectName);
			$('#sid').text(json.subjectID);
		},
		error: function(ts) { alert(ts.responseText) }
	})
}

function classPaging(page) {
	window.location.href = baseUrl+"/class/"+page;
}

function subjectPaging(page) {
	window.location.href = baseUrl+"/subject/"+page;
}