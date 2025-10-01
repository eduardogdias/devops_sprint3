package br.com.fiap.sprint3.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fiap.sprint3.model.dto.historico.HistoricoRequestDTO;
import br.com.fiap.sprint3.model.dto.historico.HistoricoResponseDTO;
import br.com.fiap.sprint3.model.entity.Historico;
import br.com.fiap.sprint3.model.service.HistoricoService;

@Controller
@RequestMapping("/web/historicos")
public class HistoricoWebController {
	
	@Autowired
	private HistoricoService historicoService;
	
	
	@GetMapping("/cadastrar")
	public String cadastrar(Model model) {
		model.addAttribute("historico", new Historico());
		return "historico/historico-form";
	}
	
	@PostMapping("/salvar")
	public String salvar(Historico historico) {
		
		HistoricoRequestDTO dto = new HistoricoRequestDTO(historico);
		
		if(historico.getId() != null) {
			historicoService.atualizar(historico.getId(), dto);
		} else {
			historicoService.criar(dto);
		}
		
		return "redirect:/web/historicos/listar";
	}
	
	
	@GetMapping("/listar")
	public String listar(Model model) {
		List<HistoricoResponseDTO> historicos = historicoService.listar();
		model.addAttribute("historicos", historicos);
		return "historico/historico-lista";
	}

	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		HistoricoResponseDTO historico = historicoService.buscarPorId(id);
		model.addAttribute("historico", historico);
		return "historico/historico-form";
	}
	
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable Integer id) {
		historicoService.deletar(id);
		return "redirect:/web/historicos/listar";
	}
}
