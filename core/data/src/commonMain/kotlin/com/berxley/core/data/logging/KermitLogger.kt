package com.berxley.core.data.logging

import com.berxley.core.domain.logging.ChirpLogger
import co.touchlab.kermit.Logger

object KermitLogger: ChirpLogger {

    override fun debug(message: String) {
        Logger.d(message)
    }

    override fun info(message: String) {
        Logger.i(message)
    }

    override fun warn(message: String) {
        Logger.w(message)
    }

    override fun error(message: String, throwable: Throwable?) {
        Logger.e(message, throwable)
    }
}