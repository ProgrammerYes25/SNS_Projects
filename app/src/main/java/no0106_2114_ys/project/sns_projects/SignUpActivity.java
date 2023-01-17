package no0106_2114_ys.project.sns_projects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG  = "signUpActivity";
    private FirebaseAuth mAuth; //FirevaseAuth 객체 선언
    EditText emailEditText, passwordEditText, passwordCheckEditText;
    Button signUpButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        passwordCheckEditText = findViewById(R.id.passwordCheckEditText);
        signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(onClickListener);
        mAuth = FirebaseAuth.getInstance(); //FirevaseAuth 객체 정의
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.signUpButton:
                    signUP();
                    break;
            }
        }
    };


    private void signUP(){
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        //푀원 가입
        // FirevaseAuth에 있는 createUserWithEmailAndPassword는 파이어 베이스에서 회원 가입 할때 사용
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    //통신 하고 나서 무슨일을 할지
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // 로그인 성공시
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // 로그인 실패시
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        }
                    }
                });
    }

}