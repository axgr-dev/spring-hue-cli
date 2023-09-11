package dev.axgr.hue

import org.springframework.shell.command.annotation.Command
import org.springframework.shell.table.ArrayTableModel
import org.springframework.shell.table.BorderStyle
import org.springframework.shell.table.TableBuilder

@Command(command = ["groups"], description = "Manage groups")
class GroupCommands(private val hue: Hue) {

  @Command(command = ["ls"], description = "List available groups")
  fun list(): String {
    val model = ArrayTableModel(hue.groups().map { arrayOf(it.id, it.name) }.toTypedArray())
    return TableBuilder(model)
      .addInnerBorder(BorderStyle.air)
      .build()
      .render(120)
  }
}

@Command(command = ["scenes"], description = "Manage scenes")
class SceneCommands(private val hue: Hue) {

  @Command(command = ["ls"], description = "List available scenes")
  fun list(): String {
    val model = ArrayTableModel(hue.scenes().map { arrayOf(it.id, it.name) }.toTypedArray())
    return TableBuilder(model)
      .addInnerBorder(BorderStyle.air)
      .build()
      .render(120)
  }

  @Command(command = ["enable"], description = "Enable a scene for a group")
  fun enable(scene: String, group: String) {
    hue.enable(scene, group)
  }
}
