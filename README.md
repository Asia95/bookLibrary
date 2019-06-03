# bookLibrary

REST API for extracting information about books in provided books.json file.

endpoints:
- /api/book/{ISBN}
- /api/category/{categoryName}/books
- /api/rating

## Building
To build the project use following command:

`mvn clean package`

## Running
After building the application run following command to start it:

`java -jar target/book-library.jar --file.name=book/books.json`

application runs on port 8090
if no `file.name` provided (or the file does not exist) application will run with a books.json file in a book directory

Few words more:
When application finishes loading the book library is created with the use of ApplicationListener interface in the InitLibrary class.
To not present empty properties in the Json response I created BookSeriazlier that extends StdSerializer class from the Jackson library.
