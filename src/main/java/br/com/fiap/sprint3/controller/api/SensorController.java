package br.com.fiap.sprint3.controller.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.sprint3.model.dto.sensor.SensorRequestDTO;
import br.com.fiap.sprint3.model.dto.sensor.SensorResponseDTO;
import br.com.fiap.sprint3.model.service.SensorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sensores")
public class SensorController {

    @Autowired
    private SensorService sensorService;


    @GetMapping
    public ResponseEntity<List<SensorResponseDTO>> listar() {
        return ResponseEntity.ok(sensorService.listar());
    }

    @GetMapping("{id}")
    public ResponseEntity<SensorResponseDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(sensorService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<SensorResponseDTO> criar(@RequestBody @Valid SensorRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sensorService.criar(dto));
    }
    
    @PutMapping("{id}")
    public ResponseEntity<SensorResponseDTO> atualizar(@PathVariable Integer id, @RequestBody @Valid SensorRequestDTO dto) {
        return ResponseEntity.ok(sensorService.atualizar(id, dto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, String>> deletar(@PathVariable Integer id) {
        sensorService.deletar(id);
        Map<String, String> resposta = new HashMap<>();
        resposta.put("mensagem", "Sensor com ID " + id + " deletado com sucesso");
        return ResponseEntity.ok(resposta);
    }
}
