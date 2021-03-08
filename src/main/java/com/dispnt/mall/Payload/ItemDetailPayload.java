package com.dispnt.mall.Payload;

import com.dispnt.mall.model.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemDetailPayload extends Item {
    private String sellerName;

}
