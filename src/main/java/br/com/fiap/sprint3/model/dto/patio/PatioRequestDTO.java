package br.com.fiap.sprint3.model.dto.patio;

import br.com.fiap.sprint3.model.entity.Patio;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatioRequestDTO { //usado no POST e PUT

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotBlank(message = "O endereço é obrigatório")
    @Size(min = 5, max = 255, message = "O endereço deve ter entre 5 e 255 caracteres")
    private String endereco;
    
    public PatioRequestDTO(Patio patio) {
    	this.nome = patio.getNome();
    	this.endereco = patio.getEndereco();
    }
}

