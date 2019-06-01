/**
 * 活动进行页面
 */
$(function(){
	//获取用户等级
	var level=$("#getUserLevel").val();
	
	//投票
	$("#toupiao-submit").on('click',function(){
		var A=confirm("是否提交?");
		if(A){
			alert("操作成功");
		}
	});
	
	//点击二维码签到
//	$("#erweima-button").on('click',function(){
//		if(level==0){
//			alert("您的操作无效");
//			return false;
//		}
//		var AAA=confirm("确定进行扫码签到");
//		if(!AAA){  return false;  }
//		showQrCodeProcessSign();
//	});
	
	
//document.getElementById("hide").style.display = "block"   "hidden";//隐藏;   "visible";//显示
	//用户等级达到,开启手动签到
	if(level != 0){
		document.getElementById("show-manual-sign-activity-div").style.display ="";
		//展示自身所有可进行签到的活动
		showAllCouldSigActivityInfo();
	}
	
	//与会人员信息展示
//	showAllHaveSignUerInfo();

	
});

/**
 * 生成二维码签到
 * @returns
 */
function showQrCodeProcessSign(){
	alert("建设中");
};
/**
 * 展示自身所有可进行签到的活动
 * @returns
 */
function showAllCouldSigActivityInfo(){
	$("#show-all-could-sign-activity-table").DataTable(
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
	                url: "/activity-hold/showAllCouldSignAct",//修改路径即可
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
			        {"data": 'activityRealjoin'},
			        {"data":'activityId',
			        	render:function(data){
			        		return   '<input type="text"  id="userId-'+data+'" /> &nbsp;&nbsp;'+ 
			        		'<button type="button" class="btn btn-accent"  onclick="signByUserManual('+data+')">签到</button>'+
			        		'&nbsp;&nbsp;<button type="button" class="btn btn-accent"  onclick="erweimaStart('+data+')">显示二维码</button>'+
			        		'&nbsp;&nbsp;<button type="button" class="btn btn-accent"  onclick="endAct('+data+')">结束</button>';
			        	}
			        }

			    ]
		});
	
};
/**
 * 用户手动进行活动签到
 * @returns
 */
function signByUserManual(result){
//	var dataId=$(obj).attr("upload-file-actid");
	var username=$("#userId-"+result).val();
	var param={};
	param.actId2=parseInt(result);
	param.userName=username;
	$.ajax({
		url: "/activity-hold/signByUserManual",
		data: param,
		type: "POST",
		success: function(data){
			if(data.status=="true"){ 
				alert("操作成功!!!");
//				window.location.reload();
			};
			if(data.status=="error"){ 
				alert(data.msg);
			};
		},
		error: function(data){  alert(data.msg);  }
	});
	
	
}
/**点击弹出二维码框
 */
function erweimaStart(obj){
	var pop=confirm("是否生成二维码?");
	if(!pop){
		return false;
	}
	document.getElementById("show-erweima-popup").style.display ="";
}
/**
 * 活动结束
 * @param result
 * @returns
 */
function endAct(result){
	var AAA=confirm("活动结束,开启活动评分?");
	if(!AAA){
		return ;
	}
//	var dataId=$(obj).attr("upload-file-actid");
	var param={};
	param.actId2=parseInt(result);
	$.ajax({
		url: "/activity-hold/endActByActid",
		data: param,
		type: "POST",
		success: function(data){
			if(data.status=="true"){ 
				alert("操作成功!!!");
//				window.location.reload();
			};
			if(data.status=="error"){ 
				alert(data.msg);
			};
		},
		error: function(data){  alert(data.msg);  }
	});
	
	
}




/**
 * 与会人员信息
 * @returns
 */
//function showAllHaveSignUerInfo(){
//	$("").DataTable(
//			{
//				"aLengthMenu": [ 5, 10,20],//设置显示条数
////				"scrollX":true,
//				"responsive": false,
//		        "bAutoWidth": true, //自动计算列宽
//	    		"ordering": true, //排序
//			    "pageLength": 10,　//初始化显示几条数据
//			    "pagingType": "full_numbers",
//			    "language": {　　// 这是修改语言的显示
//			        "paginate": {
//			            first: "首条",
//			            previous: "前一页",
//			            next: "下一页",
//			            last: "末页"
//			        },
//			        "info": "第_PAGE_页,共_PAGES_页",
//			        "infoEmpty": "未找到相关数据",
//			        "search": "关键字",
//			        "zeroRecords": "未找到相关数据",
//			        "decimal": ".",
//			        "thousands": ","
//			    },
//			    
//			    //ajax实现数据的传递的再现
//			    ajax:{
//	                url: "/activity-check/check-act-show",//修改路径即可
//	                type: 'POST',
//	                //用于处理服务器端返回的数据。 dataSrc是DataTable特有的
//	                dataSrc: function (myJson) {
//	                    if (myJson.timeout) {
//	                        return "";
//	                    }
//	                    return myJson;
//	                }
//			    },
//			    columns: [　//这个是显示到界面上的个数据　格式为 {data:'显示的字段名'}
//			        {"data":'activityTheme'},
//			        {"data":'activityOrgcollege'},
//			        {"data":'activityOrganizer'},
//			        {"data":'activityPlace'},
//			        {"data": 'activityPredjoin'},
//
//			    ]
//		});
//}

