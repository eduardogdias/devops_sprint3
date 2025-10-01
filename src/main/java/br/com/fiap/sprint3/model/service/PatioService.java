package br.com.fiap.sprint3.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.sprint3.model.dto.patio.PatioRequestDTO;
import br.com.fiap.sprint3.model.dto.patio.PatioResponseDTO;
import br.com.fiap.sprint3.model.entity.Patio;
import br.com.fiap.sprint3.model.repository.PatioRepository;
import jakarta.transaction.Transactional;

@Service
public class PatioService {

    @Autowired
    private PatioRepository patioRepository;

    //listar
    public List<PatioResponseDTO> listar() {
        return patioRepository.findAll()
                .stream()
                .map(PatioResponseDTO::new)
                .toList();
    }

    
    //buscar
    public PatioResponseDTO buscarPorId(Integer id) {
        return patioRepository.findById(id)
                .map(PatioResponseDTO::new) //se o objeto foi encontrado, transforma ele em um PatioResponseDTO (converte a entidade para um DTO de resposta)
                .orElse(null); //se nao for encontrado retona null
    }

    
    //criar
    @Transactional   // @Transactional -> faz um rollback na alteracoes se o metodo gerar excecao 
    public PatioResponseDTO criar(PatioRequestDTO request) {
        Patio patio = new Patio();
        patio.setNome(request.getNome());
        patio.setEndereco(request.getEndereco());
        Patio salvo = patioRepository.save(patio);
        return new PatioResponseDTO(salvo);
    }

    
    //atualizar
    @Transactional
    public PatioResponseDTO atualizar(Integer id, PatioRequestDTO request) {
        Optional<Patio> optional = patioRepository.findById(id); // optional -> retorna o patio se encontrado, ou null
        if (optional.isPresent()) {
            Patio patio = optional.get();
            patio.setNome(request.getNome());
            patio.setEndereco(request.getEndereco());
            Patio atualizado = patioRepository.save(patio);
            return new PatioResponseDTO(atualizado);
        }
        return null;
    }

    
    //deletar
    @Transactional
    public boolean deletar(Integer id) {
        Optional<Patio> patio = patioRepository.findById(id);
        if (patio.isPresent()) {
            patioRepository.delete(patio.get());
            return true;
        }
        return false;
    }
}



