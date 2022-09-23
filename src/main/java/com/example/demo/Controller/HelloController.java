package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")//get post
    public String hello(Model model) { //스프링 모델
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody
    //response에 body 부분을 직접 넣어주겠다
    public String helloSTring(@RequestParam("name") String name) {
        return "hello" + name;//hello spring
    }

    @GetMapping("hello-api")
    @ResponseBody //데이터를 그대로 보내야 겠다고 보냄, view controller로 보내는것이 아닌
    public Hello hellApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //responsebody를 객체 반환하면 json 방식으로 반환
        //<></> => XML방식
    }

    static class Hello{
        private String name;
        //alt + insert => getter, setter
        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
