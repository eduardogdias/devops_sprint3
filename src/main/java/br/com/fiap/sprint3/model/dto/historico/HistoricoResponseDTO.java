package br.com.fiap.sprint3.model.dto.historico;

import br.com.fiap.sprint3.model.entity.Historico;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoResponseDTO {

    private Integer id;
    private Integer posicao;
    private Integer motoId;
    private Integer zonaId;
    private Integer sensorId;

    public HistoricoResponseDTO(Historico historico) {
        this.id = historico.getId();
        this.posicao = historico.getPosicao();
        this.motoId = historico.getMoto().getId();
        this.zonaId = historico.getZona().getId();
        this.sensorId = historico.getSensor().getId();
    }
}

