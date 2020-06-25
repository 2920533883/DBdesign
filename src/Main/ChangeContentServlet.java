package Main;

import Domain.Team;
import Mapper.TeamMapper;
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

@WebServlet("/changeContent")
public class ChangeContentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String resource = "Mybatis.xml";
        String type = request.getParameter("type");
        int index = Integer.parseInt(request.getParameter("index"));
        String name = request.getParameter("name");
        String id = request.getParameter("id");
        String coach = request.getParameter("coach");
        String address = request.getParameter("address");
        int num = Integer.parseInt(request.getParameter("num"));
        String esDate = request.getParameter("esDate");
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        Team team = new Team(id, name, esDate, address, coach, num);
        System.out.println(team);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        if (index != -1){
            try(SqlSession sqlSession = sqlSessionFactory.openSession()){
                TeamMapper teamMapper = sqlSession.getMapper(TeamMapper.class);
                teamMapper.updateMsg(team, index+1);
                sqlSession.commit();
            }
        }
        else{
            try(SqlSession sqlSession = sqlSessionFactory.openSession()){
                TeamMapper teamMapper = sqlSession.getMapper(TeamMapper.class);
                teamMapper.insertTeam(team);
                sqlSession.commit();
            }
        }
    }
}
