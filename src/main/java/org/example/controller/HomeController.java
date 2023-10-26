package org.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.config.ConfigServiceImpl;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HomeController {
    private final Environment environment;
    private final ConfigServiceImpl configService;

    @GetMapping(value = "/get")
    public String get() throws JsonProcessingException {
        Map<String, String> map = new ObjectMapper().readValue(environment.getProperty("a"),
                new TypeReference<Map<String, String>>() {
                });
                System.out.println(map);
        return map.toString();
    }

    @GetMapping(value = "/reload")
    public String reload() {
        try {
            log.info("Going to reload properties");
            configService.refreshProps((ConfigurableEnvironment) environment);
            return "Success";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while reloading";
        }
    }
}
