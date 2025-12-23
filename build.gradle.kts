plugins {
    application
}

repositories {
    mavenCentral()
    maven{
        url = uri("https://repo.jmonkeyengine.org/maven2")
    }
}

dependencies {
    implementation("org.jmonkeyengine:jme3-core:3.6.1-stable")
    implementation("org.jmonkeyengine:jme3-desktop:3.6.1-stable")
    implementation("org.jmonkeyengine:jme3-lwjgl3:3.6.1-stable")
}

application {
    mainClass.set("game.Main")
}
