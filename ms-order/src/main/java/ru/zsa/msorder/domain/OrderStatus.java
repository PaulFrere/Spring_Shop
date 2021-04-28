package ru.zsa.msorder.domain;

public enum OrderStatus {
    CREATE {
        @Override
        public String toString() {
            return "CREATE";
        }
    }
    , PAID, DELIVERED, CANCELED;

}
