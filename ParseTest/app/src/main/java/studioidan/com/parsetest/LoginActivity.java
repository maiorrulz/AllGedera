package studioidan.com.parsetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class LoginActivity extends AppCompatActivity {

    CallbackManager callbackManager;
    LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);

        callbackManager = CallbackManager.Factory.create();

        loginButton = (LoginButton) findViewById(R.id.login_button);

        final AppCompatActivity activity = this;

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Intent intent = new Intent(activity, MenuActivity.class);
                startActivity(intent);
                //Profile profile = Profile.getCurrentProfile();
                //if (profile != null)
                //    ((TextView)findViewById(R.id.welcomeText)).setText("Welcome " + profile.getName());
            }

            @Override
            public void onCancel() {
                Intent intent = new Intent(activity, MenuActivity.class);
                startActivity(intent);
                //((TextView)findViewById(R.id.welcomeText)).setText("Welcome c");
            }

            @Override
            public void onError(FacebookException error) {
                Intent intent = new Intent(activity, MenuActivity.class);
                startActivity(intent);
                //((TextView)findViewById(R.id.welcomeText)).setText("Welcome e");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
