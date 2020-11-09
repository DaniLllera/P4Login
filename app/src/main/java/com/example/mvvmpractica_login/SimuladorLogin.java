package com.example.mvvmpractica_login;

import android.util.Log;

public class SimuladorLogin {

    public static class Solicitud {
        private String user;
        private String pass;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPass() {
            return pass;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }

        public Solicitud(String user, String pass) {
            this.user = user;
            this.pass = pass;
        }
    }
    interface Callback {

        void cuandotodoseacorrecto(String ok);
        void cuandoseaincorrecto(String ok);
        void cuandoElLoginseaincorrecto(int code);
        void cuandoEmpieceElCalculo();
        void cuandoFinaliceElCalculo();
    }

    public void calcular(Solicitud solicitud, Callback callback) {
        Log.e("ABCD", "Comprobando si es correcto");
        callback.cuandoEmpieceElCalculo();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }


        if (solicitud.getUser().equals("dani")){
            if(solicitud.getPass().equals("dani")) {
                callback.cuandotodoseacorrecto("TODO BIEN");
                Log.e("ABCD", "Comprobando si todo es correcto");

            }
        }

        callback.cuandoFinaliceElCalculo();

    }
}
