package Mapper;

import Domain.Team;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface TeamMapper {
    // 获取所有球队所有信息
    public ArrayList<Team> getAllMsg();
    // 增加球队信息
    public void insertTeam(Team team);
    // 删除球队
    public void deleteTeam(String name);
    // 修改内容
    public void updateMsg(@Param("team") Team team, @Param("key") int key);
    // 查询
    public Team getMsgByName(String name);
    // 更新id
    public void updateId();
}
