package java_course_04;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class java_course_04 {
    // H2 Database URL 格式: jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1
    private static final String URL = "jdbc:h2:tcp://localhost/~/Downloads/h2-2024-08-11/h2/bin/java-course";
    private static final String USER = "sa";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // 載入 H2 JDBC 驅動
            Class.forName("org.h2.Driver");

            // 建立與 H2 數據庫的連接
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            statement = connection.createStatement();

            // 創建表格
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                                     "id INT AUTO_INCREMENT PRIMARY KEY, " +
                                     "name VARCHAR(255), " +
                                     "email VARCHAR(255))";
            statement.execute(createTableSQL);
            System.out.println("連接成功!");
            // 插入數據
            String insertSQL = "INSERT INTO users (name, email) VALUES ('Alice', 'alice@example.com')," +
                                                        "('Bob', 'bob@example.com'),"+"('Ben', 'ben@example.com')," + "('Amy', 'amy@example.com')";
            statement.executeUpdate(insertSQL);
            
            // 查詢數據
            String selectSQL = "SELECT * FROM users";
            resultSet = statement.executeQuery(selectSQL);

            // 處理查詢結果
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
            }
            System.out.println("資料插入成功!");
        } catch (ClassNotFoundException e) {
            System.out.println("H2 Driver class not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQL error.");
            e.printStackTrace();
        } finally {
            // 關閉資源
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
