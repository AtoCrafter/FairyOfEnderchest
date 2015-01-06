package ato.fairyofenderchest.client

import cpw.mods.fml.relauncher.{Side, SideOnly}
import net.minecraft.client.renderer.entity.RenderLiving
import net.minecraft.entity.Entity
import net.minecraft.util.ResourceLocation

@SideOnly(Side.CLIENT)
class RenderFairyOfEnderchest extends RenderLiving(new ModelFairy(), 0.6f) {

  val texture = new ResourceLocation("textures/entity/wolf/wolf.png")

  override def getEntityTexture(p_110775_1_ : Entity): ResourceLocation = texture
}
