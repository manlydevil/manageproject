var getUrl = window.location;
var baseUrl = getUrl .protocol + "//" + getUrl.host + "/" + getUrl.pathname.split('/')[1];

function changePublic(projectID, teacherPublic) {
	if(teacherPublic == "0") {
		teacherPublic = "1"
	} else {
		teacherPublic = "0";
	}
	$.ajax({
		type :  "GET",
		contentType : "application/json",
		url : baseUrl+"/ChangePublic.tc",
		data : {
			pID : projectID,
			p : teacherPublic
		},
		dataType : 'json',
		success : function(json) {
			alert("Thành công!");
		},
	})
}

$(function(){	
	mainListStu = $('#formListStudent');
	mainListPro = $('#formListProject');
})
function clickPaging(page) {
	mainListStu.attr("action",baseUrl+"/ListStudent/page/"+page);
	mainListStu.submit();
}


function clickPagingPro(page) {
	mainListPro.attr("action", baseUrl+"/ListProject/page/"+page);
	mainListPro.submit();
}

function deleteStudent(studentID) {
	var msg = $("#msg_delete").val();
	idVal = studentID.substring(4, studentID.length - 1);
	var result = confirm(msg);
	if(result){
		$("#formListStudent").attr("action",baseUrl+"/DeleteStudent.do");
		$("#formListStudent").find("input[name != 'studentNumber']").attr("disabled", "true");
		$("#formListStudent").find("input[name = 'studentNumber']").val(idVal);
		$("#formListStudent").submit();
	}	
	return false;
} 

function deleteConfig(ID) {
	var msg = $("#msg_delete").val();
	idVal = ID.substring(4, ID.length - 1);
	var result = confirm(msg);
	if(result){
		$("#formListConfig").find("input[name = 'configID']").val(idVal);
		$("#formListConfig").submit();
	}	
	return false;
}

function deleteProject(ID) {
	var msg = $("#msg_delete").val();
	idVal = ID.substring(4, ID.length - 1);
	var result = confirm(msg);
	if(result){
		$("#formListProject").attr("action",baseUrl+"/DeleteProject.do");
		$("#formListProject").find("input[name != 'projectID']").attr("disabled", "true");
		$("#formListProject").find("input[name = 'projectID']").val(idVal);
		$("#formListProject").submit();
	}	
	return false;
}

function deleteTeacher(ID) {
	var msg = $("#msg_delete").val();
	idVal = ID.substring(4, ID.length - 1);
	var result = confirm(msg);
	if(result){
		$("#formListTeacher").attr("action",baseUrl+"/DeleteTeacher.do");
		$("#formListTeacher").find("input[name != 'teacherID']").attr("disabled", "true");
		$("#formListTeacher").find("input[name = 'teacherID']").val(idVal);
		$("#formListTeacher").submit();
	}	
	return false;
}

function downloadListStudent() {
	mainListStu.attr("action",baseUrl+"/DownloadListStudent.do");
	mainListStu.submit();
}

function downloadListProject() {
	mainListPro.attr("action",baseUrl+"/DownloadListProject.do");
	mainListPro.submit();
}