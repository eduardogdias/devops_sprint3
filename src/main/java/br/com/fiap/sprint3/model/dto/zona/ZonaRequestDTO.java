package br.com.fiap.sprint3.model.dto.zona;

import br.com.fiap.sprint3.model.entity.Zona;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZonaRequestDTO {

    @NotBlank(message = "O tipo da zona é obrigatório")
    @Size(min = 3, max = 50, message = "O tipo deve ter entre 3 e 50 caracteres")
    private String tipo;

    @NotNull(message = "A quantidade de vagas é obrigatória")
    @Min(value = 1, message = "A quantidade mínima de vagas deve ser 1")
    private Integer qtdVaga;

    @NotNull(message = "O ID do pátio é obrigatório")
    private Integer patioId;

	public ZonaRequestDTO(Zona zona) {
		this.tipo = zona.getTipo();
		this.qtdVaga = zona.getQtdVaga();
		this.patioId = zona.getPatio().getId();
	}

    
}
