package Main;

import Domain.Team;
import Mapper.TeamMapper;
import com.alibaba.fastjson.JSON;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Test {
    public static void main(String[] args) {
        String resource = "Mybatis.xml";
        try {
            InputStream resourceAsStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            try (SqlSession sqlSession = sqlSessionFactory.openSession()){
                TeamMapper mapper = sqlSession.getMapper(TeamMapper.class);
                System.out.println(JSON.toJSON(mapper.getAllMsg()));
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
