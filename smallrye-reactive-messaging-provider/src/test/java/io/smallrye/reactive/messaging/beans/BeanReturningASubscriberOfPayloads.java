package io.smallrye.reactive.messaging.beans;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.streams.ReactiveStreams;
import org.reactivestreams.Subscriber;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BeanReturningASubscriberOfPayloads {

  private List<String> list = new ArrayList<>();


  @Incoming("count")
  public Subscriber<String> create() {
    return ReactiveStreams.<String>builder().forEach(m -> {
      System.out.println("Receiving " + m);
      list.add(m);
    })
      .build();
  }

  public List<String> payloads() {
    return list;
  }

}