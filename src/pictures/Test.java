package pictures;

import org.junit.*;
import static org.junit.Assert.*;

public class Test {
  @Before
  public void initialize() {
    Picture GoT = Picture.of("Guardians of Transportation", 20, 480, 660);
    Picture TC = Picture.of("Tower City", 40, 540, 780);
    Picture WSM = Picture.of("West Side Market", 40, 720, 840);
    Picture CMA = Picture.of("Cleveland Museum of Art", 70, 600, 1080);
    Picture SH = Picture.of("Severance Hall", 20, 900, 1020);
    Picture GB = Picture.of("Glennan Building", 10, 960, 1140);
  }

  @Test
  public void testTrue() {
    assertTrue(false);
  }

  @Test
  public void test() {
    assertEquals(GoT.getPriority(), 20);
  }
}
