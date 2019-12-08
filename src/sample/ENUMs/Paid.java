package sample.ENUMs;

public enum Paid {
    True {
        @Override
        public String toString() {
            return "Оплачено";
        }
        public boolean bool(){
            return true;
        }
    },

    False{
        @Override
        public String toString(){
            return "Не оплачено";
        }
        public boolean bool(){
            return false;
        }
    }


}

