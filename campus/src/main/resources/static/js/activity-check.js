
/**
 * 页面加载
 */
$(function(){
	//判断用户等级进行展示
	var level=$("#getUserLevel").val();
	if(level==2){
		//初始化审核表
		initActivityCheckGoing();
		
		//教师审核活动结果
		teacherCheckActivityResult();
		
		var CCC=$("#getUserLevel").val();
		//用户权限达级,可以实现管理用户信息
		initUserInfoManageTable();
	
//		$("#user-info-manage-div").css({"display":"block"});
		//重置所有用户的信用分
//		$("#reset-userscore-zero").on('click',function(){
//			$("#reset-userscore-zero").on('click',function(){
//				ensureResetUserscoreZero();
//			});
//		});
		
	}else{
//		document.getElementById("#show-error-info").innerHTML='<h1>权限不够,无法展示</h1>';
		$("#show-error-info").html('<h1>权限不够,无法展示</h1>');
	}
	
});

/**
 * 教师审核活动结果
 * @returns
 */
function teacherCheckActivityResult(){
	$("#teacher-activity-check-result").DataTable(
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
	                url: "/activity-check/teacher-check-act-result-show",//修改路径即可
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
			        {"data":'activityApplystatus',
			        	render:function(data){
			        		if(data==0 || data==""){
			        			return "未申请";
			        		}else if(data==1){
			        			return "审核中";
			        		}else if(data==2){
			        			return "审核拒绝";
			        		}else if(data==3){
			        			return "审核通过";
			        		}else{
			        			return "系统错误";
			        		}
			        	}
			        },
			    ]
		});
	
};


/*
 * 初始化审核表
 */
function initActivityCheckGoing(){
	//活动审核表
	$("#activity-check-going").DataTable(
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

/**
 * 教师下载申请文件审批
 */
function checkDownFile(result){
	//获取
//	var dataId=$(obj).attr("upload-file-actid");
	var AAA=confirm("是否下载文件?");
	if(!AAA){  return;  };
	$.ajax({
		url: "/activity-check/downloadFileByObjectId",
		data: {"activityId":parseInt(result)},
		type: "POST",
		success: function(data){
			if(data.status=="true"){ 
				alert(data.msg); 
			};
			if(data.status=="error"){ 
				alert(data.msg); 
			};
		},
		error: function(data){  
			alert(data.msg);  
		}
	});
	
}


/*
 * 是否要重置所有用户的信用得分
 */
//function ensureResetUserscoreZero(){
//	var AAA=confirm("确定重置所有用户的信用得分");
//	if(!AAA){
//		return false;
//	}
//	$.ajax({
//		url:"/user/resetAllUserscoreZero",
//		type:"Post",
//		success:function(data){
//			if(data.status="true"){
//				alert(data.msg);
//				window.location.reload();
//			}else if(data.status=="error"){
//				alert(data.msg);
//			}
//		},
//		error:function(){
//			alert("操作失败!");
//		}
//	});
//}


/*
 * 初始化用户信息的  datatables 
 */
function initUserInfoManageTable(){
	$("#user-info-manage").DataTable(
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
	                url: "/user/manage-userinfo",//修改路径即可
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
			        {"data":'userName'},
			        {"data":'userNickname'},
			        {"data":'userCollege'},
			        {"data":'userScore'},
			        {"data":'userFoundtime',
			        	render:function(data){
			        		if(data==null){
			        			return "";
			        		}
		        			return data.split("T")[0]+" "+data.split("T")[1].split(".")[0];
			        	}
			        },
			       
			    ]
			
		});
};
////升级用户权限
//function riseUserlevel(obj){
//	var user=$(obj).attr("data-user-id");
//	var userLevel=$(obj).parents("tr").find("td:eq(4)").html();
//	if(userLevel=="审核员"){
//		alert("已达最高权限!!!");
//		return;
//	};
//	$.ajax({
//		url: "/user/riseUserlevel",
//		data: {"userId":parseInt(user)},
//		type:"POST",
//		success: function(result){
//			if(result.status=="true"){
//				alert("操作成功!!!");  
//			};
//			if(result.status=="error"){  
//				alert(result.msg);  
//			};
//		},
//		error: function(){   alert("操作失败!!!");   }
//	});
//};
////降低用户权限
//function downUserLevel(obj){
//	var user=$(obj).attr("data-user-id2");
//	var userLevel=$(obj).parents("tr").find("td:eq(4)").html();
//	if(userLevel=="普通用户"){
//		alert("操作错误!!!");
//		return  false;
//	};
//	$.ajax({
//		url: "/user/downUserLevel",
//		data: {"userId":parseInt(user)},
//		type:"POST",
//		success: function(result){
//			if(result.status=="true"){  alert("操作成功!!!");  };
//			if(result.status=="error"){  alert(result.msg);  };
//		},
//		error: function(){   alert("操作失败!!!");   }
//	});
//};



