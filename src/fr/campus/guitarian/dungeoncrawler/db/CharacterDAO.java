package fr.campus.guitarian.dungeoncrawler.db;

import fr.campus.guitarian.dungeoncrawler.characters.Character;
import fr.campus.guitarian.dungeoncrawler.characters.types.Warrior;
import fr.campus.guitarian.dungeoncrawler.characters.types.Wizard;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * The type Character dao.
 */
public class CharacterDAO implements AutoCloseable {

    private Connection connection;

    /**
     * Instantiates a new Character dao.
     *
     * @throws SQLException the sql exception
     * @throws IOException  the io exception
     */
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

    /**
     * Test connection.
     */
    public void testConnection() {
        try {
            Statement stmt = this.connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Characters");
            while (rs.next()) {
                System.out.println(rs.getString("Name") + " / " + rs.getInt("LifePoints"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private List<CharacterRow> getCharactersDAO() throws SQLException {
        List<CharacterRow> characters = new ArrayList<>();
        Statement stmt = this.connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Characters");
        while (rs.next()) {
            characters.add(new CharacterRow(
                    rs.getInt("Id"),
                    rs.getString("Type").toLowerCase(),
                    rs.getString("Name"),
                    rs.getInt("LifePoints"),
                    rs.getInt("Strength"),
                    rs.getString("OffensiveEquipment"),
                    rs.getString("DefensiveEquipment")
            ));
        }
        return characters;
    }

    private int setCharactersDAO(CharacterRow row) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO Characters (Type, Name, LifePoints, Strength, OffensiveEquipment, DefensiveEquipment) VALUES (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS
        );
        stmt.setString(1, row.getType());
        stmt.setString(2, row.getName());
        stmt.setInt(3, row.getLifePoints());
        stmt.setInt(4, row.getAttackPoints());
        stmt.setString(5, row.getOffensiveEquipment());
        stmt.setString(6, row.getDefensiveEquipment());
        stmt.executeUpdate();

        ResultSet generatedKeys = stmt.getGeneratedKeys();
        generatedKeys.next();
        return generatedKeys.getInt(1);
    }

    private void editCharactersDAO(CharacterRow row) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "UPDATE Characters SET Name=?, LifePoints=?, Strength=?, OffensiveEquipment=?, DefensiveEquipment=? WHERE Id=?"
        );
        stmt.setString(1, row.getName());
        stmt.setInt(2, row.getLifePoints());
        stmt.setInt(3, row.getAttackPoints());
        stmt.setString(4, row.getOffensiveEquipment());
        stmt.setString(5, row.getDefensiveEquipment());
        stmt.setInt(6, row.getId());
        stmt.executeUpdate();
    }

    private void editLifePointsDAO(int hp, int id) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(
                "UPDATE Characters SET LifePoints=? WHERE Id=?"
        );
        stmt.setInt(1, hp);
        stmt.setInt(2, id);
        stmt.executeUpdate();
    }

    // From Game.java : mapping CharacterRow <-> Character is now done here.

    /**
     * Gets heroes from db.
     *
     * @return the heroes from db
     * @throws SQLException the sql exception
     */
    public List<Character> getHeroesFromDB() throws SQLException {
        List<Character> heroes = new ArrayList<>();
        for (CharacterRow row : this.getCharactersDAO()) {
            Character character;
            if (row.getType().equals("warrior")) {
                character = new Warrior(row.getName());
            } else if (row.getType().equals("wizard")) {
                character = new Wizard(row.getName());
            } else {
                throw new SQLDataException("Data Base Characters Type ERROR");
            }
            character.setId(row.getId());
            character.setHealthPoint(row.getLifePoints());
            character.setAttackPoint(row.getAttackPoints());
            heroes.add(character);
        }
        return heroes;
    }

    /**
     * Create hero in db.
     *
     * @param c the c
     * @throws SQLException the sql exception
     */
    public void createHeroInDB(Character c) throws SQLException {
        CharacterRow row = toRow(c, 0);
        c.setId(this.setCharactersDAO(row));
    }

    /**
     * Edit hero in db.
     *
     * @param c the c
     * @throws SQLException the sql exception
     */
    public void editHeroInDB(Character c) throws SQLException {
        this.editCharactersDAO(toRow(c, c.getId()));
    }

    /**
     * Change hero life point in db.
     *
     * @param c the c
     * @throws SQLException the sql exception
     */
    public void changeHeroLifePointInDB(Character c) throws SQLException {
        this.editLifePointsDAO(c.getHealthPoint(), c.getId());
    }

    private CharacterRow toRow(Character c, int id) throws SQLException {
        String type;
        if (c instanceof Warrior) {
            type = "Warrior";
        } else if (c instanceof Wizard) {
            type = "Wizard";
        } else {
            throw new SQLDataException("Character's Type ERROR");
        }
        return new CharacterRow(
                id,
                type,
                c.getName(),
                c.getHealthPoint(),
                c.getAttackPoint(),
                c.getOffensiveEquipment() != null ? c.getOffensiveEquipment().toString() : null,
                c.getDefensiveEquipment() != null ? c.getDefensiveEquipment().toString() : null
        );
    }

    // ---- Call from Main.java ----
    @Override
    public void close() throws SQLException {
        if (this.connection != null && !this.connection.isClosed()) {
            this.connection.close();
        }
    }
}