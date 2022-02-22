package com.mpinfo.tarefas.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tarefa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;	
	private Boolean pronto;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime dataCriada;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private LocalDateTime tarefaPronta;
	
	@PrePersist
	public void beforesave() {
		setDataCriada(LocalDateTime.now());
	}

}
