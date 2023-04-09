package com.abc.provider.controller;

import com.abc.provider.bean.Depart;
import com.abc.provider.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/provider/depart")
@RestController
public class DepartController {
    @Autowired
    private DepartService service;
    // 服务发现客户端
    @Autowired
    private DiscoveryClient client;

    @PostMapping("/save")
    public boolean saveHandle(@RequestBody Depart depart) {
        return service.saveDepart(depart);
    }

    @DeleteMapping("/del/{id}")
    public boolean deleteHandle(@PathVariable("id") int id) {
        return service.removeDepartById(id);
    }

    @PutMapping("/update")
    public boolean updateHandle(@RequestBody Depart depart) {
        return service.modifyDepart(depart);
    }

    @GetMapping("/get/{id}")
    public Depart getHandle(@PathVariable("id") int id, HttpServletRequest request) {
        String remoteAddr = request.getRemoteAddr();
        int remotePort = request.getRemotePort();
        System.out.println("remoteAddr = " + remoteAddr);
        System.out.println("remotePort = " + remotePort);

        return service.getDepartById(id);
    }

    @GetMapping("/list")
    public List<Depart> listHandle() {
        return service.listAllDeparts();
    }

    @GetMapping("/discovery")
    public List<String> discoveryHandle() {
        // 获取Eureka Server中所有注册的微服务名称
        List<String> services = client.getServices();
        // 遍历所有微服务名称
        for (String service : services) {
            // 获取到当前遍历微服务名称所对应的所有instance
            List<ServiceInstance> instances = client.getInstances(service);
            // 遍历所有instance
            for (ServiceInstance instance : instances) {
                String instanceId = instance.getInstanceId();
                String serviceId = instance.getServiceId();
                String host = instance.getHost();
                int port = instance.getPort();
                System.out.println("instanceId = " + instanceId);
                System.out.println("serviceId = " + serviceId);
                System.out.println("host = " + host);
                System.out.println("port = " + port);
            }
        }

        return services;
    }
}
