package br.com.fiap.sprint3.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_SENSOR")
@Getter
@Setter 
@NoArgsConstructor 
@AllArgsConstructor
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sensor")
    private Integer id;

    @Column(name = "localizacao_sensor", nullable = false, length = 50)
    private String localizacao;

    @Column(name = "data_sensor", nullable = false)
    private LocalDate data;

    @Column(name = "hora_sensor", nullable = false)
    private LocalTime hora;
    
    @OneToMany(mappedBy = "sensor", fetch = FetchType.LAZY)
	private List<Historico> historicos;
}
