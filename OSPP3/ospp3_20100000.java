/*
Example call cmd line
    Compiling cmd line : javac ospp3_20100000.java
    Execution cmd line : java ospp3_20100000 testdata.csv result.txt 10

    @2018-12-02, in AA class, cau MI lab, 2018.
*/
import java.io.*;

// cau.alg.ospp@gmail.com

// Change your filename and classname to ospp3_[Your Student Number]
// ___________________________________________________________________
public class ospp3_20100000{  // CHANGE the class name to YOUR STUDENT NUMBER!!
// ___________________________________________________________________
    public static void main(String[] args){
        // Assume call cmd: java AA [InputFilePath] [OutputFilePath] [weight]
        File inputFile = new File(args[0]);
        File outputFile = new File(args[1]);
        int weight = Integer.parseInt(args[2]);
        // File Reading
        int[][] input = fileLoader_3(inputFile);

        // input[0][k] <- the value of k-th object
        // input[1][k] <- the weight of k-th object
        for(int i=0;i<input.length;i++){
            for(int j=0;j<input[i].length;j++){
                System.out.printf("%d ", input[i][j]);
            }
            System.out.println();
        }

        double result = input[0][0]; // print(Weight) ..?
        // Result

        // File Writing
        resultWriter_3(result, outputFile);
        return;
    }

    public static int [][] fileLoader_3(File iFile){
        int [][] RET = new int[2][];
        String line = "";
        try{
            BufferedReader bufferReader = new BufferedReader(new FileReader(iFile));
            for(int lineNum=0;lineNum<2;lineNum++){
                line = bufferReader.readLine();
                String[] strNum = line.split(",",-1);
                RET[lineNum] = new int[strNum.length];
                for(int i=0; i<strNum.length; i++){
                    RET[lineNum][i] = Integer.parseInt(strNum[i]);
                }
            }
            bufferReader.close();
        } catch(IOException e){
            e.printStackTrace();
        }
        return RET;
    }

    public static int resultWriter_3(double result, File oFile){
        try{
            FileWriter fWriter = new FileWriter(oFile);
            fWriter.write(result+" ");
            fWriter.close();
        } catch(IOException e){
            System.out.print(result+" ");
            e.printStackTrace();
        }
        return 0;
    }
}
