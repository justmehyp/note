package IntConsumerAndConsumer;

import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class IntConsumerAndConsumerTest {

    public static void main(String[] args) {
        Consumer<Integer> consumerInteger = i -> System.out.println("Integer: " + i);
        IntConsumer intConsumer = i -> System.out.println("int: " + i);

//        test(consumerInteger);
//        test(consumerInteger::accept);
//        test(e -> consumerInteger.accept(e));
        test(intConsumer);
        test(intConsumer::accept);
        test(e -> intConsumer.accept(e));
    }

//    public static void test(Consumer<Integer> consumer) {
//        if(consumer instanceof IntConsumer) {
//            test((IntConsumer) consumer);
//        }
//        else {
//            test((IntConsumer) consumer::accept);
//        }
//    }


    public static void test(IntConsumer consumer) {
        consumer.accept(200);
    }
}