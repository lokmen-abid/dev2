class Node{
    public char key;
    public Node leftChild;
    public Node rightChild;

    public Node(char value) {
        key = value;
        this.leftChild = null;
        this.rightChild = null;
    }
}
public class BinaryTree {
    private int count;
    private Node root;

    public BinaryTree(){
        count = 0;
        root = null;
    }
    public void insert(char keyToInsert){
        Node keyNode = new Node(keyToInsert);
        if(root == null){
            root = keyNode;
        }

        Node current = root;
        Node parent = null;
        boolean isLeftChild = false;

        while (current != null) {
            parent = current;
            if (keyToInsert < current.key) {
                current = current.leftChild;
                isLeftChild = true;
            } else {
                current = current.rightChild;
                isLeftChild = false;
            }
        }

        if (isLeftChild) {
            parent.leftChild = keyNode;
        } else {
            parent.rightChild = keyNode;
        }
    }
    public Node find (char key)
    {
        Node current =root;
        while (current.key != key)
        {
            if (key < current.key)
                current = current.leftChild;
            else
                current = current.rightChild;
            if (current == null)
                return null;
        }
        return current;
    }
    public boolean delete(char key) {
        Node parent = root;
        Node current = root;
        boolean isLeftChild = false;

        while (current != null && current.key != key) {
            parent = current;
            if (key < current.key) {
                isLeftChild = true;
                current = current.leftChild;
            } else {
                isLeftChild = false;
                current = current.rightChild;
            }
        }

        if (current == null) {
            return false; // Node not found
        }

        //  Node à supprimer n'a pas d'enfant
        if (current.leftChild == null && current.rightChild == null) {
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }
        }
        //  Node à supprimer a un enfant
        else if (current.leftChild == null) { // juste un enfant à patir de la droite
            if (current == root) {
                root = current.rightChild;
            } else if (isLeftChild) {
                parent.leftChild = current.rightChild;
            } else {
                parent.rightChild = current.rightChild;
            }
        } else if (current.rightChild == null) { // juste un enfant à patir de la gauche
            if (current == root) {
                root = current.leftChild;
            } else if (isLeftChild) {
                parent.leftChild = current.leftChild;
            } else {
                parent.rightChild = current.leftChild;
            }
        }
        //  Node à supprimer a 2 enfants
        else {
            Node successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.leftChild = successor;
            } else {
                parent.rightChild = successor;
            }
            successor.leftChild = current.leftChild;
        }

        count--;
        return true;
    }

    private Node getSuccessor(Node deleteNode) {
        Node successorParent = deleteNode;
        Node successor = deleteNode;
        Node current = deleteNode.rightChild;

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }

        if (successor != deleteNode.rightChild) {
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = deleteNode.rightChild;
        }

        return successor;
    }

    public boolean isEmpty(BinaryTree tree) {
        return tree.root == null;
    }

    // Méthode pour obtenir le nœud
    public char top (BinaryTree tree) {
        if (isEmpty(tree)) {
            return '0';
        } else {
            return tree.root.key;
        }
    }



}
