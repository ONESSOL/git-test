package com.git.request.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PasswordChangeRequest {

    private String beforePassword;
    private String afterPassword;
    private String checkAfterPassword;


}
