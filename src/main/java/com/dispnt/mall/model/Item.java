package com.dispnt.mall.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "imgUrl")
    private String imgUrl;

    @Column(name = "productComment")
    private String productComment;

    @Column(name = "seller_id")
    private int sellerId;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="item_id")
    private Collection<PurchaseHistory> purchaseHistory;
    public Collection<PurchaseHistory> getPurchaseHistory() {
        return purchaseHistory;
    }
}
