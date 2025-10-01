package br.com.fiap.sprint3.model.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.sprint3.model.entity.Moto;

public interface MotoRepository extends JpaRepository<Moto, Integer> {
    List<Moto> findByMarca(String marca);
    List<Moto> findByModelo(String modelo);
    List<Moto> findByMarcaAndModelo(String marca, String modelo);
}
