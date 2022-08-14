package app.imclueless.pieces

enum class Weapon(override val asString: String): PieceType {
    REVOLVER("slingshot"),
    DAGGER("saxophone"),
    LEAD_PIPE("donut"),
    ROPE("necklace"),
    CANDLESTICK("extend-o-glove"),
    WRENCH("plutonium-rod");
}