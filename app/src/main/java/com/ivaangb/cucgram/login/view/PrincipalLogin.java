package com.ivaangb.cucgram.login.view;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ivaangb.cucgram.R;
import com.ivaangb.cucgram.login.metodos.Validacion;
import com.ivaangb.cucgram.login.presenter.LoginPresenter;
import com.ivaangb.cucgram.login.presenter.LoginPresenterImpl;
import com.ivaangb.cucgram.view.ContainerActivity;
import com.ivaangb.cucgram.view.CreateAccountActivity;

public class PrincipalLogin extends AppCompatActivity implements LoginView {

    private TextInputEditText username, password;
    private Button btnLogin;
    private ProgressBar progressBarLogin;
    private LoginPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btnLogin);
        progressBarLogin = findViewById(R.id.progressBarLogin);
        hideProgressBar();

        presenter = new LoginPresenterImpl(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    presenter.signIn(username.getText().toString(),
                            password.getText().toString());

            }
        });

    }




    @Override
    public void enableInputs() {
        username.setEnabled(true);
        password.setEnabled(true);
        btnLogin.setEnabled(true);
    }

    @Override
    public void disbaleInputs() {
        username.setEnabled(false);
        password.setEnabled(false);
        btnLogin.setEnabled(false);
    }

    @Override
    public void showProgressBar() {
        progressBarLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBarLogin.setVisibility(View.GONE);
    }

    @Override
    public void loginError(String error) {
        Toast.makeText(this, getString(R.string.errorLogin) + error, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void goCreateAccount() {
        Intent intent = new Intent(this, CreateAccountActivity.class);
        startActivity(intent);
    }

    @Override
    public void goAccount() {
        Intent intent = new Intent(this, ContainerActivity.class);
        startActivity(intent);
    }
}
