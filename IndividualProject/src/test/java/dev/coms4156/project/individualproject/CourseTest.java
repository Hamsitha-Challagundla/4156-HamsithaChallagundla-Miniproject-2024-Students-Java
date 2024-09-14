package dev.coms4156.project.individualproject;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the Course class.
 * <p>
 * Includes tests for enrolling and dropping students, toString() method, updating course details, and checking if the course is full.
 * </p>
 */
@SpringBootTest
@ContextConfiguration
public class CourseTest {

  /**
   * The test course instance used for testing.
   */
  private Course testCourse;

  @BeforeEach
  void setupCourseForTesting() {
    testCourse = new Course("Adam Cannon", "417 IAB", "11:40-12:55", 400);
    testCourse.setEnrolledStudentCount(249);
  }

  @Test
  void testEnrollStudentWhenUnderCapacity() {
    assertTrue(testCourse.enrollStudent());

    testCourse.setEnrolledStudentCount(399);
    assertTrue(testCourse.enrollStudent());
  }

  @Test
  void testEnrollStudentWhenAtCapacity() {
    testCourse.setEnrolledStudentCount(400);
    assertFalse(testCourse.enrollStudent());
  }

  @Test
  void testDropStudentWhenAboveMinimum() {
    assertTrue(testCourse.dropStudent());

    testCourse.setEnrolledStudentCount(1);
    assertTrue(testCourse.dropStudent());
  }

  @Test
  void testDropStudentWhenAtMinimum() {
    testCourse.setEnrolledStudentCount(0);
    assertFalse(testCourse.dropStudent());
  }

  @Test
  void testToString() {
    String expectedResult = "\nInstructor: Adam Cannon; Location: 417 IAB; Time: 11:40-12:55";
    assertEquals(expectedResult, testCourse.toString());
  }

  @Test
  void testReassignInstructorWhenNotEmptyOrNull() {
    testCourse.reassignInstructor("Brian Borowski");
    assertEquals("Brian Borowski", testCourse.getInstructorName());
  }

  @Test
  void testReassignInstructorWhenEmptyOrNull() {
    testCourse.reassignInstructor("");
    assertEquals("Adam Cannon", testCourse.getInstructorName());
  }

  @Test
  void testReassignLocationWhenNotEmptyOrNull() {
    testCourse.reassignLocation("301 URIS");
    assertEquals("301 URIS", testCourse.getCourseLocation());
  }

  @Test
  void testReassignLocationWhenEmptyOrNull() {
    testCourse.reassignLocation(null);
    assertEquals("417 IAB", testCourse.getCourseLocation());
  }

  @Test
  void testReassignTimeWhenNotEmptyOrNull() {
    testCourse.reassignTime("4:10-5:25");
    assertEquals("4:10-5:25", testCourse.getCourseTimeSlot());
  }

  @Test
  void testReassignTimeWhenEmptyOrNull() {
    testCourse.reassignTime(null);
    assertEquals("11:40-12:55", testCourse.getCourseTimeSlot());
  }


  @Test
  void testSetEnrolledStudentCount() {
    testCourse.setEnrolledStudentCount(-1);
    assertEquals(249, testCourse.getEnrolledStudentCount());
  }

  @Test
  void testIsCourseFullWhenBelowCapacity() {
    assertFalse(testCourse.isCourseFull());
  }

  @Test
  void testIsCourseFullWhenOneBelowCapacity() {
    testCourse.setEnrolledStudentCount(399);
    assertFalse(testCourse.isCourseFull());
  }

  @Test
  void testIsCourseFullWhenAtCapacity() {
    testCourse.setEnrolledStudentCount(400);
    assertTrue(testCourse.isCourseFull());
  }
}

