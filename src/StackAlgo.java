public class StackAlgo {

    // Méthode pour transformer une expression infixée en expression post-fixée
    public static String infixToPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        Stack stack = new Stack(expression.length());


        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // Si le caractère est un espace, continuer
            if (c == ' ') {
                continue;
            }
            // Si le caractère est un chiffre
            if (Character.isDigit(c)) {
                // Gérer les nombres à plusieurs chiffres
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    result.append(expression.charAt(i++));
                }
                result.append(' ');
                i--; // Ajuster l'index après la boucle
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append((char) stack.pop()).append(' ');//.append(' ') pour garantir que chaque élément est séparé.
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && Arithmetic.priorityOf((char) stack.peek()) >= Arithmetic.priorityOf(c)) {
                    result.append((char) stack.pop()).append(' ');//.append(' ') pour garantir que chaque élément est séparé.
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            result.append((char) stack.pop()).append(' ');//.append(' ') pour garantir que chaque élément est séparé.

        }

        return result.toString();
    }

    private static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    // Méthode pour évaluer une expression post-fixée
    public static int evaluatePostfix(String postfixExpression) {
        // Initialisation de la pile
        Stack stack = new Stack(postfixExpression.length());

        // On divise l'expression postfixée en éléments individuels, " " -> séparateur
        String[] elements = postfixExpression.split(" ");
        // Boucle
        for (String element : elements) {
            if (isNumeric(element)) {
                stack.push(Integer.parseInt(element)); // Pousser l'entier sur la pile
            } else {
                int val1 = stack.pop(); // Dépiler la première valeur
                int val2 = stack.pop(); // Dépiler la deuxième valeur
                // Appliquer l'opération arithmétique et pousser le résultat sur la pile
                stack.push(Arithmetic.operation(val2, val1, element.charAt(0)));
            }
        }
        return stack.pop(); // Retourner le résultat final
    }



}
