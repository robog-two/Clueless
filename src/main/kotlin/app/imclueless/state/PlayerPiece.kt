package app.imclueless.state

import app.imclueless.pieces.Character
import app.imclueless.pieces.Location

data class PlayerPiece(
    var position: Location?,
    val character: Character,
    val ownedBy: Player?,
)
