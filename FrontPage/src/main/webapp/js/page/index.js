
var globalCount = 0;
$(".top").click(function() {
  $('body,html').animate({
    scrollTop : 0
  }, 1000);
  return false;
});

var pageNext = 1;
var count = 1;
var isEnd = false;
var time = null;
var width = window.innerWidth || document.documentElement.clientWidth;
if (width < 660) {
  var pagenav = '<p style="text-align:center;margin:-5px auto 20px;"><a href="javascript:void(0);" onclick="initBlogByClickMore()"><i class="fa fa-arrow-down"></i> 加载更多</a></p>';
  $(".pageMin").html(pagenav);
//$(".top").css("display", "none");
}

function init() {
  //实现弹出广告的效果
  //1.设置定时效果
 // time = setInterval("showAd()", 12000);
}
//2.书写显示广告图片的函数
function showAd() {
  /*//3.获取广告图片的位置
  var adEle = document.getElementById("image2");
  //4.修改广告图片元素里面的属性让其显示
  adEle.style.display = "block";*/
  //showAdToGitEE();
  //5.清除显示图片的定时操作
  clearInterval(time);
//6.设置隐藏图片的定时操作
//time = setInterval("hiddenAd()", 3000);
}

/*//7.书写隐藏广告图片的函数
function hiddenAd() {
  //8.获取广告图片并设置其style属性的display值为none
  document.getElementById("image2").style.display = "none";
  //9.清除隐藏广告图片的定时操作
  clearInterval(time);
}*/
/*
function showAdToGitEE() {
  swal({
    title : '前往开源地址 :)',
    text : '此系统已在gitee/github上开源,将全面介绍此系统的管理端,技术栈;如果您觉得不错，欢迎前往gitee上 【Star，Fork】 ',
    type : 'info',
    showCancelButton : true,
    confirmButtonColor : "#1c84c6",
    confirmButtonText : "前往",
    cancelButtonText : "取消",
    timer:8000,
    closeOnConfirm : false,
    closeOnCancel: false
  }, function(isConfirm) {
    if (isConfirm) {
      window.open("https://gitee.com/luotf/Art_Blog");
      swal.close()
    } else {
      swal({
        title:"确定不去看看吗？", 
        text:"你的访问就是对博主最大的支持，谢谢 ^-^", 
        type:"info",
        confirmButtonColor : "#1c84c6",
        showCancelButton : true,
        confirmButtonText : "前往",
        cancelButtonText : "取消",
        },function(isConfirm){
          if (isConfirm) {
            window.open("https://gitee.com/luotf/Art_Blog");
          }
          swal.close()
      });
    }
  });
}*/

$(window).scroll(
  function() {
    if (isEnd == true) {
      return;
    }
    if ($(document).scrollTop() > 110 && count == 1) {
      $(".dj").css("display", "block");
      //initBlogByClick(); //初始化点击排行5篇文章
      count++;
    }
    if ($(document).scrollTop() > 350 && count == 2) {
      $(".newblogs").css("display", "block");
      initBlogByNew(1); //初始化最新5篇文章
      count++;
    }
    if ($(document).scrollTop() > 450 && count == 3) {
      $(".git").css("display", "block");
      count++;
    }
    if ($(document).scrollTop() > 570 && count == 4) {
      $(".weixin").css("display", "block");
      count++;
    }
    if ($(document).scrollTop() > 750 && count == 5) {
      $(".link").css("display", "block");
    //  initAllLinks(); //初始化所有已上架友链
      count++;
    }
    if ($(document).scrollTop() > 1050 && width > 700) {
      $(".top").addClass('cd-is-visible fadeIn');
    } else {
      $(".top").removeClass('cd-is-visible fadeOut');
    }
    if ($(document).scrollTop() > 2200 && width > 700) {
      $(".guanzhu").css("display", "block");
    } else {
      $(".guanzhu").css("display", "none");
    }
    if ($(document).scrollTop() + 100 >= $(document).height()
      - $(window).height()
      && width > 700) {
      isEnd = true;
      $('.page').css('display', 'block');
      setTimeout(function() {
        initBlogByNew(pageNext);
      }, 500);
    } else {
      $('.page').css('display', 'none');
    }
  });

$(document).ready(function() {
 // initAllBlogTypeFroList();   //初始化开始的所有文章类型
  initBlogByTop(); //初始化置顶的3篇文章
  initBlogByAllTypeBlog();
  initBlogByLike(); //初始化特别推荐6篇文章
  initBlogByClick(); //初始化点击排行5篇文章
  
  init();
});


var initAllBlogTypeFroList= function(){
 //查询出文章类别
    //设置参数，表示查询所有的类别
   
    $.ajax({
      url : 'selectBlogTypeForList',
      type : 'get',
     // data : params,
      dataType : 'json',
      success : function(data) {
        var typeName = '';
        for (var i = 0; i < data.length; i++) {
          typeName += "<li><a href='/result?keyboard=type_"+data[i].typeId +"'>"+ data[i].type + "</a></li>";
          //typeName += '<a onclick="searchType(' + data[i].id + ',\'' + data[i].typename + '\')" href="javascript:void(0);">' + data[i].typename + '</a> '
        }
        $(".sub").html(typeName);
      },
      error : function() {
        layer.msg('请求太快，请稍后再试！', {
          icon : 5
        });
      }
    });
  } 

//初始化点击排行的
var initBlogByClickMore = function() {
  setTimeout(function() {
    initBlogByNew(pageNext);
  }, 200);
}

var returnAllCount = function() {
  if (globalCount == 2) {
    setTimeout(function() {
      $('article').css('opacity', '1');
    }, 200);
  }
}


//热门文章
var initBlogByTop = function() {
  //设置参数
  var params = {
    pageSize : 6,
    page : 1,
    status : 2,
  };
  $.ajax({
    url : 'getBlogListBySpecifitPage',
    type : 'get',
    data : params,
    dataType : 'json',
    success : function(data) {
      var topBlog = '';
      var data = data.content;
      for (var i = 0; i < data.length; i++) {
      
       // topBlog += '<li><a href="selectBlogById?id=' + data[i].blogid + '" title=' + data[i].title + ' target="_blank">' + data[i].title + '</a></li>';
      topBlog += '<li class="animated fadeIn"><a href="selectBlogById?id=' +  data[i].blogid  + '&user=2" onclick=""><img style="width:415px;height:155px;" src="' + data[i].picturePath + '"></a><span>'
        + data[i].title + '</span></li>'
      }
      // 初始化数据
      $(".notice").find("ul").html(topBlog);
      globalCount++;
      returnAllCount();
    },
    error : function() {
      layer.msg('请求太快，请稍后再试！', {
        icon : 5
      });
    }
  });
};


//初始化前5个类别的前6个博客
var initBlogByAllTypeBlog = function() {

  $.ajax({
    url : 'selectBlogByAllType',
    type : 'get',
    dataType : 'json',
    success : function(data) {
      var likeBlog = '';
     
      var tab_button = "";
      var newsitem = "";
      var indexTab = 0;
      //显示5个类别
      for (var i=0;i<data.length;i++) {
        if (indexTab == 0) {
          tab_button += "<li class='newscurrent'>" + data[i].type + "</li>"
        } else {
          tab_button += "<li>" + data[i].type + "</li>"
        }
        indexTab++;
      }
      $(".tab_buttons ul").html(tab_button);
      var index = 0;
      for (var j=0;j<data.length;j++) {             
        var newspic = "";
        var newslist = "";
        if (index == 0) {
          newsitem += "<div class='newsitem' style='display: block;'><div class='newspic'><ul>";
        } else {
          newsitem += "<div class='newsitem' style='display: none;'><div class='newspic'><ul>";
        }
        index++;
        newslist += "<ul class='newslist'>"
        var blogs=data[j].blogs;
        for (var i = 0; i < blogs.length; i++) {
          //var id = data[j][i].blogid.toString(8) * data[type][i].id;
        	var id=blogs[i].blogid;
          if (i < 2) {
            newspic += "<li> <a href=selectBlogById?id=" + id + "&user=2 target='_blank'><img src='" + blogs[i].picturePath + "'> <span>" + blogs[i].title + "</span></a></li>";
          }
          if (i > 1) {
            newslist += "<li><i></i><a href=selectBlogById?id=" + id + "&user=2 target='_blank'>" + blogs[i].title + "<p>" + blogs[i].introduction + "</p></a></li>";
          }
        }
        newspic += "</ul></div>"
        newsitem += newspic;
        newsitem += newslist;
        newsitem += "</ul></div>"
      }
      $('.newstab').html(newsitem);
    },
    error : function() {
      layer.msg('请求太快，请稍后再试！', {
        icon : 5
      });
    }
  });
};


//初始化推荐
var initBlogByLike = function() {
  //设置参数
  var params = {
    pageSize : 6,
    page : 1,
   
    status : 3,
  };
  $.ajax({
    url : 'getBlogListBySpecifitPage',
    type : 'get',
    data : params,
    dataType : 'json',
    success : function(data) {
      var likeBlog = '';
      var data = data.content;
      for (var i = 0; i < data.length; i++) {
        var id = data[i].blogid;
        var time = i * 0.05;
        likeBlog += '<li style="animation-delay:0.' + i + 's" class="animated fadeIn"><i class="ztpic"><a target="_blank" href="selectBlogById?id=' + id + '&user=2" ><img src="' + data[i].picturePath + '"></a></i><b>'
          + data[i].title
          + '</b><span>'
          + data[i].introduction
          + '</span><a href="selectBlogById?id=' + id + '&user=2" target="_blank" class="readmore">阅读原文</a></li>'
      }
      // 初始化数据
      $(".zhuanti").find("ul").html(likeBlog);
      globalCount++;
      returnAllCount();
    },
    error : function() {
      layer.msg('请求太快，请稍后再试！', {
        icon : 5
      });
    }
  });
};

//初始化最新文章
var initBlogByNew = function(page) {
  //设置参数
  var params = {
    pageSize : 5,
    page : page,
    status : 1,
  };
  $
    .ajax({
      url : 'getBlogListBySpecifitPage',
      type : 'get',
      data : params,
      dataType : 'json',
      success : function(data) {
    	  
        var newBlog = '';
        var parm = "";
      //  var arr = new Array();
       // var pageNum=data.number+1;   //当前是第几页
        
        
       // var data = data.content;     //所有数据
        var total=data.totalElements;   //数据总共有多少条
        var totalPages=data.totalPages;   //总共有几页数据
       
        //$(".newblogs").find("ul").html(total);
        for (var i = 0; i < data.content.length; i++) {
         /* arr[i] = data.content[i].blogid;
          parm += data.content[i].blogid + ",";*/
         // var id = data[i].blogid.toString(8) * data[i].blogid;
          var keyword = "";
          if (data.content[i].keyWord != ""
            && data.content[i].keyWord != null) {
            if (data.content[i].keyWord.search('；') != -1) {
              keyword = data.content[i].keyWord.replace(/;/g,
                "|");
            } else {
              keyword = data.content[i].keyWord;
            }
          }
          newBlog += '<li style="animation-delay:0.' + i + 's" class="animated fadeInDown"><h3 class="blogtitle"><a target="_blank" href="selectBlogById?id=' + data.content[i].blogid + '"  >'
            + data.content[i].title
            + '</a></h3><span class="blogpic imgscale"><a href="selectBlogById?id=' + data.content[i].blogid + '" title=""><img src="' + data.content[i].picturePath + '"  /></a></span><p class="blogtext">'
            + data.content[i].introduction
            + '</p><p class="bloginfo"><i class = "avatar"><img src="images/image_.jpg" border=0 width="30" height="30"></i><span>luotf</span><span><a href="javascript:void(0);">【'
            + keyword
            + '】</a></span><span class="m_time">'
            + data.content[i].publishedTime
            + '</span><span  class="clicknum">浏览('
            + data.content[i].pageView
            + ')</span><span class="f_r"></p><a href="selectBlogById?id=' + data.content[i].blogid + '&user=2"target="_blank" class="viewmore">阅读原文</a></span></li>'
        }
      
      /*  var p = {
          client_id : 'cytzg9rLH',
          topic_source_id : parm
        };
        $
          .ajax({
            url : 'http://changyan.sohu.com/api/2/topic/count',
            type : 'get',
            data : p,
            dataType : 'jsonp',
            success : function(pl) {
              for (var i = 0; i < arr.length; i++) {
                $('.' + arr[i])
                  .html(
                    pl.result[arr[i]].comments);
              }
            },
            error : function() {
              layer.msg('出错啦', {
                icon : 2
              });
            }
          });*/
        // 初始化数据
       // $(".newblogs").find("ul").html(newBlog);
        if (data.number >= 1) {
            $(".newblogs").find("ul").append(newBlog);
          } else {
            $(".newblogs").find("ul").html(newBlog);
          }
       
          if (data.totalElements > 5) {
            var pagenav = '';
            var pageNum=data.number+1;
            if (pageNum == data.totalPages) {
              isEnd = true;
              pagenav = '<p style="text-align:center;margin:-5px auto 10px;"><a href="javascript:void(0);" onclick="window.scrollTo(0,0)"><i class="fa fa-arrow-up"></i> 没有更多了</a></p>';
              if (width < 660) {
                $(".pageMin").html(pagenav);
              }
            } else {
              isEnd = false;
              pageNext = pageNum + 1;
              pagenav = '<div style="margin:-5px auto 10px;text-align:center;"><div class="loader-inner ball-pulse"><div></div><div></div><div></div></div></div>';
            }
            $(".page").html(pagenav);
          } else {
            $(".page").html("");
          }

        },
      error : function() {
        layer.msg('请求太快，请稍后再试！', {
          icon : 5
        });
      }
    });
};

//初始化点击排行
var initBlogByClick = function() {
  //设置参数
  var params = {
    pageSize : 5,
    page : 1,
   
  };
  $
    .ajax({
      url : 'selectBlogByPageView',
      type : 'get',
      data : params,
      dataType : 'json',
      success : function(data) {
        var clickBlog = '';
        var data = data.content;
        var time = '';
        for (var i = 0; i < data.length; i++) {
          var id = data[i].blogid;
          time = i * 0.05;
          clickBlog += '<li style="animation-delay:0.' + i + 's" class="animated fadeIn"><b><a target="_blank" href="selectBlogById?id=' + id + '&user=2">'
            + data[i].title
            + '</a></b><p><i><img src="' + data[i].picturePath + '"></i><span>'
            + data[i].introduction + '</span></p></li>'
        }
        // 初始化数据
        $(".paihang").find("ul").html(clickBlog);
      },
      error : function() {
        layer.msg('请求太快，请稍后再试！', {
          icon : 5
        });
      }
    });
};

/*
//初始化友情链接
var initAllLinks = function() {

  $.ajax({
    url : 'selectAllLinks',
    type : 'get',
    data : "",
    dataType : 'json',
    success : function(data) {
      var linksAll = '';
      var data = data.linksList;
      var time = '';
      for (var i = 0; i < data.length; i++) {
        time = i * 0.05;
        linksAll += '<li style="animation-delay:0.'
          + i
          + 's;float:left;margin: 0 1% 10px 0;padding:3px;" class="animated fadeIn"><a target="_blank" href="'
          + data[i].link
          + '" target= "_blank" onclick="clickNum('
          + data[i].id + ')">' + data[i].name
          + '</a></li>';
      }
      // 初始化数据
      $(".link").find("ul").html(linksAll);
      time = time + 0.1;
      var msg = '<h5 style="animation-delay:' + time + 's" class="animated fadeIn" title="QQ:849673404">注：添加友链,请点击&nbsp;&nbsp;&nbsp;<a class="applyLinks" onclick="applyLinks()" href="javascript:void(0);" style="font-size:13px;color:#f8ac59">申请友链</a></h5>';
      $(".msg").find("a").html(msg);
    },
    error : function() {
      layer.msg('请求太快，请稍后再试！', {
        icon : 5
      });
    }
  });
}
*/

var applyLinks = function() {
  swal({
    title : '互换友链',
    text : '注意：请在您的网站友链处添加本站链接后再行申请！！！！！！添加格式如下：名称&网站首页地址',
    type : 'input',
    showCancelButton : true,
    confirmButtonColor : "#1c84c6",
    confirmButtonText : "提交",
    closeOnConfirm : false
  }, function() {
    //swal("删除成功！", "您已经永久删除了这条信息。", "success");
    checkLinks();
  });
}

//核对链接
var checkLinks = function() {

  var inputLink = new Array();
  inputLink = $("fieldset").find("input").val().split("&");
  var title = '';
  var text = '';
  var type = '';
  if (inputLink.length != 2) {
    title = '格式错误',
    text = '请检查格式是否正确',
    type = 'error'
  } else {
    title = '请核对信息',
    text = '名称：' + inputLink[0].replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, "&quot;").replace(/'/g, "&#039;") + "   。" + '链接：' + inputLink[1].replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, "&quot;").replace(/'/g, "&#039;"),
    type = 'warning'
  }
  swal({
    title : title,
    text : text,
    type : type,
    showCancelButton : true,
    confirmButtonColor : "#1c84c6",
    confirmButtonText : "确定",
    closeOnConfirm : false
  }, function() {
    if (type == 'warning') {
      addLinks(inputLink[0], inputLink[1]);
    }
  });
};



//添加链接
var addLinks = function(name, link) {
  var params = {
    name : name,
    link : link,
    sort : 0,
    isapply : -1,
    prarm : '有新伙伴申请友链啦！',
  };
  $.ajax({
    url : 'addLinks',
    type : 'post',
    data : params,
    dataType : 'json',
    success : function(data) {
      if (data.status == 200) {
        swal("申请成功", "等待管理审核", "success");
      }
    },
    error : function() {
      swal("申请失败", "请检查格式是否正确", "error");
    }
  });
}


//更新链接点击次数
var clickNum = function(id) {
  var params = {
    id : id,
  };
  $.ajax({
    url : 'selectLinksById',
    type : 'post',
    data : params,
    dataType : 'json',
    success : function(data) {},
    error : function() {}
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