package com.example.vuelveaqui.ui.login;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.vuelveaqui.repository.UserRepository;

public class LoginViewModelFactory implements ViewModelProvider.Factory {

    private final Context context;

    public LoginViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(context);
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}