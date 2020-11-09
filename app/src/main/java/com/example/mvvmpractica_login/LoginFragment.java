package com.example.mvvmpractica_login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.mvvmpractica_login.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {
    private FragmentLoginBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentLoginBinding.inflate(inflater, container, false)).getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final MiLoginViewModel miLoginViewModel = new ViewModelProvider(this).get(MiLoginViewModel.class);

        binding.iniciaresion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = (binding.user.getText().toString());
                String pass = (binding.pass.getText().toString());

                Log.e("ABCD", "llamando al viewmodel");
                miLoginViewModel.login(user, pass);
            }
        });



        miLoginViewModel.loginOk.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onChanged(Boolean tok) {
                if(tok){
                    binding.mensaje.setText("TODO CORRECTO");
                }

            }
        });



        miLoginViewModel.validando.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean validando) {
                if (validando) {
                    binding.calculando.setVisibility(View.VISIBLE);
                    binding.mensaje.setVisibility(View.GONE);
                } else {
                    binding.calculando.setVisibility(View.GONE);
                    binding.mensaje.setVisibility(View.VISIBLE);
                }
            }
        });


    }
}
