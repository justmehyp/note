public class ZuiXiaoGongBeiShu {

    /**
     * @param a: Left margin
     * @param b: Right margin
     * @return: return the greatest common multiple
     */
    public long greatestcommonmultiple(int a, int b) {
        // write your code here
        return getLcm(getLcm(b - 2, b - 1), b);
    }

    private long getGcd(long m,long n){
        while(n > 0){
            long temp = m % n;
            m = n;
            n = temp;
        }
        return m;
    }

    private long getLcm(long m,long n){
        long gcd = getGcd(m,n);
        return (m * n) / gcd;
    }


}
