package com.adventofcode.day21;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day21 {

  public static void main(String[] args) {
    ShoppingList list = new ShoppingList();
    list.loadProducts("day21/input.txt");
    log.info(list.countIngredientsWithoutAllergens());
    log.info(list.canonicalDangerousIngredientList());
  }
}
