package com.zhong.ssm.dao;

import com.zhong.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MemberDao {
    @Select("select * from member where id=#{mid}")
    public Member findById(Integer mid);

    @Select("select * from member")
    List<Member> findAll();
}
