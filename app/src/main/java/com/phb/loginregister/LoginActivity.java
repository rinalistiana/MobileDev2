package com.phb.loginregister;

import android.widget.Button;
import android.widget.EditText;
import butterknife.BindView;

public class LoginActivity extends MainActivity{
    @BindView(R.id.edtEmail)
    EditText edtEmail;
    @BindView(R.id.edtPassword)
    EditText edtPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnLogin)
    public void onViewClicked() {

        BodyLogin bodyLogin =  new BodyLogin();
        bodyLogin.setEmail(edtEmail.getText().toString());
        bodyLogin.setPassword(edtPassword.getText().toString());


        RestClient.getService().postLogin(bodyLogin).enqueue(new Callback<LoginResponse>() {
            // TODO method dibawah otomatis pada saat new Callback
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Toast.makeText(LoginActivity.this, response.body().getToken(), Toast.LENGTH_SHORT).show();
                Log.i("Response",response.message());
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });

}
