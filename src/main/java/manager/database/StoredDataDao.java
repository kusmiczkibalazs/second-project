package manager.database;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterBeanMapper(StoredData.class)
public interface StoredDataDao {
    @SqlUpdate("""
        CREATE TABLE IF NOT EXISTS STORED_DATA (
            id IDENTITY PRIMARY KEY,
            username VARCHAR NOT NULL,
            appId VARCHAR NOT NULL,
            appUser VARCHAR NOT NULL,
            appPassword VARCHAR NOT NULL 
        )
        """
    )
    void createTable();

    @SqlUpdate("INSERT INTO STORED_DATA (username, appId, appUser, appPassword) VALUES (:username, :appId, :appUser, :appPassword)")
    @GetGeneratedKeys
    long insertUser(@BindBean StoredData storedData);

    @SqlQuery("SELECT appId FROM STORED_DATA WHERE username = :username")
    List<String> getCurrentUserData(@Bind("username") String username);

    @SqlQuery("SELECT * FROM STORED_DATA WHERE username = :username AND appId = :appId")
    List<StoredData> getChosenRecord(@Bind("username") String username, @Bind("appId") String appId);

    @SqlUpdate("UPDATE STORED_DATA " +
            "SET appUser = :appUser," +
            "appPassword = :appPassword " +
            "where username = :username AND appId = :appId")
    void updateUsernameAndPassword(@BindBean StoredData storedData);
}
