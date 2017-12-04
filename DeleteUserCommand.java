package com.example.sharingapp;

import android.content.Context;

/**
 * Command to delete a user
 */
public class DeleteUserCommand extends Command {

    private UserList user_list;
    private User user;
    private Context context;

    public DeleteUserCommand (UserList user_list, User user, Context context) {
        this.user_list = user_list;
        this.user = user;
        this.context = context;
    }

    public void execute() {
        user_list.deleteUser(user);
        super.setIsExecuted(user_list.saveUsers(context));
    }
}
