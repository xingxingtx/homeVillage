package com.wei.controller;

import com.wei.model.user.UserModel;
import com.wei.model.user.UserModelVM;
import com.wei.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author a_pen
 * @describe
 * @date 2020年07月29日
 */

@RestController
@RequestMapping("/api/user")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private IUserService userService;

    /**
     * 查询用户
     * @param vm
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "查询用户详情", httpMethod = "GET")
    public ResponseEntity selectUser(UserModelVM vm) {
        UserModel userModel = userService.selectUser(vm);
        return new ResponseEntity(userModel, HttpStatus.OK);

    }

    /**
     * 注册用户
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ApiOperation(value = "注册用户", httpMethod = "POST")
    public ResponseEntity insertUser(@RequestBody UserModel model) throws  Exception{
        try {
            userService.insertUser(model);
        }catch (NullPointerException e){

        }catch (Exception e){
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity(HttpStatus.OK);

    }

    /**
     * 修改用户信息
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ApiOperation(value = "修改用户信息", httpMethod = "PUT")
    public ResponseEntity updateUser(@RequestBody UserModel model) {
        userService.updateUser(model);
        return new ResponseEntity(HttpStatus.OK);

    }

    /**
     * 删除，注销用户信息
     * @param vm
     * @return
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @ApiOperation(value = "删除，注销用户信息", httpMethod = "DELETE")
    public ResponseEntity deleteUser(@RequestBody UserModelVM vm) {
        userService.deleteUser(vm);
        return new ResponseEntity(HttpStatus.OK);

    }

    /**
     * 修改密码
     * @param vm
     * @return
     */
    @RequestMapping(value = "/updatePassword", method = RequestMethod.PUT)
    @ApiOperation(value = "修改用户密码", httpMethod = "DELETE")
    public ResponseEntity updatePassword(@RequestBody UserModelVM vm) {
        userService.updatePassword(vm);
        return new ResponseEntity(HttpStatus.OK);

    }

    /**
     * 查询用户
     * @param vm
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation(value = "查询用户详情", httpMethod = "GET")
    public ResponseEntity selectUserList(@RequestBody UserModelVM vm) {
        List<UserModel> userModel = userService.selectUserList(vm);
        return new ResponseEntity(userModel, HttpStatus.OK);

    }


    /**
     * 用户登录
     * @param vm
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "用户登录", httpMethod = "POST")
    public ResponseEntity loginUser(@RequestBody UserModelVM vm) {
        UserModel userModel = userService.loginUser(vm);
        return new ResponseEntity(HttpStatus.OK);

    }


    /**
     * 用户密码找回
     * @param vm
     * @return
     */
    @RequestMapping(value = "/findPassword", method = RequestMethod.POST)
    @ApiOperation(value = "用户密码找回", httpMethod = "POST")
    public ResponseEntity findPassword(@RequestBody UserModelVM vm) {
        String password = userService.findPassword(vm);
        return new ResponseEntity(password, HttpStatus.OK);
    }

    /**
     * 用户信息完善
     * @param vm
     * @return
     */
    @RequestMapping(value = "/informationComplement", method = RequestMethod.POST)
    @ApiOperation(value = "用户信息完善", httpMethod = "POST")
    public ResponseEntity informationComplement(@RequestBody UserModelVM vm) {
        int result = userService.informationComplement(vm);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
