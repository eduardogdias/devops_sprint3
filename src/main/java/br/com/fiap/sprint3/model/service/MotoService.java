package br.com.fiap.sprint3.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.sprint3.model.dto.moto.MotoRequestDTO;
import br.com.fiap.sprint3.model.dto.moto.MotoResponseDTO;
import br.com.fiap.sprint3.model.entity.Moto;
import br.com.fiap.sprint3.model.repository.MotoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class MotoService {

    @Autowired
    private MotoRepository motoRepository;

    
    private String determinarMarca(String modelo) {
        return switch (modelo.toLowerCase()) {
            case "pop" -> "Honda";
            case "sport" -> "TVS";
            case "e" -> "Soco";
            default -> throw new IllegalArgumentException("Modelo não reconhecido");
        };
    }

    
    // GET
    public List<MotoResponseDTO> listar() {
        return motoRepository.findAll()
                .stream()
                .map(MotoResponseDTO::new)
                .collect(Collectors.toList());
    }

    // GET por Id
    public MotoResponseDTO buscarPorId(Integer id) {
        return motoRepository.findById(id)
                .map(MotoResponseDTO::new)
                .orElseThrow(() -> new EntityNotFoundException("Moto não encontrada"));
    }
    
    // GET por Query Params
    public List<MotoResponseDTO> buscarPorMarcaModelo(String marca, String modelo) {
        return motoRepository.findAll()
                .stream()
                .filter(m -> (marca == null || m.getMarca().equalsIgnoreCase(marca)) &&
                             (modelo == null || m.getModelo().equalsIgnoreCase(modelo)))
                .map(MotoResponseDTO::new)
                .collect(Collectors.toList());
    }
    
    //---------------------------------
    
    // POST
    @Transactional
    public MotoResponseDTO criar(MotoRequestDTO dto) {
        if ((dto.getPlaca() == null || dto.getPlaca().isBlank()) &&
            (dto.getChassi() == null || dto.getChassi().isBlank())) {
            throw new IllegalArgumentException("É necessário informar a placa ou o chassi da moto");
        }

        Moto moto = new Moto();
        moto.setPlaca(dto.getPlaca());
        moto.setChassi(dto.getChassi());
        moto.setModelo(dto.getModelo());
        moto.setMarca(determinarMarca(dto.getModelo()));

        return new MotoResponseDTO(motoRepository.save(moto));
    }
    

    // PUT
    @Transactional
    public MotoResponseDTO atualizar(Integer id, MotoRequestDTO dto) {
        Moto moto = motoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Moto não encontrada"));

        if ((dto.getPlaca() == null || dto.getPlaca().isBlank()) &&
            (dto.getChassi() == null || dto.getChassi().isBlank())) {
            throw new IllegalArgumentException("É necessário informar a placa ou o chassi da moto");
        }

        moto.setPlaca(dto.getPlaca());
        moto.setChassi(dto.getChassi());
        moto.setModelo(dto.getModelo());
        moto.setMarca(determinarMarca(dto.getModelo()));

        return new MotoResponseDTO(motoRepository.save(moto));
    }

    // DELETE
    @Transactional
    public void deletar(Integer id) {
        Moto moto = motoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Moto não encontrada"));

        motoRepository.delete(moto);
    }

    
}
