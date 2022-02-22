package com.mpinfo.tarefas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mpinfo.tarefas.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>{

}
