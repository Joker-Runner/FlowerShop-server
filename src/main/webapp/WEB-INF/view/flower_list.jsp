<%--
  Created by IntelliJ IDEA.
  User: joker
  Date: 5/25 0025
  Time: 18:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <% String path = request.getContextPath(); %>
    <meta charset="utf-8">
    <title>网上花店</title>
    <%--<link rel="shortcut icon" href="../img/logo.png"/>--%>
    <link rel="shortcut icon" href="<%=path%>/img/logo.png"/>
    <link rel="stylesheet" href="<%=path%>/css/navbar.css">
    <link rel="stylesheet" href="<%=path%>/css/footer.css">
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <script src="http://cdn.bootcss.com/jquery/3.0.0/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">

            <nav class="navbar navbar-default navbar-fixed-top">
                <div class="navbar-header">
                    <button class="navbar-toggle collapsed" type="button" data-toggle="collapse"
                            data-target="#navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="index">首页</a>
                </div>
                <div id="navbar" class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <!--<li><a href="./list.html">商品列表</a></li>-->
                        <!--<li class="dropdown">-->
                        <!--<a href="#" class="dropdown-toggle" data-toggle="dropdown">商品管理-->
                        <!--<span class="caret"></span></a>-->
                        <!--<ul class="dropdown-menu">-->
                        <!--<li class="dropdown-header">管理</li>-->
                        <!--<li><a href="#">博客信息</a></li>-->
                        <!--<li><a href="./create.html">创建博文</a></li>-->
                        <!--<li><a href="#">博客管理</a></li>-->
                        <!--<li class="divider"></li>-->
                        <!--<li class="dropdown-header">账号</li>-->
                        <li><a href="#">更改密码</a></li>
                        <li><a href="#">退出登录</a></li>
                        <!--</ul>-->
                        <!--</li>-->
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <form class="navbar-form navbar-left">
                            <div class="form-group">
                                <input type="text" class="form-control" placeholder="关键字">
                            </div>
                            <button type="submit" class="btn btn-default">搜索</button>
                        </form>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">账号管理
                                <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li class="dropdown-header">商品</li>
                                <li><a href="#">商品管理</a></li>
                                <li><a href="../../create_flower.html">添加</a></li>
                                <li class="divider"></li>
                                <li class="dropdown-header">账号</li>
                                <%--<% String s = "steng";%>--%>
                                <%--requestScope.userId: ${requestScope.userId}--%>
                                <%--<c:out value="${requestScope.userId}"/>--%>
                                <%--userId: ${userId}--%>
                                <%--<c:out value="${userId}"/>--%>
                                <%--s:${s}--%>
                                <%--<c:if test="${not empty requestScope.userId}">--%>
                                <%--<li><a href="login">--%>
                                <%--<c:out value="${requestScope.userId}"/></a></li>--%>
                                <%--</c:if>--%>
                                <%--<c:if test="${user_id==null}">--%>
                                <%--<li><a href="login">登录</a></li>--%>
                                <%--</c:if>--%>
                                <li><a href="#">退出登录</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>

            <%--<a id="modal-117072" href="#modal-container-117072" role="button" class="btn" data-toggle="modal">触发遮罩窗体</a>--%>
            <%--<div class="modal fade" id="modal-container-117072" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">--%>
            <%--<div class="modal-dialog">--%>
            <%--<div class="modal-content">--%>
            <%--<div class="modal-header">--%>
            <%--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>--%>
            <%--<h4 class="modal-title" id="myModalLabel">--%>
            <%--标题--%>
            <%--</h4>--%>
            <%--</div>--%>
            <%--<div class="modal-body">--%>
            <%--内容...--%>
            <%--</div>--%>
            <%--<div class="modal-footer">--%>
            <%--<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button> <button type="button" class="btn btn-primary">保存</button>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</div>--%>
            <%--</div>--%>

            <div class="row">
                <div class="col-md-4 col-lg-3 col-sm-6" id="flower-temp" style="display: none;">
                    <div class="thumbnail">
                        <img alt="300x300" id="image" src=""/>
                        <div class="caption">
                            <h3>
                                [{title}]
                            </h3>
                            <p>
                                Cras justo odio, dapibus ac facilisis in, egestas eget quam.
                            </p>
                            <p>
                                <a class="btn btn-primary" href="javascript:test(0);">购买</a>
                                <%--<a class="btn" href="#">Action</a>--%>
                            </p>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>

<script>
    function test(id) {
        alert(id);
    }
    $(document).ready(function () {
        $.post("./flower_list", {"start_number": 0},
            function (data, status) {
                var flowers = JSON.parse(data);
                var temp = $("#flower-temp");
                for (var i = 0; i < flowers.length; i++) {
                    var flower = flowers[i];
                    var fd = temp.clone();
                    var h3s = fd.find("h3");
                    $(h3s[0]).text(flower.title);
                    var p_intros = fd.find("p");
                    $(p_intros[0]).text(flower.introduction);
                    var imgs = fd.find("img");
                    $(imgs[0]).attr("src", flower.imageURL);
                    $(imgs[0]).attr("alt", flower.introduction);
                    var as = fd.find("a");
                    $(as[0]).attr("href","javascript:test(" + flower.id + ");");
                    fd.css("display", "block").attr("id", "");
                    temp.parent().append(fd);
                }
            })
    })
</script>

<%--<footer class="foot">--%>
<%--&copy; 2017 网上花店, Inc. &middot; <a href="#">隐私</a> &middot; <a href="#">条款</a>--%>
<%--</footer>--%>
</body>
</html>