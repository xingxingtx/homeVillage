package com.wei.mapper;

import com.wei.model.user.UserModel;
import com.wei.model.user.UserModelVM;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author a_pen
 * @describe
 * @date 2020年07月29日
 */
@Repository
@Mapper
public interface UserMapper {

    /**
     *  查询用户信息
     * @param vm
     * @return
     * @throws DataAccessException
     */
    UserModel selectUser(UserModelVM vm) throws DataAccessException;

    /**
     *  删除用户信息
     * @param vm
     * @return
     * @throws DataAccessException
     */
    int deleteUser(UserModelVM vm) throws DataAccessException;

    /**
     *  修改用户信息
     * @param model
     * @return
     * @throws DataAccessException
     */
    int updateUser(UserModel model) throws DataAccessException;

    /**
     *  插入用户信息
     * @param model
     * @return
     * @throws DataAccessException
     */
    int insertUser(@Param("model") UserModel model) throws DataAccessException;

    /**
     * 修改密码
     * @param vm
     * @return
     */
    int updatePassword(UserModelVM vm)throws DataAccessException;

    /**
     * 查询用户列表
     * @param vm
     * @return
     */
    List<UserModel> selectUserList(UserModelVM vm);
}
