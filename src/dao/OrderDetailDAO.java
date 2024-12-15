package dao;

import java.util.List;
import model.OrderDetail;

public interface OrderDetailDAO {
    void save(OrderDetail orderDetail);            
    public List<OrderDetail> show(String id_order);                      
    public void delete(String id);                        
    public void update(OrderDetail orderDetail);
    public String total(String id_order);
}
