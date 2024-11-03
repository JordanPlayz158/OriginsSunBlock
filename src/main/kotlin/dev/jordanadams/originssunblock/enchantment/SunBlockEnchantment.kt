package dev.jordanadams.originssunblock.enchantment

import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.EquipmentSlot


class SunBlockEnchantment : Enchantment(Rarity.RARE,
  EnchantmentTarget.ARMOR_HEAD,
  // Check Enchantments#ALL_ARMOR for where the list came from
  arrayOf(EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET)
) {
  override fun getMinPower(level: Int): Int {
    return 1
  }

  override fun getMaxLevel(): Int {
    return 1
  }
}