# Tiny Bank

## Pre-requisites
Assert that your machine has at least JAVA 17, Maven, and optionally Postman installed.

## Running the application

To run the application, execute the following command:

```shell
./mvnw spring-boot:run
```

## Endpoints
There examples in the Postman collection located in "./postman/Tiny Bank_requests.postman_collection.json".

### Create User
#### Path
    POST /users/{name}
### Deactivate User
#### Path
    PATCH /users/{name}/deactivate
### Get User
    GET /users/{name}
### Get User Balance
    GET /users/{name}/balance
### User Deposit
#### Path
    PATCH /users/{name}/deposit
In this endpoint a transaction is created with the type DEPOSIT.
### User Withdraw
#### Path
    PATCH /users/{name}/withdraw
In this endpoint a transaction is created with the type WITHDRAW.
### Create Transfer
#### Path
    POST /transfer
In this endpoint a transaction is created with the type TRANSFER.
#### Request Body
```json
{
    "fromUsername": "user1",
    "toUsername": "user2",
    "amount": 100
}
```
### List Transactions
#### Path
    GET /transactions

## Considerations

I didn't distinguish between the User and Account objects. I could have created the Account object, which contained the balance, but according to the exercise, I assume that the relationship between a User and an Account is 1 to 1. So, to simplify things, I only created the User object, which contains the User's name and the balance.

The user ID is the User's name for simplicity and testability. My first approach was to make the ID a UUID, but testing the application took a lot of work. Therefore, it is not possible to have Users with the same name.