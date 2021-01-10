package com.adventofcode.day25;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day25 {

  public static void main(String[] args) {
    CryptoModule cardCryptoModule = new CryptoModule(7);
    CryptoModule doorCryptoModule = new CryptoModule(7);

    int cardPublicKey = 19241437;
    int doorPublicKey = 17346587;

    int cardLoopSize = cardCryptoModule.determineLoopSize(cardPublicKey);
    int doorLoopSize = doorCryptoModule.determineLoopSize(doorPublicKey);

    log.info("Card loop size: {}", cardLoopSize);
    log.info("Door loop size: {}", doorLoopSize);

    cardCryptoModule.setLoopSize(cardLoopSize);
    doorCryptoModule.setLoopSize(doorLoopSize);

    int cardEncryptionKey = cardCryptoModule.calculateEncryptionKey(doorPublicKey);
    int doorEncryptionKey = doorCryptoModule.calculateEncryptionKey(cardPublicKey);

    assert (doorEncryptionKey == cardEncryptionKey);
    log.info("Encryption key: {}", cardEncryptionKey);
  }
}
