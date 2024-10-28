package dao;

import java.util.List;
import model.OrderDetail;

public interface OrderDetailDAO {
    void save(OrderDetail orderDetail);            
    List<OrderDetail> show();                      
    void delete(String id);                        
    void update(OrderDetail orderDetail);          
}
