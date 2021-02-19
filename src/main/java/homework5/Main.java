package homework5;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        String[] header = {"Value1", "Value2", "Value3"};
        int [][] data = {{100, 400, 600}, { 400,240,358}};
        AppData appData = new AppData(header, data);
        writeFile(appData);
        readFile("file.csv");
    }

    public static void writeFile(AppData appData){
        try(PrintWriter out = new PrintWriter("file.csv")){
            for (String v: appData.getHeader()){
                out.print(v + ";");
            }
            out.println();

            int [][] valListData = appData.getData();
            for (int i = 0;i< valListData.length; i++){
                for ( int j =0; j < valListData[i].length; j++){
                    out.print(valListData[i][j] + ";");
                }
                out.write("\r\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static AppData readFile (String filename) throws IOException{
        BufferedReader in = new BufferedReader(new FileReader(filename));
        List<int[]> data = new ArrayList<>();
        String[] dataHead = in.readLine().split(";");
        String line = null;
        while ((line= in.readLine()) != null){
            List <Integer> list = new ArrayList<>();
            String [] string = line.split(";");
            for (int i = 0; i < string.length; i++){
                list.add(Integer.parseInt(string[i]));
            }
            data.add(list.stream().mapToInt(i -> i).toArray());
        }
        AppData appData = new AppData(dataHead, data.toArray(new int[0][]));
        int [][] array = appData.getData();
        return  appData;
    }
}
