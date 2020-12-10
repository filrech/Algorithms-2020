@file:Suppress("UNUSED_PARAMETER")

package lesson7

import lesson7.rod.main

/**
 * Наибольшая общая подпоследовательность.
 * Средняя
 *
 * Дано две строки, например "nematode knowledge" и "empty bottle".
 * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
 * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
 * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
 * Если общей подпоследовательности нет, вернуть пустую строку.
 * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
 * При сравнении подстрок, регистр символов *имеет* значение.
 */
// Алгоритм Нидлмана—Вунша
// Трудоемкость O(n * m) , n - длинна первой строки, m - длинна второй строки
// Ресурсоемкость O(n * m)
fun longestCommonSubSequence(first: String, second: String): String {
    if (first.isNullOrEmpty() || second.isNullOrEmpty()) return ""
    if (first == second) return first

    var lengthFirst = first.length
    var lengthSecond = second.length

    val mainMatrix = Array(lengthFirst + 1) { IntArray(lengthSecond + 1) { 0 } }

    for (i in 1..lengthFirst) {
        for (j in 1..lengthSecond) {
            if (first[i - 1] == second[j - 1]) {
                mainMatrix[i][j] = mainMatrix[i - 1][j - 1] + 1
            } else {
                mainMatrix[i][j] = maxOf(mainMatrix[i - 1][j], mainMatrix[i][j - 1])
            }
        }
    }

    var result = ""

    while (lengthFirst > 0 && lengthSecond > 0) {
        when {
            first[lengthFirst - 1] == second[lengthSecond - 1] -> {
                result = first[lengthFirst - 1] + result
                lengthFirst--
                lengthSecond--
            }
            mainMatrix[lengthFirst - 1][lengthSecond] > mainMatrix[lengthFirst][lengthSecond - 1] -> {
                lengthFirst--
            }
            else -> {
                lengthSecond--
            }
        }
    }

    return result
}

/**
 * Наибольшая возрастающая подпоследовательность
 * Сложная
 *
 * Дан список целых чисел, например, [2 8 5 9 12 6].
 * Найти в нём самую длинную возрастающую подпоследовательность.
 * Элементы подпоследовательности не обязаны идти подряд,
 * но должны быть расположены в исходном списке в том же порядке.
 * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
 * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
 * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
 */
// Трудоемкость O(n ^ 2)
// Ресурсоемкость O(n)

fun longestIncreasingSubSequence(list: List<Int>): List<Int> {
    if (list.isNullOrEmpty()) return emptyList()
    if (list.size == 1) return list

    var mainList = MutableList(list.size) { 1 }
    var prevList = MutableList(list.size) { -1 }

    for (i in list.indices) {
        for (j in 0 until i) {
            if (list[j] < list[i] && mainList[j] + 1 > mainList[i]) {
                mainList[i] = mainList[j] + 1
                prevList[i] = j
            }
        }
    }

    var pos = 0
    var length = mainList[0]
    for (i in list.indices) {
        if (mainList[i] > length) {
            pos = i
            length = mainList[i]
        }
    }

    val result = mutableListOf<Int>()

    while (pos != -1) {
        result.add(list[pos])
        pos = prevList[pos]
    }

    return result.asReversed()
}

/**
 * Самый короткий маршрут на прямоугольном поле.
 * Средняя
 *
 * В файле с именем inputName задано прямоугольное поле:
 *
 * 0 2 3 2 4 1
 * 1 5 3 4 6 2
 * 2 6 2 5 1 3
 * 1 4 3 2 6 2
 * 4 2 3 1 5 0
 *
 * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
 * В каждой клетке записано некоторое натуральное число или нуль.
 * Необходимо попасть из верхней левой клетки в правую нижнюю.
 * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
 * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
 *
 * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
 */
fun shortestPathOnField(inputName: String): Int {
    TODO()
}

// Задачу "Максимальное независимое множество вершин в графе без циклов"
// смотрите в уроке 5