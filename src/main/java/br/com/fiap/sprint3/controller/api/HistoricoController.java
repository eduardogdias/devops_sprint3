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
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.sprint3.model.dto.historico.HistoricoRequestDTO;
import br.com.fiap.sprint3.model.service.HistoricoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/historicos")
@SuppressWarnings("all")
public class HistoricoController {

    @Autowired
    private HistoricoService historicoService;

   
    @GetMapping
    public ResponseEntity listar() {
        return ResponseEntity.ok(historicoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(historicoService.buscarPorId(id));
    }
    
    @GetMapping("/moto/{motoId}")
    public ResponseEntity buscarPorMoto(@PathVariable Integer motoId) {
        return ResponseEntity.ok(historicoService.buscarPorMotoId(motoId));
    }

    @PostMapping
    public ResponseEntity criar(@RequestBody @Valid HistoricoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(historicoService.criar(dto));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Integer id, @RequestBody @Valid HistoricoRequestDTO dto) {
        return ResponseEntity.ok(historicoService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Integer id) {
        historicoService.deletar(id);
        Map<String, String> resposta = new HashMap<>();
        resposta.put("mensagem", "Histórico com id " + id + " deletado com sucesso");
        return ResponseEntity.ok(resposta);
    }
}

