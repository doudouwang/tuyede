package com.abc.task.web.task;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.abc.task.service.TaskService;
import com.abc.task.vo.TaskItem;

@Controller
public class TaskActions {
	@Resource
	private TaskService taskService;
	@Resource
	private ObjectMapper objectMapper;

	@ResponseBody
	@RequestMapping("/loadtasks.action")
	public List<TaskItem> tasks(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "p",defaultValue="1") int page,
			@RequestParam(value = "e" , defaultValue="10") int per,
			@RequestParam(value = "c" , defaultValue="0") int cat){
		return taskService.taskItemList(per, page, cat, "partake");
	}
	@ResponseBody
	@RequestMapping("/taskcount.action")
	public int taskCount(HttpServletRequest request, HttpServletResponse response){
		return taskService.taskCount();
	}
}
