package beligas.Koneksi;

import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Koneksi_beligasDB {
    private static Connection con;

    public static Connection getConnection() {
        if (con == null) {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setDatabaseName("beligas");
            dataSource.setUser("root");
            dataSource.setPassword("");

            try {
                con = dataSource.getConnection();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return con;
    }
}
