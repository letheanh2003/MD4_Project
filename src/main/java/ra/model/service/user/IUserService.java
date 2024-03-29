package ra.model.service.user;

import ra.model.entity.User;
import ra.model.entity.UserLogin;
import ra.model.service.IService;

public interface IUserService extends IService<User, Integer> {
    UserLogin login(User user);
    boolean checkExistsUsername(String username);
    boolean updateStatusUser (Integer id,Integer status);
}
