package com.adventofcode.day21;

import com.adventofcode.utils.InputUtils;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import lombok.extern.log4j.Log4j2;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Log4j2
public class ShoppingList {
  private static final Pattern FOOD = Pattern.compile("(((\\w+) )+)\\(contains (((\\w+)(, )?)+)\\)");

  private final Map<String, Set<String>> allergenProducts = new HashMap<>();
  private final Object2IntMap<String> productCounts = new Object2IntOpenHashMap<>();

  public void loadProducts(String inputFile) {
    InputUtils.inputLines(inputFile).forEach(this::processComposition);
    log.info(allergenProducts);
  }

  public int countIngredientsWithoutAllergens() {
    Set<String> productsWithAllergens = allergenProducts.values()
        .stream()
        .flatMap(Set::stream)
        .collect(Collectors.toSet());

    return productCounts.object2IntEntrySet()
        .stream()
        .filter(e -> !productsWithAllergens.contains(e.getKey()))
        .mapToInt(Object2IntMap.Entry::getIntValue)
        .sum();
  }

  private void processComposition(String line) {
    Matcher matcher = FOOD.matcher(line);

    Set<String> solvedAllergens = new HashSet<>();
    Queue<String> unambiguousAllergens = new LinkedList<>();

    if (matcher.matches()) {
      Set<String> ingredients = new HashSet<>(Arrays.asList(matcher.group(1).split(" ")));
      Set<String> allergens = new HashSet<>(Arrays.asList(matcher.group(4).split(", ")));

      countProducts(ingredients);

      for (String allergen : allergens) {
        Set<String> products = allergenProducts.get(allergen);
        if (products == null) {
          products = new HashSet<>(ingredients);
          allergenProducts.put(allergen, products);
        } else {
          products.retainAll(ingredients);
        }

        products.removeAll(solvedAllergens);

        if (products.size() == 1) {
          unambiguousAllergens.add(products.iterator().next());
        }
      }
    }

    String unambiguousAllergen;
    while ((unambiguousAllergen = unambiguousAllergens.poll()) != null) {

      List<Set<String>> productsSets = allergenProducts.values()
          .stream()
          .filter(s -> s.size() > 1)
          .collect(Collectors.toList());

      for (Set<String> products : productsSets) {
        products.remove(unambiguousAllergen);
        if (products.size() == 1) {
          unambiguousAllergens.add(products.iterator().next());
        }
      }
      solvedAllergens.add(unambiguousAllergen);
    }
  }

  private void countProducts(Set<String> ingredients) {
    for (String ingredient : ingredients) {
      productCounts.put(ingredient, productCounts.getInt(ingredient) + 1);
    }
  }

  public String canonicalDangerousIngredientList() {
    return allergenProducts.entrySet()
        .stream()
        .sorted(Map.Entry.comparingByKey())
        .map(Map.Entry::getValue)
        .flatMap(Set::stream)
        .collect(Collectors.joining(","));
  }
}
