
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class betterQuestion {
    static Map<Integer, String> token = Map.of(0, "-", 1, "+");
    static int N = 1; // 记录题数

    public static void generateQuestions(int n) {
        for (int i = 0; i < n; i++) {
            getOneQuestion();
            N++;
        }
    }

    private static void getOneQuestion() {
        int[] nums = new int[2];
        int min = 0, max = 100;
        int res = 0;
        int t;
        String op = getOperation();
        do {
            nums[0] = min + (int) (Math.random() * (max - min));
            nums[1] = min + (int) (Math.random() * (max - min));
            res = getResult(nums, op);
        } while (res > max);
        if (res < 0) {
            res = -res;
            t = nums[0];
            nums[0] = nums[1];
            nums[1] = t;
        }
        String question = nums[0] + " " + op + " " + nums[1] + " = ";
        saveQuestionToFile(question);
        saveResultToFile(res + "");
    }

    private static void saveQuestionToFile(String question) {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter("Exercises.txt", true));
            // System.out.println(question);
            bw.write(N + ". " + question);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveResultToFile(String res) {
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter("Answers.txt", true));
            bw.write(N + ". " + res);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getOperation() {
        int r = (int) (Math.random() * 2);
        return token.get(r);
    }

    private static int getResult(int[] nums, String op) {
        int sum = 0;
        switch (op) {
            case "+":
                sum = nums[0] + nums[1];
                break;
            default:
                sum = nums[0] - nums[1];
                break;
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        generateQuestions(50);
    }
}
