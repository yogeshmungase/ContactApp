package com.app.contactapp.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.app.contactapp.Model.Contact;
import com.app.contactapp.R;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private List<Contact> contactList;
    private OnContactClickListener listener;

    public ContactAdapter(List<Contact> contactList, OnContactClickListener listener) {
        this.contactList = contactList;
        this.listener = listener;
    }

    public interface OnContactClickListener {
        void onContactClick(Contact contact);
    }

    public void addContact(Contact contact) {
        contactList.add(contact);
        notifyItemInserted(contactList.size() - 1);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contactlist, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Contact contact = contactList.get(position);
        holder.imageView.setImageResource(contact.getImageResId());
        holder.nameTextView.setText(contact.getName());
        holder.numberTextView.setText(contact.getNumber());


        // to add new contact

        // to update contact
        holder.btnOpenDialog.setOnClickListener(v -> {
            Dialog dialog = new Dialog(v.getContext());
            dialog.setContentView(R.layout.add_update_contact);

            EditText edtName = dialog.findViewById(R.id.edtName);
            EditText edtNumber = dialog.findViewById(R.id.edtumber);
            Button btnAction = dialog.findViewById(R.id.btnAction);
            TextView txtTitle = dialog.findViewById(R.id.titleText);

            txtTitle.setText("Update Contact");
            btnAction.setText("Update");

            edtName.setText(contact.getName());
            edtNumber.setText(contact.getNumber());

            btnAction.setOnClickListener(view -> {
                String name = edtName.getText().toString();
                String number = edtNumber.getText().toString();

                if (!name.isEmpty() && !number.isEmpty()) {
                    contactList.set(position, new Contact(name, number));
                    notifyItemChanged(position);
                    dialog.dismiss();
                } else {
                    Toast.makeText(v.getContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
            });

            dialog.show();
        });

        // to delete contact
        holder.btnOpenDialog.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext())
                        .setTitle("Delete Contact")
                        .setMessage("Are you sure you want to delete this contact?")
                        .setIcon(R.drawable.delete)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                contactList.remove(position);
                                notifyItemRemoved(position);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;
            }
        });
        
        // add call button after onclick redirect to another activity
        holder.btncall.setOnClickListener(v -> {
            Dialog dialog = new Dialog(v.getContext());

            dialog.setContentView(R.layout.call_dialog);

            Button btnCall = dialog.findViewById(R.id.btnCall);
            Button btnCancel = dialog.findViewById(R.id.btnCancel);

            btnCall.setOnClickListener(view -> {
                TextView tvNumber = dialog.findViewById(R.id.tvCallNumber);
                tvNumber.setText(contact.getNumber());
                Toast.makeText(v.getContext(), "Calling...", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            });

            btnCancel.setOnClickListener(view -> {
                dialog.dismiss();
            });

            dialog.show();

        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView nameTextView;
        private TextView numberTextView;
        private View btnOpenDialog;
        private View btncall;

        public ContactViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            nameTextView = itemView.findViewById(R.id.nameText);
            numberTextView = itemView.findViewById(R.id.numberText);
            btnOpenDialog = itemView.findViewById(R.id.btnOpenDialog);
            btncall = itemView.findViewById(R.id.btncall);




            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Contact contact = contactList.get(position);
                    listener.onContactClick(contact);
                }
            });
        }
    }
}
