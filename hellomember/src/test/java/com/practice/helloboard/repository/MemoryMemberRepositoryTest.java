package com.practice.helloboard.repository;

import com.practice.helloboard.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;


class MemoryMemberRepositoryTest {

    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @AfterEach
    void afterEach() {
        memberRepository.storeClear();
    }


    @Test
    void save(){
        Member member = new Member("123", "lee", "123");
        Optional<Member> saveMember = Optional.ofNullable(memberRepository.save(member));
        System.out.println("member = " + member.getId());
        Optional<Member> findMember = memberRepository.findById(member.getId());
        Assertions.assertThat(saveMember).isEqualTo(findMember);
    }

    @Test
    void findAll() {
        Member member1 = new Member("123", "lee1", "123");
        Member member2 = new Member("1234", "lee2", "1234");

        memberRepository.save(member1);
        memberRepository.save(member2);

        List<Member> all = memberRepository.findAll();
        Assertions.assertThat(all.size()).isEqualTo(2);
        Assertions.assertThat(all).contains(member1, member2);
    }

    @Test
    void findByName() {
        Member member = new Member("123", "lee", "123");
        Optional<Member> saveMember = Optional.ofNullable(memberRepository.save(member));

        Optional<Member> findByNameMember = memberRepository.findByName(member.getName());

        Assertions.assertThat(saveMember).isEqualTo(findByNameMember);
    }

    @Test
    void checkLogin() {
        Member member = new Member("123", "lee", "123");
        Optional<Member> saveMember = Optional.ofNullable(memberRepository.save(member));

        Optional<Member> checkedMember = memberRepository.checkLogin("123", "123");
        Assertions.assertThat(saveMember).isEqualTo(checkedMember);
    }


}