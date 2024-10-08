public class Stack {

    private int top;
    private int[] data;

    public Stack(int size){
        data = new int[size];
        top = -1;
    }

    public void push(int c)  {
        if(isFull()){
            System.out.println("la pile est pleine");
        }
        data[++top] = c;

    }

    public int pop()  {
        if(isEmpty()){
            System.out.println("La pile est vide");
            return -1;
        }
         return data[top--];
    }

    public int peek() {
        if(isEmpty()){
            System.out.println("La pile est vide");
            return -1;
        }
         return data[top];
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public  boolean isFull(){
        return top == data.length -1;
    }

    public String toString(){
        String pile = "{";
        for( int i = 0; i<= top; i++){
            pile += data[i]+" ";
        }

        return  pile + "}";
    }

}
