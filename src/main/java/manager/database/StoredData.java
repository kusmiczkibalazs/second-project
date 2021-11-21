package manager.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoredData {
    String username;
    String appId;
    String appUser;
    String appPassword;
}
