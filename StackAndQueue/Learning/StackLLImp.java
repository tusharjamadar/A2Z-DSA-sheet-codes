package StackAndQueue.Learning;


public class StackLLImp {
    public class Node {
        int data;
        Node next;

        Node(int data){
            this.data = data;
            next = null;
        }
    }

    Node top = null;
    int size = 0;

    void push(int ele){
        Node temp = new Node(ele);
        temp.next = top;
        top = temp;
        size++;
    }

    int pop(){
        if(top == null){
            System.out.println("Stack is empty!!");
            return -1;
        }
        Node temp = top;
        top = top.next;
        size--;
        return temp.data;
    }

    int size(){
        return size;
    }

    int top(){
        if(top == null){
            System.out.println("Stack is empty!!");
            return -1;
        }
        return top.data;
    }

    public static void main(String[] args) {
        StackLLImp st = new StackLLImp();

        st.push(19);
        st.push(22);
        st.push(323);
        st.push(42);
        System.out.println(st.size());
        System.out.println(st.top());
        st.pop();
        st.pop();
        st.pop();
        System.out.println(st.top());
    }

}
