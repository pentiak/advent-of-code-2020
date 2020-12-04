package com.adventofcode.day4;

import com.adventofcode.tools.InputLoader;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day4 {

  public static void main(String[] args) {
    DocumentLoader loader = new DocumentLoader(InputLoader.resourcePath("day4/input.txt"));
    DocumentValidator lenientValidator = new LenientPassportValidator();

    long validLenientPassports = loader.loadDocuments().stream().filter(lenientValidator::isValid).count();
    log.info("Valid lenient passports: {}", validLenientPassports);

    DocumentValidator strictValidator = AirportPassportValidatorFactory.newAirportPassportValidator();
    long validStrictPassports = loader.loadDocuments().stream().filter(strictValidator::isValid).count();
    log.info("Valid strict passports: {}", validStrictPassports);

  }

}
