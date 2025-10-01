package br.com.fiap.sprint3.model.dto.moto;

import br.com.fiap.sprint3.model.entity.Moto;
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
public class MotoRequestDTO {

    @Size(min = 7, max = 7, message = "A placa deve ter exatamente 7 caracteres")
    private String placa;

    @Size(min = 17, max = 17, message = "O chassi deve ter exatamente 17 caracteres")
    private String chassi;

    @NotBlank(message = "O modelo da moto é obrigatório")
    private String modelo;

    public MotoRequestDTO(Moto moto) {
    	this.placa = moto.getPlaca();
    	this.chassi = moto.getChassi();
    	this.modelo = moto.getModelo();
    }
}


