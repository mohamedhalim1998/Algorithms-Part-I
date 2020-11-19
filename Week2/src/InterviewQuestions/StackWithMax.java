package InterviewQuestions;

import edu.princeton.cs.algs4.Stack;

import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class StackWithMax extends Stack<Integer> {
    PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

    public int max() {
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return -priorityQueue.peek();
    }

    @Override
    public void push(Integer integer) {
        super.push(integer);
        priorityQueue.add(-integer);
    }

    @Override
    public Integer pop() {
        int i = super.pop();
        if (priorityQueue.peek() == -i) {
            priorityQueue.remove();
        }
        return i;
    }
}
