<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <% String path = request.getContextPath(); %>
    <meta charset="utf-8">
    <title>网上花店</title>
    <link rel="shortcut icon" href="<%=path%>/img/logo.png"/>
    <link rel="stylesheet" href="<%=path%>/css/navbar.css">
    <link rel="stylesheet" href="<%=path%>/css/footer.css">
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <script src="http://cdn.bootcss.com/jquery/3.0.0/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <style>
        .thumbnail{
            height:120px;
        }
        .thumbnail img{
            height: 80px;
        }
    </style>
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
                        <!--<li><a href="#">更改密码</a></li>-->
                        <!--<li><a href="#">退出登录</a></li>-->
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
                                <li><a href="flower_list">商品管理</a></li>
                                <li><a href="create_flower">添加</a></li>
                                <li class="divider"></li>
                                <li class="dropdown-header">账号</li>
                                <% String s = ""+request.getAttribute("userId"); %>
                                <%= s.equals("null")?"<li><a href=\"login\">登录</a></li>"
                                        :"<li><a onclick=\"deleteCookie('name')\" href=\"index\">注销</a></li>"%>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>


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
    // 删除cookie
    function deleteCookie(name) {
        document.cookie = name + '=; Max-Age=0';
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