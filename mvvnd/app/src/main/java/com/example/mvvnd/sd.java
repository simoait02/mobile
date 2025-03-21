package com.example.mvvnd;

import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.databinding.DataBindingUtil;
import androidx.annotation.Nullable;

import com.example.mvvnd.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize ViewModel using ViewModelProvider (AndroidX)
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        // Initialize DataBinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Set the LifecycleOwner for LiveData observation
        binding.setLifecycleOwner(this);

        // Bind ViewModel to layout using DataBinding
        binding.setLoginViewModel(loginViewModel);

        // Observe the User LiveData from ViewModel
        loginViewModel.getUser().observe(this, new Observer<LoginUser>() {
            @Override
            public void onChanged(@Nullable LoginUser loginUser) {
                if (loginUser != null) {
                    if (TextUtils.isEmpty(loginUser.getStrEmailAddress())) {
                        binding.txtEmailAddress.setError("Enter an E-Mail Address");
                        binding.txtEmailAddress.requestFocus();
                    }
                    else if (!loginUser.isEmailValid()) {
                        binding.txtEmailAddress.setError("Enter a Valid E-mail Address");
                        binding.txtEmailAddress.requestFocus();
                    }
                    else if (TextUtils.isEmpty(loginUser.getStrPassword())) {
                        binding.txtPassword.setError("Enter a Password");
                        binding.txtPassword.requestFocus();
                    }
                    else if (!loginUser.isPasswordLengthGreaterThan5()) {
                        binding.txtPassword.setError("Enter at least 6 Digit password");
                        binding.txtPassword.requestFocus();
                    }
                    else {
                        binding.lblEmailAnswer.setText(loginUser.getStrEmailAddress());
                        binding.lblPasswordAnswer.setText(loginUser.getStrPassword());
                    }
                }
            }
        });
    }
}
