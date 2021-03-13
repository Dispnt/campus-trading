package com.dispnt.mall.Payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemDetailPayload {
    private String sellerName;

    private int id;

    private String name;

    private int price;

    private String imgUrl;

    private String productComment;

    private int sellerId;

//    private Collection<PurchaseHistory> purchaseHistory;
}
