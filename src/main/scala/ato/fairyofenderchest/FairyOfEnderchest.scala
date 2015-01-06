package ato.fairyofenderchest

import ato.fairyofenderchest.proxy.ProxyCommon
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.{Mod, SidedProxy}

@Mod(modid = "FairyOfEnderchest", modLanguage = "scala")
object FairyOfEnderchest {

  @SidedProxy(
    serverSide = "ato.fairyofenderchest.proxy.ProxyCommon",
    clientSide = "ato.fairyofenderchest.proxy.ProxyClient"
  )
  var proxy: ProxyCommon = _

  @EventHandler
  def preInit(event: FMLInitializationEvent): Unit = {
    proxy.register()
  }
}
