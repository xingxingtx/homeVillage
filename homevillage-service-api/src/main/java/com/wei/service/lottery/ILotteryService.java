package com.wei.service.lottery;

import com.wei.model.lottery.LotteryModel;
import com.wei.model.lottery.LotteryVM;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年01月15日
 */
public interface ILotteryService {
    LotteryModel getLottery(LotteryVM vm);
}
