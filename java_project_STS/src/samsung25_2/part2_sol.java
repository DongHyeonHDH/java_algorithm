package samsung25_2;
/*
 * 삼성 2025 하반기 오후 2번 문제
 */

import java.io.*;
import java.util.*;

public class part2_sol {

    static long N;
    static int nextId;

    static TreeSet<Long> positions = new TreeSet<>();
    static TreeSet<Gap> gaps = new TreeSet<>((a, b) -> {
        int cmp = Long.compare(b.length(), a.length());

        if (cmp != 0) return cmp;

        return Long.compare(a.left, b.left);
    });

    static HashMap<Integer, Long> idToPos = new HashMap<>();

    static class Gap {
        long left, right;

        Gap(long left, long right) {
            this.left = left;
            this.right = right;
        }

        long length() {
            return right - left;
        }
    }

    static void addLight() {
        Gap gap = gaps.pollFirst();

        long left = gap.left;
        long right = gap.right;

        // 가운데 위치 올림
        long mid = left + (right - left + 1) / 2;

        positions.add(mid);
        idToPos.put(++nextId, mid);

        gaps.add(new Gap(left, mid));
        gaps.add(new Gap(mid, right));
    }

    static void deleteLight(int id) {
        if (positions.size() <= 2) return;

        Long pos = idToPos.remove(id);
        if (pos == null) return;

        Long left = positions.lower(pos);
        Long right = positions.higher(pos);

        if (left != null) {
            gaps.remove(new Gap(left, pos));
        }

        if (right != null) {
            gaps.remove(new Gap(pos, right));
        }

        if (left != null && right != null) {
            gaps.add(new Gap(left, right));
        }

        positions.remove(pos);
    }

    static long calcLight() {
        long leftDist = 2 * (positions.first() - 1);
        long rightDist = 2 * (N - positions.last());
        long maxGap = gaps.first().length();

        return Math.max(maxGap, Math.max(leftDist, rightDist));
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(
            new InputStreamReader(System.in)
        );

        int Q = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());

        int order = Integer.parseInt(st.nextToken());
        N = Long.parseLong(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        nextId = M;

        for (int i = 1; i <= M; i++) {
            long pos = Long.parseLong(st.nextToken());
            positions.add(pos);
            idToPos.put(i, pos);
        }

        Long prev = null;

        for (long pos : positions) {
            if (prev != null) {
                gaps.add(new Gap(prev, pos));
            }
            prev = pos;
        }

        for (int i = 1; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            order = Integer.parseInt(st.nextToken());

            if (order == 200) {
                addLight();
            }
            else if (order == 300) {
                int D = Integer.parseInt(st.nextToken());
                deleteLight(D);
            }
            else if (order == 400) {
                sb.append(calcLight()).append('\n');
            }
        }

        System.out.print(sb);
    }
}
