package dev.coms4156.project.individualproject;

import static io.micrometer.common.util.StringUtils.isNotEmpty;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a course with details such as instructor,
 * location, time slot, and enrollment capacity.
 * <p>
 * This class provides methods to enroll and drop students,
 * update course details, and check course capacity.
 * </p>
 */
public class Course implements Serializable {

  @Serial
  private static final long serialVersionUID = 123456L;

  private final int enrollmentCapacity;
  private int enrolledStudentCount;
  private String courseLocation;
  private String instructorName;
  private String courseTimeSlot;

  /**
   * Constructs a new Course object with the given parameters. Initial count starts at 0.
   *
   * @param instructorName The name of the instructor teaching the course.
   * @param courseLocation The location where the course is held.
   * @param timeSlot       The time slot of the course.
   * @param capacity       The maximum number of students that can enroll in the course.
   */
  public Course(String instructorName, String courseLocation, String timeSlot, int capacity) {
    this.instructorName = instructorName;
    this.courseLocation = courseLocation;
    this.courseTimeSlot = timeSlot;
    this.enrollmentCapacity = capacity;
    this.enrolledStudentCount = 0;
  }

  /**
   * Enrolls a student in the course if there is space available.
   *
   * @return true if the student is successfully enrolled, false otherwise.
   */
  public boolean enrollStudent() {
    if (!isCourseFull()) {
      enrolledStudentCount++;
      return true;
    } else {
      return false;
    }
  }

  /**
   * Drops a student from the course if a student is enrolled.
   *
   * @return true if the student is successfully dropped, false otherwise.
   */
  public boolean dropStudent() {
    if (enrolledStudentCount > 0) {
      enrolledStudentCount--;
      return true;
    }
    return false;
  }

  /**
   * Returns the location where the course is held.
   *
   * @return The course location.
   */
  public String getCourseLocation() {
    return this.courseLocation;
  }

  /**
   * Returns the name of the instructor teaching the course.
   *
   * @return The instructor's name.
   */
  public String getInstructorName() {
    return this.instructorName;
  }

  /**
   * Returns the time slot of the course.
   *
   * @return The course time slot.
   */
  public String getCourseTimeSlot() {
    return this.courseTimeSlot;
  }

  /**
   * Returns the currently enrolled number of students in the course.
   *
   * @return The number of enrolled students.
   */
  public int getEnrolledStudentCount() {
    return this.enrolledStudentCount;
  }

  /**
   * Sets the number of students currently enrolled in the course.
   * if the count is within the valid range (0 to capacity).
   *
   * @param count The new number of enrolled students.
   */
  public void setEnrolledStudentCount(int count) {
    if (count >= 0 && count <= enrollmentCapacity) {
      this.enrolledStudentCount = count;
    }
  }

  /**
   * Returns a string representation of the course details.
   *
   * @return A string representing the course.
   */
  @Override
  public String toString() {
    return "\nInstructor: " + instructorName + "; Location: " + courseLocation + "; Time: "
        + courseTimeSlot;
  }

  /**
   * Reassigns a new instructor to the course if the given new name is not empty.
   *
   * @param newInstructorName The name of the new instructor for the course.
   */
  public void reassignInstructor(String newInstructorName) {
    if (isNotEmpty(newInstructorName)) {
      this.instructorName = newInstructorName;
    }
  }

  /**
   * Updates the location where the course is held if the given new location is not empty.
   *
   * @param newLocation The new location for the course.
   */
  public void reassignLocation(String newLocation) {
    if (isNotEmpty(newLocation)) {
      this.courseLocation = newLocation;
    }
  }

  /**
   * Changes the time slot of the course if the given new time slot is not empty.
   *
   * @param newTime The new time slot for the course.
   */
  public void reassignTime(String newTime) {
    if (isNotEmpty(newTime)) {
      this.courseTimeSlot = newTime;
    }
  }

  /**
   * Checks if the course is full based on the current enrollment student count and capacity.
   *
   * @return true if the course is full, false otherwise.
   */
  public boolean isCourseFull() {
    return enrolledStudentCount >= enrollmentCapacity;
  }
}
