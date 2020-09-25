[Injecting kafka producer beans](https://micronaut-projects.github.io/micronaut-kafka/latest/guide/#kafkaClientScope)
using the `@KafkaClient` annotation works but the configuration on the annotation is not used. 

### Steps to Reproduce

1. Create BookSender class and inject kafka producer bean
2. Add acks attribute to @KafkaClient("book-producer", acks = KafkaClient.Acknowledge.ALL)
3. Create a test with embedded kafka
4. Collect producer config logs

### Expected Behaviour

Producer acks property should be -1 (ALL ack).

### Actual Behaviour

Producer acks property is 1 as the default configuration.

```
18:58:03.174 [main] INFO  o.a.k.c.producer.ProducerConfig - ProducerConfig values: 
	acks = 1
	batch.size = 16384
	bootstrap.servers = [localhost:9092]
    
```

### Environment Information

- **Operating System**: macOS
- **Micronaut Version:** 2.0.3
- **Micronaut Kafka Version:** 3.0.0
- **JDK Version:** 11.0.1

### Example Application

- Run test https://github.com/fromlabs/micronaut_kafka_issue1
