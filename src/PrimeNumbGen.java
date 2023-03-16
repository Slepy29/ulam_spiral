import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class PrimeNumbGen {
    private final int width;
    private final int height;
    private boolean[] primesArr;
    public PrimeNumbGen(int width, int height) {
        this.width = width;
        this.height = height;
        int maxValue = height<=width?height*height*10:width*width*10;
        int byteSizeCounter = 1;
        this.primesArr = primes(maxValue+1);
        ArrayList<Integer> arrayList = new ArrayList<>();
        try {
            FileWriter fw = new FileWriter("data.bin",false);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for (int i = 2; i < primesArr.length; i++) {
                if (primesArr[i]){
                    if (i<=(Math.pow(2,(byteSizeCounter*8)-1)-1)){
                        arrayList.add(i);
                    }
                    else{
                        for (int j = 0; j < 8-(32-Integer.numberOfLeadingZeros(arrayList.size()))/8; j++) {
                            pw.print(0+"\t");
                        }
                        pw.print("["+arrayList.size()+"]\t");
                        for (int num : arrayList) {
                            pw.print(num+"\t");
                        }
                        pw.println();
                        arrayList.clear();
                        arrayList.add(i);
                        byteSizeCounter++;
                    }
                }
            }
            for (int j = 0; j < 8-(32-Integer.numberOfLeadingZeros(arrayList.size()))/8; j++) {
                pw.print(0+"\t");
            }
            pw.print("["+arrayList.size()+"]\t");
            for (int num : arrayList) {
                pw.print(num+"\t");
            }
            pw.close();
            bw.close();
            fw.close();
        }
        catch (Exception e){
            e.getStackTrace();
        }
    }

    public boolean[] getPrimesArr() {
        return primesArr;
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