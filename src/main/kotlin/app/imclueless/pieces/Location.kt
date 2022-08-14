package app.imclueless.pieces

enum class Location(override val asString: String): PieceType {
    BALLROOM("nuclear-power-plant"),
    BILLIARD_ROOM("simpson-house"),
    CONSERVATORY("retirement-castle"),
    DINING_ROOM("bowl-a-rama"),
    HALL("burns-manor"),
    KITCHEN("kwik-e-mart"),
    LOUNGE("krustylu-studios"),
    LIBRARY("frying-dutchman"),
    STUDY("androids-dungeon");
}