package Main;

import Domain.Team;
import Mapper.TeamMapper;
import com.alibaba.fastjson.JSON;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet("/deleteContent")
public class DeleteContentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resource = "Mybatis.xml";
        String name = request.getParameter("name");
        int id = Integer.parseInt(request.getParameter("id"));
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            TeamMapper teamMapper = sqlSession.getMapper(TeamMapper.class);
            teamMapper.deleteTeam(name);
            teamMapper.updateId();
            sqlSession.commit();
        }
    }
}
