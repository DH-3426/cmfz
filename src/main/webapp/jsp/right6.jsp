<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    // 初始化jgGrid插件
    $(function () {
        $("#allUserTable").jqGrid({
            url:"${pageContext.request.contextPath}/user/queryAll",
            datatype:"json",
            colNames:["id","电话","密码","盐值","状态","图片","名字","昵称","性别","签名","地址","注册时间","最后登录时间"],
            colModel:[
            <!--alight居中-->
                {name:"id",align:"center"},
                {name:"phone"},
                {name:"password"},
                {name:"stale"},
                {name: "status",
                    formatter: function (cellValue, option, row) {
                        if (cellValue == ("1")) {
                           //展示
                           return "<button class='btn btn-danger' onclick='updateStatus(\"" + row.id + "\",\"" + cellValue + "\")' >不展示</button>";
                        } else {
                            //不展示
                            return "<button class='btn btn-success' onclick='updateStatus(\"" + row.id + "\",\"" + cellValue + "\")' >展示</button>";
                        }
                     }
                 },
                {name:"photo",editable:true,edittype:"file",
                    formatter:function(cellValue){
                       return "<img src='"+cellValue+"' style='width:100px;height:80px' >";
                    }
                },
                {name:"name"},
                {name:"nickName"},
                {name:"sex"},
                {name:"sign"},
                {name:"location"},
                {name:"rigestDate"},
                {name:"lastLogin"}
            ],
            autowidth:true,
            multiselect:true,
            pager:"#dpager",
            styleUI:"Bootstrap",
            page:1,
            rowNum:5,
            rowList:[5,10,15,20],
            viewrecords:true,
            editurl:""
            }).jqGrid("navGrid","#dpager",{add:true, edit:true, del:true, refresh:true},
            {closeAfterEdit:true,},
            {closeAfterAdd:true})
        })
</script>
<table id="allUserTable"></table>
<div id="dpager" style="height: 50px"></div>

