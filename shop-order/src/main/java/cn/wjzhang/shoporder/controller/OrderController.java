package cn.wjzhang.shoporder.controller;

import cn.wjzhang.shopcommon.entity.Order;
import cn.wjzhang.shopcommon.entity.Product;
import cn.wjzhang.shoporder.service.OrderService;
import cn.wjzhang.shoporder.service.ProductService;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private OrderService orderService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private ProductService productService;

//    //准备买1件商品
////    @GetMapping("/order/prod/{pid}")
////    public Order order(@PathVariable("pid") Integer pid) {
////        log.info(">>客户下单，这时候要调用商品微服务查询商品信息");
//////通过restTemplate调用商品微服务
////        Product product = restTemplate.getForObject("http://localhost:8081/product/" + pid, Product.class);
////
////        Order order = new Order();
////        order.setUid(product.getPid());
////        orderService.save(order);
////        return order;
////    }

//    @GetMapping("/order/prod/{pid}")
//    public Order order(@PathVariable("pid") Integer pid) {
//        log.info(">>客户下单，这时候要调用商品微服务查询商品信息");
////从nacos中获取服务地址
//        List<ServiceInstance> instances =
//                discoveryClient.getInstances("service-product") ;
//        int index = new Random().nextInt(instances.size());
//        ServiceInstance serviceInstance = instances.get(index);
//
//        String url = serviceInstance.getHost() + ":" + serviceInstance.getPort();
//        log.info(">>从nacos中获取到的微服务地址为:" + url);
////通过restTemplate调用商品微服务
//        Product product = restTemplate.getForObject(
//                "http://" + url + "/product/" + pid, Product.class);
//        log.info(">>商品信息,查询结果:" + JSON.toJSONString(product));
//        Order order = new Order();
//        order.setUsername("测试用户");
//        order.setUid(product.getPid());
//        orderService.save(order);
//        return order;
//    }


//    @GetMapping("/order/prod/{pid}")
//    public Order order(@PathVariable("pid") Integer pid) {
//        log.info(">>客户下单，这时候要调用商品微服务查询商品信息");
//
//        String url = "service-product";
//        ServiceInstance serviceInstance = this.loadBalancerClient.choose(url);
//// 打印当前调用服务的信息
//        log.info("===" + ":" + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":"
//                + serviceInstance.getPort());
//
//        log.info(">>从nacos中获取到的微服务地址为:" + url);
////通过restTemplate调用商品微服务
//        Product product = restTemplate.getForObject(
//                "http://" + url + "/product/" + pid, Product.class);
//        log.info(">>商品信息,查询结果:" + JSON.toJSONString(product));
//        Order order = new Order();
//        order.setUsername("测试用户");
//        order.setUid(product.getPid());
//        orderService.save(order);
//        return order;
//    }

    @GetMapping("/order/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        log.info(">>客户下单,这时候要调用商品微服务查询商品信息");
//通过fegin调用商品微服务
        Product product = productService.findByPid(pid);
        log.info(">>商品信息,查询结果:" + JSON.toJSONString(product));



        //模拟一次网络延时
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        ServiceInstance serviceInstance = this.loadBalancerClient.choose("service-product");
//// 打印当前调用服务的信息
        log.info("===" + ":" + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":"
                + serviceInstance.getPort());
        Order order = new Order();
        order.setUsername("测试用户");
        order.setUid(product.getPid());
//        orderService.save(order);
        return order;
    }
    @RequestMapping("/order/message")
    public String message() {
        return "高并发下的问题测试";
    }

    @RequestMapping("/order/message1")
    public String message1() {
        return "message1";
    }
    @RequestMapping("/order/message2")
    public String message2() {
        return "message2";
    }

}