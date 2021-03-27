package com.wei.impl.lottery;

import com.wei.mapper.LotteryMapper;
import com.wei.model.lottery.LotteryModel;
import com.wei.model.lottery.LotteryVM;
import com.wei.service.lottery.ILotteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年01月16日
 */
@Service
public class LotteryServiceImpl implements ILotteryService {
    @Autowired
    private LotteryMapper lotteryMapper;

    @Override
    public LotteryModel getLottery(LotteryVM vm) {
        return lotteryMapper.getLottery(vm);
    }
}
