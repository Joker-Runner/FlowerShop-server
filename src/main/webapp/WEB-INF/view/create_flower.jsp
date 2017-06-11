<%--
  Created by IntelliJ IDEA.
  User: joker
  Date: 5/15 0015
  Time: 19:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>发布商品</title>
    <% String path = request.getContextPath(); %>
    <link rel="shortcut icon" href="<%=path%>/img/logo.png"/>
    <link rel="stylesheet" href="<%=path%>/css/navbar.css">
    <link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <script src="http://cdn.bootcss.com/jquery/3.0.0/jquery.min.js"></script>
    <script src="http://cdn.bootcss.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
    <script src="<%=path%>/dist/html5ImgCompress.min.js"></script>
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
                                <li><a href="flower_list">商品管理</a></li>
                                <li><a href="create_flower">添加</a></li>
                                <li class="divider"></li>
                                <li class="dropdown-header">账号</li>
                                <li><a href="#">更改密码</a></li>
                                <li><a href="#">退出登录</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>


            <div class="col-md-1 column">
            </div>
            <div class="col-md-6 column">
                <form  class="form-horizontal" role="form">
                    <div class="form-group">
                        <label for="title" class="col-sm-2 control-label">标题</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="title"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="introduction" class="col-sm-2 control-label">描述</label>
                        <div class="col-sm-6">
                            <input type="text"  class="form-control" id="introduction"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="price" class="col-sm-2 control-label">价格</label>
                        <div class="col-sm-6">
                            <input type="number" class="form-control" id="price"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="single" class="col-sm-2 control-label">图片</label>
                        <div class="col-sm-6">
                            <input type="file" id="single"/>
                            <p class="help-block">
                                请选择图片
                            </p>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-md-2 column">
                            <button type="button" class="btn btn-default " onclick="upload()">提交</button>
                        </div>
                        <!--<div class="col-md-6 column">-->
                        <!--<a href="login.html" class="btn btn-default btn-info" role="button">登录</a>-->
                        <!--</div>-->
                    </div>
                </form>
            </div>
            <div class="col-md-5 column">
            </div>
        </div>
    </div>
</div>
</body>
<script>
    var datas = {};
    function upload() {
        datas.title = $("#title").val();
        datas.introduction = $("#introduction").val();
        datas.price = $("#price").val();
        $.ajax({
            url: "./create_flower",
            type: "POST",
            data: datas,
//            contentType: "application/json; charset=utf-8",
            dataType: "text",
            success:function (data) {
                alert("上传成功！");
//                window.location.href = window.location.href + "./message.jsp";
            },
            error:function (data) {
                alert(JSON.stringify(data));
            }
        })

    }

    $(function () {
        // 单张
        $('#single').on('change', function (e) {
            new html5ImgCompress(e.target.files[0], {
                before: function (file) {
                    console.log('单张: 压缩前...');
                },
                done: function (file, base64) {
                    datas["base64"] = base64;
                    console.log('单张: 压缩成功...');
//                    insertImg(file, j); // 显示原图
//                    insertImg(base64, j++); // 显示压缩后
                },
                fail: function (file) {
                    console.log('单张: 压缩失败...');
                },
                complete: function (file) {
                    console.log('单张: 压缩完成...');
                },
                notSupport: function (file) {
                    alert('浏览器不支持！');
                }
            });
        });
    });
</script>
</html>