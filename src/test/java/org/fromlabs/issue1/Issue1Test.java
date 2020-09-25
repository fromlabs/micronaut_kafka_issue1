package org.fromlabs.issue1;

import io.micronaut.configuration.kafka.config.AbstractKafkaConfiguration;
import io.micronaut.context.ApplicationContext;
import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

@MicronautTest
class Issue1Test {
  @Test
  void testBookClient() {
    Map<String, Object> config =
        Collections.singletonMap(AbstractKafkaConfiguration.EMBEDDED, true);

    try (ApplicationContext ctx = ApplicationContext.run(config)) {
      BookClient bookClient = ctx.getBean(BookClient.class);
      Book book = new Book();
      book.setTitle("The Stand");
      bookClient.send("Stephen King", book);

      KafkaProducerFactoryMonitor kafkaProducerFactoryMonitor =
          ctx.getBean(KafkaProducerFactoryMonitor.class);

      Assertions.assertEquals(
          "book-client",
          kafkaProducerFactoryMonitor.getConfiguration().getConfig().getProperty("client.id"));
      Assertions.assertEquals(
          "all", kafkaProducerFactoryMonitor.getConfiguration().getConfig().getProperty("acks"));
    }
  }

  @Test
  void testBookSender() {
    Map<String, Object> config =
        Collections.singletonMap(AbstractKafkaConfiguration.EMBEDDED, true);

    try (ApplicationContext ctx = ApplicationContext.run(config)) {
      BookSender bookSender = ctx.getBean(BookSender.class);
      Book book = new Book();
      book.setTitle("The Stand");
      bookSender.send("Stephen King", book);

      KafkaProducerFactoryMonitor kafkaProducerFactoryMonitor =
          ctx.getBean(KafkaProducerFactoryMonitor.class);

      Assertions.assertEquals(
          "book-sender",
          kafkaProducerFactoryMonitor.getConfiguration().getConfig().getProperty("client.id"));
      Assertions.assertEquals(
          "all", kafkaProducerFactoryMonitor.getConfiguration().getConfig().getProperty("acks"));
    }
  }
}
