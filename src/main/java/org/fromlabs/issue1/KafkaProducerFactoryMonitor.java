package org.fromlabs.issue1;

import io.micronaut.configuration.kafka.KafkaProducerFactory;
import io.micronaut.configuration.kafka.config.AbstractKafkaProducerConfiguration;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Parameter;
import io.micronaut.context.annotation.Primary;
import io.micronaut.context.annotation.Prototype;
import org.apache.kafka.clients.producer.KafkaProducer;

@Factory
public class KafkaProducerFactoryMonitor extends KafkaProducerFactory {

  private AbstractKafkaProducerConfiguration<?, ?> configuration;

  @Primary
  @Prototype
  @Override
  public <K, V> KafkaProducer<K, V> createProducer(
      @Parameter AbstractKafkaProducerConfiguration<K, V> producerConfiguration) {
    this.configuration = producerConfiguration;

    return super.createProducer(producerConfiguration);
  }

  public AbstractKafkaProducerConfiguration<?, ?> getConfiguration() {
    return this.configuration;
  }
}
