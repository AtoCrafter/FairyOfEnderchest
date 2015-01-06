package ato.fairyofenderchest

import ato.fairyofenderchest.eventhandler.InventoryBalancer
import ato.fairyofenderchest.proxy.ProxyCommon
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.{Mod, SidedProxy}
import net.minecraftforge.common.MinecraftForge

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

    MinecraftForge.EVENT_BUS.register(new InventoryBalancer())
  }
}
