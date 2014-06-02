package com.abc.task.web.uc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abc.task.annotation.LoginMember;
import com.abc.task.annotation.RequiredLogin;
import com.abc.task.annotation.ResultTypeEnum;
import com.abc.task.enums.WealthType;
import com.abc.task.service.AccountService;
import com.abc.task.vo.Member;

@Controller
public class UcAccountActions {
	@Resource
	private AccountService uCAccountService;

	@Resource
	private ObjectMapper objectMapper;

	@Resource
	private Map<String, String> config;

	@RequiredLogin(ResultTypeEnum.json)
	@ResponseBody
	@RequestMapping("/ucenter/score.action")
	public Map<String, Object> sorce(HttpServletRequest request,
			HttpServletResponse response, @LoginMember Member loginMember) {
		return uCAccountService.userScore(loginMember.getId());
	}
	
	@RequiredLogin(ResultTypeEnum.json)
	@ResponseBody
	@RequestMapping("/ucenter/scorelog.action")
	public List<Map<String, Object>> scoreLog(HttpServletRequest request,
			HttpServletResponse response, @LoginMember Member loginMember,
			@RequestParam(value = "itemId", required = false, defaultValue = "0") int itemId,
			@RequestParam(value = "begin", required = false) Date begin,
			@RequestParam(value = "end", required = false) Date end,
			@RequestParam(value = "p",defaultValue="1") int page,
			@RequestParam(value = "e" , defaultValue="10") int per) {
		return uCAccountService.scoreLogs(page,per,itemId, loginMember.getId(), begin, end);
	}
	
	@ResponseBody
	@RequestMapping("/ucenter/scorelogcount.action")
	public int scoreLogCount(HttpServletRequest request,
			HttpServletResponse response, @LoginMember Member loginMember,
			@RequestParam(value = "itemId", required = false, defaultValue = "0") int itemId,
			@RequestParam(value = "begin", required = false) Date begin,
			@RequestParam(value = "end", required = false) Date end) {
		return uCAccountService.scoreLogCount(itemId, loginMember.getId(), begin, end);
	}
	
	@RequiredLogin(ResultTypeEnum.json)
	@ResponseBody
	@RequestMapping("/ucenter/delayscore.action")
	public int delaySorce(HttpServletRequest request,
			HttpServletResponse response, @LoginMember Member loginMember) {
		return uCAccountService.userDelayScore(loginMember.getId());
	}
	
	@RequiredLogin(ResultTypeEnum.json)
	@ResponseBody
	@RequestMapping("/ucenter/delayscorelog.action")
	public List<Map<String, Object>> memberScoreLogDelay(HttpServletRequest request,
			HttpServletResponse response, @LoginMember Member loginMember,
			@RequestParam(value = "p",defaultValue="1") int page,
			@RequestParam(value = "e" , defaultValue="10") int per) {
		return uCAccountService.delayScoreLogs(page,per,loginMember.getId());
	}
	
	@ResponseBody
	@RequestMapping("/ucenter/delayscorelogcount.action")
	public int memberScoreLogDelay(HttpServletRequest request,
			HttpServletResponse response, @LoginMember Member loginMember) {
		return uCAccountService.delayScoreLogCount(loginMember.getId());
	}
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class,new CustomDateEditor(format, true));
		binder.registerCustomEditor(Date.class,new CustomDateEditor(format, true));
	}
	@ResponseBody
	@RequestMapping("/taskrecentlist.action")
	public List<Map<String, Object>> recentList(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "c",required = false,defaultValue="5") int c,
			@RequestParam(value = "t",required = false) WealthType t,
			@RequestParam(value = "b",required = false) Date begin,
			@RequestParam(value = "e",required = false) Date end){
		return uCAccountService.accountPeriodList(c, t, begin, end);
	}
	@ResponseBody
	@RequestMapping("/taskrecentlogs.action")
	public List<Map<String, Object>> recentLogs(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "c",required = false,defaultValue="5") int c,
			@RequestParam(value = "t",required = false) WealthType t,
			@RequestParam(value = "b",required = false) Date begin,
			@RequestParam(value = "e",required = false) Date end){
		return uCAccountService.accountPeriodLogs(c, t, begin, end);
	}
}
