package app.imclueless.state

import app.imclueless.pieces.Character
import app.imclueless.pieces.Location
import app.imclueless.pieces.PieceType
import app.imclueless.pieces.Weapon

data class Possibility(
    val character: Character,
    val location: Location,
    val weapon: Weapon
) {
    fun contains(pieceType: PieceType?): Boolean {
        if (pieceType == null) return true
        return (character == pieceType) ||
                (location == pieceType) ||
                (weapon == pieceType)
    }

    override fun toString(): String {
        return "${character.asString} with ${weapon.asString} at ${location.asString}"
    }
}
