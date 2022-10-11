package com.samasung.chatapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.github.library.bubbleview.BubbleTextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import android.text.format.DateFormat;

public class Chat2Activity extends AppCompatActivity {
    public int a = 0;
    private static int SIGH_IN_CODE = 1;
    private RelativeLayout activity_main;
    private FirebaseListAdapter<Message> adapter;
    private FloatingActionButton sendBtn;
    private FloatingActionButton closeBtn;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SIGH_IN_CODE) {
            if (resultCode == RESULT_OK) {
                Snackbar.make(activity_main, "Вы авторизованы", Snackbar.LENGTH_LONG).show();
                displayAllMessages();
            } else {
                Snackbar.make(activity_main, "Вы не авторизованы", Snackbar.LENGTH_LONG).show();
                finish();
            }
        }
    }
  /*  private void showEncryptionWindow(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        LayoutInflater inflater = LayoutInflater.from(this);
        View encryption_window = inflater.inflate(R.layout.encryption_window, null);
        dialog.setView(encryption_window);
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat1);

        activity_main = findViewById(R.id.activity_main);
        closeBtn = findViewById(R.id.btnClose);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sendBtn = findViewById(R.id.btnSend);
        sendBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //  showEncryptionWindow();
                EditText textField = findViewById(R.id.messageField);
                if (textField.getText().toString() == "")
                    return;


                FirebaseDatabase.getInstance().getReference().push().setValue(
                        new Message(FirebaseAuth.getInstance().getCurrentUser().getDisplayName(),
                                textField.getText().toString()
                        )
                );
                textField.setText("");

            }

        });

        //пользователь еще не авторизован
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(), SIGH_IN_CODE);
        } else {
            Snackbar.make(activity_main, "Вы авторизованы", Snackbar.LENGTH_LONG).show();
            displayAllMessages();

        }


    }


    private void displayAllMessages() {
        ListView listOsMessages = findViewById(R.id.list_of_messages);
        adapter = new FirebaseListAdapter<Message>(this, Message.class, R.layout.list_item, FirebaseDatabase.getInstance().getReference()) {
            @Override
            protected void populateView(View v, Message model, int position) {
                TextView mess_user, mess_time;
                BubbleTextView mess_text;
                Button btnRF;

                mess_user = v.findViewById(R.id.message_user);
                mess_time = v.findViewById(R.id.message_time);
                mess_text = v.findViewById(R.id.message_text);
                btnRF = v.findViewById(R.id.btnRF);


                mess_text.setText(model.getTextMessage2());

                mess_user.setText(model.getUserName());
                mess_time.setText(DateFormat.format("dd-mm-yyyy HH:mm:ss", model.getMessageTime()));

              /*  sendBtn.setOnClickListener(new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder a_builder = new AlertDialog.Builder(Chat1Activity.this);
                        a_builder.setMessage("Выберите тип шифрование")
                                .setCancelable(false)
                                .setPositiveButton("Перекодировка", new DialogInterface.OnClickListener() {
                                    int a = 1;
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        EditText textField = findViewById(R.id.messageField);
                                        if (textField.getText().toString() == "")
                                            return;



                                        FirebaseDatabase.getInstance().getReference().push().setValue(
                                                new Message(FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                                                        textField.getText().toString()
                                                )
                                        );
                                        textField.setText("");

                                    }

                                })

                                .setNegativeButton("Шифр цезаря", new DialogInterface.OnClickListener() {
                                    int a = 2;
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        EditText textField = findViewById(R.id.messageField);
                                        if (textField.getText().toString() == "")
                                            return;


                                        FirebaseDatabase.getInstance().getReference().push().setValue(
                                                new Message(FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                                                        textField.getText().toString()
                                                )
                                        );
                                        textField.setText("");

                                    }
                                })
                                .setNeutralButton("Двоичный код", new DialogInterface.OnClickListener() {
                                    int a = 3;
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        EditText textField = findViewById(R.id.messageField);
                                        if (textField.getText().toString() == "")

                                            return;


                                        FirebaseDatabase.getInstance().getReference().push().setValue(
                                                new Message(FirebaseAuth.getInstance().getCurrentUser().getEmail(),
                                                        textField.getText().toString()
                                                )
                                        );
                                        textField.setText("");


                                    }

                                });
                        AlertDialog alert = a_builder.create();
                        alert.setTitle("Шифрование");
                        alert.show();

                    }

                });*/

                btnRF.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mess_text.setText(model.getTextMessage1());
                    }
                });

            }
        };

        listOsMessages.setAdapter(adapter);
    }
}