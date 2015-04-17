package com.epam.entity;

import java.util.Comparator;

/*Main entity, which contains one pair name-value*/
public class DataPair {
    String name;
    String value;

    public DataPair(String token, String token1) {
        this.name = token;
        this.value = token1;
    }

    public DataPair() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public static Comparator<DataPair> NameAscendingComparator
            = new Comparator<DataPair>() {

        public int compare(DataPair dataPair1, DataPair dataPair2) {

            String name1 = dataPair1.getName().toUpperCase();
            String name2 = dataPair2.getName().toUpperCase();


            return name1.compareTo(name2);

        }

    };

    public static Comparator<DataPair> ValueAscendingComparator
            = new Comparator<DataPair>() {

        public int compare(DataPair dataPair1, DataPair dataPair2) {

            String value1 = dataPair1.getValue().toUpperCase();
            String value2 = dataPair2.getValue().toUpperCase();

            return value1.compareTo(value2);

        }

    };
}
