$("#one1").on("mouseover",function(){$("#one1").addClass('hover');$("#one2").removeClass('hover');$("#con_one_1").show();$("#con_one_2").hide();});
$("#one2").on("mouseover",function(){$("#one2").addClass('hover');$("#one1").removeClass('hover');$("#con_one_2").show();$("#con_one_1").hide();});
var load_option = {
		p:1,
		per:10,
		page_id:'page',
		url:J_task.Config.website+"loadtasks.action",
		params:{},
		showlist:function(result){
			var li = new StringBuilder();
			$.each(result,function(k,v){
				li.append("<li>");
				li.append("<b>");
				li.append("<a href='/task.htm?id=").append(v.id).append("' target='_blank'>");
				li.append("<img style='max-width:60px;max-height:73px' src='").append(v.logo).append("' />");
				li.append("</a>");
				li.append("</b>");
				li.append("<span>");
				li.append("<p class='title'>");
				li.append("<a href='/task.htm?id=").append(v.id).append("' target='_blank'>").append(v.name).append("</a>");
				li.append("</p>");
				li.append("<p class='color_99'>可赚：");
				li.append("<strong class='color_hs'>");
				li.append("<em class='number'>");
				li.append(v.award);
				li.append("</em>个");
				li.append("</strong>");
				li.append("</p>");
				li.append("<p class='color_99'>");
				li.append(v.partakeCount);
				li.append("人关注</p>");
				li.append("</span>");
				li.append("</li>");
			});
			$("#tasklist").html(li.toString());
		}
};
(function() {
	load_option.params.cat = 1;
})();
load({data:load_option});
var now = new Date();
var _now =  new Date();
_now.setDate(now.getDate()-1);
var today = now.format('yyyy-MM-dd');
var yesterday = _now.format('yyyy-MM-dd');
$.ajax({
	url : J_task.Config.website+"taskrecentlist.action",
	type : "POST",
	dataType : "JSON",
	jsonp : "jsoncallback",
	async:false,
	data : {'c':10,'t':'TASK','b':today,'e':today},
	success : function(result) {
		var li = new StringBuilder();
		$.each(result,function(k,v){
			li.append("<li>");
			li.append("<span>");
			li.append(k+1);
			li.append("</span>");
			li.append("<strong>");
			li.append(v.name);
			li.append("</strong>");
			li.append("</p>");
			li.append("<p>");
			li.append(v.wealth);
			li.append("</p>");
			li.append("</li>");
		});
		$("#todayList").html(li.toString());
	},
	error : function(xhr, ts, et) {
		xhr = null;
		load_option.t = 0;
	}
});
$.ajax({
	url : J_task.Config.website+"taskrecentlist.action",
	type : "POST",
	dataType : "JSON",
	jsonp : "jsoncallback",
	async:false,
	data : {'c':10,'t':'TASK','b':yesterday,'e':yesterday},
	success : function(result) {
		var li = new StringBuilder();
		$.each(result,function(k,v){
			li.append("<li>");
			li.append("<span>");
			li.append(k+1);
			li.append("</span>");
			li.append("<strong>");
			li.append(v.name);
			li.append("</strong>");
			li.append("</p>");
			li.append("<p>");
			li.append(v.wealth);
			li.append("</p>");
			li.append("</li>");
		});
		$("#yesterdayList").html(li.toString());
	},
	error : function(xhr, ts, et) {
		xhr = null;
	}
});