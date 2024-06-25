import * as cdk from "aws-cdk-lib";
import { Construct } from "constructs";
import * as lambda from "aws-cdk-lib/aws-lambda";
import * as iam from "aws-cdk-lib/aws-iam";
import * as path from "path";

export class DataSyncStack extends cdk.Stack {
  constructor(scope: Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    //define iam role for lambda function - grant dynamodb and secrets manager access
    const lambdaExecutionRole = new iam.Role(this, "LambdaExecutionRole", {
      assumedBy: new iam.ServicePrincipal("lambda.amazonaws.com"),
      managedPolicies: [
        iam.ManagedPolicy.fromAwsManagedPolicyName(
          "service-role/AWSLambdaBasicExecutionRole"
        ),
        iam.ManagedPolicy.fromAwsManagedPolicyName("SecretsManagerReadWrite"),
        iam.ManagedPolicy.fromAwsManagedPolicyName("AmazonDynamodbFullAccess"),
      ],
    });

    //define our datasync lambda function
    const lambda2 = new lambda.Function(this, "DataSync", {
      runtime: lambda.Runtime.JAVA_17,
      handler: "com.rafferty.DataHandler::handleRequest",
      code: lambda.Code.fromAsset(
        path.join(__dirname, "..", "datasync/target/lambda2-1.0-SNAPSHOT.jar")
      ),
      timeout: cdk.Duration.minutes(3),
      role: lambdaExecutionRole,
      memorySize: 512,
    });
  }
}
