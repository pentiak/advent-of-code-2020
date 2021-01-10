package com.adventofcode.day25;

public class CryptoModule {

  private final int subjectNumber;
  private int loopSize;

  public CryptoModule(int subjectNumber) {
    this.subjectNumber = subjectNumber;
  }

  public int calculatePublicKey() {
    return transform(subjectNumber);
  }

  private int transform(int sn) {
    long value = 1;
    for (int i = 0; i < loopSize; i++) {
      value = transformIteration(sn, value);
    }
    return (int) value;
  }

  private long transformIteration(int sn, long value) {
    value *= sn;
    value %= 20201227;
    return value;
  }

  public int calculateEncryptionKey(int otherPublicKey) {
    return transform(otherPublicKey);
  }

  public int determineLoopSize(int desiredPublicKey) {
    int calculatedLoopSize = 0;
    long value = 1;
    while (value != desiredPublicKey) {
      value = transformIteration(subjectNumber, value);
      calculatedLoopSize++;
    }
    return calculatedLoopSize;
  }

  public void setLoopSize(int loopSize) {
    this.loopSize = loopSize;
  }
}
