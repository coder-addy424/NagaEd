package org.example.dao.impl;

import org.example.dao.CourseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.data.CourseData;
import org.example.util.DBUtil;


public class CourseDaoImpl implements CourseDao {

        @Override
        public String createCourse(CourseData course) {
            String status="Not Registerd..";

            Connection conn=DBUtil.provideConnection();

            try {
                PreparedStatement ps=conn.prepareStatement("insert into course values(?,?,?,?)");

                ps.setInt(1, course.getId());
                ps.setString(2,course.getTitle());
                ps.setString(3,course.getDescription() );
                ps.setFloat(4,course.getDuration() );
                int x=ps.executeUpdate();
                if(x>0)
                {
                    status="Course created Succesfully with Id "+course.getId();
                }
            } catch (SQLException e) {
                status="Error !"+e.getMessage();
                e.printStackTrace();
            }
            return status;
        }

    @Override
        public boolean updateCourse(CourseData course) {
          boolean flag=false;
         Connection conn=DBUtil.provideConnection();
            try {
                PreparedStatement ps=conn.prepareStatement("update course set description=?,duration=? where id=?");

                ps.setString(1, course.getDescription());
                ps.setFloat(2, course.getDuration());
                ps.setInt(3, course.getId());

                int x=ps.executeUpdate();

                if(x>0)
                    flag=true;
            } catch (SQLException e) {

                e.printStackTrace();
            }
            return flag;
        }

        @Override
        public List<CourseData> getAllCourses()
        {
            List<CourseData> allCourse = new ArrayList<CourseData>();

            Connection con=DBUtil.provideConnection();

            try
            {
                PreparedStatement ps=con.prepareStatement("select * from course");

                ResultSet rs=ps.executeQuery();

                while(rs.next())
                {
                    int id = rs.getInt(1);
                    String title=rs.getString(2);
                    String description=rs.getString(3);
                    float duration=rs.getFloat(4);

                    CourseData c=new CourseData(id,title,description,duration);
                    allCourse.add(c);

                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return allCourse;
        }

    @Override
    public CourseData getCourseById(int id) {
        CourseData c=new CourseData();
        Connection con=DBUtil.provideConnection();
        try
        {
            PreparedStatement ps=con.prepareStatement("select * from course where id=?");
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();

            while(rs.next())
            {
                c.setId(rs.getInt(1));
                c.setTitle(rs.getString(2));
                c.setDescription(rs.getString(3));
                c.setDuration(rs.getFloat(4));

            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    @Override
    public void deleteCourse(int id) {

        Connection con=DBUtil.provideConnection();

        try
        {
            PreparedStatement ps=con.prepareStatement("select * from course where id=?");
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

