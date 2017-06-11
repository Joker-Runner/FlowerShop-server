<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <% String path = request.getContextPath(); %>
    <link rel="shortcut icon" href="<%=path%>/img/logo.png"/>
    <link rel="stylesheet" href="<%=path%>/css/navbar.css">
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
                        <!--<li><a href="./list.html">我的首页</a></li>-->
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
                                <li><a href="../../flower_list.html">商品管理</a></li>
                                <li><a href="../../create_flower.html">添加</a></li>
                                <li class="divider"></li>
                                <li class="dropdown-header">账号</li>
                                <%--<li><a href="#">更改密码</a></li>--%>
                                <li><a href="login">登录</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>

            <div class="col-md-1 column">
            </div>
            <div class="col-md-6 column">
                <div class="form-horizontal">
                    <div class="form-group">
                        <label for="username" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-6">
                            <input type="text" name="username" class="form-control" id="username"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="email" class="col-sm-2 control-label">账号</label>
                        <div class="col-sm-6">
                            <input type="email" name="email" class="form-control" id="email"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-6">
                            <input type="password" name="password" class="form-control" id="password"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="icon" class="col-sm-2 control-label">头像</label>
                        <div class="col-sm-6">
                            <input type="file" id="icon"/>
                            <p class="help-block">
                                请选择您的头像
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-md-2 column">
                            <button onclick="signUp()" class="btn btn-default ">注册</button>
                        </div>
                        <div class="col-md-6 column">
                            <a href="login" class="btn btn-default btn-info" role="button">登录</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-md-5 column">
            </div>

        </div>
    </div>
</div>

<script>
    var datas = {};
    function signUp() {
        datas.username = $("#username").val();
        datas.email = $("#email").val();
        datas.password = $("#password").val();
        $.post("./sign_up", datas, function (data, status) {
            if (data === 1) {
                window.location.href = "./login";
            }

        });
    }
</script>
</body>
</html>
