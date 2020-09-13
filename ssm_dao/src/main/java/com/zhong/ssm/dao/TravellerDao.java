package com.zhong.ssm.dao;

import com.zhong.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TravellerDao {
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{oid})")
    public List<Traveller> findByOrderId(Integer oid);

    @Select("select * from traveller")
    List<Traveller> findAll();
}
