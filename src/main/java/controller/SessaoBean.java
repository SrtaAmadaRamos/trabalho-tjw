package controller;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.UsuarioDao;
import model.Usuario;

@ManagedBean
@SessionScoped
public class SessaoBean extends BaseBean {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuarioLogado;
	private Usuario usuario;
	private UsuarioDao usuarioDao;
	
	@PostConstruct
	private void inicializar() {		
		usuario = new Usuario();		
		usuarioDao  = new UsuarioDao();				
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUser(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioDao getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	
	public Boolean logado() {
		return usuarioLogado != null;
	}
	
	public Usuario getusuarioLogado() {
		return usuarioLogado;
	}
	
	public void sair() {
		usuarioLogado = null;		
		redirect("/login.jsf");
	}
	
	public void registrar() {		
		if(!usuario.ValidoParaInserir()) {
			addMessage("Dados inválidos!");	
			return;
		}
		
		if(usuarioDao.VerifyIfUsedEmail(usuario.getEmail())) {
			addMessage("Email já em uso!");	
			return;			
		}

		try {
			usuarioDao.insert(usuario);
		} catch(Exception e) {
			usuario = new Usuario();
			addMessage("Ops, ocorreu um erro ao registrar!");	
			return;
		}
		
		usuarioLogado = usuario;
		usuario = new Usuario();
				
		redirect("/itens/index.jsf");
	}
	
	public void login() {		
		Usuario u = usuarioDao.ProcurarEmailSenha(usuario.getEmail(), usuario.getSenha());		
		usuario = new Usuario();
		
		if(u == null) {
			addMessage("Email ou Senha inválidos");			
			return;
		}
		
		usuarioLogado = u;		
		
		redirect("/itens/index.jsf");
	}    
}
