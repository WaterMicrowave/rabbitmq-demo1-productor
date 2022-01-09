package com.example.rabbitmqdemo1;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
/**
 * @Author : 李向平
 * @CreateTime : 2022/1/9
 * @Description :
 *  该配置类：以下绑定关系如果在RabbitMQ_management管理界面绑定可以不需要
 **/
@Configuration
public class DirectRabbitConfig {

    //Direct交换机（会和yml文件的虚拟主机绑定）
    @Bean
    DirectExchange Exchange() {
        return new DirectExchange("jiaohuanji",true,false);
    }

    //队列 起名：order
    @Bean
    public Queue TestDirectQueueOrder() {
        // durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
        // exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
        // autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
        //   return new Queue("order",true,true,false);
        //一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue("order",true);
    }
    //队列 起名：coupon
    @Bean
    public Queue TestDirectQueueCoupon() {
        return new Queue("coupon",true);
    }
    //队列 起名：level
    @Bean
    public Queue TestDirectQueueLevel() {
        return new Queue("level",true);
    }

    /**
     * 队列和交换机绑定（也就是是绑定了虚拟主机）, 并设置用于路由键
     * @return
     */
    @Bean
    Binding bindingDirectOrder() {
        return BindingBuilder.bind(TestDirectQueueOrder()).to(Exchange()).with("key1");
    }
    //绑定  将队列和交换机绑定, 并设置用于匹配键：coupon
    @Bean
    Binding bindingDirectCoupon() {
        return BindingBuilder.bind(TestDirectQueueCoupon()).to(Exchange()).with("key1");
    }
    @Bean
    Binding bindingDirectCoupon2() {
        return BindingBuilder.bind(TestDirectQueueCoupon()).to(Exchange()).with("key2");
    }

}