package com.abc.task.web.ad;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.abc.task.service.AdService;

@Controller
public class Ad {
	@Resource
	private AdService adService;
	@Resource
	private ObjectMapper objectMapper;

	@RequestMapping(value = "/adshow")
	public String ad(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = true) int id) {
		Map<String, Object> ad = adService.getAdScript(id);
		request.setAttribute("ad", ad);
		return (String)ad.get("tpl");
	}
}
