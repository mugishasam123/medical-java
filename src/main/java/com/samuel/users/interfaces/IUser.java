package com.samuel.users.interfaces;

import com.samuel.utils.Message;
import com.samuel.users.models.User;

public interface IUser {
  public abstract Message<User> register() throws Exception;

  public void fromUser(User user);
}