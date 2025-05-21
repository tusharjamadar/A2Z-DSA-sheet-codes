package StackAndQueue.Learning;

public class StackArrayImp {
    private int capacity = 10;
    private int top  = -1;

    private int[] stack = new int[capacity];

    void push(int ele){
        if(top+1 >= capacity){
            System.out.println("Stack is full!! Can't push elements.");
            return;
        }
        top+=1;
        stack[top] = ele;
    }

    int pop(){
        if(top == -1){
            System.out.println("Stack is empty!!");
            return -1;
        }
        int el = stack[top];
        top -= 1;
        return el;
    }

    int size(){
        return top+1;
    }
    int top(){
        if(top == -1){
            System.out.println("Stack is empty!!");
            return -1;
        }
        return stack[top];
    }

    public static void main(String[] args) {
        StackArrayImp st = new StackArrayImp();
        st.push(1);
        st.push(2);
        st.push(3);
        System.out.println(st.pop());
        System.out.println(st.top());
        System.out.println(st.size());

        System.out.println(st.pop());
        System.out.println(st.pop());
        System.out.println(st.top());
        System.out.println(st.size());
    }
}
