package com.practice.helloboard.domain;

import lombok.Data;

@Data
public class Member {
    private Long sequence;
    private Long id;
    private String name;
    private String password;

    public Member() {
    }

    public Member(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

}
