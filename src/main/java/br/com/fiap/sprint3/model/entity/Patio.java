package br.com.fiap.sprint3.model.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="TB_PATIO")
@Getter
@Setter
@NoArgsConstructor
public class Patio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_patio") 
	private Integer id;

	@Column(name = "nome_patio", nullable = false)
    private String nome;

	@Column(name = "endereco_patio", nullable = false)
    private String endereco;

    @OneToMany(mappedBy = "patio", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Zona> zonas;
	

	//contrutor para definir as tabelas do banco (sem coluna para as zonas)
	public Patio(Integer id, String nome, String endereco) {
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
	}
	
}
