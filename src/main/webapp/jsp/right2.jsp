<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<!--横幅-->
<div class="jumbotron" id="centerLay">
    <div class="container">
        <h3>欢迎使用持名法洲系统！</h3>
    </div>
</div>
<div id="myCarousel" class="carousel slide">
    <!-- 轮播（Carousel）指标 -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <!-- 轮播（Carousel）项目 -->
    <div class="carousel-inner">
        <div class="item active">
            <img src="${pageContext.request.contextPath}/img/1.jpg" alt="First slide" style="height: 600px;width: 1400px">
            <div class="carousel-caption">彩虹六号</div>
        </div>
        <div class="item">
            <img src="${pageContext.request.contextPath}/img/2.jpg" alt="Second slide">
            <div class="carousel-caption">不认识的男人</div>
        </div>
        <div class="item">
            <img src="${pageContext.request.contextPath}/img/4.png" alt="Third slide">
            <div class="carousel-caption">龙狙</div>
        </div>
    </div>
    <!-- 轮播（Carousel）导航 -->
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>