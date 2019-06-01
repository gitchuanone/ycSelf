$(function(){
	
	$("#addmanage-submit").on('click',function(){
		var name=$("#add-name").val();
		var pwd=$("#add-pwd").val();
		var email=$("#add-email").val();
		var phone=$("#add-phone").val();
		var desc=$("#add-description").val();
		if(name==null || name==""){
			alert("账户不能为空");
			return ;
		};
		if(pwd==null || pwd==""){
			alert("密码不能为空");
			return ;
		};
		//
		var param={};
		param.magName=name;
		param.magPassword=pwd;
		param.magEmail=email;
		param.magPhone=phone;
		param.magDescription=desc;
		$.ajax({
			type: "POST",
			url: "/manage/addNewManage",
			data: param,
			success: function(result){
				if(result.status=="true"){  
					alert("操作成功!!!");
					window.location.reload();
				};
				if(result.status=="error"){  alert(result.msg);  };
			},
			error: function(){   alert("操作失败!!!");   }
		});
		
	});
	
});