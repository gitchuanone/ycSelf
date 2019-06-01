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
	//将datetime-local转换为Date
	var date = new Date();
    date.setFullYear(parseInt(datetime.substring(0, 4)));
    date.setMonth(parseInt(datetime.substring(5, 7)) - 1);
    date.setDate(parseInt(datetime.substring(8, 10)));
    date.setHours(parseInt(datetime.substring(11, 13)));
    date.setMinutes(parseInt(datetime.substring(14, 16)));
    return date;
}
/**
 * 获取时间格式为 yyyy-MM-dd hh:MM:ss
 * @returns
 */
function getDateTime(data){
	//  "#input-start-time"
	var getuserFoundtime=datetimeLocalToDate($(data).val());
	var d = new Date(getuserFoundtime);
	var userFoundtime=d.getFullYear() + '-' + (d.getMonth() + 1) + '-' + d.getDate() + ' ' + d.getHours() + ':' + d.getMinutes() + ':' + d.getSeconds();
}

/***
* 用户基本信息管理页面;页面加载主函数
 * @returns
 */
$(function(){
	//用户信息初始化
	initManageBasicInfo();
	
});
/**
 * 初始化用户信息表
 * @returns
 */
function  initManageBasicInfo(){
	$("#show-all-manage").DataTable(
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
	                url: "/manage/showAllManage",//修改路径即可
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
			        {"data":'magName'},
			        {"data":'magPassword'},
			        {"data":'magNickname'},
			        {"data":'magEmail'},
			        {"data":'magPhone'},
			        {"data":'magDescription',
			        	"orderable" : false
			        },
			        {"data":'magFoundtime' //截取时间格式显示
//			        	render:function(data){
//			        		return data.split("T")[0]+" "+data.split("T")[1].split(".")[0];
//			        	}
			        },
			        {"data":'magId',
			        	render : function(data){
			        		return '<button type="button" data-user-id1="'+data+'" onclick="removeManageByManageId(this)" >删除</button>';
			        	}
			        },
			    ],
			    
		});
};
/**
 * 删除用户
 */
function removeManageByManageId(obj){
	var AAA=confirm("确认删除?");
	if(!AAA){  return ;  };
	//获取id值
	var user=$(obj).attr("data-user-id1");
	//
	$.ajax({
		url: "/manage/removeManageByManageId",
		data: {"magId":parseInt(user)},
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
