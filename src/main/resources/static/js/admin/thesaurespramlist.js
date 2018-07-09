$(function () {
	//加载词库参数列表
	loadTheSauresPramList();
});

//词库参数列表
function loadTheSauresPramList(){
    var queryUrl = '/Emp/admin/thesauresParamlist/queryAllByPage'
    var table = $('#thesauresPramList').bootstrapTable({
		url : queryUrl, // 请求后台的URL（*）
		method : 'GET', // 请求方式（*）
		toolbar : '#toolbar', // 工具按钮用哪个容器 
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
		strictSearch : true,
		showColumns : true, // 是否显示所有的列（选择显示的列）
		showRefresh : true, // 是否显示刷新按钮
		minimumCountColumns : 2, // 最少允许的列数
		clickToSelect : true, // 是否启用点击选中行
		// height: 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		uniqueId : "ID", // 每一行的唯一标识，一般为主键列
		showToggle : false, // 是否显示详细视图和列表视图的切换按钮
		cardView : false, // 是否显示详细视图
		detailView : false, // 是否显示父子表
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
			field : 'name',
			title : 'Name',
			sortable : true
		}, {
			field : 'remark',
			title : 'Remark',
			sortable : true
		}],
		onLoadSuccess : function() {
		},
		onLoadError : function() {
			
		},
		onDblClickRow : function(row, $element) {
			
		},
	});
}

function openWordModal() {
	var row= $("#thesauresPramList").bootstrapTable('getSelections');
	var thesauresType;
	if(row.length==1){
	     thesauresType=row[0].id;
	     $("#thesauresType").val(thesauresType);
	}else{
		alert("请选中一行");
		return;
	}
	$("#wordAdd").modal('show');
	loadWordList(thesauresType);
}

function loadWordList(id) {
	$("#thesauresType").val(id);
	var queryUrl = '/Emp/admin/word/queryAll';
	var table = $('#wordlist').bootstrapTable({
		url : queryUrl, // 请求后台的URL（*）
		method : 'GET', // 请求方式（*）
		toolbar : '#toolbar2', // 工具按钮用哪个容器
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
		strictSearch : true,
		showColumns : true, // 是否显示所有的列（选择显示的列）
		showRefresh : true, // 是否显示刷新按钮
		minimumCountColumns : 2, // 最少允许的列数
		clickToSelect : true, // 是否启用点击选中行
		// height: 500, //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
		uniqueId : "id", // 每一行的唯一标识，一般为主键列
		showToggle : false, // 是否显示详细视图和列表视图的切换按钮
		cardView : false, // 是否显示详细视图
		detailView : false, // 是否显示父子表
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
		// 是否显示复选框
		}, {
			field : 'word',
			title : 'Word',
			sortable : true
		}, {
			field : 'interpretation',
			title : 'Interpretation',
			sortable : true
		}/*, {
			title : 'Operation',
			width : 120,
			align : 'center',
			valign : 'middle',
			formatter : formatOperat
		}, */],
		onLoadSuccess : function() {
		},
		onLoadError : function() {
			
		},
		onDblClickRow : function(row, $element) {
			
		},
	});
}

/*function formatOperat(value, row, index) {
	return [ '<button type="button" onclick="return openEditWord(\''+ row.id+ '\')" class="btn btn-warning glyphicon glyphicon-pencil">Edit</button>' ];
}*/

function save(){
	var thesauresType=$("#thesauresType").val();
	var rows= $("#wordlist").bootstrapTable('getSelections');
	var ids=new Array();
	for(var key in rows){
		var id=rows[key].id;
		ids.push(id);
	}
	$.ajax({            	
		url:'/Emp/admin/word/updateBatch',
		type : "get",
		dataType:"json",
		async:true,
		cache:false,
		data:{"ids":ids,"thesauresType":thesauresType},
		type:"post",
        success:function(result)
        {
        	if(result>0){
        		$("#wordAdd").modal('hide');
        	}else{
        		alert("Modifications failed");
        	}
        },
        error:function()
        {
          
        },
        complete:function()
        {
        	
        }
   });
}

function search(){
	//获取查询条件
	var name = $("#name").val();
	var queryParams = { 
		query: {  
			name:name
        }
    }  
	//刷新表格  
    $('#thesauresPramList').bootstrapTable('refresh',queryParams);  
}

