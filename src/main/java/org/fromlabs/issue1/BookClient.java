package org.fromlabs.issue1;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.KafkaKey;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient(id = "book-client", acks = KafkaClient.Acknowledge.ALL)
public interface BookClient {

  @Topic("my-books")
  void send(@KafkaKey String name, Book book);
}
