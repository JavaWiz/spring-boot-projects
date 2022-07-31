### book-service - A spring boot rest project

A Spring Boot REST style web service to handle CRUD operations from a H2 In-memory database.

A REST controller to create the following REST API endpoints :

HTTP 	Method	URI			Description
GET			/books			List all books.
POST		/books			Save a book.
GET			/books/{id}		Find a book where id = {:id}.
PUT			/books/{id}		Update a book where id = {:id}, or save it.
PATCH		/books/{id}		Update a single field where id = {:id}.
DELETE		/books/{id}		Delete a book where id = {:id}.

 
### Test
Start Spring Boot application, and test the REST API endpoints with curl command.
Find all books

```
curl -v localhost:8080/books
```

Find One - /books/1

```
curl -v localhost:8080/books/1
```

Test 404 - /books/5

```
curl -v localhost:8080/books/5
```

Save - /books -d {json}

```
curl -v -X POST localhost:8080/books -H "Content-type:application/json" -d "{\"name\":\"Spring REST tutorials\",\"author\":\"Javawiz\",\"price\":\"9.99\"}"
```

Test Update - /books/4 -d {json}

```
curl -v -X PUT localhost:8080/books/4 -H "Content-type:application/json" -d "{\"name\":\"Spring Forever\",\"author\":\"pivotal\",\"price\":\"9.99\"}"
```

Test Update a 'author' field - /books/4 -d {json}

```
curl -v -X PATCH localhost:8080/books/4 -H "Content-type:application/json" -d "{\"author\":\"oracle\"}"
```

Test delete - /books/4

```
curl -v -X DELETE localhost:8080/books/4
```
