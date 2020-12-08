package com.adventofcode.day8;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class GameConsole {

  private final Processor processor;
  private final SoftwareLoader softwareLoader;
  private Software software;

  public GameConsole(Processor processor, SoftwareLoader softwareLoader) {
    this.processor = processor;
    this.softwareLoader = softwareLoader;
  }

  public void reloadSoftware() {
    this.software = softwareLoader.load();
  }

  public void run() {
    if (software == null) {
      reloadSoftware();
    }
    processor.run(software);
  }
}
