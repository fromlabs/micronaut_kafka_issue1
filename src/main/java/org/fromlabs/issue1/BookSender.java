package org.fromlabs.issue1;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import javax.inject.Singleton;
import java.util.concurrent.Future;

@Singleton
public class BookSender {

  private final Producer<String, Book> kafkaProducer;

  public BookSender(
      @KafkaClient(value = "book-sender", acks = KafkaClient.Acknowledge.ALL)
          Producer<String, Book> kafkaProducer) {
    this.kafkaProducer = kafkaProducer;
  }

  public Future<RecordMetadata> send(String author, Book book) {
    return this.kafkaProducer.send(new ProducerRecord<>("books", author, book));
  }
}
