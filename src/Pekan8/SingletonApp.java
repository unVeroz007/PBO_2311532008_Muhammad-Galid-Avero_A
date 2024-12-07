package Pekan8;
import model.Order;
import model.OrderDetail;
public class SingletonApp {
	public static void main(String[] args) {
		Order order = new Order();
		order.save("001");
		OrderDetail orderdetail = new OrderDetail();
		orderdetail.save("001", "Buku A");
		orderdetail.save("001", "Buku B");
		orderdetail.save("001", "Buku C");
		
	}
}