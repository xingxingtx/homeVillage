package com.wei.impl;

import com.wei.exception.UserBusinessException;
import com.wei.mapper.UserMapper;
import com.wei.model.user.UserModel;
import com.wei.model.user.UserModelVM;
import com.wei.service.IUserService;
import com.wei.utils.generate.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wei.peng
 * @descripe
 * @Date 2019/7/2 0002.
 */
@Service
public class UserServiceImpl  implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserModel selectUser(UserModelVM vm) {
        System.out.println("ssss");
        return userMapper.selectUser(vm);
    }

    @Override
    public int deleteUser(UserModelVM vm) throws DataAccessException {
        return userMapper.deleteUser(vm);
    }

    @Override
    public int updateUser(UserModel model) throws DataAccessException {
        return userMapper.updateUser(model);
    }

    @Override
    public int insertUser(UserModel model) throws DataAccessException {
        return userMapper.insertUser(model);
    }

    @Override
    public int updatePassword(UserModelVM vm) {
        String errorMessage =  validatePassword(vm);
        if(!StringUtils.isEmpty(errorMessage, false)){
            throw new UserBusinessException(errorMessage);
        }
        return userMapper.updatePassword(vm);
    }

    @Override
    public List<UserModel> selectUserList(UserModelVM vm) throws DataAccessException {
        return userMapper.selectUserList(vm);
    }

    @Override
    public UserModel loginUser(UserModelVM vm) throws DataAccessException {
        return null;
    }

    @Override
    public String findPassword(UserModelVM vm) throws DataAccessException {
        return null;
    }

    @Override
    public int informationComplement(UserModelVM vm) {
        return 0;
    }


    /**
     * 校验密码修改规则
     * @param vm
     */
    private String validatePassword(UserModelVM vm) {
        String result = null;
        if(!StringUtils.lengthValidate(vm.getPassword(), 6, 20)){
            result = "密码长度应该在6-20字符";
        }
        /**查询密码是否与原密码一致*/
        UserModel user = userMapper.selectUser(vm);
        if(!StringUtils.isEmpty(vm.getPassword(), false) && vm.getPassword().equals(user.getPassword())){
            result = "与原密码一致";
        }
        return result;
    }


}
