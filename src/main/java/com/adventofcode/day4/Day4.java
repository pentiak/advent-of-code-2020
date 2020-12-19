package com.adventofcode.day4;

import com.adventofcode.utils.InputUtils;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class Day4 {

  public static void main(String[] args) {
    List<Document> documents = InputUtils.inputSplitByBlankLine("day4/input.txt")
        .map(Document::new)
        .collect(Collectors.toList());

    DocumentValidator lenientValidator = new LenientPassportValidator();
    long validLenientPassports = documents.stream().filter(lenientValidator::isValid).count();
    log.info("Valid lenient passports: {}", validLenientPassports);

    DocumentValidator strictValidator = AirportPassportValidatorFactory.newAirportPassportValidator();
    long validStrictPassports = documents.stream().filter(strictValidator::isValid).count();
    log.info("Valid strict passports: {}", validStrictPassports);

  }

}
