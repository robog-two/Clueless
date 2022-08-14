package app.imclueless.pieces

enum class Character(override val asString: String): PieceType {
    PEACOCK("peacock"),
    MUSTARD("mustard"),
    GREEN("green"),
    PLUM("plum"),
    SCARLET("scarlet"),
    WHITE("white");
}