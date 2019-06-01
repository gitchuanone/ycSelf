/***
 * 用户基本信息管理页面;页面加载主函数
 * @returns
 */
$(function(){
	//用户信息初始化
	initCollegeBasicInfo();
	
});
/**
 * 初始化用户信息表
 * @returns
 */
function  initCollegeBasicInfo(){
	$("#show-all-college").DataTable(
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
	                url: "/college/showAllCollege",//修改路径即可
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
			       {"data":"collegeName"},
			       {"data":"collegeState",
			    	  render: function(data){
			    		  if(data==null || data==0){
			    			  return "撤销";
			    		  };
			    		  if(data==1){
			    			  return "现存";
			    		  };
			    	  }
			       },
			        {"data":'collegeId',
			        	render : function(data){
			        		return '<button type="button" data-user-id1="'+data+'" onclick="removeCollege(this)" >删除</button>';
			        	}
			        },
			    ]
		});
	
};
/**
 * 删除学院
 * @returns
 */
function removeCollege(obj){
	var AAA=confirm("确认删除用户?");
	if(!AAA){  return ;  };
	//获取id值
	var user=$(obj).attr("data-user-id1");
	alert(user);
	//
	$.ajax({
		url: "/college/removeCollegeByCollegeId",
		data: {"collegeId":parseInt(user)},
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




