package br.com.fiap.sprint3.controller.api;

import java.util.Collections;

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

import br.com.fiap.sprint3.model.dto.zona.ZonaRequestDTO;
import br.com.fiap.sprint3.model.dto.zona.ZonaResponseDTO;
import br.com.fiap.sprint3.model.service.ZonaService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/zonas")
@SuppressWarnings("all")
public class ZonaController {

    @Autowired
    private ZonaService zonaService;

    
    @GetMapping
    public ResponseEntity listarZonas() {
        return ResponseEntity.ok(zonaService.listar());
    }

    
    @GetMapping("/{id}")
    public ResponseEntity buscarZonaPorId(@PathVariable Integer id) {
        ZonaResponseDTO dto = zonaService.buscarPorId(id);
        if (dto == null) {
            throw new EntityNotFoundException("Zona não encontrada");
        }
        return ResponseEntity.ok(dto);
    }

    
    @PostMapping
    public ResponseEntity<ZonaResponseDTO> criarZona(@RequestBody @Valid ZonaRequestDTO dto) {
        ZonaResponseDTO criada = zonaService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarZona(@PathVariable Integer id, @RequestBody @Valid ZonaRequestDTO dto) {
        ZonaResponseDTO atualizada = zonaService.atualizar(id, dto);
        return ResponseEntity.ok(atualizada);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarZona(@PathVariable Integer id) {
        boolean deletada = zonaService.deletar(id);
        if (deletada) {
            return ResponseEntity.ok(Collections.singletonMap("mensagem", "Zona com ID " + id + " deletada com sucesso!"));
        } else {
            throw new EntityNotFoundException("Zona não encontrada");
        }
    }
}
