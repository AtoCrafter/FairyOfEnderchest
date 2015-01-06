package ato.fairyofenderchest

import ato.fairyofenderchest.entity.EntityFairyOfEnderchest
import ato.fairyofenderchest.proxy.ProxyCommon
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.registry.EntityRegistry
import cpw.mods.fml.common.{Mod, SidedProxy}
import net.minecraft.entity.EnumCreatureType
import net.minecraft.world.biome.BiomeGenBase

@Mod(modid = "FairyOfEnderchest", modLanguage = "scala")
object FairyOfEnderchest {

  @SidedProxy(
    serverSide = "ato.fairyofenderchest.proxy.ProxyCommon",
    clientSide = "ato.fairyofenderchest.proxy.ProxyClient"
  )
  var proxy: ProxyCommon = _

  @EventHandler
  def preInit(event: FMLInitializationEvent): Unit = {
    EntityRegistry.registerModEntity(classOf[EntityFairyOfEnderchest], "FairyOfEnderchest", 0, this, 250, 1, false)
    EntityRegistry.addSpawn(classOf[EntityFairyOfEnderchest], 20, 100, 100, EnumCreatureType.creature, BiomeGenBase.plains)
    proxy.register()
  }
}