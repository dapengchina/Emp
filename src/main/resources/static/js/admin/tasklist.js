$(function() {
	// 加载任务列表
	loadTaskList();
	datehandle();
	loadCourse();
	$("#thesaure").hide();
});


// 任务列表
function loadTaskList() {
	var queryUrl = '/Emp/admin/task/queryAll'
	var table = $('#tasklist').bootstrapTable({
		url : queryUrl, // 请求后台的URL（*）
		method : 'GET', // 请求方式（*）
		toolbar: '#toolbar', // 工具按钮用哪个容器 
		striped : true, // 是否显示行间隔色
		cache : false, // 是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
		pagination : true, // 是否显示分页（*）
		sortable : true, // 是否启用排序
		sortOrder : "asc", // 排序方式
		sidePagination : "server", // 分页方式：client客户端分页，server服务端分页（*）
		pageNumber : 1, // 初始化加载第一页，默认第一页,并记录
		pageSize : 10, // 每页的记录行数（*）
		pageList : [ 10, 25, 50, 100 ], // 可供选择的每页的行数（*）
		search : false, // 是否显示表格搜索
		strictSearch : false,
		showColumns : true, // 是否显示所有的列（选择显示的列）
		showRefresh : true, // 是否显示刷新按钮
		minimumCountColumns : 2, // 最少允许的列数
		clickToSelect : true, // 是否启用点击选中行
		// height: 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		uniqueId : "ID", // 每一行的唯一标识，一般为主键列
		showToggle : false, // 是否显示详细视图和列表视图的切换按钮
		cardView : false, // 是否显示详细视图
		detailView : false, // 是否显示父子表
		singleSelect:true, 				//禁止多选_____
		// 得到查询的参数
		queryParams : function(params) {
			return {
				pageSize : params.limit,
				pageNumber : params.offset / params.limit + 1
			};
		},
		columns : [ {
			checkbox : true,
			visible : true
		// 是否显示复选框tParamName
		}, {
			field : 'taskname',
			title : 'Taskname',
			sortable : true
		},{
			field : 'taskstatename',
			title : 'TaskState',
			sortable : true
		},{
			field : 'coursename',
			title : 'Course',
			sortable : true
		},{
			field : 'thesauresTypeName',
			title : 'Thesaure',
			sortable : true
		}, {
			field : 'startStringDate',
			title : 'StartDate',
			sortable : true
		}, {
			field : 'endStringDate',
			title : 'EndDate',
			sortable : true
		},{
			title : 'Operation',
			width : 120,
			align : 'center',
			valign : 'middle',
			formatter: function (value, row, index) {
				return  '<button type="button" onclick="deleteTask(\''+ row.id+ '\')" class="btn btn-danger glyphicon glyphicon-trash">' 
				+ '<button type="button" onclick="endTask(\''+ row.id+ '\')" class="btn btn-danger glyphicon glyphicon-off">' ;
			}
		}],
		onLoadSuccess : function() {
		},
		onLoadError : function() {
			
		},
		onDblClickRow : function(row, $element) {
			
		},
	});
}

function deleteTask(taskid){
	$.ajax({
		type : 'get',
		url :"/Emp/admin/userTask/getTask/"+taskid,
		dataType : 'json',
		success : function(result) {
			if(result.code=="0"){
				$("#taskid").val(taskid);
				$("#deletetask").modal('show');
			}else{
				alert(result.msg);
			}
		}
	});
}

function deleteSure(){
	var taskid = $("#taskid").val();
	$.ajax({
		type : 'get',
		url :"/Emp/admin/task/delete/"+taskid,
		dataType : 'json',
		success : function(result) {
			if(result.code=="1"){
				alert(result.msg);
				$('#tasklist').bootstrapTable('refresh');
				$("#deletetask").modal('hide');
			}else{
				alert(result.msg);
			}
		}
	});
}

function openTaskModal() {
	
	$("#taskadd").modal('show');
	$.ajax({
		type : 'get',
		url :"/Emp/admin/thesaure/querySelAll",
		dataType : 'json',
		success : function(datas) {//返回list数据并循环获取
			$("#thesauresIDAdd").empty();
			var select = $("#thesauresIDAdd");
			select.append("<option value='-1'>"+"--请选择--"+ "</option>");
			for (var i = 0; i < datas.length; i++) {
				select.append("<option value='"+datas[i].id+"'>"+ datas[i].name + "</option>");
			}
			$('.selectpicker').selectpicker('val', '');
			$('.selectpicker').selectpicker('refresh');
		}
	});
}

$('body').on('hidden.bs.modal', '.modal', function () {
    $(this).removeData('bs.modal');
});

function saveTask() {
	
		var taskName = $('#taskNameAdd').val();
		var paramid = $('#paramaddID').val();
		var thesauresType = $('#thesauresIDAdd option:selected').val();//选中的值
		var courseid = $('#course option:selected').val();//选中的值
		var startDate = $('#startDateAdd').val();
		var endDate = $('#endDateAdd').val();
		
		var bootstrapValidator = $("#addTaskForm").data('bootstrapValidator');
		bootstrapValidator.validate();
		
		if(bootstrapValidator.isValid()){
			//如果选择背单词,则必须选择词库
			if(courseid=='1'){
				if(thesauresType=='-1'){
					alert("请选择词库");
					return
				}
			}
			/**
			 * 验证通过之后调用计算任务量方法
			 */
			$.ajax({
				url : '/Emp/admin/task/calculateTaskCount',
	    		dataType:"json",
	    		data:{
	    			"thesaure":thesauresType,
	    			"startDate":startDate,
	    			"endDate":endDate},
	    		async:true,
	    		cache:false,
	    		type:"post",
				success : function(result) {
					$("#taskcountAdd").val(result);
					if(result != "-1"){
						$.ajax({
							url : '/Emp/admin/task/add',
				    		dataType:"json",
				    		data:{"taskname":taskName,
				    			"thesauresType":thesauresType,
				    			"courseid":courseid,
				    			"startStringDate":startDate,
				    			"endStringDate":endDate},
				    		async:true,
				    		cache:false,
				    		type:"post",
							success : function(result) {
								if(result.code == "1"){
									alert(result.msg);
									$('#tasklist').bootstrapTable('refresh');
									$("#addTaskForm").bootstrapValidator('resetForm');
									$("#taskadd").modal('hide');
				                }else{
				                	alert(result.msg);
				                }
							},
							error : function() {
					
							},
							complete : function() {
								
							}
						});
	                }
				},
				error : function() {
		
				},
				complete : function() {
					
				}
			});
		}
}

function datehandle(){
	$('.form_datetime').datetimepicker({
		format: 'yyyy-mm-dd',//显示格式
		todayHighlight: 1,//今天高亮
		minView: "month",//设置只显示到月份
		startView:2,
		forceParse: 0,
		showMeridian: 1,
		autoclose: 1//选择后自动关闭
	}).on('changeDate', function(ev){
		 $('#addTaskForm').bootstrapValidator('revalidateField', 'startDateAdd');
		 $('#addTaskForm').bootstrapValidator('revalidateField', 'endDateAdd');
	});;
}

//初始化子表格(无线循环)
function initSubTable(index, row, $detail) {
    var parentid = row.id;
    var cur_table = $detail.html('<table></table>').find('table');
    $(cur_table).bootstrapTable({
        url: '/Emp/admin/sysparam/queryChild',
        method: 'get',
        queryParams: {id:parentid},
        ajaxOptions: {id:parentid},
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
        singleSelect:true, 				//禁止多选_____
        columns: [{
        	checkbox: true,  
            visible: true  
        }, {
            field: 'childName',
            title: 'Name'
        }, {
            field: 'childValue',
            title: 'Value'
        }],
        //无线循环取子表，直到子表里面没有记录
        onExpandRow: function (index, row, $Subdetail) {
        	initSubTable(index, row, $Subdetail);
        },
        onClickRow: function (row,  $element) {
        	$("#taskcountAdd").val(row.childValue);
        	$("#paramaddID").val(row.id);
        }
    });
    $("#cur_table").bootstrapTable('refresh');
}

function search(){
	//获取查询条件
	var taskName = $("#taskNameSearch").val();
	var queryParams = { 
		query: {  
			taskname:taskName
        }
    }  
	//刷新表格  
    $('#tasklist').bootstrapTable('refresh',queryParams);  
}

function loadCourse(){
	$.ajax({
		type : 'get',
		url :"/Emp/admin/scecategory/getScecategory",
		dataType : 'json',
		success : function(datas) {//返回list数据并循环获取
			$("#course").empty();
			var select = $("#course");
			select.append("<option value='-1'>"+"--请选择--"+ "</option>");
			for (var i = 0; i < datas.length; i++) {
				select.append("<option value='"+datas[i].id+"'>"+ datas[i].scecatname + "</option>");
			}
			$('.selectpicker').selectpicker('val', '');
			$('.selectpicker').selectpicker('refresh');
		}
	});
}

function courseChange(){
	var courseid = $('#course option:selected').val();//选中的值
	//如果选择的是背单词,则显示词库选择框
	if(courseid=="1"){
		$("#thesaure").show();
	}else{
		$("#thesaure").hide();
	}
}

//结束任务
function endTask(taskid){
	if(confirm("确认要结束任务吗?")){
		$.ajax({
			type : 'get',
			url :"/Emp/admin/task/endTask/"+taskid,
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
