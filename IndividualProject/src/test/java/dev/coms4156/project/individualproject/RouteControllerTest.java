package dev.coms4156.project.individualproject;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * Tests for the RouteController class.
 * <p>
 * Includes tests for retrieving department and course details,
 * checking course availability, managing course enrollment,
 * updating course information, and verifying correct HTTP status codes
 * and response body.
 * </p>
 */
@SpringBootTest
public class RouteControllerTest {
  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext context;

  @BeforeEach
  public void setUp() {
    mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  public void testRetrieveDepartmentOkStatus() throws Exception {
    mockMvc.perform(get("/retrieveDept")
            .param("deptCode", "COMS"))
        .andExpect(status().isOk());
  }

  @Test
  public void testRetrieveDepartmentReturnsOkStatusForLowerCaseDeptCode() throws Exception {
    mockMvc.perform(get("/retrieveDept")
            .param("deptCode", "coms"))
        .andExpect(status().isOk());
  }

  @Test
  public void testRetrieveDepartmentNotFoundStatus() throws Exception {
    mockMvc.perform(get("/retrieveDept")
            .param("deptCode", "CSE"))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Department Not Found"));
  }

  @Test
  public void testRetrieveCourseOkStatus() throws Exception {
    mockMvc.perform(get("/retrieveCourse")
            .param("deptCode", "ECON")
            .param("courseCode", "1105"))
        .andExpect(status().isOk());
  }

  @Test
  public void testRetrieveCourseNotFoundStatus() throws Exception {
    mockMvc.perform(get("/retrieveCourse")
            .param("deptCode", "ECON")
            .param("courseCode", "1111"))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Course Not Found"));
  }

  @Test
  public void testIsCourseFullTrueCase() throws Exception {
    mockMvc.perform(get("/isCourseFull")
            .param("deptCode", "PSYC")
            .param("courseCode", "3212"))
        .andExpect(status().isOk())
        .andExpect(content().string("The course 3212 is full"));
  }

  @Test
  public void testIsCourseFullFalseCase() throws Exception {
    mockMvc.perform(get("/isCourseFull")
            .param("deptCode", "ECON")
            .param("courseCode", "3412"))
        .andExpect(status().isOk())
        .andExpect(content().string("The course 3412 is not full"));
  }

  @Test
  public void testGetMajorCountFromDeptOkStatus() throws Exception {
    mockMvc.perform(get("/getMajorCountFromDept")
            .param("deptCode", "PHYS")
            .param("courseCode", "1201"))
        .andExpect(status().isOk())
        .andExpect(content().string("There are: 43 majors in the department"));
  }

  @Test
  public void testGetMajorCountFromDeptNotFoundStatus() throws Exception {
    mockMvc.perform(get("/getMajorCountFromDept")
            .param("deptCode", "MATH")
            .param("courseCode", "1201"))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Department Not Found"));
  }

  @Test
  public void testIdDeptChair() throws Exception {
    mockMvc.perform(get("/idDeptChair")
            .param("deptCode", "ELEN"))
        .andExpect(status().isOk())
        .andExpect(content().string("Ioannis Kymissis is the department chair."));
  }

  @Test
  public void testFindCourseLocationOkStatus() throws Exception {
    mockMvc.perform(get("/findCourseLocation")
            .param("deptCode", "PSYC")
            .param("courseCode", "4493"))
        .andExpect(status().isOk())
        .andExpect(content().string("200 SCH is where the course is located."));
  }

  @Test
  public void testFindCourseLocationNotFoundStatus() throws Exception {
    mockMvc.perform(get("/findCourseLocation")
            .param("deptCode", "PSYC")
            .param("courseCode", "4499"))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Course Not Found"));
  }

  @Test
  public void testFindCourseInstructor() throws Exception {
    mockMvc.perform(get("/findCourseInstructor")
            .param("deptCode", "CHEM")
            .param("courseCode", "4102"))
        .andExpect(status().isOk())
        .andExpect(content().string("Dalibor Sames is the instructor for the course."));
  }

  @Test
  public void testFindCourseTimeOkStatus() throws Exception {
    mockMvc.perform(get("/findCourseTime")
            .param("deptCode", "IEOR")
            .param("courseCode", "2500"))
        .andExpect(status().isOk())
        .andExpect(content().string("The course meets at: 11:40-12:55"));
  }

  @Test
  public void testFindCourseTimeNotFoundStatus() throws Exception {
    mockMvc.perform(get("/findCourseTime")
            .param("deptCode", "ECON")
            .param("courseCode", "4800"))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Course Not Found"));
  }

  @Test
  public void testAddMajorToDeptOkStatus() throws Exception {
    mockMvc.perform(patch("/addMajorToDept")
            .param("deptCode", "PSYC"))
        .andExpect(status().isOk())
        .andExpect(content().string("Major was added to the department PSYC successfully"));
  }

  @Test
  public void testAddMajorToDeptNotFoundStatus() throws Exception {
    mockMvc.perform(patch("/addMajorToDept")
            .param("deptCode", "ARCH"))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Department Not Found"));
  }

  @Test
  public void testRemoveMajorFromDeptOkStatus() throws Exception {
    mockMvc.perform(patch("/removeMajorFromDept")
            .param("deptCode", "COMS"))
        .andExpect(status().isOk())
        .andExpect(content().string("Major was removed from the department COMS successfully"));
  }

  @Test
  public void testRemoveMajorFromDeptNotFoundStatus() throws Exception {
    mockMvc.perform(patch("/removeMajorFromDept")
            .param("deptCode", "ENG"))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Department Not Found"));
  }

  @Test
  public void testDropStudentFromCourseOkStatus() throws Exception {
    mockMvc.perform(patch("/dropStudentFromCourse")
            .param("deptCode", "IEOR")
            .param("courseCode", "3658"))
        .andExpect(status().isOk())
        .andExpect(content().string("Student has been dropped."));
  }

  @Test
  public void testDropStudentFromCourseNotFoundStatus() throws Exception {
    mockMvc.perform(patch("/dropStudentFromCourse")
            .param("deptCode", "IEOR")
            .param("courseCode", "2560"))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Course Not Found"));
  }

  @Test
  public void testSetEnrollmentCount() throws Exception {
    mockMvc.perform(patch("/setEnrollmentCount")
            .param("deptCode", "COMS")
            .param("count", "30")
            .param("courseCode", "1004"))
        .andExpect(status().isOk())
        .andExpect(content().string("EnrollmentCount for course 1004 was updated successfully."));
  }

  @Test
  public void testChangeCourseTimeOkStatus() throws Exception {
    mockMvc.perform(patch("/changeCourseTime")
            .param("deptCode", "PSYC")
            .param("time", "1:10-2:25")
            .param("courseCode", "1610"))
        .andExpect(status().isOk())
        .andExpect(content().string("CourseTime for 1610 was updated successfully."));
  }

  @Test
  public void testChangeCourseTimeNotFoundStatus() throws Exception {
    mockMvc.perform(patch("/changeCourseTime")
            .param("deptCode", "PSYC")
            .param("time", "1:10-2:25")
            .param("courseCode", "1111"))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Course Not Found"));
  }

  @Test
  public void testChangeCourseTeacherOkStatus() throws Exception {
    mockMvc.perform(patch("/changeCourseTeacher")
            .param("deptCode", "ELEN")
            .param("courseCode", "1201")
            .param("teacher", "Kenneth Shepard"))
        .andExpect(status().isOk())
        .andExpect(content().string("CourseTeacher for 1201 was updated successfully."));
  }

  @Test
  public void testChangeCourseTeacherNotFoundStatus() throws Exception {
    mockMvc.perform(patch("/changeCourseTeacher")
            .param("deptCode", "MECH")
            .param("courseCode", "1201")
            .param("teacher", "Kenneth Shepard"))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Course Not Found"));
  }

  @Test
  public void testChangeCourseLocationOkStatus() throws Exception {
    mockMvc.perform(patch("/changeCourseLocation")
            .param("deptCode", "PHYS")
            .param("location", "329 PUP")
            .param("courseCode", "3008"))
        .andExpect(status().isOk())
        .andExpect(content().string("CourseLocation for 3008 was updated successfully."));
  }

  @Test
  public void testChangeCourseLocationNotFoundStatus() throws Exception {
    mockMvc.perform(patch("/changeCourseLocation")
            .param("deptCode", "PHYS")
            .param("location", "329 PUP")
            .param("courseCode", "3000"))
        .andExpect(status().isNotFound())
        .andExpect(content().string("Course Not Found"));
  }


}
