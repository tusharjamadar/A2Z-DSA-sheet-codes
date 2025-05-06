package LinkedList.DLL;

class Node
{
    int data;
    Node next;
    Node prev;
    Node(int data)
    {
        this.data = data;
        next = prev = null;
    }
}

public class ConvertArrayToDLL {

    public static Node deleteHead(Node head) {
        // your code here
        if(head == null)return null;
        if(head.next == null)return null;
        
        Node temp = head;
        head = head.next;
        head.prev = null;
        temp.next = null;
        
        return head;
    }

    public static Node deleteTail(Node head) {
        // your code here
        if(head == null || head.next == null)return null;
        
        Node curr = head;

        while (curr.next.next != null) {
            curr = curr.next;
        }

        Node temp = curr.next;
        temp.prev = null;
        curr.next = null;
        
        return head;
    }

    public static Node deleteNode(Node head, int x) {
        // code here
        Node temp = head;
        int cnt = 0;
        
        while(temp != null){
            cnt++;
            if(cnt == x)break;
            temp = temp.next;
        }
        
        Node prev = temp.prev;
        Node front = temp.next;
        
        
        if(prev == null && front == null){
            // means DLL has only 1 ele
            return null;
        }else if(prev == null){
            // means we have to delete head
            return deleteHead(head);
        }else if(front == null){
            // means we have to delete tail
            return deleteTail(head);
        }
        
        prev.next = front;
        front.prev = prev;
        temp.next = null;
        temp.prev = null;
        
        return head;
        
    }
    
    public static Node convertArrayToDLL(int[] arr){
        Node head = new Node(arr[0]);
        Node prev = head;

        for(int i=1; i<arr.length; i++){
            Node temp = new Node(arr[i]);
            temp.prev = prev;
            prev.next = temp;    
            prev = temp;
        }
        return head;
    }

    public static void DLLTraversal(Node head){
        System.out.println("DLL in normal order: ");

        while(head.next != null){
            System.out.print(head.data + " ");
            head = head.next;
        }
        System.out.print(head.data + " ");

        System.out.println("\nDLL in reverse order: ");

        while(head != null){
            System.out.print(head.data +" ");
            head = head.prev;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};

        Node head = convertArrayToDLL(arr);

        // head = deleteHead(head);
        // head = deleteTail(head);
        head = deleteNode(head, 5);

        DLLTraversal(head);


    }
}
