package com.example.devmobapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AccountsApi {
    @GET("accounts")
    Call<List<Account>> getAccounts();
}
