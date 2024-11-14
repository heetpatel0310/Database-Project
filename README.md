# Group17-Project-3380

---

# Animal Crossing: New Horizons Catalog Interface

---

## Description

This project introduces a Java-based command-line interface (CLI) designed to interact with the "Animal Crossing: New Horizons Catalog" database. Created with a focus on user-friendliness and functionality, this interface allows users, especially analysts, to explore and manage game data effectively without the need for SQL knowledge. Designed to run on the aviary server, this CLI requires no additional installations, making it readily accessible for all users.

## Key Features

- **Secure and Simplified Data Interaction:** Launches with a secure connection setup to the database, offering a variety of user commands for data exploration.
- **Intuitive User Commands:** Includes simple-to-use commands for querying the database, accompanied by help (`h`) and exit options.
- **SQL Injection Prevention:** Utilizes user input options to prevent SQL injection, ensuring database security.
- **Reset Functionality:** Allows users to reset the dataset to its original state, ensuring data integrity.
- **Error Handling and Processing Indicators:** Provides clear error messages and processing indicators for a seamless user experience.
- **Structured Data Presentation:** Displays query results in easily readable tables, enhancing data analysis.

## Usage

Upon initiating the CLI, users are greeted with a welcome message outlining all available functionalities. Users can execute various queries, such as finding unique game entities or exploring villager traits, by simply entering the corresponding command number. The CLI offers clear instructions and instant feedback for each action, along with a comprehensive help guide for further assistance.

### Example Commands

- `2` - Find unique game entities
- `d` - Drop all tables
- `p` - Repopulate all data
- `h` - Display help information
- `e` - Quit the interface

## Contributors

1. Yugam Amish Patel
2. Heetkumar Patel
3. Dhvani Thakkar

## Features

- Command line interface (CLI) for user interaction
- Multiple queries supported

## Requirements

- Java 8 or higher
- SQL Server

## How to use

To use the "Animal Crossing: New Horizons Catalog" CLI, follow these steps:

1. **Setup:** Ensure your environment is ready with Java and the necessary database connections configured.
2. **Login Details:** Please check authentication file(auth.cfg) for login details.
3. **Clone the Repository:** Obtain the project files by cloning the repository to your local machine.

4. **Compile and Populate the Database:**

   - Open a terminal and navigate to the project directory.
   - Use the `make` command to compile the Java files.
   - Populate the database by running `make runPopulateDB` or there is an option in interface to drop and repopulate all data. This step initializes the database with the required data for the application.

5. **Launch the Interface:**

   - To start the CLI and interact with the database, execute `make runACNHDataBase`.
   - The CLI will provide a menu with various options. Use the numbers or commands as prompted to explore the database.

6. **Interact with the CLI:**

   - Follow the on-screen prompts to navigate through the interface. You can view items, villagers' details, and more. The interface includes a help section (`h`) for guidance on available commands.

7. **Exiting:**
   - To exit the CLI at any time, type `e`.

Remember, this CLI is designed to be intuitive and user-friendly, catering to users with varying levels of technical expertise. Enjoy exploring the vast world of Animal Crossing: New Horizons through this interactive tool!

## Data Source

The dataset used for this project was obtained from Kaggle. Our dataset is called Animal Crossing: New Horizons Catalog Dataset, which is based on a Nintendo game. This dataset offers a meticulous snapshot of in-game items and characters. It includes details on villagers, clothing, songs, and various other categories, each with numerous attributes reflecting the intricate mechanics of the game.

https://www.kaggle.com/datasets/jessicali9530/animal-crossing-new-horizons-nookplaza-dataset

## Disclaimer

## This project is for educational purposes only and is not intended for commercial use.
