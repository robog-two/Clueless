package app.imclueless.state

import app.imclueless.pieces.Character

data class Player (
    val numberInTurn: Int,
    val piece: PlayerPiece
)