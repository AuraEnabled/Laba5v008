package sample.ENUMs;

public enum Procedures {
    Кастрация {
        @Override
        public String toString() {
            return "Кастрация";
        }
    },
    Чистка {
        @Override
        public String toString() {
            return "Чистка зубов";
        }
    },
    Пломбирование {
        @Override
        public String toString() {
            return "Пломбирование";
        }
    },
    Протезирование {
        @Override
        public String toString() {
            return "Протезирование";
        }
    },
    Кремация {
        @Override
        public String toString() {
            return "Кремация тела";
        }
    }
}
