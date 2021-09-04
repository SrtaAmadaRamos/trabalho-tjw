package controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import model.ItemCarrinho;
import util.StringUtils;

@ManagedBean
@ViewScoped
public class FinalizarCompraBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{sessaoBean}")
	private SessaoBean sessaoBean;
	
	@ManagedProperty("#{carrinhoBean}")
	private CarrinhoBean carrinhoBean;
	
	private List<ItemCarrinho> carrinho;
	
	@PostConstruct
	private void inicializar() {	
		if(!sessaoBean.logado())
			redirect("/login.jsf");		
		
		carrinho = carrinhoBean.getCarrinho();
		carrinhoBean.limparCarrinho();
	}
	
	public void setSessaoBean(SessaoBean sessaoBean) {
		this.sessaoBean = sessaoBean;
	}	
	
	public void setCarrinhoBean(CarrinhoBean carrinhoBean) {
		this.carrinhoBean = carrinhoBean;
	}	
	
	public List<ItemCarrinho> getCarrinho() {
		return carrinho;
	}
	
	public String convertStringPrecoTotal() {
		Double total = 0d; 
		
		for(ItemCarrinho ci : carrinho) {
			total += ci.getPrecoTotal();
		}
		
		return StringUtils.converterParaDinheiro(total);		
	}	
}
