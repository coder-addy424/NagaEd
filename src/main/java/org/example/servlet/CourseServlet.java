package org.example.servlet;

import org.example.dao.CourseDao;
import org.example.dao.impl.CourseDaoImpl;
import org.example.data.CourseData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/courses")
public class CourseServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CourseDao courseDAO = new CourseDaoImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //CourseDao courseDAO = new CourseDaoImpl();
        String action = request.getParameter("action");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        if ("get".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            CourseData course = courseDAO.getCourseById(id);
            out.println(course != null ? course : "{}");
        } else if ("list".equals(action)) {
            List<CourseData> courses = courseDAO.getAllCourses();
            out.println(courses);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        float duration = Float.parseFloat(request.getParameter("duration"));
        CourseData course = new CourseData(1, title, description, duration);
        courseDAO.createCourse(course);
        response.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        float duration = Float.parseFloat(request.getParameter("duration"));
        CourseData course = new CourseData(id, title, description, duration);
        courseDAO.updateCourse(course);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        courseDAO.deleteCourse(id);
        response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
