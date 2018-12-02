/*
Example call cmd line
    Compiling cmd line : javac ospp2_201_0000.java
    Execution cmd line : java ospp2_201_0000 testdata.csv result.txt

    @2018-11-19, in AA class, cau MI lab, 2018.
*/
import java.io.*;

// cau.alg.ospp@gmail.com

// Change your filename and classname to ospp2_[Your Student Number]
// ___________________________________________________________________
public class ospp2_20124902{  // CHANGE the class name to YOUR STUDENT NUMBER!!
// ___________________________________________________________________
    public static void main(String[] args){
        // Assume call cmd: java AA [InputFilePath] [OutputFilePath]
        File inputFile = new File(args[0]);
        File outputFile = new File(args[1]);
        // File Reading
        int[] input = fileLoader(inputFile);

        // Reference : My STONE Head. I love myself. Sincerely.
        int[] result;
        result = quicksort(input);

        // File Writing
        resultWriter(result, outputFile);
        return;
    }

    public static void partsort(int[] Arr, int beginIdx, int endIdx){
        // [beginIdx, ..., endIdx];
        int criIdx = beginIdx;
        int leftIdx = beginIdx+1, rightIdx = endIdx;

        if(leftIdx > rightIdx){
            return;
        }
        while( leftIdx<rightIdx ){
            while( leftIdx<rightIdx && Arr[leftIdx]<=Arr[criIdx] ){leftIdx++;}
            while( leftIdx<rightIdx && Arr[criIdx]<=Arr[rightIdx]){rightIdx--;}
            swap(Arr, leftIdx, rightIdx);
        }
        if(leftIdx!=rightIdx){ return; } // arrest Error. If not equal, There is singularity point. REALLY?
        if( Arr[criIdx] <= Arr[leftIdx] ){ leftIdx--; }
        swap(Arr, criIdx, leftIdx);

        partsort(Arr, beginIdx, leftIdx-1);
        partsort(Arr, leftIdx+1, endIdx);
    }

    public static int[] quicksort(int[] Arr){
        int[] RET = new int[Arr.length];

        System.arraycopy( Arr, 0, RET, 0, Arr.length);
        partsort(RET, 0, RET.length-1);
        return RET;
    }

    public static void swap(int[] Arr, int index1, int index2){
        int temp = Arr[index1];
        Arr[index1] = Arr[index2];
        Arr[index2] = temp;
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
