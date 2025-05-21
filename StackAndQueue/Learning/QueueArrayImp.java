package StackAndQueue.Learning;

public class QueueArrayImp {
    private int capacity = 3;
    private int size = 0;
    private int start = -1, end = -1;

    private int[] queue = new int[capacity];

    void push(int ele){
        if(size == capacity){
            System.out.println("Queue is full!");
            return;
        }else if(start == -1 && end == -1){
            start = 0;
            end = 0;
        }else{
            end = (end+1) % capacity;
        }
        size++;
        queue[end] = ele;
    }

    int pop(){
        if((start == -1 && end == -1) || (size == 0)){
            System.out.println("Queue is Empty!!");
            return -1;
        }
        int ele = queue[start];
        
        if (size == 1) {
            start = -1;
            end = -1;
        } else
            start = (start + 1) % capacity;

        size--;
        return ele;
    }
    int top(){
        if((start == -1 && end == -1) || (size == 0)){
            System.out.println("Queue is Empty!!");
            return -1;
        }
        return queue[start];
    }
    int size(){
        return size;
    }


    public static void main(String[] args) {
        QueueArrayImp q = new QueueArrayImp();
        q.push(1);
        q.push(2);
        q.push(3);
        q.push(4);
        System.out.println(q.pop());
        q.push(4);
        System.out.println(q.top());
    }
}
