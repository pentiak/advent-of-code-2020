package com.adventofcode.day5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardingPassDecoderTest {

    private final BoardingPassDecoder decoder = new BoardingPassDecoder();

    @ParameterizedTest
    @CsvSource(delimiter = ',', value = {
            "BFFFBBFRRR,70,7,567",
            "FFFBBBFRRR,14,7,119",
            "BBFFBBFRLL,102,4,820"
    })
    void boardingPasses(String boardingPass, int row, int column, int id) {
        Seat seat = decoder.decode(boardingPass);

        assertEquals(row, seat.getRow());
        assertEquals(column, seat.getColumn());
        assertEquals(id, seat.getId());
    }
}