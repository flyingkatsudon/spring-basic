package com.basic;

import com.basic.discount.DiscountPolicy;
import com.basic.discount.RateDiscountPolicy;
import com.basic.member.MemberRepository;
import com.basic.member.MemberService;
import com.basic.member.MemoryMemberRepository;
import com.basic.order.OrderServiceImpl;
import com.basic.member.MemberServiceImpl;
import com.basic.order.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
