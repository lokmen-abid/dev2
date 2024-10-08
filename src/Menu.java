import java.util.Scanner;

public class Menu {
    public void afficherMenu() {
        Scanner scanner = new Scanner(System.in);
        int choix;
        do {
            System.out.println("--- MENU ---------------------------------------");
            System.out.println("1. Évaluer une expression avec un arbre binaire");
            System.out.println("2. Évaluer une expression avec une pile");
            System.out.println("3. Quitter");
            System.out.println("------------------------------------------------");
            System.out.print("Choix: ");
            choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    evaluerAvecArbreBinaire(scanner);
                    break;
                case 2:
                    evaluerAvecPile(scanner);
                    break;
                case 3:
                    System.out.println("Merci, au revoir!");
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        } while (choix != 3);

        scanner.close();
    }

    // Méthode pour lancer l'évaluation d'une expression en utilisant un arbre binaire
    public void evaluerAvecArbreBinaire(Scanner scanner) {
        System.out.print("Saisir l'expression infixée: ");
        String expression = scanner.nextLine();


        // Construire l'arbre binaire
        BinaryTreeAlgo.Node root = BinaryTreeAlgo.TreeFromInfix(expression);

        // Convertir l'arbre en post-fixée et afficher
        String postfixExpression = BinaryTreeAlgo.TreetoPostfix(root);
        System.out.println("Expression post-fixée: " + postfixExpression);

        // Afficher le résultat de l'évaluation
        int resultat = BinaryTreeAlgo.evaluateTree(root);
        System.out.println("Résultat avec arbre binaire: " + resultat);
    }



    // Méthode pour lancer l'évaluation d'une expression en utilisant une pile
    public void evaluerAvecPile(Scanner scanner) {
        System.out.print("Saisir l'expression infixée: ");
        String expression = scanner.nextLine();

        // Convertir l'expression infixée en notation post-fixée
        String postfixExpression = StackAlgo.infixToPostfix(expression);
        System.out.println("Expression post-fixée: " + postfixExpression);

        // Évaluer l'expression post-fixée dans la pile/stack
        int resultat = StackAlgo.evaluatePostfix(postfixExpression);

        // Afficher le résultat de l'évaluation
        System.out.println("Résultat avec pile : " + resultat);
    }
}
