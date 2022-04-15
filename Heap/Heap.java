import java.io.File;
import java.util.Scanner;
import java.util.Arrays;

////////////////FINISHED PROJECT////////////////////////////////

public class Heap {
    // 3 ary heap is basically a binary tree
    // with a possibility of 3 children nodes for each parent
    // 'TernaryHeap'
    public static void main(String[] args) throws Exception {
        int answer = 0;
        File inputFile = new File("inputFile.txt");
        Scanner scan = new Scanner(inputFile);
        int num_Directions = scan.nextInt();
        Heap Riley = new Heap(num_Directions);
        for (int i = 1; i <= num_Directions;) {
            String function = scan.next();
            if (function.equals("IN")) {
                int element = scan.nextInt();
                Riley.insert(element);
            } else if (function.equals("EM")) {
                Riley.H_size--;
                if (i == num_Directions) {
                    answer = Riley.lastEM();
                } else {
                    Riley.extractMin();
                }
            } else if (function.equals("DK")) {
                Riley.H_size--;
                int index = scan.nextInt();
                int newElement = scan.nextInt();
                Riley.decreaseKey(index, newElement);
            }
            i++;
        }
        scan.close();
        System.out.println(answer + " // answer from last EM");
    }

    private int[] H;
    private int H_size;
    private int next;

    /////////////////////////////////
    /**
     * Constructor
     * 
     */
    public Heap(int max_size) {
        H_size = max_size;
        H = new int[H_size - 1];
        next = 0;

    }

    public void swap(int i, int j) {
        int temp = H[i];
        H[i] = H[j];
        H[j] = temp;
    }

    public void floatUp(int child_index) {
        int parent_index = (child_index - 1) / 3;
        while (parent_index >= 0) {
            if (H[child_index] < H[parent_index]) {
                swap(child_index, parent_index);
                child_index = parent_index;
                parent_index = (child_index - 1) / 3;
            } else {
                break;
            }
        }
        return;
    }

    public int minIndex2(int a, int b) {
        if (H[a] < H[b]) {
            return a;
        }
        return b;
    }

    public int minIndex3(int a, int b, int c) {
        if (H[a] < H[b]) {
            if (H[a] < H[c]) {
                return a;
            }
            return c;
        } else if (H[b] < H[c]) {
            return b;
        }
        return c;
    }

    public int minIndex4(int a, int b, int c, int d) {
        if (H[a] < H[b]) {
            if (H[a] < H[c]) {
                if (H[a] < H[d]) {
                    return a;
                }
                return d;
            } else if (H[c] < H[d]) {
                return c;
            } else {
                return d;
            }
        } else {
            if (H[b] < H[c]) {
                if (H[b] < H[d]) {
                    return b;
                }
                return d;
            } else if (H[c] < H[d]) {
                return c;
            } else {
                return d;
            }
        }
    }

    public void sinkDown(int start, int end) {
        int min = start * 3 + 1;
        int p1, p2, p3;
        if (min > end) {
            return;
        }
        if (min < end) {
            if (H[min + 1] != 0) {
                p1 = min + 1;
                if (H[p1 + 1] != 0) {
                    p2 = p1 + 1;
                    if (H[p2 + 1] != 0) {
                        p3 = p2 + 1;
                        min = minIndex4(p1, p2, p3, min);
                    } else {
                        min = minIndex3(p1, p2, min);
                    }
                } else {
                    min = minIndex2(p1, min);
                }
            }
            if (H[0] <= H[min]) {
                return;
            }
            swap(0, min);
            sinkDown(0, end);
        }
    }

    public int rootIndex(int[] A, int first, int last) {
        int min = first;
        for (int i = first; i < last; i++) {
            if (A[min] > A[i]) {
                min = i;
            }
        }
        return min;
    }

    /**
     * Function to insert into Ternary heap *
     * 
     * @param element
     */
    public void insert(int data) {
        H[next] = data;
        floatUp(next);
        next++;
        return;
    }

    public void decreaseKey(int index, int newElement) {
        H[index] = newElement;
        floatUp(index);
        return;
    }

    public void extractMin() {
        int temp = H[H_size - 1];
        int[] newH = new int[H_size - 1];
        for (int i = 1; i < newH.length; i++) {
            newH[i] = H[((i < H_size - 1) ? i : i + 1)];
        }
        H = newH;
        H[0] = temp;
        sinkDown(0, H.length);
    }

    public int lastEM() {
        int temp = H[H_size - 1];
        int[] newH = new int[H_size - 1];
        for (int i = 1; i < newH.length; i++) {
            newH[i] = H[((i < H_size - 1) ? i : i + 1)];
        }
        H = newH;
        H[0] = temp;
        sinkDown(0, H.length);
        return H[0];
    }
    /////////////////////////////////
}
