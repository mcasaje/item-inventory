# Item Library (Demo)

A customizable library that is able to sort objects or 'items' based on custom attributes.
You start by defining an 'item' (eg. Book, Movie, Video Game, Recipe)
and item 'attributes' (eg. Author, Rating, Genre).
Once you've defined an item, you can start adding instances of the item to its library,
being able to sort and filter through all the items based on attributes and tags respectively.

## Purpose

This was a demo project that I created as part of my interview process at Ensemble Systems
with the purpose of demonstrating my skill and knowledge at the time.

Its initial implementation was completed in 4 out of the allotted 7 days
and deployed onto a Linode server with a MySQL database.

I've tagged the initial version v0.1.

**Note:** this is not meant for production use and has no guarantees whatsoever.

## Features

- User account creation
- Login
- Item library setup
  - Define an item (eg. book, movie, video game, recipe)
  - Define an item's attributes
  - Define possible tags for an item
- Add an item
- Delete an item
- Tag an item
- Sort by item attribute (ascending and descending)
- Filter items by tags

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
