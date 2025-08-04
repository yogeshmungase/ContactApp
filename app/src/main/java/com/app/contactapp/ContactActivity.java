package com.app.contactapp;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.app.contactapp.Model.Contact;
import com.app.contactapp.adapter.ContactAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Contact> contactList;
    private ContactAdapter adapter;
    private FloatingActionButton btnOpenDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        recyclerView = findViewById(R.id.recyclerView);
        btnOpenDialog = findViewById(R.id.btnOpenDialog);


        contactList = new ArrayList<>();
        adapter = new ContactAdapter(contactList, contact -> {

        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        // Load dummy contacts
        loadDummyContacts();

        // Open Add Contact Dialog
        btnOpenDialog.setOnClickListener(v -> showAddContactDialog());
    }

    private void loadDummyContacts() {
        contactList.add(new Contact(R.drawable.msd, "John Doe", "9876543210"));
        contactList.add(new Contact(R.drawable.person_icon, "Jane Smith", "9123456780"));
        contactList.add(new Contact(R.drawable.person_icon, "Robert John", "9998887776"));
        contactList.add(new Contact(R.drawable.person_icon, "Emily Davis", "8887776665"));
        contactList.add(new Contact(R.drawable.msd, "Mich Brown", "8776665554"));
        contactList.add(new Contact(R.drawable.person_icon, "Sarah Wilson", "8665554443"));
        contactList.add(new Contact(R.drawable.person_icon, "David Miller", "8554443332"));
        contactList.add(new Contact(R.drawable.person_icon, "Laura Moore", "8443332221"));
        contactList.add(new Contact(R.drawable.person_icon, "James ", "8332221110"));
        contactList.add(new Contact(R.drawable.msd, "Ava Thomas", "8221110009"));
        adapter.notifyDataSetChanged();
    }

    private void showAddContactDialog() {
        Dialog dialog = new Dialog(ContactActivity.this);
        dialog.setContentView(R.layout.add_update_contact);

        EditText edtName = dialog.findViewById(R.id.edtName);
        EditText edtNumber = dialog.findViewById(R.id.edtumber);
        Button btnAdd = dialog.findViewById(R.id.btnAction);

        btnAdd.setText("Add");
        dialog.setTitle("Add Contact");

        btnAdd.setOnClickListener(v -> {
            String name = edtName.getText().toString().trim();
            String number = edtNumber.getText().toString().trim();

            if (!name.isEmpty() && !number.isEmpty()) {
                contactList.add(new Contact(R.drawable.person_icon, name, number));
                adapter.notifyItemInserted(contactList.size() - 1);
                recyclerView.scrollToPosition(contactList.size() - 1);
                dialog.dismiss();
            } else {
                Toast.makeText(ContactActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }
}
