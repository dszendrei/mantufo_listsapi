package com.mantufo.listsapi.mantufo.listsapi.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class GoogleSheetsUtil {
    public static Sheets getSheetsService() throws GeneralSecurityException, IOException {
        Sheets sheets = new Sheets.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(),
                GoogleSheetsCredential.getCredentials())
                .setApplicationName("Google Sheets Client").build();
        return sheets;
    }
}
