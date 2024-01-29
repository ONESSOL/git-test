package com.git.request.member;

import com.git.domain.member.Address;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberUpdateRequest {

    private String phoneNum;
    private String email;
    private Address address;

}
