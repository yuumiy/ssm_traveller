package com.zhong.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.zhong.ssm.domain.Product;
import com.zhong.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RequestMapping("/product")
@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name="page",required = true,defaultValue = "1")Integer page, @RequestParam(name="size",required = true,defaultValue = "5")Integer size){
        ModelAndView modelAndView=new ModelAndView();
        List<Product> productList=productService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(productList);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.setViewName("product-list");
        return modelAndView;
    }
    @RequestMapping("/save.do")
    public String save(Product product){
        productService.save(product);
        return "redirect:findAll.do";
    }
    @RequestMapping("/deleteById.do")
    public String deleteById(@RequestParam(name="id",required = true)Integer pid){
        productService.deleteById(pid);
        return "redirect:findAll.do";
    }
    @RequestMapping("/editById.do")
    public ModelAndView editById(@RequestParam(name="id",required = true)Integer pid){
        ModelAndView mv=new ModelAndView();
        Product product=productService.findById(pid);
        mv.addObject("product",product);
        mv.setViewName("product-edit");
        return mv;
    }
    @RequestMapping("/edit.do")
    public String edit(Product product){
        productService.edit(product);
        return "redirect:findAll.do";
    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name="id",required = true)Integer pid){
        ModelAndView mv=new ModelAndView();
        Product product=productService.findById(pid);
        mv.addObject("product",product);
        mv.setViewName("product-show");
        return mv;
    }
}
