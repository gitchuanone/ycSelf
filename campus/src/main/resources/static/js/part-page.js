$(function(){
//	$("#mytable").DataTable({
//		searching: true, //是否开启搜索功能
//	    ordering: true,//是否排序
//	   	processing: true,//获取数据过程中是否出现加载指示
//	   	bPaginate:true,//是否允许分页
////	   	bInfo:true,//是否显示表格相关信息
////	   	destroy:true,//销毁一个实例
//	   	iDisplayLength:[5,10,20],//分页时每页显示的行数
////	   	bLengthChange:false,
//		striped: true,  // 设置表格隔行变色效果
//	    serverSide: true,//当用ajax请求数据源时，这个属性必须添加，切记
//	    
//		ajax:{ 				//使用ajax记得要引入jquery.min.js
//	    url:'/activity/showAllAct', //请求路径，也就是控制器里方法
//	    type:"post",
//	    async:true,  //异步请求
////	    data:{"authorityId":authorityId} //请求参数
//		},
//		column:[  //绑定的数据源，后台的json数据里必须有这些属性，保持一致性
//			{"actList":"actList.activityId"},
//			{"actList":"activityTheme"},
//			{"actList":"activityPlace"},
//			{"actList":"activityOrgcollege"},
//			{"actList":"activityFinallyscore"},
//		],
////		columnDefs:[
////		    {
////		    targets:5,  //对数据源中的第六行进行渲染
////		    render:function(data,type,row){
////		    var html = '<a href="javascript:cancelUser('+data['id']+');">取消授权</a>';
////		    return html;
////		    }
////		    }	    
////		 ],
//		"oLanguage" : {      // 国际化配置
//            "sProcessing" : "正在获取数据，请稍后...",
//            "sLengthMenu" : "显示 _MENU_ 条",
//            "sZeroRecords" : "没有找到数据",
//            "sInfo" : "从 _START_ 到  _END_ 条记录 总记录数为 _TOTAL_ 条",
//            "sInfoEmpty" : "记录数为0",
//            "sInfoFiltered" : "(全部记录数 _MAX_ 条)",
//            "sInfoPostFix" : "",
//            "sSearch" : "查询",
//            "sUrl" : "",
//            "oPaginate" : {
//                "sFirst" : "第一页",
//                "sPrevious" : "上一页",
//                "sNext" : "下一页",
//                "sLast" : "最后一页"
//            }
//        },
//		
//	})
	$.ajax({
		 cache: false,
		 type: "POST",
		 url: "/activity/showAllAct",
		 success: function(result){
			 if(result.status=='true'){
				 initTable(result.object);
			 }else if(result.status=='error'){
				 alert(result.msg);
			 }
		 },
		 error:function(result){
			 alert("请求列表失败");
		 }
	 });
});
function initTable(list){
	for(var i=0;i<list.length;i++){
		$("#table_tbody").append(
			'<tr class="odd gradeX">'+
             '   <td>'+list[i].activityOrganizer+'</td>'+
             '   <td>'+list[i].activityOrgcollege+'</td>'+
               ' <td>'+list[i].activityOrganizer+'</td>'+
             '   <td class="center"> '+list[i].activityTheme+'</td>'+
             '   <td class="center">'+list[i].activityProcesstime+'</td>'+
          '  </tr>'
		);
	}
}