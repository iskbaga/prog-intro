import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scan=new Scanner(System.in);
        int a=scan.nextInt();
        int b=scan.nextInt();
        int c=scan.nextInt();
        int s=0;
        if(((c-b)%(b-a))>0){
            s++;
        }
        s+=(c-b)/(b-a);
        s=s*2;
        System.out.println(s+1);

    }
}