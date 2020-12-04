package com.adventofcode.day4;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document {

  private static final Pattern FIELD_PATTERN = Pattern.compile("(\\w+):([\\w#]+)");

  private final Map<String, String> fields;

  public Document(String input) {
    Matcher matcher = FIELD_PATTERN.matcher(input);
    Map<String, String> inputFields = new HashMap<>();
    while (matcher.find()) {
      inputFields.put(matcher.group(1), matcher.group(2));
    }
    this.fields = Collections.unmodifiableMap(inputFields);
  }

  public boolean isFieldPresent(String fieldName) {
    return fields.containsKey(fieldName);
  }

  public String getValue(String fieldName) {
    return fields.get(fieldName);
  }
}
