package com.example.devmobapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.sql.DriverManager.println;

public class AccountPage extends AppCompatActivity {

    private TextView textViewResult;
    private Button btnUpdate;

    private void GetAccountsFromApi()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://60102f166c21e10017050128.mockapi.io/labbbank/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AccountsApi jsonApi = retrofit.create(AccountsApi.class);
        Call<List<Account>> call = jsonApi.getAccounts();

        call.enqueue(new Callback<List<Account>>() {
            @Override
            public void onResponse(Call<List<Account>> call, Response<List<Account>> response) {
                if(!response.isSuccessful()){
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                List<Account> accounts = response.body();

                for(Account account : accounts ) {
                    String content = "";
                    content += "ID: " + account.getId() + "\n";
                    content += "Account Name : " + account.getAccountName() + "\n";
                    content += "Amount: " + account.getAmount() + "\n";
                    content += "IBAN: " + account.getIban() + "\n";
                    content += "Currency: " + account.getCurrency() + "\n";



                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Account>> call, Throwable t) {
                textViewResult.setText("Erreur");
            }

        });
        println("Done");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_page);
        textViewResult =findViewById(R.id.text_view_result);
        btnUpdate = findViewById(R.id.btn_update);

        GetAccountsFromApi();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewResult.setText("");
                GetAccountsFromApi();
            }
        });
    }
}