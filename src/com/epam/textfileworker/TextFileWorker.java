package com.epam.textfileworker;


import com.epam.entity.DataPair;

import java.io.File;
import java.util.*;

import static com.epam.parser.DataPairsParser.parseFromFile;
import static com.epam.parser.DataPairsParser.parseStringLineToDataPair;
import static com.epam.parser.DataPairsParser.saveToFile;

public class TextFileWorker {

    StringBuilder menuBuilder = new StringBuilder();
    List<DataPair> currentDataPairs = null;


    {
        menuBuilder.append("Text file worker menu:\n");
        menuBuilder.append("1 - Load data from file\n");
        menuBuilder.append("2 - Show data from file\n");
        menuBuilder.append("3 - Add data pair\n");
        menuBuilder.append("4 - Remove data pair\n");
        menuBuilder.append("5 - Save all data to file\n");
        menuBuilder.append("6 - Sort data by name\n");
        menuBuilder.append("7 - Sort data by value\n");
        menuBuilder.append("8 - Filter data by name\n");
        menuBuilder.append("9 - Filter data by value\n");
        menuBuilder.append("0 - Exit\n");
    }

    /*method, which show main menu and choosing next actions*/
    public void menu() {
        System.out.println(menuBuilder.toString());
        String answer = scanningAnswer();
        switch (answer.charAt(0)){
            case '1':
                currentDataPairs = new ArrayList<DataPair>();
                currentDataPairs = parseFromFile(loadFile());
                System.out.println("Data loaded!");
                menu();
                break;
            case '2':
                showDataPairs(currentDataPairs);
                menu();
                break;
            case '3':
                DataPair addedDataPair = getDataPair();
                if(currentDataPairs == null){
                    currentDataPairs = new ArrayList<DataPair>();
                }
                currentDataPairs.add(addedDataPair);
                System.out.println("Data pair added!");
                menu();
                break;
            case '4':
                System.out.println("Enter name of data pair: ");
                String name = scanningAnswer();
                for (int i = 0; i < currentDataPairs.size(); i++) {
                    if(currentDataPairs.get(i).getName().equals(name)){
                        currentDataPairs.remove(i);
                    }
                }
                System.out.println("Data pair"+name+" removed");
                menu();
                break;
            case '5':
                System.out.println("Data is saved? "+saveData());
                menu();
                break;
            case '6':
                System.out.println("1 - Ascending order");
                System.out.println("2 - Descending order");
                String nameOrder = scanningAnswer();
                if(nameOrder.equals("1")){
                    Collections.sort(currentDataPairs, DataPair.NameAscendingComparator);
                    System.out.println("Data sorted!");
                } else if(nameOrder.equals("2")){
                    Collections.sort(currentDataPairs,Collections.reverseOrder(DataPair.NameAscendingComparator));
                    System.out.println("Data sorted!");
                }else
                    System.out.println("Wrong argument!");
                menu();
                break;
            case '7':
                System.out.println("1 - Ascending order");
                System.out.println("2 - Descending order");
                String valueOrder = scanningAnswer();
                if(valueOrder.equals("1")){
                    Collections.sort(currentDataPairs, DataPair.ValueAscendingComparator);
                    System.out.println("Data sorted!");
                } else if(valueOrder.equals("2")){
                    Collections.sort(currentDataPairs,Collections.reverseOrder(DataPair.ValueAscendingComparator));
                    System.out.println("Data sorted!");
                }else
                    System.out.println("Wrong argument!");
                menu();
                break;
            case '8':
                System.out.println("Enter filter name: ");
                String nameFilter = scanningAnswer().toLowerCase();
                List<DataPair> filteredByName = new ArrayList<DataPair>();
                for (int i = 0; i < currentDataPairs.size(); i++) {
                    String currentName = currentDataPairs.get(i).getName().toLowerCase();
                    if(nameFilter.equals(currentName)){
                        filteredByName.add(currentDataPairs.get(i));
                    }
                }
                showDataPairs(filteredByName);
                menu();
                break;
            case '9':
                System.out.println("Enter filter name: ");
                String valueFilter = scanningAnswer().toLowerCase();
                List<DataPair> filteredByValue = new ArrayList<DataPair>();
                for (int i = 0; i < currentDataPairs.size(); i++) {
                    String currentValue = currentDataPairs.get(i).getValue().toLowerCase();
                    if(valueFilter.equals(currentValue)){
                        filteredByValue.add(currentDataPairs.get(i));
                    }
                }
                showDataPairs(filteredByValue);
                menu();
                break;

            case '0':
                System.out.println("Finish Text File Worker!");
                System.exit(0);
                break;
            default:
                menu();
        }
    }

    private void showDataPairs(List<DataPair> dataPairs) {
        for (int i = 0; i < dataPairs.size(); i++) {
            System.out.println(dataPairs.get(i).getName()+" = "+dataPairs.get(i).getValue());
        }
    }

    private DataPair getDataPair() {
        System.out.println("Enter data pair with symbol ' = ' between");
        String datapairLine = scanningAnswer();
        return parseStringLineToDataPair(datapairLine);
    }

    private File loadFile() {
        System.out.println("Enter file path:");
        String filePath = scanningAnswer();
        File currentFile = new File(filePath);
        return currentFile;
    }

    private boolean saveData(){
        System.out.println("Enter file path to save:");
        String filePath = scanningAnswer();
        return saveToFile(currentDataPairs, filePath);
    }

    private static String scanningAnswer(){
        Scanner scanner = new Scanner(System.in);
        String answer = scanner.nextLine();
        return answer;
    }

}
