// сочетание определения класса и конструктора одновременно объявляет переменные и задаёт их значения
class Rect(var x: Int, var y: Int, var width: Int, var height: Int, id: Int = 0) :
    Movable, Transforming, Figure(id) {
    // реализован интерфейс Transforming, класс унаследован от Figure
    var color: Int = -1 // при объявлении каждое поле нужно инициализировать

    lateinit var name: String // значение на момент определения неизвестно (только для объектных типов)
    // дополнительный конструктор вызывает основной
    constructor(rect: Rect) : this(rect.x, rect.y, rect.width, rect.height, rect.id)

    // нужно явно указывать, что вы переопределяете метод
    override fun move(dx: Int, dy: Int) {
        x += dx; y += dy
    }

    // для каждого класса area() определяется по-своему
    override fun area(): Float {
        return (width * height).toFloat() // требуется явное приведение к вещественному числу
    }

    override fun resize(zoom: Int) {
        require(zoom > 0) { "zoom должен быть положительным" }
        // фигура увеличивается, позиция (x, y) не меняется, пропорции сохраняются
        width *= zoom
        height *= zoom
    }

    override fun rotate(direction: RotateDirection, centerX: Int, centerY: Int) {
        // поворачиваем все четыре угла прямоугольника и берём новую границу фигур
        val corners = listOf(
            rotatePoint(x, y, direction, centerX, centerY),
            rotatePoint(x + width, y, direction, centerX, centerY),
            rotatePoint(x + width, y + height, direction, centerX, centerY),
            rotatePoint(x, y + height, direction, centerX, centerY)
        )
        val minX = corners.minOf { it.first }
        val minY = corners.minOf { it.second }
        val maxX = corners.maxOf { it.first }
        val maxY = corners.maxOf { it.second }
        x = minX; y = minY
        // при повороте на 90 градусов ширина и высота меняются местами
        width = maxX - minX
        height = maxY - minY
    }

    override fun toString(): String {
        return "Rect(id=$id, x=$x, y=$y, w=$width, h=$height, area=${area()})"
    }
}
