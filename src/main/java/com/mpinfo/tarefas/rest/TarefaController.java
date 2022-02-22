package com.mpinfo.tarefas.rest;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.mpinfo.tarefas.model.Tarefa;
import com.mpinfo.tarefas.repositories.TarefaRepository;

@RestController
@RequestMapping("/tarefas")
@CrossOrigin("http://localhost:4200")
public class TarefaController {
	
	@Autowired
	private TarefaRepository tarefaRepo;
	
	@PostMapping
	public Tarefa save(@RequestBody Tarefa tarefa) {
		return tarefaRepo.save(tarefa);		
	}
	
	@GetMapping
	public List<Tarefa> getAll() {
		return tarefaRepo.findAll();
	}

	@GetMapping("{id}")
	public Tarefa getById(@PathVariable Long id) {
		return tarefaRepo.
				findById(id).
				orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));		
	}
	
	@DeleteMapping("{id}")
	public void delete(@PathVariable Long id) {
		tarefaRepo.deleteById(id);
	}
	
	@PatchMapping("{id}/pronto")
	public Tarefa markSePronto(@PathVariable Long id) {
		return tarefaRepo.findById(id)
				.map(tarefa -> {
					tarefa.setPronto(true);
					tarefa.setTarefaPronta(LocalDateTime.now());
					tarefaRepo.save(tarefa);
					return tarefa;
				}).orElse(null);
	}
}
