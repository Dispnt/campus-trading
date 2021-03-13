package com.dispnt.mall.controller;


import com.dispnt.mall.Payload.BuyPayload;
import com.dispnt.mall.Payload.RegisterCheckPayload;
import com.dispnt.mall.repository.ItemRepository;
import com.dispnt.mall.repository.PurchaseHistoryRepository;
import com.dispnt.mall.repository.StudentRepository;
import com.dispnt.mall.repository.UserRepository;
import com.dispnt.mall.Payload.AuthenticatePayload;
import com.dispnt.mall.model.Item;
import com.dispnt.mall.model.Purchase_history;
import com.dispnt.mall.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;

@CrossOrigin("*")
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PurchaseHistoryRepository purchaseHistoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/register")
    public User userRegister(@RequestBody RegisterCheckPayload stuuser){
        Boolean checkQualified = studentRepository.existsByIdAndNameAndPhoneNumber(stuuser.getStuId(),stuuser.getName(),stuuser.getPhoneNumber());
        Boolean checkRegistered = userRepository.existsByStuId(stuuser.getStuId());

        if (checkQualified && !checkRegistered){
            User user = new User();
            int intNum = (int)(Math.random() * 10000);
            user.setJsonWebToken(Integer.toString(intNum));
            user.setPassword(stuuser.getPassword());
            user.setIntro(stuuser.getIntro());
            user.setStuId(stuuser.getStuId());
            user.setUserName(stuuser.getUserName());
            System.out.println("RegisterSuccess");
            return userRepository.save(user);
        }else {
            System.out.println("RegisterFailed");
            return null;
        }

    }


    @PostMapping("/authenticate")
    public User authenticate(@RequestBody AuthenticatePayload user){
        User user1 = userRepository.findByUserName(user.getUserName());
        if (user1 != null){

            if (user1.getPassword().equals(user.getPassword()) ){
                return user1;
            }else{
                System.out.println("Wrong Pwd");
                return null;
            }


        }
        else{
            return null;
        }




    }


    @PostMapping("/buy")
    public User checkHistory(@RequestHeader (value="Authorization") String jwt, @RequestBody BuyPayload item){
        User user2 = userRepository.findByJsonWebToken(jwt);
        int item_id = item.getItemId();
        int user_id = user2.getId();
        Purchase_history purchaseHistory = new Purchase_history(user_id, item_id);
        purchaseHistoryRepository.save(purchaseHistory);
        User user0 = userRepository.findOne(purchaseHistory.getUserId());

        return userRepository.save(user0);
    }

    @GetMapping("/purchase/history")
    public ArrayList<Item> buyItem(@RequestHeader (value="Authorization") String jwt){
        User user2 = userRepository.findByJsonWebToken(jwt);
        Iterator<Purchase_history> iterator = user2.getPurchaseHistory().iterator();

        ArrayList<Item> item_list = new ArrayList<>();

        while((iterator).hasNext()){
            Purchase_history ph = iterator.next();
            item_list.add(itemRepository.findOne(ph.getItemId()));
            System.out.println(itemRepository.findOne(ph.getItemId()));

        }

        return item_list;
    }
}
