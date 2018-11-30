$(function(){
	loadCourseType();
	loadCourseApplyList();
	datehandle();
});

function loadCourseType(){
	var queryUrl = '/Emp/admin/courseApply/queryCourseType';
	$.ajax({
		url: queryUrl,
		method: "post",
		dataType: "json",
		cache: false,
		async: true,
		success: function(data){
			var html = "<option value>-- Option --</option>";
			data.forEach(function(item, index){
				if(item.fircatname != "SmartTools"){
					html += "<option value='"+ item.id +"'>"+ item.fircatname +"</option>";
				}
			});
			$("#courseType").html(html);
		}
	})
}

function loadCourseApplyList(){
	var queryUrl = '/Emp/admin/courseApply/queryAll';
	var coursefirstleveltype =$("#courseType").val();
	var courseName = $("#courseName").val();
	var courseState = $("#courseState").val();
	var applyDate = $("#applyDate").val();
	var table = $("#courseApplyList").bootstrapTable({
		 	url: queryUrl,                      //请求后台的URL（*）
	        method: 'POST',                      //请求方式（*）
	        contentType:'application/x-www-form-urlencoded; charset=UTF-8',
	        striped: true,                      //是否显示行间隔色
	        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
	        pagination: true,                   //是否显示分页（*）
	        sortable: false,                     //是否启用排序
	        sortOrder: "asc",                   //排序方式
	        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
	        pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
	        pageSize: 10,                     //每页的记录行数（*）
	        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
	        search: false,                      //是否显示表格搜索
	        strictSearch: false,				//精确搜索
	        showColumns: false,                  //是否显示所有的列（选择显示的列）
	        showRefresh: true,                  //是否显示刷新按钮
	        minimumCountColumns: 2,             //最少允许的列数
	        clickToSelect: false,                //是否启用点击选中行
	        //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
	        uniqueId: "id",                     //每一行的唯一标识，一般为主键列
	        showToggle: false,                   //是否显示详细视图和列表视图的切换按钮
	        cardView: false,                    //是否显示详细视图
	        detailView: false,                  //是否显示父子表
	        singleSelect:false, 				//禁止多选_____
	        //得到查询的参数
	        queryParams : function (params) {
	        	return {
	        		pageSize: params.limit,
	        		pageNumber: params.offset/params.limit+1,
	        		coursefirstleveltype: coursefirstleveltype,
	        		coursename: courseName,
	        		coursestate : courseState,
	        		approvestringdate : applyDate
	            };
	        },
	        columns: [{
	            field: 'applyUserName',
	            title: 'Apply User',
	            sortable: true
	        },{
	            field: 'coursename',
	            title: 'Course Name',
	            sortable: true
	        },{
	            field: 'courseFirstTypeName',
	            title: 'Course Type',
	            sortable: true
	        },{
	            field: 'coursestateName',
	            title: 'Course State',
	            sortable: true
	        },{
	            field: 'applydate',
	            title: 'Apply Date',
	            sortable: true,
	            formatter:function(value, row, index){
	            	return "<strong>"+new Date(value).format("yyyy-MM-dd")+"</strong>";
	            }
	        }
	        ,{	
	        	field: "approvestate",
	            title: 'Operate',
	            sortable: true,
	            formatter:function(value, row, index){
	            	if(value == "0"){
	            		var html = "<div id='"+ row.id +"'><button class='btn btn-default' style='margin-right:10px' onclick= updateInfo('" + row.id + "','1')>Approve</button>" +
	            					"<button class='btn btn-default' index='2' onclick= updateInfo('" + row.id + "','2')>Refuse</button></div>";
	            		return html;
	            	}else if(value == "1"){
	            		return "Approved";
	            	}else if(value == "2"){
	            		return "Refused";
	            	}
	            }
	        }
	       ]
	});
}

function searchCourseList(){
	var queryUrl = '/Emp/admin/courseApply/queryAll';
	var coursefirstleveltype =$("#courseType").val();
	var courseName = $("#courseName").val();
	var courseState = $("#courseState").val();
	var applyDate = $("#applyDate").val();
	queryParams = {
		query: {
			coursefirstleveltype: coursefirstleveltype,
			coursename: courseName,
			coursestate : courseState,
			approvestringdate : applyDate
		}
    }
	$("#courseApplyList").bootstrapTable('refresh',queryParams);
}

function updateInfo(id, state){
	var parent = window.event.srcElement.parentElement;
	var url = "/Emp/admin/courseApply/update";
	var data = { id: id, approvestate: state};
	$.ajax({
		url: url,
		data: data,
		dataType: "json",
		method: "post",
		cache: false,
		async: true,
		success: function(){
			if(state == "1"){
				$('#' + id +'').parent().html("Approved");
			}else{
				$('#' + id +'').parent().html("Refused");
			}
		}
	});
}

function datehandle(){
	$('.form_datetime').datetimepicker({
		format: 'yyyy-mm-dd',//显示格式
		todayHighlight: 1,//今天高亮
		minView: "month",//设置只显示到月份c
		startView:2,
		forceParse: 0,
		showMeridian: 1,
		autoclose: 1//选择后自动关闭
	}).on('changeDate', function(ev){
//		 $('#addTaskForm').bootstrapValidator('revalidateField', 'startDateAdd');
//		 $('#addTaskForm').bootstrapValidator('revalidateField', 'endDateAdd');
	});;
}

//Date Format
Date.prototype.format = function(fmt) { 
    var o = { 
       "M+" : this.getMonth()+1,
       "d+" : this.getDate(),
       "h+" : this.getHours(),
       "m+" : this.getMinutes(),
       "s+" : this.getSeconds(),
       "q+" : Math.floor((this.getMonth()+3)/3),
       "S"  : this.getMilliseconds()
   }; 
   if(/(y+)/.test(fmt)) {
           fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
   }
    for(var k in o) {
       if(new RegExp("("+ k +")").test(fmt)){
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
        }
    }
   return fmt; 
}