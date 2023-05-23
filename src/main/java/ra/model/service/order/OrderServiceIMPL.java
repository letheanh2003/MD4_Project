package ra.model.service.order;

import ra.model.entity.Order;
import ra.model.util.ConnectionToDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Types;

public class OrderServiceIMPL implements IOrderService {

    @Override
    public int createOrderNew(Order o) {
        int orderNewId = -1;
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_CREATEORDERNEW(?,?,?,?,?)}");
            callSt.setInt(1, o.getOrderId());
            callSt.setDouble(2,o.getTotal());
            callSt.setString(3, o.getPhone());
            callSt.setString(4, o.getAddress());
            callSt.registerOutParameter(5, Types.INTEGER);
            callSt.executeUpdate();
            orderNewId = callSt.getInt(5);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionToDB.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orderNewId;

    }
}
