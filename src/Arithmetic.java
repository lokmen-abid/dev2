public class Arithmetic {
    public static boolean isOperator(char c) {
        return "+-*/".indexOf(c) != -1;
    }

    // Methode pour déterminer la priorité des opérateurs
    public static int priorityOf(char operator) {
        return switch (operator) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> -1;
        };
    }

    // Methode effectue une opération et retourner le résultat
    public static int operation(int left, int right, char operator) {
        return switch (operator) {
            case '+' -> left + right;
            case '-' -> left - right;
            case '*' -> left * right;
            case '/' -> {
                if (right == 0) throw new ArithmeticException("Division par zéro");
                yield left / right;
            }
            default -> throw new IllegalArgumentException("Opérateur invalide: " + operator);
        };
    }

}
