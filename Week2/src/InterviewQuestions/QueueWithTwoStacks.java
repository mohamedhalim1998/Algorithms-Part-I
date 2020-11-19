package InterviewQuestions;

import edu.princeton.cs.algs4.Stack;

public class QueueWithTwoStacks<Item> {
    Stack<Item> in = new Stack<>();
    Stack<Item> out = new Stack<>();

    public void enqueue(Item item){
        in.push(item);
    }

    public Item dequeue() {
        if(out.isEmpty()){
            refill();
        }
        return out.pop();
    }

    private void refill() {
        for (Item item : in){
            out.push(item);
        }
    }


}
