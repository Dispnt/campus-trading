package com.dispnt.mall.Payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateInfoPayload {
    private String password;

    private String intro;

    private String userName;

}
