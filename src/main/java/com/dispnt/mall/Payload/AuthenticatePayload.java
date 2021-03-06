package com.dispnt.mall.Payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticatePayload {
    private Integer stuId;
    private String password;

}
