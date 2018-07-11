$(function () {
	$('iframe').attr('src','/Emp/admin/content'); 
});

function adminList(){
	$('iframe').attr('src','/Emp/admin/adminuser'); 
}

function userList(){
	$('iframe').attr('src','/Emp/admin/user'); 
}

function taskList(){
	$('iframe').attr('src','/Emp/admin/task'); 
}

function wordList(){
	$('iframe').attr('src','/Emp/admin/word');
}

function sysParamList(){
	$('iframe').attr('src','/Emp/admin/sysparam');
}

function reportList(){
	$('iframe').attr('src','/Emp/admin/userreport');
}

function thesaureList(){
	$('iframe').attr('src','/Emp/admin/thesaure');
}