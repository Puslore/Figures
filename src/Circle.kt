// позиция центра окружности и её радиус
class Circle(var x: Int, var y: Int, var radius: Int, id: Int = 0) :
    Movable, Transforming, Figure(id) {
    // реализован интерфейс Transforming, класс унаследован от Figure
    override fun area(): Float {
        return (kotlin.math.PI * radius * radius).toFloat()
    }

    override fun move(dx: Int, dy: Int) {
        x += dx; y += dy
    }

    override fun resize(zoom: Int) {
        require(zoom > 0) { "zoom должен быть положительным" }
        // у окружности пропорции (один радиус) сохраняются всегда
        radius *= zoom
    }

    override fun rotate(direction: RotateDirection, centerX: Int, centerY: Int) {
        // для окружности важна только точка её центра
        val (newX, newY) = rotatePoint(x, y, direction, centerX, centerY)
        x = newX; y = newY
    }

    override fun toString(): String {
        return "Circle(id=$id, center=($x, $y), r=$radius, area=${area()})"
    }
}
