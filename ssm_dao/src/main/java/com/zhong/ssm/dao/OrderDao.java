package com.zhong.ssm.dao;

import com.zhong.ssm.domain.Member;
import com.zhong.ssm.domain.Order;
import com.zhong.ssm.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface OrderDao {
    @Select("select * from orders")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "com.zhong.ssm.dao.ProductDao.findById"))})
    public List<Order> findAll();

    @Select("select * from orders where id=#{orderId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "productId", column = "productId"),
            @Result(property = "memberId", column = "memberId"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "com.zhong.ssm.dao.ProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select = "com.zhong.ssm.dao.MemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType =java.util.List.class,many = @Many(select = "com.zhong.ssm.dao.TravellerDao.findByOrderId"))
    })
    public Order findById(Integer orderId);

    @Delete("delete from order_traveller where orderId=#{oid}")
    void deleteOrder_TravellerById(Integer oid);

    @Delete("delete from orders where id=#{oid}")
    void deleteOrderById(Integer oid);

    @Insert("insert into orders(orderNum,orderTime,peopleCount,orderDesc,payType,orderStatus,productId,memberId) values(#{orderNum},#{orderTime},#{peopleCount},#{orderDesc},#{payType},#{orderStatus},#{productId},#{memberId})")
    void save(Order order);

    @Update("update orders set orderNum=#{orderNum},orderTime=#{orderTime},peopleCount=#{peopleCount},orderDesc=#{orderDesc},payType=#{payType},orderStatus=#{orderStatus},productId=#{productId},memberId=#{memberId} where id=#{id}")
    void edit(Order order);
}
