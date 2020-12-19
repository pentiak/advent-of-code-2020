package com.adventofcode.day9;

import com.adventofcode.day1.ArgumentsFinder;
import com.adventofcode.utils.InputUtils;
import it.unimi.dsi.fastutil.longs.LongComparators;
import it.unimi.dsi.fastutil.longs.LongList;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day9 {

  public static void main(String[] args) {
    LongList input = InputUtils.inputLongList("day9/input.txt");
    XmasDecryptor decryptor = new XmasDecryptor(input, 25);
    long corruptedValue = decryptor.findCorruptedValue();
    log.info("Corrupted value: {}", corruptedValue);

    LongList summingSublist = ArgumentsFinder.findSummingSublist(input, corruptedValue);
    log.info("summingSublist: {}", summingSublist);
    summingSublist.sort(LongComparators.NATURAL_COMPARATOR);
    long encryptionWeakness = summingSublist.getLong(0) + summingSublist.getLong(summingSublist.size() - 1);
    log.info("Encryption weakness: {}", encryptionWeakness);
  }
}
