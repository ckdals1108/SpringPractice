package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {
//ctr + r 이전에 실행했떤거 실행하기
    MemberService memberService;
    MemoryMemberRepository memberRepository;
    //다른 인스턴스(주소)를 갖고있기때문에 같게 맞춰주는게 좋다

    @BeforeEach //이렇게 작성하면 같은 메모리 리포지토리를 사용하게 된다. 이런것을 DI라고 한다.
    public void bforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }


    @Test //한글로도 입력가능함.
    void 회원가입() {
        // given 주어졌을때(이러한 데이터 기반)
        Member member = new Member();
        member.setName("hellio");

        // when 이러한경우일때
        Long saveId = memberService.join(member);

        // then 주어져야함
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        /* try{
            memberService.join(member2);
            fail();
        } catch(IllegalArgumentException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다,.123");
        }*/
        //커맨드 옵션 + v 해당 변수로 만들기
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}