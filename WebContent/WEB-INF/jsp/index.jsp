<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html><html lang=zh>
<head>
	<%@ include file="jscss.jsp" %>
	<script type="text/javascript" src="js/sollortwo.js"></script>
</head>
<body>
<%@ include file="top.jsp" %>
<%@ include file="head.jsp" %>
<div class="imain">
	<div class="left">
    	<script type="text/javascript">ad_show('1');</script>
        <div class="tash_name font16">任务中心</div>
        <div class="tash_bg">
            <div class="tash_list">
            	<ul id="tasklist"></ul>
 		  		<div class="clear"></div>
            </div>
            <div id="page" class="pages"></div>
        </div>
    </div>
    <div class="right">
    	<!--  个人中心-->
    	<div class="center_bg">
        	<div class="center_name font16">个人中心</div>
         	<div id="nologindiv" class="center_nr">
            	<p>做任务轻松赚集分宝！</p>
                <p class="center_botton"><a href="/login.htm">会员登陆<b></b></a></p>
            </div>
            <div id="logindiv" class="center_nr_dl" style="display:none">
            	<p>您好<span id="i_uname"> </span></p>
                <p>集分宝余额：<strong id="i_uscore"></strong>个</p>
               	<p>我最近参与了<b>乱世隋唐</b></p>
                <p class="center_botton_dl"><a href="/ucenter/exchange.htm">兑换集分宝<b></b></a></p>
            </div>
        </div>
        <!--  公告  -->
        <div class="guanggao_bg">
        	<div class="guanggao_name font16">公告</div>
        	<div class="guanggao_list">
        	<script type="text/javascript">ad_show('2');</script>
            </div>
        </div>
        <!--  最新兑换  -->
        <div class="guanggao_bg">
        	<div class="guanggao_name font16">最近兑换</div>
        	<div class="duihuan_list">
            	<ul id="recent_task_score_list">
                </ul>
            </div>
        </div>
        <!--  积分赚取榜  -->
        <div class="guanggao_bg">
        	<div class="guanggao_name font16">积分赚取榜</div>
            <div class="jifen_name">
            	<ul>
                	<li><a class="hover" id="one1" >今日</a></li>
                	<li class="next"><a id="one2" >昨日</a></li>
                </ul>
            </div>
        	<div class="jifen_list" id="con_one_1">
            	<ul id="todayList">
                </ul>
                <div class="clear"></div>
            </div>
            <div class="jifen_list display_none" id="con_one_2">
            	<ul id="yesterdayList">
                </ul>
                <div class="clear"></div>
            </div>
        </div> 
    </div>
    <div class="clear"></div>
</div>
<%@ include file="foot.jsp" %>
<script src="/js/index.js" type="text/javascript"></script>
</body>
</html>