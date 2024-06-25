#!/usr/bin/env node
import "source-map-support/register";
import * as cdk from "aws-cdk-lib";
import { ApiGatewayStack } from "../lib/apigateway-stack";
import { DataSyncStack } from "../lib/datasync-stack";
import { DynamodbStack } from "../lib/dynamodb-stack";
import { OpensearchStack } from "../lib/opensearch-stack";

const app = new cdk.App();
new ApiGatewayStack(app, "ApiGatewayStack", {});
new DataSyncStack(app, "DataSyncStack", {});
new DynamodbStack(app, "DynamodbStack", {});
new OpensearchStack(app, "OpensearchStck", {});
