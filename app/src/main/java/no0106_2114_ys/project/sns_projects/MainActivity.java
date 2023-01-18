package no0106_2114_ys.project.sns_projects;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth; //FirevaseAuth 객체 선언
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance(); //FirevaseAuth 객체 정의
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null){
            startSignUpActivity();
        }
    }
    private void startSignUpActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}