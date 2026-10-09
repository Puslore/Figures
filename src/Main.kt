fun main() {
    // интерфейсы удобно использовать в коллекциях
    val rect = Rect(2, 2, 4, 2, id = 1)
    val square = Square(-4, -4, 3, id = 2)
    val circle = Circle(5, 5, 3, id = 3)

    val figures: Array<Figure> = arrayOf(rect, square, circle)
    val movables: Array<Movable> = arrayOf(rect, square, circle)

    println("1) Исходные фигуры")
    show(figures)

    println("\n2) Перемещение move(3, -2)")
    movables.forEach { it.move(3, -2) }
    show(figures)

    println("\n3) Масштабирование resize(2)")
    figures.filterIsInstance<Transforming>().forEach { it.resize(2) }
    show(figures)

    println("\n4) Поворот по часовой стрелке вокруг точки (0, 0)")
    figures.filterIsInstance<Transforming>().forEach { it.rotate(RotateDirection.Clockwise, 0, 0) }
    show(figures)

    println("\n5) Поворот против часовой стрелки вокруг точки (0, 0) - возврат к шагу 3")
    figures.filterIsInstance<Transforming>().forEach { it.rotate(RotateDirection.CounterClockwise, 0, 0) }
    show(figures)

    println("\n6) Поворот прямоугольника вокруг точки (3, -3) - как на рисунке figury.png")
    val example = Rect(2, 2, 4, 2)
    println("   исходный:  $example")
    example.rotate(RotateDirection.Clockwise, 3, -3)
    println("   CW:        $example")
    example.rotate(RotateDirection.CounterClockwise, 3, -3)
    println("   CCW:       $example")

    println("\n7) Площади фигур (метод абстрактного класса Figure)")
    figures.forEach { println("   ${it::class.simpleName}: area() = ${it.area()}") }
}

private fun show(figures: Array<Figure>) {
    figures.forEach { println("   $it") }
}
