name := "SMAF_Core"

organization := "com.gmail.nmarshall23"

version := "0.2.2"

licenses := Seq("GPL v2+" -> url("http://www.gnu.org/licenses/gpl-2.0.txt"))

scalaVersion := "2.9.2"

MinecraftReobfuscaterSettings

modDirectoryPath  := Path.userHome / ".minecraft" / "mods" / "1.5.2"

minecraftLibrary := "de.ocean-labs" % "mcp" % "1.5.2"

EclipseKeys.withSource := true

autoScalaLibrary := false

libraryDependencies += "de.ocean-labs" % "mcp" % "1.5.2"

libraryDependencies += "org.lwjgl.lwjgl" % "lwjgl" % "2.9.0"

libraryDependencies += "org.lwjgl.lwjgl" % "lwjgl_util" % "2.9.0"

libraryDependencies += "net.xeon" % "jspf.core" % "1.0.3"
