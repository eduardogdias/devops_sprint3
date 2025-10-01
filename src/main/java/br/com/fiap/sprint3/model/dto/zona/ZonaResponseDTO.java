package br.com.fiap.sprint3.model.dto.zona;

import br.com.fiap.sprint3.model.entity.Zona;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZonaResponseDTO {

    private Integer id;
    private String tipo;
    private Integer qtdVaga;
    private Integer patioId;
    private String patioNome;

    public ZonaResponseDTO(Zona zona) {
        this.id = zona.getId();
        this.tipo = zona.getTipo();
        this.qtdVaga = zona.getQtdVaga();
        this.patioId = zona.getPatio().getId();
        this.patioNome = zona.getPatio().getNome();
    }

}

