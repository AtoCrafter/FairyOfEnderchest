package ato.fairyofenderchest.eventhandler

import cpw.mods.fml.common.eventhandler.SubscribeEvent
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import net.minecraftforge.event.entity.player.EntityItemPickupEvent

import scala.math._

class InventoryBalancer {

  @SubscribeEvent
  def onItemPickup(event: EntityItemPickupEvent) = {
    val player = event.entityPlayer
    val item = event.item.getEntityItem
    val enderchest = player.getInventoryEnderChest

    if (!player.inventory.addItemStackToInventory(item)) {
      storeToEnderchest(enderchest, item)
    }
  }

  private def storeToEnderchest(inventory: IInventory, item: ItemStack) = {
    for (i <- 0 to inventory.getSizeInventory - 1 if item.stackSize > 0) {
      Option(inventory.getStackInSlot(i)) match {
        case Some(stored) if canMerge(item, stored) => {
          val trans = max(0, min(min(stored.getMaxStackSize, inventory.getInventoryStackLimit) - stored.stackSize, item.stackSize))
          stored.stackSize += trans
          item.stackSize -= trans
        }
        case _ =>
      }
    }
    for (i <- 0 to inventory.getSizeInventory - 1 if item.stackSize > 0) {
      Option(inventory.getStackInSlot(i)) match {
        case None => inventory.setInventorySlotContents(i, item.splitStack(min(inventory.getInventoryStackLimit, item.stackSize)))
        case _ =>
      }
    }
  }

  private def canMerge(item1: ItemStack, item2: ItemStack): Boolean =
    item1.isItemEqual(item2) && item1.isStackable && ItemStack.areItemStackTagsEqual(item1, item2)
}
