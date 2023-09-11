package dev.axgr

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.shell.command.annotation.CommandScan

@CommandScan
@SpringBootApplication(proxyBeanMethods = false)
class App

fun main(args: Array<String>) {
  runApplication<App>(*args)
}
