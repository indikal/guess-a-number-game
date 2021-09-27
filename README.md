## Number Guessing Game

A simple Spring Boot and Angular application to demonstrate the Orchestration in Kubernetes.

* A user first register to play the game.
** User has to give Username, First name and Last name to register
* Upon registering the user is assigned 1000 credits to play the game.
* Every win will award the user with 10 credits to account.
* Every loss will cost him 10 points from his account.
* User can play until his account has enough money to pay.

##  Bank API
This will represent a dummy bank. All accounts were held in an in-memory collection and should be moved into a database later.

Has following public end-points
* [GET] /bank/api/v1/user/{id} - search a user by given ID
* [POST] /bank/api/v1/create - create a new user
* [PUT] /bank/api/v1/user/{id}/credit - credit the user account for given amount
* [PUT] /bank/api/v1/user/{id}/debit - debit the user account for given amount

## Random Number API
This API will generate a random number between 0 and 10

Has following public end-points
* [GET] /randomnumber/api/v1/generate - generates a random number from 0 to 10

## Number Game API
This is the main service handling the game. A user need to be registered first. After that user has to login to play the game.
Each successful win will award the user 10 credits but any loss will cost him a loss of 10 points.

Has following public end-points
* [GET] /numbergame/api/v1/register - register a new user
* [GET] /numbergame/api/v1/user - checks whether the user is already registered.
* [GET] /numbergame/api/v1/play - play the game by entering any number between 0 and 10.
