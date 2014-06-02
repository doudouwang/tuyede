package com.abc.task.web.ad;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abc.task.service.AdService;

@Controller
public class AdActions {
	@Resource
	private AdService adService;
	@Resource
	private ObjectMapper objectMapper;

	@ResponseBody
	@RequestMapping(value = "/ad.action")
	public Map<String, Object> ad(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "id", required = true) int id) {
		return adService.getAdScript(id);
	}
	
	@ResponseBody
	@RequestMapping(value = "/adcontent.action")
	public List<Map<String, Object>> adContent(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "g", required = true) String group) {
		return adService.getAdContent(group);
	}
}
