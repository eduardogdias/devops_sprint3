package br.com.fiap.sprint3.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.sprint3.model.dto.zona.ZonaRequestDTO;
import br.com.fiap.sprint3.model.dto.zona.ZonaResponseDTO;
import br.com.fiap.sprint3.model.entity.Patio;
import br.com.fiap.sprint3.model.entity.Zona;
import br.com.fiap.sprint3.model.repository.PatioRepository;
import br.com.fiap.sprint3.model.repository.ZonaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ZonaService {

    @Autowired
    private ZonaRepository zonaRepository;

    @Autowired
    private PatioRepository patioRepository;

    
    public List<ZonaResponseDTO> listar() {
        return zonaRepository.findAll().stream()
                .map(ZonaResponseDTO::new)
                .collect(Collectors.toList());
    }

    
    public ZonaResponseDTO buscarPorId(Integer id) {
        return zonaRepository.findById(id)
                .map(ZonaResponseDTO::new)
                .orElse(null);
    }

    
    @Transactional
    public ZonaResponseDTO criar(ZonaRequestDTO dto) {
        Patio patio = patioRepository.findById(dto.getPatioId())
                .orElseThrow(() -> new EntityNotFoundException("Pátio não encontrado"));

        Zona zona = new Zona();
        zona.setTipo(dto.getTipo());
        zona.setQtdVaga(dto.getQtdVaga());
        zona.setPatio(patio);

        return new ZonaResponseDTO(zonaRepository.save(zona));
    }

    
    @Transactional
    public ZonaResponseDTO atualizar(Integer id, ZonaRequestDTO dto) {
        Zona zona = zonaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Zona não encontrada"));

        Patio patio = patioRepository.findById(dto.getPatioId())
                .orElseThrow(() -> new EntityNotFoundException("Pátio não encontrado"));

        zona.setTipo(dto.getTipo());
        zona.setQtdVaga(dto.getQtdVaga());
        zona.setPatio(patio);

        return new ZonaResponseDTO(zonaRepository.save(zona));
    }

    
    @Transactional
    public boolean deletar(Integer id) {
        Zona zona = zonaRepository.findById(id).orElse(null);
        if (zona != null) {
            zonaRepository.delete(zona);
            return true;
        }
        return false;
    }
}

