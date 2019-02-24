var pageNext = 1;
var isEnd = false;
var globalCount = 0;
var type=1;
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
	selectLogByDate(pageNext, null, null,1);
});

var returnAllCount = function() {
	if (globalCount == 1) {
		setTimeout(function() {
			$('#fakeloader').css('display', 'none');
		}, 500);
	}
}
$(window).scroll(function() {
	if (isEnd == true) {
		return;
	}
	if ($(document).scrollTop() + 50 >= $(document).height() - $(window).height()) {
		isEnd = true;
		setTimeout(function() {
			selectLogByDate(pageNext, $('#start').val(), $('#end').val(),type);
		}, 500);
	}
});

var reset = function() {
	var index = '';
	layer.ready(function() {
		index = layer.load(2, {
			shade : [ 0.1, '#eee' ] //0.1透明度的白色背景
		});
	});
	$('#start').val("");
	$('#end').val("");
	$('.keyword').val("");
	$(".refresh").find("i").addClass("fa-spin");
	selectLogByDate(1, null, null,0);
	setTimeout(function() {
		layer.close(index);
		setTimeout(function() {
			$(".refresh").find("i").removeClass("fa-spin");
		}, 500);
	}, 500);
};

var fastToSearch = function(fastKey,num) {
	$(".keyword").val(fastKey);
	type=num;
	selectLogByDate(1,$('#start').val(), $('#end').val(),type);
}

//指定日期查询事件
var selectLogByDate = function(pageNum, startTime, endTime,type) {
	var keyword = $(".keyword").val();
	var params = {
		startTime : startTime,
		endTime : endTime,
		pageSize : 10,
		page : pageNum,
		keyWord : keyword,
		type : type,
	
	};
	$.ajax({
		url : '../selectLogList',
		type : 'post',
		data : params,
		dataType : 'json',
		success : function(data) {
			if (data.content!= null) {
				//当前页数
				var pageNum=data.number+1;
				var pages=data.totalPages;
				var visitors = data.content;
				var total=data.totalElements
				var logList = '';
				var now = new Date().getTime();
				var dateToAddTime = '';
				var minutes = 1000 * 60;
				var time = 0;
				var timeStr = '';
				var userType="";
				//var logList=new Array();
				for (var i = 0; i < visitors.length; i++) {
					
					//计算现在时间与日志时间的差值
					/*var visitTime=visitors[i].visitTime;
					visitTime=visitTime.replace(/-/g,'/');
					var date=new Date(visitTime);
					dateToAddTime = now - date ;
					time = parseInt(dateToAddTime / minutes);
					if (time < 60) {
						if (time == 0) {
							timeStr = '<span class="label label-danger"> 刚刚</span>';
						} else {
							timeStr = '<span class="label label-info">' + time + '分钟前</span>';
						}
					} else {
						timeStr = '<span class="label label-primary">' + parseInt(time / 60) + '小时前</span>';
						if (time >= 1440) {
							timeStr = '<span class="label label-warning">' + parseInt(time / 1440) + '天前</span>';
							if (time >= 43200) {
								timeStr = '<span class="label label-success">' + parseInt(time / 43200) + '个月前</span>';
							}
						}
					}*/
					timeStr = '<span class="label label-danger"> 刚刚</span>';
					if (visitors[i].status == 1){
						userType = "管理员" + '&nbsp;&nbsp;&nbsp;&nbsp;';
					}
					else
					{
						userType = "普通人员" + '&nbsp;&nbsp;&nbsp;&nbsp;';
					}
					
					
					var param=visitors[i].params;
					var p = visitors[i].param;
					if (visitors[i].operate == "查看博客") {
						visitors[i].operate = '查看的博客ID为：<a class="text-info" title="点击查看对应博客"  data-toggle="modal" data-target="#myModal" onclick="selectBlogById(' + visitors[i].params + ')">' + visitors[i].params + '</a>';
						param = "";
					} else {
						if (param.length > 50) {
							param = param.substring(0, 5) + "...";
						}
					}
					var time = i * 0.03;
					logList += '<li style="animation-delay:' + time + 's" class="list-group-item  animated fadeInDown"><p style="margin: 4px 0px;">' + timeStr + '<strong>&nbsp;&nbsp;<i class="fa fa-user"> ' + userType + '</i>&nbsp;</strong><a class="text-info" href="#" onclick="fastToSearch(\'' + visitors[i].ip + '\')">@' + visitors[i].ip + '</a>  &nbsp;<strong><small class="text-muted"><i class="fa fa-tag"> </i> </small>' + visitors[i].operate + '</strong><span title=\'' + visitors[i].param + '\'>：' + param + '</span><span  title="' + visitors[i].visitTime + '" class="pull-right"><i class="fa fa-clock-o"> ' + visitors[i].visitTime + '</i></span></p></li>'
				}
				//数据当前页数
				if (pageNum >= 2) {
					$(".logList").append(logList);
				} else {
					$(".logList").html(logList);
				}
				//
				if (total > 9) {
					var pagenav = '';
					
					if (pageNum == pages) {
						isEnd = true;
						pagenav = '<li class="list-group-item"><p style="text-align:center"><a href="javascript:void(0);" onclick="window.scrollTo(0,0)"><i class="fa fa-arrow-up"></i> 没有更多了</a></p></li>';
					} else {
						isEnd = false;
						pageNext = pageNum + 1;
						pagenav = '<div style="margin:10px auto;text-align:center;"><div class="loader-inner ball-pulse"><div></div><div ></div><div></div></div></div>';
					}
					$(".page").html(pagenav);
				} else {
					$(".page").html('<li class="list-group-item"><p style="text-align:center"><a href="javascript:void(0);" onclick="window.scrollTo(0,0)"><i class="fa fa-arrow-up"></i> 没有更多了</a></p></li>');
				}

			} else {
				swal("查询失败", "不存在该日志信息", "error");
				$('#start').val("");
				$('#end').val("");
			}
		},
		error : function() {
			swal("查询错误", "请重新操作", "error");
			$('#start').val("");
			$('#end').val("");
		}
	});
	globalCount++;
	returnAllCount();
};

var pageNav = function(page) {
	selectLogByDate(page, $('#start').val(), $('#end').val(),0);
}

$('#start').datepicker({
	keyboardNavigation : false,
	forceParse : false,
	autoclose : true,
	format : 'mm/dd/yyyy',
	todayHighlight : true,
	endDate : new Date(),
}).on('changeDate', function(ev) {
	var start = ev.date.valueOf();
	$('#start').val(Format(start, "yyyy-MM-dd"));
	if ($('#end').val() != "" && $('#start').val() > $('#end').val()) {
		swal("指定日期范围出错", "请重新检查", "error");
	} else if ($('#start').val() != "" && $('#end').val() != "") {
		type=0;
		selectLogByDate(1, $('#start').val(), $('#end').val(),type);
	}
});

$('#end').datepicker({
	keyboardNavigation : false,
	forceParse : false,
	autoclose : true,
	format : 'mm/dd/yyyy',
	todayHighlight : true,
	endDate : new Date(),
})
	.on(
		'changeDate',
		function(ev) {
			var end = ev.date.valueOf();
			$('#end').val(Format(end, "yyyy-MM-dd"));
			if ($('#start').val() != ""
				&& $('#start').val() > $('#end').val()) {
				swal("指定日期范围出错", "请重新检查", "error");
			} else if ($('#start').val() != ""
				&& $('#end').val() != "") {
				type=0;
				selectLogByDate(1, $('#start').val(), $('#end')
					.val(),type);
			}

		});

var selectBlogById = function(vid) {
	var id;
	/*for (var i = 1; i <= vid; i++) {
		if (i * i.toString(8) == vid) {
			id = i;
			break;
		}
	}*/
	var param = {
		id : vid
	}
	$.ajax({
		url : '../selectBlogById',
		type : 'get',
		data : param,
		dataType : 'json',
		success : function(data) {
			//查询成功
			var blog=data.blog;
			var keyWord=blog.keyWord;
			if (blog != null) {
				$(".newsview").find(".news_title").html(blog.title);
				$(".newsview").find(".au02").html(blog.publishedTime);
				$(".au03").find('b').html(blog.pageView);
				$(".news_about").find(".news_intr").html(blog.introduction);
				var keyword = '';
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

				$(".newsview").find(".news_infos").html(blog.content);
			}
			$('pre').each(function(i, block) {
				hljs.highlightBlock(block);
			});
		},
		error : function() {
			swal("查询错误", "请重新操作", "error");
		}
	});
}


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
		"S" : datetime.getMilliseconds()
	//毫秒   
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (datetime.getFullYear() + "")
			.substr(4 - RegExp.$1.length));
	for (var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1,
				(RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k])
					.substr(("" + o[k]).length)));
	return fmt;
}