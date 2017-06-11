<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
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
                                <%--<li class="dropdown-header">商品</li>--%>
                                <%--<li><a href="flower_list.html">商品管理</a></li>--%>
                                <%--<li><a href="create_flower.html">添加</a></li>--%>
                                <%--<li class="divider"></li>--%>
                                <li class="dropdown-header">账号</li>
                                <%--<li><a href="#">登录</a></li>--%>
                                <li><a href="sign_up">注册</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>

            <div class="col-md-1 column">
            </div>
            <div class="col-md-6 column">
                <div class="form-horizontal" >
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
                        <div class="col-sm-offset-2 col-sm-10">
                            <div class="checkbox">
                                <label><input name="remember" type="checkbox"/>记住我</label>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-md-2 column">
                            <button onclick="login()" class="btn btn-default">登录</button>
                        </div>
                        <div class="col-md-6 column">
                            <a href="sign_up" class="btn btn-default btn-info" role="button">注册</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
<script>
    var datas = {};
    function login() {
        datas.email = $("#email").val();
        datas.password = $("#password").val();
        $.post("./login",datas,function (data,status) {
            var user = JSON.parse(data);
            if(user!==null){
                window.location.href = "./index";
            }

        });
//        $.ajax({
//            url: "./login",
//            type: "POST",
//            data: datas,
////            contentType: "application/json; charset=utf-8",
//            dataType: "text",
//            success: function (data) {
//                var user = JSON.parse(data);
////                if (data == "true") {
//                    alert("登录成功");
//                    window.location.href = "index";
////                }
//            },
//            error: function (data) {
//                alert(JSON.stringify(data));
//            }
//        })

    }
</script>
</html>