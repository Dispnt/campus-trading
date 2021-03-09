package com.dispnt.mall.Payload;

import com.dispnt.mall.model.Item;
import com.dispnt.mall.model.PurchaseHistory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.Collection;

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
