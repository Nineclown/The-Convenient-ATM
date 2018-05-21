import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class AdminTest {

    @Test
    public void shouldCheckAdminAccount() {
        DataStore ds = new DataStore();
        ArrayList<Admin> adminList =  ds.loadAdminData();
    assertEquals(adminList.get(0).checkAdminAccount("1","1234"),true);
    assertEquals(adminList.get(0).checkAdminAccount("1","1444"),false);
    }
}
