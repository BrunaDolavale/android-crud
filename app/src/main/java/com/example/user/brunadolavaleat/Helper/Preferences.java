package com.example.user.brunadolavaleat.Helper;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {
    private Context context;
    private SharedPreferences preferences;
    private String NOME_ARQUIVO = "projetoFirebase.preferencias";
    private int NOME = 0;
    private SharedPreferences.Editor editor;

    private final String KEY_IDENTIFIER = "identifierLoggedUser";
    private final String KEY_NAME = "nameLoggedUser";

    public Preferences(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(NOME_ARQUIVO, NOME);

        editor = preferences.edit();
    }

    public void saveUserPreferencies(String identifierUser, String userName) {
        editor.putString(KEY_IDENTIFIER, identifierUser);
        editor.putString(KEY_NAME, userName);
        editor.commit();
    }

    public String getIdentifiers() {
        return preferences.getString(KEY_IDENTIFIER, null);
    }

    public String getNome() {
        return preferences.getString(KEY_NAME, null);
    }
}
