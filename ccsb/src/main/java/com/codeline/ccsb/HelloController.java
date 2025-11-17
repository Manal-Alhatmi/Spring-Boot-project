package com.codeline.ccsb;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(@RequestParam(defaultValue = "Guest") String name) {
        return "Hello " + name;
    }

    @GetMapping("/sum")
    public String sum(@RequestParam int a, @RequestParam int b) {
        return "Sum = " + (a + b);
    }

    @GetMapping("/info")
    public Map<String, String> info() {
        Map<String, String> info = new LinkedHashMap<>();
        info.put("name", "Manal");
        info.put("city", "Muscat");
        info.put("language", "Java");
        return info;
    }

    @GetMapping("/greet")
    public String greet(@RequestParam String name) {
        return "Welcome " + name + " to our website!";
    }

    @GetMapping("/upper")
    public String upper(@RequestParam String input) {
        return input.toUpperCase();
    }

    @GetMapping("/random")
    public int random() {
        return new Random().nextInt(100) + 1;
    }
}
