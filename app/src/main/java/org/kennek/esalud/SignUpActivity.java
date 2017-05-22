package org.kennek.esalud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void click(View v) {
        Intent intent;
        switch(v.getId()) {
            case R.id.link_login:
                intent = new Intent(this, SplashActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_signup:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }

    }
}
