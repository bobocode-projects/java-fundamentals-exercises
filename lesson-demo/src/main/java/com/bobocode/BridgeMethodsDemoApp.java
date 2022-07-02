package com.bobocode;

public class BridgeMethodsDemoApp {
    public static void main(String[] args) {
        var stringContainer = new StringContainer("We're almost done!");
        for (var declaredMethod : stringContainer.getClass().getDeclaredMethods()) {
            System.out.println(declaredMethod);
        }
    }

    static class Container<T> {
        private T element;

        public Container(T element) {
            this.element = element;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }
    }


    static class StringContainer extends Container<String> {
        public StringContainer(String element) {
            super(element);
        }

        // this method is not compatible with its parent method after the compilation
        // so the compiler will generate additional bridge method (see byte code)
        public String getElement() {
            return super.getElement();
        }

        // this method is not compatible with its parent method after the compilation
        // so the compiler will generate additional bridge method (see byte code)
        public void setElement(String element) {
            super.setElement(element);
        }
    }


}
