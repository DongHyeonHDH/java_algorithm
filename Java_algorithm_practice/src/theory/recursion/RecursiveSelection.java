package theory.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecursiveSelection {
    // 서로 다른 원소를 사용한다. values와 R을 바꾸어 연습할 수 있다.
    static int[] values = {1, 2, 3};
    static final int R = 2;
    static int[] picked = new int[R];
    static boolean[] visited = new boolean[values.length];
    static List<Integer> subset = new ArrayList<>();

    public static void main(String[] args) {
        if (R < 0 || R > values.length) {
            throw new IllegalArgumentException("R은 0 이상 원소 개수 이하여야 합니다.");
        }

        System.out.println("순열 (순서 구분, 중복 선택 없음)");
        permutation(0);

        System.out.println("조합 (순서 무관, 중복 선택 없음)");
        combination(0, 0);

        System.out.println("부분집합 (공집합 포함)");
        powerSet(0);
    }

    // depth: 지금까지 뽑은 개수. 매번 모든 원소 중 아직 쓰지 않은 것을 고른다.
    static void permutation(int depth) {
        if (depth == R) {
            System.out.println(Arrays.toString(picked));
            return;
        }

        for (int i = 0; i < values.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            picked[depth] = values[i];
            permutation(depth + 1);
            visited[i] = false; // 선택을 복구하여 다음 경우를 탐색한다.
        }
    }

    // start: 다음으로 고를 수 있는 시작 인덱스. 앞에서 고른 원소는 다시 보지 않는다.
    static void combination(int depth, int start) {
        if (depth == R) {
            System.out.println(Arrays.toString(picked));
            return;
        }

        for (int i = start; i < values.length; i++) {
            picked[depth] = values[i];
            combination(depth + 1, i + 1);
        }
    }

    // index: 포함 여부를 결정할 원소의 인덱스.
    static void powerSet(int index) {
        if (index == values.length) {
            System.out.println(subset);
            return;
        }

        subset.add(values[index]);
        powerSet(index + 1); // 현재 원소를 포함하는 경우

        subset.remove(subset.size() - 1); // 선택 복구
        powerSet(index + 1); // 현재 원소를 포함하지 않는 경우
    }
}
