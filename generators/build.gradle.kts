plugins {
  kotlin("multiplatform")
  kotlin("plugin.serialization")
  id("io.bkbn.sourdough.library.mpp")
  id("io.kotest.multiplatform")
  id("io.gitlab.arturbosch.detekt")
  id("com.adarshr.test-logger")
  id("maven-publish")
  id("java-library")
  id("signing")
}

sourdoughLibrary {
  githubOrg.set("unredundant")
  githubRepo.set("satisfaketion")
  libraryName.set("Satisfaketion")
  libraryDescription.set("A collection of useful Satisfaketion generators")
  licenseName.set("MIT License")
  licenseUrl.set("https://mit-license.org")
  developerId.set("unredundant")
  developerName.set("Ryan Brink")
  developerEmail.set("admin@bkbn.io")
}

dependencies {
  detektPlugins(group = "io.gitlab.arturbosch.detekt", name = "detekt-formatting", version = "1.22.0")
}

kotlin {
  jvm {
    compilations.all {
      kotlinOptions.jvmTarget = "17"
    }
    withJava()
    testRuns["test"].executionTask.configure {
      useJUnitPlatform()
    }
  }
  js {
    // browser()
    nodejs()
  }
  sourceSets {
    val commonMain by getting {
      resources.srcDirs("resources")
      dependencies {
        implementation(kotlin("stdlib"))
        implementation(projects.satisfaketionCore)
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
        implementation("com.squareup.okio:okio:3.3.0")
        implementation("co.touchlab:kermit:1.2.2")
      }
    }
    val commonTest by getting {
      dependencies {
        implementation("io.kotest:kotest-assertions-core:5.6.2")
        implementation("io.kotest:kotest-framework-engine:5.6.2")
      }
    }
    val jvmMain by getting {
      resources.srcDirs("resources")
      dependencies {
        implementation("io.kotest:kotest-runner-junit5-jvm:5.6.2")
      }
    }
    val jvmTest by getting
    val jsMain by getting {
      resources.srcDirs("resources")
      dependencies {
        implementation("com.squareup.okio:okio-nodefilesystem:3.3.0")
      }
    }
    val jsTest by getting
//    val nativeMain by getting
//    val nativeTest by getting {
//      resources.srcDirs("resources")
//    }
  }
}
