# SB

## BenchmarkLists.java
Бенчмарки производительности вставки в середину для ArrayList и LinkedList с использованием JMH.
```
Benchmark                    (N)  Mode  Cnt   Score    Error  Units
BenchmarkLists.arrayList   10000  avgt    5   1,742 ±  0,189  ms/op
BenchmarkLists.linkedList  10000  avgt    5  54,265 ± 11,193  ms/op
```
Получилось, что разница в ~32 раза на 10000 элементах в пользу ArrayList. С ростом N разрыв будет увеличиваться.

## SingletoneDoubleCheckedLocking.java
Реализация паттерна синглтон с блокировкой с двойной проверкой.

## ThreadsSameTimeStart.java
Одновременный запуск потоков с помощью CyclicBarrier.

