public class BinaryTreeAlgo {
    public static class Node {
        String data;
        Node left, right;

        Node(String data) {
            this.data = data;
        }
    }
    //
    // Idée: Infixé -> Arbre binaire -> Parcours Post-Ordre -> Post fixé
    //
    // - On identifie l'opérateur ayant la plus basse préséance en dehors des parenthèses
    //   et crée un noeud pour cet opérateur (principal).
    // - Les sous-expressions à gauche et à droite de cet opérateur sont traitées de manière récursive
    //   pour créer les sous-arbres gauche et droit.
    // - Une fois l'arbre construit, un parcours en post-ordre.

    // Méthode lancer l'arbre binaire à partir d'une expression infixée
    public static Node TreeFromInfix(String infix) {
        return buildTreeFromInfix(infix, 0, infix.length() - 1);
    }

    // Méthode pour construire l'arbre binaire
    private static Node buildTreeFromInfix(String infix, int start, int end) {
        if (start > end) {
            return null; // Condition d'arrêt -> intervalle invalide
        }

        int minPrecedence = Integer.MAX_VALUE; // Vérification précedence
        int mainOperator = -1; // Position de mainOperator

        for (int i = start; i <= end; i++) {
            char c = infix.charAt(i);

            // Gestion des parenthèses
            if (c == '(') {
                int openBrackets = 1;
                while (openBrackets > 0) {
                    i++;
                    if (infix.charAt(i) == '(') openBrackets++;
                    if (infix.charAt(i) == ')') openBrackets--;
                }
            }
            // Trouver mainOperator avec la plus basse préséance
            else if (Arithmetic.isOperator(c) && Arithmetic.priorityOf(c) <= minPrecedence) {
                minPrecedence = Arithmetic.priorityOf(c);
                mainOperator = i;
            }
        }

        // Si aucun mainOperator trouvé, vérifier les parenthèses ou retourner le nombre
        if (mainOperator == -1) {
            if (infix.charAt(start) == '(' && infix.charAt(end) == ')') {
                return buildTreeFromInfix(infix, start + 1, end - 1);
            }
            return new Node(infix.substring(start, end + 1));
        }

        // Créer le noeud racine avec le mainOperator
        Node root = new Node(String.valueOf(infix.charAt(mainOperator)));
        // Construire les cotés gauche et droit
        root.left = buildTreeFromInfix(infix, start, mainOperator - 1);
        root.right = buildTreeFromInfix(infix, mainOperator + 1, end);

        return root;
    }

    // Méthode pour effectuer le parcours post-ordre
    public static String TreetoPostfix(Node root) {
        if (root == null) {
            return "";
        }
        String left = TreetoPostfix(root.left);
        String right = TreetoPostfix(root.right);
        return left + right + root.data + " ";
    }

    // Méthode pour évaluer l'arbre binaire
    public static int evaluateTree(Node root) {
        if (root == null) {
            return 0;
        }
        // Si le noeud n'est pas un opérateur -> retourner la valeur numérique
        if (!Arithmetic.isOperator(root.data.charAt(0))) {
            return Integer.parseInt(root.data);
        }
        // Évaluer récursivement le sous-arbre gauche
        int leftValue = evaluateTree(root.left);
        // Évaluer récursivement le sous-arbre droit
        int rightValue = evaluateTree(root.right);

        // Lancer l'opération de l'opérateur actuel sur les valeurs des sous-arbres gauche et droit
        return Arithmetic.operation(leftValue, rightValue, root.data.charAt(0));
    }
}
