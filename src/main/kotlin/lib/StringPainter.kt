package lib

object StringPainter {
    private const val RESET = "\u001B[0m"
    fun paint(text: String): String {
        return text + RESET
    }

    fun paint(text: String, textColor: TextColor): String {
        return textColor.color + text + RESET
    }

    fun paint(text: String, textColor: TextColor, backgroundColor: BackgroundColor): String {
        return backgroundColor.color + textColor.color + text + RESET
    }

    enum class TextColor(val color: String) {
        TXT_BLACK("\u001B[30m"),
        TXT_RED("\u001B[31m"),
        TXT_GREEN("\u001B[32m"),
        TXT_YELLOW("\u001B[33m"),
        TXT_BLUE("\u001B[34m"),
        TXT_PURPLE("\u001B[35m"),
        TXT_CYAN("\u001B[36m"),
        TXT_WHITE("\u001B[37m")

    }

    enum class BackgroundColor(val color: String) {
        BG_BLACK("\u001B[40m"),
        BG_RED("\u001B[41m"),
        BG_GREEN("\u001B[42m"),
        BG_YELLOW("\u001B[43m"),
        BG_BLUE("\u001B[44m"),
        BG_PURPLE("\u001B[45m"),
        BG_CYAN("\u001B[46m"),
        BG_WHITE("\u001B[47m")
    }
}
