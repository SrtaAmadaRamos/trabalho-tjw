package model;

import util.StringUtils;

//import br.edu.ifce.utils.StringUtils;

public class ItemCarrinho {
    private Item item;
    private int itemId;
    private int quantidade;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.itemId = item.getId();
        this.item = item;
    }

    public int getItemId() {
        return itemId;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantity) {
        this.quantidade = quantity;
    }

    public double getPrecoTotal()
    {
        return item.getPreco() * quantidade;
    }
    public String getConverteStringPreco()
    {
        return StringUtils.converterParaDinheiro(getPrecoTotal());
    }
}
