package br.com.supero.ti.desafio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.supero.ti.desafio.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	Task findByTitle(String title);

	@Query("select t from Task t where status = 'TODO' ")
	public List<Task> findTodoTasks();

	@Query("select t from Task t where status = 'DOING' ")
	public List<Task> findDoingTasks();

	@Query("select t from Task t where status = 'DONE' ")
	public List<Task> findDoneTasks();
}