<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<link href="${ctxStatic}/common/leaning.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-gl/echarts-gl.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/simplex.js"></script>
<script src="${ctxStatic}/echart/dark.js" type="text/javascript"></script>
<script src="${ctxStatic}/echart/roma.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
	var dom = document.getElementById("container");
	console.log(dom);
	var myChart = echarts.init(dom,"roma");
	var app = {};
	option = null;
	option = {
	    xAxis: {show:false},
	    yAxis: {show:false},
	    tooltip:{show:true,trigger:'item',formatter:'人流数{c}'},
	    series: [{
	        symbolSize: 20,
	        data: [
	            [10.0, 8.04],
	            [8.0, 6.95],
	            [13.0, 7.58],
	            [9.0, 8.81],
	            [11.0, 8.33],
	            [14.0, 9.96],
	            [6.0, 7.24],
	            [4.0, 4.26],
	            [12.0, 10.84],
	            [7.0, 4.82],
	            [5.0, 5.68]
	        ],
	        type: 'scatter'
	    }]
	};
	if (option && typeof option === "object") {
	    myChart.setOption(option, true);
	}
})
	

</script>
<title>人流</title>
</head>
<body>
<div id="container" class="img-back">

</div>
</body>
</html>