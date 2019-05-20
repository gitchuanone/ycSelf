$(function(){
     $('#myTable').DataTable(
    		{
    		"ordering": true,
		    "pageLength": 10,　//初始化显示几条数据
		    "pagingType": "full_numbers",
		    "lengthMenu": [10, 25, 50, 60],
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
		    ajax:{
                url: "findData",//修改路径即可
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
		        {"data":'name'},
		        {"data":'age'},
		        {"data":'sex'},
		        {"data":'school'},
		        {"data":'hobby'},
		        {"data":'operation',
		        	render:function(){
		        		return '<div><span>修改</span>&nbsp;&nbsp;<span>删除</span></div>';
		        	}
		        }
		    ]
     });
});