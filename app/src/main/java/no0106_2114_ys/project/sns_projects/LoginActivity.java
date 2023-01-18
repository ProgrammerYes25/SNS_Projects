package no0106_2114_ys.project.sns_projects;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG  = "signUpActivity";
    private FirebaseAuth mAuth; //FirevaseAuth 객체 선언
    EditText emailEditText, passwordEditText;
    Button signUpButton, loginButton;
    private long backBtnTime = 0l;  //뒤로가기 누른 횟수 계산하기 위한 변수
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signUpButton = findViewById(R.id.signUpButton);
        loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(onClickListener);
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
                case R.id.loginButton:
                    login();
                    break;
            }
        }
    };

    private void signUP(){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
    private void login(){
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if(email.length()>0 && password.length()>0){//비밀번호와 이메일 비어있는 지 확인
            // 로그인
            // FirevaseAuth에 있는 signInUserWithEmailAndPassword는 파이어 베이스에서 로그인 할때 사용
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        //통신 하고 나서 무슨일을 할지
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // 로그인 성공시
                                showToast("로그인에 성공 하셨습니다.");
                                FirebaseUser user = mAuth.getCurrentUser();
                                startMainActivity();
                            } else {
                                // 로그인 실패시
                                showToast("이메일 또는 비밀번호를 다시 확인해주세요.");
                            }
                        }
                    });
        }
        else {
            showToast("이메일 또는 비밀번호를 입력해주세요.");
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg,Toast.LENGTH_SHORT).show();
    }

    private void startMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {//뒤로 가기 누르면 종료
        long curTime = System.currentTimeMillis();
        long gapTime = curTime - backBtnTime;

        if(0 <= gapTime && 2000 >= gapTime) {
            super.onBackPressed();
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
        else {
            backBtnTime = curTime;
            Toast.makeText(this, "한번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show();
        }
    }

}