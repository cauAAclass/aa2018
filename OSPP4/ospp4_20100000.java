/*
Example call cmd line
    Compiling cmd line : javac ospp4_20100000.java
    Execution cmd line : java ospp4_20100000 testdata1.csv result.txt 0

    @2018-12-02, in AA class, cau MI lab, 2018.
*/
import java.io.*;
import java.lang.Math.*;

// cau.alg.ospp@gmail.com

// Change your filename and classname to ospp3_[Your Student Number]
// ___________________________________________________________________
public class ospp4_20100000{  // CHANGE the class name to YOUR STUDENT NUMBER!!
// ___________________________________________________________________
    public static void main(String[] args){
        // Assume call cmd: java ospp4_20100000 [Data: inputFilePath] [Result: OutputFilePath] [StartCityNumber]
        File inputFile = new File(args[0]);
        File outputFile = new File(args[1]);
        int startCityNum = Integer.parseInt(args[2]);

        // File Reading
        int[][] mapData = fileLoader_4(inputFile);
        // mapData[a][b] <- the distance(cost) of road, city a to city b.

        //___________________________
        //      mapData[a][b]
        // a --------------------> b 
        //
        // a <-------------------- b 
        //      mapData[b][a]
        //___________________________
        // Note that can't mapData[a][b] == mapData[b][a].

        int nodeSize = mapData.length;
        int[] result = new int[nodeSize];
        // result[i] <-- minimum distance(cost) of route startCity to i-th City.
        // Assume --> return[startCity] = 0;

        for(int i=0;i<nodeSize;i++){
            result[i] = 2;
        }
        result[startCityNum]=0;


        // File Writing
        resultWriter_4(result, outputFile);
        return;
    }

    public static int [][] fileLoader_4(File iFile){
        int nodeSize = -1;
        int[][] RET = null;
        String line = "";
        try{
            BufferedReader bufferReader = new BufferedReader(new FileReader(iFile));
            int row=0;
            while((line=bufferReader.readLine())!=null){
                String[] strNum = line.split(",",-1);
                if(nodeSize==-1){
                    nodeSize = strNum.length;
                    RET = new int[nodeSize][nodeSize];
                }
                for(int col=0;col<nodeSize;col++){
                    RET[row][col] = Integer.parseInt(strNum[col]);
                }
                row++;
                if(row>=nodeSize){
                    break;
                }
            }
            bufferReader.close();
        } catch(IOException e){
            e.printStackTrace();
        }
        return RET;
    }

    public static int resultWriter_4(int[] result, File oFile){
        try{
            FileWriter fWriter = new FileWriter(oFile);
            for (int i=0;i<result.length;i++){
                fWriter.write(result[i]+" ");
            }
            fWriter.close();
        } catch(IOException e){
            for (int i=0;i<result.length;i++){
                System.out.print(result[i]+" ");
            }
            e.printStackTrace();
        }
        return 0;
    }
}
