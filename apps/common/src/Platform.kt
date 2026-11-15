package nl.entreco.elchrono

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
