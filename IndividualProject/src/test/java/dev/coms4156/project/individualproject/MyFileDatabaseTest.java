package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the MyFileDatabase class.
 * <p>
 * Includes tests for setting department mappings, deserializing object from a file,
 * and toString() method.
 * </p>
 */
class MyFileDatabaseTest {

  private MyFileDatabase myFileDatabase;

  @BeforeEach
  void setUp() {
    myFileDatabase = new MyFileDatabase(0, "./testDataFile.txt");
  }

  @Test
  void testSetMapping() {
    HashMap<String, Department> testMapping = new HashMap<>();
    testMapping.put("PSYC", new Department("PSYC", new HashMap<>(), "Nim Tottenham", 437));

    myFileDatabase.setMapping(testMapping);
    assertEquals(testMapping, myFileDatabase.getDepartmentMapping());
  }

  @Test
  void testDeSerializeObjectFromFile() {
    HashMap<String, Department> testMapping = new HashMap<>();
    testMapping.put("PHYS", new Department("PHYS", new HashMap<>(), "Dmitri N. Basov", 43));

    myFileDatabase.setMapping(testMapping);
    myFileDatabase.saveContentsToFile();

    MyFileDatabase newDatabase = new MyFileDatabase(0, "./testDataFile.txt");
    assertNotNull(newDatabase.getDepartmentMapping());
    assertEquals(testMapping.size(), newDatabase.getDepartmentMapping().size());
  }

  @Test
  void testToString() {
    HashMap<String, Department> testMapping = new HashMap<>();
    Department compScienceDept = new Department("COMS", new HashMap<>(), "Luca Carloni", 2700);
    testMapping.put("COMS", compScienceDept);
    myFileDatabase.setMapping(testMapping);

    String expected = "For the COMS department: \n" + compScienceDept;
    assertEquals(expected, myFileDatabase.toString());
  }
}
