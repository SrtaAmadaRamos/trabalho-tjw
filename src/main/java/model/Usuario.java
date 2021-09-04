package model;


import javax.persistence.*;

import com.sun.istack.NotNull;

@Entity
@Table (name = "usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String nome;

    @NotNull
    @Column(unique = true)
    private String email;

    private String senha;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public Boolean ValidoParaInserir() {
    	return nome.length() > 0
    			&& email.length() > 0
    			&& senha.length() > 0;
    }
}
