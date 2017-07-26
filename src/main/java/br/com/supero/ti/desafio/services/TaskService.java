package br.com.supero.ti.desafio.services;

import java.util.List;

import br.com.supero.ti.desafio.model.Task;

public interface TaskService {
	
	Task findById(Long id);

	void saveTask(Task task);

	void updateTask(Task task);

	void deleteTask(Long id);

	List<Task> findAllTasks();

	List<Task> findTodoTasks();

	List<Task> findDoingTasks();

	List<Task> findDoneTasks();
}