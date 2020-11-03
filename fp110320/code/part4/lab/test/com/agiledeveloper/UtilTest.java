package com.agiledeveloper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;

public class UtilTest {
  @Test
  void numberAtPosition() {
    assertAll(
      () -> assertEquals(Arrays.asList("3 4 5"), Util.computeTriples(1))
      , () -> assertEquals(Arrays.asList("3 4 5", "8 6 10"), Util.computeTriples(2))
      , () -> assertEquals(Arrays.asList("3 4 5", "8 6 10", "5 12 13"), Util.computeTriples(3))
      , () -> assertEquals(Arrays.asList("3 4 5", "8 6 10", "5 12 13", "15 8 17"), Util.computeTriples(4))
      , () -> assertEquals(Arrays.asList("3 4 5", "8 6 10", "5 12 13", "15 8 17", "12 16 20"), Util.computeTriples(5))
      , () -> assertEquals(10, Util.computeTriples(10).size())
      , () -> assertTrue(Util.computeTriples(10).contains("21 20 29"))
      , () -> assertEquals(20, Util.computeTriples(20).size())
      , () -> assertTrue(Util.computeTriples(20).contains("45 28 53"))
    );
  }
}
