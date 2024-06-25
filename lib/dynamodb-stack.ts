import * as cdk from "aws-cdk-lib";
import { Construct } from "constructs";
import * as dynamodb from "aws-cdk-lib/aws-dynamodb";
import { ALL } from "dns";

export class DynamodbStack extends cdk.Stack {
  constructor(scope: Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    //define dynamodb table
    const table = new dynamodb.Table(this, "RestaurantData", {
      partitionKey: {
        name: "business",
        type: dynamodb.AttributeType.STRING,
      },
      sortKey: {
        name: "id",
        type: dynamodb.AttributeType.STRING,
      },
      tableName: "restaurant-data",
    });

    table.addGlobalSecondaryIndex({
      indexName: "cuisineIndex",
      partitionKey: { name: "cuisine", type: dynamodb.AttributeType.STRING },
      projectionType: dynamodb.ProjectionType.ALL,
    });
  }
}
