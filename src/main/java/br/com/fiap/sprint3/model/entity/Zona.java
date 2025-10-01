package br.com.fiap.sprint3.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TB_ZONA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Zona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zona")
    private Integer id;

    @Column(name = "tipo_zona", nullable = false)
    private String tipo;

    @Column(name = "qtd_vaga_zona", nullable = false)
    private Integer qtdVaga;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_patio", nullable = false) // a zona deve um ter patio para ser instanciada
    @NotNull(message = "O pátio da zona é obrigatório")
    private Patio patio;
    
    @OneToMany(mappedBy = "zona", fetch = FetchType.LAZY)
	private List<Historico> historicos;

}