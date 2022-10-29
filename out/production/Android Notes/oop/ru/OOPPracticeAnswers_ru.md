#### 1) Задача переопределить equals для дочернего класса
*Ошибка N1*
 ```
@Override
public boolean equals(Object o) {
    if (!(o instanceof ColorPoint))
        return false;
    return super.equals(0) && ((ColorPoint) o).color == color;
} 
   ```

Если начать проверять на ColorPoint, то мы нарушим правило симметричности
**если x.equals(y), то y.equals(x)**.

*Ошибка N2*

 ```
@Override
public boolean equals(Object o) {
    if (!(o instanceof Point))
        return false;
        
    if (!(o instanceof ColorPoint))
        return super.equals(o);
    return super.equals(o) && ((ColorPoint) o).color == color;
} 
   ```

Здесь мы нарушаем правило транзитивности
ColorPoint p1 == new ColorPoint(1, 2, Color.RED)
Point p2 == new Point(1, 2)
ColorPoint p3 == new ColorPoint(1, 2, Color.BLUE)
