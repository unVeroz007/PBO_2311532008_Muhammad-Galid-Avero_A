package dao;
import java.util.List;

import model.Order;

public interface OrderDAO {
	void save(Order cs);
	public abstract List<Order> show();
	public void delete(Order id);
	public void update(Order cs);
	void delete(String id);
}
