package com.mantufo.listsapi.mantufo.listsapi.model;

import com.mantufo.listsapi.mantufo.listsapi.Model.Coordinate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoordinateTest {

    @Test
    public void calculateStringXCoordinate() {
        assertAll(
                () -> assertEquals("A", Coordinate.calculateStringXCoordinate(1), "1"),
                () -> assertEquals("B", Coordinate.calculateStringXCoordinate(2), "2"),
                () -> assertEquals("Z", Coordinate.calculateStringXCoordinate(26), "26"),
                () -> assertEquals("AA", Coordinate.calculateStringXCoordinate(27), "27"),
                () -> assertEquals("AB", Coordinate.calculateStringXCoordinate(28), "28"),
                () -> assertEquals("AZ", Coordinate.calculateStringXCoordinate(52), "52"),
                () -> assertEquals("BA", Coordinate.calculateStringXCoordinate(53), "53"),
                () -> assertEquals("ABC", Coordinate.calculateStringXCoordinate(731), "731")
        );
    }

    @Test
    public void getSheetsFormattedCoordinate() {
        Coordinate coordinate1 = new Coordinate(2,2);
        Coordinate coordinate2 = new Coordinate(52,52);
        assertEquals("B2",coordinate1.getSheetsFormattedCoordinate());
        assertEquals("AZ52",coordinate2.getSheetsFormattedCoordinate());
    }

    @Test
    public void calculateIntXCoordinate() {
        assertAll(
                () -> assertEquals(1, Coordinate.calculateIntXCoordinate("A"), "A"),
                () -> assertEquals(2, Coordinate.calculateIntXCoordinate("B"), "B"),
                () -> assertEquals(26, Coordinate.calculateIntXCoordinate("Z"), "Z"),
                () -> assertEquals(52, Coordinate.calculateIntXCoordinate("AZ"), "AZ"),
                () -> assertEquals(53, Coordinate.calculateIntXCoordinate("BA"), "BA"),
                () -> assertEquals(731, Coordinate.calculateIntXCoordinate("ABC"), "ABC")
        );
        assertThrows(IllegalArgumentException.class, () -> Coordinate.calculateIntXCoordinate(""));
        assertThrows(IllegalArgumentException.class, () -> Coordinate.calculateIntXCoordinate("2"));
    }
}
