package br.com.fiap.sprint3.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fiap.sprint3.model.dto.zona.ZonaRequestDTO;
import br.com.fiap.sprint3.model.dto.zona.ZonaResponseDTO;
import br.com.fiap.sprint3.model.entity.Zona;
import br.com.fiap.sprint3.model.service.ZonaService;

@Controller
@RequestMapping("/web/zonas")
public class ZonaWebController {
	
	@Autowired
	private ZonaService zonaService;
	
	
	@GetMapping("/cadastrar")
	public String cadastrar(Model model) {
		model.addAttribute("zona", new Zona());
		return "zona/zona-form";
	}
	
	@PostMapping("/salvar")
	public String salvar(Zona zona) {
		
		ZonaRequestDTO dto = new ZonaRequestDTO(zona);
		
		if(zona.getId() != null) {
			zonaService.atualizar(zona.getId(), dto);
		} else {
			zonaService.criar(dto);
		}
		
		return "redirect:/web/zonas/listar";
	}
	
	
	@GetMapping("/listar")
	public String listar(Model model) {
		List<ZonaResponseDTO> zonas = zonaService.listar();
		model.addAttribute("zonas", zonas);
		return "zona/zona-lista";
	}

	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		ZonaResponseDTO zona = zonaService.buscarPorId(id);
		model.addAttribute("zona", zona);
		return "zona/zona-form";
	}
	
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable Integer id) {
		zonaService.deletar(id);
		return "redirect:/web/zonas/listar";
	}
}
