package model;

//import br.edu.ifce.utils.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import util.StringUtils;

@Entity
@Table(name = "itens")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 100)
	private String nome;

	@Column(length = 255)
	private String descricao;

	private String imagem;

	@Column
	private Double preco;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(@NotNull String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(@NotNull String descricao) {
		this.descricao = descricao;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(@NotNull String imagem) {
		this.imagem = imagem;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(@NotNull Double preco) {
		this.preco = preco;
	}

	public String getConverteStringPreco() {
		return StringUtils.converterParaDinheiro(preco);
	}
	
	public Boolean validar() {
		return nome.length() > 0 && nome.length() <= 100
				&& descricao.length() > 0 && descricao.length() <= 255
				&& preco != null && preco > 0
				&& imagem.length() > 0;
	}
}
