package dao;

import model.Item;

public class ItemDao extends GenericDao<Item> {
	public ItemDao() {
		super(Item.class);
	}
}
