interface Transforming {
    fun resize(zoom: Int)
    // увеличивает фигуру, не перемещая, с сохранением пропорций

    fun rotate(direction: RotateDirection, centerX: Int, centerY: Int)
    // поворот фигуры вокруг точки (centerX, centerY) на 90 градусов
}

enum class RotateDirection {
    // направление вращения фигуры на 90 градусов
    Clockwise, CounterClockwise
}

// поворот точки (x, y) вокруг точки (centerX, centerY) на 90 градусов
// ось Y направлена вверх, как на рисунке figury.png
fun rotatePoint(x: Int, y: Int, direction: RotateDirection, centerX: Int, centerY: Int): Pair<Int, Int> {
    val dx = x - centerX
    val dy = y - centerY
    return when (direction) {
        RotateDirection.Clockwise -> Pair(centerX + dy, centerY - dx)
        // (dx, dy) -> (dy, -dx)
        RotateDirection.CounterClockwise -> Pair(centerX - dy, centerY + dx)
        // (dx, dy) -> (-dy, dx)
    }
}
