import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n=Integer.parseInt(scan.nextLine());
        int xl=1000000000;
        int yl=1000000000;
        int xr=-1000000000;
        int yr=-1000000000;
        for(int i=0;i<n;i++){
            Scanner line=new Scanner(scan.nextLine());
            int x=line.nextInt();
            int y=line.nextInt();
            int h=line.nextInt();
            xl=Math.min(xl, x-h);
            yl=Math.min(yl, y-h);
            xr=Math.max(xr, x+h);
            yr=Math.max(yr, y+h);
        }
        int ansH=(int) Math.max(xr-xl, yr-yl);
        ansH=(int) (ansH+1)/2;
        int ansX= (xl+xr)/2;
        int ansY= (yl+yr)/2;
        System.out.println(ansX+" "+ansY+" "+ansH);
    }
}

