package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.repositories.OrderRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;

@Controller
@RequestMapping("/statistics/")
public class StatisticsController {

    @Autowired
    private UserRepository ur;

    @Autowired
    private ProductRepository pr;

    @Autowired
    private OrderRepository or;

    @GetMapping("getStatisticsPage")
    public String getStatisticsPage(Model model) {
        long userCount = ur.count();
        long productCount = pr.count();
        long orderCount = or.count();
        Integer totalRevenue = or.sumTotalPrice(); 

        model.addAttribute("userCount", userCount);
        model.addAttribute("productCount", productCount);
        model.addAttribute("orderCount", orderCount);
        model.addAttribute("totalRevenue", totalRevenue);

        return "admin/statistics";
    }
}
