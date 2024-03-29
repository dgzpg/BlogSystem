var globalCount = 0;
$("#fakeloader").fakeLoader({
	timeToHide : 10000, //Time in milliseconds for fakeLoader disappear
	zIndex : 999, // Default zIndex
	spinner : "spinner6", //Options: 'spinner1', 'spinner2', 'spinner3', 'spinner4', 'spinner5', 'spinner6', 'spinner7' 
	bgColor : "#fff", //Hex, RGB or RGBA colors
});

setTimeout(function() {
	$('body').css('opacity', '1');
	$('body').attr("class", "gray-bg") //添加样式
}, 100);

$(document).ready(function() {
	//参数1表示当前页为1
	initBlog(1);

});

var returnAllCount = function() {
	if (globalCount == 1) {
		setTimeout(function() {
			$('#fakeloader').css('display', 'none');
		}, 500);
	}
}

var initBlog = function(pageNum) {
	//查询出文章
	//获取关键字，表示查询所有符合的记录
	//keyword : $(".form-control").val().replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, "&quot;").replace(/'/g, "&#039;"),	
	
	var params = {
		param:'关键字为#'+$(".form-control").val().replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, "&quot;").replace(/'/g, "&#039;")+'#',
		user : 1,
		keyword : $(".form-control").val().replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, "&quot;").replace(/'/g, "&#039;"),	
		pageSize : 5,
		page : pageNum,
		title : $(".form-control").val().replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, "&quot;").replace(/'/g, "&#039;"),
		status : 1 //1 表示已发布
	};
	$.ajax({
		url : 'selectBlogListByPage',
		type : 'get',
		data : params,
		dataType : 'json',
		success : function(data) {
			var blogList = '';
			//var page = data.pageInfo;
			var pageNum=data.number+1;//当前是第几页，
			var datas = data.content;  //所有数据
			var total = data.totalElements;  //数据总共有的条数
			var totalPages = data.totalPages;//总共有几页数据
			var currentNum=data.numberOfElements;
			var circle = new Array("text-navy", "text-danger", " text-info", "text-primary", "text-warning");
			for (var i = 0; i < currentNum; i++) {
				var time = 0.03 * i;
				blogList += '<li class="animated fadeInDown" style="margin: 0 0 5px 0;animation-delay:' + time + 's""><a href="javascript:void(0);" style="padding: 0;" onclick="findBlogById(' + datas[i].blogid + ')"> <i style="width:14px;height: 15px;" class="fa ' + circle[i % 5] + ' fa-circle "></i> ' + datas[i].title + '</a></li>';
			}
			//当前页数大于2，显示下面的
			if (pageNum >= 2) {
				$(".category-list").append(blogList);
			} else {
				$(".category-list").html(blogList);
				findBlogById(datas[0].blogid);
				//显示总的已发表博客数
				$(".allTotal").find("b").html(total);
				//总共的页数
				$(".allPage").find("b").html(totalPages);
			}
			
			var nextPage = pageNum + 1;
			//pageNum=pageNum+1;
			var more = '<p style="text-align:center"><a onclick="pageNav(' + nextPage + ',' + totalPages + ')"><i class="fa fa-arrow-down"></i> 加载更多</a></p>';
			if (pageNum == totalPages) {
				more = '<p style="text-align:center"><a href="javascript:void(0);" onclick="window.scrollTo(0,0)"><i class="fa fa-arrow-up"></i> 没有更多了</a></p>';
			}
			$(".pageNav").html(more);
			//当前是第几页
			$(".cPage").find("b").html(pageNum);

		},
		error : function() {
			swal("您的请求太快", "请重新操作", "error");
		}
	});
	globalCount++;
	returnAllCount();
};

var pageNav = function(pageNum, allPage) {
	if (pageNum <= 0) {
		swal("查询失败", "当前为第1页", "error");
	} else if (pageNum > allPage) {
		swal("查询失败", "当前为最后一页", "error");
	} else {
		initBlog(pageNum);
	}
}

var findBlogByKey = function() {
	if ($(".form-group").find(".form-control").val() != "") {
		initBlog(1);
	}

}


var findBlogById = function(id) {
	var params = {
		id : id
	};
	$.ajax({
		url : 'selectBlogById',
		type : 'get',
		data : params,
		dataType : 'json',
		success : function(data) {
			//初始化右边详情内容
			$(".newsview").find(".news_title").html(data.blog.title);
			$(".newsview").find(".au02").html(data.blog.publishedTime);
			$(".au03").find('b').html(data.blog.pageView);
			$(".news_about").find(".news_intr").html(data.blog.introduction);
			var keyword = "";
			var keyWord=data.blog.keyWord;
			$(".newsview").find(".tags").html("");
			if (keyWord != '' && keyWord != null) 
			{
				
				var word="";
				for(var i=0;i<keyWord.length;i++)
				{
					if(keyWord[i]=='；')
					{
						keyword+='<a href="#">' + word + '</a>';
						word="";
					}
					else
					{
						word+=keyWord[i];
					}
				}
				if(word!="")
				{
					keyword+='<a href="#">' + word + '</a>';
				}
				
			}
			$(".newsview").find(".tags").append(keyword);
			$(".newsview").find(".news_infos").html(data.blog.content);
			$('pre').each(function(i, block) {
				hljs.highlightBlock(block);
			});
		},
		error : function() {
			swal("初始化内容失败", "请重新操作", "error");
		}
	});

};

//格式化时间
function Format(datetime, fmt) {
	if (parseInt(datetime) == datetime) {
		if (datetime.length == 10) {
			datetime = parseInt(datetime) * 1000;
		} else if (datetime.length == 13) {
			datetime = parseInt(datetime);
		}
	}
	datetime = new Date(datetime);
	var o = {
		"M+" : datetime.getMonth() + 1, //月份   
		"d+" : datetime.getDate(), //日   
		"h+" : datetime.getHours(), //小时   
		"m+" : datetime.getMinutes(), //分   
		"s+" : datetime.getSeconds(), //秒   
		"q+" : Math.floor((datetime.getMonth() + 3) / 3), //季度   
		"S" : datetime.getMilliseconds() //毫秒   
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (datetime.getFullYear() + "").substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
}