package br.com.fiap.sprint3.model.entity;

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
@Table(name = "TB_MOTO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Moto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_moto")
	private Integer id;
	
	@Column(name = "placa_moto", length = 7)
	private String placa; //7 caracteres
	
	@Column(name = "chassi_moto", length = 17)
	private String chassi;  //17 caracteres
	
	@Column(name = "marca_moto")
	private String marca;
	
	@Column(name = "modelo_moto", nullable = false, length = 20)
	private String modelo;
	
	@OneToMany(mappedBy = "moto", fetch = FetchType.LAZY)
	private List<Historico> historicos;


}
