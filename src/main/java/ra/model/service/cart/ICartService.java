package ra.model.service.cart;

import ra.model.entity.Cart;
import ra.model.entity.CartItem;
import ra.model.service.IService;

import java.util.List;

public interface ICartService extends IService<CartItem, Integer> {
    boolean clearCart(int CartId);
    List<CartItem> FindAllByCartId(int CartId);
    CartItem findCartItemByID(int cartId , int id);
    Cart findCartByUserId(int userId);

}
