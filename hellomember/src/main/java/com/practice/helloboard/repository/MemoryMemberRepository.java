package com.practice.helloboard.repository;

import com.practice.helloboard.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setSequence(++sequence);
        store.put(member.getSequence(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(String id) {
        return store.values().stream().filter(member -> member.getId().equals(id)).findAny();
    }

    @Override
    public Member findBySeq(long seq) {
        return store.get(seq);
    }

    @Override
    public Optional<Member> checkLogin(String id, String password) {
       return store.values().stream().filter(member -> member.getId().equals(id) && member.getPassword().equals(password)).findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void updateMember(long seq, Member member) {
        Member findBySeqMember = findBySeq(seq);
        findBySeqMember.setId(member.getId());
        findBySeqMember.setName(member.getName());
    }

    @Override
    public void deleteMember(long seq) {
        store.remove(seq);
    }

    public void storeClear() {
        store.clear();
    }
}
