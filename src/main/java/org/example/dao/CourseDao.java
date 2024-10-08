package org.example.dao;

import org.example.data.CourseData;

import java.util.List;

public interface CourseDao {
    String createCourse(CourseData course);

    boolean updateCourse(CourseData course);

    List<CourseData> getAllCourses();

    CourseData getCourseById(int id);

    void deleteCourse(int id);
}
