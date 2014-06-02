package com.abc.task.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abc.task.service.TaskService;
import com.abc.task.vo.TaskItem;

@Controller
public class Index {

	@Resource
	TaskService taskService;

	@RequestMapping("/index")
	public String index(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		List<Map<String, Object>> types = taskService.taskTypeList();
		request.setAttribute("types", types);

		List<TaskItem> tasks = taskService.taskItemList(12, 1, 0,
				null);
		request.setAttribute("tasks", tasks);
		return "/index";
	}
}