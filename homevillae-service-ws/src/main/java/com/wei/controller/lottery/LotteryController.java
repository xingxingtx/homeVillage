package com.wei.controller.lottery;

import com.wei.model.lottery.LotteryModel;
import com.wei.model.lottery.LotteryVM;
import com.wei.model.user.UserModelVM;
import com.wei.service.lottery.ILotteryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年01月15日
 */
@RestController
@RequestMapping("/api/lottery")
public class LotteryController {
    @Autowired
    private ILotteryService lotteryService;
    /**
     * 购买彩票
     * @param vm
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "购买彩票", httpMethod = "GET")
    public ResponseEntity buyLottery(LotteryVM vm) {
        LotteryModel userModel = lotteryService.getLottery(vm);
        return new ResponseEntity(userModel, HttpStatus.OK);

    }
}
