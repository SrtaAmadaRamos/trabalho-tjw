package dao;

import java.util.List;

import javax.persistence.TypedQuery;

import model.Usuario;

public class UsuarioDao extends GenericDao<Usuario> {
	
	public UsuarioDao() {
		super(Usuario.class);
	}
	
	public boolean VerifyIfUsedEmail(String email) {
        TypedQuery<Usuario> query = manager.createQuery("SELECT u FROM Usuario u WHERE u.email = ?1", Usuario.class);
        query.setParameter(1, email);
        List<Usuario> users = query.getResultList();

        return users.size() > 0;
    }
	
	public Usuario ProcurarEmailSenha(String email, String senha) {
        TypedQuery<Usuario> query = manager.createQuery("SELECT u FROM Usuario u WHERE u.email = ?1 AND u.senha = ?2", Usuario.class);
        query.setParameter(1, email);
        query.setParameter(2, senha);

        List<Usuario> users = query.getResultList();
        
        if(users.size() == 0)
            return null;
        return users.get(0);
    }
}
