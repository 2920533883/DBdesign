package Main;

import Domain.Team;
import Mapper.TeamMapper;
import com.alibaba.fastjson.JSON;
import com.mysql.cj.xdevapi.JsonArray;
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

@WebServlet("/getAllContent")
public class GetAllContentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json; charset=utf-8");
        String resource = "Mybatis.xml";
        InputStream resourceAsStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        try(SqlSession sqlSession = sqlSessionFactory.openSession()){
            TeamMapper teamMapper = sqlSession.getMapper(TeamMapper.class);
            ArrayList<Team> allMsg = teamMapper.getAllMsg();
            Object json = JSON.toJSON(allMsg);
            PrintWriter writer = response.getWriter();
            writer.print(json);
            writer.flush();
            writer.close();
        }
    }
}
