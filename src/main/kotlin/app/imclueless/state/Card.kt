package app.imclueless.state

import app.imclueless.pieces.PieceType

data class Card (
    val represents: PieceType,
    val owner: Player
)