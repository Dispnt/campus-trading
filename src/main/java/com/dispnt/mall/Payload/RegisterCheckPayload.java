package com.dispnt.mall.Payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterCheckPayload {
    private String phoneNumber;
    private String name;
    private int stuId;
    private String password;
    private String intro;
    private String jsonWebToken;
}
