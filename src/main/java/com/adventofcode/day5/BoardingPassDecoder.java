package com.adventofcode.day5;

public class BoardingPassDecoder {

  public Seat decode(String boardingPass) {
    return new Seat(
        Integer.parseInt(convertRowToBinary(boardingPass.substring(0, 7)), 2),
        Integer.parseInt(convertColumnToBinary(boardingPass.substring(7)), 2)
    );
  }

  private String convertRowToBinary(String column) {
    return column.replace('F', '0').replace('B', '1');
  }

  private String convertColumnToBinary(String column) {
    return column.replace('L', '0').replace('R', '1');
  }
}
