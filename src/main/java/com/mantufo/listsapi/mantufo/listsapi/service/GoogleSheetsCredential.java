package com.mantufo.listsapi.mantufo.listsapi.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import org.jasypt.util.text.AES256TextEncryptor;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

public class GoogleSheetsCredential {
    public static Credential credential;

    private static InputStream getStream() throws IOException {
        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword(System.getenv("textEncryptorPass"));
        String plainText = textEncryptor.decrypt(
                Files.readAllLines(Paths.get("src/main/resources").resolve("mantufo-g_credit-decoded.json")).get(0));
        return new ByteArrayInputStream(plainText.getBytes());
    }

    public static HttpRequestInitializer getCredentials() throws IOException {
        if (credential == null) {
            GoogleCredentials googleCredentials = GoogleCredentials.fromStream(getStream())
                .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS_READONLY));
            return new HttpCredentialsAdapter(googleCredentials);
        }
        return credential;
    }
}