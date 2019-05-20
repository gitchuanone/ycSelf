
$(function(){
	var remarkShowLength = 15;//默认现实的字符串长度

//	活动预参与信息展示
	showPredJoinApplyActivity();
	
//	 活动审核过程信息表 
	showCheckingApplyActivity();
	
	/*
	 *活动审核结果信息表 
	 */
	$("#check-end-result-activity").DataTable(
			{
				"aLengthMenu": [5, 10,20],//设置显示条数
//				"scrollX": true,
				"responsive": false,
		        "bAutoWidth": true,
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
	                url: "/activity/show/checkendresult",//修改路径即可
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
			        {"data":'activityDescription'},
			        {"data": 'activityPredtime',
			        	render:function(data){
			        		return data.split("T")[0]+" "+data.split("T")[1].split(".")[0];
			        	}
			        },
			        {"data": 'activityProcesstime',
			        	render:function(data){
			        		return data.split("T")[0]+" "+data.split("T")[1].split(".")[0];
			        	}
			        },
			        {"data":'activityPredjoin'},
			        {"data":'activityApplystatus',
			        	render:function(data, type, row, meta){
			        		if(data==0){
			        			return "审核未通过";
			        		}else if(data==1){
			        			return "审核中";
			        		}else if(data==2){
			        			return "审核通过";
			        		}else{
			        			return "系统出错了!";
			        		}
			        	}
			        },
			    ]
			
		});
	
	
	/*
	 * 前两周活动结果公示
	 */
	$("#hold-result-activity").DataTable(
			{
				"aLengthMenu": [5, 10,20],//设置显示条数
//				"scrollX": true,
				"responsive": false,
		        "bAutoWidth": true,
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
	                url: "/activity/show/holdresult",//修改路径即可
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
			        {"data": 'activityProcesstime',
			        	render:function(data){
			        		return data.split("T")[0]+" "+data.split("T")[1].split(".")[0];
			        	}
			        },
			        {"data": 'activityEndtime',
			        	render:function(data){
			        		return data.split("T")[0]+" "+data.split("T")[1].split(".")[0];
			        	}
			        },
			        {"data":'activityRealjoin'},
			        {"data":'activityInnovatescore'},
			        {"data":'activityExecutescore'},
			        {"data":'activityProcessscore'},
			        {"data":'activityFinallyscore'},
			        
			    ]
			
		});
	
	
	
});

/*
 * 活动预参与信息展示
 */
function  showPredJoinApplyActivity(){
	$("#pred-activity").DataTable(
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
	                url: "/activity/show/predact",//修改路径即可
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
			        {"data":'activityDescription'},
			        {"data": 'activityPredtime',
			        	render:function(data){
			        		return data.split("T")[0]+" "+data.split("T")[1].split(".")[0];
			        	}
			        },
			        {"data": 'activityProcesstime',
			        	render:function(data){
			        		return data.split("T")[0]+" "+data.split("T")[1].split(".")[0];
			        	}
			        },
			        {"data":'activityPredjoin'},
			        {"data":'activityId',
			        	render:function(data){
			        		return '<button type="button" class="btn btn-accent"  data-toggle="modal" onclick="userApplyPredJoin('+data+')">参加</button>';
			        	}
			        },
			    ]
			
		});
};
//用户点击参与活动预参与的人数统计
function userApplyPredJoin(data){
	var AAA=confirm("确定参加");
	if(!AAA){ return ; };
	var clickCountt=1;
	var param={};
	param.activityId=data;
	param.clickCount=clickCountt;
	$.ajax({
		url: "/activity-apply/userApplyPredJoin",
		data: param,
		type: "POST",
		success: function(data){
			if(data.status=="true"){
				alert("操作成功!");
				window.location.reload();
			}
			if(data.status=="error"){
				alert(data.msg);
			}
		},
		error: function(){ alert("操作失败"); },
	});
		
};


/*
 *自己参与的申请;活动审核过程信息表 
 */
function showCheckingApplyActivity(){
	$("#check-result-activity").DataTable(
			{
				"aLengthMenu": [5, 10,20],//设置显示条数
//				"scrollX": true,
				"responsive": false,
		        "bAutoWidth": true,
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
	                url: "/activity/show/checkresult",//修改路径即可
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
			        {"data":'activityDescription'},
			        {"data": 'activityPredtime',
			        	render:function(data){
			        		return data.split("T")[0]+" "+data.split("T")[1].split(".")[0];
			        	}
			        },
			        {"data": 'activityProcesstime',
			        	render:function(data){
			        		return data.split("T")[0]+" "+data.split("T")[1].split(".")[0];
			        	}
			        },
			        {"data":'activityPredjoin'},
			        {"data":'activityApplystatus',
			        	render:function(data, type, row, meta){
			        		if(data==0){
			        			return "审核未通过";
			        		}else if(data==1){
			        			return "审核中";
			        		}else if(data==2){
			        			return "审核通过";
			        		}else{
			        			return "系统出错了!";
			        		}
			        	}
			        },
			    ]
		});
};








