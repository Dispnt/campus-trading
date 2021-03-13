package com.dispnt.mall.Payload;

import com.dispnt.mall.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterCheckPayload extends User {
    private String phoneNumber;
    private String name;
}