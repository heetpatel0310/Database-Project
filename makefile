# Assuming the MS SQL Server JDBC driver is named mssql-jdbc-12.6.1.jre11.jar and located in the same directory.
JDBC_DRIVER := mssql-jdbc-12.6.1.jre11.jar
CLASSPATH := .:$(JDBC_DRIVER)

# Java compiler
JAVAC := javac
JAVA := java

# Source files
SOURCES := SetupConnection.java PopulateDB.java ACNHDataBase.java

# The main class to run for database population and the application
MAIN_POPULATE_DB := PopulateDB
MAIN_APP := ACNHDataBase

# Compilation target for all classes
build: $(SOURCES)
	$(JAVAC) $(SOURCES)

# Target to run the database population
runPopulateDB: build
	$(JAVA) -cp $(CLASSPATH) $(MAIN_POPULATE_DB)

# Target to run the application
runACNHDataBase: build
	$(JAVA) -cp $(CLASSPATH) $(MAIN_APP)

# Clean up compiled .class files
clean:
	rm -f *.class
