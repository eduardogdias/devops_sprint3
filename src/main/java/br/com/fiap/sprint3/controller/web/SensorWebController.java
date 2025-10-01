package br.com.fiap.sprint3.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.fiap.sprint3.model.dto.sensor.SensorRequestDTO;
import br.com.fiap.sprint3.model.dto.sensor.SensorResponseDTO;
import br.com.fiap.sprint3.model.entity.Sensor;
import br.com.fiap.sprint3.model.service.SensorService;

@Controller
@RequestMapping("/web/sensores")
public class SensorWebController {
	
	@Autowired
	private SensorService sensorService;
	
	
	@GetMapping("/cadastrar")
	public String cadastrar(Model model) {
		model.addAttribute("sensor", new Sensor());
		return "sensor/sensor-form";
	}
	
	@PostMapping("/salvar")
	public String salvar(Sensor sensor) {
		
		// se recebesse um SensorRequestDTO direto, não seria possível verifica o ID
		SensorRequestDTO dto = new SensorRequestDTO(sensor);
		
		if(sensor.getId() != null) {
			sensorService.atualizar(sensor.getId(), dto);
		} else {
			sensorService.criar(dto);
		}
		
		return "redirect:/web/sensores/listar";
	}
	
	
	@GetMapping("/listar")
	public String listar(Model model) {
		List<SensorResponseDTO> sensores = sensorService.listar();
		model.addAttribute("sensores", sensores);
		return "sensor/sensor-lista";
	}

	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		SensorResponseDTO sensor = sensorService.buscarPorId(id);
		model.addAttribute("sensor", sensor);
		return "sensor/sensor-form";
	}
	
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable Integer id) {
		sensorService.deletar(id);
		return "redirect:/web/sensores/listar";
	}
}
