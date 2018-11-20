/*
Example call cmd line
    Compiling cmd line : javac ospp1_201_0000.java
    Execution cmd line : java ospp1_201_0000 testdata.csv result.txt

    @2018-11-19, in AA class, cau MI lab, 2018.
*/
import java.io.*;

// cau.alg.ospp@gmail.com

// Change your filename and classname to ospp1_[Your Student Number]
public class ospp1_201_0000{
    public static void main(String[] args){
        // Assume call cmd: java AA [InputFilePath] [OutputFilePath]
        File inputFile = new File(args[0]);
        File outputFile = new File(args[1]);

        // File Reading
        int [] result = fileLoader(inputFile);

        // There is a example code.
        // You can write your algorithm in this block
        for(int i=0;i<result.length;i++){
            for(int j=i+1;j<result.length;j++){
                if(result[j] < result[i]){
                    int temp = result[i];
                    result[i] = result[j];
                    result[j] = temp;
                }
            }
        }
        
        // File Writing
        resultWriter(result, outputFile);
        return;
    }

    public static int [] fileLoader(File iFile){
        int [] RET = null;
        String line = "";
        try{
            BufferedReader bufferReader = new BufferedReader(new FileReader(iFile));
            line=bufferReader.readLine();
            String[] strNum = line.split(",",-1);
            RET = new int[strNum.length];
            for(int i=0;i<strNum.length;i++){
                RET[i] = Integer.parseInt(strNum[i]);
            }
            bufferReader.close();
        } catch(IOException e){
            e.printStackTrace();
        }
        return RET;
    }

    public static int resultWriter(int[] result, File oFile){

        for(int i=0;i<result.length-1;i++){
            if(result[i]>result[i+1]){
                System.out.println("Error: sort result");
                System.out.println(i+"th :"+result[i]+", "+(i+1)+"th :"+result[i+1]);
                return -1;
            }
        }

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
