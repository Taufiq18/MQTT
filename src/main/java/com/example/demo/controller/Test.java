package com.example.demo.controller;

import org.eclipse.paho.mqttv5.client.MqttClient;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("mqtt")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Test {

  private final MqttClient client;

  @PostMapping
  public ResponseEntity<String> test() {
    try {
      client.publish("test", new MqttMessage("Test".getBytes()));
      return ResponseEntity.ok("Message published successfully");
    } catch (MqttException e) {
      // Log error 
      System.err.println("Error publishing message: " + e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to publish message");
    }
  }
}
