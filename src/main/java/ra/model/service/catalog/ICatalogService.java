package ra.model.service.catalog;

import ra.model.entity.Catalog;
import ra.model.service.IService;

import java.util.List;

public interface ICatalogService extends IService<Catalog,Integer> {
    List<Catalog> findByName(String name);
}
