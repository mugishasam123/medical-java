package com.samuel.interfaces;

import com.samuel.utils.Message;
import com.samuel.models.User;

public interface IUser {
  public abstract Message<User> register() throws Exception;

  public void fromUser(User user);
}