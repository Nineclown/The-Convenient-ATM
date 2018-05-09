import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Interpreting {

    private BufferedReader reader;
    private BufferedWriter writer;
    private boolean[][][][] check;
    private char[][] asembly;
    private String[] s;
    private  String output;
    // 방향.
    private  int[] dx = {0, 0, -1, 1};
    private int[] dy = {1, -1, 0, 0};

    private int tc;
    private int row, column;
    private int memory, cases;
    private int direction;
    private boolean result;

    public Interpreting() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));
    }

    public void init() throws Exception {
        int i, j;
        tc = Integer.parseInt(reader.readLine());

        for (cases = 1; cases <= tc; cases++) {
            // 기본 입력을 받는다.
            s = reader.readLine().split(" ");
            row = Integer.parseInt(s[0]);
            column = Integer.parseInt(s[1]);

            // 받은 입력을 바탕으로 혁셈블리어 초기화.
            asembly = new char[row][column];
            for (i = 0; i < row; i++) {
                String temp = reader.readLine();
                for (j = 0; j < column; j++) {
                    asembly[i][j] = temp.charAt(j);
                    // 종료될 가능성이 있는 언어와 그렇지 않은 언어로 구분.
                    if ('@' == asembly[i][j])
                        result = true;
                }
            }

            // 종료될 가능성이 있을 경우.
            if (result) {
                result = false;
                memory = 0;
                direction = 0;
                // 방문했는지를 체크할 혁셈블리어 코드와 동일한 사이즈 + 방향을 담을 1byte와 메모리 상태를 담는 1byte.
                check = new boolean[row][column][4][16];
                interpreter(0, 0);
            }

            output = (result) ? "YES" : "NO";

            writer.write("#" + cases + " " + output + "\n");
            writer.flush();
        }
    }

    public void interpreter(int x, int y) {
        // 방향 설정.
        if (x == -1) x = row - 1;
        if (x == row) x = 0;
        if (y == -1) y = column - 1;
        if (y == column) y = 0;

        switch (asembly[x][y]) {
            case '>':
                direction = 0;
                break;
            case '<':
                direction = 1;
                break;
            case '^':
                direction = 2;
                break;
            case 'v':
                direction = 3;
                break;
            case '_':
                direction = (memory == 0) ? 0 : 1;
                break;
            case '|':
                direction = (memory == 0) ? 3 : 2;
                break;
            case '+':
                memory++;
                if (memory == 16) memory = 0;
                break;
            case '-':
                memory--;
                if (memory == -1) memory = 15;
                break;
            case '@':
                result = true;
                break;
        }

        if (result) return;
        if (asembly[x][y] >= 48 && asembly[x][y] <= 57) memory = asembly[x][y] - 48;

        // 프로그램이 종료될 가능성이 있는지 묻는 문제이기 때문에 4가지 방향 모두 검사를 해야 한다.
        if (asembly[x][y] == '?') {
            for (int i = 0; i < 4; i++) {
                direction = i;
                // 검사를 했는가를 기록. visited.
                if (!check[x][y][i][0]) {
                    check[x][y][i][0] = true;
                    interpreter(x + dx[i], y + dy[i]);
                }
            }
        }
        // 해석을 마친 후, 진행 방향대로 계속 이동한다. 방문을 안한 경우에만.
        else {
            if (!check[x][y][direction][memory]) {
                check[x][y][direction][memory] = true;
                interpreter(x + dx[direction], y + dy[direction]);
            }
        }
    }

    public int plus(int a, int b) {
        return (a + b);
    }

    public static void main(String[] args) throws Exception {
        Interpreting mySol = new Interpreting();
        mySol.init();

    }
}
