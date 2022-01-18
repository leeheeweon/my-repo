package com.practice.helloboard.controller;

import com.practice.helloboard.repository.MemberRepository;
import com.practice.helloboard.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
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

    @GetMapping("mainPage")
    public String main() {
        return "/basic/mainPage";
    }

    @PostMapping("/login")
    public String login(String id, String password) {
        Optional<Member> member = memberRepository.checkLogin(id, password);
        System.out.println("member = " + member.toString());
        if (member.isEmpty()) {
            return "redirect:login";
        } else {
            return "redirect:mainPage";
        }
    }

    @GetMapping("/join")
    public String joinPage() {
        return "/basic/join";
    }

    @PostMapping("/join")
    public String join(Member member, RedirectAttributes redirectAttributes) {
        Member save = memberRepository.save(member);
        redirectAttributes.addAttribute("name", save.getName());
        return "redirect:/joinsuccess";
    }

    @GetMapping("/joinsuccess")
    public String joinSuccess() {
        return "/basic/joinSuccess";
    }

    @PostConstruct
    private void init() {
        memberRepository.save(new Member("limiter1", "leeheeweon1", "1111"));
        memberRepository.save(new Member("limiter2", "leeheeweon2", "1111"));
        memberRepository.save(new Member("limiter3", "leeheeweon3", "1111"));
    }
}
