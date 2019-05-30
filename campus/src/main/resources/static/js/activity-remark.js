/**
 * 活动进行页面
 */
$(function(){
	//展示所有可以平分的活动
	showAllCouldRemarkAct();
	
});

/**
 * 展示自身所有可进行签到的活动
 * @returns
 */
function showAllCouldRemarkAct(){
	$("#show-pingfen-act").DataTable(
			{	
				"searching": false, //关闭搜索框
				"aLengthMenu": [ 5, 10,20],//设置显示条数
//				"scrollX":true,
				"responsive": false,
		        "bAutoWidth": true, //自动计算列宽
	    		"ordering": false, //排序
			    "pageLength": 5,　//初始化显示几条数据
			    "pagingType": "full_numbers",
			    "language": {　　// 这是修改语言的显示
			        "paginate": {
			            first: "首条",
			            previous: "前一页",
			            next: "下一页",
			            last: "末页"
			        },
			        "info": "第_PAGE_页,共_PAGES_页",
			        "infoEmpty": "未找到相关数据",
			        "search": "关键字",
			        "zeroRecords": "未找到相关数据",
			        "decimal": ".",
			        "thousands": ","
			    },
			    
			    //ajax实现数据的传递的再现
			    ajax:{
	                url: "/activity-hold/showAllCouldRemarkAct",//修改路径即可
	                type: 'POST',
	                //用于处理服务器端返回的数据。 dataSrc是DataTable特有的
	                dataSrc: function (myJson) {
	                    if (myJson.timeout) {
	                        return "";
	                    }
	                    return myJson;
	                }
			    },
			    columns: [　//这个是显示到界面上的个数据　格式为 {data:'显示的字段名'}
			        {"data":'activityTheme'},
			        {"data":'activityOrgcollege'},
			        {"data":'activityOrganizer'},
			        {"data": 'activityImpltime',
			        	render:function(data){
			        		if(data!=null){
			        			return data.split("T")[0]+" "+data.split("T")[1].split(".")[0];
			        		}else{
			        			return "";
			        		}
			        	}
			        },
			        {"data": 'activityPredjoin'},
			        {"data": 'activityRealjoin'},
			        {"data":'activityId',
			        	render:function(data){
			        		return   '<button type="button" class="btn btn-accent"  data-toggle="modal"  onclick="userRemarkAct('+data+')">评分</button>';
			        	}
			        }

			    ]
		});
	
};
/**
 * 用户评分弹出框
 * @returns
 */
function userRemarkAct(result){
	$("#my-starts").modal("show");
	
//	var dataId=$(obj).attr("upload-file-actid");
//	var username=$("#userId-"+result).val();
//	var param={};
//	param.actId2=parseInt(result);
//	param.userName=username;
//	$.ajax({
//		url: "/activity-hold/userRemarkAct",
//		data: param,
//		type: "POST",
//		success: function(data){
//			if(data.status=="true"){ 
//				alert("操作成功!!!");
////				window.location.reload();
//			};
//			if(data.status=="error"){ 
//				alert(data.msg);
//			};
//		},
//		error: function(data){  alert(data.msg);  }
//	});
	
	
}


//======================================================================
function initActivityCheckGoing(){
	//活动审核表
	$("").DataTable(
			{
				"aLengthMenu": [ 5, 10,20],//设置显示条数
//				"scrollX":true,
				"responsive": false,
		        "bAutoWidth": true, //自动计算列宽
	    		"ordering": true, //排序
			    "pageLength": 10,　//初始化显示几条数据
			    "pagingType": "full_numbers",
			    "language": {　　// 这是修改语言的显示
			        "paginate": {
			            first: "首条",
			            previous: "前一页",
			            next: "下一页",
			            last: "末页"
			        },
			        "info": "第_PAGE_页,共_PAGES_页",
			        "infoEmpty": "未找到相关数据",
			        "search": "关键字",
			        "zeroRecords": "未找到相关数据",
			        "decimal": ".",
			        "thousands": ","
			    },
			    
			    //ajax实现数据的传递的再现
			    ajax:{
	                url: "/activity-check/check-act-show",//修改路径即可
	                type: 'POST',
	                //用于处理服务器端返回的数据。 dataSrc是DataTable特有的
	                dataSrc: function (myJson) {
	                    if (myJson.timeout) {
	                        return "";
	                    }
	                    return myJson;
	                }
			    },
			    columns: [　//这个是显示到界面上的个数据　格式为 {data:'显示的字段名'}
			        {"data":'activityTheme'},
			        {"data":'activityOrgcollege'},
			        {"data":'activityOrganizer'},
			        {"data":'activityPlace'},
			        {"data": 'activityPredtime',
			        	render:function(data){
			        		if(data!=null){
			        			return data.split("T")[0]+" "+data.split("T")[1].split(".")[0];
			        		}else{
			        			return "";
			        		}
			        	}
			        },
			        {"data": 'activityPredjoin'},
			        {"data":'activityId',
			        	render:function(data){
			        		return '<button type="button" class="btn btn-accent"  onclick="AggreActivityCheckGoing('+data+')">同意</button>'+
			        		'&nbsp;&nbsp;<button type="button" class="btn btn-accent"   onclick="RefuseActivityCheckGoing('+data+')">拒绝</button>'+
			        		'&nbsp;&nbsp;<button type="button" class="btn btn-accent"   onclick="checkDownFile('+data+')" >下载文件</button>';
			        	}
			        },
			        {"data": 'applyFilename',
			        	render: function(data){
			        		if(data==null || data==""){
			        			return "无上传文件";
			        		}else{
			        			return data;
			        		}
			        	}
			        },

			    ]
		});
	
};
//同意审核请求
function  AggreActivityCheckGoing(result){
	var AAA=confirm("是否同意???");
	if(!AAA){  return;  };
	$.ajax({
		url: "/activity-check/aggreActivityCheckGoing",
		data: {"activityId":parseInt(result)},
		type: "POST",
		success: function(data){
			if(data.status=="true"){ 
				alert("操作成功!!!");
				window.location.reload();
			};
			if(data.status=="error"){ 
				alert(data.msg);
			};
		},
		error: function(data){  alert(data.msg);  }
	});
};
//拒绝审核请求
function  RefuseActivityCheckGoing(result){
	var AAA=confirm("是否拒绝???");
	if(!AAA){  return;  };
	$.ajax({
		url: "/activity-check/refuseActivityCheckGoing",
		data: {"activityId":parseInt(result)},
		type: "POST",
		success: function(data){
			if(data.status=="true"){ 
				alert("操作成功!!!"); 
				window.location.reload();
			};
			if(data.status=="error"){ 
				alert(data.msg); 
			};
		},
		error: function(data){ alert(data.msg);  }
	});
};







