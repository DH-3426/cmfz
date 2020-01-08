<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<script>
    $(function(){
        $("#allAlbumTable").jqGrid({
                url : "${pageContext.request.contextPath}/album/queryAll",
                datatype : "json",
                height : 500,
                colNames : [ 'id', 'title', 'score', 'author', 'broadcast','cover', 'count','description','status','createDate'],
                colModel : [
                    {name : 'id',align:"center"},
                    {name : 'title',align:"center",editable:true},
                    {name : 'score',align:"center",editable:true},
                    {name : 'author',align:"center",editable:true},
                    {name : 'broadcast',align:"center",editable:true},
                    {name : 'cover',align:"center",editable:true,
                        formatter:function (data) {
                        alert(data);
                            return "<img style='height: 80px;width: 180px' src='"+data+"'>";
                        },edittype: "file",editoptions:{enctype:"multipart/form-data"}
                    },
                    {name : 'count',align:"center"},
                    {name : 'description',align:"center",editable:true},
                    {name : 'status',edittype:"select",editoptions: {value:"1:展示;2:冻结"},
                        formatter:function (data) {
                            if (data=="1"){
                                return "展示";
                            } else return "冻结";
                        }
                    },
                    {name : 'createDate',align:"center"}
                ],
                rowNum : 8,
                rowList : [ 8, 10, 20, 30 ],
                pager : '#fpager',
                sortname : 'id',
                viewrecords : true,
                sortorder : "desc",
                multiselect : false,
                subGrid : true,
                styleUI:"Bootstrap",
                caption : "专辑管理",
                editurl:'${pageContext.request.contextPath}/album/edit',
            //嵌套子表ge格开始
                subGridRowExpanded : function(subgrid_id, row_id) {
                    var subgrid_table_id, pager_id;
                    subgrid_table_id = subgrid_id + "_t";
                    pager_id = "p_" + subgrid_table_id;
                    $("#" + subgrid_id).html(
                        "<table id='" + subgrid_table_id + "' class='scroll'>" +
                        "</table><div id='" + pager_id + "' class='scroll'></div>");
                    $("#" + subgrid_table_id).jqGrid(
                        {
                            url : "${pageContext.request.contextPath}/album/queryAllChap?id=" + row_id,
                            datatype : "json",
                            colNames : [ 'id', 'title',  'size','time','createTime','albumId','url' ],
                            colModel : [
                                {name : "id"},
                                {name : "title",align:"center",editable:true},
                                {name : "size",align : "center"},
                                {name : "time",align : "center"},
                                {name : "createTime",align : "center"},
                                {name : "albumId",align : "center",editable:true},
                                {name : "url",
                                    formatter:function (cellvalue, options, rowObject) {
                                        var button = "<button type=\"button\" class=\"btn btn-primary\" onclick=\"download('"+cellvalue+"')\">下载</button>&nbsp;&nbsp;";
                                        //                                                                声明一个onPlay方法 --> 显示模态框 ---> 为audio标签添加src  需要url路径作为参数传递
                                        //                                                              'onPlay(参数)' ---> \"onPlay('"+cellvalue+"')\"
                                        button+= "<button type=\"button\" class=\"btn btn-danger\" onclick=\"onPlay('"+cellvalue+"')\">在线播放</button>";
                                        return button;
                                    },editable:true,edittype:"file",editoptions:{enctype:"multipart/form-data"}
                                }
                            ],
                            rowNum : 20,
                            pager : pager_id,
                            sortname : '',
                            sortorder : "asc",
                            styleUI:"Bootstrap",
                            height : '100%',
                            editurl: '${pageContext.request.contextPath}/album/chapEdit?albumId='+row_id
                        });
                    //子表操作方法后续操作开始
                    $("#" + subgrid_table_id).jqGrid('navGrid', "#" + pager_id, {
                            edit : true,
                            add : true,
                            del : true
                        },{
                        closeAfterEdit:true,
                        afterSubmit:function (response,postData) {
                            var chapterId = response.responseJSON.chapterId;
                            $.ajaxFileUpload({
                                url:"${pageContext.request.contextPath}/album/uploadChapPath",
                                type:"post",
                                datatype:"json",
                                data:{chapterId:chapterId},
                                fileElementId:"url",
                                success:function (data) {
                                    $("#" + subgrid_table_id).trigger("reloadGrid");
                                }
                            });
                            return postData;
                        }
                    },{
                        //子表格添加后续
                        closeAfterAdd:true,
                        afterSubmit:function (response,postData) {
                            var chapterId = response.responseJSON.chapterId;
                            $.ajaxFileUpload({
                                url:"${pageContext.request.contextPath}/album/uploadChapPath",
                                type:"post",
                                datatype:"json",
                                data:{chapterId:chapterId},
                                fileElementId:"url",
                                success:function (data) {
                                    $("#" + subgrid_table_id).trigger("reloadGrid");
                                }
                            });
                            return postData;
                        }
                    },{
                        closeAfterDel:true,
                    });
                },
                subGridRowColapsed : function(subgrid_id, row_id) {
                },
            });
        //父表操作方法后续操作开始
        $("#allAlbumTable").jqGrid('navGrid', '#fpager', {
            add : true,
            edit : true,
            del : true
        },{
            closeAfterEdit:true,
            afterSubmit:function (response,postData) {
                var albumId = response.responseJSON.albumId;
                $.ajaxFileUpload({
                    url:"${pageContext.request.contextPath}/album/updateImagePath",
                    type:"post",
                    datatype:"json",
                    data:{albumId:albumId},
                    fileElementId:"cover",
                    success:function (data) {
                        $("#allAlbumTable").trigger("reloadGrid")
                    }
                });
                return postData;
            }
        },{
            closeAfterAdd:true,
            //父表格添加后续
            afterSubmit:function (response,postData) {
                var albumId = response.responseJSON.albumId;
                $.ajaxFileUpload({
                    url:"${pageContext.request.contextPath}/album/updateImagePath",
                    type:"post",
                    datatype:"json",
                    data:{albumId:albumId},
                    fileElementId:"cover",
                    success:function (data) {
                        $("#allAlbumTable").trigger("reloadGrid")
                    }
                });
                return postData;
            }


        },{
            closeAfterDel:true,
        });
    })
    function onPlay(cellValue) {
        $("#music").attr("src",cellValue);
        $("#myModal").modal("show");
    }
    function download(cellValue) {
        location.href = "${pageContext.request.contextPath}/album/downloadChap?url="+cellValue;
    }
</script>
<table id="allAlbumTable"></table>
<div id="fpager" style="height: 50px"></div>
<!--拟态框-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <audio id="music" src="" controls="controls">
        </audio>
    </div><!-- /.modal -->
</div>