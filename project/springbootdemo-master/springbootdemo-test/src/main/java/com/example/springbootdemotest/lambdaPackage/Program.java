package com.example.springbootdemotest.lambdaPackage;



public class Program {
    public static void main(String[] args) {
        Comparator comparator = new MyComparator();
        Comparator comparator1 =new Comparator() {
            @Override
            public int compare(int a, int b) {
                return a-b;
            }
        };
        Comparator comparator2 = (a,b)->a-b;
    }

     static class MyComparator implements Comparator {

        @Override
        public int compare(int a, int b) {
            return a - b;
        }
    }

    @FunctionalInterface
    public interface Comparator {
        int compare(int a, int b);
    }
}
