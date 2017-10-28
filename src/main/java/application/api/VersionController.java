package application.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
    @RequestMapping("/version")
    public Version version() {
        return new Version("1.0-SNAPSHOT");
    }
}
