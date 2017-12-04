package com.example.sharingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * UserAdapter is responsible for what information is displayed in ListView entries.
 */
public class UserAdapter extends ArrayAdapter<User> {
    private LayoutInflater inflater;
    private Context context;

    public UserAdapter(Context context, ArrayList<User> contacts) {
        super(context, 0, contacts);
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // getItem(position) gets the contact ("user") at "position" in the "contacts" ArrayList
        // (where "contacts" is a parameter in the UserAdapter creator as seen above ^^)
        User user = getItem(position);
        UserController user_controller = new UserController(user);

        String username = "Username: " + user_controller.getUsername();
        String email = "Email: " + user_controller.getEmail();

        // Check if an existing view is being reused, otherwise inflate the view.
        if (convertView == null) {
            convertView = inflater.from(getContext()).inflate(R.layout.contact_item, parent, false);
        }

        TextView username_tv = (TextView) convertView.findViewById(R.id.username_tv);
        TextView email_tv = (TextView) convertView.findViewById(R.id.email_tv);
        ImageView photo = (ImageView) convertView.findViewById(R.id.contacts_image_view);

        photo.setImageResource(android.R.drawable.ic_menu_gallery);

        username_tv.setText(username);
        email_tv.setText(email);

        return convertView;
    }
}
