package com.dispnt.mall.controller;


import com.dispnt.mall.Payload.RegisterCheckPayload;
import com.dispnt.mall.Payload.UpdateInfoPayload;
import com.dispnt.mall.model.Student;
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
import java.util.List;


@RequestMapping("/user")
@CrossOrigin("*")
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
    public Object userRegister(@RequestBody RegisterCheckPayload stuuser){
        Boolean checkQualified = studentRepository.existsByIdAndNameAndPhoneNumber(stuuser.getStuId(),stuuser.getName(),stuuser.getPhoneNumber());
        Boolean checkRegistered = userRepository.existsByStuId(stuuser.getStuId());
        User user = new User();
        if (checkQualified && !checkRegistered){

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
            return "RegisterFailed";
        }

    }


    @PostMapping("/authenticate")
    public Object authenticate(@RequestBody AuthenticatePayload user){
        User user1 = userRepository.findByUserName(user.getUserName());
        if (user1 != null){
            if (user1.getPassword().equals(user.getPassword()) ){
                return user1;
            }else{
                System.out.println("Wrong Pwd");
                return "Wrong Pwd or account";
            }
        }
        else{
            return "Wrong Pwd or account";
        }
    }


    @GetMapping("/getuser")
    public User getUserInfo(@RequestHeader (value="Authorization") String jwt){
        User user = userRepository.findByJsonWebToken(jwt);
        return user;
    }

    @GetMapping("/getstudent")
    public Student getStuedntInfo(@RequestParam("stuid") int stuid){
        Student student = studentRepository.findById(stuid);
        return student;
    }

    @PostMapping("/update")
    public void updateUserInfo(@RequestHeader (value="Authorization") String jwt, @RequestBody UpdateInfoPayload userupdate){
        User user = userRepository.findByJsonWebToken(jwt);
        user.setIntro(userupdate.getIntro());
        user.setPassword(userupdate.getPassword());
        user.setUserName(userupdate.getUserName());
        System.out.println("updated:"+userupdate.getUserName()+" intro to "+userupdate.getIntro());
        userRepository.save(user);

    }


    @PostMapping("/buy")
    public User buy(@RequestHeader (value="Authorization") String jwt, @RequestParam("itemid") int itemId){
        User user2 = userRepository.findByJsonWebToken(jwt);
        int user_id = user2.getId();
        Purchase_history purchaseHistory = new Purchase_history(user_id, itemId);
        purchaseHistoryRepository.save(purchaseHistory);
        User user0 = userRepository.findOne(purchaseHistory.getUserId());
        System.out.println("买了"+itemId);
        return userRepository.save(user0);
    }

    @PostMapping("/del")
    public int delItem(@RequestHeader (value="Authorization") String jwt, @RequestParam("itemid") int itemId){
        List<Purchase_history> item = purchaseHistoryRepository.findByItemIdAndUserId(itemId,userRepository.findByJsonWebToken(jwt).getId());
        purchaseHistoryRepository.delete(item.get(0).getId());
        return 0;
    }

    @GetMapping("/cart")
    public ArrayList<Item> getCart(@RequestHeader (value="Authorization") String jwt){
        User user2 = userRepository.findByJsonWebToken(jwt);
        Iterator<Purchase_history> iterator = user2.getPurchaseHistory().iterator();

        ArrayList<Item> item_list = new ArrayList<>();

        while((iterator).hasNext()){
            Purchase_history ph = iterator.next();
            item_list.add(itemRepository.findOne(ph.getItemId()));

        }

        return item_list;
    }


    @PostMapping("/sellerinfo")
    public String getSeller(@RequestParam("userid") int id){
        User u= userRepository.findById(id);
        String result = u.getUserName();
        return result;
    }

}
