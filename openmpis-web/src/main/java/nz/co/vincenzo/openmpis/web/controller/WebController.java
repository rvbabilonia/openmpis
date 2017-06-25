package nz.co.vincenzo.openmpis.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Marvic on 17/06/2017.
 */
@RestController
public class WebController {

    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;

    @Autowired
    public WebController(DiscoveryClient discoveryClient, RestTemplate restTemplate) {
        this.discoveryClient = discoveryClient;
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/case")
    public String getCase() {
        ServiceInstance serviceInstance = getServiceInstance("case-service");
        return restTemplate.getForObject(serviceInstance.getUri().toString() + "/case", String.class);
    }

    @RequestMapping("/user")
    public String getUser() {
        ServiceInstance serviceInstance = getServiceInstance("user-service");
        return restTemplate.getForObject(serviceInstance.getUri().toString() + "/user", String.class);
    }

    private ServiceInstance getServiceInstance(String serviceId) {
        return discoveryClient.getInstances(serviceId).get(0);
    }
}
