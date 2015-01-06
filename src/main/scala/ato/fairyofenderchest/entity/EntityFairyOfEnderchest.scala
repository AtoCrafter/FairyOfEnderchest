package ato.fairyofenderchest.entity

import net.minecraft.entity.Entity
import net.minecraft.entity.passive.EntityAmbientCreature
import net.minecraft.world.World

class EntityFairyOfEnderchest(world: World) extends EntityAmbientCreature(world) {

  override def canBePushed: Boolean = false

  protected override def collideWithEntity(entity: Entity) = {}

  protected override def collideWithNearbyEntities = {}

  override def isAIEnabled: Boolean = true

  protected override def updateAITasks = {
    super.updateAITasks()
  }

  protected override def canTriggerWalking: Boolean = false

  protected override def fall(f: Float) = {}

  protected override def updateFallState(d: Double, b: Boolean) = {}

  override def doesEntityNotTriggerPressurePlate: Boolean = true
}
