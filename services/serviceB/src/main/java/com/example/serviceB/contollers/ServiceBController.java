package com.example.serviceB.contollers;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("caller")
public class ServiceBController {

    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    public ServiceBController(DiscoveryClient discoveryClient, RestClient.Builder restClientBuilder) {
        this.discoveryClient = discoveryClient;
        restClient = restClientBuilder.build();
    }
    @GetMapping("hello/{name}")
    public String helloWorld(@PathVariable String name){
        ServiceInstance serviceInstance = discoveryClient.getInstances("servicea").getFirst();
        return restClient.get()
                .uri(serviceInstance.getUri() + "/cloud/hello/{name}", name)
                .retrieve()
                .body(String.class);
    }
}
