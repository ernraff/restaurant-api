package com.rafferty.dei;

import com.fasterxml.jackson.databind.ObjectMapper;
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
public final class ObjectMapperModule_ObjectMapperFactory implements Factory<ObjectMapper> {
  private final ObjectMapperModule module;

  public ObjectMapperModule_ObjectMapperFactory(ObjectMapperModule module) {
    this.module = module;
  }

  @Override
  public ObjectMapper get() {
    return objectMapper(module);
  }

  public static ObjectMapperModule_ObjectMapperFactory create(ObjectMapperModule module) {
    return new ObjectMapperModule_ObjectMapperFactory(module);
  }

  public static ObjectMapper objectMapper(ObjectMapperModule instance) {
    return Preconditions.checkNotNullFromProvides(instance.objectMapper());
  }
}
