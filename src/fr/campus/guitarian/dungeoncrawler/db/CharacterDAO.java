package fr.campus.guitarian.dungeoncrawler.db;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.sql.*;

public class CharacterDAO {
    private Connection connection;

    private CharacterRow characterRow;

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

    public List<CharacterRow> getCharactersDAO() throws SQLException {
        List<CharacterRow> characters = new ArrayList<>();
        try{
            Statement stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Characters");
            while (rs.next()) {
                String name = rs.getString("Name");
                int id = rs.getInt("Id");
                int hp = rs.getInt("LifePoints");
                int ap = rs.getInt("Strength");
                String typeLabel = rs.getString("Type").toLowerCase();
                String offEquipLbl = rs.getString("OffensiveEquipment");
                String defEquipLbl = rs.getString("DefensiveEquipment");
                characters.add(new CharacterRow(id, typeLabel, name, hp, ap, offEquipLbl, defEquipLbl));
            }
            return  characters;
        } catch (SQLException e) {
            //throw new RuntimeException(e);
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }
    }

    public int setCharactersDAO(CharacterRow characterRow) throws SQLException {
        int newId;
        PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO Characters (Type, Name, LifePoints, Strength, OffensiveEquipment, DefensiveEquipment) " +
                        "VALUES (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS   // <-- indispensable pour pouvoir récupérer l'Id ensuite
        );

        stmt.setString(1, characterRow.getType());
        stmt.setString(2, characterRow.getName());
        stmt.setInt(3, characterRow.getLifePoints());
        stmt.setInt(4, characterRow.getAttackPoints());
        stmt.setString(5, characterRow.getOffensiveEquipment());
        stmt.setString(6, characterRow.getDefensiveEquipment());

        stmt.executeUpdate();

        ResultSet generatedKeys = stmt.getGeneratedKeys();
        generatedKeys.next();
        newId = generatedKeys.getInt(1);

        return newId;
    }

    public void editCharactersDAO(CharacterRow characterRow) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "UPDATE Characters SET Name=?, LifePoints=?, Strength=?, OffensiveEquipment=?, DefensiveEquipment=? WHERE Id=?");

        stmt.setString(1, characterRow.getName());
        stmt.setInt(2, characterRow.getLifePoints());
        stmt.setInt(3, characterRow.getAttackPoints());
        stmt.setString(4, characterRow.getOffensiveEquipment());
        stmt.setString(5, characterRow.getDefensiveEquipment());
        stmt.setInt(6, characterRow.getId());

        stmt.executeUpdate();
    }

    public void editLifePointsDAO(int hp, int id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "UPDATE Characters SET LifePoints=? WHERE Id=?");

        stmt.setInt(1, hp);
        stmt.setInt(2, id);

        stmt.executeUpdate();
    }

}
