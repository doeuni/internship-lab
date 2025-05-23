package com.intern.integration.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intern.integration.api.service.AQService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class WebViewController {

    private final AQService aQService;
    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @PostMapping("/residency")
    public String callApi(@RequestParam("fnm") String name, Model model){
        //api 호출
        Map<String, Object> result = aQSerivce.getResidencyInfoJsp(name);
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResult = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);

            model.addAttribute("result1", jsonResult);

        } catch (Exception e){
            model.addAttribute("result1", "JSON변환오류"+ e.getMessage());
        };

        model.addAttribute("formName1", name);
        model.addAttribute("formName2", "");
        return "home";

    }


    @PostMapping("/income")
    public String callIncome(@RequestParam("fnm") String name, Model model){
        //api 호출
        Map<String, Object> result = aQService.getIncomePerson(name);

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonResult = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result);

            model.addAttribute("result2", jsonResult);

        } catch (Exception e){
            model.addAttribute("result2", "JSON변환오류"+ e.getMessage());
        };

        model.addAttribute("formName1", "");
        model.addAttribute("formName2", name);
        return "home";

    }

}
