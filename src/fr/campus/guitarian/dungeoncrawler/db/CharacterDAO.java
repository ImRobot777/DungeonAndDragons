package fr.campus.guitarian.dungeoncrawler.db;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.sql.*;

public class CharacterDAO {
    private Connection connection;

    public CharacterDAO() throws SQLException, IOException {
        Properties dbProperties = new Properties();
        try (FileInputStream fis = new FileInputStream("db.properties")) {
            dbProperties.load(fis);
        }

        String url = dbProperties.getProperty("db.url");
        String user = dbProperties.getProperty("db.user");
        String password = dbProperties.getProperty("db.password");
        this.connection = DriverManager.getConnection(url, user, password);
    }

    public void testConnection() throws SQLException {
        try{
            Statement stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Characters");
            while (rs.next()) {
                System.out.print(rs.getString("Name") + " / " + rs.getInt("LifePoints"));
            }
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }
    }



}
