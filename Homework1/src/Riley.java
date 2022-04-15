
import java.util.Arrays;
import java.util.FormattableFlags;
import java.util.Random;
import java.util.TreeSet;

public class Riley {
    public static void main(String[] args) throws Exception {
        int array_length = Integer.parseInt("8");
        TreeSet<Integer> array_elements = new TreeSet<Integer>();
        Random random = new Random();
        for (int i = 0; i < array_length; i++) {
            array_elements.add(random.nextInt(100) + 1);
        }
        Object[] array = array_elements.toArray();
        int[] A = new int[array_length];
        int i = random.nextInt(5) + 1;
        for (int j = 0; j < i; j++) {
            A[j] = (Integer) array[j];
        }
        int end = array.length - 1;
        for (int j = i; j < A.length; j++) {
            A[j] = (Integer) array[end];
            end--;
        }
        System.out.println("1.)");
        System.out.println("Integer array A[] ⟹ " + Arrays.toString(A) + "\n");
        Riley find = new Riley();
        int answer = find.maxEntry(A);
        System.out.println("maximum entry at A[i] ⟹ (" + i + ", " + answer + ")\n");
        find.explainMaxEntry();
        System.out.println("\n2.)\nFind complexity for: \n");
        find.printAlg1();
        System.out.println("\nGiven array : " + Arrays.toString(A));
        System.out.print("(a) output: ");
        find.algorithm1(A);
        System.out.println("\n");
        find.explainAlg1();
        System.out.println("\nFind complexity for: \n");
        find.printAlg2();
        System.out.println("\nGiven array : " + Arrays.toString(A));
        System.out.print("(b) output: ");
        find.algorithm2(A);
        System.out.println();
        find.explainAlg2();
        System.out.println("\n3.)");
        int[] contig = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int Y = 5;
        boolean yes_no = find.fasterMethod(contig, Y);
        System.out.println(yes_no);
        find.explain3();

    }

    // (1.
    /*
     * An array A[1..n] has the following property:
     * → Increases up to a certain point, then decreases for the remaining points
     * We Know that for some number i... :
     * 
     * {i : A[j] < A[j + 1], ∀(j < i)} & {i : A[j] > A[j + 1], ∀(j ≥ i)}
     * 
     * ∴ A[i] is the maximum entry, this is what we want to find.
     * 
     * → The given algorithm takes Ο(log n) time
     */
    public int maxEntry(int[] A) {

        int min = 0;
        int max = A.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (A[mid] >= A[mid - 1] && A[mid] >= A[mid + 1]) {
                return A[mid];
            } else if (A[mid] <= A[mid + 1]) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }
        }
        return -1;
    }

    public void explainMaxEntry() {
        System.out.println("Conventional binary search used which is considered ϵ O(logn)");
        System.out.println(" The algorithm is performed by locating the first and last index in memory");
        System.out.println(" and then partitioning starting from the middle of the two,");
        System.out.println("then testing whether the A[middle] satisfies properties for A[i] (max)");
        System.out.println("the answer, according to this test will return the element which we are pointing to");
        System.out.println("if it is A[i]");
        System.out.println("and discards the HALF of the array which we know A[i] must not be in.");
        System.out.println("This grants us complexity measured by ∑ 1/2ⁿ → BinarySearch ϵ O(logn) ");
    }
    // 1.)

    /////////////////////////////

    // (2.
    public void printAlg1() {
        String tab = "    ";
        System.out.print("(a) k = 1\n" + tab + "for(i = 0; i < n; i++){\n");
        System.out.print(tab + tab + "for(j = 0; j < n; j = j + k){\n");
        System.out.print(tab + tab + tab + "print(A[j]));\n");
        System.out.print(tab + tab + "}\n" + tab + tab + "k = k * 2;\n");
        System.out.print(tab + "}\n");
    }

    public void printAlg2() {
        String tab = "    ";
        System.out.print("(b) for(i = 1; i ≤ n; i++){\n");
        System.out.print(tab + tab + "for(j = 0; j < n; j = j + i){\n");
        System.out.print(tab + tab + tab + "print(A[j]);\n" + tab + tab + "}\n");
        System.out.println(tab + "}");

    }
    /*
     * The following algorithms use print operation that executes in O(1)
     * Try to come up with θ runtime analysis for the following two
     * algorithms:
     * 
     */

    public void algorithm1(int[] A) {
        int k = 1;
        int n = A.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j = j + k) {
                System.out.print(A[j] + " ");
            }
            k = k * 2;
        }
    }

    public void algorithm2(int[] A) {
        int n = A.length;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < n; j = j + i) {
                System.out.print(A[j] + " ");
            }
        }
    }

    public void explainAlg1() {
        System.out.println("The algorithm iterates through the array the first time, in n steps");
        System.out.println("The next iteration takes n/2 steps");
        System.out.println("And the following iteration takes n/4");
        System.out.println("Therefore our algorithm has the sequence : ∑ | 1 → n ( 1/(2ⁿ) )");
        System.out.println(" = 1, ½, ¼, ⅛...");
        System.out.println("-That is up until our i partition is equal to log(n)");
        System.out.println(
                "we see from this point on : i > log(n) ⟹ the first element is printed 6 times ( ∑ n/2) times");
        System.out.println("So for the outer loop to run n times");
        System.out.println(" and the inner loop running n / k times ");
        System.out.println("that gives us n * n/k steps");
        System.out.println("to give a θ time complexity representation, which means:");
        System.out.println("the time for this algorithm to execute averages out over arbitrary sizes for n");
        System.out.println("∴ θ(logn) is the complexity that represents this algorithm");
    }

    public void explainAlg2() {
        System.out.println("\n for this algorithm the outer loop is performed n times");
        System.out.println(" the inner loop runs logn times ∵ j updates to j + i every time");
        System.out.println("It is not reasonable to represent this complexity with θ");
        System.out.println("∵ for large amounts of n, the inner loop runs significantly longer");
        System.out.println("than for small inputs of n");
        System.out.println("\n we have the inner loop in steps : 1,2,3,...,n");
        System.out.println("and the innerloop: n/2, n/4, n/8");
        System.out.println("This gives us n * log(n) time ⟹ O(nlogn)");
    }
    // 2.)

    /////////////////////////////

    // (3.
    /*
     * My friend gives me an array B[1..n] that has only positive ints.
     * Okay cool, he wants to know if there is going to be a contiguous portion in
     * the array ( B[i..j] ) such that the sum of its data values is equal to say..
     * 'Y'
     */

    /*
     * Dr. Shah's suggestion: For each pair (i, j), compute the sum of all possible
     * entries.
     * ~Consecutively I'm assuming. That is in B[i..j]. And check whether or not any
     * of the possibilities are equal to Y.
     * #But this algorithm would be really innefficient θ(n²) sums to test
     * Each sum involves O(n) addition operations. Accurate analysis would be →
     * θ(n³) running time total!
     */

    public boolean drShahsAlg(int[] B, int Y) {
        for (int i = 0; i < B.length; i++) {
            int sigma = B[i];
            for (int j = 1 + i; j < B.length; j++) {
                sigma += B[j];
                if (sigma == Y) {
                    return true;
                }
            }
        }
        return false;
    }
    /*
     * Here is an algorithm to accomplish this task in O(n)-time
     * That way I can help my friend find her portion of B[i..j] in more efficient
     * time.
     */

    /**
     * We have : O(B.length) * kO(i - 1) → where the length of B[1..n] is iterated n
     * times. becuse the
     * number of times k where we will have to remove the first element from our
     * continuous sum search,
     * each k iteration will take i - 1 steps for the current iteration of
     * B[1..i..n].
     * So in total since the significance of k is less than B.length, we have O(n)
     * time for fasterMethod(B[1..n], Y)
     * 
     * @param B
     * @param Y
     * @return
     */

    public boolean fasterMethod(int[] B, int Y) {
        System.out.println("fasterMethod Called:  (" + Arrays.toString(B) + ", " + Y + ")");
        for (int i = 1; i < B.length; i++) { // O(B.length)
            B[i] += B[i - 1]; // O(1)
            System.out.println("B[i] += B[i - 1]; ⟹ " + B[i] + " where i = " + i);
            if (B[i] % Y == 0 || (i >= 2 && B[i] - B[i - 2] == 0)) { // O(1)
                return true; // O(1)
            }
            if (B[i] > Y) { // O(1) // our current contiguous sum has broken Y
                for (int j = 0; j < i - 1; j++) { // O(i - 1)
                    System.out.println("B[i] - B[j] → " + B[i] + " - " + B[j]);
                    if ((B[i] - B[j]) % Y == 0) { // O(1)
                        return true; // O(1)
                    }
                }
            }
        }
        return false; // O(1)
    }

    public void explain3() {
        System.out.println("Notes explained for the faster contiguous sum algorithm are in the fasterMethod javadoc");
    }
    // 3.)

    // (4.
    /*
     * (a) Let f(n) = 3n³ + n² & g(n) = n³ - n².
     * Show that f(n) ϵ θ(g(n)).
     * Give appropriate choices of constants c₁, c₂, and nₒ such that:
     * { c₁g(n) ≤ f(n) ≤ c₂g(n) : ∀(n ≥ nₒ) }
     * =========================================
     * We can prove this by showing f(n) ϵ O(g(n))
     * and then showing g(n) ϵ O(f(n))
     * Respectively choosing constants for f(n) and g(n)..
     */
    // answer//
    /**
     * f(n) = 3n³ + n² & g(n) = n³ - n²
     * 3n³ + n² ≤ C(n³ - n²)
     * (-3n³ + n²) / (n³ - n²) ≤ C
     * [(-24 + 4) / (8 - 4)] - 5 ≤ C
     * → C = -5 & nₒ = 2 ⟹ ∴ f(n) ϵ O(g(n))
     * --------------
     * n³ - n² ≤ C(3n³ + n²)
     * (n³ - n²) / (3n³ + n²) ≤ C
     * 0/4 = 0 ≤ C
     * → C = 4 & nₒ = 1 ⟹ ∴ g(n) ϵ O(f(n))
     * ---------------
     * ==============
     * ∵ f(n) ϵ Ω(g(n)) ⟹ ∴ f(n) ϵ θ(g(n))
     * ==============
     */

    /*
     * (b) Let f(n) = n² & g(n) = nlogn
     * Describe the relationship between f(n) and g(n) with respect to
     * each of big-O, big-Ω, big-θ, little-o, and little-ω complexity notations
     */
    // answer//
    /**
     * f(n) = n² & g(n) = nlogn
     * n² ≤ C(nlogn)
     * n / logn ≤ C
     * 2 ≤ C → C = 2 & nₒ = 2
     * 
     * n² ≥ C(nlogn)
     * n/logn ≥ C
     * → C = 2 & nₒ = 2
     * 
     * ∵ f(n) ϵ O(g(n)) & f(n) ϵ Ω(g(n)) ⟹ ∴ f(n) ϵ θ(g(n)).
     * 
     * lim n² / nlogn ⟹ lim (n / logn) = (∞ / ∞)
     * n→∞ n→∞
     * 
     * lim nln(2) = ln(2) * lim (n) = ∞
     * n→∞ n→∞
     * 
     * ==========
     * ∴ f(n) ϵ ω(g(n))
     * ==========
     * 
     */

    // 4.)

    ///////////////////////////////

    // (5.

    /*
     * Order the functions by their big-O relationships:
     *
     *
     * 6nlogn
     * log(logn)
     * log²n
     * 2ˡᴼᵍ⁽ⁿ⁾
     * n²
     * (logn)ˡᴼᵍ⁽ⁿ⁾
     * nˡᴼᵍ⁽ˡᴼᵍⁿ⁾
     * 2ⁿ
     * eⁿ
     *
     * 
     * Dr. Shah's tip : When comparing two functions f(n) & g(n), it might help if
     * you compare log(f(n)) and log(g(n)).
     *
     */
    // answer//
    /**
     * Slowest → Fastest
     * 
     * 
     * log(logn)
     * nˡᴼᵍ⁽ˡᴼᵍⁿ⁾
     * log²n
     * 2ˡᴼᵍ⁽ⁿ⁾
     * 6nlogn
     * n²
     * 2ⁿ
     * eⁿ
     * 
     * 
     */

    // 5.)

}
