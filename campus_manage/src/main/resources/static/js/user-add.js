$(function(){
	
	$("#adduser-submit").on('click',function(){
		var name=$("#adduser-name").val();
		var pwd1=$("#adduser-pwd1").val();
		var pwd2=$("#adduser-pwd2").val();
		if(name==null || name==""){
			alert("账户不能为空");
			return ;
		};
		if(pwd1==null || pwd1=="" || pwd2==null || pwd2==""){
			alert("密码不能为空");
			return ;
		};
		if(pwd2 != pwd1){
			alert("两次输入密码不一致");
			return ;
		};
		//
		var param={};
		param.userName=name;
		param.userPassword=pwd1;
		$.ajax({
			type: "POST",
			url: "/user/adduser",
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