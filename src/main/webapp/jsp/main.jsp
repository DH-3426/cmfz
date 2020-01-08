<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport"
              content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link rel="stylesheet" href="../boot/css/bootstrap.min.css">
        <link rel="stylesheet" href="../boot/css/back.css">
        <link rel="stylesheet" href="../jqgrid/css/trirand/ui.jqgrid-bootstrap.css">
        <link rel="stylesheet" href="../jqgrid/css/jquery-ui.css">
        <script src="../boot/js/jquery-2.2.1.min.js"></script>
        <script src="../boot/js/bootstrap.min.js"></script>
        <script src="../jqgrid/js/trirand/src/jquery.jqGrid.js"></script>
        <script src="../jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
        <script src="../boot/js/ajaxfileupload.js"></script>
        <script src="../kindeditor/kindeditor-all-min.js"></script>
        <script src="../kindeditor/lang/zh-CN.js"></script>
        <script src="../echarts/echarts.min.js"></script>
        <script type="text/javascript" src="http://cdn.goeasy.io/goeasy-1.0.3.js"></script>
        <script>
            // KindEditor初始化时必须放在head标签中,不然会出现无法初始化的情况
            KindEditor.ready(function (K) {
                // K.create("textarea的Id")
                // 如需自定义配置 在id后使用,{}的形式声明
                window.editor = K.create('#editor_id', {
                    width : '500px',
                    //上传文件
                    uploadJson: '${pageContext.request.contextPath}/article/uploadImg',
                    allowFileManager: true,
                    //图片管理  自定义路径
                    fileManagerJson: '${pageContext.request.contextPath}/article/showAllImg',
                    // 失去焦点后 触发的事件
                    afterBlur: function () {
                        // 同步数据方法
                        this.sync();
                    }
                });
            });
            $(function () {
                //轮播图管理面板
                $("#banner").click(function () {
                    $("#zzz").load("${pageContext.request.contextPath}/jsp/right1.jsp");
                });
                //专辑管理面板
                $("#album").click(function () {
                    $("#zzz").load("${pageContext.request.contextPath}/jsp/right3.jsp");
                });
                //文章管理面板
                $("#article").click(function () {
                    $("#zzz").load("${pageContext.request.contextPath}/jsp/right4.jsp");
                });
                //上师管理面板
                $("#guru").click(function () {
                    $("#zzz").load("${pageContext.request.contextPath}/jsp/right5.jsp");
                });
                //地图
                $("#map").click(function () {
                    $("#zzz").load("${pageContext.request.contextPath}/jsp/map.jsp");
                });
                //折线图
                $("#echarts").click(function () {
                    $("#zzz").load("${pageContext.request.contextPath}/jsp/echarts.jsp");
                });
                //用户管理
                $("#user").click(function () {
                    $("#zzz").load("${pageContext.request.contextPath}/jsp/right6.jsp");
                });
            });
        </script>
    </head>
<body>
<!-- 导航栏 -->
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">持名法洲后台管理系统</a>
        </div>
        <div>
            <!--向右对齐-->
            <ul class="nav navbar-nav navbar-right">
                <li><a>欢迎:玄悲</a></li>
                <li><a href="#">退出登陆</a></li>
            </ul>
        </div>
    </div>
</nav>
<!-- 栅格系统 -->
    <div class="container-fluid">
        <div class="row">
            <!-- 手风琴 左部 -->
            <div class="col-xs-2">
                <jsp:include page="left.jsp"></jsp:include>
            </div>
            <!--中部-->
            <div class="col-xs-10" id="zzz">
                <!--中央替换区-->
                <jsp:include page="right2.jsp"></jsp:include>
            </div>
        </div>
    </div>
    <div class="panel-footer">
        <h4 style="text-align: center">@百知教育 @baizhiedu.com.cn</h4>
    </div>
    <!--模态框-->
    <div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">文章信息</h4>
            </div>
            <div class="modal-body">
                <form role="form" id="kindForm">
                    <div class="form-group">
                        <input type="hidden" class="form-control" id="id" placeholder="请输入名称">
                    </div>
                    <div class="form-group">
                        <label for="title">标题</label>
                        <input type="text" class="form-control" name="title" id="title" placeholder="请输入名称">
                    </div>
                    <div class="form-group">
                        <label for="inputfile">封面</label>
                        <!-- name不能起名和实体类一致 会出现使用String类型接受二进制文件的情况 -->
                        <input type="file" id="inputfile" name="inputfile">
                    </div>
                    <div class="form-group">
                        <label for="editor_id">内容</label>
                        <textarea id="editor_id" name="content" style="width:500px;height:300px;"></textarea>
                    </div>
                    <div class="form-group">
                        <label for="status">状态</label>
                        <select class="form-control" id="status" name="status">
                            <option value="1">展示</option>
                            <option value="2">冻结</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="guru_list">上师列表</label>
                        <select class="form-control" id="guru_list" name="guruId">
                        </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="sub()">提交更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>