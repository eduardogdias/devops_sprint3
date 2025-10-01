package br.com.fiap.sprint3.controller.api;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.sprint3.model.dto.patio.PatioRequestDTO;
import br.com.fiap.sprint3.model.dto.patio.PatioResponseDTO;
import br.com.fiap.sprint3.model.entity.Patio;
import br.com.fiap.sprint3.model.repository.PatioRepository;
import br.com.fiap.sprint3.model.service.PatioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/patios")
@SuppressWarnings("all")
public class PatioController {

    @Autowired
    private PatioService patioService;

    @GetMapping
    public ResponseEntity listar() {
        return ResponseEntity.ok(patioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Integer id) {
        PatioResponseDTO dto = patioService.buscarPorId(id);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @PostMapping
    public ResponseEntity criar(@RequestBody @Valid PatioRequestDTO request) {
        PatioResponseDTO dto = patioService.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Integer id, @RequestBody @Valid PatioRequestDTO request) {
        PatioResponseDTO dto = patioService.atualizar(id, request);
        if (dto != null) {
            return ResponseEntity.ok(dto);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Integer id) {
        boolean deleted = patioService.deletar(id);
        if (deleted) {
            return ResponseEntity.ok(Map.of("mensagem", "Pátio com ID " + id + " deletado com sucesso."));
        } else {
            throw new EntityNotFoundException();
        }
    }
}
