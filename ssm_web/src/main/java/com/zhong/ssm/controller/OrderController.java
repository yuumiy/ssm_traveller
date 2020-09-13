package com.zhong.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.zhong.ssm.domain.Member;
import com.zhong.ssm.domain.Order;
import com.zhong.ssm.domain.Product;
import com.zhong.ssm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    //查询所有订单--分页
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page, @RequestParam(name = "size", required = true, defaultValue = "5") Integer size) {
        ModelAndView modelAndView = new ModelAndView();
        List<Order> orderList = orderService.findAll(page, size);
        PageInfo pageInfo = new PageInfo(orderList);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("order-list");
        return modelAndView;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id", required = true) Integer orderId) {
        ModelAndView modelAndView = new ModelAndView();
        Order order = orderService.findById(orderId);
        modelAndView.addObject("order", order);
        modelAndView.setViewName("order-show");
        return modelAndView;
    }

    @RequestMapping("/deleteById.do")
    public String deleteById(@RequestParam(name = "id", required = true) Integer oid) throws Exception {
        orderService.deleteById(oid);
        return "redirect:findAll.do";
    }

    @RequestMapping("/add.do")
    public ModelAndView add() {
        ModelAndView modelAndView = new ModelAndView();
        List<Member> memberList = orderService.findAllMembers();
        List<Product> productList = orderService.findAllProducts();
        modelAndView.addObject("memberList", memberList);
        modelAndView.addObject("productList", productList);
        modelAndView.setViewName("order-add");
        return modelAndView;
    }

    @RequestMapping("/save.do")
    public String save(Order order) {
        orderService.save(order);
        return "redirect:findAll.do";
    }
    @RequestMapping("/editById.do")
    public ModelAndView editById(@RequestParam(name="id",required = true)Integer oid){
        ModelAndView mv=new ModelAndView();
        Order order=orderService.findById(oid);
        List<Member> memberList = orderService.findAllMembers();
        List<Product> productList = orderService.findAllProducts();
        Integer productId=order.getProductId();
        Integer memberId=order.getMemberId();
        for (Member member : memberList) {
            member.setTempId(memberId);
        }
        for (Product product : productList) {
            product.setTempId(productId);
        }
        mv.addObject("memberList", memberList);
        mv.addObject("productList", productList);
        mv.addObject("order",order);
        mv.setViewName("order-edit");
        return mv;
    }
    @RequestMapping("/edit.do")
    public String edit(Order order){
        orderService.edit(order);
        return "redirect:findAll.do";
    }
}
