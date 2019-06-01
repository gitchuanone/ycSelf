$(function(){
	
	$("#addcollege-submit").on('click',function(){
		var collegename=$("#add-college").val();
		if(collegename=="" || collegename==null){
			alert("输入的信息为空");
			return ;
		}
		var AAA=confirm("确认新增?");
		if(!AAA){  return ;  };
		var param={};
		param.collegeName=collegename;
		$.ajax({
			type: "POST",
			url: "/college/addNewCollege",
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