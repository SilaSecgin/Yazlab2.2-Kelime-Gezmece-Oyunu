package com.sila.kelimegezmece.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CreatePuzzle {
    List<List<String>> level1WordsList = new ArrayList<>();
    List<List<String>> level2WordsList = new ArrayList<>();
    List<List<String>> level3WordsList = new ArrayList<>();

    public List<List<String>> chooseWords(int levelNumber) {

        if (levelNumber == 1) {
            return ShuffleList(chooseListLevelOne());
        } else if (levelNumber == 2) {
            return ShuffleList(chooseListLevelTwo());
        } else if (levelNumber == 3) {
            return ShuffleList(chooseListLevelThree());
        }
        return null;
    }

    public List<List<String>> chooseListLevelOne() {
        //3 Harfli Kelime Listesi

        List<String> level1_1 = Arrays.asList("ŞUT", "TUŞ");
        List<String> level1_2 = Arrays.asList("MAÇ", "ÇAM");
        List<String> level1_3 = Arrays.asList("KAŞ", "AŞK");
        List<String> level1_4 = Arrays.asList("RAY", "YAR");
        List<String> level1_5 = Arrays.asList("PAS", "SAP");
        List<String> level1_6 = Arrays.asList("TAY", "YAT");
        List<String> level1_7 = Arrays.asList("PAY", "YAP");

        level1WordsList.add(level1_1);
        level1WordsList.add(level1_2);
        level1WordsList.add(level1_3);
        level1WordsList.add(level1_4);
        level1WordsList.add(level1_5);
        level1WordsList.add(level1_6);
        level1WordsList.add(level1_7);

        return level1WordsList;

    }

    private List<List<String>> chooseListLevelTwo() {
        //4 Harfli Kelime Listesi

        List<String> level2_1 = Arrays.asList("AFRA", "FAR", "ARA");
        List<String> level2_2 = Arrays.asList("DAMA", "AMA", "DAM");
        List<String> level2_3 = Arrays.asList("KOŞU", "ŞOK", "KUŞ");
        List<String> level2_4 = Arrays.asList("KAZI", "AZI", "KIZ");
        List<String> level2_5 = Arrays.asList("AMCA", "AMA", "CAM");
        List<String> level2_6 = Arrays.asList("ARSA", "ARA", "SAR");
        List<String> level2_7 = Arrays.asList("VANA", "VAN", "ANA");

        level2WordsList.add(level2_1);
        level2WordsList.add(level2_2);
        level2WordsList.add(level2_3);
        level2WordsList.add(level2_4);
        level2WordsList.add(level2_5);
        level2WordsList.add(level2_6);
        level2WordsList.add(level2_7);

        return level2WordsList;
    }

    private List<List<String>> chooseListLevelThree() {
        //5 Harfli Kelime Listesi

        List<String> level3_1 = Arrays.asList("BAŞKA", "AŞK", "KAŞ", "ŞAKA");
        List<String> level3_2 = Arrays.asList("TAMAM", "AMA", "TAM", "MAMA");
        List<String> level3_3 = Arrays.asList("BESİN", "SEN", "BEN", "ESİN");
        List<String> level3_4 = Arrays.asList("KİLİM", "İKİ", "KİL", "İLİM");
        List<String> level3_5 = Arrays.asList("MİNİK", "İKİ", "KİN", "MİNİ");
        List<String> level3_6 = Arrays.asList("DİKEN", "KİN", "DİN", "EKİN");
        List<String> level3_7 = Arrays.asList("BAHİS", "HİS", "BAS", "SİHA");

        level3WordsList.add(level3_1);
        level3WordsList.add(level3_2);
        level3WordsList.add(level3_3);
        level3WordsList.add(level3_4);
        level3WordsList.add(level3_5);
        level3WordsList.add(level3_6);
        level3WordsList.add(level3_7);

        return level3WordsList;
    }

    public List<List<String>> ShuffleList(List list) {
        Collections.shuffle(list);
        return list;
    }

    public List<String> getLetter(String word) {
        // Seçtiği kelimedeki harfleri buradan alacak. diğer tarafa liste olarak verecek.

        List<String> letters = new ArrayList<String>();

        for (int i = 0; i < word.length(); i++) {
            letters.add(String.valueOf(word.charAt(i)));
        }

        return letters;
    }
}
