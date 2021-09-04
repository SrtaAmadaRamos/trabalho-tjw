package controller;

import java.io.Serializable;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.ItemDao;
import model.Item;

@ManagedBean
@ViewScoped
public class ItemBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private ItemDao itemDao;
	
	private Item item;
	private Collection<Item> itens;
	
	private String modo = "add";
	
	@ManagedProperty("#{sessaoBean}")
	private SessaoBean sessaoBean;
	
	@PostConstruct
	private void inicializar() {
		
		item = new Item();
		itemDao = new ItemDao();
		
		if(!sessaoBean.logado()) {
			redirect("/login.jsf");
		}

		FacesContext facesContext = FacesContext.getCurrentInstance();
		String editar = facesContext.getExternalContext().getRequestParameterMap().get("editar");
		
		if(editar != null) {
			modo = "editar";
			
			item = itemDao.getById(Integer.parseInt(editar));
		}
	
		itens = getItens();
		 	
	}
	
	public void setSessaoBean(SessaoBean sessaoBean) {
		this.sessaoBean = sessaoBean;
	}	

	public ItemDao getItemDao() {
		return itemDao;
	}

	public void setItemDao(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Collection<Item> getItens() {
		itens = itemDao.findAll();
		
		return itens;
	}

	public void setItens(Collection<Item> itens) {
		this.itens = itens;
	}	
	
	public void redirecionaPaginaEditar(int id) {		
		redirect("/itens/adicionar.jsf?editar=" + id);
	}
		
	public Boolean modoEditar() {
		return modo.equals("editar");
	}
	
	public void salvarItem() {
		if(!item.validar()) {
			addMessage("Formulário inválido.");
			return;
		}
		
		if(modoEditar()) {
			itemDao.update(item);
		} else {
			itemDao.insert(item);
		}
		
		redirect("/itens/index.jsf");
	}
	
	public String getPaginaHeader() {
		return modoEditar() ? "Editar Item" : "Novo Item";
	}
}
