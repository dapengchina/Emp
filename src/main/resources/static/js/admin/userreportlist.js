$(function () {
    loadUserReportList();
});

	
function loadUserReportList(){
    var queryUrl = '/Emp/admin/userreport/queryAll'
    var table = $('#userreportlist').bootstrapTable({
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
        singleSelect:true, 				//禁止多选_____
        //得到查询的参数
        queryParams : function (params) {
        	return {
        		pageSize: params.limit,
        		pageNumber: params.offset/params.limit+1,
            };
        },
        columns: [{
            field: 'userName',
            title: 'UserName',
            sortable: true
        },{
            field: 'phone',
            title: 'Phone',
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
        url: '/Emp/admin/userreport/queryHitCardRecord',
        method: 'get',
        queryParams: {id:userid},
        ajaxOptions: {id:userid},
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
        columns: [{
            field: 'date',
            title: 'HitCardDate'
        }]
//        //无线循环取子表，直到子表里面没有记录
//        onExpandRow: function (index, row, $Subdetail) {
//        	initSubTable(index, row, $Subdetail);
//        }
    });
    $("#cur_table").bootstrapTable('refresh');
}

//function userHitCardRecord(id){
//	//在初始化table之前，要将table销毁，否则会保留上次加载的内容，尤其当打开模态框时
//	$("#userHitCardRecord").bootstrapTable('destroy');
//}
//查找用户
function userSearch(){
	var userName = $("#search_name").val();
    var nickName = $("#search_nickName").val(); 
    var phone = $("#search_phone").val(); 
	var queryParams = { 
		query: {  
			userName:userName,
			nickName:nickName,
			phone:phone
        }
    }  
	//刷新表格  
    $('#userlist').bootstrapTable('refresh',queryParams);  
}


