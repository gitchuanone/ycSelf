/*
 * 页面加载
 */
$(function(){
	//活动预申请的点击模态框
	clickActivityAdvanceApplyModalInit();
	
	//活动申请的模态框
	$("#click-activity-apply").on("click",function(){//会执行,但是只是绑定点击事件
		$("#click-activity-apply-modal").modal("show");
	}); 
	
	 //申请预参与活动的信息展示 
	applyActivityPredActivityShowInit();
	
	//活动审核过程信息表
	applyActivityCheckActivitySelfInfoInit();
	
});

//==============================
/**
 * 显示当前时刻
 */
function  getD1(){
         var date=new Date();
         var d1=date.toLocaleString();
         var div1=document.getElementById("insert-newTime1");
         var div2=document.getElementById("insert-newTime2");
         div1.innerHTML =d1;
         div2.innerHTML =d1;
     }
setInterval("getD1();",1000);

//通过输入的时间获取包含datetime的时间
//Date转为datetimeLocal
function dateToDatetimeLocal(date){
	var now = new Date();
	now.setTime(date);
  var datetime = now.getFullYear() + "-" + fix((now.getMonth() + 1), 2) + "-" + fix(now.getDate(), 2) + "T" + fix(now.getHours(), 2) + ":" + fix(now.getMinutes(), 2);
  return datetime;
}
//datetimeLocal转为Date
function datetimeLocalToDate(datetime){
  if(datetime == ''){
	  return '';
  }
	//将datetime-local转换为Date
	var date = new Date();
  date.setFullYear(parseInt(datetime.substring(0, 4)));
  date.setMonth(parseInt(datetime.substring(5, 7)) - 1);
  date.setDate(parseInt(datetime.substring(8, 10)));
  date.setHours(parseInt(datetime.substring(11, 13)));
  date.setMinutes(parseInt(datetime.substring(14, 16)));
  return date;
}
//获取多个复选框的值
function getCheckboxVal(inputname){
	//jquery获取复选框值    
	var chk_value =[];//定义一个数组    
    $('input[name="'+inputname+'"]:checked').each(function(){//遍历每一个名字为interest的复选框，其中选中的执行函数    
    	chk_value.push($(this).val());//将选中的值添加到数组chk_value中    
    });
    return chk_value.join(",");
};

//==============================
/**
 * //活动预申请的点击模态框
 * @returns
 */
function clickActivityAdvanceApplyModalInit(){
	$("#click-activity-advance-apply").on("click",function(){//会执行,但是只是绑定点击事件
		if( $("#getUserLevelForPermiss").val()==0){
			alert("您的权限不够");
			return false;
		};
		$("#click-activity-advance-apply-modal").modal("show");
		
		//预参与申请编辑提交路径
		$("#activity-advance-apply-button").on('click',function(){
			var AAA=confirm("确认提交?");
			if(!AAA){   return ;  };
			if($("#click-activity-advance-apply-modal").find("input[name='activityPredtime1']").val()==""){
				alert("时间不能为空!");
				return;
			}
			var param={};
			param.activityUsername=$("#activityUsername1").val();
			param.activityTheme=$("#activityTheme1").val();
			param.activityOrgcollege=getCheckboxVal('activityOrgcollege1');
			param.activityOrganizer=$("#activityOrganizer1").val();
			param.activityDescription=$("#activityDescription1").val();
			param.activityPredtime=datetimeLocalToDate($("#click-activity-advance-apply-modal").find("input[name='activityPredtime1']").val()),
			$.ajax({
				type:"POST",
				url: "/activity-apply/advanceapply",
				data: param,
				success: function(result){
					 if(result.status=='true'){
						 alert("操作成功!");
					 }else if(result.status=='error'){
						 alert(result.msg);
					 }
				 }
			});
			
		});
	}); 
	
};

/**申请预参与活动的信息展示 
 */
function applyActivityPredActivityShowInit(){
	$("#apply-activity-pred-activity").DataTable(
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
	                url: "/activity-apply/show-predact",//修改路径即可
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
			        		return '<button type="button" class="btn btn-accent" data-activity-id="'+data+'" data-toggle="modal" onclick="updateApplyAdvanceActivit(this)" >编辑</button>'+
			        		'&nbsp;&nbsp;<button type="button" class="btn btn-accent"  data-toggle="modal"  onclick="deleteApplyAdvanceActivit('+data+')">删除</button>';
			        	}
			        },
			    ]
		});
};
//删除预参与活动的信息
function deleteApplyAdvanceActivit(result){
	var AAA=confirm("是否选择删除!");
	if(!AAA){ return false; };
	$.ajax({
		url: "/activity-apply/deleteSelfApplyAdvanceActicity",
		type:"POST",
		data: {"activityId":parseInt(result)},
		success: function(data){
			if(data.status=="true"){
				alert("操作成功!");
			}
			if(data.status=="error"){
				alert(data.msg);
			}
		},
		error: function(){ aler("删除失败!!!"); }
	});
	
};
//预申请修改信息       click-activity-advance-apply-update-modal
function updateApplyAdvanceActivit(obj){
	$("#update-activityTheme1").val($(obj).parents("tr").find("td:eq(0)").html());
	$("#update-activityDescription1").val($(obj).parents("tr").find("td:eq(3)").html());
	$("#click-activity-advance-apply-update-modal").modal('show');
	
	//提交按钮,修改信息
	$("#activity-advance-update-apply-button").on('click',function(){
		if($("#click-activity-advance-apply-update-modal").find("input[name='update-activityPredtime1']").val()==""){
			alert("时间不能为空!");
			return;
		}
		var param={};
		param.activityId=$(obj).attr("data-activity-id");
		param.activityTheme=$("#update-activityTheme1").val();
		param.activityOrgcollege=getCheckboxVal('update-activityOrgcollege1');
		param.activityDescription=$("#update-activityDescription1").val();
		param.activityPredtime=datetimeLocalToDate($("#click-activity-advance-apply-update-modal").find("input[name='update-activityPredtime1']").val()),
		$.ajax({
			url: "/activity-apply/updateSelfApplyAdvanceActicity",
			data: param,
			type: "POST",
			success: function(result){
				if(result.status=="true"){
					alert("修改成功");
				}
				if(result.status=="error"){
					alert(result.msg);
				}
			},
			error: function(){ alert(result.msg);  }
		});
		window.location.reload();
	});
	
};


/*
 *活动审核过程信息表
 */
function applyActivityCheckActivitySelfInfoInit(){
	$("#apply-activity-check-activity").DataTable(
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
	                url: "/activity-apply/show-checkact",//修改路径即可
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
			    ],
			
		});
	
};









