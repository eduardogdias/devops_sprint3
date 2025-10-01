package br.com.fiap.sprint3.model.dto.moto;

import br.com.fiap.sprint3.model.entity.Moto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MotoResponseDTO {

    private Integer id;
    private String placa;
    private String chassi;
    private String marca;
    private String modelo;

    public MotoResponseDTO(Moto moto) {
        this.id = moto.getId();
        this.placa = moto.getPlaca();
        this.chassi = moto.getChassi();
        this.marca = moto.getMarca();
        this.modelo = moto.getModelo();
    }

    
}
