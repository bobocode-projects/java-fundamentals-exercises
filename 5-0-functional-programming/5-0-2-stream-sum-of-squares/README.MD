 public static int sumOfSquares(int start, int end) {
        return IntStream.rangeClosed(start, end)
                        .map(x -> x * x)
                        .sum();
    }
    public static void main(String[] args) {
        int start = 1;
        int end = 5;
        int result = sumOfSquares(start, end);
        System.out.println(result);
    }