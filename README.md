# watches

Expose one API

GET /users/{userId}/matches

and returns the payload desired.

LIMITAITONS AND ASSUMPTIONS:
- The implementation is using an InMemoryRepo, If I would have to choose I would use a NoSql DB that gives me more flexibility.
- No Endpoint to add licenses to the user. Sorry I did not have much time but it can be test from the tests.
- I assumed that the summaryType param is comming from the API.
