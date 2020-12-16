package com.adventofcode.day16;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day16 {

  public static void main(String[] args) {
    TicketDecipher decipher = new TicketDecipher("day16/input.txt");
    long errorRate = decipher.analyzeTickets();
    log.info("Error rate: {}", errorRate);
    log.info("Departure value: {}", decipher.calculateDepartureValue());
  }
}
