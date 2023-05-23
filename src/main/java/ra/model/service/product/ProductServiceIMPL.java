package ra.model.service.product;

import ra.model.entity.Product;
import ra.model.service.catalog.CatalogServiceIMPL;
import ra.model.util.ConnectionToDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceIMPL implements IProductService {
    @Override
    public List<Product> findAll() {
        CatalogServiceIMPL catalogServiceIMPL = new CatalogServiceIMPL();
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectionToDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{call PROC_GetAllProduct()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setTitle(rs.getString("title"));
                product.setQuantity(rs.getInt("quantity"));
                product.setPrice(rs.getFloat("price"));
                product.setThumbnailUrl(rs.getString("thumbnail"));
                product.setCatalogId(catalogServiceIMPL.findById(rs.getInt("catalogId")));
                products.add(product);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionToDB.close(connection);
        }
        return products;
    }

    @Override
    public boolean save(Product product) {
        Connection connection = null;
        try {
            connection = ConnectionToDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{call PROC_InsertNewProduct(?,?,?,?,?,?)}");
            callSt.setString(1, product.getName());
            callSt.setString(2, product.getTitle());
            callSt.setInt(3, product.getQuantity());
            callSt.setFloat(4, product.getPrice());
            callSt.setString(5, product.getThumbnailUrl());
            callSt.setInt(6, product.getCatalogId().getId());

            callSt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionToDB.close(connection);
        }
        return true;
    }

    @Override
    public boolean update(Product product) {
        Connection connection = null;
        try {
            connection = ConnectionToDB.getConnection();

            CallableStatement callableStatement = connection.prepareCall("{call PROC_UpdateProduct(?,?,?,?,?,?,?)}");
            callableStatement.setInt(1, product.getId());
            callableStatement.setString(2, product.getName());
            callableStatement.setString(3, product.getTitle());
            callableStatement.setInt(4, product.getQuantity());
            callableStatement.setFloat(5, product.getPrice());
            callableStatement.setString(6, product.getThumbnailUrl());
            callableStatement.setInt(7,product.getCatalogId().getId());

            callableStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionToDB.close(connection);
        }
        return true;
    }

    @Override
    public Product findById(Integer id) {
        CatalogServiceIMPL catalogServiceIMPL = new CatalogServiceIMPL();
        Product product = new Product();
        Connection connection = null;
        try {
            connection = ConnectionToDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{call PROC_FindProductById(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setTitle(rs.getString("title"));
                product.setQuantity(rs.getInt("quantity"));
                product.setPrice(rs.getFloat("price"));
                product.setThumbnailUrl(rs.getString("thumbnail"));
                product.setCatalogId(catalogServiceIMPL.findById(rs.getInt("catalogId")));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionToDB.close(connection);
        }
        return product;
    }

    @Override
    public boolean delete(Integer id) {
        Connection connection = null;
        try {
            connection = ConnectionToDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{call PROC_DeleteProduct(?)}");
            callSt.setInt(1, id);
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
    public List<Product> findByName(String name) {
        CatalogServiceIMPL catalogServiceIMPL = new CatalogServiceIMPL();
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectionToDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{call PROC_FindByNameProduct(?)}");
            callSt.setString(1, name);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setTitle(rs.getString("title"));
                product.setQuantity(rs.getInt("quantity"));
                product.setPrice(rs.getFloat("price"));
                product.setThumbnailUrl(rs.getString("thumbnail"));
                product.setCatalogId(catalogServiceIMPL.findById(rs.getInt("catalogId")));
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            ConnectionToDB.close(connection);
        }
        return products;
    }


    @Override
    public List<Product> searchByCatalog(String name) {
        CatalogServiceIMPL catalogServiceIMPL = new CatalogServiceIMPL();
        List<Product> products = new ArrayList<>();
        Connection connection = null;
        try {
            connection = ConnectionToDB.getConnection();
            CallableStatement callSt = connection.prepareCall("{call PROC_FindByCatalog(?)}");
            callSt.setString(1, name);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setTitle(rs.getString("title"));
                product.setQuantity(rs.getInt("quantity"));
                product.setPrice(rs.getFloat("price"));
                product.setThumbnailUrl(rs.getString("thumbnail"));
                product.setCatalogId(catalogServiceIMPL.findById(rs.getInt("catalogId")));
                products.add(product);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionToDB.close(connection);
        }
        return products;
    }
}
