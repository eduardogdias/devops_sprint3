package br.com.fiap.sprint3.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fiap.sprint3.model.dto.moto.MotoRequestDTO;
import br.com.fiap.sprint3.model.dto.moto.MotoResponseDTO;
import br.com.fiap.sprint3.model.entity.Moto;
import br.com.fiap.sprint3.model.service.MotoService;

@Controller
@RequestMapping("/web/motos")
public class MotoWebController {
	
	@Autowired
	private MotoService motoService;
	
	
	@GetMapping("/cadastrar")
	public String cadastrar(Model model) {
		model.addAttribute("moto", new Moto());
		return "moto/moto-form";
	}
	
	@PostMapping("/salvar")
	public String salvar(Moto moto) {
		
		MotoRequestDTO dto = new MotoRequestDTO(moto);
		
		if(moto.getId() != null) {
			motoService.atualizar(moto.getId(), dto);
		} else {
			motoService.criar(dto);
		}
		
		return "redirect:/web/motos/listar";
	}
	
	
	@GetMapping("/listar")
	public String listar(Model model) {
		List<MotoResponseDTO> motos = motoService.listar();
		model.addAttribute("motos", motos);
		return "moto/moto-lista";
	}

	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		MotoResponseDTO moto = motoService.buscarPorId(id);
		model.addAttribute("moto", moto);
		return "moto/moto-form";
	}
	
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable Integer id) {
		motoService.deletar(id);
		return "redirect:/web/motos/listar";
	}
}
