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

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy()); // 필드 주입 테스트 시 주석
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new RateDiscountPolicy();
    }
}
