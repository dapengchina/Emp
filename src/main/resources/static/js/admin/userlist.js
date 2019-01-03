$(function () {
	//加载用户列表
    loadUserList();
    //验证
    validate();
    loadTutor();
});

//查找用户
function userSearch(){
	var userName = $("#txt_search_departmentname").val();
    var phone = $("#txt_search_phone").val();
    var tutor = $("#search_tutor").val();
	var queryParams = { 
		query: {  
			userName:userName,
			phone:phone,
			tutor:tutor
        }
    }  
	//刷新表格  
    $('#userlist').bootstrapTable('refresh',queryParams);  
} 	

//用户列表
function loadUserList(){
    var queryUrl = '/Emp/admin/user/queryAll'
    var table = $('#userlist').bootstrapTable({
        url: queryUrl,                      //请求后台的URL（*）
        method: 'GET',                      //请求方式（*）
        toolbar: '#toolbar',              //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: 10,                     //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                      //是否显示表格搜索
        strictSearch: false,				//精确搜索
        showColumns: true,                  //是否显示所有的列（选择显示的列）
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: true,                  //是否显示父子表
        singleSelect:false, 				//禁止多选_____
        //得到查询的参数
        queryParams : function (params) {
        	return {
        		pageSize: params.limit,
        		pageNumber: params.offset/params.limit+1,
            };
        },
        columns: [{
            checkbox: true,  
            visible: true                  //是否显示复选框  
        },{
            field: 'userName',
            title: 'Name',
            sortable: true
        },{
            field: 'tutor',
            title: 'Tutor',
            sortable: true
        },{
            field: 'phone',
            title: 'Phone',
            sortable: true
        },{
            field: 'code',
            title: 'Code',
            sortable: true
        }],
        onLoadSuccess: function () {
        },
        onLoadError: function () {
            
        },
        onDblClickRow: function (row, $element) {
           
        },
        //注册加载子表的事件。注意下这里的三个参数！
        onExpandRow: function (index, row, $detail) {
            initSubTable(index, row, $detail);
        }
    });
}

//初始化子表格(无线循环)
function initSubTable(index, row, $detail) {
    var userid = row.id;
    var cur_table = $detail.html('<table></table>').find('table');
    $(cur_table).bootstrapTable({
        url: '/Emp/admin/userTask/getUserTask',
        method: 'get',
        ajaxOptions: {userid:userid},
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: 10,                     //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                      //是否显示表格搜索
        strictSearch: false,				//精确搜索
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: true,                  //是否显示父子表
        singleSelect:false, 				//禁止多选_____
        //得到查询的参数
		queryParams : function(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset / params.limit + 1,
				userid:userid
			};
		},
        columns: [
        {
			title: 'Ln',//标题  可不加
			formatter: function (value, row, index) {
				return index+1;
			}
        },
        {
			field : 'taskname',
			title : 'TaskName',
			sortable : true
		},{
			field : 'taskstateName',
			title : 'TaskState',
			sortable : true
		},{
			field : 'coursename',
			title : 'Course',
			sortable : true
		},{
			field : 'startDate',
			title : 'StartDate',
			sortable : true
		},{
			field : 'endDate',
			title : 'EndDate',
			sortable : true
		},{
			field : 'completepercent',
			title : 'CompletePercent',
			sortable : true,
			formatter: function (value, row, index) {
            	return value+"%";
            }
		}, {
			field : 'thesauresTypeName',
			title : 'Thesaures',
			sortable : true
		},{
			title : 'Operation',
			width : 120,
			align : 'center',
			valign : 'middle',
			formatter: function (value, row, index) {
				return [ '<button type="button" onclick="removeTask(\''+ row.userid+ '\',\''+ row.taskid+ '\')" class="btn btn-danger glyphicon glyphicon-trash"></button>'+"&nbsp;"+'<button type="button" onclick="endTask(\''+ row.userid+ '\',\''+ row.taskid+ '\')" class="btn btn-danger glyphicon glyphicon-off"/>' ];
			}
		}],
		//注册加载子表的事件。注意下这里的三个参数！
        onExpandRow: function (index, row, $detail) {
            loadUserSubTask(index, row, $detail);
        }
    });
    $("#cur_table").bootstrapTable('refresh');
}

function loadUserSubTask(index, row, $detail){
	var userid = row.userid;
	var taskid = row.taskid;
	var dropletid = row.dropletid;
	var dropletconftypeid = row.dropletconftypeid;
	var index = row.index;
	
    var cur_table = $detail.html('<table></table>').find('table');
    $(cur_table).bootstrapTable({
        url: '/Emp/admin/usersubtask/getUserSubTask',
        method: 'post',
        ajaxOptions: {
        	userid:userid,
        	taskid:taskid,
        	dropletid:dropletid,
        	dropletconftypeid:dropletconftypeid,
        	index:index
        },
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: 10,                     //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                      //是否显示表格搜索
        strictSearch: false,				//精确搜索
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: true,                  //是否显示父子表
        singleSelect:false, 				//禁止多选_____
        //得到查询的参数
		queryParams : function(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset / params.limit + 1,
				userid:userid,
				taskid:taskid,
	        	dropletid:dropletid,
	        	dropletconftypeid:dropletconftypeid,
	        	index:index
			};
		},
        columns: [
        {
			title: 'Ln',//标题  可不加
			formatter: function (value, row, index) {
				return index+1;
			}
        },
        {
			field : 'name',
			title : 'SubTaskName',
			sortable : true
		},{
			field : 'averageScore',
			title : 'AverageScore',
			sortable : true,
			formatter: function (value, row, index) {
				return "<div><Strong><font color='green'>"+value+"</font></Strong></div>";
			}
		},{
			field : 'completePercent',
			title : 'CompletePercent',
			sortable : true,
			formatter: function (value, row, index) {
				return "<div><Strong><font color='green'>"+value+"%</font></Strong></div>";
			}
		},{
			field : 'star',
			title : 'Star',
			sortable : true,
			formatter: function (value, row, index) {
				return "<div><img class='star' src='/Emp/image/star.png'></img><Strong><font color='green'>"+row.currentPoint+"/"+row.totalPoint+"</font></Strong></div>";
			}
		}],
		//注册加载子表的事件。注意下这里的三个参数！
        onExpandRow: function (index, row, $detail) {
            loadUserSubTask2(index, row, $detail);
        }
    });
    $("#cur_table").bootstrapTable('refresh');
}

function loadUserSubTask2(index, row, $detail){
	var userid = row.userid;
	var taskid = row.taskid;
	var dropletid = row.dropletid;
	var dropletconftypeid = row.dropletconftypeid;
	var index = row.index;
	
    var cur_table = $detail.html('<table></table>').find('table');
    $(cur_table).bootstrapTable({
        url: '/Emp/admin/usersubtask/getUserSubTask',
        method: 'post',
        ajaxOptions: {
        	userid:userid,
        	taskid:taskid,
        	dropletid:dropletid,
        	dropletconftypeid:dropletconftypeid,
        	index:index
        },
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: 10,                     //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                      //是否显示表格搜索
        strictSearch: false,				//精确搜索
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        singleSelect:false, 				//禁止多选_____
        //得到查询的参数
		queryParams : function(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset / params.limit + 1,
				userid:userid,
				taskid:taskid,
	        	dropletid:dropletid,
	        	dropletconftypeid:dropletconftypeid,
	        	index:index
			};
		},
        columns: [
        {
			title: 'Ln',//标题  可不加
			formatter: function (value, row, index) {
				return index+1;
			}
        },
        {
			field : 'name',
			title : 'SubTaskName',
			sortable : true
		},{
			field : 'score',
			title : 'Score',
			sortable : true,
			formatter: function (value, row, index) {
				if(row.ifpassname=='未通过'){
					return "<div><Strong><font color='red'>"+value+"</font></Strong></div>";
				}
				if(row.ifpassname=='通过'){
					return "<div><Strong><font color='green'>"+value+"</font></Strong></div>";
				}
			}
		},{
			field : 'ifpassname',
			title : 'Ifpass',
			sortable : true,
			formatter: function (value, row, index) {
				if(value=='未通过'){
					return "<div><Strong><font color='red'>未通过</font></Strong></div>";
				}
				if(value=='通过'){
					return "<div><Strong><font color='green'>通过</font></Strong></div>";
				}
			}
		},{
			field : 'passscore',
			title : 'Passscore',
			sortable : true
		}],
		//注册加载子表的事件。注意下这里的三个参数！
        onExpandRow: function (index, row, $detail) {
            //loadUserSubTask2(index, row, $detail);
        }
    });
    $("#cur_table").bootstrapTable('refresh');
}


function openImportPage(){
	$("#uimport").modal('show');
}

function validate(){
	$('#upuserfile').bootstrapValidator({
	　　　　message: 'This value is not valid',
	      　     feedbackIcons: {
	        　　　　　　　　valid: 'glyphicon glyphicon-ok',
	        　　　　　　　　invalid: 'glyphicon glyphicon-remove',
	        　　　　　　　　validating: 'glyphicon glyphicon-refresh'
	      　　　　　　　　  },
	      fields: {
	    	  file: {
	          validators: {
	            notEmpty: {
	              message: 'Please Choose File'
	            }
	          }
	        }
	      }
	});
}

function openAppointPage(){
	//获取所选用户
	var userRows = $("#userlist").bootstrapTable('getSelections');
	var user = [];
	for(k in userRows){
	    user.push(userRows[k].id);
	}
	var id = user;
	if(id != "" && id != null ){
		$("#appointTask").modal('show');
		loadTaskList();
//		$.ajax({
//			url:'/Emp/admin/user/verifyUserAppoint',
//			dataType:"json",
//			data:{"id":user},
//			async:true,
//			cache:false,
//			type:"post",
//			success:function(result){
//				if(result == "1"){
//					alert("用户已经存在指派任务");
//				}else{
//					$("#appointTask").modal('show');
//					loadTaskList();
//				}
//			}
//		});
	}else{
		alert("请选择需要指派任务的用户");
	}
}

//任务列表
function loadTaskList(){
    var queryUrl = '/Emp/admin/task/queryAll'
    var table = $('#tasklist').bootstrapTable({
        url: queryUrl,                      //请求后台的URL（*）
        method: 'GET',                      //请求方式（*）
        //toolbar: '#toolbar',              //工具按钮用哪个容器
        singleSelect:true,                  //禁止多选
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,                   //是否显示分页（*）
        sortable: true,                     //是否启用排序
        sortOrder: "asc",                   //排序方式
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
        pageSize: 10,                     //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        //search: true,                      //是否显示表格搜索
        strictSearch: true,
        showColumns: true,                  //是否显示所有的列（选择显示的列）
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        //得到查询的参数
        queryParams : function (params) {
        	return {
        		pageSize: params.limit,
        		pageNumber: params.offset/params.limit+1,
        		taskstate:0
            };
        },
        columns: [{
            checkbox: true,  
            visible: true                  //是否显示复选框  
        }, {
            field: 'taskname',
            title: 'Taskname',
            sortable: true
        },{
            field: 'taskstatename',
            title: 'TaskState',
            sortable: true
        },{
			field : 'thesauresTypeName',
			title : 'Thesaure',
			sortable : true
		},{
            field: 'startStringDate',
            title: 'StartDate',
            sortable: true
        }, {
            field: 'endStringDate',
            title: 'EndDate',
            sortable: true
        }],
        onLoadSuccess: function () {
        },
        onLoadError: function () {
            
        },
        onDblClickRow: function (row, $element) {
           
        },
    });
}

function appoint(){
	//获取所选用户
	var userRows = $("#userlist").bootstrapTable('getSelections');
	var taskRows = $("#tasklist").bootstrapTable('getSelections');
	var user = "";
	var task = [];
	for(k in userRows){
		user = user + userRows[k].id+",";
	}
	for(j in taskRows){
		task.push(taskRows[j].id);
	}
	var id = user;
	var taskid = task[0];
	
	if ("" != id && typeof(taskid) != "undefined") {
		$.ajax({
			url:'/Emp/admin/user/taskAppoint',
			dataType:"json",
			data:{"ids":id,"taskid":taskid},
			async:true,
			cache:false,
			type:"post",
			success:function(result){
				if(result=='0'){
					alert("任务指派成功");
				}else{
					alert("任务指派失败");
				}
				$("#appointTask").modal('hide');
				$("#tasklist").bootstrapTable('refresh');
				$("#userlist").bootstrapTable('refresh');
			}
		});
		
	}
	else{
		alert("没有选择用户或任务");
	}
}

function loadTutor(){
	$.ajax({
		type : 'get',
		url :"/Emp/admin/adminuser/getTutor",
		dataType : 'json',
		success : function(data) {
			$("#search_tutor").empty();
			$("#search_tutor").append("<option value=''>--Option--</option>");
		    $.each(data, function(i, item) {
		    	   $("#search_tutor").append("<option value='"+data[i].adminName+"'>"+ data[i].adminName + "</option>");
		    })
		}
	});
}

function removeTask(userid,taskid){
	$("#userid").val(userid);
	$("#taskid").val(taskid);
	$("#removetask").modal('show');
}

function removeSure(){
	var userid = $("#userid").val();
	var taskid = $("#taskid").val();
	$.ajax({
		type : 'get',
		url :"/Emp/admin/userTask/deleteUserTask/"+userid+"/"+taskid,
		dataType : 'json',
		success : function(result) {
			if(result.code=="1"){
				alert(result.msg);
				$('#userlist').bootstrapTable('refresh');
				$("#removetask").modal('hide');
			}else{
				alert(result.msg);
			}
		}
	});
}


//结束任务
function endTask(userid,taskid){
	if(confirm("确认要结束任务吗?")){
		$.ajax({
			type : 'get',
			url :"/Emp/admin/task/endUserTask/"+userid+"/"+taskid,
			dataType : 'json',
			success : function(result) {
				if(result.code=="1"){
					alert(result.msg);
					$('#tasklist').bootstrapTable('refresh');
				}else{
					alert(result.msg);
				}
			}
		});
	}else{
		//alert("不结束");
	}
}