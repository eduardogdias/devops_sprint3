package br.com.fiap.sprint3.model.dto.historico;

import br.com.fiap.sprint3.model.entity.Historico;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoRequestDTO {

    @NotNull(message = "A posição é obrigatória")
    private Integer posicao;

    @NotNull(message = "O ID da moto é obrigatório")
    private Integer motoId;

    @NotNull(message = "O ID da zona é obrigatório")
    private Integer zonaId;

    @NotNull(message = "O ID do sensor é obrigatório")
    private Integer sensorId;

	public HistoricoRequestDTO(Historico historico) {
		this.posicao = historico.getPosicao();
		this.motoId = historico.getMoto().getId();
		this.zonaId = historico.getZona().getId();
		this.sensorId = historico.getSensor().getId();
	}
    
    
    
}
