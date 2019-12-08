package sample.ENUMs;

public enum Paid {
    True {
        @Override
        public String toString() {
            return "Оплачено";
        }
    },

    False{
        @Override
        public String toString(){
            return "Не оплачено";
        }
    }
}

