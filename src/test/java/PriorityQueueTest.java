import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PriorityQueueTest{

    static Stream<Arguments> StringIntAndListProvider() {
        return Stream.of(
                //  test,correctAnswer
                Arguments.of(new int[]{3,1,2}, new int[]{1,2,3}),
                Arguments.of(new int[]{-3,-1,-2,5}, new int[]{-3,-2,-1,5}),
                Arguments.of(new int[]{3,-2,-5,-1,2}, new int[]{-5,-2,-1,2,3}),
                Arguments.of(new int[]{-3,1,11,0,9,3}, new int[]{-3,0,1,3,9,11}),
                Arguments.of(new int[]{3,7,2,-1,-2}, new int[]{-2,-1,2,3,7}),
                Arguments.of(new int[]{}, new int[]{})
        );
    }

    @ParameterizedTest
    @MethodSource("StringIntAndListProvider")
    void PriorityQueue_RUnTest(int[] random_array, int[] correct_array) {
        List<Integer> random_list = new ArrayList<Integer>(random_array.length);
        for(int i =0;i<random_array.length;i++){
            random_list.add(random_array[i]);
        }
        Collections.sort(random_list);

        List<Integer> correct_list = new ArrayList<Integer>(correct_array.length);
        for(int i =0;i<correct_array.length;i++){
            correct_list.add(correct_array[i]);
        }

        boolean comparedResult = true;
        for(int i = 0 ; i<random_array.length;i++){
            if( correct_list.remove(0)!= random_list.remove(0)){
                comparedResult = false;
            }
        }
        assertTrue(comparedResult);
    }



    @Test
    public void whenExceptionThrown_InitialCapacityIsNullTest(){
        assertThrows(NullPointerException.class, () -> {
            List<Integer> toTest = new ArrayList<Integer>(null);
        });
    }

    @Test
    public void whenExceptionThrown_IllegalInitialCapacityTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            List<Integer> toTest = new ArrayList<Integer>(-5);
        });
    }

    @Test
    public void whenExceptionThrown_RemoveTest(){
        assertThrows(IndexOutOfBoundsException.class, () -> {
            new ArrayList<Integer>().remove(0);
        });
    }
}