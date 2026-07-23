package Lesson_7;

public class Calculation {
    private int a,b;
    private String sign;
    public static int calculation(int a, String sign, int b) throws ArithmeticException {
        return switch (sign) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> {
                if (b == 0) throw new ArithmeticException("Делить на ноль нельзя.");
                yield a / b;
            }
            default -> {
                throw new ArithmeticException("Вы ввели некорректный знак.");
            }
        };
    }

    public static void main(String[] args) {
        try {
            System.out.println(calculation(20, "*", 11));
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }
}
