package com.example.mvvmpractica_login;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MiLoginViewModel extends AndroidViewModel {
    Executor executor;

    SimuladorLogin simulador;

    MutableLiveData<Boolean> loginOk = new MutableLiveData<>();
    MutableLiveData<Boolean> algoestamal = new MutableLiveData<>();
    MutableLiveData<Boolean> errorUser = new MutableLiveData<>();
    MutableLiveData<Boolean> errorPass = new MutableLiveData<>();
    MutableLiveData<Boolean> validando = new MutableLiveData<>();


    public MiLoginViewModel(@NonNull Application application) {
        super(application);
        executor = Executors.newSingleThreadExecutor();
        simulador = new SimuladorLogin();
    }

    public void login(final String user, String pass) {
        Log.e("ABCD", "llamando al viewmodel");
        final SimuladorLogin.Solicitud solicitud = new SimuladorLogin.Solicitud(user, pass);

        executor.execute(new Runnable() {
            @Override
            public void run() {

                simulador.calcular(solicitud, new SimuladorLogin.Callback() {


                    @Override
                    public void cuandotodoseacorrecto(String o) {loginOk.postValue(true);

                    }

                    @Override
                    public void cuandoseaincorrecto(String ok) {algoestamal.postValue(true);

                    }

                    @Override
                    public void cuandoElLoginseaincorrecto(int code) {
                        errorUser.postValue(true);
                    }

                    @Override
                    public void cuandoEmpieceElCalculo() {
                        validando.postValue(true);

                    }

                    @Override
                    public void cuandoFinaliceElCalculo() {
                        validando.postValue(false);

                    }
                });
            }
        });
    }


}
