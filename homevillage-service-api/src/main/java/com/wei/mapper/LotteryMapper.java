package com.wei.mapper;

import com.wei.model.lottery.LotteryModel;
import com.wei.model.lottery.LotteryVM;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Describe
 * @Author wei.peng
 * @Date 2021年01月26日
 */
@Repository
@Mapper
public interface LotteryMapper {
    LotteryModel getLottery(LotteryVM vm);
}
