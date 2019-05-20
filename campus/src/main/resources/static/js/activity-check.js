
/**
 * 页面加载
 */
$(function(){
		//初始化审核表
		initActivityCheckGoing();
	
		var CCC=$("#getUserLevel").val();
		//用户权限达级,可以实现管理用户信息
		initUserInfoManageTable();
		
//		$("#user-info-manage-div").css({"display":"block"});
		//重置所有用户的信用分
		$("#reset-userscore-zero").on('click',function(){
			$("#reset-userscore-zero").on('click',function(){
				ensureResetUserscoreZero();
//				location.replace(document.referrer);
			});
		});
	
	
});


/*
 * 是否要重置所有用户的信用得分
 */
function ensureResetUserscoreZero(){
	var AAA=confirm("确定重置所有用户的信用得分");
	if(!AAA){
		return false;
	}
	$.ajax({
		url:"/user/resetAllUserscoreZero",
		type:"Post",
		success:function(data){
			if(data.status="true"){
				alert(data.msg);
			}else if(data.status=="error"){
				alert(data.msg);
			}
		},
		error:function(){
			alert("操作失败!");
		}
	});
}

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
			        		return '<button type="button" class="btn btn-accent"  id="aggre-apply-activity"  onclick="AggreActivityCheckGoing('+data+')">同意</button>'+
			        		'&nbsp;&nbsp;<button type="button" class="btn btn-accent"  id="refuse-apply-activity"  onclick="RefuseActivityCheckGoing('+data+')">拒绝</button>';
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
			if(data.status=="true"){ alert("操作成功!!!"); };
			if(data.status=="error"){ alert(data.msg); };
		},
		error: function(){  alert("操作失败");  }
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
			if(data.status=="true"){ alert("操作成功!!!"); };
			if(data.status=="error"){ alert(data.msg); };
		},
		error: function(){  alert("操作失败");  }
	});
};

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
			        {"data":'userLevel',
			        	render : function(data){
			        		if(data==0){
			        			return "普通用户";
			        		}else if(data==1){
			        			return "管理员";
			        		}else if(data==2){
			        			return "审核员";
			        		}
			        	}
			        },
			        {"data":'userFoundtime',
			        	render:function(data){
		        			return data.split("T")[0]+" "+data.split("T")[1].split(".")[0];
			        	}
			        },
			        {"data":'userId',
			        	render:function(data){
			        		return '<button type="button" class="btn btn-accent"  data-user-id="'+data+'" onclick="riseUserlevel(this)">升级</button>'+
			        		'&nbsp;&nbsp;<button type="button" class="btn btn-accent"  data-user-id2="'+data+'" onclick="downUserLevel(this)">降级</button>';
			        	}
			        },
			    ]
			
		});
};
//升级用户权限
function riseUserlevel(obj){
	var user=$(obj).attr("data-user-id");
	var userLevel=$(obj).parents("tr").find("td:eq(4)").html();
	if(userLevel=="审核员"){
		alert("已达最高权限!!!");
		return;
	};
	$.ajax({
		url: "/user/riseUserlevel",
		data: {"userId":parseInt(user)},
		type:"POST",
		success: function(result){
			if(result.status=="true"){  alert("操作成功!!!");  };
			if(result.status=="error"){  alert(result.msg);  };
		},
		error: function(){   alert("操作失败!!!");   }
	});
};
//降低用户权限
function downUserLevel(obj){
	var user=$(obj).attr("data-user-id2");
	var userLevel=$(obj).parents("tr").find("td:eq(4)").html();
	if(userLevel=="普通用户"){
		alert("操作错误!!!");
		return  false;
	};
	$.ajax({
		url: "/user/downUserLevel",
		data: {"userId":parseInt(user)},
		type:"POST",
		success: function(result){
			if(result.status=="true"){  alert("操作成功!!!");  };
			if(result.status=="error"){  alert(result.msg);  };
		},
		error: function(){   alert("操作失败!!!");   }
	});
	
};
