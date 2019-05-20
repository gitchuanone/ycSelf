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
	
	
});


