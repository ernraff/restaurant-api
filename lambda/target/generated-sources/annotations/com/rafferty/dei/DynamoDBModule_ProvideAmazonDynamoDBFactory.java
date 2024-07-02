package com.rafferty.dei;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class DynamoDBModule_ProvideAmazonDynamoDBFactory implements Factory<AmazonDynamoDB> {
  private final DynamoDBModule module;

  public DynamoDBModule_ProvideAmazonDynamoDBFactory(DynamoDBModule module) {
    this.module = module;
  }

  @Override
  public AmazonDynamoDB get() {
    return provideAmazonDynamoDB(module);
  }

  public static DynamoDBModule_ProvideAmazonDynamoDBFactory create(DynamoDBModule module) {
    return new DynamoDBModule_ProvideAmazonDynamoDBFactory(module);
  }

  public static AmazonDynamoDB provideAmazonDynamoDB(DynamoDBModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.provideAmazonDynamoDB());
  }
}
