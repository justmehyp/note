public class RunNian {

    private static final int[] days = { 0,
            31, 29, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31
    };

    /**
     * @param inputQueries: input Queries, means [[m1, d1, m2, d2, x], [m1, d1, m2, d2, x],...]
     * @return: guess whether y1 is leep year
     */
    public String guessYear(int[][] inputQueries) {
        // write your code here

        StringBuilder sb = new StringBuilder();
        for (int[] inputQuery : inputQueries) {
            sb.append(guessYear(inputQuery));
        }
        return sb.toString();
    }

    private char guessYear(int[] inputQuery) {
        if (inputQuery[0] <= 2 && inputQuery[2] == 2 && inputQuery[3] == 29) {
            return 'R';
        }

        int x = inputQuery[4];
        int m1 = inputQuery[0];
        int d1 = inputQuery[1];
        int m2 = inputQuery[2];
        int d2 = inputQuery[3];

        boolean isFrom2To3 = false;

        x -= days[m1] - d1 + 1;
        int m = m1 + 1;
        if(m == 13) m = 1;
        int d = 1;
        if (m == 3) {
            isFrom2To3 = true;
        }
        while (x > 0) {
            if (x >= days[m]) {
                x -= days[m];
                m++;
                if(m == 13) m = 1;

                if (m == 3) {
                    isFrom2To3 = true;
                }
            } else {
                d = 1 + x;
                x = 0;
            }
        }

        return isFrom2To3 && m1 <=2 ? (m == m2 && d == d2 ? 'R' : 'P') : '?';
    }
}
