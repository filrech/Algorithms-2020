package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     *
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     *
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     *
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) {
        throw new NotImplementedError();
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     *
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     *
     * 1 2 3
     * 8   4
     * 7 6 5
     *
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     *
     * 1 2 3
     * 8   4
     * 7 6 х
     *
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     *
     * 1 х 3
     * 8   4
     * 7 6 Х
     *
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     *
     * 1 Х 3
     * х   4
     * 7 6 Х
     *
     * 1 Х 3
     * Х   4
     * х 6 Х
     *
     * х Х 3
     * Х   4
     * Х 6 Х
     *
     * Х Х 3
     * Х   х
     * Х 6 Х
     *
     * Х Х 3
     * Х   Х
     * Х х Х
     *
     * Общий комментарий: решение из Википедии для этой задачи принимается,
     * но приветствуется попытка решить её самостоятельно.
     */
    static public int josephTask(int menNumber, int choiceInterval) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     *
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */
    // Решение "В лоб"
    // Трудоемкость O(n ^ 3) худший случай, n - длинна строки
    // Ресурсоемкость O(n).
    static public String longestCommonSubstring(String first, String second) {
        first = first.replaceAll("\\n", " ");
        second = second.replaceAll("\\n", " ");

        String longestSubstring = "";

        for (int i = 0; i < first.length(); i++) { // O(n)
            for (int j = 0; j < second.length(); j++) { // O(n)
                if (first.charAt(i) == second.charAt(j)) {
                    int count = 1;
                    while ((i + count) < first.length() && (j + count) < second.length() &&
                            first.charAt(i + count) == second.charAt(j + count)) { // O(n) в худшем
                        count++;
                    }
                    String substring = first.substring(i, i + count);
                    if (longestSubstring.length() < substring.length()) {
                        longestSubstring = substring;
                    }
                }
            }
        }

        return longestSubstring;
    }

    /**
     * Число простых чисел в интервале
     * Простая
     *
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     *
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    // Упрощенное Решето Аткина (Википедийное решение. Я бы просто перебором всех делителей решал до гуглинга :0 )
    // Трудоемкость O(n) , n - limit.
    // Ресурсоемкость O(n).
    static public int calcPrimesNumber(int limit) {
        boolean[] isPrime = new boolean[limit + 1];

        double sqrt = Math.sqrt(limit);
        int x2;
        int y2;
        int n;
        for (int x = 1; x <= sqrt; x++) { // O(sqrt(n))
            for (int y = 1; y <= sqrt; y++) { // O(sqrt(n))
                x2 = x * x;
                y2 = y * y;
                n = 4 * x2 + y2;
                if (n <= limit && (n % 12 == 1 || n % 12 == 5)) {
                    isPrime[n] = !isPrime[n];
                }
                n = 3 * x2 + y2;
                if (n <= limit && n % 12 == 7) {
                    isPrime[n] = !isPrime[n];
                }
                n = 3 * x2 - y2;
                if (x > y && n <= limit && n % 12 == 11) {
                    isPrime[n] = !isPrime[n];
                }
            }
        }

        // Грубая оценка меньше O(n)
        if (isPrime.length > 5) {
            for (int i = 5; i < sqrt; i += 2) { // O(sqrt(n))
                if (isPrime[i]) { // O(1) для не прайм чисел
                    int i2 = i * i;
                    for (int j = i2; j <= limit; j += i2) {
                        isPrime[j] = false;
                    }
                }
            }
        }

        if (isPrime.length > 2) {
            isPrime[2] = true;
        }

        if (isPrime.length > 3) {
            isPrime[3] = true;
        }

        int count = 0;
        for (boolean bool : isPrime) { // O(n)
            if (bool) {
                count++;
            }
        }

        return count;
    }
}
