/***
 * 用户基本信息管理页面;页面加载主函数
 * @returns
 */
$(function(){
	//用户信息初始化
	initUserBasicInfo();
	//重置所有信用分
	$("#resetAllUserScore").on('click',function(){
		resetAllUserScore();
	});
	
});
/**
 * 重置所有信用分
 */
function resetAllUserScore(){
	var AAA=confirm("确认重置所有信用得分?");
	if(!AAA){ return  ;  };
	$.ajax({
		url: "/user/resetAllUserScore",
		type:"POST",
		success: function(result){
			if(result.status=="true"){  
				alert("操作成功!!!");
				window.location.reload();
			};
			if(result.status=="error"){  
				alert(result.msg);
			};
		},
		error: function(){   alert("操作失败!!!");   }
	});
};

/**
 * 初始化用户信息表
 * @returns
 */
function  initUserBasicInfo(){
	$("#show-all-user").DataTable(
			{
				"aLengthMenu": [5, 10,20],//设置显示条数
				"responsive": false,
		        "bAutoWidth": true, //自动计算列宽
	    		"ordering": true, //排序
			    "pageLength": 10,　//初始化显示几条数据
			    "pagingType": "full_numbers",//显示底部按钮
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
	                url: "/user/showAllUser",//修改路径即可
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
			        {"data":'userPassword'},
			        {"data":'userNickname'},
			        {"data":'userPhone'},
			        {"data":'userScore'},
//			        {"data":'userFoundtime' //截取时间格式显示
//			        	render:function(data){
//			        		return data.split("T")[0]+" "+data.split("T")[1].split(".")[0];
//			        	}
//			        },
			        {"data":'userLevel',
			        	render: function(data){
			        		if(data==0 || data==null){
			        			return "普通用户";
			        		}else if(data==1){
			        			return "活动管理员";
			        		}else if(data==2){
			        			return "教师";
			        		}
			        	}
			        },
			        {"data":'userId',
			        	render : function(data){
			        		return '<button type="button" data-user-id1="'+data+'" onclick="upUserLevel(this)" >升级</button>'+
			        		'&nbsp;&nbsp;<button type="button" data-user-id2="'+data+'" onclick="downUserLevel(this)" >降级</button>'+
			        		'&nbsp;&nbsp;<button type="button" data-user-id3="'+data+'" onclick="resetUserInfo(this)" >重置</button>';
			        	}
			        },
			    ]
			    
		});
	
};
/**
 * 升级用户等级
 */
function upUserLevel(obj){
	var AAA=confirm("确认提升用户等级?");
	if(!AAA){  return ;  };
	//获取id值
	var user=$(obj).attr("data-user-id1");
	var userLevel=$(obj).parents("tr").find("td:eq(5)").html();
	if(userLevel=="教师"){
		alert("已达最高权限!!!");
		return  ;
	};
	$.ajax({
		url: "/user/riseUserlevel",
		data: {"userId":parseInt(user)},
		type:"POST",
		success: function(result){
			if(result.status=="true"){  
				alert("操作成功!!!");
				window.location.reload();
			};
			if(result.status=="error"){ 
				alert(result.msg);  
			};
		},
		error: function(){   alert("操作失败!!!");   }
	});
//	$('#show-all-user').DataTable().ajax.reload();
};
/**
 * 降低用户等级
 */
function downUserLevel(obj){
	var AAA=confirm("确认降低用户等级?");
	if(!AAA){  return ;  };
	//
	var user=$(obj).attr("data-user-id2");
	var userLevel=$(obj).parents("tr").find("td:eq(5)").html();
	if(userLevel=="普通用户"){
		alert("不可执行!");
		return  ;
	};
	$.ajax({
		url: "/user/downUserLevel",
		data: {"userId":parseInt(user)},
		type:"POST",
		success: function(result){
			if(result.status=="true"){  
				alert("操作成功!!!");  
				window.location.reload();
			};
			if(result.status=="error"){  alert(result.msg);  };
		},
		error: function(){   alert("操作失败!!!");   }
	});
};
/**
 * 重置用户信息
 */
function resetUserInfo(obj){
	var AAA=confirm("确认重置用户信息?");
	if(!AAA){  return ;  };
	//
	var user=$(obj).attr("data-user-id3");
	$.ajax({
		url: "/user/resetUserInfo",
		data: {"userId":parseInt(user)},
		type:"POST",
		success: function(result){
			if(result.status=="true"){  
				alert("操作成功!!!"); 
				window.location.reload();
			};
			if(result.status=="error"){  alert(result.msg);  };
		},
		error: function(){   alert("操作失败!!!");   }
	});
	
};


