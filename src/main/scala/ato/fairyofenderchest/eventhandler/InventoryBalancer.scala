package ato.fairyofenderchest.eventhandler

import ato.fairyofenderchest.FairyOfEnderchest
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.inventory.IInventory
import net.minecraft.item.{ItemBlock, ItemStack}
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent
import net.minecraftforge.event.entity.player.EntityItemPickupEvent

import scala.math._

class InventoryBalancer {

  @SubscribeEvent
  def onItemPickup(event: EntityItemPickupEvent) = {
    val player = event.entityPlayer
    val item = event.item.getEntityItem
    val enderchest = player.getInventoryEnderChest

    if (!player.inventory.addItemStackToInventory(item) && hasFairyAndChest(player)) {
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
    item1 != null && item2 != null &&
      item1.isItemEqual(item2) && item1.isStackable && ItemStack.areItemStackTagsEqual(item1, item2)

  @SubscribeEvent
  def onUpdate(event: LivingUpdateEvent) = event.entityLiving match {
    case player: EntityPlayer if hasFairyAndChest(player) => {
      val time = player.worldObj.getWorldTime
      val inventory = player.inventory
      val enderchest = player.getInventoryEnderChest

      val i = (time % inventory.getSizeInventory).toInt
      if (inventory.getStackInSlot(i) != null) {
        val item = inventory.getStackInSlot(i)
        for (j <- 0 to enderchest.getSizeInventory - 1 if item.stackSize < item.getMaxStackSize) {
          val stored = enderchest.getStackInSlot(j)
          if (canMerge(item, stored)) {
            val trans = max(0, min(min(item.getMaxStackSize, inventory.getInventoryStackLimit) - item.stackSize, stored.stackSize))
            item.stackSize += trans
            stored.stackSize -= trans
            if (stored.stackSize <= 0) {
              enderchest.setInventorySlotContents(j, null)
              enderchest.markDirty()
            }
          }
        }
      }
    }
    case _ =>
  }

  private def hasFairyAndChest(player: EntityPlayer): Boolean = {
    var hasFairy = false
    var hasChest = false

    val inventory = player.inventory
    for (i <- 0 to inventory.getSizeInventory - 1) {
      val itemstack = inventory.getStackInSlot(i)
      if (itemstack != null) {
        itemstack.getItem match {
          case FairyOfEnderchest.itemFairy => hasFairy = true
          case itemblock: ItemBlock if itemblock.field_150939_a == Blocks.ender_chest => hasChest = true
          case _ =>
        }
      }
    }

    return hasFairy && hasChest
  }
}
