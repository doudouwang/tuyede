package com.abc.task.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service("adService")
public class AdService {
	@Resource
	private JdbcTemplate jdbcTemplate;

	public Map<String, Object> getAdScript(int id) {
		return jdbcTemplate.queryForMap(
				"select * from ad_script where id = ?", id);
	}
	
	public List<Map<String, Object>> getAdContent(String group) {
		return jdbcTemplate.queryForList(
				"select * from ad_content where group = ?", group);
	}
}
