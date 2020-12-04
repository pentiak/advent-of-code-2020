package com.adventofcode.day4;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public class ConfigurableValidator implements DocumentValidator {

  private final List<Predicate<Document>> requirements;

  public ConfigurableValidator(List<Predicate<Document>> requirements) {
    this.requirements = Collections.unmodifiableList(requirements);
  }

  @Override
  public boolean isValid(Document document) {
    return requirements.stream().allMatch(p -> p.test(document));
  }
}
