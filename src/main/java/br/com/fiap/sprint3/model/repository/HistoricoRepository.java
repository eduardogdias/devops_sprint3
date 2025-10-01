package br.com.fiap.sprint3.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.sprint3.model.entity.Historico;

public interface HistoricoRepository extends JpaRepository<Historico, Integer> {
	List<Historico> findByMotoId(Integer motoId);

}
