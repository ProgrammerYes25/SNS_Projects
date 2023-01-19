package no0106_2114_ys.project.sns_projects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordResetActivity extends AppCompatActivity {
    private FirebaseAuth mAuth; //FirevaseAuth 객체 선언
    EditText emailEditText;
    Button gotoPasswordResetButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);
        mAuth = FirebaseAuth.getInstance(); //FirevaseAuth 객체 정의
        emailEditText = findViewById(R.id.emailEditText);
        gotoPasswordResetButton = findViewById(R.id.gotoPasswordResetButton);
        gotoPasswordResetButton.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.gotoPasswordResetButton:
                    send();
                    break;
            }
        }
    };
    private void send(){    //메일 보내기
        String email = emailEditText.getText().toString();

        if(email.length() > 0){
            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                showToast("이메일을 보냈습니다.");
                            }
                        }
                    });
        }else{
            showToast("이메일 주소를 입려해주세요.");
        }
    }
    private void showToast(String msg) {
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
    }
}