package com.tanmaya.careergator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {
   private EditText Email,Password;
   private Button RTeacher,LoginT;
    private FirebaseAuth mAuth;
    private TextView Login;
    String Activity;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Email=findViewById(R.id.email);
        Password=findViewById(R.id.password);
        LoginT = findViewById(R.id.btn_teacher);
        RTeacher=findViewById(R.id.reg_teacher);
        Login=findViewById(R.id.link_sa);
        mAuth=FirebaseAuth.getInstance();
        firebaseAuthListener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                if (user!=null)
                {
                        Intent intent = new Intent(Register.this,TeacherDashboard.class);
                            startActivity(intent);
                            finish();
                            return;
                    }

                }



        };
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Register.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


        LoginT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email=Email.getText().toString();
                final String password=Password.getText().toString();
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(Register.this, "Login  Error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        RTeacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email=Email.getText().toString();
                final String password=Password.getText().toString();
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(Register.this, "SignUp Error", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            String user_id=mAuth.getCurrentUser().getUid();
                            DatabaseReference current_user_db= FirebaseDatabase.getInstance().getReference().child("Users").child("Teachers").child(user_id);
                            current_user_db.setValue(true);
                            Toast.makeText(Register.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

    }
    @Override
    protected void onStart()
    {
        super.onStart();
        mAuth.addAuthStateListener(firebaseAuthListener);
    }
    @Override
    protected void onStop()
    {
        super.onStop();
        mAuth.removeAuthStateListener(firebaseAuthListener);
    }

}

