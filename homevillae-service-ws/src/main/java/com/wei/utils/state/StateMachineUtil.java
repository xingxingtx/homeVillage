package com.wei.utils.state;

import com.wei.statemachine.StateMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2020年11月05日
 */
@Component
public class StateMachineUtil {
    @Autowired
    private StateMachineService stateMachineService;

}
