package query.execute;

import dao.User;
import query.LambdaQuery;
import query.TableExecute;
import utils.BeanUtils;

import java.util.List;


public class UserTableExecute extends TableExecute<User> {
    
    public static UserTableExecute getInstance() {
        return BeanUtils.getSingleTon(UserTableExecute.class, obj -> new UserTableExecute());
    }
    
    public static User getUser(User user) {
        LambdaQuery<User> query = new LambdaQuery<>();
        query.select(User::getUserId, User::getUsername, User::getPassword).eq(User::getUsername, user.getUsername())
                .and().eq(User::getPassword, user.getPassword());
        UserTableExecute execute = new UserTableExecute();
        return getInstance().selectOne(query);
    }
    
    public static List<User> getAllUsers() {
        LambdaQuery<User> query = new LambdaQuery<>();
        query.select(User::getUserId, User::getUsername, User::getPassword);
        UserTableExecute execute = new UserTableExecute();
        return getInstance().selectList(query);
    }
    
    public static Integer deleteUserById(Integer id) {
        LambdaQuery<User> query = new LambdaQuery<>();
        query.eq(User::getUserId, id);
        UserTableExecute execute = new UserTableExecute();
        return getInstance().delete(query);
    }
    
    public static Integer updateUser(User user) {
        LambdaQuery<User> query = new LambdaQuery<>();
        query.eq(User::getUserId, user.getUserId()).set(User::getUsername, user.getUsername())
                .set(User::getPassword, user.getPassword());
        UserTableExecute execute = new UserTableExecute();
        return getInstance().update(query);
    }
    
    
    public static Boolean insertUser(User user) {
        return getInstance().save(user);
    }
    
    
}
