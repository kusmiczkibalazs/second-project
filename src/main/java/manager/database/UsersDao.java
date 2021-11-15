package manager.database;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterBeanMapper(User.class)
public interface UsersDao {
    @SqlUpdate("""
        CREATE TABLE IF NOT EXISTS USERS (
            id IDENTITY PRIMARY KEY,
            username VARCHAR NOT NULL UNIQUE,
            password VARCHAR NOT NULL
        )
        """
    )
    void createTable();
    @SqlUpdate("INSERT INTO USERS (username, password) VALUES (:username, :password)")
    @GetGeneratedKeys
    long insertUser(@BindBean User user);

    @SqlQuery("SELECT username FROM users")
    List<String> getUsernames();

    @SqlQuery("SELECT password FROM users WHERE username = :username")
    String getPassword(@Bind("username") String username);
}
