package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import java.lang.reflect.Field;
import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

/**
 * Tests for the IndividualProjectApplication class.
 * <p>
 * Includes tests for application running, database overriding, and reset data file logic.
 * </p>
 */
class IndividualProjectApplicationTest {
  @InjectMocks
  private IndividualProjectApplication app;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    MyFileDatabase mockDatabase = Mockito.mock(MyFileDatabase.class);
    IndividualProjectApplication.overrideDatabase(mockDatabase);
  }

  @Test
  void testRunWithSetUpArgument() {
    String[] args = {"setup"};
    app.run(args);

    assertNotNull(IndividualProjectApplication.myFileDatabase);
  }

  @Test
  void testRunWithoutSetUpArgument() {
    String[] args = {};
    app.run(args);

    assertNotNull(IndividualProjectApplication.myFileDatabase);
  }

  @Test
  void testDatabaseOverride() {
    MyFileDatabase testDatabase = new MyFileDatabase(0, "./testDataFile.txt");
    IndividualProjectApplication.overrideDatabase(testDatabase);

    assertEquals(testDatabase, IndividualProjectApplication.myFileDatabase);
  }

  @Test
  void testResetDataFile(){
    app.resetDataFile();
    verify(IndividualProjectApplication.myFileDatabase).setMapping(any(HashMap.class));
  }
}
