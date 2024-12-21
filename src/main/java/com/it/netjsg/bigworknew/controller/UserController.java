package com.it.netjsg.bigworknew.controller;

import com.it.netjsg.bigworknew.pojo.Result;
import com.it.netjsg.bigworknew.pojo.User;
import com.it.netjsg.bigworknew.service.UserService;
import com.it.netjsg.bigworknew.utils.JwtUtil;
import com.it.netjsg.bigworknew.utils.Md5Util;
import com.it.netjsg.bigworknew.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.apache.ibatis.io.ResolverUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    //导入UserService
    @Autowired
    private UserService userService;
    //注册
    @PostMapping("/register")
    public Result<String> register(@Pattern(regexp = "^\\S{5,16}") String username, String password){
        //查询是否存在
        User user=userService.findUser(username);
        if(user!=null){
            return    Result.error("用户名已存在");


        }
        //调用service层注册方法，同时进行MD5加密
        userService.register(username,password);
        return Result.success("注册成功");
    }
    //登录
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}") String username,  @Pattern(regexp = "^\\S{5,16}$")  String password){
        //根据用户名查询用户
        User loginUser = userService.findUser(username);
        //判断该用户是否存在
        if (loginUser == null) {
            return Result.error("用户名错误");
        }

        //判断密码是否正确  loginUser对象中的password是密文
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())) {
            //登录成功
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }
    /**
     * 获取已经登陆用户信息
     * 因为是已经登陆的，所以这个请求必须先登录
     *前端不传任何参数，需要从ThreadLocal中直接获取当前用户信息
     */
    @GetMapping("userInfo")
    public Result<User> userInfo(){
        //根据用户名查询用户
       /* Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("username");*/
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        System.out.println(username);
        User user = userService.findUser(username);
        return Result.success(user);
    }
    /**
     * 修改用户信息
     * @param user
     * @return Result<String>
     */
    @PutMapping("/update")
    public Result<String> update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success("修改成功");
    }
    //更新用户头像
    @PatchMapping("updateAvatar")
    public Result<String> updateAvatar(String avatarUrl){
        //从ThreadLocal中获取当前用户信息
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userService.updateAvatar(id,avatarUrl);
        return Result.success("修改成功");
    }
    //更新用户密码
    @PatchMapping("updatePassword")
    public Result<String> updatePassword(@RequestBody Map<String, String> params){
        //获取传送的json数据
        String oldPassword = params.get("old_pwd");
        String newPassword = params.get("new_pwd");
        String renewPassword = params.get("re_pwd");
        //获取用户信息
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User User = userService.findUser(username);
        //判断原密码是否正确
        if (!Md5Util.checkPassword(oldPassword,User.getPassword())){
            return Result.error("原密码错误");
        }
        //如果两次确认密码不一致
        if (!newPassword.equals(renewPassword)){
            return Result.error("两次密码不一致");
        }
        //满足要求
        userService.updatePassword(User.getId(),newPassword);
        return Result.success("修改成功");

    }






}
