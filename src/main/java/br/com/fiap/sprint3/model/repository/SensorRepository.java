package br.com.fiap.sprint3.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.sprint3.model.entity.Sensor;

public interface SensorRepository extends JpaRepository<Sensor, Integer>{

}
