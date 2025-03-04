import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "auth-service", url = "http://localhost:8082") // Replace with the actual URL of the Auth Service Microservice
public interface AuthServiceClient {
    @PostMapping("/v1/users")
    void createUser(@RequestBody Map<String, String> user);
}