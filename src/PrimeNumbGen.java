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
        /*for (int i = 2; i <= primesArr.length; i++) {
            if (primesArr[i]){
                if (i<=Math.pow(2,byteSizeCounter*8-1)-1){
                    //System.out.println(i<<byteSizeCounter);
                }
                else{
                    byteSizeCounter++;

                }
            }
        }*/
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