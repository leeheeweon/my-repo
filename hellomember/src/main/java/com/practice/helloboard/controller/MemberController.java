package com.practice.helloboard.controller;

import com.practice.helloboard.domain.Member;
import com.practice.helloboard.repository.MemoryMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/members")
public class MemberController {
    MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository();

    @GetMapping
    public String members(Model model) {
        List<Member> members = memoryMemberRepository.findAll();
        model.addAttribute("members", members);
        return "/member/members";
    }

    @GetMapping("/{memberSeq}")
    public String member(@PathVariable long memberSeq, Model model) {
        Member member = memoryMemberRepository.findBySeq(memberSeq);
        model.addAttribute("member", member);
        return "/member/member";
    }

    @GetMapping("/{memberSeq}/edit")
    public String editForm(@PathVariable long memberSeq, Model model) {
        Member member = memoryMemberRepository.findBySeq(memberSeq);
        model.addAttribute("member", member);
        return "/member/editForm";
    }

    @PostMapping("/{memberSeq}/edit")
    public String edit(@PathVariable long memberSeq, Member member, RedirectAttributes redirectAttributes) {
        memoryMemberRepository.updateMember(memberSeq, member);
        redirectAttributes.addAttribute("memberSeq", memberSeq);
        return "redirect:/members/{memberSeq}";
    }

    @PostMapping("/{memberSeq}/delete")
    public String delete(@PathVariable long memberSeq) {
        memoryMemberRepository.deleteMember(memberSeq);
        return "redirect:/members";
    }
}
