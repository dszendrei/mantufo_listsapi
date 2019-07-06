package com.mantufo.listsapi.mantufo.listsapi.serviece;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.sheets.v4.SheetsScopes;
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

    public static Credential getCredential() throws IOException {
        if (credential == null) {
            AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
            textEncryptor.setPassword(System.getenv("textEncryptorPass"));
            String plainText = textEncryptor.decrypt(
                    Files.readAllLines(Paths.get("src/main/resources").resolve("mantufo-g_credit-decoded.json")).get(0));
            InputStream targetStream = new ByteArrayInputStream(plainText.getBytes());

            credential = GoogleCredential.fromStream(targetStream)
                    .createScoped(Collections.singleton(SheetsScopes.SPREADSHEETS));
        }
        return credential;
    }
}