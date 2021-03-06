<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!-- 引入 echarts.js -->
    <script src="${pageContext.request.contextPath}/echarts/echarts.min.js" charset="UTF-8"></script>
    <script type="text/javascript" src="../echarts/china.js" charset="UTF-8"></script>
    <script type="text/javascript" src="http://cdn.goeasy.io/goeasy-1.0.3.js"></script>
    <script type="text/javascript">
        var goEasy = new GoEasy({
            host:'hangzhou.goeasy.io',//应用所在的区域地址，杭州：hangzhou.goeasy.io，新加坡：singapore.goeasy.io
            appkey: "BC-5802a265f5ff430e9dfdacece51ebf17",//替换为您的应用appkey
            forceTLS:false, //如果需要使用HTTPS/WSS，请设置为true，默认为false
            onConnected: function() {
                console.log('连接成功！')
            },
            onDisconnected: function() {
                console.log('连接断开！')
            },
            onConnectFailed: function(error) {
                console.log('连接失败或错误！')
            }
        });

        $(function () {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('uMap'));
            var option = {
                title: {
                    text: '用户分布图',
                    subtext: '纯属虚构',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: ['男', '女']
                },
                visualMap: {
                    min: 0,
                    max: 10,
                    left: 'left',
                    top: 'bottom',
                    text: ['高', '低'],           // 文本，默认为数值文本
                    calculable: true
                },
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    left: 'right',
                    top: 'center',
                    feature: {
                        mark: {show: true},
                        dataView: {show: true, readOnly: false},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                series: [
                    {
                        name: '男',
                        type: 'map',
                        mapType: 'china',
                        roam: false,
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data: [
                            //Math.round(Math.random()*1000)
                            {name: '北京',value: 0},
                            {name: '天津',value: 0},
                            {name: '上海',value: 0},
                            {name: '重庆',value: 0},
                            {name: '河北',value: 0},
                            {name: '河南',value: 0},
                            {name: '云南',value: 0},
                            {name: '辽宁',value: 0},
                            {name: '湖南',value: 0},
                            {name: '安徽',value: 0},
                            {name: '山东',value: 0},
                            {name: '新疆',value: 0},
                            {name: '江苏',value: 0},
                            {name: '浙江',value: 0},
                            {name: '江西',value: 0},
                            {name: '湖北',value: 0},
                            {name: '广西',value: 0},
                            {name: '甘肃',value: 0},
                            {name: '山西',value: 0},
                            {name: '陕西',value: 0},
                            {name: '吉林',value: 0},
                            {name: '福建',value: 0},
                            {name: '贵州',value: 0},
                            {name: '广东',value: 0},
                            {name: '青海',value: 0},
                            {name: '西藏',value: 0},
                            {name: '四川',value: 0},
                            {name: '宁夏',value: 0},
                            {name: '海南',value: 0},
                            {name: '台湾',value: 0},
                            {name: '香港',value: 0},
                            {name: '澳门',value: 0},
                            {name: '内蒙古',value:0},
                            {name: '黑龙江',value: 0},

                        ]
                    },
                    {
                        name: '女',
                        type: 'map',
                        mapType: 'china',
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data: [
                            {name: '北京',value: 0},
                            {name: '天津',value: 0},
                            {name: '上海',value: 0},
                            {name: '重庆',value: 0},
                            {name: '河北',value: 0},
                            {name: '河南',value: 0},
                            {name: '云南',value: 0},
                            {name: '辽宁',value: 0},
                            {name: '湖南',value: 0},
                            {name: '安徽',value: 0},
                            {name: '山东',value: 0},
                            {name: '新疆',value: 0},
                            {name: '江苏',value: 0},
                            {name: '浙江',value: 0},
                            {name: '江西',value: 0},
                            {name: '湖北',value: 0},
                            {name: '广西',value: 0},
                            {name: '甘肃',value: 0},
                            {name: '山西',value: 0},
                            {name: '陕西',value: 0},
                            {name: '吉林',value: 0},
                            {name: '福建',value: 0},
                            {name: '贵州',value: 0},
                            {name: '广东',value: 0},
                            {name: '青海',value: 0},
                            {name: '西藏',value: 0},
                            {name: '四川',value: 0},
                            {name: '宁夏',value: 0},
                            {name: '海南',value: 0},
                            {name: '台湾',value: 0},
                            {name: '香港',value: 0},
                            {name: '澳门',value: 0},
                            {name: '内蒙古',value: 0},
                            {name: '黑龙江',value: 0},
                        ]
                    }
                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            $.get("${pageContext.request.contextPath}/user/queryAllByLocation","json", function (data) {
                myChart.setOption({
                    series: [{
                        name: '男',
                        type: 'map',
                        mapType: 'china',
                        roam: false,
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data: data.man,
                    }, {
                        name: '女',
                        type: 'map',
                        mapType: 'china',
                        roam: false,
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data: data.women,
                    }]
                })

            })
            goEasy.subscribe({
                channel: "cmfz",
                onMessage: function (message) {
                    console.log(message.content);
                    //var data = eval('(' + message.content + ')');
                    var data = JSON.parse(message.content);
                    myChart.setOption({
                        series: [{
                            name: '男',
                            type: 'map',
                            mapType: 'china',
                            roam: false,
                            label: {
                                normal: {
                                    show: false
                                },
                                emphasis: {
                                    show: true
                                }
                            },
                            data: data.man,
                        }, {
                            name: '女',
                            type: 'map',
                            mapType: 'china',
                            roam: false,
                            label: {
                                normal: {
                                    show: false
                                },
                                emphasis: {
                                    show: true
                                }
                            },
                            data: data.women,
                        }]
                    })
                }
            });

        });
    </script>


<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="uMap" style="width: 600px;height:400px;"></div>


