package ato.fairyofenderchest.proxy

import ato.fairyofenderchest.client.RenderFairyOfEnderchest
import ato.fairyofenderchest.entity.EntityFairyOfEnderchest
import cpw.mods.fml.client.registry.RenderingRegistry

class ProxyClient extends ProxyCommon {

  override def register(): Unit = {
//    RenderingRegistry.registerEntityRenderingHandler(classOf[EntityFairyOfEnderchest],
//      new RenderFairyOfEnderchest())
  }
}
