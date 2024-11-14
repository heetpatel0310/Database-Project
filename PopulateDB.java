import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PopulateDB {

    private Connection connection;

    public PopulateDB(Connection connection) {
        this.connection = connection;
    }

    public static void main(String[] args) {
        Connection connection = SetupConnection.getConnection();
        if (connection == null) {
            System.out.println("Failed to establish database connection.");
            return;
        }
        PopulateDB db = new PopulateDB(connection);
        db.initializeDB();
    }

    public void initializeDB() {
        dropAllTables();

        try {
            createAllGameTables();
            readInputData();
        } catch (SQLException e) {
            // dropAllTables();
            System.out.println(e.getMessage());
            e.printStackTrace();
            System.out.println("Error occured while initializing the database\n\nDROPPING ALL OF THE DATABASE");
        } catch (IOException fnf) {
            System.out.println(fnf.getMessage());
        }
    }

    private void createAllGameTables() throws SQLException {

        // Villagers table
        createVillagersTable();

        // Reactions table
        createReactionsTable();

        // Songs table
        createSongsTable();

        // Posters table
        createPostersTable();

        // Photos table
        createPhotosTable();

        // Achievements table
        createAchievementsTable();

        // Umbrellas table
        createUmbrellasTable();

        // Tools table
        createToolsTable();

        // Socks table
        createSocksTable();

        // Shoes table
        createShoesTable();

        // Housewares table
        createHousewaresTable();

        // Headwear table
        createHeadwearTable();

        // Bottoms table
        createBottomsTable();

        // Bags table
        createBagsTable();
    }

    // 1 Villager table
    private void createVillagersTable() throws SQLException {
        connection.createStatement().executeUpdate(
                "CREATE TABLE villagers (" +
                        "Name VARCHAR(100) NOT NULL," +
                        "Species VARCHAR(50) NOT NULL," +
                        "Gender VARCHAR(10) NOT NULL," +
                        "Personality VARCHAR(50) NOT NULL," +
                        "Hobby VARCHAR(255) NOT NULL," +
                        "Birthday VARCHAR(10) NOT NULL," +
                        "Poster VARCHAR(255)," +
                        "AchievementID INT," +
                        "Catchphrase VARCHAR(255) NOT NULL," +
                        "FurnitureList VARCHAR(255) NOT NULL," +
                        "UniqueEntryID CHAR(255) NOT NULL," +
                        "PRIMARY KEY (UniqueEntryID))");
    }

    // 2 Reactions table
    private void createReactionsTable() throws SQLException {
        connection.createStatement().executeUpdate(
                "CREATE TABLE reactions (" +
                        "Name VARCHAR(255) NOT NULL," +
                        "Source VARCHAR(100) NOT NULL," +
                        "UniqueEntryID CHAR(255) NOT NULL," +
                        "PRIMARY KEY (UniqueEntryID))");
    }

    // 3 Songs table
    private void createSongsTable() throws SQLException {
        connection.createStatement().executeUpdate(
                "CREATE TABLE songs (" +
                        "Name VARCHAR(255) NOT NULL," +
                        "Source VARCHAR(255) NOT NULL," +
                        "Version VARCHAR(10) NOT NULL," +
                        "Catalog VARCHAR(100) NOT NULL," +
                        "InternalID INT NOT NULL," +
                        "UniqueEntryID CHAR(255) NOT NULL," +
                        "PRIMARY KEY (UniqueEntryID))");
    }

    // 4 Posters table
    private void createPostersTable() throws SQLException {
        connection.createStatement().executeUpdate(
                "CREATE TABLE posters (" +
                        "Name VARCHAR(255) NOT NULL," +
                        "Color1 VARCHAR(50) NOT NULL," +
                        "Color2 VARCHAR(50) NOT NULL," +
                        "InternalID INT NOT NULL," +
                        "UniqueEntryID CHAR(255) NOT NULL," +
                        "PRIMARY KEY (UniqueEntryID))");
    }

    // 5 Photos table
    private void createPhotosTable() throws SQLException {
        connection.createStatement().executeUpdate(
                "CREATE TABLE photos (" +
                        "Name VARCHAR(255) NOT NULL," +
                        "Variation VARCHAR(100) NOT NULL," +
                        "Color1 VARCHAR(50) NOT NULL," +
                        "Color2 VARCHAR(50) NOT NULL," +
                        "VariantID VARCHAR(10) NOT NULL," +
                        "InternalID INT NOT NULL," +
                        "UniqueEntryID CHAR(255) NOT NULL," +
                        "PRIMARY KEY (UniqueEntryID))");
    }

    // 6 Achievements table
    private void createAchievementsTable() throws SQLException {
        connection.createStatement().executeUpdate(
                "CREATE TABLE achievements (" +
                        "Name VARCHAR(255) NOT NULL," +
                        "AwardCriteria TEXT NOT NULL," +
                        "InternalID INT NOT NULL," +
                        "UniqueEntryID CHAR(255) NOT NULL," +
                        "PRIMARY KEY (UniqueEntryID))");
    }

    // 7 Umbrellas table
    private void createUmbrellasTable() throws SQLException {
        connection.createStatement().executeUpdate(
                "CREATE TABLE umbrellas (" +
                        "Name VARCHAR(255) NOT NULL," +
                        "Color1 VARCHAR(50) NOT NULL," +
                        "Color2 VARCHAR(50) NOT NULL," +
                        "Source VARCHAR(100) NOT NULL," +
                        "VillagerEquippable VARCHAR(3) NOT NULL," +
                        "InternalID INT NOT NULL," +
                        "UniqueEntryID CHAR(255) NOT NULL," +
                        "PRIMARY KEY (UniqueEntryID))");
    }

    // 8 Tools table
    private void createToolsTable() throws SQLException {
        connection.createStatement().executeUpdate(
                "CREATE TABLE tools (" +
                        "Name VARCHAR(255) NOT NULL," +
                        "Variation VARCHAR(50) NOT NULL," +
                        "BodyTitle VARCHAR(50) NOT NULL," +
                        "Color1 VARCHAR(50) NOT NULL," +
                        "Color2 VARCHAR(50) NOT NULL," +
                        "SetName VARCHAR(100) NOT NULL," +
                        "Source VARCHAR(255) NOT NULL," +
                        "InternalID INT NOT NULL," +
                        "UniqueEntryID CHAR(255) NOT NULL," +
                        "PRIMARY KEY (UniqueEntryID))");

    }

    // 9 Socks table
    private void createSocksTable() throws SQLException {
        connection.createStatement().executeUpdate(
                "CREATE TABLE socks (" +
                        "Name VARCHAR(255) NOT NULL," +
                        "Variation VARCHAR(100) NOT NULL," +
                        "Color1 VARCHAR(50) NOT NULL," +
                        "Color2 VARCHAR(50) NOT NULL," +
                        "SeasonalAvailability VARCHAR(50) NOT NULL," +
                        "Style VARCHAR(50) NOT NULL," +
                        "LabelThemes VARCHAR(100) NOT NULL," +
                        "VillagerEquippable VARCHAR(5) NOT NULL," +
                        "InternalID INT NOT NULL," +
                        "UniqueEntryID CHAR(255) NOT NULL," +
                        "PRIMARY KEY (UniqueEntryID))");
    }

    // 10 Shoes table
    private void createShoesTable() throws SQLException {
        connection.createStatement().executeUpdate(
                "CREATE TABLE shoes (" +
                        "Name VARCHAR(255) NOT NULL," +
                        "Variation VARCHAR(100) NOT NULL," +
                        "Color1 VARCHAR(50) NOT NULL," +
                        "Color2 VARCHAR(50) NOT NULL," +
                        "Source VARCHAR(100) NOT NULL," +
                        "SeasonalAvailability VARCHAR(50) NOT NULL," +
                        "Style VARCHAR(50) NOT NULL," +
                        "LabelThemes VARCHAR(255) NOT NULL," +
                        "InternalID INT NOT NULL," +
                        "UniqueEntryID CHAR(255) NOT NULL," +
                        "PRIMARY KEY (UniqueEntryID))");
    }

    // 11 Housewares table
    private void createHousewaresTable() throws SQLException {
        connection.createStatement().executeUpdate(
                "CREATE TABLE housewares (" +
                        "Name VARCHAR(255) NOT NULL," +
                        "Variation VARCHAR(100) NOT NULL," +
                        "BodyTitle VARCHAR(100)," +
                        "Color1 VARCHAR(50) NOT NULL," +
                        "Color2 VARCHAR(50) NOT NULL," +
                        "HHAConcept1 VARCHAR(100)," +
                        "HHAConcept2 VARCHAR(100)," +
                        "Interact VARCHAR(50) NOT NULL," +
                        "Tag VARCHAR(100) NOT NULL," +
                        "SpeakerType VARCHAR(100)," +
                        "LightingType VARCHAR(100)," +
                        "InternalID INT NOT NULL," +
                        "UniqueEntryID CHAR(255) NOT NULL," +
                        "PRIMARY KEY (UniqueEntryID))");
    }

    // 12 Headwear table
    private void createHeadwearTable() throws SQLException {
        connection.createStatement().executeUpdate(
                "CREATE TABLE headwear (" +
                        "Name VARCHAR(255) NOT NULL," +
                        "Variation VARCHAR(100) NOT NULL," +
                        "Color1 VARCHAR(50) NOT NULL," +
                        "Color2 VARCHAR(50) NOT NULL," +
                        "Source VARCHAR(100) NOT NULL," +
                        "SeasonalAvailability VARCHAR(50) NOT NULL," +
                        "Style VARCHAR(50) NOT NULL," +
                        "LabelThemes VARCHAR(255) NOT NULL," +
                        "Type VARCHAR(50) NOT NULL," +
                        "InternalID INT NOT NULL," +
                        "UniqueEntryID CHAR(255) NOT NULL," +
                        "PRIMARY KEY (UniqueEntryID))");

    }

    // 13 Bottoms table
    private void createBottomsTable() throws SQLException {
        connection.createStatement().executeUpdate(
                "CREATE TABLE bottoms (" +
                        "Name VARCHAR(255) NOT NULL," +
                        "Variation VARCHAR(100) NOT NULL," +
                        "Color1 VARCHAR(50) NOT NULL," +
                        "Color2 VARCHAR(50) NOT NULL," +
                        "Source VARCHAR(100) NOT NULL," +
                        "SeasonalAvailability VARCHAR(50) NOT NULL," +
                        "Style VARCHAR(50) NOT NULL," +
                        "LabelThemes VARCHAR(255) NOT NULL," +
                        "VillagerEquippable VARCHAR(3) NOT NULL," +
                        "InternalID INT NOT NULL," +
                        "UniqueEntryID CHAR(255) NOT NULL," +
                        "PRIMARY KEY (UniqueEntryID))");
    }

    // 14 Bags table
    private void createBagsTable() throws SQLException {
        connection.createStatement().executeUpdate(
                "CREATE TABLE bags (" +
                        "Name VARCHAR(255) NOT NULL," +
                        "Variation VARCHAR(100)," +
                        "Color1 VARCHAR(50) NOT NULL," +
                        "Color2 VARCHAR(50) NOT NULL," +
                        "Source VARCHAR(100) NOT NULL," +
                        "SeasonalAvailability VARCHAR(50) NOT NULL," +
                        "Style VARCHAR(50) NOT NULL," +
                        "LabelThemes VARCHAR(255) NOT NULL," +
                        "InternalID INT NOT NULL," +
                        "UniqueEntryID CHAR(255) NOT NULL," +
                        "PRIMARY KEY (UniqueEntryID))");
    }

    private void readInputData() throws SQLException, IOException {

        System.out.println("Good things take time......");
        insertIntoVillagers();
        insertIntoReactions();
        insertIntoSongs();
        insertIntoPosters();
        insertIntoPhotos();
        insertIntoAchievements();
        insertIntoUmbrellas();
        insertIntoTools();
        insertIntoSocks();
        insertIntoShoes();
        insertIntoHousewares();
        insertIntoHeadwear();
        insertIntoBottoms();
        insertIntoBags();

        System.out.println();

    }

    private void insertIntoVillagers() throws SQLException, IOException {
        System.out.println("");
        System.out.println("Populating villagers table...");

        BufferedReader br = null;
        PreparedStatement pstmt = null;
        try {
            br = new BufferedReader(new FileReader("Data/villagers.csv"));
            String sql = "INSERT INTO villagers (Name, Species, Gender, Personality, Hobby, Birthday, Poster, AchievementID, Catchphrase, FurnitureList, UniqueEntryID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = connection.prepareStatement(sql);
            String inputLine;
            int lineNumber = 1; // Start at 1 to account for the header row

            br.readLine(); // Skip the header row

            while ((inputLine = br.readLine()) != null) {
                lineNumber++; // Increment line number
                String[] inputArr = inputLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);

                if (inputArr.length != 11) {
                    System.out.println(
                            "Skipping line " + lineNumber + " due to incorrect number of fields: " + inputLine);
                    continue;
                }
                for (int i = 0; i < inputArr.length; i++) {
                    String data = inputArr[i].trim().replace("\"", "");
                    if (i == 7) {
                        int achievementId = data.isEmpty() || data.equals("-1") ? -1 : Integer.parseInt(data);
                        pstmt.setInt(8, achievementId);
                    } else {
                        pstmt.setString(i + 1, data);
                    }
                }

                pstmt.executeUpdate();
            }
            System.out.println("Villagers table populated successfully.");
        } catch (FileNotFoundException fnfe) {
            throw new FileNotFoundException("Data/villagers.csv file not found.");
        } catch (IOException ioe) {
            throw new IOException("Error occurred while reading Data/villagers.csv.");
        } catch (SQLException se) {
            se.printStackTrace();
            throw new SQLException("Error occurred while inserting into villagers table.");
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (br != null) {
                br.close();
            }
        }
    }

    private void insertIntoReactions() throws SQLException, IOException {
        System.out.println("");
        System.out.println("Populating reactions table...");
        try {
            BufferedReader br = new BufferedReader(new FileReader("Data/reactions.csv"));
            String sql = "INSERT INTO reactions (Name, Source, UniqueEntryID) VALUES (?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            String inputLine;

            br.readLine(); // Skipping the header row

            while ((inputLine = br.readLine()) != null) {
                String[] inputArr = inputLine.split(",");
                if (inputArr.length < 3) {
                    System.out.println("Skipping incomplete line: " + inputLine);
                    continue; // Skip this iteration and move to the next line
                }

                pstmt.setString(1, inputArr[0]);
                pstmt.setString(2, inputArr[1]);
                pstmt.setString(3, inputArr[2]);
                pstmt.executeUpdate();
            }

            br.close();
            System.out.println("Reactions table populated successfully.");
        } catch (FileNotFoundException fnfe) {
            throw new FileNotFoundException("Data/reactions.csv file not found.");
        } catch (IOException ioe) {
            throw new IOException("Error occurred while reading Data/reactions.csv.");
        } catch (SQLException se) {
            se.printStackTrace();
            throw new SQLException("Error occurred while inserting into reactions table.");
        }
    }

    private void insertIntoSongs() throws SQLException, IOException {
        System.out.println("");
        System.out.println("Populating songs table...");

        try {
            BufferedReader br = new BufferedReader(new FileReader("Data/songs.csv"));
            String sql = "INSERT INTO songs (Name, Source, Version, Catalog, InternalID, UniqueEntryID) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            String inputLine;

            br.readLine(); // Skipping the header row

            while ((inputLine = br.readLine()) != null) {
                // System.out.println(inputLine);
                String[] inputArr = inputLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                pstmt.setString(1, inputArr[0].replace("\"", "")); // Remove potential double quotes
                pstmt.setString(2, inputArr[1].replace("\"", "")); // Remove potential double quotes
                pstmt.setString(3, inputArr[2]);
                pstmt.setString(4, inputArr[3]);
                pstmt.setInt(5, Integer.parseInt(inputArr[4]));
                pstmt.setString(6, inputArr[5]);
                pstmt.executeUpdate();

            }
            br.close();
            System.out.println("Songs table populated successfully.");
        } catch (FileNotFoundException fnfe) {
            throw new FileNotFoundException("Data/songs.csv file not found.");
        } catch (IOException ioe) {
            throw new IOException("Error occurred while reading Data/songs.csv.");
        } catch (SQLException se) {
            se.printStackTrace();
            throw new SQLException("Error occurred while inserting into songs table.");
        }
    }

    private void insertIntoPosters() throws SQLException, IOException {
        System.out.println("");
        System.out.println("Populating posters table...");

        try {
            BufferedReader br = new BufferedReader(new FileReader("Data/posters.csv"));
            String sql = "INSERT INTO posters (Name, Color1, Color2, InternalID, UniqueEntryID) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            String inputLine;
            String[] inputArr;

            br.readLine(); // Skipping the header row

            while ((inputLine = br.readLine()) != null) {
                inputArr = inputLine.split(",");

                pstmt.setString(1, inputArr[0]);
                pstmt.setString(2, inputArr[1]);
                pstmt.setString(3, inputArr[2]);
                pstmt.setInt(4, Integer.parseInt(inputArr[3]));
                pstmt.setString(5, inputArr[4]);
                pstmt.executeUpdate();
            }
            br.close();
            System.out.println("Posters table populated successfully.");
        } catch (FileNotFoundException fnfe) {
            throw new FileNotFoundException("Data/posters.csv file not found.");
        } catch (IOException ioe) {
            throw new IOException("Error occurred while reading Data/posters.csv.");
        } catch (SQLException se) {
            se.printStackTrace();
            throw new SQLException("Error occurred while inserting into posters table.");
        }
    }

    private void insertIntoPhotos() throws SQLException, IOException {
        System.out.println("");
        System.out.println("Populating photos table...");

        try {
            BufferedReader br = new BufferedReader(new FileReader("Data/photos.csv"));
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO photos (Name, Variation, Color1, Color2, VariantID, InternalID, UniqueEntryID) VALUES (?, ?, ?, ?, ?, ?, ?)");
            String inputLine;
            while ((br.readLine()) != null) { // Skip header
                inputLine = br.readLine();
                if (inputLine == null)
                    break;
                String[] inputArr = inputLine.split(",");
                pstmt.setString(1, inputArr[0]);
                pstmt.setString(2, inputArr[1]);
                pstmt.setString(3, inputArr[2]);
                pstmt.setString(4, inputArr[3]);
                pstmt.setString(5, inputArr[4]);
                pstmt.setInt(6, Integer.parseInt(inputArr[5]));
                pstmt.setString(7, inputArr[6]);
                pstmt.executeUpdate();
            }
            br.close();
            System.out.println("Photos table populated successfully.");
        } catch (FileNotFoundException e) {
            System.err.println("Data/photos.csv file not found.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void insertIntoAchievements() throws SQLException, IOException {
        System.out.println("");
        System.out.println("Populating achievements table...");

        try {
            BufferedReader br = new BufferedReader(new FileReader("Data/achievements.csv"));
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO achievements (Name, AwardCriteria, InternalID, UniqueEntryID) VALUES (?, ?, ?, ?)");
            String inputLine;
            while ((br.readLine()) != null) { // Skip header
                inputLine = br.readLine();
                if (inputLine == null)
                    break;
                String[] inputArr = inputLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                pstmt.setString(1, inputArr[0].trim().replace("\"", ""));
                pstmt.setString(2, inputArr[1].trim().replace("\"", ""));
                pstmt.setInt(3, Integer.parseInt(inputArr[2]));
                pstmt.setString(4, inputArr[3]);
                pstmt.executeUpdate();
            }
            br.close();
            System.out.println("Achievements table populated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertIntoUmbrellas() throws SQLException, IOException {
        System.out.println("");
        System.out.println("Populating umbrellas table...");

        try {
            BufferedReader br = new BufferedReader(new FileReader("Data/umbrellas.csv"));
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO umbrellas (Name, Color1, Color2, Source, VillagerEquippable, InternalID, UniqueEntryID) VALUES (?, ?, ?, ?, ?, ?, ?)");
            String inputLine;
            while ((br.readLine()) != null) { // Skip header
                inputLine = br.readLine();
                if (inputLine == null)
                    break;
                String[] inputArr = inputLine.split(",");
                pstmt.setString(1, inputArr[0]);
                pstmt.setString(2, inputArr[1]);
                pstmt.setString(3, inputArr[2]);
                pstmt.setString(4, inputArr[3]);
                pstmt.setString(5, inputArr[4]);
                pstmt.setInt(6, Integer.parseInt(inputArr[5]));
                pstmt.setString(7, inputArr[6]);
                pstmt.executeUpdate();
            }
            br.close();
            System.out.println("Umbrellas table populated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertIntoTools() throws SQLException, IOException {
        System.out.println("");
        System.out.println("Populating tools table...");

        BufferedReader br = null;
        PreparedStatement pstmt = null;
        try {
            br = new BufferedReader(new FileReader("Data/tools.csv"));
            String sql = "INSERT INTO tools (Name, Variation, BodyTitle, Color1, Color2, SetName, Source, InternalID, UniqueEntryID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = connection.prepareStatement(sql);
            String inputLine;

            br.readLine(); // Skip the header

            while ((inputLine = br.readLine()) != null) {
                String[] inputArr = inputLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (inputArr.length != 9) {
                    System.out.println("Skipping incomplete or incorrectly formatted line: " + inputLine);
                    continue; // Skip this iteration and move to the next line
                }

                pstmt.setString(1, inputArr[0]);
                pstmt.setString(2, inputArr[1]);
                pstmt.setString(3, inputArr[2]);
                pstmt.setString(4, inputArr[3]);
                pstmt.setString(5, inputArr[4]);
                pstmt.setString(6, inputArr[5]);
                pstmt.setString(7, inputArr[6]);
                pstmt.setInt(8, Integer.parseInt(inputArr[7].trim())); // Trim any whitespace before parsing
                pstmt.setString(9, inputArr[8]);
                pstmt.executeUpdate();
            }
            System.out.println("Tools table populated successfully.");
        } catch (FileNotFoundException e) {
            System.err.println("Data/tools.csv file not found.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (br != null) {
                br.close();
            }
        }
    }

    private void insertIntoSocks() throws SQLException, IOException {
        System.out.println("");
        System.out.println("Populating socks table...");

        try {
            BufferedReader br = new BufferedReader(new FileReader("Data/socks.csv"));
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO socks (Name, Variation, Color1, Color2, SeasonalAvailability, Style, LabelThemes, VillagerEquippable, InternalID, UniqueEntryID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            String inputLine;
            while ((br.readLine()) != null) { // Skip header
                inputLine = br.readLine();
                if (inputLine == null)
                    break;
                String[] inputArr = inputLine.split(",");
                pstmt.setString(1, inputArr[0]);
                pstmt.setString(2, inputArr[1]);
                pstmt.setString(3, inputArr[2]);
                pstmt.setString(4, inputArr[3]);
                pstmt.setString(5, inputArr[4]);
                pstmt.setString(6, inputArr[5]);
                pstmt.setString(7, inputArr[6]);
                pstmt.setString(8, inputArr[7]);
                pstmt.setInt(9, Integer.parseInt(inputArr[8]));
                pstmt.setString(10, inputArr[9]);
                pstmt.executeUpdate();
            }
            br.close();
            System.out.println("Socks table populated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertIntoShoes() throws SQLException, IOException {
        System.out.println("");
        System.out.println("Populating shoes table...");

        try {
            BufferedReader br = new BufferedReader(new FileReader("Data/shoes.csv"));
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO shoes (Name, Variation, Color1, Color2, Source, SeasonalAvailability, Style, LabelThemes, InternalID, UniqueEntryID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            String inputLine;
            while ((br.readLine()) != null) { // Skip header
                inputLine = br.readLine();
                if (inputLine == null)
                    break;
                String[] inputArr = inputLine.split(",");
                pstmt.setString(1, inputArr[0]);
                pstmt.setString(2, inputArr[1]);
                pstmt.setString(3, inputArr[2]);
                pstmt.setString(4, inputArr[3]);
                pstmt.setString(5, inputArr[4]);
                pstmt.setString(6, inputArr[5]);
                pstmt.setString(7, inputArr[6]);
                pstmt.setString(8, inputArr[7]);
                pstmt.setInt(9, Integer.parseInt(inputArr[8]));
                pstmt.setString(10, inputArr[9]);
                pstmt.executeUpdate();
            }
            br.close();
            System.out.println("Shoes table populated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertIntoHousewares() throws SQLException, IOException {
        System.out.println("");
        System.out.println("Populating housewares table...");

        try {
            BufferedReader br = new BufferedReader(new FileReader("Data/housewares.csv"));
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO housewares (Name, Variation, BodyTitle, Color1, Color2, HHAConcept1, HHAConcept2, Interact, Tag, SpeakerType, LightingType, InternalID, UniqueEntryID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            String inputLine;
            while ((br.readLine()) != null) { // Skip header
                inputLine = br.readLine();
                if (inputLine == null)
                    break;
                String[] inputArr = inputLine.split(",");
                pstmt.setString(1, inputArr[0]);
                pstmt.setString(2, inputArr[1]);
                pstmt.setString(3, inputArr[2]);
                pstmt.setString(4, inputArr[3]);
                pstmt.setString(5, inputArr[4]);
                pstmt.setString(6, inputArr[5]);
                pstmt.setString(7, inputArr[6]);
                pstmt.setString(8, inputArr[7]);
                pstmt.setString(9, inputArr[8]);
                pstmt.setString(10, inputArr[9]);
                pstmt.setString(11, inputArr[10]);
                pstmt.setInt(12, Integer.parseInt(inputArr[11]));
                pstmt.setString(13, inputArr[12]);
                pstmt.executeUpdate();
            }
            br.close();
            System.out.println("Housewares table populated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertIntoHeadwear() throws SQLException, IOException {
        System.out.println("");
        System.out.println("Populating headwear table...");

        BufferedReader br = null;
        PreparedStatement pstmt = null;
        try {
            br = new BufferedReader(new FileReader("Data/headwear.csv"));
            String sql = "INSERT INTO headwear (Name, Variation, Color1, Color2, Source, SeasonalAvailability, Style, LabelThemes, Type, InternalID, UniqueEntryID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = connection.prepareStatement(sql);
            String inputLine;

            br.readLine(); // Skip the header line

            while ((inputLine = br.readLine()) != null) {
                String[] inputArr = inputLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (inputArr.length != 11) {
                    System.out.println("Skipping incomplete or incorrectly formatted line: " + inputLine);
                    continue;
                }

                pstmt.setString(1, inputArr[0].trim().replace("\"", ""));
                pstmt.setString(2, inputArr[1].trim().replace("\"", ""));
                pstmt.setString(3, inputArr[2].trim().replace("\"", ""));
                pstmt.setString(4, inputArr[3].trim().replace("\"", ""));
                pstmt.setString(5, inputArr[4].trim().replace("\"", ""));
                pstmt.setString(6, inputArr[5].trim().replace("\"", ""));
                pstmt.setString(7, inputArr[6].trim().replace("\"", ""));
                pstmt.setString(8, inputArr[7].trim().replace("\"", ""));
                pstmt.setString(9, inputArr[8].trim().replace("\"", ""));
                pstmt.setInt(10, Integer.parseInt(inputArr[9].trim())); // Parse the InternalID
                pstmt.setString(11, inputArr[10].trim().replace("\"", ""));

                pstmt.executeUpdate();
            }
            System.out.println("Headwear table populated successfully.");
        } catch (FileNotFoundException e) {
            System.err.println("Data/headwear.csv file not found.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
            if (br != null) {
                br.close();
            }
        }
    }

    private void insertIntoBottoms() throws SQLException, IOException {
        System.out.println("");
        System.out.println("Populating bottoms table...");

        try {
            BufferedReader br = new BufferedReader(new FileReader("Data/bottoms.csv"));
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO bottoms (Name, Variation, Color1, Color2, Source, SeasonalAvailability, Style, LabelThemes, VillagerEquippable, InternalID, UniqueEntryID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            String inputLine;
            while ((br.readLine()) != null) { // Skip header
                inputLine = br.readLine();
                if (inputLine == null)
                    break;
                String[] inputArr = inputLine.split(",");
                pstmt.setString(1, inputArr[0]);
                pstmt.setString(2, inputArr[1]);
                pstmt.setString(3, inputArr[2]);
                pstmt.setString(4, inputArr[3]);
                pstmt.setString(5, inputArr[4]);
                pstmt.setString(6, inputArr[5]);
                pstmt.setString(7, inputArr[6]);
                pstmt.setString(8, inputArr[7]);
                pstmt.setString(9, inputArr[8]);
                pstmt.setInt(10, Integer.parseInt(inputArr[9]));
                pstmt.setString(11, inputArr[10]);
                pstmt.executeUpdate();
            }
            br.close();
            System.out.println("Bottoms table populated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertIntoBags() throws SQLException, IOException {
        System.out.println("");
        System.out.println("Populating bags table...");

        try {
            BufferedReader br = new BufferedReader(new FileReader("Data/bags.csv"));
            PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO bags (Name, Variation, Color1, Color2, Source, SeasonalAvailability, Style, LabelThemes, InternalID, UniqueEntryID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            String inputLine;
            while ((br.readLine()) != null) { // Skip header
                inputLine = br.readLine();
                if (inputLine == null)
                    break;
                String[] inputArr = inputLine.split(",");
                pstmt.setString(1, inputArr[0]);
                pstmt.setString(2, inputArr[1]);
                pstmt.setString(3, inputArr[2]);
                pstmt.setString(4, inputArr[3]);
                pstmt.setString(5, inputArr[4]);
                pstmt.setString(6, inputArr[5]);
                pstmt.setString(7, inputArr[6]);
                pstmt.setString(8, inputArr[7]);
                pstmt.setInt(9, Integer.parseInt(inputArr[8]));
                pstmt.setString(10, inputArr[9]);
                pstmt.executeUpdate();
            }
            br.close();
            System.out.println("Bags table populated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropAllTables() {
        dropTable("villagers");
        dropTable("reactions");
        dropTable("songs");
        dropTable("posters");
        dropTable("photos");
        dropTable("achievements");
        dropTable("umbrellas");
        dropTable("tools");
        dropTable("socks");
        dropTable("shoes");
        dropTable("housewares");
        dropTable("headwear");
        dropTable("bottoms");
        dropTable("bags");
        System.out.println("All tables dropped successfully.");
        System.out.println();
    }

    private void dropTable(String tableName) {
        try {
            String sql = "DROP TABLE IF EXISTS " + tableName + ";";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.executeUpdate();
                System.out.println("Dropped table " + tableName);
            }
        } catch (SQLException se) {
            System.out.println("Error while dropping the table " + tableName + ": " + se.getMessage());
            se.printStackTrace();
        }
    }

}
