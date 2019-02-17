<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>${blog.title}</title>
<link rel="shortcut icon"
  href="${pageContext.request.contextPath}/images/favicon.ico">
<meta name="keywords" content="${blog.keyWord}" />
<meta name="description" content="${blog.introduction}" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${pageContext.request.contextPath}/css/github-gist.css"
  rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/font-awesome.css"
  rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/animate.css"
  rel="stylesheet">
<script
    src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
</head>
<body
  style="background:url(${pageContext.request.contextPath}/images/bj.png) repeat top left scroll">
  <%@ include file="top.jsp"%>
  <article>
    
    <!-- 显示文章部分 -->
    <div class="lbox">
      <div class="infos">
        <div class="newsview ">
          <h2 class="intitle">
            您现在的位置是：<a href="/">网站首页</a>&nbsp;&gt;&nbsp;<a href="/">技术专栏</a>
          </h2>
          <c:choose>
            <c:when test="${status== '0' || status== '500'}">
              <h1 style="font-size:110px;text-align:center;margin:20px;">404</h1>
              <h3 style="text-align:center;" class="font-bold">抱歉，你所访问的页面不存在~</h3>
              <h4 style="margin-top:55px;text-align:center;">
                <a
                  style="background-color: #676a6c;padding: 5px 10px;color: #fff;border-radius: 10px;"
                  href="${pageContext.request.contextPath}/index.jsp">去首页</a>
              </h4>
            </c:when>
            <c:otherwise>
              <h3 class="news_title animated fadeIn">${blog.title}</h3>
              <input class="id" type="hidden" value="${blog.blogid}">
              <div class="news_author animated fadeIn">
                <span class="au01 ">luotf</span><span class="au02">
                  <input class="addtime" type="hidden"
                  value="${blog.publishedTime}">
                </span><span class="au03">共<b>${blog.pageView}</b>人围观
                </span>
              </div>
              <input class="typeId" type="hidden"
                value="${typeId}">
              <div class="tags animated fadeIn">
                <input class="tag" type="hidden" value="${blog.keyWord}">
              </div>
              <div class="news_about animated fadeIn">
                <strong>简介</strong>${blog.introduction}</div>
              <div class="news_infos animated fadeIn">${blog.content}</div>
              <br>
              <br>
              <p
                style="font-size:15px;padding: 10px 20px;background: #f7f7f7; border-left: 5px solid rgb(255, 146, 111);">
                <b>转载：</b>感谢您对luotf个人博客网站平台的认可，及对该作品以及文章的青睐，非常欢迎各位朋友分享到个人站长或者朋友圈，但转载请说明文章出处。本文章部分图片、文章来源于网络，版权归原作者所有，如有侵权，请与我联系删除。
              </p>
            </c:otherwise>
          </c:choose>
        </div>
        <div class="ds" style="text-align: center;opacity:0">
          <div id="cyReward" role="cylabs" data-use="reward"></div>
        </div>
      </div>
      <div class="nextinfo animated fadeIn">
        <p>
          上一篇：<span class="pre"></span>
        </p>
        <p>
          下一篇：<span class="next"></span>
        </p>
      </div>
      <div class="otherlink animated fadeIn"
        style="padding-bottom:20px;">
        <h2>相关文章</h2>
        <ul>

        </ul>
      </div>
      <div class="news_pl " id="news_pl">
        <h2>文章评论</h2>
        <div style="width:90%;margin: 0 auto;">
          <div id="cyEmoji" role="cylabs" data-use="emoji"
            sid="${blog.blogid }"></div>
          <!--PC和WAP自适应版-->
          <div id="SOHUCS" sid="${blog.blogid }"></div>
        </div>
      </div>

    </div>
    <div class="rbox  ">

      <div class="dianji paihang whitebg">
        <h2 class="cloud_hometitle">本栏推荐</h2>
        <ul class="like " style="padding:0px">

        </ul>
      </div>
      <div class="dj dianji paihang whitebg animated fadeInUp"
        style="display:none;animation-delay:0.3s">
        <h2 class="cloud_hometitle">点击排行</h2>
        <ul class="click" style="padding:0px">

        </ul>

      </div>
      <script type="text/javascript" charset="utf-8"
        src="http://changyan.sohu.com/js/changyan.labs.https.js?appid=cytzg9rLH"></script>
      <div class="fixed-menu-list animated fadeInUp">
        <div class="sidebar-nav-toc">文章目录</div>
        <div class="post-toc">
          <div class="post-toc-content">
            <ol class="nav1">

            </ol>
          </div>
        </div>
      </div>
    </div>

    <a href="#" class="top cd-top animated ">Top</a>
  </article>

  <%@ include file="fonter.jsp"%>
  <script src="${pageContext.request.contextPath}/js/page/info.js"></script>
  <script src="${pageContext.request.contextPath}/js/highlight.pack.js"></script>
  <script>hljs.initHighlightingOnLoad();
  </script>
  <script src="${pageContext.request.contextPath}/js/layer/layer.js"></script>
</body>
</html>
