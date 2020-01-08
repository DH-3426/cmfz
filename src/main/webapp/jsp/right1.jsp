<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    // 初始化jgGrid插件
    $(function () {
        $("#allBannerTable").jqGrid({
            url:"${pageContext.request.contextPath}/queryAll",
            datatype:"json",
            colNames:["id","标题","图片","路径","创建时间","描述","状态"],
            colModel:[
            <!--alight居中-->
            {name:"id",align:"center"},
            {name:"title",editable:true},
            {name:"url",editable:true,edittype:"file",
                formatter:function(cellValue){
                    return "<img src='${pageContext.request.contextPath}/upload/photo/"+cellValue+"' style='width:100px;height:80px' >";
                }
            },
            {name:"href"},
            {name:"createDate"},
            {name:"description",editable:true},
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
            }
            ],
            autowidth:true,
            multiselect:true,
            pager:"#pager",
            styleUI:"Bootstrap",
            page:1,
            rowNum:5,
            rowList:[5,10,15,20],
            viewrecords:true,
            editurl:"${pageContext.request.contextPath}/save"
            }).jqGrid("navGrid","#pager",{add:true, edit:true, del:true, refresh:true},
            {
                closeAfterEdit:true,
                afterSubmit:function(response,postData){
                    var bannerId = response.responseJSON.bannerId;
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/updatePath",
                        datatype:"json",
                        type:"post",
                        fileElementId:"url", // 需要上传的文件域的ID
                        data:{id:bannerId},
                        success:function(data){
                            $("#allAccountTable").trigger("reloadGrid");
                        }
                    });
                    return postData;
                }
            },
            {
                closeAfterAdd:true,
                afterSubmit:function(response,postData){
                    var bannerId = response.responseJSON.bannerId;
                    $.ajaxFileUpload({
                        url:"${pageContext.request.contextPath}/updatePath",
                        datatype:"json",
                        type:"post",
                        fileElementId:"url", // 需要上传的文件域的ID
                        data:{id:bannerId},
                        success:function(data){
                            $("#allAccountTable").trigger("reloadGrid");
                        }
                    });
                    return postData;
                }
        })
        })
    function updateStatus(id,status) {
        alert(status);
        $.ajax({
            url:"${pageContext.request.contextPath}/changeStruts",
            type:"post",
            dataType:"JSON",
            data:{"id":id,"status":status},
            success:function(){
                $("#allBannerTable").trigger("reloadGrid");
            }
        })
    }
</script>
<table id="allBannerTable"></table>
<div id="pager" style="height: 50px"></div>

