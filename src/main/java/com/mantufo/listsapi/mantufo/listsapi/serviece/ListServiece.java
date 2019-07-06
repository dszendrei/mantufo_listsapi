package com.mantufo.listsapi.mantufo.listsapi.serviece;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.DataFilter;
import com.google.api.services.sheets.v4.model.GetSpreadsheetByDataFilterRequest;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.mantufo.listsapi.mantufo.listsapi.Model.Cell;
import com.mantufo.listsapi.mantufo.listsapi.Model.Coordinate;
import com.mantufo.listsapi.mantufo.listsapi.Model.ListOfCells;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListServiece {

    @Autowired
    ListOfCells listOfCells;

    public List<Cell> getCellsByValue(String value) {
        return listOfCells.getListOfCells().stream().filter(c -> c.getValue().equals(value)).collect(Collectors.toList());
    }

    public Cell getCellByCoordinate(Coordinate coordinate) {
        return listOfCells.getListOfCells().stream().filter(c -> c.getCoordinate().equals(coordinate)).findFirst().orElse(null);
    }

    public static void refillListOfCells() throws IOException, GeneralSecurityException {
        //TODO make ajax to google sheets and collect data to proper classes
        // The spreadsheet to request.
        String spreadsheetId = "1ICM830TsC433d3FB6P1bFMZ7_zvo6a39RFUMbm6GtSI"; // TODO: Update placeholder value.

        // The DataFilters used to select which ranges to retrieve from
        // the spreadsheet.
        List<DataFilter> dataFilters = new ArrayList<>(); // TODO: Update placeholder value.

        // True if grid data should be returned.
        // This parameter is ignored if a field mask was set in the request.
        boolean includeGridData = false; // TODO: Update placeholder value.

        // TODO: Assign values to desired fields of `requestBody`:
        GetSpreadsheetByDataFilterRequest requestBody = new GetSpreadsheetByDataFilterRequest();
        requestBody.setDataFilters(dataFilters);
        requestBody.setIncludeGridData(includeGridData);

        Sheets sheetsService = createSheetsService();
        Sheets.Spreadsheets.GetByDataFilter request =
                sheetsService.spreadsheets().getByDataFilter(spreadsheetId, requestBody);

        Spreadsheet response = request.execute();

        // TODO: Change code below to process the `response` object:
        System.out.println(response);
    }

    public static Sheets createSheetsService() throws IOException, GeneralSecurityException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

        // TODO: Change placeholder below to generate authentication credentials. See
        // https://developers.google.com/sheets/quickstart/java#step_3_set_up_the_sample
        //
        // Authorize using one of the following scopes:
        //   "https://www.googleapis.com/auth/drive"
        //   "https://www.googleapis.com/auth/drive.file"
        //   "https://www.googleapis.com/auth/spreadsheets"
        GoogleCredential credential = null;

        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("/path/to/credentials.json"));
        credentials.refreshIfExpired();
        //AccessToken token = credentials.getAccessToken();

        return new Sheets.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName("Google-SheetsSample/0.1")
                .build();
    }
}
