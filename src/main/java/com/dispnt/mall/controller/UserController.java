package com.dispnt.mall.controller;


import com.dispnt.mall.Payload.BuyPayload;
import com.dispnt.mall.Payload.RegisterCheckPayload;
import com.dispnt.mall.model.Student;
import com.dispnt.mall.repository.ItemRepository;
import com.dispnt.mall.repository.PurchaseHistoryRepository;
import com.dispnt.mall.repository.StudentRepository;
import com.dispnt.mall.repository.UserRepository;
import com.dispnt.mall.Payload.AuthenticatePayload;
import com.dispnt.mall.model.Item;
import com.dispnt.mall.model.PurchaseHistory;
import com.dispnt.mall.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
            System.out.println("RegisterSuccess");
            return userRepository.save(user);
        }else {
            System.out.println("RegisterFailed");
            return null;
        }

    }

//    @PostMapping("/register1")
//    public String userRegister1(@RequestBody RegisterCheckPayload user){
//        Boolean check = studentRepository.existsByIdAndNameAndPhoneNumber(user.getId(),user.getName(),user.getPhoneNumber());
//        System.out.println(check);
//        return "good";
//    }

    @PostMapping("/authenticate")
    public User authenticate(@RequestBody AuthenticatePayload user){

        Boolean check = userRepository.existsByStuId(user.getStuId());

        if (check){
            List<User> userList = userRepository.findBystuId(user.getStuId());
            User real_user = userList.get(0);

            if (real_user.getPassword().equals(user.getPassword()) ){
                return real_user;
            }else{
                return null;
            }

        }else{
            return null;
        }



    }


    @PostMapping("/buy")
    public User checkHistory(@RequestHeader (value="Authorization") String jwt, @RequestBody BuyPayload item){
        List<User> userList = userRepository.findByJsonWebToken(jwt);
        User user2 = userList.get(0);
        int item_id = item.getItemId();
        int user_id = user2.getId();
        PurchaseHistory purchaseHistory = new PurchaseHistory(user_id, item_id);
        purchaseHistoryRepository.save(purchaseHistory);
        User user0 = userRepository.findOne(purchaseHistory.getUserId());

        return userRepository.save(user0);
    }

    @GetMapping("/purchase/history")
    public ArrayList<Item> buyItem(@RequestHeader (value="Authorization") String jwt){
        List<User> userList = userRepository.findByJsonWebToken(jwt);
        User user2 = userList.get(0);
        Iterator<PurchaseHistory> iterator = user2.getPurchaseHistory().iterator();
//        购买列表
        ArrayList<Item> item_list = new ArrayList<>();

        while((iterator).hasNext()){
//            有就添加数量
            PurchaseHistory ph = (PurchaseHistory)iterator.next();
            item_list.add( itemRepository.findOne(ph.getItemId()));
        }

        return item_list;
    }
}
