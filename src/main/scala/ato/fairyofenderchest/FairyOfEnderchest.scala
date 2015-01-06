package ato.fairyofenderchest

import ato.fairyofenderchest.eventhandler.InventoryBalancer
import ato.fairyofenderchest.item.ItemEggFairyOfEnderchest
import ato.fairyofenderchest.proxy.ProxyCommon
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.registry.GameRegistry
import cpw.mods.fml.common.{Mod, SidedProxy}
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraftforge.common.MinecraftForge

@Mod(modid = "FairyOfEnderchest", modLanguage = "scala")
object FairyOfEnderchest {

  @SidedProxy(
    serverSide = "ato.fairyofenderchest.proxy.ProxyCommon",
    clientSide = "ato.fairyofenderchest.proxy.ProxyClient"
  )
  var proxy: ProxyCommon = _
  val itemFairy = new ItemEggFairyOfEnderchest()

  @EventHandler
  def preInit(event: FMLInitializationEvent): Unit = {
    proxy.register()

    GameRegistry.registerItem(itemFairy, "FairyOfEnderchestEgg")
    GameRegistry.addShapelessRecipe(new ItemStack(itemFairy), Items.egg, Items.ender_eye)

    MinecraftForge.EVENT_BUS.register(new InventoryBalancer())
  }
}
