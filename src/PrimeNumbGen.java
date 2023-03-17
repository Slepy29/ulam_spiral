import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class PrimeNumbGen {
    private final int width;
    private final int height;
    private static boolean generatoted = false;
    private boolean[] primesArr;
    public PrimeNumbGen(int width, int height) {
        this.width = width;
        this.height = height;
        int maxValue = height<=width?height*height*10:width*width*10;
        int byteSizeCounter = 1;
        this.primesArr = primes(maxValue+1);
        if (!generatoted){
            ArrayList<Integer> arrayList = new ArrayList<>();
            try {
                FileOutputStream fos = new FileOutputStream("data.bin",false);
                FileWriter fw = new FileWriter("data.bin",true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);
                for (int i = 2; i < primesArr.length; i++) {
                    if (primesArr[i]){
                        if (i<=Math.pow(2,byteSizeCounter*8)-1){
                            arrayList.add(i);
                        }
                        else{
                            writeToFile(fos,byteSizeCounter,arrayList);
                            pw.println();
                            arrayList.clear();
                            arrayList.add(i);
                            byteSizeCounter++;
                        }
                    }
                }
                writeToFile(fos,byteSizeCounter,arrayList);
                pw.close();
                bw.close();
                fw.close();
                generatoted = true;
            }
            catch (Exception e){
                e.getStackTrace();
            }
        }

    }

    public boolean[] getPrimesArr() {
        return primesArr;
    }
    private void writeToFile(FileOutputStream FOS, int byteCounter, ArrayList<Integer> arrayList){
        int size = arrayList.size();
        int sizeByteSpace;

        if (size<=(Math.pow(2,8)-1))
            sizeByteSpace = 1;
        else if (size<=(Math.pow(2,16)-1))
            sizeByteSpace = 2;
        else if (size<=(Math.pow(2,24)-1))
            sizeByteSpace = 3;
        else
            sizeByteSpace = 4;

        try {
            //Numbers of primes in line
            for (int i = 0; i < 8-sizeByteSpace; i++) {
                FOS.write(0);
            }
            switch (sizeByteSpace){
                case 1 -> FOS.write(size);
                case 2 -> {
                    FOS.write(size>>>8);
                    FOS.write((size<<24)>>>24);
                }
                case 3 -> {
                    FOS.write(size>>>16);
                    FOS.write((size<<16)>>>24);
                    FOS.write((size<<24)>>>24);
                }
                case 4 -> {
                    FOS.write(size>>>24);
                    FOS.write((size<<8)>>>24);
                    FOS.write((size<<16)>>>24);
                    FOS.write((size<<24)>>>24);
                }
            }

            //Primes
            for (int num : arrayList) {
                switch (byteCounter){
                    case 1 -> FOS.write(num);
                    case 2 -> {
                        FOS.write(num>>>8);
                        FOS.write((num<<24)>>>24);
                    }
                    case 3 -> {
                        FOS.write(num>>>16);
                        FOS.write((num<<16)>>>24);
                        FOS.write((num<<24)>>>24);
                    }
                    case 4 -> {
                        FOS.write(num>>>24);
                        FOS.write((num<<8)>>>24);
                        FOS.write((num<<16)>>>24);
                        FOS.write((num<<24)>>>24);
                    }
                }
            }
        }catch (Exception e){
            e.getStackTrace();
        }
    }
    private boolean[] primes(int num){
        boolean primesArr[] = new boolean[num];
        Arrays.fill(primesArr, true);
        primesArr[0]=false;
        primesArr[1]=false;
        for(int i=2;i<=Math.sqrt(num);i++)
        {
            for(int t=i+i;t<num;t=t+i){
                primesArr[t]=false;
            }
        }
        return primesArr;
    }
}