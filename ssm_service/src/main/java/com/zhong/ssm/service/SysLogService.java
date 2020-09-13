package com.zhong.ssm.service;

import com.zhong.ssm.domain.SysLog;

import java.util.List;

public interface SysLogService {

    public void save(SysLog sysLog) throws Exception;

    List<SysLog> findAll(Integer page,Integer size) throws Exception;
}
