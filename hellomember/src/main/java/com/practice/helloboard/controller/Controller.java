package com.practice.helloboard.controller;

import com.practice.helloboard.repository.MemberRepository;
import com.practice.helloboard.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@org.springframework.stereotype.Controller
@Slf4j
@RequiredArgsConstructor
public class Controller {
    private final MemberRepository memberRepository;

    @GetMapping("/login")
    public String loginPage() {
        return "index";
    }

    @PostMapping("/login")
    public String login(Long id, String password, RedirectAttributes redirectAttributes) {
        Optional<Member> member = memberRepository.checkLogin(id, password);
        System.out.println("member = " + member.toString());
        if (member.isEmpty()) {
            return "redirect:login";
        } else {
            return "redirect:/members";
        }
    }

    @GetMapping("/join")
    public String joinPage() {
        return "join";
    }

    @PostMapping("/join")
    public String join(Member member, RedirectAttributes redirectAttributes) {
        Member save = memberRepository.save(member);
        System.out.println("save = " + save);
        return "redirect:/login";
    }


}
