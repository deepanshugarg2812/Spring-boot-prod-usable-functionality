package org.example.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.entity.ConfigMaster;
import org.example.repo.ConfigRepo;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConfigServiceImpl {
    private final ConfigRepo configRepo;
    public void refreshProps(ConfigurableEnvironment environment) {
        log.info("Inside refreshProps");
        Map<String, Object> map = readProperties();
        MapPropertySource mapPropertySource = new MapPropertySource("CACHED_PROPERTY_SOURCE", map);
        environment.getPropertySources().addFirst(mapPropertySource);
        log.info("Properties updated");
    }

    private Map<String, Object> readProperties() {
        Map<String, Object> map = new HashMap<>();
        try {
            List<ConfigMaster> configMasters = configRepo.findAll();
            configMasters.stream().forEach((props) -> {
                log.info("Loaded the {} , {}", props.getKey(), props.getValue());
                map.put(props.getKey(), props.getValue());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
