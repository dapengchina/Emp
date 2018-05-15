$(function () {
	$(document).keydown(function(event){ 
		if(event.keyCode==13){ 
			login();
		}	 
	});
});


//登录
function login(){
	var bootstrapValidator = $("#loginForm").data('bootstrapValidator');
	bootstrapValidator.validate();
	if(bootstrapValidator.isValid()){
		//$("#jindu").modal('show');
		//获取用户名
		var name = $("#username").val();
		//获取密码
		var pwd = $("#pwd").val();
		$.ajax({
			url:'/Emp/admin/login',
			dataType:"json",
			data:{"adminName":name,"adminPwd":pwd},
			async:true,
			cache:false,
			type:"post",
			success:function(result){
				//$("#jindu").modal('hide');
				if(result=="0"){
					var uri = "/Emp/admin/main";
					window.location.href = uri;
				}else{
					$("#loginTip").show();
					$("#loginTip").html("<strong>Info:</strong>"+result);
				}
			}
		})
	}
}