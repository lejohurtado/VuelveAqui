package com.example.vuelveaqui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.vuelveaqui.data.model.User;
import com.example.vuelveaqui.databinding.ActivityRegisterBinding;
import com.example.vuelveaqui.repository.UserRepository;
import com.example.vuelveaqui.ui.login.LoginActivity;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final Button regresar = binding.idButtonBack;
        final Button registrar = binding.idButtonRegister;

        final TextInputEditText nombres = binding.idNombres;
        final TextInputEditText telefono = binding.idTelefono;
        final TextInputEditText correo = binding.idCorreoElectronico;
        final TextInputEditText contrasena = binding.idContrasena;

        regresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Logica para guardar en bd
                UserRepository userRepository = new UserRepository(v.getContext());
                userRepository.insert_user(new User(nombres.getText().toString(),Long.parseLong(telefono.getText().toString()),correo.getText().toString(),contrasena.getText().toString()));

                String register = "Registro exitoso" + nombres;
                // TODO : initiate successful logged in experience
                Toast.makeText(getApplicationContext(), register, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(v.getContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}