package br.com.fiap.sprint3.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.sprint3.model.dto.sensor.SensorRequestDTO;
import br.com.fiap.sprint3.model.dto.sensor.SensorResponseDTO;
import br.com.fiap.sprint3.model.entity.Sensor;
import br.com.fiap.sprint3.model.repository.SensorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    
    //GET
    @Transactional
    public List<SensorResponseDTO> listar() {
        return sensorRepository.findAll().stream()
                .map(SensorResponseDTO::new)
                .toList();
    }

    //GET por ID
    @Transactional
    public SensorResponseDTO buscarPorId(Integer id) {
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sensor não encontrado"));
        return new SensorResponseDTO(sensor);
    }

    //POST
    @Transactional
    public SensorResponseDTO criar(SensorRequestDTO dto) {
        Sensor sensor = new Sensor();
        sensor.setLocalizacao(dto.getLocalizacao());
        sensor.setData(dto.getData());
        sensor.setHora(dto.getHora());

        return new SensorResponseDTO(sensorRepository.save(sensor));
    }
    
    //PUT
    @Transactional
    public SensorResponseDTO atualizar(Integer id, SensorRequestDTO dto) {
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sensor não encontrado"));

        sensor.setLocalizacao(dto.getLocalizacao());
        sensor.setData(dto.getData());
        sensor.setHora(dto.getHora());

        return new SensorResponseDTO(sensorRepository.save(sensor));
    }

    //DELETE
    @Transactional
    public void deletar(Integer id) {
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sensor não encontrado"));
        sensorRepository.delete(sensor);
    }
}
