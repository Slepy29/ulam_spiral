import java.util.ArrayList;
import java.util.HashMap;

public class PrimeNumbGen {
    private int width;
    private int height;
    private HashMap hMap;
    public PrimeNumbGen(int width, int height) {
        this.width = width;
        this.height = height;
        int maxValue = height<=width?height*height*10:width*width*10;
        int byteSizeCounter = 1;
        HashMap<Integer,ArrayList<Integer>> hashMap = new HashMap<>();//key - byte size | val - prime list
        hashMap.put(byteSizeCounter,new ArrayList<Integer>());
        for (int i = 2; i <= maxValue; i++) {
            if (isPrime(i)){
                if (i<=Math.pow(2,byteSizeCounter*8-1)-1)
                    hashMap.get(byteSizeCounter).add(i);
                else{
                    byteSizeCounter++;
                    hashMap.put(byteSizeCounter,new ArrayList<Integer>());
                    hashMap.get(byteSizeCounter).add(i);
                }
            }
        }
        //System.out.println(hashMap);
        hMap=hashMap;
    }

    public HashMap gethMap() {
        return hMap;
    }

    private boolean isPrime(int num){
        int a = 2;
        if (num==1)
            return false;
        if (num==2)
            return true;
        do{
            if (num%a==0)
                return false;
            a++;
        }while(a<=Math.sqrt(num));
        return true;
    }
}