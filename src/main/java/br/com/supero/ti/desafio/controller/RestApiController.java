package br.com.supero.ti.desafio.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.mysql.jdbc.StringUtils;

import br.com.supero.ti.desafio.model.Task;
import br.com.supero.ti.desafio.services.TaskService;
import br.com.supero.ti.desafio.util.CustomErrorType;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

	@Autowired
	TaskService taskService;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/task/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getTask(@PathVariable("id") long id) {
		logger.info("Fetching Task with id {}", id);
		Task task = taskService.findById(id);
		if (task == null) {
			logger.error("Task with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Task with id " + id + " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/task", method = RequestMethod.POST)
	public ResponseEntity<?> createTask(@RequestBody Task task, UriComponentsBuilder ucBuilder) {
		logger.info("Creating Task : {}", task);
		taskService.saveTask(task);
		return new ResponseEntity<Task>(task, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/task/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateTask(@PathVariable("id") long id, @RequestBody Task task) {
		logger.info("Updating Task with id {}", id);

		Task currentTask = taskService.findById(id);

		if (currentTask == null) {
			logger.error("Unable to update. Task with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to upate. Task with id " + id + " not found."), HttpStatus.NOT_FOUND);
		}

		if (!StringUtils.isNullOrEmpty(task.getTitle())) {
			currentTask.setTitle(task.getTitle());
		}
		if (!StringUtils.isNullOrEmpty(task.getDescription())) {
			currentTask.setDescription(task.getDescription());
		}
		if (!StringUtils.isNullOrEmpty(task.getStatus())) {
			currentTask.setStatus(task.getStatus().toUpperCase());
		}

		taskService.updateTask(currentTask);
		return new ResponseEntity<Task>(currentTask, HttpStatus.OK);
	}

	@CrossOrigin
	@RequestMapping(value = "/task/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTask(@PathVariable("id") long id) {
		logger.info("Fetching & Deleting Task with id {}", id);

		Task task = taskService.findById(id);
		if (task == null) {
			logger.error("Unable to delete. Task with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("Unable to delete. Task with id " + id + " not found."), HttpStatus.NOT_FOUND);
		}
		taskService.deleteTask(id);
		return new ResponseEntity<Task>(HttpStatus.OK);
	}

	@RequestMapping(value = "/tasks", method = RequestMethod.GET)
	public ResponseEntity<List<Task>> listAllTasks() {
		List<Task> tasks = taskService.findAllTasks();
		if (tasks.isEmpty()) {
			return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}

	@RequestMapping(value = "/todotasks", method = RequestMethod.GET)
	public ResponseEntity<List<Task>> listTodoTasks() {
		List<Task> tasks = taskService.findTodoTasks();
		if (tasks.isEmpty()) {
			return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}

	@RequestMapping(value = "/doingtasks", method = RequestMethod.GET)
	public ResponseEntity<List<Task>> listDoingTasks() {
		List<Task> tasks = taskService.findDoingTasks();
		if (tasks.isEmpty()) {
			return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}

	@RequestMapping(value = "/donetasks", method = RequestMethod.GET)
	public ResponseEntity<List<Task>> listDoneTasks() {
		List<Task> tasks = taskService.findDoneTasks();
		if (tasks.isEmpty()) {
			return new ResponseEntity<List<Task>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}
}