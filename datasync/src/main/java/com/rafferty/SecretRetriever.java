package com.rafferty;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClientBuilder;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

public class SecretRetriever {

    private static Region region = Region.US_EAST_1;

    public String requestSecret(String secretName) {
        try{
            SecretsManagerClient secretsClient = SecretsManagerClient.builder()
                    .region(region)
                    .build();

            GetSecretValueRequest valueRequest = GetSecretValueRequest.builder()
                    .secretId(secretName).
                    build();

            GetSecretValueResponse valueResponse = secretsClient.getSecretValue(valueRequest);
            String secret = valueResponse.secretString();
            System.out.println(secret);

            secretsClient.close();

            return secret;

        } catch (Exception e){
            throw e;
        }
    }

}
