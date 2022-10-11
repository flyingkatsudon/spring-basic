package com.basic.order;

import com.basic.annotation.MainDiscountPolicy;
import com.basic.member.Member;
import com.basic.member.MemberRepository;
import com.basic.discount.DiscountPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    // final 키워드가 붙으면 생성자에서만 주입 가능
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 수정자 메서드 -> 선택적 의존 관계, 변경 가능성 있는 의존 관계
    /*@Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }*/

    // 생성자 주입 -> 가급적 필수, 불변인 값
    // 위의 setter가 있으면 이게 필요 없어도 주입 가능
    //@Autowired //-> 생성자가 1개 있다면 자동으로 @Autowired가 붙음
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 아무 메서드에나 생성자처럼 구현 -> 일반 메서드 주입은 잘 사용하지 않음
    /*@Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
