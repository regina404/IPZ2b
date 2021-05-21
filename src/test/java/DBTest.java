import com.myproject.DAO.CompanyDAO;
import com.myproject.DAO.UserDAO;
import com.myproject.Units.User;
import com.myproject.WebConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Serzhan Daniil
 * @version 12.0.2
 * Class-test
 */

public class DBTest {

    /**
     * method to init
     */
    @BeforeEach
    void init() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setContinueOnError(true);
        databasePopulator.addScript(new ClassPathResource("data.sql"));
        DatabasePopulatorUtils.execute(databasePopulator, WebConfig.posgresqlDataSource());
    }

    /**
     * method to test empty db
     */
    @Test
    void testEmptyUser() {
        UserDAO userDAO = new UserDAO();
        Assertions.assertEquals(0, userDAO.getAll().size());
    }

    /**
     * method to test empty db
     */
    @Test
    void testEmptyCompany() {
        CompanyDAO companyDAO = new CompanyDAO();
        Assertions.assertEquals(0, companyDAO.getAll().size());
    }

    /**
     * method to test user Dan
     */
    @Test
    void testAddDan() {
        UserDAO userDAO = new UserDAO();
        userDAO.create(new User(1, "Dan", "pass"));
        List<User> users = userDAO.getAll();
        List<User> actual = new ArrayList<>();
        actual.add(new User(1, "Dan", "pass"));
        assertEquals(users, actual);
    }


}
