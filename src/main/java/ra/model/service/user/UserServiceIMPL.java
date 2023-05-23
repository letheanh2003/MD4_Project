package ra.model.service.user;

import ra.model.entity.User;
import ra.model.entity.UserLogin;
import ra.model.util.ConnectionToDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserServiceIMPL implements IUserService {
    @Override
    public List<User> findAll() {
        List<User> list_users = new ArrayList<>();
        Connection conn= null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_GETALL_USER()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPhone_number(rs.getString("phone_number"));
                user.setAddress(rs.getString("address"));
                user.setPassword(rs.getString("password"));
                user.setRole(rs.getInt("role"));
                user.setStatus(rs.getInt("status"));
                list_users.add(user);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            ConnectionToDB.close(conn);
        }
        return list_users;
    }

    @Override
    public boolean save(User user) {
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_REGISTER(?,?,?)}");
            callSt.setString(1, user.getUsername());
            callSt.setString(2, user.getEmail());
            callSt.setString(3, user.getPassword());
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
    public boolean update(User user) {
        return false;
    }

    @Override
    public User findById(Integer integer) {
        return null;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public UserLogin login(User user) {
        UserLogin userLogin = null;
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_LOGIN(?,?)}");
            callSt.setString(1, user.getUsername());
            callSt.setString(2, user.getPassword());
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                userLogin = new UserLogin();
                userLogin.setId(rs.getInt("id"));
                userLogin.setUsername(rs.getString("username"));
                userLogin.setEmail(rs.getString("email"));
                userLogin.setPhone_number(rs.getString("phone_number"));
                userLogin.setAddress(rs.getString("address"));
                userLogin.setPassword(rs.getString("password"));
                userLogin.setRole(rs.getInt("role"));
                userLogin.setCartId(rs.getInt("orderId"));
                userLogin.setStatus(rs.getBoolean("status"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionToDB.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return userLogin;
    }

    @Override
    public boolean checkExistsUsername(String username) {

        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call PROC_FINDBYUSERNAME(?)}");
            callSt.setString(1, username);

            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ConnectionToDB.close(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean updateStatusUser(Integer id, Integer status) {
        boolean st = (status == 1) ? true : false;
        Connection conn = null;
        try {
            conn = ConnectionToDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call UpdateAccountStatus(?,?)}");
            callSt.setBoolean(1, st);
            callSt.setInt(2, id);
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
}
