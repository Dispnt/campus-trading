package com.dispnt.mall.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.OneToMany;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;


    @Column(name = "jsonWebToken")
    private String jsonWebToken;

    @Column(name = "stuId")
    private int stuId;

    @Column(name = "password")
    private String password;

    @Column(name = "intro")
    private String intro;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private Collection<PurchaseHistory> purchaseHistory;
    public void addPurchase(PurchaseHistory purchase){
        if(purchaseHistory == null){
            purchaseHistory = new ArrayList<PurchaseHistory>();
        }
        purchaseHistory.add(purchase);
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="seller_id")
    @Fetch(FetchMode.SUBSELECT)
    private Collection<Item> items;
    public Collection<Item> getitems() {
        return items;
    }


}

