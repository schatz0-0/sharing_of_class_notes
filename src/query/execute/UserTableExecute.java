package query.execute;

import dao.Note;
import dao.User;
import query.LambdaQuery;
import query.TableExecute;

import java.util.List;


public class UserTableExecute extends TableExecute<User> {

    public static User getUser(User user) {
        LambdaQuery<User> query = new LambdaQuery<>();
        query.select(User::getUserId, User::getUsername, User::getPassword)
                .eq(User::getUsername, user.getUsername()).and()
                .eq(User::getPassword, user.getPassword());
        UserTableExecute execute = new UserTableExecute();
        return execute.selectOne(query);
    }

}
