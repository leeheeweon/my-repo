package com.practice.helloboard.repository;

import com.practice.helloboard.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save (Member member);

    Optional<Member> findById(String id);

    Member findBySeq(long seq);

    Optional<Member> checkLogin(String id , String password);

    Optional<Member> findByName(String name);

    List<Member> findAll();
}
