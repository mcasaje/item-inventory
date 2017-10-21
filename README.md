# item-library-demo

This is a demo project that I created as part of my interview process at Ensemble Systems.

## Dependencies

- SBT 0.13.8+
- Java 8 (SBT doesn't work with Java 9 when developed)
- MySQL 5.6+

## Setup

1. Run your MySQL database, matching up the port and user credentials found in the application.conf and persistence.xml.
   - Note that persistence.xml and application.conf configs should be merged in future.
2. Make sure your MySQL database is running and to create a new database named "item_library".
3. Execute 'sbt run' on the application root.
4. Upon your first run of the app, you will be prompted to apply the DB evolution scripts.
   Click the apply button to set up the database.
