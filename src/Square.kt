// позиция левого верхнего угла и длина стороны
class Square(var x: Int, var y: Int, var side: Int, id: Int = 0) :
    Movable, Transforming, Figure(id) {
    // унаследован от Figure, реализованы area() и интерфейс Transforming
    override fun area(): Float {
        return (side * side).toFloat()
    }

    override fun move(dx: Int, dy: Int) {
        x += dx; y += dy
    }

    override fun resize(zoom: Int) {
        require(zoom > 0) { "zoom должен быть положительным" }
        // все стороны квадрата увеличиваются одинаково
        side *= zoom
    }

    override fun rotate(direction: RotateDirection, centerX: Int, centerY: Int) {
        // все четыре угла квадрата
        val corners = listOf(
            rotatePoint(x, y, direction, centerX, centerY),
            rotatePoint(x + side, y, direction, centerX, centerY),
            rotatePoint(x + side, y + side, direction, centerX, centerY),
            rotatePoint(x, y + side, direction, centerX, centerY)
        )
        // сторона при повороте не меняется, меняется только положение
        x = corners.minOf { it.first }
        y = corners.minOf { it.second }
    }

    override fun toString(): String {
        return "Square(id=$id, x=$x, y=$y, side=$side, area=${area()})"
    }
}
