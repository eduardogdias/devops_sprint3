package br.com.fiap.sprint3.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fiap.sprint3.model.dto.patio.PatioRequestDTO;
import br.com.fiap.sprint3.model.dto.patio.PatioResponseDTO;
import br.com.fiap.sprint3.model.entity.Patio;
import br.com.fiap.sprint3.model.service.PatioService;

@Controller
@RequestMapping("/web/patios")
public class PatioWebController {
	
	@Autowired
	private PatioService patioService;
	
	
	@GetMapping("/cadastrar")
	public String cadastrar(Model model) {
		model.addAttribute("patio", new Patio());
		return "patio/patio-form";
	}
	
	@PostMapping("/salvar")
	public String salvar(Patio patio) {
		
		PatioRequestDTO dto = new PatioRequestDTO(patio);
		
		if(patio.getId() != null) {
			patioService.atualizar(patio.getId(), dto);
		} else {
			patioService.criar(dto);
		}
		
		return "redirect:/web/patios/listar";
	}
	
	
	@GetMapping("/listar")
	public String listar(Model model) {
		List<PatioResponseDTO> patios = patioService.listar();
		model.addAttribute("patios", patios);
		return "patio/patio-lista";
	}

	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		PatioResponseDTO patio = patioService.buscarPorId(id);
		model.addAttribute("patio", patio);
		return "patio/patio-form";
	}
	
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable Integer id) {
		patioService.deletar(id);
		return "redirect:/web/patios/listar";
	}
}
