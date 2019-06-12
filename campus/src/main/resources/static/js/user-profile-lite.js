/**
 * user-profile-lite.HTML页面的js代码
 */

/**select->option 年龄显示函数
 * @returns
 */
function selectAge(){
	/* $("#selectAge").append("<option >==请选择==</option>"); */
	    for(var i=16;i<=26;i++){
		$("#userAge").append("<option th:value='"+i+"'>"+i+"</option>");
		//$("#selectAge").append('<option value="'+i+'">'+i+'</option>');
	}  
}

/**
 * 页面主函数 
 */
$(function(){
	// 从button按钮找到---function中的click函数---模态框弹出编辑页面
	$("#update-mymodal-click").on("click",function(){//会执行,但是只是绑定点击事件
		selectAge();
		$("#my-modal-update").modal("show");
	});
	//selectAge();//会执行,初始化数据
	//<option value='2'>2</option>
	
	//个人信息修改参数传递
	$("#update-userinfo-modalbuttom").on("click",function(){
		 var AAA= confirm("确认修改？");
		 if(!AAA){
		  return false;
		 };
		 
		 var param_user={};
		 param_user.userName = $("#userName").val();
		 param_user.userNickname = $("#userNickname").val();
		 param_user.userAge = $("#userAge").val();
		 param_user.userSex = $("input[name='userSex']").val();
		 param_user.userCollege = $("#userCollege").val();
		 param_user.userDescription = $("#userDescription").val();
		 param_user.userPassword=$("#userPassword").val();
		 $.ajax({
			 cache: false,
			 type: "POST",
			 url: "/user/modifyuser",
			 data: param_user,
			 success: function(result){
				 if(result.status=='true'){
					 alert("修改成功!");
				 }else if(result.status=='error'){
					 alert(result.msg);
				 }
				window.location.reload(); 
			 }
		 });
	});
	
	//展示待参加活动
	showWaitJoinActivity();
	
});
/**
 * 展示用户待参加活动详情
 * @returns
 */
function showWaitJoinActivity(){
	$("#show-wait-activity").DataTable(
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
	                url: "/user/showWaitJoinActivity",//修改路径即可
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
			        {"data": 'activityPredjoin'},
			        {"data": 'activityPredtime',
			        	render:function(data){
			        		return data.split("T")[0]+" "+data.split("T")[1].split(".")[0];
			        	}
			        },

			    ]
		});
	
};
