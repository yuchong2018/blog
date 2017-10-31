package yc.mapper.user;

import yc.po.user.PermissionRole;
import yc.po.user.PermissionRoleExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionRoleMapper {
    int countByExample(PermissionRoleExample example);

    int deleteByExample(PermissionRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PermissionRole record);

    int insertSelective(PermissionRole record);

    List<PermissionRole> selectByExample(PermissionRoleExample example);

    PermissionRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PermissionRole record, @Param("example") PermissionRoleExample example);

    int updateByExample(@Param("record") PermissionRole record, @Param("example") PermissionRoleExample example);

    int updateByPrimaryKeySelective(PermissionRole record);

    int updateByPrimaryKey(PermissionRole record);
}