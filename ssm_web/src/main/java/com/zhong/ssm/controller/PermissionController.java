package com.zhong.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.zhong.ssm.domain.Permission;
import com.zhong.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/deletePermission")
    public String deletePermission(Integer id) throws Exception {
        permissionService.deleteById(id);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(Integer id) throws Exception {
      Permission permission=  permissionService.findById(id);
      ModelAndView mv=new ModelAndView();
      mv.setViewName("permission-show");
      mv.addObject("permission",permission);
      return mv;
    }

    @RequestMapping("/save.do")
    public String save(Permission permission) throws Exception {
        permissionService.save(permission);
        return "redirect:findAll.do";
    }
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="page",required = true,defaultValue = "1")Integer page, @RequestParam(name="size",required = true,defaultValue = "5")Integer size) throws Exception {
        ModelAndView mv=new ModelAndView();
        List<Permission> permissionList = permissionService.findAll(page, size);
        PageInfo pageInfo=new PageInfo(permissionList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("permission-list");
        return mv;
    }
}
