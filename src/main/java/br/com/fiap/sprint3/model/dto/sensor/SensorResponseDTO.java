package br.com.fiap.sprint3.model.dto.sensor;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.fiap.sprint3.model.entity.Sensor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SensorResponseDTO {

    private Integer id;
    private String localizacao;
    private LocalDate data;
    private LocalTime hora;

    public SensorResponseDTO(Sensor sensor) {
        this.id = sensor.getId();
        this.localizacao = sensor.getLocalizacao();
        this.data = sensor.getData();
        this.hora = sensor.getHora();
    }

}