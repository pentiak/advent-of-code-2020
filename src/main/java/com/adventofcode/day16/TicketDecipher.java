package com.adventofcode.day16;

import com.adventofcode.tools.InputLoader;
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;

import java.util.*;
import java.util.regex.Pattern;

@Log4j2
public class TicketDecipher {
  private static final Pattern VALIDATOR_DEFINITION = Pattern.compile(": ");
  private static final Pattern RANGES_SEPARATOR = Pattern.compile(" or ");

  private final Map<String, IntValidator> fieldValidators = new HashMap<>();
  private final List<Ticket> nearbyTickets = new ArrayList<>();
  private final List<Set<String>> possibleFieldNamesList = new ArrayList<>();
  private Ticket myTicket;
  private InputSection inputSection;

  public TicketDecipher(String inputFile) {
    inputSection = InputSection.VALIDATORS;

    InputLoader.inputLines(inputFile).forEach(this::parseLine);

    initPossibleFieldNamesList();
  }

  private void initPossibleFieldNamesList() {
    for (int i = 0; i < myTicket.getValues().size(); i++) {
      possibleFieldNamesList.add(new HashSet<>(fieldValidators.keySet()));
    }
  }

  private void parseLine(String line) {
    switch (inputSection) {
      case VALIDATORS:
        parseValidator(line);
        break;
      case MY_TICKET:
        parseMyTicket(line);
        break;
      case NEARBY_TICKETS:
        parseNearbyTicket(line);
        break;
      default:
        break;
    }
  }

  private void parseNearbyTicket(String line) {
    if (line.contains("nearby tickets:")) {
      return;
    }

    IntList values = parseTicketValues(line);
    Ticket nearbyTicket = new Ticket(fieldValidators.keySet(), values);
    nearbyTickets.add(nearbyTicket);
  }

  private void parseMyTicket(String line) {
    if (line.contains("your ticket:")) {
      return;
    }

    if (Strings.isBlank(line)) {
      inputSection = InputSection.NEARBY_TICKETS;
      return;
    }

    IntList values = parseTicketValues(line);
    myTicket = new Ticket(fieldValidators.keySet(), values);

  }

  private IntList parseTicketValues(String line) {
    return Arrays.stream(line.split(","))
        .mapToInt(Integer::parseInt)
        .collect(IntArrayList::new, IntArrayList::add, IntArrayList::addAll);
  }

  private void parseValidator(String line) {
    if (Strings.isBlank(line)) {
      inputSection = InputSection.MY_TICKET;
      return;
    }

    String[] definitionSplit = VALIDATOR_DEFINITION.split(line);
    String fieldName = definitionSplit[0];
    IntValidator validator = createValidator(definitionSplit[1]);
    fieldValidators.put(fieldName, validator);

  }

  private IntValidator createValidator(String rangeLine) {
    IntValidator validator = new IntValidator();

    String[] rangesSplit = RANGES_SEPARATOR.split(rangeLine);
    for (String rangeString : rangesSplit) {
      String[] valueSplit = rangeString.split("-");
      IntRange range = IntRange.of(Integer.parseInt(valueSplit[0]), Integer.parseInt(valueSplit[1]));
      validator.addRange(range);
    }

    return validator;
  }

  public long analyzeTickets() {
    long ticketErrorRate = 0;
    Set<Map.Entry<String, IntValidator>> validators = fieldValidators.entrySet();
    for (Ticket ticket : nearbyTickets) {
      IntList values = ticket.getValues();
      List<Set<String>> possibleTicketFieldNames = new ArrayList<>();
      boolean ticketValid = true;
      for (int i = 0; i < values.size(); i++) {
        int value = values.getInt(i);
        Set<String> possibleFieldNames = new HashSet<>();

        boolean fitInAnyRange = false;
        for (Map.Entry<String, IntValidator> validatorEntry : validators) {
          boolean inRange = validatorEntry.getValue().test(value);
          if (inRange) {
            possibleFieldNames.add(validatorEntry.getKey());
            fitInAnyRange = true;
          }
        }

        if (!fitInAnyRange) {
          ticketErrorRate += value;
          ticketValid = false;
        } else {
          possibleTicketFieldNames.add(possibleFieldNames);
        }
      }
      if (ticketValid) {
        for (int i = 0; i < possibleFieldNamesList.size(); i++) {
          possibleFieldNamesList.set(i,
              Sets.intersection(possibleFieldNamesList.get(i), possibleTicketFieldNames.get(i)));
        }
      }

    }

    return ticketErrorRate;
  }

  private Object2IntMap<Set<String>> determineFieldNames() {
    Object2IntMap<Set<String>> indexedFieldMap = new Object2IntOpenHashMap<>(possibleFieldNamesList.size());

    for (int i = 0; i < possibleFieldNamesList.size(); i++) {
      HashSet<String> mutableSet = new HashSet<>(possibleFieldNamesList.get(i));
      possibleFieldNamesList.set(i, mutableSet);
      indexedFieldMap.put(mutableSet, i);
    }

    possibleFieldNamesList.sort(Comparator.comparingInt(Set::size));

    Set<String> assignedFieldNames = new HashSet<>();
    for (Set<String> possibleNames : possibleFieldNamesList) {
      Set<String> intersection = new HashSet<>(Sets.intersection(possibleNames, assignedFieldNames));
      possibleNames.removeAll(intersection);
      assignedFieldNames.addAll(possibleNames);
    }

    return indexedFieldMap;
  }

  public long calculateDepartureValue() {
    Object2IntMap<Set<String>> fieldNamesMap = determineFieldNames();
    long departureValue = 1;
    for (Object2IntMap.Entry<Set<String>> entry : fieldNamesMap.object2IntEntrySet()) {
      String fieldName = new ArrayList<>(entry.getKey()).get(0);
      if (fieldName.contains("departure")) {
        departureValue *= myTicket.getValues().getInt(entry.getIntValue());
      }
    }
    return departureValue;
  }

}
