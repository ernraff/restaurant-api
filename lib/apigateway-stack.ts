import * as cdk from "aws-cdk-lib";
import { Construct } from "constructs";
import * as lambda from "aws-cdk-lib/aws-lambda";
import * as path from "path";
import { OpenApiGatewayToLambda } from "@aws-solutions-constructs/aws-openapigateway-lambda";
import { Asset } from "aws-cdk-lib/aws-s3-assets";
import * as iam from "aws-cdk-lib/aws-iam";
export class ApiGatewayStack extends cdk.Stack {
  constructor(scope: Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    //Define the API Gateway REST API using OpenAPI definition
    const apiDefinitionAsset = new Asset(this, "ApiDefinitionAsset", {
      path: path.join(__dirname, "openapi.yaml"),
    });

    // Define the IAM role for the Lambda function
    const lambdaExecutionRole = new iam.Role(this, "LambdaExecutionRole", {
      assumedBy: new iam.ServicePrincipal("lambda.amazonaws.com"),
      managedPolicies: [
        iam.ManagedPolicy.fromAwsManagedPolicyName(
          "service-role/AWSLambdaBasicExecutionRole"
        ),
        iam.ManagedPolicy.fromAwsManagedPolicyName("AmazonDynamodbFullAccess"),
      ],
    });

    new OpenApiGatewayToLambda(this, "OpenApiGatewayToLambda", {
      apiDefinitionAsset,
      apiIntegrations: [
        {
          id: "RestaurantHandler",
          lambdaFunctionProps: {
            runtime: lambda.Runtime.JAVA_17,
            handler: "com.rafferty.handler.RestaurantHandler::handleRequest",
            code: lambda.Code.fromAsset(
              path.join(
                __dirname,
                "..",
                "lambda/target/lambda-1.0-SNAPSHOT.jar"
              )
            ),
            role: lambdaExecutionRole,
            timeout: cdk.Duration.minutes(3),
          },
        },
      ],
    });
  }
}
