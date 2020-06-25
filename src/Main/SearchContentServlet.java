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

@WebServlet("/searchContent")
public class SearchContentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json; charset=utf-8");
        String name = request.getParameter("name");
        String resource = "Mybatis.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            TeamMapper teamMapper = sqlSession.getMapper(TeamMapper.class);
            Team teamContent = teamMapper.getMsgByName(name);
            Object json = JSON.toJSON(teamContent);
            PrintWriter writer = response.getWriter();
            writer.print(json);
            writer.flush();
            writer.close();
        }
    }
}
