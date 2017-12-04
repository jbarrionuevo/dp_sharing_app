package com.example.sharingapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Edit preexisting User: editing a user consists of deleting the old user and adding a new user
 * with the old user's id.
 * Note: You will not be able contacts which are "active" borrowers
 */
public class EditUserActivity extends AppCompatActivity implements Observer {

    private UserList user_list = new UserList();
    private UserListController user_list_controller = new UserListController(user_list);

    private User user;
    private UserController user_controller;
    private EditText email;
    private EditText username;
    private Context context;
    private int pos;
    private boolean on_create_update = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        Intent intent = getIntent();
        pos = intent.getIntExtra("position", 0);

        context = getApplicationContext();
        user_list_controller.addObserver(this);
        user_list_controller.loadUsers(context);
        on_create_update = false;
    }

    public void saveUser(View view) {

        String email_str = email.getText().toString();

        if (email_str.equals("")) {
            email.setError("Empty field!");
            return;
        }

        String username_str = username.getText().toString();

        // Check that username is unique AND username is changed (Note: if username was not changed
        // then this should be fine, because it was already unique.)
        if (!user_list_controller.isUsernameAvailable(username_str) &&
                !(user.getUsername().equals(username_str))){
            username.setError("Username already taken!");
            return;
        }

        // Reuse the user id
        String id_str = user_controller.getId();
        User updated_user = new User(username_str, email_str, id_str);

        // Edit User: replace user with updated user
        boolean success = user_list_controller.editUser(user, updated_user, context);
        if (!success) {
            return;
        }

        // End EditUserActivity
        finish();
    }

    public void deleteUser(View view) {

        // Delete user
        boolean success = user_list_controller.deleteUser(user, context);
        if (!success) {
            return;
        }

        // End EditUserActivity
        finish();
    }

    /**
     * Called when the activity is destroyed, thus we remove this activity as a listener
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        user_list_controller.removeObserver(this);
    }

    /**
     * Only need to update the view from the onCreate method
     */
    public void update(){

        if (on_create_update) {

            user = user_list_controller.getUser(pos);
            user_controller = new UserController(user);

            username = (EditText) findViewById(R.id.username);
            email = (EditText) findViewById(R.id.email);

            // Update the view
            username.setText(user_controller.getUsername());
            email.setText(user_controller.getEmail());
        }
    }
}
