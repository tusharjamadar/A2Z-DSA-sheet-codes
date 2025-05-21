package StackAndQueue.Learning;

import java.util.*;

public class StackUsingQueue {
    Queue<Integer> queue = new LinkedList<>();

    void push(int x){
        queue.offer(x);
        int size = queue.size();

        for(int i=0; i<size; i++){
            queue.offer(queue.peek());
            queue.poll();
        }
    }

    int pop(){
        return queue.poll();
    }

    int top(){
        return queue.peek();
    }

    public static void main(String[] args) 
    {
        StackUsingQueue s = new StackUsingQueue();
        s.push(10);
        s.push(20);
        System.out.println(s.top());
        s.pop();
        s.push(30);
        s.pop();
        System.out.println(s.top());
    }
}
