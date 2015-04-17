package com.epam.parser;

import com.epam.entity.DataPair;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*Class for data parsing*/
public class DataPairsParser {

    /*method, which parse file to list of DataPairs*/
    public static List<DataPair> parseFromFile(File file){
        List<DataPair> dataPairs = new ArrayList<DataPair>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = reader.readLine()) != null) {
                DataPair dataPair = new DataPair();
                dataPair = parseStringLineToDataPair(line);
                dataPairs.add(dataPair);

            }
            reader.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e){
            e.printStackTrace();
        }

        return dataPairs;
    }

    /*method, which save list of DataPairs to File, every DataPair in one line*/
    public static boolean saveToFile(List<DataPair> datapairs, String filePath){
        File file = null;
        boolean allOK = false;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(parseDataPairsToString(datapairs));
            bw.close();
            allOK = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allOK;
    }

    /*method, which parsing list of DataPairs to one String,every DataPair in one line*/
    public static String parseDataPairsToString(List<DataPair> dataPairs){
        StringBuilder builder = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        for (int i = 0; i < dataPairs.size(); i++) {
            builder.append(dataPairs.get(i).getName());
            builder.append(" = ");
            builder.append(dataPairs.get(i).getValue());
            builder.append(newLine);
        }


        return builder.toString();
    }

    /*method, which return one DataPair from one Stringline by spliting it using '='*/
    public static DataPair parseStringLineToDataPair(String line) {
        String delims = "=";
        String[] tokens = line.split(delims);
        for (int i = 0; i < tokens.length; i++) {
            tokens[i] = tokens[i].trim();
        }
        if(tokens.length != 2){
            throw new IllegalArgumentException("Can't parse line:" + line);
        }
        DataPair dataPair = new DataPair(tokens[0],tokens[1]);
        return dataPair;
    }
}
