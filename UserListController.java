package com.example.sharingapp;

import android.content.Context;

import java.util.ArrayList;

/**
 * UserListController is responsible for all communication between views and UserList object
 */
public class UserListController {

    private UserList user_list;

    public UserListController(UserList user_list){
        this.user_list = user_list;
    }

    public void setUsers(ArrayList<User> user_list) {
        this.user_list.setUsers(user_list);
    }

    public ArrayList<User> getUsers() {
        return user_list.getUsers();
    }

    public ArrayList<String> getAllUsernames(){
        return  user_list.getAllUsernames();
    }

    public boolean addUser(User user, Context context) {
        AddUserCommand add_user_command = new AddUserCommand(user_list, user, context);
        add_user_command.execute();
        return add_user_command.isExecuted();
    }

    public boolean deleteUser(User user, Context context) {
        DeleteUserCommand delete_user_command = new DeleteUserCommand(user_list, user, context);
        delete_user_command.execute();
        return delete_user_command.isExecuted();
    }

    public boolean editUser(User user, User updated_user, Context context){
        EditUserCommand edit_user_command = new EditUserCommand(user_list, user, updated_user, context);
        edit_user_command.execute();
        return edit_user_command.isExecuted();
    }

    public User getUser(int index) {
        return user_list.getUser(index);
    }

    public int getSize() {
        return user_list.getSize();
    }

    public User getUserByUsername( String username) {
        return user_list.getUserByUsername(username);
    }

    public boolean hasUser(User user) {
        return user_list.hasUser(user);
    }

    public int getIndex(User user) {
        return user_list.getIndex(user);
    }

    public void loadUsers(Context context) {
        user_list.loadUsers(context);
    }

    public boolean isUsernameAvailable(String username){
        return user_list.isUsernameAvailable(username);
    }

    public void addObserver(Observer observer) {
        user_list.addObserver(observer);
    }

    public void removeObserver(Observer observer) {
       user_list.removeObserver(observer);
    }
}
