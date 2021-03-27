package com.wei.service;

import com.wei.model.user.UserModel;
import com.wei.model.user.UserModelVM;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * @author wei.peng
 * @descripe
 * @Date 2019/7/2 0002.
 */
public interface IUserService {
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
    int insertUser(UserModel model) throws DataAccessException;

    /**
     * 修改密码
     * @param vm
     * @return
     */
    int updatePassword(UserModelVM vm) throws DataAccessException;

    /**
     * 查询用户列表
     * @param vm
     * @return
     */
    List<UserModel> selectUserList(UserModelVM vm) throws DataAccessException;

    /**
     * 用户登录
     * @param vm
     * @return
     */
    UserModel loginUser(UserModelVM vm)throws DataAccessException;

    /**
     * 密码找回
     * @param vm
     * @return
     */
    String findPassword(UserModelVM vm)throws DataAccessException;

    /**
     * 用户信息完善
     * @param vm
     * @return
     */
    int informationComplement(UserModelVM vm)throws DataAccessException;
}
