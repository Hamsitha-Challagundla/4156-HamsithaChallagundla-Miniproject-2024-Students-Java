package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Department class.
 * <p>
 * Includes tests for retrieving department details, modifying the number of majors,
 * adding and creating courses, and toString() method.
 * </p>
 */
public class DepartmentTest {

  private Department department;
  private HashMap<String, Course> courses;
  private Course course;

  @BeforeEach
  public void setUp() {
    course = new Course("Gail Kaiser", "501 NWC", "10:10-11:25", 120);
    course.setEnrolledStudentCount(109);

    // Initialize courses HashMap
    courses = new HashMap<>();
    courses.put("4156", course);

    // Initialize department
    department = new Department("COMS", courses, "Luca Carloni", 2700);
  }

  @Test
  public void testGetNumberOfMajors() {
    assertEquals(2700, department.getNumberOfMajors());
  }

  @Test
  public void testGetDepartmentChair() {
    assertEquals("Luca Carloni", department.getDepartmentChair());
  }

  @Test
  public void testGetCourseSelection() {
    Map<String, Course> result = department.getCourseSelection();
    assertEquals(1, result.size());
    assertTrue(result.containsKey("4156"));
  }

  @Test
  public void testIncreaseMajorsByOne() {
    department.increaseMajorsByOne();
    assertEquals(2701, department.getNumberOfMajors());
  }

  @Test
  public void testDecreaseMajorsByOneWhenPositive() {
    department.decreaseMajorsByOne();
    assertEquals(2699, department.getNumberOfMajors());
  }

  @Test
  public void testDecreaseMajorsByOneWhenZero() {
    department = new Department("COMS", courses, "Luca Carloni", 0);
    department.decreaseMajorsByOne();
    assertEquals(0, department.getNumberOfMajors());
  }

  @Test
  public void testAddCourse() {
    Course coms3827 = new Course("Daniel Rubenstein", "207 Math", "10:10-11:25", 300);
    department.addCourse("3827", coms3827);

    assertEquals(2, department.getCourseSelection().size());
    assertTrue(department.getCourseSelection().containsKey("3827"));
  }

  @Test
  public void testCreateCourse() {
    department.createCourse("3261", "Josh Alman", "417 IAB", "2:40-3:55", 150);

    assertEquals(2, department.getCourseSelection().size());
    Course addedCourse = department.getCourseSelection().get("3261");
    assertEquals("Josh Alman", addedCourse.getInstructorName());
  }

  @Test
  public void testToString() {
    String expected = "COMS 4156: \nInstructor: Gail Kaiser; Location: 501 NWC; Time: 10:10-11:25\n";
    assertEquals(expected, department.toString());
  }

}
