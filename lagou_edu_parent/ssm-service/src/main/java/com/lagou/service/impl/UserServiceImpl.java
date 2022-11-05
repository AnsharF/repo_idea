package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    /*
        用户分页&多条件组合查询
     */
    @Override
    public PageInfo findAllUserByPage(UserVO userVo) {

        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());
        List<User> allUserByPage = userMapper.findAllUserByPage(userVo);

        PageInfo<User> pageInfo = new PageInfo<>(allUserByPage);


        return pageInfo;
    }

    /*
        用户登录
     */
    @Override
    public User login(User user) throws Exception {

        // 1.调用mapper方法 user2:包含了密文密码
        User user2 = userMapper.login(user);

        System.out.println(user2);

        if (user2 != null && Md5.verify(user.getPassword(), "lagou", user2.getPassword())) {
            System.out.println(user2);
            return user2;
        } else {
            return null;
        }
    }

    /*
       分配角色（回显）
    */
    @Override
    public List<Role> findUserRelationRoleById(Integer id) {
        List<Role> userRelationRoleById = userMapper.findUserRelationRoleById(id);
        return userRelationRoleById;
    }

    /*
       分配角色
    */
    @Override
    public void userContextRole(UserVO userVO) {

        //先清空中间表关联关系
        userMapper.deleteUserContextRole(userVO.getUserId());

        for (Integer roleId : userVO.getRoleIdList()) {
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVO.getUserId());
            user_role_relation.setRoleId(roleId);

            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedBy("system");
            userMapper.userContextRole(user_role_relation);


        }
        
    }

    /*
        获取用户拥有的权限,进行菜单动态展示
     */
    @Override
    public ResponseResult getUserPermissions(Integer userId) {

        // 1. 获取当前用户拥有的角色
        List<Role> roleList = userMapper.findUserRelationRoleById(userId);

        // 2. 获取角色ID，保存到List集合中
        ArrayList<Integer> roleIds = new ArrayList<>();

        for (Role role : roleList) {
            roleIds.add(role.getId());
        }

        // 3.根据角色ID查询父菜单
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(roleIds);

        // 4.查封封装父菜单关联的子菜单
        for (Menu menu : parentMenu) {
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenu);
        }

        // 5.获取资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIds);


        // 6. 封装数据并返回
        Map<String, Object> map = new HashMap<>();
        map.put("menuList",parentMenu);
        map.put("resourceList",resourceList);



        return new ResponseResult(true,200,"获取用户权限信息成功",map);
    }


}