package com.adventofcode.day22;

import com.adventofcode.utils.InputUtils;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeckLoader {

  private static final Pattern PLAYER = Pattern.compile("Player (\\d+):");
  private static final Pattern CARD = Pattern.compile("\\d+");

  private final Int2ObjectMap<Deck> players = new Int2ObjectOpenHashMap<>();

  private int inputPlayer;

  public Int2ObjectOpenHashMap<Deck> load(String inputFile) {
    players.clear();
    InputUtils.inputLines(inputFile).forEach(this::processInput);
    return new Int2ObjectOpenHashMap<>(players);
  }

  private void processInput(String line) {
    Matcher matcher = PLAYER.matcher(line);
    if (matcher.matches()) {
      inputPlayer = Integer.parseInt(matcher.group(1));
      players.put(inputPlayer, new Deck());
      return;
    }

    matcher = CARD.matcher(line);
    if (matcher.matches()) {
      players.get(inputPlayer).addOnBottom(Integer.parseInt(matcher.group()));
    }
  }
}
