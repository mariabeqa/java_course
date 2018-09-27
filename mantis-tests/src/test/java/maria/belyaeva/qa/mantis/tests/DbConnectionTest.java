package maria.belyaeva.qa.mantis.tests;


import maria.belyaeva.qa.model.UserData;
import maria.belyaeva.qa.model.Users;
import org.testng.annotations.Test;

import java.sql.*;

public class DbConnectionTest {

    @Test
    public void testDbConnection() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?" +
                            "user=root&password=");

            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select id, username, email, enabled =1 from mantis_user_table");
            Users users = new Users();
            while ((resultSet.next())) {
                users.add(new UserData().withId(resultSet.getInt("id")).withUsername(resultSet.getString("username"))
                        .withEmail(resultSet.getString("email")));
            }
            resultSet.close();
            statement.close();
            conn.close();
            System.out.println(users);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
