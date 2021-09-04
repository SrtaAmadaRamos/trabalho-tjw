package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import model.ItemCarrinho;
import model.Item;
import util.StringUtils;

@ManagedBean
@SessionScoped
public class CarrinhoBean extends BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<ItemCarrinho> carrinho;	
	
	@ManagedProperty("#{sessaoBean}")
	private SessaoBean sessaoBean;
	
	@PostConstruct
	private void inicializar() {	
		carrinho = new ArrayList<ItemCarrinho>();		
	}
		
	public void limparCarrinho() {
		carrinho = new ArrayList<ItemCarrinho>();		
	}
		
	public void setSessaoBean(SessaoBean sessaoBean) {
		this.sessaoBean = sessaoBean;
	}	
	
	public int contarItens() {
		return carrinho.size();
	}

	public List<ItemCarrinho> getCarrinho() {
		return carrinho;
	}
	
	public String getConverteStringPrecoTotal() {
		Double total = 0d; 
		
		for(ItemCarrinho ci : carrinho) {
			total += ci.getPrecoTotal();
		}
		
		return StringUtils.converterParaDinheiro(total);		
	}
	
	public void adicionarItemCarrinho(Item item) {
		if(!sessaoBean.logado()) {
			redirect("/login.jsf");
			return;
		}
				
		if(VerificarItemCar(item, carrinho)) {
            AtualizarQuantidade(item, 1, carrinho);
            addMessage("Carrinho atualizado");
            return;
        }
		
		ItemCarrinho carrinhoItem = new ItemCarrinho();
		carrinhoItem.setItem(item);
		carrinhoItem.setQuantidade(1);
        carrinho.add(carrinhoItem);
        
        addMessage("Item adicionado ao carrinho");
        redirect("/index.jsf");
	}
	
	private boolean VerificarItemCar(Item item, List<ItemCarrinho> carrinho) {
        for(ItemCarrinho carrinhoItem : carrinho) {
            if(carrinhoItem.getItemId() == item.getId())
                return true;
        }

        return false;
    }

    private ItemCarrinho GetItemCarrinho(Item item, List<ItemCarrinho> carrinho) {
        for(ItemCarrinho carrinhoItem : carrinho) {
            if(carrinhoItem.getItemId() == item.getId())
                return carrinhoItem;
        }

        return null;
    }

    private void AtualizarQuantidade(Item item, int quantidade, List<ItemCarrinho> carrinho) {
    	ItemCarrinho carrinhoItem = GetItemCarrinho(item, carrinho);

    	carrinhoItem.setQuantidade(carrinhoItem.getQuantidade() + quantidade);
    }
}
