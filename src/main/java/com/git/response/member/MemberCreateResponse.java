package com.git.response.member;

import com.git.domain.member.Address;
import com.git.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberCreateResponse {

    private String username;
    private String name;
    private String phoneNum;
    private String email;
    private Address address;

    public static MemberCreateResponse toSave(Member member) {
        MemberCreateResponse response = new MemberCreateResponse();
        response.setUsername(member.getUsername());
        response.setName(member.getName());
        response.setPhoneNum(member.getPhoneNum());
        response.setEmail(member.getEmail());
        response.setAddress(member.getAddress());
        return response;
    }
}
