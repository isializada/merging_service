# Merging service


#### Problem: Fetch asynchronous data from an API

There are 2 enpoints which are responsing user data and user comments by userID.
The merging service gathers these data from the endpoints asynchronously by ID and merges the responses.

### Endpoint

[GET] {host}:8085/merge/{userID}


[RESPONSE] JSON of user data and comments


### Service developed on Java, Spring boot and Unit and Entpoint tests are implemented.

