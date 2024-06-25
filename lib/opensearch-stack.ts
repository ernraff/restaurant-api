import * as cdk from "aws-cdk-lib";
import { Construct } from "constructs";
import { Domain, EngineVersion } from "aws-cdk-lib/aws-opensearchservice";
import { EbsDeviceVolumeType } from "aws-cdk-lib/aws-ec2";
import * as secretsmanager from "aws-cdk-lib/aws-secretsmanager";
import * as iam from "aws-cdk-lib/aws-iam";
import { AWS_CUSTOM_RESOURCE_LATEST_SDK_DEFAULT } from "aws-cdk-lib/cx-api";
export class OpensearchStack extends cdk.Stack {
  constructor(scope: Construct, id: string, props?: cdk.StackProps) {
    super(scope, id, props);

    const opensearchSecret = secretsmanager.Secret.fromSecretNameV2(
      this,
      "MyOpensearchSecret",
      "RestaurantSearchMasterUserPassword"
    );

    //define opensearch domain
    const openSearchDomain = new Domain(this, "MyOpenSearchDomain", {
      version: EngineVersion.ELASTICSEARCH_7_10,
      capacity: {
        multiAzWithStandbyEnabled: false,
        dataNodeInstanceType: "t3.small.search",
        dataNodes: 3,
      },
      ebs: {
        enabled: true,
        volumeType: EbsDeviceVolumeType.GENERAL_PURPOSE_SSD,
        volumeSize: 100,
      },
      encryptionAtRest: {
        enabled: true,
      },
      nodeToNodeEncryption: true,
      domainName: "restaurant-search-domain",
      fineGrainedAccessControl: {
        masterUserName: "erin",
        masterUserPassword: opensearchSecret.secretValue,
      },
      logging: {
        slowSearchLogEnabled: true,
        appLogEnabled: true,
        slowIndexLogEnabled: true,
      },
      automatedSnapshotStartHour: 0,
      enforceHttps: true,
      accessPolicies: [
        new iam.PolicyStatement({
          actions: ["es:*"],
          effect: iam.Effect.ALLOW,
          principals: [new iam.AccountPrincipal("604242335225")],
          resources: [
            "arn:aws:es:us-east-1:604242335225:domain/restaurant-search-domain/*",
          ],
        }),
        new iam.PolicyStatement({
          actions: ["es:*"],
          effect: iam.Effect.ALLOW,
          principals: [
            new iam.ArnPrincipal(
              "arn:aws:iam::604242335225:role/DataSyncStack-LambdaExecutionRoleD5C26073-6Y4rL6dmpUcj"
            ),
          ],
          resources: [
            "arn:aws:es:us-east-1:604242335225:domain/restaurant-search-domain/*",
          ],
        }),
        new iam.PolicyStatement({
          actions: ["es:*"],
          sid: "",
          effect: iam.Effect.ALLOW,
          principals: [new iam.AnyPrincipal()],
          resources: [
            "arn:aws:es:us-east-1:604242335225:domain/restaurant-search-domain/*",
          ],
          conditions: {
            IpAddress: {
              "aws:SourceIp": "0.0.0.0/0",
            },
          },
        }),
      ],
    });
  }
}
