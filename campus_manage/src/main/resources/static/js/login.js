$(function(){
		$("#login-button").on('click',function(){
			var param={};
			param.name=$("#inputName").val();
			param.password=$("#inputPassword").val();
			$.ajax({
				type: "POST",
				url: "/manage/login",
				data: param,
				success: function(data){
					if(data.status == "true"){  
						alert("恭喜登录成功!");
						window.location.href="index-home.html";
					};
					if(data.status == "error"){  
						alert(data.msg);
					};
				},
				error: function(data){
					alert(data.msg);
				}
			}) ;
			
			
		});
		
	});