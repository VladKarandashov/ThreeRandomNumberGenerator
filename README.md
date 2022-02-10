# ThreeRandomNumberGenerator

В файле src\main\resources\config.properties можно отредактировать следующие параметры:
* delayTime - время между генерацией случайных чисел
  + принимает целые значения от 0 и до 2^63-1
* start и stop - границы промежутков для чисел (границы включаются в промежуток)
  + принимают значения от -2^63 до 2^63-1 для целых
  + принимают значения от -1.7*10^308 до 1.7*10^308 для дробных
* decimalPlaces - количество знаков после запятой для третьего числа
  + принимает целые значения от 0 и до 2^63-1

Для простоты отладки файл конфигурации лежит в ресурсах и программа может использоваться без аргументов.
В случае сборки Jar необходимо при запуске программы передать путь к конфигурации:
```
java -jar ThreeRandomNumberGenerator.jar config.properties
```

Для того чтобы автоматически посылать POST запросы на сервер можно писать:
```
java -jar ThreeRandomNumberGenerator.jar config.properties | xargs -n 3 ./script.sh
```
Где script.sh будет:
```
#!/bin/bash
curl -X POST http://localhost:8080/putNote -H "Content-Type: application/json" -d '{"member" : '$1', "group" : '$2', "item" : '$3'}'
echo send  $1 $2 $3
```