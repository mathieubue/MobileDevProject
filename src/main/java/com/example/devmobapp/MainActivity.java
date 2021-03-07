package com.example.devmobapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.sql.DriverManager.println;

public class MainActivity extends AppCompatActivity {

    private TextView tvHello;
    private TextView tvId;
    private TextView tvLastname;
    private  Button btnLogin;
    private TextInputEditText tilId;
    private TextInputEditText tilLastname;




    private void Login()
    {
        //try {
            //OkhttpManager.getInstance().setTrustrCertificates(getAssets().open("mycer.cer"));
            //I don't know how to create a certificate
            //OkHttpClient mOkhttpClient= OkhttpManager.getInstance().build();




        Retrofit retrofit = new Retrofit.Builder()
                    //.client(mOkhttpClient)
                    .baseUrl("https://60102f166c21e10017050128.mockapi.io/labbbank/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Api jsonApi = retrofit.create(Api.class);
            Call<List<Config>> call = jsonApi.getConfigs();

            call.enqueue(new Callback<List<Config>>() {
                @Override
                public void onResponse(Call<List<Config>> call, Response<List<Config>> response) {
                    if(!response.isSuccessful()){
                        return;
                    }
                    List<Config> configs = response.body();

                    for(Config config : configs ) {
                        int id = config.getId();
                        String strId = String.valueOf(id);
                        String strLastname = config.getLastname();
                        if((tilId.getText().toString().equals(strId)) && (tilLastname.getText().toString().equals(strLastname)))
                        {
                            Toast.makeText(getApplicationContext(), "Connected",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(),AccountPage.class);
                            startActivity(intent);

                            break;
                        }

                    }

                }

                @Override
                public void onFailure(Call<List<Config>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), "Erreur",Toast.LENGTH_LONG).show();
                }

            });
            /*
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Erreur",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }*/

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvHello = findViewById(R.id.tv_hello);
        tvId= findViewById(R.id.tv_key1);
        tvLastname= findViewById(R.id.tv_key2);
        btnLogin= findViewById(R.id.btn_login);
        tilId= findViewById(R.id.input_key1);
        tilLastname= findViewById(R.id.input_key2);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Login();
                Toast.makeText(getApplicationContext(), "User not found",Toast.LENGTH_LONG).show();
            }
        });
    }
}