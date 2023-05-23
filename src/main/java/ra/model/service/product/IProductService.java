package ra.model.service.product;

import ra.model.entity.Product;
import ra.model.service.IService;

import java.util.List;

public interface IProductService extends IService<Product,Integer> {
    List<Product> findByName(String name);
    List<Product> searchByCatalog(String name);

}

