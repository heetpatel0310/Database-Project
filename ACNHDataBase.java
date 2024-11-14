import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;

public class ACNHDataBase {

    private static AnimalCrossingDatabase db;
    private static PopulateDB pdb;

    public static void main(String[] args) {
        try (Connection connection = SetupConnection.getConnection()) {
            if (connection != null) {
                Scanner console = new Scanner(System.in);
                db = new AnimalCrossingDatabase(connection, console);
                pdb = new PopulateDB(connection);
                runConsole(console);
            } else {
                System.out.println("Failed to establish database connection.");
            }
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }
    }

    public static void runConsole(Scanner console) {
        String input;
        do {
            printMenu();
            System.out.print(
                    "Enter your choice (1-16, d to drop all tables, p to repopulate all data, h for help, e to exit): ");
            input = console.nextLine().trim().toLowerCase();

            if (input.equals("1")) {
                db.viewItemStyles();
            } else if (input.equals("2")) {
                db.viewUniqueGameEntities();
            } else if (input.equals("3")) {
                db.viewPopularSongs();
            } else if (input.equals("4")) {
                db.countToolsByVillager();
            } else if (input.equals("5")) {
                db.printVillagersWithSameBirthday();
            } else if (input.equals("6")) {
                db.listVillagersWithAchievements();
            } else if (input.equals("7")) {
                db.identifyExclusiveSongs();
            } else if (input.equals("8")) {
                db.listReactionsByPopularity();
            } else if (input.equals("9")) {
                db.countSpeakerTypes();
            } else if (input.equals("10")) {
                db.viewTopVillagersByPosters();
            } else if (input.equals("11")) {
                db.analyzeShoeColorsBySource();
            } else if (input.equals("12")) {
                db.analyzeUmbrellaColorsBySource();
            } else if (input.equals("13")) {
                db.findNaturalWoodAndRedPhotos();
            } else if (input.equals("14")) {
                db.countSocksByStyle();
            } else if (input.equals("15")) {
                db.findHeadwearByColors();
            } else if (input.equals("16")) {
                db.filterBottomsByCriteria();
            } else if (input.equals("d")) {
                pdb.dropAllTables();
            } else if (input.equals("p")) {
                pdb.initializeDB();
            } else if (input.equals("h")) {
                printHelp();
            } else if (input.equals("e")) {
                System.out.println("Exiting the application.");
            } else {
                System.out.println("Invalid input. Type 'h' for help or 'e' to exit.");
            }
        } while (!input.equals("e"));
        console.close();
    }

    private static void printMenu() {
        System.out.println("\nPlease select an option:");
        System.out.println("1. View Item Styles");
        System.out.println("2. View Unique Game Entities");
        System.out.println("3. View Popular Songs");
        System.out.println("4. Count Tools by Villager");
        System.out.println("5. Print Villagers with Same Birthday");
        System.out.println("6. List Villagers with Achievements");
        System.out.println("7. Identify Exclusive Songs");
        System.out.println("8. List Reactions by Popularity");
        System.out.println("9. Count Speaker Types");
        System.out.println("10. View Top Villagers by Posters");
        System.out.println("11. Analyze Shoe Colors by Source");
        System.out.println("12. Analyze Umbrella Colors by Source");
        System.out.println("13. Find 'Natural Wood' and 'Red' Photos");
        System.out.println("14. Filter Socks By Style and Season");
        System.out.println("15. Filter Headwear By Color");
        System.out.println("16. Filter Bottoms By Criteria");
        System.out.println("d - Drop All Tables");
        System.out.println("p - Repopulate All Data");
        System.out.println("h - Help");
        System.out.println("e - Exit");
    }

    private static void printHelp() {
        System.out.println("\nYou selected: Help");
        System.out.println("Help Menu:");
        System.out.println("1. View Item Styles - Input a style to view items of that style.");
        System.out.println("2. View Unique Game Entities - Input a type to get names of unique game entities.");
        System.out.println("3. View Popular Songs - Shows the most to least popular songs among villagers.");
        System.out.println("4. Count Tools by Villager - Counts how many tools are associated with each villager.");
        System.out.println("5. Print Villagers with Same Birthday - Displays villagers sharing the same birthday.");
        System.out.println(
                "6. List Villagers with Achievements - Lists villagers who have achieved specific milestones.");
        System.out.println("7. Identify Exclusive Songs - Finds songs that are not for sale or not in the catalog.");
        System.out.println(
                "8. List Reactions by Popularity - Displays reactions and how many villagers can perform them.");
        System.out.println("9. Count Speaker Types - Counts how many of each speaker type are owned by villagers.");
        System.out.println("10. View Top Villagers by Posters - Lists villagers with the most posters collected.");
        System.out.println(
                "11. Analyze Shoe Colors by Source - Shows the distribution of shoe colors from different sources.");
        System.out.println(
                "12. Analyze Umbrella Colors by Source - Analyzes umbrella colors available from each source.");
        System.out.println(
                "13. Find 'Natural Wood' and 'Red' Photos - Finds photos with 'Natural Wood' variation and 'Red' color.");
        System.out.println("14. Filter Socks By Style and Season - Filters socks by style and season.");
        System.out.println("15. Filter Headwear By Color - Filters headwear based on color.");
        System.out.println("16. Filter Bottoms By Criteria - Filters bottoms based on multiple criteria.");
        System.out.println("d - Drop All Tables - Deletes all tables from the database.");
        System.out.println("p - Repopulate All Data - Repopulates the database with initial data.");
        System.out.println("h/help - Help - Displays this help menu.");
        System.out.println("e/exit - Exit - Exits the application.");
    }
}

class AnimalCrossingDatabase {
    private Connection connection;
    private Scanner scanner;

    public AnimalCrossingDatabase(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
    }

    public void viewItemStyles() {
        System.out.print("Enter the style [Cute/Simple/Active/Elegant/Gorgeous/Cool] you want to search for: ");
        String style = scanner.nextLine();

        String sql = "SELECT Name, Style, Color1, Color2, Source, SeasonalAvailability, LabelThemes, InternalID, UniqueEntryID FROM bags WHERE Style = ?;";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, style);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("No bags found for the style '" + style + "'.");
                return;
            }

            // Print the header row
            System.out.println("-".repeat(140));
            System.out.printf("%-25s %-10s %-10s %-10s %-15s %-20s %-15s %-10s %-15s%n", "Name", "Style", "Color1",
                    "Color2", "Source", "Seasonal Availability", "Label Themes", "Internal ID", "Unique Entry ID");
            System.out.println("-".repeat(140));

            // Iterate through the result set and print each row
            while (rs.next()) {
                String name = rs.getString("Name");
                String styleValue = rs.getString("Style");
                String color1 = rs.getString("Color1");
                String color2 = rs.getString("Color2");
                String source = rs.getString("Source");
                String seasonalAvailability = rs.getString("SeasonalAvailability");
                String labelThemes = rs.getString("LabelThemes");
                int internalId = rs.getInt("InternalID");
                String uniqueEntryID = rs.getString("UniqueEntryID");

                System.out.printf("%-25s %-10s %-10s %-10s %-15s %-20s %-15s %-10d %-15s%n", name, styleValue, color1,
                        color2, source, seasonalAvailability, labelThemes, internalId, uniqueEntryID);
            }
        } catch (SQLException e) {
            System.out.println("Database error occurred: " + e.getMessage());
        }
    }

    public void viewUniqueGameEntities() {
        System.out.print("Enter the entity [Song/ Photos/ Reactions] you want to get the names for: ");
        String entityType = scanner.nextLine();

        String sql = "";
        String[] headers = new String[0];
        int totalLength = 0; // Variable to keep track of total length of headers

        if (entityType.equalsIgnoreCase("Song")) {
            sql = "SELECT DISTINCT TOP 10 Name, Source, Version, Catalog, InternalID, UniqueEntryID FROM songs ORDER BY Name;";
            headers = new String[] { "Name", "Source", "Version", "Catalog", "InternalID", "UniqueEntryID" };
            totalLength = 15 + 30 + 15 + 15 + 15 + 30; // Lengths of each column plus spacing
        } else if (entityType.equalsIgnoreCase("Photos")) {
            sql = "SELECT DISTINCT TOP 10 Name, Variation, Color1, Color2, VariantID, InternalID, UniqueEntryID FROM photos ORDER BY Name;";
            headers = new String[] { "Name", "Variation", "Color1", "Color2", "VariantID", "InternalID",
                    "UniqueEntryID" };
            totalLength = 20 + 20 + 20 + 20 + 15 + 15 + 30; // Lengths of each column plus spacing
        } else if (entityType.equalsIgnoreCase("Reactions")) {
            sql = "SELECT DISTINCT TOP 10 Name, Source, UniqueEntryID FROM reactions ORDER BY Name;";
            headers = new String[] { "Name", "Source", "UniqueEntryID" };
            totalLength = 20 + 20 + 20; // Lengths of each column plus spacing
        } else {
            System.out.println("Invalid entity type. Please choose between Song, Photos, or Reactions.");
            return;
        }

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("No " + entityType + " found.");
                return;
            }

            // Print the header row with separators
            System.out.println("-".repeat(totalLength));
            for (String header : headers) {
                System.out.printf("%-20s", header);
            }
            System.out.println();
            System.out.println("-".repeat(totalLength));

            // Print each row
            while (rs.next()) {
                for (String header : headers) {
                    System.out.printf("%-20s", rs.getString(header));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Database error occurred: " + e.getMessage());
        }
    }

    public void viewPopularSongs() {
        String sql = "SELECT TOP 10 Name, Source, Version, Catalog, COUNT(UniqueEntryID) AS songCount FROM songs GROUP BY Name, Source, Version, Catalog ORDER BY songCount DESC";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            // Print column headers
            System.out.println(String.join("", java.util.Collections.nCopies(105, "-")));
            System.out.printf("%-30s %-30s %-15s %-20s %s\n", "Song Name", "Source", "Version", "Catalog", "Count");
            System.out.println(String.join("", java.util.Collections.nCopies(105, "-")));

            // Print each row data
            while (rs.next()) {
                String songName = rs.getString("Name");
                String source = rs.getString("Source");
                String version = rs.getString("Version");
                String catalog = rs.getString("Catalog");
                int count = rs.getInt("songCount");

                System.out.printf("%-30s %-30s %-15s %-20s %d\n", songName, source, version, catalog, count);
            }
        } catch (SQLException e) {
            System.out.println("Database error occurred: " + e.getMessage());
        }
    }

    public void countToolsByVillager() {
        String sql = "SELECT Name, COUNT(*) AS totalTools FROM tools GROUP BY Name ORDER BY totalTools DESC";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("No tools found in the game.");
                return;
            }

            System.out.println("Count of each type of tool in the game:");
            System.out.println(String.join("", java.util.Collections.nCopies(63, "-")));
            System.out.printf("%-50s %s\n", "Tool Name", "Total Count");
            System.out.println(String.join("", java.util.Collections.nCopies(63, "-")));

            // Iterate through the result set and print each tool name with its count
            while (rs.next()) {
                String name = rs.getString("Name");
                int totalTools = rs.getInt("totalTools");

                System.out.printf("%-50s %d\n", name, totalTools);
            }
        } catch (SQLException e) {
            System.out.println("Database error occurred: " + e.getMessage());
        }
    }

    public void printVillagersWithSameBirthday() {
        System.out.print("Enter the birthday to search for (DD-Mon[Example: 02-Jul]): ");
        String birthday = scanner.nextLine();

        String sql = "SELECT Name, Species, Gender, Personality, Hobby, Birthday, Catchphrase FROM villagers WHERE Birthday = ?;";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, birthday);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println("No villagers found with the birthday '" + birthday + "'.");
                return;
            }

            // Print the header row
            System.out.println("-".repeat(110));
            System.out.printf("%-20s %-15s %-10s %-15s %-15s %-15s %-30s%n", "Name", "Species", "Gender", "Personality",
                    "Hobby", "Birthday", "Catchphrase");
            System.out.println("-".repeat(110));

            // Iterate through the result set and print each row
            while (rs.next()) {
                String name = rs.getString("Name");
                String species = rs.getString("Species");
                String gender = rs.getString("Gender");
                String personality = rs.getString("Personality");
                String hobby = rs.getString("Hobby");
                String catchphrase = rs.getString("Catchphrase");

                System.out.printf("%-20s %-15s %-10s %-15s %-15s %-15s %-30s%n", name, species, gender, personality,
                        hobby, birthday, catchphrase);
            }
        } catch (SQLException e) {
            System.out.println("Database error occurred: " + e.getMessage());
        }
    }

    public void listVillagersWithAchievements() {
        String sql = "SELECT villagers.Name AS VillagerName, achievements.Name AS AchievementName, achievements.AwardCriteria "
                +
                "FROM villagers " +
                "JOIN achievements ON villagers.AchievementID = achievements.InternalID";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            System.out.println("Listing Villagers with Their Achievements:");
            System.out.println(
                    "-----------------------------------------------------------------------------------------------");
            System.out.printf("%-20s %-30s %-50s\n", "Villager Name", "Achievement Name", "Award Criteria"); // Header
            System.out.println(
                    "-----------------------------------------------------------------------------------------------");

            while (rs.next()) {
                String villagerName = rs.getString("VillagerName");
                String achievementName = rs.getString("AchievementName");
                String awardCriteria = rs.getString("AwardCriteria");

                // Print each record in a formatted manner
                System.out.printf("%-20s %-30s %-50s\n", villagerName, achievementName, awardCriteria);
            }
        } catch (SQLException e) {
            System.out.println("Database error occurred: " + e.getMessage());
        }
    }

    public void identifyExclusiveSongs() {
        String sql = "SELECT name FROM songs WHERE Catalog = 'Not for sale' " +
                "UNION " +
                "SELECT name FROM songs WHERE Catalog = 'Not in catalog'";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            System.out.println("Identifying Exclusive Songs (Not for Sale or Not in Catalog):");
            // Print table header
            System.out.println(String.join("", java.util.Collections.nCopies(15, "-")));
            System.out.printf("%-30s\n", "Song Name");
            System.out.println(String.join("", java.util.Collections.nCopies(15, "-")));

            while (rs.next()) {
                String name = rs.getString("name");
                // Print each row in the table
                System.out.printf("%-30s\n", name);
            }

            if (!rs.isBeforeFirst()) {
                System.out.println("No exclusive songs found.");
            }
        } catch (SQLException e) {
            System.out.println("Database error occurred: " + e.getMessage());
        }
    }

    public void listReactionsByPopularity() {
        String sql = "SELECT reactions.name, COUNT(villagers.UniqueEntryID) AS villagerCount " +
                "FROM reactions " +
                "JOIN villagers ON reactions.Source = villagers.Personality " +
                "GROUP BY reactions.name " +
                "ORDER BY villagerCount DESC";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            // Print header
            System.out.println(String.join("", java.util.Collections.nCopies(55, "-")));
            System.out.printf("%-5s %-30s %s\n", "Rank", "Reaction Name", "Villager Count");
            System.out.println(String.join("", java.util.Collections.nCopies(55, "-")));

            int rank = 1;
            while (rs.next()) {
                String reactionName = rs.getString("name");
                int villagerCount = rs.getInt("villagerCount");

                // Print each row
                System.out.printf("%-5d %-30s %d\n", rank, reactionName, villagerCount);
                rank++;
            }
        } catch (SQLException e) {
            System.out.println("Database error occurred: " + e.getMessage());
        }
    }

    public void countSpeakerTypes() {
        System.out.print("Enter the Speaker type [Does not play music/ Hi-fi/ Phono/ Retro] you want to search for: ");
        String speakerType = scanner.nextLine();

        if (!speakerType.equalsIgnoreCase("Does not play music") && !speakerType.equalsIgnoreCase("Hi-fi") &&
                !speakerType.equalsIgnoreCase("Phono") && !speakerType.equalsIgnoreCase("Retro")) {
            System.out.println(
                    "Invalid speaker type entered. Please choose between 'Does not play music', 'Hi-fi', 'Phono', or 'Retro'.");
            return;
        }

        String sql = "SELECT COUNT(Housewares.UniqueEntryID) AS totalCount FROM Housewares WHERE Housewares.SpeakerType = ?;";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, speakerType);
            ResultSet rs = pstmt.executeQuery();

            // Print table header
            System.out.println(String.join("", java.util.Collections.nCopies(35, "-"))); // Separator for the table
            System.out.printf("%-20s %s\n", "Speaker Type", "Total Count");
            System.out.println(String.join("", java.util.Collections.nCopies(35, "-"))); // Separator for the table

            if (rs.next()) {
                int totalCount = rs.getInt("totalCount");
                // Print the data row
                System.out.printf("%-20s %d\n", speakerType, totalCount);
            } else {
                System.out.println("No data found for the specified speaker type.");
            }
        } catch (SQLException e) {
            System.out.println("Database error occurred: " + e.getMessage());
        }
    }

    public void viewTopVillagersByPosters() {
        try {
            System.out.print("Enter the number of top villagers to display: ");
            int topVillagersCount = Integer.parseInt(scanner.nextLine());

            String sql = String.format(
                    "SELECT TOP %d villagers.name, COUNT(posters.UniqueEntryID) AS posterCount " +
                            "FROM villagers " +
                            "JOIN posters ON villagers.Poster = posters.Name " +
                            "GROUP BY villagers.name " +
                            "ORDER BY posterCount DESC",
                    topVillagersCount);

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                ResultSet rs = pstmt.executeQuery();

                // Print table headers
                System.out.println(String.join("", java.util.Collections.nCopies(35, "-")));
                System.out.printf("%-20s %-15s\n", "Villager Name", "Posters Count");
                System.out.println(String.join("", java.util.Collections.nCopies(35, "-")));

                // Print each row data
                while (rs.next()) {
                    String villagerName = rs.getString("name");
                    int posterCount = rs.getInt("posterCount");
                    System.out.printf("%-20s %-15d\n", villagerName, posterCount);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid integer number.");
        } catch (SQLException e) {
            System.out.println("Database error occurred: " + e.getMessage());
        }
    }

    public void analyzeShoeColorsBySource() {
        System.out.print("Enter the source [Crafting/ Kicks/ Able Sisters/ Labelle/ Nook Miles Shop] from shoes? ");
        String source = scanner.nextLine();

        if (!source.equalsIgnoreCase("Crafting") && !source.equalsIgnoreCase("Kicks") &&
                !source.equalsIgnoreCase("Able Sisters") && !source.equalsIgnoreCase("Labelle") &&
                !source.equalsIgnoreCase("Nook Miles Shop")) {
            System.out.println("Invalid source. Please choose from the provided options.");
            return;
        }

        String sql = "SELECT Color1, COUNT(*) AS totalShoes " +
                "FROM shoes " +
                "WHERE Source = ? " +
                "GROUP BY Color1 " +
                "HAVING COUNT(*) > 1;";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, source);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("Analyzing shoe colors sold by '" + source + "'...");
            System.out.println(String.join("", java.util.Collections.nCopies(35, "-")));
            System.out.printf("%-20s %-10s\n", "Color", "Total Pairs");
            System.out.println(String.join("", java.util.Collections.nCopies(35, "-")));

            while (rs.next()) {
                String color = rs.getString("Color1");
                int totalShoes = rs.getInt("totalShoes");
                System.out.printf("%-20s %-10d\n", color, totalShoes);
            }
        } catch (SQLException e) {
            System.out.println("Database error occurred: " + e.getMessage());
        }
    }

    public void analyzeUmbrellaColorsBySource() {
        System.out.print(
                "Enter a source [Crafting/Nook's Cranny/Bug-Off/Dodo Airlines/Fishing Tourney/Nook Miles Shop] for umbrellas: ");
        String source = scanner.nextLine();

        if (!source.equalsIgnoreCase("Crafting") && !source.equalsIgnoreCase("Nook's Cranny") &&
                !source.equalsIgnoreCase("Bug-Off") && !source.equalsIgnoreCase("Dodo Airlines") &&
                !source.equalsIgnoreCase("Fishing Tourney") && !source.equalsIgnoreCase("Nook Miles Shop")) {
            System.out.println("Invalid source. Please choose from the provided options.");
            return;
        }

        String sql = "WITH SpecificUmbrellas AS (" +
                "  SELECT Color1, Color2, COUNT(*) AS totalUmbrellas " +
                "  FROM Umbrellas " +
                "  WHERE Source = ? AND VillagerEquippable = 'Yes' " +
                "  GROUP BY Color1, Color2" +
                ") " +
                "SELECT Color1, Color2, totalUmbrellas " +
                "FROM SpecificUmbrellas " +
                "ORDER BY totalUmbrellas DESC;";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, source);
            ResultSet rs = pstmt.executeQuery();

            System.out.println("Analyzing umbrella colors distributed by '" + source + "'...");
            System.out.println(String.join("", java.util.Collections.nCopies(50, "-")));
            System.out.printf("%-15s %-15s %-15s\n", "Color 1", "Color 2", "Total Umbrellas");
            System.out.println(String.join("", java.util.Collections.nCopies(50, "-")));

            while (rs.next()) {
                String color1 = rs.getString("Color1");
                String color2 = rs.getString("Color2");
                int totalUmbrellas = rs.getInt("totalUmbrellas");
                System.out.printf("%-15s %-15s %-15d\n", color1, color2, totalUmbrellas);
            }
        } catch (SQLException e) {
            System.out.println("Database error occurred: " + e.getMessage());
        }
    }

    public void findNaturalWoodAndRedPhotos() {
        String sql = "SELECT Name, Variation, Color1, Color2, InternalID, UniqueEntryID " +
                "FROM Photos " +
                "WHERE Variation = 'Natural wood' " +
                "INTERSECT " +
                "SELECT Name, Variation, Color1, Color2, InternalID, UniqueEntryID " +
                "FROM Photos " +
                "WHERE Color1 = 'Red';";

        try (PreparedStatement pstmt = connection.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()) {

            System.out.println("Photos with 'Natural wood' variation and 'Red' color:");
            System.out.println(String.join("", java.util.Collections.nCopies(105, "-")));
            System.out.printf("%-30s %-20s %-10s %-10s %-10s %-20s\n", "Name", "Variation", "Color 1", "Color 2",
                    "Internal ID", "Unique Entry ID");
            System.out.println(String.join("", java.util.Collections.nCopies(105, "-")));

            while (rs.next()) {
                String name = rs.getString("Name");
                String variation = rs.getString("Variation");
                String color1 = rs.getString("Color1");
                String color2 = rs.getString("Color2");
                int internalId = rs.getInt("InternalID");
                String uniqueEntryId = rs.getString("UniqueEntryID");

                System.out.printf("%-30s %-20s %-10s %-10s %-10d %-20s\n", name, variation, color1, color2, internalId,
                        uniqueEntryId);
            }
        } catch (SQLException e) {
            System.out.println("Database error occurred: " + e.getMessage());
        }
    }

    public void countSocksByStyle() {
        String sql = "SELECT Style, COUNT(Name) AS SocksCount FROM socks WHERE SeasonalAvailability = 'All Year' AND VillagerEquippable = 'No' GROUP BY Style";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();

            // Print column headers
            System.out.println(String.join("", java.util.Collections.nCopies(27, "-")));
            System.out.printf("%-20s %s\n", "Style", "Count");
            System.out.println(String.join("", java.util.Collections.nCopies(27, "-"))); // Separator line

            if (!rs.isBeforeFirst()) {
                System.out.println("No data found.");
                return;
            }

            // Print each row data
            while (rs.next()) {
                String style = rs.getString("Style");
                int socksCount = rs.getInt("SocksCount");

                System.out.printf("%-20s %d\n", style, socksCount);
            }
        } catch (SQLException e) {
            System.out.println("Database error occurred: " + e.getMessage());
        }
    }

    public void findHeadwearByColors() {
        List<String> validColors = Arrays.asList("Brown", "Yellow", "Green", "Red", "Blue", "Beige", "Black", "White",
                "Light blue", "Pink", "Orange", "Colorful", "Gray", "Purple");

        System.out.println("Available Colors: " + String.join("/", validColors));

        System.out.print("Enter Color 1: ");
        String color1Input = scanner.nextLine();
        // Validate the color1 input
        if (!validColors.contains(color1Input)) {
            System.out.println("Invalid color entered. Please choose from the available colors.");
            return;
        }

        System.out.print("Enter Color 2: ");
        String color2Input = scanner.nextLine();
        // Validate the color2 input
        if (!validColors.contains(color1Input)) {
            System.out.println("Invalid color entered. Please choose from the available colors.");
            return;
        }

        String sql = "SELECT * FROM headwear WHERE color1= ? AND color2 = ?;";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, color1Input);
            pstmt.setString(2, color2Input);
            ResultSet rs = pstmt.executeQuery();

            if (!rs.isBeforeFirst()) {
                System.out.println(
                        "No headwear found with Color 1 '" + color1Input + "' and Color 2 '" + color2Input + "'.");
                return;
            }

            // Print the header row
            System.out.println("-".repeat(160));
            System.out.printf("%-20s %-20s %-10s %-10s %-15s %-25s %-15s %-15s %-20s%n", "Name", "Variation", "Color1",
                    "Color2", "Source", "Seasonal Availability", "Style", "Label Themes", "Unique Entry ID");
            System.out.println("-".repeat(160));

            // Iterate through the result set and print each row
            while (rs.next()) {
                String name = rs.getString("Name");
                String variation = rs.getString("Variation");
                String color1 = rs.getString("Color1");
                String color2 = rs.getString("Color2");
                String source = rs.getString("Source");
                String seasonalAvailability = rs.getString("SeasonalAvailability");
                String style = rs.getString("Style");
                String labelThemes = rs.getString("LabelThemes");
                String uniqueEntryID = rs.getString("UniqueEntryID");

                System.out.printf("%-20s %-20s %-10s %-10s %-15s %-25s %-15s %-15s %-20s%n", name, variation, color1,
                        color2, source, seasonalAvailability, style, labelThemes, uniqueEntryID);
            }
        } catch (SQLException e) {
            System.out.println("Database error occurred: " + e.getMessage());
        }
    }

    public void filterBottomsByCriteria() {
        List<String> validSeasons = Arrays.asList("All Year", "Spring", "Summer", "Fall", "Winter", "None");
        List<String> validEquipStatus = Arrays.asList("Yes", "No");
        List<String> validStyles = Arrays.asList("Simple", "Cool", "Cute", "Elegant", "Gorgeous", "Active");

        System.out.print("Enter Seasonal Availability [All Year/Spring/Summer/Fall/Winter/None]: ");
        String seasonalAvailability = scanner.nextLine();

        // Validate the seasonal availability input
        if (!validSeasons.contains(seasonalAvailability)) {
            System.out.println("Invalid input for Seasonal Availability.");
            return;
        }

        System.out.print("Is it Villager Equippable? [Yes/No]: ");
        String villagerEquippable = scanner.nextLine();

        // Validate the villager equippable input
        if (!validEquipStatus.contains(villagerEquippable)) {
            System.out.println("Invalid input for Villager Equippable.");
            return;
        }

        System.out.print("Enter Style [Simple/Cool/Cute/Elegant/Gorgeous/Active]: ");
        String style = scanner.nextLine();

        // Validate the style input
        if (!validStyles.contains(style)) {
            System.out.println("Invalid input for Style.");
            return;
        }

        String sql = "SELECT Name, Variation, Color1, Color2, SeasonalAvailability, LabelThemes FROM bottoms " +
                "WHERE SeasonalAvailability = ? AND VillagerEquippable = ? AND Style = ?;";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, seasonalAvailability);
            pstmt.setString(2, villagerEquippable);
            pstmt.setString(3, style);
            ResultSet rs = pstmt.executeQuery();

            // Print header
            System.out.println("-".repeat(120));
            System.out.printf("%-30s %-20s %-10s %-10s %-20s %-30s\n",
                    "Name", "Variation", "Color1", "Color2",
                    "Seasonal Availability", "Label Themes");
            System.out.println("-".repeat(120));

            // Print rows
            while (rs.next()) {
                String name = rs.getString("Name");
                String variation = rs.getString("Variation");
                String color1 = rs.getString("Color1");
                String color2 = rs.getString("Color2");
                seasonalAvailability = rs.getString("SeasonalAvailability");
                String labelThemes = rs.getString("LabelThemes");

                System.out.printf("%-30s %-20s %-10s %-10s %-20s %-30s\n",
                        name, variation, color1, color2,
                        seasonalAvailability, labelThemes);
            }
        } catch (SQLException e) {
            System.out.println("Database error occurred: " + e.getMessage());
        }
    }
}