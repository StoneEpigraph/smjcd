package cn.sm.algorithms.base;

public class ArrayTest {

    public static void main(String[] args) throws Exception{
        booleanArray(10, 10);
    }

    // 二维布尔数组,只有M和N互质时才为true
    public static void booleanArray(int m, int n) throws Exception{
        boolean[][] arr = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = i; j < n; j++) {
                int maxGcd = Gcd.gcd(i + 1, j + 1);
                System.out.printf("i = %d, j = %d, gcd = %d", i, j, maxGcd);
                System.out.println("");
                if (maxGcd <= 1) {
                    arr[i][j] = true;
                    if (i < n && j < m) {
                        arr[j][i] = true;
                    }

                } else {
                    arr[i][j] = false;
                    if (i < n && j < m) {
                        arr[j][i] = false;
                    }
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + "\t");
            }
            System.out.println("");
        }
    }
}
