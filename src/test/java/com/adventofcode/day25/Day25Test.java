package com.adventofcode.day25;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day25Test {

  @Test
  void example() {
    CryptoModule cardCryptoModule = new CryptoModule(7);
    CryptoModule doorCryptoModule = new CryptoModule(7);

    int cardPublicKey = 5764801;
    int doorPublicKey = 17807724;

    int cardLoopSize = cardCryptoModule.determineLoopSize(cardPublicKey);
    int doorLoopSize = cardCryptoModule.determineLoopSize(doorPublicKey);

    assertEquals(8, cardLoopSize);
    assertEquals(11, doorLoopSize);

    cardCryptoModule.setLoopSize(cardLoopSize);
    doorCryptoModule.setLoopSize(doorLoopSize);

    int cardEncryptionKey = cardCryptoModule.calculateEncryptionKey(doorPublicKey);
    int doorEncryptionKey = doorCryptoModule.calculateEncryptionKey(cardPublicKey);

    assertEquals(doorEncryptionKey, cardEncryptionKey);
  }
}