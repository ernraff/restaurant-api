# Restaurants API Using AWS CDK and OpenAPI

The purpose of this project is to create an API that allows users to query restaurants by cuisine, restaurant ID, or restaurant name and receive detailed restaurant data in return. The project was developed using the AWS CDK. The API is defined by AWS CDK and implemented with AWS Lambda for scalable and efficient execution. Restaurant data is pulled using Yelp's API and stored in DynamoDB and ElasticSearch for efficient querying and retrieval.

![RestaurantApi drawio (1)](https://github.com/ernraff/restaurant-api/assets/103540977/d4e7b283-d77d-4bdf-bc5b-75b5ca89bc78)


## Restaurant API

Developed using API Gateway using Lambda proxy integration. Currently has working get endpoint. Plan is to add put functionality for modification of restaurant data.

## RestaurantHandler

Lambda proxy that queries DynamoDB for restaurant data according to query parameters. For query by id, queries DynamoDB table by the table's sort key. For query by cuisine, queries GSI (cuisineIndex).

## DataSync

Lambda function which collects restaurant data by cuisine from the Yelp Fusion API and adds to a DynamoDB Table.

## GET endpoint

Allows users to query restaurant data.

Paths:
/restaurant-by-cuisine/{cuisine} - queries restaurant data by cuisine. returns data for five restaurants to the user

/restaurant-by-id/{id} - queries restaurant data based on the restaurant's unique id |

## PUT endpoint

Once GET endpoint is working as desired, will work on PUT endpoint with lambda proxy integration. This will allow changes to be made to restaurant data. Changes will have to be made to both the Opensearch doc as well as the DynamoDB table.

When the API's basic functionality is up and running, I plan to add Cognito authentication and Dagger dependency injection to my API.
