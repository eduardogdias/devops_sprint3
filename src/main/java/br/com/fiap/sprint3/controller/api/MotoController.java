package br.com.fiap.sprint3.controller.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.sprint3.model.dto.moto.MotoRequestDTO;
import br.com.fiap.sprint3.model.service.MotoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/motos")
@SuppressWarnings("all")
public class MotoController {

    @Autowired
    private MotoService motoService;

    

    @GetMapping
    public ResponseEntity listar() {
        return ResponseEntity.ok(motoService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(motoService.buscarPorId(id));
    }

    @GetMapping("/buscar") // query params
    public ResponseEntity buscarPorMarcaModelo(@RequestParam(required = false) String marca, @RequestParam(required = false) String modelo) {
        return ResponseEntity.ok(motoService.buscarPorMarcaModelo(marca, modelo));
    }
    
    
    @PostMapping
    public ResponseEntity criar(@RequestBody @Valid MotoRequestDTO dto) {
        return ResponseEntity.status(201).body(motoService.criar(dto));
    }
    
    
    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Integer id, @RequestBody @Valid MotoRequestDTO dto) {
        return ResponseEntity.ok(motoService.atualizar(id, dto));
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Integer id) {
        motoService.deletar(id);
        return ResponseEntity.ok().body(Map.of("mensagem", "Moto deletada com sucesso"));
    }

    
}

