package ra.model.service.cart;

import ra.model.entity.Cart;
import ra.model.entity.CartItem;
import ra.model.util.ConnectionToDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartServiceIMPL implements ICartService {
    @Override
    public List<CartItem> FindAllByCartId(int CartId) {
        List<CartItem> listCart = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectionToDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{call PROC_FindListOrderDetail(?)}");
            callSt.setInt(1, CartId);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                CartItem cartItem = new CartItem();
                cartItem.setId(rs.getInt("id"));
                cartItem.setOrderId(rs.getInt("order_id"));
                cartItem.setProductId(rs.getInt("product_id"));
                cartItem.setPrice(rs.getDouble("product_price"));
                cartItem.setQuantity(rs.getInt("quantity"));
                cartItem.setName(rs.getString("name"));
                cartItem.setImageURL(rs.getString("thumbnail"));
                listCart.add(cartItem);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionToDB.close(connection);
        }
        return listCart;
    }

    @Override
    public List<CartItem> findAll() {
        return null;
    }

    @Override
    public boolean save(CartItem cartItem) {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call  PROC_CreateOrderDetail(?,?,?,?)}");
            callSt.setInt(1, cartItem.getOrderId());
            callSt.setInt(2, cartItem.getProductId());
            callSt.setDouble(3, cartItem.getPrice());
            callSt.setInt(4, cartItem.getQuantity());
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionToDB.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public boolean update(CartItem cartItem) {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_ChangeQuantity(?,?)}");
            callSt.setInt(1,cartItem.getId());
            callSt.setInt(2, cartItem.getQuantity());
            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                ConnectionToDB.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    @Override
    public CartItem findCartItemByID(int cartId, int id) {
        List<CartItem> lists = FindAllByCartId(cartId);
        for (CartItem item : lists) {
            if (item.getProductId() == id) {
                return item;
            }
        }
        return null;
    }

    @Override
    public CartItem findById(Integer cartItemId) {
        return null;
    }

    @Override
    public boolean delete(Integer idDel) {
        Connection connection = null;
        try {
            connection = ConnectionToDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{call  PROC_DeleteOrderDetail(?)}");
            callSt.setInt(1, idDel);
            callSt.executeQuery();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionToDB.close(connection);
        }
        return false;
    }

    @Override
    public Cart findCartByUserId(int userId) {
        Connection conn = null;
        Cart cart = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_FindOrderByUserId(?)}");
            callSt.setInt(1, userId);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                int orderId = rs.getInt(1);
                int user_id = rs.getInt(2);
                float total = rs.getFloat(3);
                String createdDate = rs.getString(4);
                boolean type = rs.getBoolean(5);
                boolean status = rs.getBoolean(6);
                String phone = rs.getString(7);
                String address = rs.getString(8);
                cart = new Cart(orderId, user_id, total, createdDate, type, status, phone, address);
            }

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            ConnectionToDB.close(conn);
        }
        return cart;
    }

    @Override
    public boolean clearCart(int CartId) {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call  PROC_DeleteProduct(?)}");
            callSt.setInt(1, CartId);
            callSt.executeQuery();
            return true;
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            ConnectionToDB.close(conn);
        }
        return false;
    }
}
