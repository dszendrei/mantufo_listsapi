package com.mantufo.listsapi.mantufo.listsapi.Model.Enum;

public enum SheetNames {
    TANTARGYAK_ITTHON("Tantárgyak itthon"),
    SZAKOK("Szakok"),
    VERSENYEK("Versenyek"),
    EUROPAI_DIPLOMAK("Európai diplomák"),
    ESEMENYEK("Események, MOOC"),
    KUTATOCSOPORTOK_ES_CEGEK("Kutatócsoportok és cégek"),
    PARTNEREK("Partnerek"),
    EDDIGI_MEGJELENESEINK("Eddigi megjelenéseink");

    String value;

    SheetNames(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static String convertSheetNameToGetValues(SheetNames sheetName) {
        return "'" + sheetName.toString() + "'!";
    }

    public static String convertSheetNameToGetValues(String sheetName) {
        return convertSheetNameToGetValues(SheetNames.valueOf(sheetName.toUpperCase()));
    }
}
