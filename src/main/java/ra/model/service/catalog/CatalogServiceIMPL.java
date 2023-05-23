package ra.model.service.catalog;

import ra.model.entity.Catalog;
import ra.model.util.ConnectionToDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CatalogServiceIMPL implements ICatalogService{
    @Override
    public List<Catalog> findAll() {
        List<Catalog> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_GetAllCatalog()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Catalog catalog = new Catalog();
                catalog.setId(rs.getInt(1));
                catalog.setName(rs.getString(2));
                list.add(catalog);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionToDB.close(conn);
        }
        return list;
    }

    @Override
    public boolean save(Catalog catalog)  {
        Connection conn = null;
        try {
        conn = ConnectionToDB.getConnection();
        CallableStatement callSt = conn.prepareCall("{call PROC_InsertNewCatalog(?)}");
        callSt.setString(1, catalog.getName());
        callSt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            ConnectionToDB.close(conn);
        }
        return true;
    }

    @Override
    public boolean update(Catalog catalog){
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_UpdateCatalog(?,?)}");
            callSt.setInt(1,catalog.getId());
            callSt.setString(2, catalog.getName());
            callSt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            ConnectionToDB.close(conn);
        }
        return true;
    }

    @Override
    public Catalog findById(Integer id) {
        Connection conn = null;
        Catalog catalog = new Catalog();
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_FindCatalogById(?)}");
            callSt.setInt(1,id);
            ResultSet rs =callSt.executeQuery();
            while (rs.next()) {
                catalog.setId(rs.getInt("id"));
                catalog.setName(rs.getString("name"));
            }
        }catch (Exception e) {
            e.printStackTrace();

        }finally {
            ConnectionToDB.close(conn);
        }
        return catalog;
    }

    @Override
    public boolean delete(Integer id) {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_DeleteCatalog(?)}");
            callSt.setInt(1,id);
            callSt.executeUpdate();
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }finally {
            ConnectionToDB.close(conn);
        }
        return true;
    }


    @Override
    public List<Catalog> findByName(String name) {
        List<Catalog> list = new ArrayList<>();
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_FindByName(?)}");
            callSt.setString(1,name);
            ResultSet rs= callSt.executeQuery();
            while (rs.next()) {
                Catalog catalog = new Catalog();
                catalog.setId(rs.getInt("id"));
                catalog.setName(rs.getString("name"));
                catalog.setUrl(rs.getString("url"));
                list.add(catalog);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionToDB.close(conn);
        }
        return list;
    }
}
