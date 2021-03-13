package com.dispnt.mall.Payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticatePayload {
    private String userName;
    private String password;

}
