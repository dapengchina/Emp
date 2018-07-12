$(function () {
	//加载管理员列表
    loadAdminList();
});


function openAddAdminTable(){
	$("#addAdminTable").modal('show');
}

//创建管理员
function addAdmin(){
	var AdminName = $("#AdminName").val();
	var AdminPwd = $("#AdminPwd").val();
	var AdminConPwd = $("#AdminConPwd").val();
//	var bootstrapValidator = $("#addAdminForm").data('bootstrapValidator');
//	bootstrapValidator.validate();
//	if(bootstrapValidator.isValid()){
		$.ajax({
			url:'/Emp/admin/adminuser/addAdmin',
			dataType:"json",
			data:{"adminName":AdminName,"adminPwd":AdminPwd},
			async:false,
			cache:false,
			type:"post",
			success:function(result){
				$("#addAdminTable").modal('hide');
				$('#adminlist').bootstrapTable('refresh');
			}
		});
	//}
}

//查找管理员
function searchAdmin(){
	adminName = $("#searchAdminName").val();
	status = $("#searchAdminStatus").val();
	var queryParams = { 
		query: {  
			adminName:adminName,
			status:status
        }
    }  
	//刷新表格  
    $('#adminlist').bootstrapTable('refresh',queryParams);  
}

function AddFunctionAlty(value, row, index){
	if(row.status==1){
		return[
			"<button id='adminEnable' type='button' class='btn btn-default'>启用</button>"
		].join("")
	}else{
		return[
			"<button id='adminDisable' type='button' class='btn btn-default'>禁用</button>"
		].join("")
	}
}

window.operateEvents = {
	"click #adminEnable": function (e, value, row, index) {
		var msg="确认要启用吗？"; 
		if(confirm(msg)){
			$.ajax({
				url:'/Emp/admin/adminuser/update',
				dataType:"json",
				data:{"adminId":row.id,"adminStatus":0},
				async:false,
				cache:false,
				type:"post",
			});
			$('#adminlist').bootstrapTable('refresh');
		}
	},
	"click #adminDisable": function (e, value, row, index) {
		var msg="确认要禁用吗？"; 
		if(confirm(msg)){
			$.ajax({
				url:'/Emp/admin/adminuser/update',
				dataType:"json",
				data:{"adminId":row.id,"adminStatus":1},
				async:false,
				cache:false,
				type:"post"
			});  
			$('#adminlist').bootstrapTable('refresh'); 
		}
	}
};

	

//管理员列表
function loadAdminList(){
	$("#adminlist").bootstrapTable('destroy');
    var queryUrl = '/Emp/admin/adminuser/queryAll'
    var table = $('#adminlist').bootstrapTable({
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
        strictSearch: true,
        showColumns: true,                  //是否显示所有的列（选择显示的列）
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
        uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
        showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
        cardView: false,                    //是否显示详细视图
        detailView: false,                  //是否显示父子表
        singleSelect:true, 				//禁止多选_____
        //得到查询的参数
        queryParams : function (params) {
            //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            /*var temp = {   
                rows: params.limit,                         //页面大小
                page: (params.offset / params.limit) + 1,   //页码
                sort: params.sort,      //排序列名  
                sortOrder: params.order //排位命令（desc，asc） 
            };
            return temp;*/
        	return {
        		pageSize: params.limit,
        		pageNumber: params.offset/params.limit+1,
            };
        },
        columns: [{
            checkbox: true,  
            visible: true                  //是否显示复选框  
        }, {
            field: 'adminName',
            title: 'Name',
            sortable: true
        }, {
            field: 'statusName',
            title: 'StatusName',
            sortable: true
        }, {
            field:'ID',
            title: 'Operation',
            width: 120,
            align: 'center',
            valign: 'middle',
            events: operateEvents,
            formatter: AddFunctionAlty
        }],
        onLoadSuccess: function () {
        },
        onLoadError: function () {
            //showTips("数据加载失败！");
        },
        onDblClickRow: function (row, $element) {
            //var id = row.ID;
           // EditViewById(id, 'view');
        },
    });
}
