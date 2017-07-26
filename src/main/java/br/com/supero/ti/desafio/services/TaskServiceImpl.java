package br.com.supero.ti.desafio.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.supero.ti.desafio.model.Task;
import br.com.supero.ti.desafio.repositories.TaskRepository;
import br.com.supero.ti.desafio.util.TaskStatus;

@Service("taskService")
@Transactional
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	public Task findById(Long id) {
		return taskRepository.findOne(id);
	}

	public List<Task> findAllTasks() {
		return taskRepository.findAll();
	}

	public void saveTask(Task task) {
		if (task.getStatus() == null || task.getStatus() == "") {
			task.setStatus(TaskStatus.TODO.name());
		}
		if (task.getDateadded() == null) {
			task.setDateadded(new Date());
		}
		taskRepository.save(task);
	}

	public void updateTask(Task task) {
		saveTask(task);
	}

	public void deleteTask(Long id) {
		taskRepository.delete(id);
	}

	@Override
	public List<Task> findTodoTasks() {
		return taskRepository.findTodoTasks();
	}

	@Override
	public List<Task> findDoingTasks() {
		return taskRepository.findDoingTasks();
	}

	@Override
	public List<Task> findDoneTasks() {
		return taskRepository.findDoneTasks();
	}
}