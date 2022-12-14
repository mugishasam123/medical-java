package com.samuel.models;

import java.util.UUID;

import javax.naming.AuthenticationException;

import com.google.gson.annotations.Expose;
import com.samuel.db.Database;
import com.samuel.utils.GenerateJwt;
import com.samuel.utils.ApiResponse;
import com.samuel.types.GenderTypes;
import com.samuel.types.UserTypes;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public abstract class User {
    public String id;
    @NonNull
    public String email;
    @NonNull
    public String firstName;
    @NonNull
    private String lastName;
    @Expose(serialize = false)
    private String password;
    private GenderTypes gender;
    private Integer age;
    public UserTypes role;

    public User() {
        id = UUID.randomUUID().toString();
    }

    public abstract ApiResponse<User> register() throws Exception;

    public abstract void createUser(User user);

    public void encrptPass() {
        String encryptedPassword = "";
        for (int i = 0; i < this.password.length(); i++) {
            encryptedPassword = this.password.charAt(i) + encryptedPassword;
        }
        encryptedPassword = "**" + encryptedPassword + "</>*" + this.role + "**";
        this.setPassword(encryptedPassword);
    }

    public String decrptPass() {
        String decryptedPwd = "";
        String reversedPwd = this.password.substring(2, this.password.length() - 2).split("</>*")[0];
        for (int i = 0; i < reversedPwd.length(); i++) {
            decryptedPwd = reversedPwd.charAt(i) + decryptedPwd;
        }
        return decryptedPwd;
    }

    public ApiResponse<String> login(String email, String Password) throws Exception {
        User foundedUser = Database.findUser(email);

        if (foundedUser == null)
            throw new AuthenticationException("You entered Invalid Credentials");

        if (!Password.equals(foundedUser.decrptPass()))
            throw new AuthenticationException("You entered Invalid Credentials");

        return new ApiResponse<String>("you have logged in successfully", GenerateJwt.generateJwt(foundedUser));
    }

}