$(function () {
	//加载系统参数列表
    loadSysParamList();
});


//用户列表
function loadSysParamList(){
    var queryUrl = '/Emp/admin/sysparam/queryAll'
    var table = $('#sysparamlist').bootstrapTable({
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
            field: 'parentName',
            title: 'Name',
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

function AddFunctionAlty(value, row, index){
	return[
		"<button id='delParam' type='button' class='btn btn-default'>删除</button>"
	].join("")
}

window.operateEvents = {
	"click #delParam": function (e, value, row, index) {
		var msg="确认要删除吗？"; 
		if(confirm(msg)){
			 $.ajax({
					url:'/Emp/admin/sysparam/deleteSysParam',
					dataType:"json",
					data:{"id":row.id},
					async:false,
					cache:false,
					type:"post",
			});
			$('#sysparamlist').bootstrapTable('refresh');
		}
	}
};

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
        singleSelect:false, 				//禁止多选_____
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
        }
    });
    $("#cur_table").bootstrapTable('refresh');
}

function search(){
	//获取查询条件
	var paramname = $("#paramname").val();
	var queryParams = { 
		query: {  
			parentName:paramname
        }
    }  
	//刷新表格  
    $('#sysparamlist').bootstrapTable('refresh',queryParams);  
}


function openAddParamTable(){
	$("#addParamTable").modal('show');
}

var optionVal;
function chooseOption(){
	 optionVal = $("#optionName").val();
	 if(optionVal == ""){
		 $("#showModel").show();
		 $("#parentId").hide();
		 $("#childId").hide();
	 }else if(optionVal == "0"){
		 $("#showModel").hide();
		 $("#parentId").show();
		 $("#childId").hide();
	 }else if(optionVal == "1"){
		 $("#showModel").hide();
		 $("#parentId").hide();
		 $("#childId").show();
	 }
}

function addSysParam(){
	var bootstrapValidator = $("#addAdminForm").data('bootstrapValidator');
	bootstrapValidator.validate();
	if(bootstrapValidator.isValid()){
		//var childName = $("#childName").val();
		if(optionVal == "0"){
			var parentName = $("#parentName").val();
			$.ajax({
				url:'/Emp/admin/sysparam/addSysParam',
				dataType:"json",
				data:{"parentName":parentName},
				async:false,
				cache:false,
				type:"post",
				success:function(result){
					$("#addParamTable").modal('hide');
					$('#sysparamlist').bootstrapTable('refresh');
				}
			});
		}
		else if(optionVal == "1"){
			var childName = $("#childName").val();
			var childValue = $("#childValue").val();
			var childForPname = $("#treeg").bootstrapTable('getSelections')[0].parentName;
			if(typeof(childForPname) != "undefined"){
				$.ajax({
					url:'/Emp/admin/sysparam/addSysParam',
					dataType:"json",
					data:{"childName":childName,"childValue":childValue,"parentName":childForPname},
					async:false,
					cache:false,
					type:"post",
					success:function(result){
						$("#addParamTable").modal('hide');
						$('#sysparamlist').bootstrapTable('refresh');
					}
				});
			}
		}
	}
}

function loadParamTree(){
    var queryUrl = '/Emp/admin/sysparam/queryAll'
    var table = $('#treeg').bootstrapTable({
        url: queryUrl,                      //请求后台的URL（*）
        method: 'GET',                      //请求方式（*）
        singleSelect:true,
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
        showColumns: false,                  //是否显示所有的列（选择显示的列）
        showRefresh: false,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: true,                  //是否显示父子表
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
        },
        {
            field: 'parentName',
            title: 'Name',
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
