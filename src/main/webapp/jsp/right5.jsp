<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    // 初始化jgGrid插件
    $(function () {
        $("#allGuruTable").jqGrid({
            url:"${pageContext.request.contextPath}/guru/queryAll",
            datatype:"json",
            colNames:["id","姓名","图片","状态","昵称"],
            colModel:[
            <!--alight居中-->
            {name:"id",align:"center"},
            {name:"name",editable:true},
            {name:"photo",editable:true,edittype:"file",
                formatter:function(cellValue){
                    return "<img src='${pageContext.request.contextPath}/upload/guruImg/"+cellValue+"' style='width:100px;height:80px' >";
                }
            },
            {name:"status",
                    formatter:function(cellValue,option,row){
                        if(cellValue==("1")){
                            //展示
                            return "<button class='btn btn-danger' onclick='updateStatus(\""+row.id+"\",\""+cellValue+"\")' >不展示</button>";
                        }else{
                            //不展示
                            return "<button class='btn btn-success' onclick='updateStatus(\""+row.id+"\",\""+cellValue+"\")' >展示</button>";
                        }
                    }
                },
            {name:"nickName"}
            ],
            autowidth:true,
            multiselect:true,
            pager:"#pager",
            styleUI:"Bootstrap",
            page:1,
            rowNum:5,
            rowList:[5,10,15,20],
            viewrecords:true,
            editurl:""
            }).jqGrid("navGrid","#gpager",{add:true, edit:true, del:true, refresh:true},
            {closeAfterEdit:true},
            {closeAfterAdd:true})
        })
</script>
<table id="allGuruTable"></table>
<div id="gpager" style="height: 50px"></div>

